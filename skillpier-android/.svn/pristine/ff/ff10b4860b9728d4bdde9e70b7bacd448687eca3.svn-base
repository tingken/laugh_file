package com.quark.skillopedia.Googlemap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiHttpClient;
import com.quark.skillopedia.base.BaseFragementActivity;
import com.quark.skillopedia.util.TLog;
import com.quark.skillopedia.util.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2016/5/18 0018.
 * 地址界面
 */
public class InputAddressActivity extends BaseFragementActivity implements GoogleApiClient.OnConnectionFailedListener {

    @InjectView(R.id.autocomplete_places)
    AutoCompleteTextView mAutocompleteView;

    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputaddress);
        ButterKnife.inject(this);

        setBackButton();

        address = (String) getValue4Intent("address");
        if (!Utils.isEmpty(address)) {
            mAutocompleteView.setText(address);
        }
        mAutocompleteView.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
                // TODO Auto-generated method stub
                if(arg1 == EditorInfo.IME_ACTION_SEARCH){
                    address = mAutocompleteView.getText().toString();
                    if (Utils.isEmpty(address)) {
                        showToast("please input address");
                    } else {
                        analysisData();
                    }
                }
                return false;
            }

        });

        //=============================自动填充==================================
        mPlaceGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, 0 /* clientId */, this)
                .addApi(Places.GEO_DATA_API)
                .build();
        mAutocompleteView.setOnItemClickListener(mAutocompleteClickListener);
        mAdapter = new PlaceAutocompleteAdapter(this, mPlaceGoogleApiClient, BOUNDS_GREATER_SYDNEY, null);
        mAutocompleteView.setAdapter(mAdapter);

        mAutocompleteView.setFocusable(true);
        mAutocompleteView.setFocusableInTouchMode(true);
        mAutocompleteView.requestFocus();
        InputMethodManager inputManager = (InputMethodManager) mAutocompleteView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(mAutocompleteView, 0);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
                           public void run() {
                               InputMethodManager inputManager =
                                       (InputMethodManager) mAutocompleteView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                               inputManager.showSoftInput(mAutocompleteView, 0);
                           }
                       },
                998);


    }

//    @OnClick(R.id.right)
//    public void sb(View v) {
//        address = mAutocompleteView.getText().toString();
//        if (Utils.isEmpty(address)) {
//            showToast("please input address");
//        } else {
//            analysisData();
//        }
//    }

    // 根据经纬度获取对应的地址
    public void analysisData() {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + address;
        showWait(true);
        ApiHttpClient.get(url, null, analysismHandler);
    }

    private final AsyncHttpResponseHandler analysismHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            try {
                String ds = new String(arg2, "UTF-8");
                analysisData(ds);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            showWait(false);
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            AppContext.showToast("网络出错" + arg0);
            showWait(false);
        }
    };

    public void analysisData(String ds) {
        try {
            Log.e("error", ds);
            JSONObject js = new JSONObject(ds);
            String status = js.getString("status");
            if (status.equals("OK")) {
                JSONArray jlist = js.getJSONArray("results");
                if (jlist != null && jlist.length() > 0) {
                    JSONObject res = (JSONObject) jlist.get(0);
                    JSONObject geometryRes = res.getJSONObject("geometry");
                    JSONObject location = geometryRes.getJSONObject("location");
                    LatLng latLng = new LatLng(location.getDouble("lat"), location.getDouble("lng"));
                    TLog.error("latitude=" + latLng.latitude + "longitude=" + latLng.longitude);
                    showWait(false);
                    if (latLng == null) {
                        showToast("The address could not be found. Please re-enter");
                    } else {
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putString("address", address);
                        bundle.putDouble("lng", latLng.longitude);
                        bundle.putDouble("lat", latLng.latitude);
                        intent.putExtras(bundle);
                        setResult(1001, intent);
                        finish();
                    }
                }
            }else{
                showToast("The address could not be found. Please re-enter");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("error", "JSON解析出错");
            showToast("The address could not be found. Please re-enter");
        }
    }

    //====================自动填充=============================
    GoogleApiClient mPlaceGoogleApiClient;
    private PlaceAutocompleteAdapter mAdapter;
    private static final LatLngBounds BOUNDS_GREATER_SYDNEY = new LatLngBounds(
            new LatLng(25, -130), new LatLng(49, -70));
    private AdapterView.OnItemClickListener mAutocompleteClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            final AutocompletePrediction item = mAdapter.getItem(position);
            final String placeId = item.getPlaceId();
            final CharSequence primaryText = item.getPrimaryText(null);
            mAutocompleteView.setText(primaryText.toString());
            mAutocompleteView.setSelection(primaryText.toString().length());//将光标移至文字末尾

            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi.getPlaceById(mPlaceGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
        }
    };

    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback
            = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                places.release();
                return;
            }
            places.release();
        }
    };

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
    }
    //====================自动填充 end=========================
}
