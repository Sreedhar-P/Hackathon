package com.example.sreedhar.hackathon;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    EditText e;
    static double f1,f2;
    ArrayList markerPoints= new ArrayList();
    private RecyclerView recyclerView;
    //LayoutInflater inflater, @Nullable ViewGroup container,

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        e = (EditText) findViewById(R.id.lan);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
        double[][] data={{14.470170,78.823720},{12.9716,77.5946},{17.3850,78.4867},{13.0827,80.2707},
                {15.8281,78.0373},{14.1130,78.1606},{13.8185,77.4989}};
        int i;
        for (i=0;i<data.length;i++)
        {

        LatLng India = new LatLng(data[i][0], data[i][1]);
        //mMap.addMarker(new MarkerOptions().position(India).title("Marker in India"));
        //CameraPosition myPosition = new CameraPosition.Builder()
         //       .target(India).zoom(6).bearing(0).tilt(0).build();
        //googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(myPosition));
            //
        }
    }

    // ban=12.9716 77.5946
    // hy 17.3850,78.4867
    // chenn  13.0827,80 2707
    // anan 14.6819,77.6006
    // kadiri 14.1130,78.1606
    // hdp 13.8185,77.4989
    public void search(View view)
    {

        mMap.setMyLocationEnabled(true);
        if (mMap!=null)
        {
            mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                @Override
                public void onMyLocationChange(Location location) {
                    mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(),location.getLongitude())).title("me"));
                    LatLng my=new LatLng(location.getLatitude(),location.getLongitude());
                    CircleOptions circleOptions=new CircleOptions();
                    circleOptions.center(my);
                    circleOptions.radius(50000.0);
                    circleOptions.strokeColor(Color.BLACK);
                    circleOptions.strokeWidth(2);
                    //mMap.addCircle(circleOptions);

                    double[][] data={{14.470170,78.823720},{12.9716,77.5946},{17.3850,78.4867},{13.0827,80.2707},
                            {15.8281,78.0373},{14.1130,78.1606},{13.8185,77.4989}};
                    double[][] dist=new double[7][3];
                    int i;float[] distance=new float[2];
                    for (i=0;i<data.length;i++)
                    {
                        Location.distanceBetween(data[i][0],data[i][1],location.getLatitude(),location.getLongitude(),distance);
                        dist[i][0]=distance[0];
                        dist[i][1]=data[i][0];
                        dist[i][2]=data[i][1];
                    }
                    int j=0;
                    for (i=0;i<7;i++)
                    {
                        for(j=i+1;j<7;j++)
                        {
                            if (dist[i][0]>dist[j][0])
                            {
                                double k,k1,k2;
                                k=dist[i][0];
                                dist[i][0]=dist[j][0];
                                dist[j][0]=k;

                                k1=dist[i][1];
                                dist[i][1]=dist[j][1];
                                dist[j][1]=k1;

                                k2=dist[i][2];
                                dist[i][2]=dist[j][2];
                                dist[j][2]=k2;
                            }
                        }
                    }
                    LatLng India = new LatLng(dist[0][1], dist[0][2]);
                    mMap.addMarker(new MarkerOptions().position(India).title("me"));
                    Toast.makeText(MapsActivity.this,dist[0][0]/1000+" km",Toast.LENGTH_SHORT).show();


                    String url = getDirectionsUrl(origin, dest);

                    DownloadTask downloadTask = new DownloadTask();

                    // Start downloading json data from Google Directions API
                    downloadTask.execute(url);
                }
            });
        }
    }
}

/*
* [

{lat: 14.470170, lng: 78.823720},{lat: 14.471050, lng: 78.835890},{lat: 14.461380, lng: 78.834061},{lat: 14.477234, lng: 78.826904},{lat: 14.762005, lng: 78.874889},

{lat: 14.476750, lng: 78.823580},
{lat: 14.477234, lng: 78.826904},
{lat: 14.470170, lng: 78.823720},
{lat: 14.470170, lng: 78.823720},
{lat: 14.485490, lng: 78.821910},
{lat: 14.482880, lng: 78.822550},
{lat: 14.459330, lng: 78.835910},
{lat: 16.927090, lng: 82.236090},
{lat: 14.482880, lng: 78.822550},
{lat: 14.441758, lng: 79.971983}

]*/

