package com.quark.skillopedia.uiview.fenlei.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.quark.skillopedia.Googlemap.PermissionUtils;
import com.quark.skillopedia.R;
import com.quark.skillopedia.base.BaseFragementActivity;
import com.quark.skillopedia.util.Utils;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/6/23 0023.
 * 课程详情 对应的 details片段
 */
public class DetailsFrament extends BaseFragementActivity implements OnMapReadyCallback {


    public static double lng = 113.867102;
    public static double lat = 22.567009;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    GoogleApiClient mGoogleApiClient;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_fragment_layout);
        ButterKnife.inject(this);
        setBackButton();
        setTopTitle("Position");
//        sinceTv.setText(getArguments().getString("teachingSince"));
//        durationTv.setText(getArguments().getString("sessionLength") + " minutes");
//        teacherTv.setText("Age " + getArguments().getString("teachingAge") + " and up");
////        addressTv.setText(getArguments().getString("address"));
//        travelTv.setText(getArguments().getString("yesORno"));
//
//        String additional_partner = getArguments().getString("additional_partner");
//        if ((!Utils.isEmpty(additional_partner))&&(!additional_partner.equals("0"))){
//            groupSTv.setText("Up to " + additional_partner + " ppl");
//        }else{
//            groupSTv.setVisibility(View.GONE);
//        }
//
//        if (getArguments().getString("yesORno").equals("Yes")) {
//            distanceLy.setVisibility(View.VISIBLE);
//            travelDistanceTv.setText(getArguments().getString("Distance"));
//        }else{
//            distanceLy.setVisibility(View.GONE);
//            travelDistanceTv.setText(getArguments().getString("Distance"));
//        }

        String lngStr = (String) getValue4Intent("lngStr");
        String latStr = (String) getValue4Intent("latStr");

        try {
            if (!Utils.isEmpty(lngStr)) {
                lng = Double.valueOf(lngStr);
            }
            if (!Utils.isEmpty(latStr)) {
                lat = Double.valueOf(latStr);
            }
        } catch (Exception e) {
            Log.e("error", "is no double");
        }

        //获取地图的fragment
        FragmentManager fmg = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fmg.findFragmentById(R.id.mapshow);
        mapFragment.getMapAsync(this);

    }



    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        enableMyLocation();

        map.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).icon(BitmapDescriptorFactory.fromResource(R.drawable.spotlight_poi)));
        mMap.getUiSettings().setZoomControlsEnabled(false);
        mMap.getUiSettings().setZoomGesturesEnabled(false);

//        CameraUpdateFactory.zoomBy(13);
        //滚动到上课位置
        LatLngBounds AUSTRALIA = new LatLngBounds(new LatLng(lat, lng), new LatLng(lat, lng));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(AUSTRALIA.getCenter(), 11));

        // Instantiates a new CircleOptions object and defines the center and radius
//        CircleOptions circleOptions = new CircleOptions().center(new LatLng(lat, lng)).radius(1000);
//        Circle circle = mMap.addCircle(circleOptions);
//        circle.setFillColor(R.color.map_cilcle);
//        circle.setStrokeColor(R.color.map_cilcle_wai);
//        circle.setStrokeWidth(0);

    }

//    private void updateMapView() {
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 13f), 4000, null);
//    }

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
    public void initView() {

    }

    @Override
    public void initData() {

    }


//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        if (hasFocus) {
//            details_fragment.measure(0,0);
//            int width=details_fragment.getMeasuredWidth();
//            int height=details_fragment.getMeasuredHeight();
//
//        }
//    }
}
