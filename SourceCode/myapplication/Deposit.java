//this part is written by java to show code interoperability.
//this is for Deposit process
package com.example.myapplication;


import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Random;

import static android.widget.Toast.LENGTH_LONG;

public class Deposit extends FragmentActivity implements OnMapReadyCallback {
    public GoogleMap map;
    LatLng latLng;
    LatLng latLng1;
    LatLng latLng2;
    LatLng latLng3;


    Location currentLocation;

    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE= 101;
    public ArrayList<LatLng> points = new ArrayList<LatLng>();
    int index=0;
    //finding minimum location variable
    float[] minimum = new float[1];
    float[] x = new float[1];
    float[] minLocation = new float[3];
    float[] minimumLocation = new float[21];


    int[] personByAtm = new int[21];
    int[] totalTime = new int[21];
    int[] indexArray = new int[21];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();

    }

    private void fetchLastLocation() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
            return;
        }
        Task<Location>task=fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!= null){
                    currentLocation=location;
                    SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
                    supportMapFragment.getMapAsync(Deposit.this);
                    assign();


                }

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        latLng=new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
        double lng=points.get(indexArray[0]).latitude;
        double ltd=points.get(indexArray[0]).longitude;
        double lng1=points.get(indexArray[1]).latitude;
        double ltd1=points.get(indexArray[1]).longitude;
        double lng2=points.get(indexArray[2]).latitude;
        double ltd2=points.get(indexArray[2]).longitude;

        latLng1=new LatLng(lng,ltd);
        latLng2 = new LatLng(lng1,ltd1);
        latLng3 = new LatLng(lng2,ltd2);

        //marker options on googlemap
        int height = 100;
        int width = 80;
        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.greeenmarker);
        Bitmap b=bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        BitmapDrawable bitmapdraw2=(BitmapDrawable)getResources().getDrawable(R.drawable.bluemarker);
        Bitmap b1=bitmapdraw2.getBitmap();
        Bitmap smallMarker1 = Bitmap.createScaledBitmap(b1, width, height, false);

        BitmapDrawable bitmapdraw3=(BitmapDrawable)getResources().getDrawable(R.drawable.redmarker);
        Bitmap b2=bitmapdraw3.getBitmap();
        Bitmap smallMarker2 = Bitmap.createScaledBitmap(b2, width, height, false);

        MarkerOptions markerOptions=new MarkerOptions().position(latLng).title("Your Loc");

        MarkerOptions markerOptions1=new MarkerOptions().position(latLng1).title(totalTime[indexArray[0]] + " Mins (" + Math.round(minimumLocation[indexArray[0]])+" m " + personByAtm[indexArray[0]] + " clients) " ).icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
        MarkerOptions markerOptions2=new MarkerOptions().position(latLng2).title(totalTime[indexArray[1]] + " Mins (" + Math.round(minimumLocation[indexArray[1]])+" m " + personByAtm[indexArray[1]] + " clients) " ).icon(BitmapDescriptorFactory.fromBitmap(smallMarker1));
        MarkerOptions markerOptions3=new MarkerOptions().position(latLng3).title(totalTime[indexArray[2]] + " Mins (" + Math.round(minimumLocation[indexArray[2]])+" m " + personByAtm[indexArray[2]] + " clients) " ).icon(BitmapDescriptorFactory.fromBitmap(smallMarker2));



        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));


        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));

        googleMap.addMarker(markerOptions).showInfoWindow();
        googleMap.addMarker(markerOptions1).showInfoWindow();
        googleMap.addMarker(markerOptions2);
        googleMap.addMarker(markerOptions3);

    }
    //creating random person who waits at any ATM.
    public void createRandomPerson()
    {
        Random rand = new Random();

        for(int i=1;i<21;i++){
            personByAtm[i] = rand.nextInt(6)+1;
        }


    }
    //time calculation regarding the formula.
    public void calculateTime(){
        createRandomPerson();
        int [] tempTime = new int[21];
        int [] distToTime = new int[3];
        int y;
        for(int i=0;i<3;i++){

            totalTime[indexArray[i]] = personByAtm[indexArray[i]]*3;
            distToTime[i]=(int)(minLocation[i]/100);
            y = totalTime[indexArray[i]]-distToTime[i];

            if (y > 0) totalTime[indexArray[i]] = (int) (totalTime[indexArray[i]]+ ( distToTime[i]-distToTime[i]));

            else totalTime[indexArray[i]] = distToTime[i];

        }



    }
    //sorting the min 3 atm times
    public void compareTotalTime() {
        int temp = totalTime[indexArray[0]];
        int temp_2 = indexArray[0];

        for(int i = 1;i<3;i++){
            if(temp > totalTime[indexArray[i]]){
                temp = indexArray[i];
                indexArray[i-1] = indexArray[i];
                indexArray[i] = temp_2;
                temp_2 = indexArray[i];
            }
        }
        temp = totalTime[indexArray[1]];
        temp_2 = indexArray[1];

        for(int i = 1;i<3;i++){
            if(temp > totalTime[indexArray[i]]){
                temp = indexArray[i];
                indexArray[i-1] = indexArray[i];
                indexArray[i] = temp_2;
                temp_2 = indexArray[i];
            }
        }

    }

    public void assign(){
        points.add(new LatLng(47.481920,19.068655));
        points.add(new LatLng(47.474856,19.098792));
        points.add(new LatLng(47.483543,19.065962));
        points.add(new LatLng(47.4859617,19.0579744));
        points.add(new LatLng(47.4848047,19.0604441));
        points.add(new LatLng(47.481920,19.068655));
        points.add(new LatLng(47.4740863,19.0604441));
        points.add(new LatLng(47.475256,19.07149));
        points.add(new LatLng(47.4671284,19.075725));
        points.add(new LatLng(47.486211,19.074642));
        points.add(new LatLng(47.4751959,19.072594));
        points.add(new LatLng(47.478773,19.087352));
        points.add(new LatLng(47.479982,19.071813));
        points.add(new LatLng(47.478868,19.089672));
        points.add(new LatLng(47.478100,19.089588));
        points.add(new LatLng(47.476693,19.091690));
        points.add(new LatLng(47.459747,19.107592));
        points.add(new LatLng(47.4618666,19.1162203));
        points.add(new LatLng(47.462041,19.0796032));
        points.add(new LatLng(47.4656135,19.115323));
        points.add(new LatLng(47.498651,19.055133 ));
        points.add(new LatLng(47.496910,19.069070 ));


        //Finding Nearest 3 ATM
        Location.distanceBetween(currentLocation.getLatitude(),currentLocation.getLongitude(),
                points.get(0).latitude,points.get(0).longitude,minimum);
        for(int i=1;i<21;i++){
            Location.distanceBetween(currentLocation.getLatitude(),currentLocation.getLongitude(),
                    points.get(i).latitude,points.get(i).longitude,x);
            if (minimum[0]>x[0]){
                minimum[0]=x[0];
                minLocation[0]=x[0];
                indexArray[0]=i;

            }

        }
        Location.distanceBetween(currentLocation.getLatitude(),currentLocation.getLongitude(),
                points.get(0).latitude,points.get(0).longitude,minimum);

        for(int i=1;i<21;i++){
            Location.distanceBetween(currentLocation.getLatitude(),currentLocation.getLongitude(),
                    points.get(i).latitude,points.get(i).longitude,x);
            if (minimum[0]>x[0] && (minLocation[0]!=x[0])){
                minimum[0]=x[0];
                minLocation[1]=x[0];
                indexArray[1]=i;

            }
        }

        Location.distanceBetween(currentLocation.getLatitude(),currentLocation.getLongitude(),
                points.get(0).latitude,points.get(0).longitude,minimum);

        for(int i=1;i<21;i++){
            Location.distanceBetween(currentLocation.getLatitude(),currentLocation.getLongitude(),
                    points.get(i).latitude,points.get(i).longitude,x);
            if (minimum[0]>x[0] && (minLocation[0]!=x[0]) && (minLocation[1]!=x[0]) ){
                minimum[0]=x[0];
                minLocation[2]=x[0];
                indexArray[2]=i;

            }
        }
        for(int i=0;i<3;i++){
            minimumLocation[indexArray[i]]=minLocation[i];
        }

        calculateTime();
        compareTotalTime();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case REQUEST_CODE:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    fetchLastLocation();
                }
                break;
        }
    }

}
