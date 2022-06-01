package com.example.tourismsmolensk;


import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.tourismsmolensk.databinding.ActivityMapsBinding;

import java.util.Random;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setBuildingsEnabled(true);
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String markerName = marker.getTitle();
                Toast.makeText(MapsActivity.this, "Clicked location is " + markerName, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        createLocations();
    }

    public void createLocations(){
        LatLng smolensk = new LatLng(54.760550, 32.090256);
        float zoomLevel = 16.0f; //1 to 20
        mMap.addMarker(new MarkerOptions().position(smolensk).title("Solovinaya Rosha"));

        LatLng kolodnia = new LatLng(54.783175, 32.03784444);
        float zoomLevelk = 16.0f; //1 to 20
        mMap.addMarker(new MarkerOptions().position(kolodnia).title("Lopatinskyi Sad"));

        LatLng sobor = new LatLng(54.7884303, 32.0535142);
        float zoomLevelSOb = 16.0f; //1 to 20
        mMap.addMarker(new MarkerOptions().position(sobor).title("Svyato-Uspenskij kafedral'nyj sobor"));
        final double maxl = 54.798017;
        final double minl = 54.759668;
        final double maxlo = 31.984713;
        final double minlo = 32.103460;
        Random r = new Random();
        for(int i = 0; i<30;i++){
            double lat = minl + (maxl - minl) * r.nextDouble();
            double lon = minlo + (maxlo - minlo) * r.nextDouble();
            LatLng marker = new LatLng(lat, lon);
            mMap.addMarker(new MarkerOptions().position(marker).title("marker"));
        }
 
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sobor, zoomLevelSOb));
    }

}
