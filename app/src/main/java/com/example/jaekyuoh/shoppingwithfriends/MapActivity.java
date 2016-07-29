package com.example.jaekyuoh.shoppingwithfriends;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.test.mock.MockContext;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by EllisCooper on 3/24/15.
 *
 * Class represents the map object for the application.
 */
public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        MapFragment mapFrag = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);
    }

    //Method sets up map with appropriate content before display.
    @Override
    public void onMapReady(GoogleMap map) {
        String address = getIntent().getExtras().getString("addr");
        ArrayList<Double> coordinates = geoLocate(address);
        if(coordinates != null) {
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(coordinates.get(0), coordinates.get(1)))
                    .title("Marker"));
        } else {
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(0, 0))
                    .title("Marker"));
        }
    }

    /**
     * Method uses the "geocoding" class to convert the users address text to coordinates for
     * the GPS to pinpoint on the map.
     *
     * @return ArrayList of Doubles representing the latitude and longitude of the user
     * entered address
     */
    public ArrayList<Double> geoLocate(String add) {
        try {
            Geocoder locator = new Geocoder(this);
            List<Address> addresses;
            ArrayList<Double> latLong = new ArrayList<Double>(0);

            addresses = locator.getFromLocationName(add, 1);
            latLong.add(addresses.get(0).getLatitude());
            latLong.add(addresses.get(0).getLongitude());

            return latLong;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
