package com.quark.skillopedia.uiview.dingdan;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.VerifyVisaPaymentsRequest;
import com.quark.api.auto.bean.VerifyVisaPaymentsResponse;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.util.Utils;

import net.authorize.acceptsdk.AcceptSDKApiClient;
import net.authorize.acceptsdk.datamodel.merchant.ClientKeyBasedMerchantAuthentication;
import net.authorize.acceptsdk.datamodel.transaction.CardData;
import net.authorize.acceptsdk.datamodel.transaction.EncryptTransactionObject;
import net.authorize.acceptsdk.datamodel.transaction.TransactionObject;
import net.authorize.acceptsdk.datamodel.transaction.TransactionType;
import net.authorize.acceptsdk.datamodel.transaction.callbacks.EncryptTransactionCallback;
import net.authorize.acceptsdk.datamodel.transaction.response.EncryptTransactionResponse;
import net.authorize.acceptsdk.datamodel.transaction.response.ErrorTransactionResponse;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * @author pan
 *         created at 2016/5/30 0030
 * @确认下单
 */
public class VisaPayActivity extends BaseActivity implements View.OnClickListener, EncryptTransactionCallback {
    @InjectView(R.id.cardnumber)
    EditText cardnumberView;
    @InjectView(R.id.month)
    EditText monthView;
    @InjectView(R.id.year)
    EditText yearView;
    @InjectView(R.id.cvv)
    EditText cvvView;
    @InjectView(R.id.submit)
    Button submitbt;
    String realMoney, orderId, cardNumber, month, year, cvv;
    String dataValue, dataDescriptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visapay);
        ButterKnife.inject(this);
        setBackButton();
        setTopTitle("Visa payment");

        realMoney = (String) getValue4Intent("realMoney");
        orderId = (String) getValue4Intent("orderId");
        initVisa();
    }

    @OnClick(R.id.submit)
    public void sub(View b) {
        submitbt.setEnabled(false);
        cardNumber = cardnumberView.getText().toString().replace(" ", "");
        month = monthView.getText().toString();
        year = yearView.getText().toString();
        cvv = cvvView.getText().toString();
        if (check()) {
            showWait(true);
            requestToVisa();
        }else{
            submitbt.setEnabled(true);
        }
    }

    public boolean check() {
        if (Utils.isEmpty(cardNumber) || (cardNumber.length() < MIN_CARD_NUMBER_LENGTH)) {
            showToast("Please enter the correct card number！");
            return false;
        }
        if (Utils.isEmpty(month)) {
            showToast("Please input month！");
            return false;
        }
        int monthNum = Integer.parseInt(month);
        if (monthNum < 1 || monthNum > 12 || month.length() < MIN_YEAR_LENGTH) {
            showToast("Please input correct month！");
            return false;
        }

        if (Utils.isEmpty(year) || year.length() < MIN_YEAR_LENGTH) {
            showToast("Please input correct year！");
            return false;
        }
        if (Utils.isEmpty(cvv) || cvv.length() < MIN_CVV_LENGTH) {
            showToast("Please input correct cvv！");
            return false;
        }

        return true;
    }

    //调用visa获取到DataValue和DataDescriptor后调用服务器接口
    public void verifyVisaPaymentsRequest() {
        VerifyVisaPaymentsRequest rq = new VerifyVisaPaymentsRequest();
        rq.setAmount(realMoney);
        rq.setOrders_ids(orderId);
        rq.setDataValue(dataValue);
        rq.setDataDescriptor(dataDescriptor);

        QuarkApi.HttpRequest(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, VisaPayActivity.this, VerifyVisaPaymentsResponse.class);
            if (kd != null) {
                VerifyVisaPaymentsResponse info = (VerifyVisaPaymentsResponse) kd;
                if (info.getStatus() == 1) {
//                    showToast("Payment success");
                    showTips();
                } else {
                    showToast(info.getMessage());
                }
            }
            showWait(false);
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            AppContext.showToast("Network error" + arg0);
            showWait(false);
        }
    };

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
    public void showTips() {
        final Dialog dlg = new Dialog(this, R.style.ActionSheet);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.dialog_normal_layout, null);

        final int cFullFillWidth = 10000;
        layout.setMinimumWidth(cFullFillWidth);
        TextView positiveButton = (TextView) layout.findViewById(R.id.positiveButton);
        TextView negativeButton = (TextView) layout.findViewById(R.id.negativeButton);
        TextView title = (TextView) layout.findViewById(R.id.title);
        TextView message = (TextView) layout.findViewById(R.id.message);
//        title.setText("温馨提示");
//        message.setText("激活订单后，用户将可以在线完成支付。是否继续？");

        positiveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (FillOrderActivity.instance!=null){
                    FillOrderActivity.instance.finish();
                }

                if (PaymentActivity.instance!=null){
                    PaymentActivity.instance.finish();
                }
//                Intent intent = new Intent(VisaPayActivity.this, DetailsorderActivity.class);
//                intent.putExtra("orders_id",orderId);
//                startActivity(intent);
                finish();
                dlg.dismiss();
            }
        });

        negativeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (FillOrderActivity.instance!=null){
                    FillOrderActivity.instance.finish();
                }

                if (PaymentActivity.instance!=null){
                    PaymentActivity.instance.finish();
                }
//                Intent intent = new Intent(VisaPayActivity.this, DetailsorderActivity.class);
//                intent.putExtra("orders_id",orderId);
//                startActivity(intent);
                finish();
                dlg.dismiss();
            }
        });

        Window w = dlg.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.x = 0;
        final int cMakeBottom = -1000;
        lp.y = cMakeBottom;
        lp.gravity = Gravity.CENTER;
        dlg.onWindowAttributesChanged(lp);
        dlg.setCanceledOnTouchOutside(false);
        dlg.setContentView(layout);
        dlg.show();
    }

    //=================================Visa start===================================================
    private final String CLIENT_KEY =
            "52a2pKvF8xLYV59CQddU3mEk5y3C8C44Mt5ujTd7dLQmUV4V36bn4hXjwtY3Q68q";
    // replace with your CLIENT KEY
    private final String API_LOGIN_ID = "3X9Py7Fp4"; // replace with your API LOGIN_ID
//    private final String API_LOGIN_ID = "9J4X72TA5rz6qs6j"; // replace with your API LOGIN_ID

    private final int MIN_CARD_NUMBER_LENGTH = 13;
    private final int MIN_YEAR_LENGTH = 2;
    private final int MIN_CVV_LENGTH = 3;
    private final String YEAR_PREFIX = "20";

    private AcceptSDKApiClient apiClient;

    public void initVisa() {
        try {
            apiClient = new AcceptSDKApiClient.Builder(this,
                    AcceptSDKApiClient.Environment.SANDBOX).connectionTimeout(
                    4000) // optional connection time out in milliseconds
                    .build();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        setUpCreditCardEditText();
    }

    private void setUpCreditCardEditText() {
        cardnumberView.addTextChangedListener(new TextWatcher() {
            private boolean spaceDeleted;

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // check if a space was deleted
                CharSequence charDeleted = s.subSequence(start, start + count);
                spaceDeleted = " ".equals(charDeleted.toString());
            }

            public void afterTextChanged(Editable editable) {
                // disable text watcher
                cardnumberView.removeTextChangedListener(this);

                // record cursor position as setting the text in the textview
                // places the cursor at the end
                int cursorPosition = cardnumberView.getSelectionStart();
                String withSpaces = formatText(editable);
                cardnumberView.setText(withSpaces);
                // set the cursor at the last position + the spaces added since the
                // space are always added before the cursor
                cardnumberView.setSelection(cursorPosition + (withSpaces.length() - editable.length()));

                // if a space was deleted also deleted just move the cursor
                // before the space
                if (spaceDeleted) {
                    cardnumberView.setSelection(cardnumberView.getSelectionStart() - 1);
                    spaceDeleted = false;
                }

                // enable text watcher
                cardnumberView.addTextChangedListener(this);
            }

            private String formatText(CharSequence text) {
                StringBuilder formatted = new StringBuilder();
                int count = 0;
                for (int i = 0; i < text.length(); ++i) {
                    if (Character.isDigit(text.charAt(i))) {
                        if (count % 4 == 0 && count > 0) formatted.append(" ");
                        formatted.append(text.charAt(i));
                        ++count;
                    }
                }
                return formatted.toString();
            }
        });
    }

    public void requestToVisa() {
        try {
            EncryptTransactionObject transactionObject = prepareTransactionObject();

      /*
        Make a call to get Token API
        parameters:
          1) EncryptTransactionObject - The transactionObject for the current transaction
          2) callback - callback of transaction
       */
            apiClient.getTokenWithRequest(transactionObject, this);
        } catch (NullPointerException e) {
            // Handle exception transactionObject or callback is null.
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
//            if (progressDialog.isShowing()) progressDialog.dismiss();
//            e.printStackTrace();
            submitbt.setEnabled(true);
            showWait(false);
        }
    }

    /**
     * prepares a transaction object with dummy data to be used with the Gateway transactions
     */
    private EncryptTransactionObject prepareTransactionObject() {
        ClientKeyBasedMerchantAuthentication merchantAuthentication =
                ClientKeyBasedMerchantAuthentication.
                        createMerchantAuthentication(API_LOGIN_ID, CLIENT_KEY);

        // create a transaction object by calling the predefined api for creation
        return TransactionObject.
                createTransactionObject(
                        TransactionType.SDK_TRANSACTION_ENCRYPTION) // type of transaction object
                .cardData(prepareCardDataFromFields()) // card data to get Token
                .merchantAuthentication(merchantAuthentication).build();
    }

/* ---------------------- Callback Methods - Start -----------------------*/

    @Override
    public void onEncryptionFinished(EncryptTransactionResponse response) {

        dataDescriptor = response.getDataDescriptor();
        dataValue = response.getDataValue();

        verifyVisaPaymentsRequest();//请求自己的服务器
    }

    @Override
    public void onErrorReceived(ErrorTransactionResponse errorResponse) {
        String code = errorResponse.getFirstErrorMessage().getMessageCode();
        String texts = errorResponse.getFirstErrorMessage().getMessageText();
        String errorString = code + " " + texts;
        showToast("Error" + errorString);
        submitbt.setEnabled(true);
        showWait(false);
    }

/* ---------------------- Callback Methods - End -----------------------*/

    private CardData prepareCardDataFromFields() {
        return new CardData.Builder(cardNumber, month, year).cvvCode(cvv) //CVV Code is optional
                .build();
    }

    @Override
    public void onClick(View v) {

    }
//=====================================visa end=====================================================
}
