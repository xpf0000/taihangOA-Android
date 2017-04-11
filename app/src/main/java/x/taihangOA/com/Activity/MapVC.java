package x.taihangOA.com.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.bigkoo.alertview.AlertView;

import java.util.HashMap;
import java.util.Map;

import util.XNetUtil;
import util.XNotificationCenter;
import x.taihangOA.com.R;

/**
 * Created by Administrator on 2017/4/5 0005.
 */

public class MapVC extends Activity implements BDLocationListener {

    MapView mMapView = null;
    Marker marker;
    TextView addressTv;
    GeoCoder geocode;
    BaiduMap mBaiduMap;
    boolean isFirstLoc = true;
    String addressName = "";
    LatLng clatLng;
    String caddress = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapvc);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        addressTv = (TextView) findViewById(R.id.address);

        mBaiduMap = mMapView.getMap();

        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
// 定位初始化
        LocationClient mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(this);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();

//        LatLng ll = new LatLng(100,
//                100);
//        MapStatus.Builder builder = new MapStatus.Builder();
//        builder.target(ll).zoom(18.0f);
//        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));


        geocode = GeoCoder.newInstance();

        geocode.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    return;
                }
                if (result != null && result.error == SearchResult.ERRORNO.NO_ERROR) {
                    String address = result.getAddressDetail().province+
                            result.getAddressDetail().city+
                            result.getAddressDetail().district+
                            result.getAddressDetail().street+
                            result.getAddressDetail().streetNumber+
                            addressName;
                    addressTv.setText(address);
                    caddress = address;
                    System.out.println("得到位置" + address);
                }
            }

        });


        mMapView.getMap().setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                XNetUtil.APPPrintln(latLng.toString());
                addMarker(latLng);
                getAddress(latLng,null);
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {

                LatLng latLng = mapPoi.getPosition();
                addMarker(latLng);
                getAddress(latLng,mapPoi.getName().replace("\\",""));

                return true;
            }
        });


        mMapView.getMap().setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                getAddress(marker.getPosition(),null);
            }

            @Override
            public void onMarkerDragStart(Marker marker) {

            }
        });

    }

    private void addMarker(LatLng latLng)
    {
        if(marker != null)
        {
            marker.remove();
        }
        clatLng = latLng;
//构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.zuobiao);
//构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(latLng)
                .draggable(true)
                .icon(bitmap);
//在地图上添加Marker，并显示
        marker = (Marker) mMapView.getMap().addOverlay(option);
    }

    private void getAddress(LatLng latLng,String name)
    {
        addressName = name == null ? "" : name;
        ReverseGeoCodeOption options = new ReverseGeoCodeOption().location(latLng);
        // 发起反地理编码请求
        geocode.reverseGeoCode(options);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    public void back(View v)
    {
        finish();
    }

    public void doSubmit(View v)
    {
        if(clatLng != null && caddress.length() > 0)
        {
            Map<String,String> map = new HashMap<>();
            map.put("caddress",caddress);
            map.put("cmap",clatLng.latitude+","+clatLng.longitude);
            XNotificationCenter.getInstance().postNotice("Map",map);
            finish();
        }
        else
        {
            Toast.makeText(this, "请点击地图选择一个位置", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onReceiveLocation(BDLocation location) {

        // map view 销毁后不在处理新接收的位置
        if (location == null || mMapView == null) {
            return;
        }

        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(location.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(100).latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();
        mBaiduMap.setMyLocationData(locData);

        if (isFirstLoc) {
            isFirstLoc = false;
            LatLng ll = new LatLng(location.getLatitude(),
                    location.getLongitude());
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(ll).zoom(18.0f);
            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        }

    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }
}
