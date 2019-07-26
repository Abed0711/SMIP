package com.example.abed.smit;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import java.security.Permission;

public class GPSTreacker extends Service implements LocationListener {

    boolean isGPSEnable = false;
    boolean isNetworkEnable = false;
    boolean canGetLocation = false;

    Location location;
    protected LocationManager locationManager;

    private final Context context;

    public GPSTreacker(Context context) {

        this.context = context;

    }

    public Location getLocation() {
        try {
            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            isGPSEnable = locationManager.isProviderEnabled(locationManager.GPS_PROVIDER);
            isNetworkEnable = locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER);

            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                if (isGPSEnable) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 10000, 10, this);
                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        }
                    }
                }
                if (location == null) {
                    if (isNetworkEnable) {
                        locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER, 10000, 10, this);
                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        }

                    }
                }
            }
        } catch (Exception ex) {

        }
        return location;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public IBinder onBind(Intent arg0) {
        return null;
    }
}
