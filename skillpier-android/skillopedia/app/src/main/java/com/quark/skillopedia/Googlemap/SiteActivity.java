package com.quark.skillopedia.Googlemap;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.IsFulfilTravelDistanceRequest;
import com.quark.api.auto.bean.IsFulfilTravelDistanceResponse;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseFragementActivity;
import com.quark.skillopedia.util.TLog;
import com.quark.skillopedia.util.Utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.x;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import pub.devrel.easypermissions.EasyPermissions;

import static com.quark.skillopedia.R.id.autocomplete_places;

/**
 * Created by Administrator on 2016/5/18 0018.
 * 地址界面
 */
public class SiteActivity extends BaseFragementActivity implements GoogleMap.OnMyLocationButtonClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnCameraChangeListener, EasyPermissions.PermissionCallbacks {

    @InjectView(R.id.right)
    LinearLayout rightmy;
    @InjectView(autocomplete_places)
    TextView mAutocompleteView;

    private GoogleMap mMap;
    private boolean mPermissionDenied = false;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    GoogleApiClient mGoogleApiClient;
    String premise = "", street_number = "", route = "";
    String addressStr = "";
    public static double lng;
    public static double lat;
    boolean haoll = false; //是否有經緯度 有的話識圖到經緯度上
    boolean needshow = true;//第一次進入不顯示地址
    String zone;//区
    String province;//区
    String city, area, street, address, zipcode, lngStr, latStr;
    String coure_id, from;  //from = fillOrder 订单 需要判断是否在上门范围内

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);
        ButterKnife.inject(this);

        setBackButton();

        locationpic.setVisibility(View.INVISIBLE);

        from = (String) getValue4Intent("from");
        coure_id = (String) getValue4Intent("coure_id");
        city = (String) getValue4Intent("city");    //省
        area = (String) getValue4Intent("area");    //市
        street = (String) getValue4Intent("street");
        address = (String) getValue4Intent("address");
        zipcode = (String) getValue4Intent("zipcode");
        lngStr = (String) getValue4Intent("longitude");
        latStr = (String) getValue4Intent("latitude");

//        try {
//            if (!Utils.isEmpty(lngStr)) {
//                lng = Double.valueOf(lngStr);
//            } else {
//                needshow = false;
//            }
//            if (!Utils.isEmpty(latStr)) {
//                lat = Double.valueOf(latStr);
//                haoll = true;
//            }
//            LatLngBounds AUSTRALIA = new LatLngBounds(new LatLng(lat, lng), new LatLng(lat, lng));
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(AUSTRALIA.getCenter(), 15.5f));
//        } catch (Exception e) {
//            Log.e("error", "is no double");
//        }

        if (!Utils.isEmpty(city)) {
            address = street + "," + area + "," + city;
            mAutocompleteView.setText(street + "," + area + "," + city);
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //啟動 獲取當前位置
        if (mGoogleApiClient == null) { //订单过来显示的是教练上课的位置
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        //=============================自动填充==================================
//        mPlaceGoogleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this, 0 /* clientId */, this)
//                .addApi(Places.GEO_DATA_API)
//                .build();
//        // Register a listener that receives callbacks when a suggestion has been selected
//        mAutocompleteView.setOnItemClickListener(mAutocompleteClickListener);
//        // Set up the adapter that will retrieve suggestions from the Places Geo Data API that cover
//        // the entire world.
//        mAdapter = new PlaceAutocompleteAdapter(this, mPlaceGoogleApiClient, BOUNDS_GREATER_SYDNEY, null);
//        mAutocompleteView.setAdapter(mAdapter);
    }

    @OnClick(autocomplete_places)
    public void toinputAddress(View b) {
        Intent intent = new Intent().setClass(this, InputAddressActivity.class);
        intent.putExtra("address", address);
        startActivityForResult(intent, 1001);
    }

    Marker marker;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != 0) {
            if (resultCode == 1001) {
                address = data.getStringExtra("address");
                mAutocompleteView.setText(address);
                lng = data.getDoubleExtra("lng", 0);
                lat = data.getDoubleExtra("lat", 0);
                LatLng latLng = new LatLng(lat, lng);
                if (marker!=null)
                    marker.remove();
                marker = mMap.addMarker(new MarkerOptions().position(latLng));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f));
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        try {
            if (!Utils.isEmpty(lngStr)) {
                lng = Double.valueOf(lngStr);
            } else {
                needshow = false;
            }
            if (!Utils.isEmpty(latStr)) {
                lat = Double.valueOf(latStr);
                haoll = true;
            }
            LatLngBounds AUSTRALIA = new LatLngBounds(new LatLng(lat, lng), new LatLng(lat, lng));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(AUSTRALIA.getCenter(), 12f));
            if("fillOrder".equals(from)){
                if (marker!=null)
                    marker.remove();
                marker = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(lat, lng))
                );
            }
        } catch (Exception e) {
            Log.e("error", "is no double");
        }


        mMap.setOnMyLocationButtonClickListener(this);
        CameraUpdateFactory.zoomBy(15.5f);
        enableMyLocation();
        updateMapView();
//        getData();
        mMap.getUiSettings().setZoomControlsEnabled(false);
        mMap.getUiSettings().setScrollGesturesEnabled(false);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
//监听地图滑动
//        mMap.setOnCameraChangeListener(camerachange);
//        if (!Utils.isEmpty(lngStr)){ //为空为非编辑进来的 不显示mark
//            map.addMarker(new MarkerOptions().position(new LatLng(lat, lng)));
//        }
    }

    /**
     * camera移动停止后会调用
     *
     * @author leon
     * @time 2016/7/25 0025 下午 2:04
     */
    GoogleMap.OnCameraChangeListener camerachange = new GoogleMap.OnCameraChangeListener() {
        @Override
        public void onCameraChange(CameraPosition cameraPosition) {
            Log.e("error", "OnCameraChangeListener  longitude=" + cameraPosition.target.longitude + "  latitude=" + cameraPosition.target.latitude);
//                seek();
        }
    };

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE, Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
//        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog.newInstance(true).show(getSupportFragmentManager(), "dialog");
    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            if (!haoll) {
                lng = mLastLocation.getLongitude();
                lat = mLastLocation.getLatitude();
//                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 15.5f), 4000, null);
                Log.e("error", "getLatitude=" + String.valueOf(mLastLocation.getLatitude()));
                Log.e("error", "getLongitude=" + String.valueOf(mLastLocation.getLongitude()));
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void updateMapView() {
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 15.5f), 4000, null);

        LatLngBounds AUSTRALIA = new LatLngBounds(new LatLng(lat, lng), new LatLng(lat, lng));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(AUSTRALIA.getCenter(), 12f));
    }

//    private Handler handler = new Handler() {
//        public void handleMessage(Message msg) {
//            getData();
//            super.handleMessage(msg);
//        }
//
//        ;
//    };

    // 根据经纬度获取对应的地址
    public void getData() {
        googledizi("http://maps.google.com/maps/api/geocode/json?latlng=" + lat + "," + lng + "&sensor=false");
    }

    public Object get(String ds) {
        route = street = street_number = premise = zone = province = "";
        Object obj = null;
        try {
            Log.e("error", ds);
            JSONObject js = new JSONObject(ds);
            String status = js.getString("status");
            if (status.equals("OK")) {
                JSONArray jlist = js.getJSONArray("results");
                if (jlist != null && jlist.length() > 0) {
                    JSONObject res = (JSONObject) jlist.get(0);
                    //只要街道 號碼
                    JSONArray jlistper = res.getJSONArray("address_components");
                    for (int Ii = 0; Ii < jlistper.length(); Ii++) {
                        JSONObject perdata = (JSONObject) jlistper.get(Ii);
                        JSONArray types = perdata.getJSONArray("types");
                        for (int a = 0; a < types.length(); a++) {
                            String type = (String) types.get(a);
                            if (type.equals("premise")) {  //大廈
                                premise = (String) perdata.get("long_name");
                            }
                            if (type.equals("street_number")) {//門牌號
                                street_number = (String) perdata.get("long_name");
                            }
                            if (type.equals("route")) {//街道
                                route = (String) perdata.get("long_name");
                                street = (String) perdata.get("long_name");
                            }
                            if (type.equals("postal_code")) { //邮政编码
                                zipcode = (String) perdata.get("long_name");
                            }
                            if (type.equals("sublocality")) { //区
                                zone = (String) perdata.get("long_name");
                            }
                            if (type.equals("locality")) { //市
                                area = (String) perdata.get("long_name");
                            }

                            if (type.equals("administrative_area_level_1")) { //省
                                city = (String) perdata.get("long_name");
                            }
                        }
                    }
                    if ("fillOrder".equals(from)) {
                        isFulfilTravelDistanceRequest();
                    } else {
                        setbackresult();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("error", "JSON解析出错");
        }
        return obj;
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        Log.e("error", "lati=" + cameraPosition.target.latitude + "    " + cameraPosition.target.longitude);
    }

//    public void seek() {
//        int sw = new AppParam().getScreenWidth(this);
//        int sh = new AppParam().getScreenHeight(this);
//        int y = (int) locationpic.getY() + Utils.dip2px(this, 60);
//        Point point = new Point(sw / 2, y);
//        LatLng newPoint = mMap.getProjection().fromScreenLocation(point);
//        Log.e("error", "old=" + lat + " " + lng);
//        lat = newPoint.latitude;
//        lng = newPoint.longitude;
//        Log.e("error", "new=" + lat + " " + lng);
//        getData();
//    }

    @InjectView(R.id.locationpic)
    ImageView locationpic;

    @OnClick(R.id.right)
    public void iok(View v) {
        addressStr = mAutocompleteView.getText().toString();
        if (Utils.isEmpty(addressStr)) {
            showToast("Unable to get location information");
        } else {
            getData();

        }
    }

    public void isFulfilTravelDistanceRequest() {
        IsFulfilTravelDistanceRequest rq = new IsFulfilTravelDistanceRequest();
        rq.setCourse_id(coure_id);
        rq.setLatitude(lat + "");
        rq.setLongitude(lng + "");
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlertotra);
    }

    private final AsyncHttpResponseHandler mHandlertotra = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, SiteActivity.this, IsFulfilTravelDistanceResponse.class);
            if (kd != null) {
                IsFulfilTravelDistanceResponse info = (IsFulfilTravelDistanceResponse) kd;
                if (info.getStatus() != 1) {
                    showToast(info.getMessage());
                } else {
                    setbackresult();
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

    public void setbackresult() {
        showToast(addressStr);
        Intent intent = new Intent();
        intent.putExtra("city", city);
        intent.putExtra("area", area);
        intent.putExtra("street", street);
        intent.putExtra("address", addressStr);
        intent.putExtra("zipcode", zipcode);
        intent.putExtra("longitude", lng + "");
        intent.putExtra("latitude", lat + "");
        setResult(102, intent);
        finish();
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
    }

    public void googledizi(String partUrl) {
        Log.e("error", "请求地址：" + partUrl);
        org.xutils.http.RequestParams params = new org.xutils.http.RequestParams(partUrl);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                TLog.error(result);
                get(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
//                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
//                Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFinished() {

            }
        });
    }

    //====================自动填充=============================
//    GoogleApiClient mPlaceGoogleApiClient;
//    private PlaceAutocompleteAdapter mAdapter;
//    boolean fromTianchong = false;
//    private static final LatLngBounds BOUNDS_GREATER_SYDNEY = new LatLngBounds(
//            new LatLng(-34.041458, 150.790100), new LatLng(-33.682247, 151.383362));
//
//    /**
//     * Listener that handles selections from suggestions from the AutoCompleteTextView that
//     * displays Place suggestions.
//     * Gets the place id of the selected item and issues a request to the Places Geo Data API
//     * to retrieve more details about the place.
//     *
//     * @see com.google.android.gms.location.places.GeoDataApi#getPlaceById(com.google.android.gms.common.api.GoogleApiClient,
//     * String...)
//     */
//    private AdapterView.OnItemClickListener mAutocompleteClickListener
//            = new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            /*
//             Retrieve the place ID of the selected item from the Adapter.
//             The adapter stores each Place suggestion in a AutocompletePrediction from which we
//             read the place ID and title.
//              */
//            final AutocompletePrediction item = mAdapter.getItem(position);
//            final String placeId = item.getPlaceId();
//            final CharSequence primaryText = item.getPrimaryText(null);
//
//            Log.i(TAG, "Autocomplete item selected: " + primaryText);
//
//            /*
//             Issue a request to the Places Geo Data API to retrieve a Place object with additional
//             details about the place.
//              */
//            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi.getPlaceById(mPlaceGoogleApiClient, placeId);
////            PendingResult result = Places.GeoDataApi.getAutocompletePredictions(mGoogleApiClient, query, mBounds, mAutocompleteFilter);
//            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
////            Toast.makeText(getApplicationContext(), "Clicked: " + primaryText, Toast.LENGTH_SHORT).show();
//            Log.i(TAG, "Called getPlaceById to get Place details for " + placeId);
//        }
//    };
//
//    /**
//     * Callback for results from a Places Geo Data API query that shows the first place result in
//     * the details view on screen.
//     */
//    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback
//            = new ResultCallback<PlaceBuffer>() {
//        @Override
//        public void onResult(PlaceBuffer places) {
//            if (!places.getStatus().isSuccess()) {
//                // Request did not complete successfully
//                Log.e(TAG, "Place query did not complete. Error: " + places.getStatus().toString());
//                places.release();
//                return;
//            }
//            final Place place = places.get(0);
//            LatLng latitude = place.getLatLng();
//            lat = latitude.latitude;
//            lng = latitude.longitude;
//            Log.i(TAG, "Place details received: " +place.getAttributions().toString()+"  "+place.getAddress().toString()+"  "+place.getLocale().getCountry()+" "+ place.getName()+"  ("+place.getLatLng().latitude+","+place.getLatLng().longitude+")");
//            fromTianchong = true;
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 15.5f));
//            places.release();
//        }
//    };
//    //====================自动填充 end=========================
}
