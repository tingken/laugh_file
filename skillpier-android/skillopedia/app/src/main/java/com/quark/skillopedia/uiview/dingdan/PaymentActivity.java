package com.quark.skillopedia.uiview.dingdan;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.paypal.android.sdk.payments.PayPalAuthorization;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalFuturePaymentActivity;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalProfileSharingActivity;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.quark.api.auto.bean.ComfiyPayRequest;
import com.quark.api.auto.bean.ComfiyPayResponse;
import com.quark.api.auto.bean.PayMoneyRequest;
import com.quark.api.auto.bean.PayMoneyResponse;
import com.quark.api.auto.bean.VerifyPaypalPaymentsRequest;
import com.quark.api.auto.bean.VerifyPaypalPaymentsResponse;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseActivity;

import org.json.JSONException;

import java.math.BigDecimal;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * @author pan
 *         created at 2016/5/30 0030
 * @支付界面
 */
public class PaymentActivity extends BaseActivity {

    String orders_ids;  //支付的订单集合
    String realMoney;
    String orderId;
    String paytype = "1";  //1-支付宝，2-visa，3-paypal
    public static PaymentActivity instance;


    @InjectView(R.id.left_img)
    ImageView leftImg;
    @InjectView(R.id.left)
    LinearLayout left;
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.sign)
    TextView sign;
    @InjectView(R.id.rightrig)
    ImageView rightrig;
    @InjectView(R.id.right)
    LinearLayout right;
    @InjectView(R.id.head)
    RelativeLayout head;
    @InjectView(R.id.real_tv)
    TextView realTv;
    @InjectView(R.id.original_tv)
    TextView originalTv;
    @InjectView(R.id.visa_ly)
    LinearLayout visaLy;
    @InjectView(R.id.paypal_ly)
    LinearLayout paypalLy;

    /*==========PayPal=====================*/
    private static final String TAG = "paymentExample";
    /**
     * - Set to PayPalConfiguration.ENVIRONMENT_PRODUCTION to move real money.
     * <p/>
     * - Set to PayPalConfiguration.ENVIRONMENT_SANDBOX to use your test credentials
     * from https://developer.paypal.com
     * <p/>
     * - Set to PayPalConfiguration.ENVIRONMENT_NO_NETWORK to kick the tires
     * without communicating to PayPal's servers.
     */
    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_SANDBOX;   //沙盒环境 上线要换成上线环境
//    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_PRODUCTION;   //沙盒环境 上线要换成上线环境
    // note that these credentials will differ between live & sandbox environments.
    private static final String CONFIG_CLIENT_ID = "ARfQSB9_dtIZnqQst128jO6F_L0-1Pd_dFF0nGhvZpBfIgCi3VjyVs0Q8UAI_KVo2uuT2EJu-YNloMvL";
    //更换账号后的client_id 正式环境
//    private static final String CONFIG_CLIENT_ID = "Af6T3jz6-gYqh3XTT-ByFStHrcqNE1DmjebIekF2SXojnkGi-aFYEiw8nBElsGv7GK32UW0pk8aQERAA";

    private static final int REQUEST_CODE_PAYMENT = 1;
    private static final int REQUEST_CODE_FUTURE_PAYMENT = 2;
    private static final int REQUEST_CODE_PROFILE_SHARING = 3;

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(CONFIG_ENVIRONMENT)
            .clientId(CONFIG_CLIENT_ID)
                    // The following are only used in PayPalFuturePaymentActivity.
            .merchantName("Skillopedia")
            .acceptCreditCards(false)
            .merchantPrivacyPolicyUri(Uri.parse("https://www.example.com/privacy"))
            .merchantUserAgreementUri(Uri.parse("https://www.example.com/legal"));

    /*==========PayPal end=====================*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.inject(this);
        orders_ids = (String) getValue4Intent("orders_ids");
        setTopTitle("Payment");
        setBackButton();
        instance = this;
        payMoneyRequest();
//        getAlipayInfoRequest();

        /*===paypal====*/
        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);
        /*====paypal=====*/

    }

    @OnClick({R.id.visa_ly, R.id.paypal_ly})
    public void alsp(View v) {
        switch (v.getId()) {
//            case R.id.alypay_ly:
//                paytype = "1";
//                comfiyPayRequest();
//                break;
            case R.id.visa_ly:
                paytype = "2";
                comfiyPayRequest();

                break;
            case R.id.paypal_ly:
                paytype = "3";
                comfiyPayRequest();
//                startActivityByClass(SampleActivity.class);
                break;
        }
    }

    /**
     * 获取金额
     *
     * @author leon
     * @time 2016/7/6 0006 下午 4:27
     */
    public void payMoneyRequest() {
        PayMoneyRequest rq = new PayMoneyRequest();
        rq.setOrders_ids(orders_ids);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlerpay);
    }

    private final AsyncHttpResponseHandler mHandlerpay = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, PaymentActivity.this, PayMoneyResponse.class);
            if (kd != null) {
                PayMoneyResponse info = (PayMoneyResponse) kd;
                if (info.getStatus() == 1) {
                    realMoney = info.getTotal_total_session_rate();
                    realTv.setText("$" + info.getTotal_total_session_rate());
                    originalTv.setText("$" + info.getTotal_original_total_session_rate());
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

    /**
     * 支付 获取订单id
     *
     * @author leon
     * @time 2016/7/6 0006 下午 4:31
     */
    public void comfiyPayRequest() {
        ComfiyPayRequest rq = new ComfiyPayRequest();
        rq.setOrders_ids(orders_ids);
        rq.setPay_type(paytype);
        rq.setTotal_money(realMoney);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlercomfy);
    }

    private final AsyncHttpResponseHandler mHandlercomfy = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, PaymentActivity.this, ComfiyPayResponse.class);
            if (kd != null) {
                ComfiyPayResponse info = (ComfiyPayResponse) kd;
                if (info.getStatus() == 1) {
                    orderId = info.getOrders_ids();
                    if (paytype.equals("3")) { //paypal
                        onBuyPressed();
                    } else if (paytype.equals("2")) { //visa
                        Bundle bundle = new Bundle();
                        bundle.putString("realMoney", realMoney);
                        bundle.putString("orderId", orderId);
                        startActivityByClass(VisaPayActivity.class, bundle);
                    }
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

    /*=========================paypal start=====================*/

    public void onBuyPressed() {
        /*
         * PAYMENT_INTENT_SALE will cause the payment to complete immediately.
         * Change PAYMENT_INTENT_SALE to
         *   - PAYMENT_INTENT_AUTHORIZE to only authorize payment and capture funds later.
         *   - PAYMENT_INTENT_ORDER to create a payment for authorization and capture
         *     later via calls from your server.
         *
         * Also, to include additional payment details and an item list, see getStuffToBuy() below.
         */
        PayPalPayment thingToBuy = getThingToBuy(PayPalPayment.PAYMENT_INTENT_SALE);

        /*
         * See getStuffToBuy(..) for examples of some available payment options.
         */

        Intent intent = new Intent(PaymentActivity.this, com.paypal.android.sdk.payments.PaymentActivity.class);

        // send the same configuration for restart resiliency
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        intent.putExtra(com.paypal.android.sdk.payments.PaymentActivity.EXTRA_PAYMENT, thingToBuy);

        startActivityForResult(intent, REQUEST_CODE_PAYMENT);
    }

    private PayPalPayment getThingToBuy(String paymentIntent) {
        return new PayPalPayment(new BigDecimal(realMoney), "USD", "课程费用",
                paymentIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                PaymentConfirmation confirm =
                        data.getParcelableExtra(com.paypal.android.sdk.payments.PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirm != null) {
                    try {
                        Log.i(TAG, confirm.toJSONObject().toString(4));
                        Log.i(TAG, confirm.getPayment().toJSONObject().toString(4));
                        /**
                         *  TODO: send 'confirm' (and possibly confirm.getPayment() to your server for verification
                         * or consent completion.
                         * See https://developer.paypal.com/webapps/developer/docs/integration/mobile/verify-mobile-payment/
                         * for more details.
                         *
                         * For sample mobile backend interactions, see
                         * https://github.com/paypal/rest-api-sdk-python/tree/master/samples/mobile_backend
                         */
                        displayResultText("PaymentConfirmation info received from PayPal");

                        verifyPaypalPaymentsRequest(confirm.getProofOfPayment().getPaymentId());
                    } catch (JSONException e) {
                        Log.e(TAG, "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i(TAG, "The user canceled.");
            } else if (resultCode == com.paypal.android.sdk.payments.PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i(
                        TAG,
                        "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        } else if (requestCode == REQUEST_CODE_FUTURE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                PayPalAuthorization auth =
                        data.getParcelableExtra(PayPalFuturePaymentActivity.EXTRA_RESULT_AUTHORIZATION);
                if (auth != null) {
                    try {
                        Log.i("FuturePaymentExample", auth.toJSONObject().toString(4));

                        String authorization_code = auth.getAuthorizationCode();
                        Log.i("FuturePaymentExample", authorization_code);

                        sendAuthorizationToServer(auth);
                        displayResultText("Future Payment code received from PayPal");

                    } catch (JSONException e) {
                        Log.e("FuturePaymentExample", "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("FuturePaymentExample", "The user canceled.");
            } else if (resultCode == PayPalFuturePaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i(
                        "FuturePaymentExample",
                        "Probably the attempt to previously start the PayPalService had an invalid PayPalConfiguration. Please see the docs.");
            }
        } else if (requestCode == REQUEST_CODE_PROFILE_SHARING) {
            if (resultCode == Activity.RESULT_OK) {
                PayPalAuthorization auth =
                        data.getParcelableExtra(PayPalProfileSharingActivity.EXTRA_RESULT_AUTHORIZATION);
                if (auth != null) {
                    try {
                        Log.i("ProfileSharingExample", auth.toJSONObject().toString(4));

                        String authorization_code = auth.getAuthorizationCode();
                        Log.i("ProfileSharingExample", authorization_code);

                        sendAuthorizationToServer(auth);
                        displayResultText("Profile Sharing code received from PayPal");

                    } catch (JSONException e) {
                        Log.e("ProfileSharingExample", "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("ProfileSharingExample", "The user canceled.");
            } else if (resultCode == PayPalFuturePaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i(
                        "ProfileSharingExample",
                        "Probably the attempt to previously start the PayPalService had an invalid PayPalConfiguration. Please see the docs.");
            }
        }
    }

    protected void displayResultText(String result) {
//        ((TextView)findViewById(R.id.txtResult)).setText("Result : " + result);
//        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
    }

    private void sendAuthorizationToServer(PayPalAuthorization authorization) {
        Log.e("error", "222");
        /**
         * TODO: Send the authorization response to your server, where it can
         * exchange the authorization code for OAuth access and refresh tokens.
         *
         * Your server must then store these tokens, so that your server code
         * can execute payments for this user in the future.
         *
         * A more complete example that includes the required app-server to
         * PayPal-server integration is available from
         * https://github.com/paypal/rest-api-sdk-python/tree/master/samples/mobile_backend
         */
    }

    public void verifyPaypalPaymentsRequest(String paypalId) {
        VerifyPaypalPaymentsRequest rq = new VerifyPaypalPaymentsRequest();
        rq.setOrders_id(orderId);
        rq.setPaypal_response_id(paypalId);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, PaymentActivity.this, VerifyPaypalPaymentsResponse.class);
            if (kd != null) {
                VerifyPaypalPaymentsResponse info = (VerifyPaypalPaymentsResponse) kd;
                if (info.getStatus() == 1) {
                    showTips();
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

    /*=========================paypal end=====================*/



    //=====================================支付宝支付=======================================
//    public void getAlipayInfoRequest() {
//        GetAlipayInfoRequest rq = new GetAlipayInfoRequest();
//        showWait(true);
//        QuarkApi.HttpRequest(rq, mHandlerzfb);
//    }
//
//    private final AsyncHttpResponseHandler mHandlerzfb = new AsyncHttpResponseHandler() {
//        @Override
//        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
//            Object kd = ApiResponse.get(arg2, PaymentActivity.this, GetAlipayInfoResponse.class);
//            if (kd != null) {
//                GetAlipayInfoResponse aliinfo = (GetAlipayInfoResponse) kd;
//                alipay.setOut_trade_no(orderId);
//                alipay.setAccount(aliinfo.getAlipay_account());
//                alipay.setPartner(aliinfo.getAlipay_partner());
//                alipay.setPrivate_key(aliinfo.getAlipay_private_key());
//                alipay.setPrivate_key_pkcs8(aliinfo.getAlipay_private_key_pkcs8());
//            }
//            showWait(false);
//        }
//
//        @Override
//        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
//            AppContext.showToast("网络出错" + arg0);
//            showWait(false);
//        }
//    };
//
//    CommentAliPay alipay = new CommentAliPay();
//    private static final int SDK_PAY_FLAG = 1;
//    private static final int SDK_CHECK_FLAG = 2;
//    private Handler mHandler = new Handler() {
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case SDK_PAY_FLAG: {
//                    //通知更新订单
//                    Intent intent = new Intent("fragmentThree");
//                    intent.putExtra("option", "refresh");
//                    sendBroadcast(intent);
//
//                    PayResult payResult = new PayResult((String) msg.obj);
//                    String resultInfo = payResult.getResult();
//                    String resultStatus = payResult.getResultStatus();
//                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
//                    if (TextUtils.equals(resultStatus, "9000")) {
//                        //调用服务器进行充值
//                        Toast.makeText(PaymentActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
//                        finish();
//                    } else {
//                        // 判断resultStatus 为非“9000”则代表可能支付失败
//                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
//                        if (TextUtils.equals(resultStatus, "8000")) {
//                            Toast.makeText(PaymentActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
//                        } else {
//                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
//                            Toast.makeText(PaymentActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    break;
//                }
//                case SDK_CHECK_FLAG: {
//                    Toast.makeText(PaymentActivity.this, "检查结果为：" + msg.obj, Toast.LENGTH_SHORT).show();
//                    break;
//                }
//                default:
//                    break;
//            }
//        }
//
//        ;
//    };
//
//    public void pay() {
//        String orderInfo = getOrderInfo("支付订单", "支付订单" + realMoney + "元", realMoney + "");
//        String sign = sign(orderInfo);
//        try {
//            sign = URLEncoder.encode(sign, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&"
//                + getSignType();
//
//        Runnable payRunnable = new Runnable() {
//
//            @Override
//            public void run() {
//                PayTask alipay = new PayTask(PaymentActivity.this);
//                String result = alipay.pay(payInfo);
//                Message msg = new Message();
//                msg.what = SDK_PAY_FLAG;
//                msg.obj = result;
//                mHandler.sendMessage(msg);
//            }
//        };
//        Thread payThread = new Thread(payRunnable);
//        payThread.start();
//    }
//
//    public void check(View v) {
//        Runnable checkRunnable = new Runnable() {
//
//            @Override
//            public void run() {
//                PayTask payTask = new PayTask(PaymentActivity.this);
//                boolean isExist = payTask.checkAccountIfExist();
//                Message msg = new Message();
//                msg.what = SDK_CHECK_FLAG;
//                msg.obj = isExist;
//                mHandler.sendMessage(msg);
//            }
//        };
//        Thread checkThread = new Thread(checkRunnable);
//        checkThread.start();
//    }
//
//    public void getSDKVersion() {
//        PayTask payTask = new PayTask(this);
//        String version = payTask.getVersion();
//        Toast.makeText(this, version, Toast.LENGTH_SHORT).show();
//    }
//
//    public String getOrderInfo(String subject, String body, String price) {
//        String orderInfo = "partner=" + "\"" + alipay.getPartner() + "\"";
//        orderInfo += "&seller_id=" + "\"" + alipay.getAccount() + "\"";//seller
//        orderInfo += "&out_trade_no=" + "\"" + orderId + "\"";
//        orderInfo += "&subject=" + "\"" + subject + "\"";
//        orderInfo += "&body=" + "\"" + body + "\"";
//        orderInfo += "&total_fee=" + "\"" + price + "\"";
//        orderInfo += "&notify_url=" + "\"" + ApiHttpClient.aliPayAysn + "\"";
//        orderInfo += "&service=\"mobile.securitypay.pay\"";
//        orderInfo += "&payment_type=\"1\"";
//        orderInfo += "&_input_charset=\"utf-8\"";
//        orderInfo += "&it_b_pay=\"30m\"";
//        orderInfo += "&return_url=\"m.alipay.com\"";
//        return orderInfo;
//    }
//
//    public String sign(String content) {
////			return SignUtils.sign(content, RSA_PRIVATE);
//        return SignUtils.sign(content, alipay.getPrivate_key());
//    }
//
//    public String getSignType() {
//        return "sign_type=\"RSA\"";
//    }
    //==================支付宝end==================

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
    }

    @Override
    public void onDestroy() {
        // Stop service when done
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
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
                dlg.dismiss();
                if (FillOrderActivity.instance!=null){
                    FillOrderActivity.instance.finish();
                }
                finish();
            }
        });

        negativeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dlg.dismiss();
                if (FillOrderActivity.instance!=null){
                    FillOrderActivity.instance.finish();
                }
                finish();
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

}
