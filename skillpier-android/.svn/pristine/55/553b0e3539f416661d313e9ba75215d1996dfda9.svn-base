package com.quark.skillopedia.Googlemap;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.quark.skillopedia.R;
import com.quark.skillopedia.base.BaseFragementActivity;
import com.quark.skillopedia.util.Utils;

import butterknife.ButterKnife;


public class ShowPositionActivity extends BaseFragementActivity implements OnMapReadyCallback {

    public static double lng = 113.867102;
    public static double lat = 22.567009;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    String address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_show);
        ButterKnife.inject(this);
        setTopTitle("Location");
        setBackButton();

        String lngStr = (String) getIntent().getStringExtra("longitude");
        String latStr = (String) getIntent().getStringExtra("latitude");
        address = (String) getIntent().getStringExtra("address");

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

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        enableMyLocation();

        map.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).title("Marker")).setTitle(address);
//        mMap.getUiSettings().setZoomControlsEnabled(false);
//        mMap.getUiSettings().setZoomGesturesEnabled(false);
//        CameraUpdateFactory.zoomBy(13);
        //滚动到上课位置
        LatLngBounds AUSTRALIA = new LatLngBounds(new LatLng(lat, lng), new LatLng(lat, lng));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(AUSTRALIA.getCenter(), 13));

        // Instantiates a new CircleOptions object and defines the center and radius
        CircleOptions circleOptions = new CircleOptions().center(new LatLng(lat, lng)).radius(1000);

//        Polyline line = map.addPolyline(new PolylineOptions()
//                .add(new LatLng(-37.81319, 144.96298), new LatLng(-31.95285, 115.85734))
//                .width(25)
//                .color(Color.BLUE)
//                .geodesic(true));

// Get back the mutable Circle
//        Circle circle = mMap.addCircle(circleOptions);
//        circle.setFillColor(R.color.map_cilcle);
//        circle.setStrokeColor(R.color.map_cilcle);
//        circle.setStrokeWidth(0);
    }

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
}
