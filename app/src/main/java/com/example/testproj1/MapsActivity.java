package com.example.testproj1;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private double longitude;
    private double latitude;

    private Button buttonDeleteMarker, buttonNextMarker;
    private ArrayList<MarkerOptions> listMarkers;
    private int currentMarkerIndex = 0; // Permet d'avoir l'index du dernier marker visiter dans la liste de markers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        buttonDeleteMarker = (Button)findViewById(R.id.buttonRemoveOneMarker);
        buttonNextMarker = (Button)findViewById(R.id.buttonNextMarker);

        listMarkers = new ArrayList<MarkerOptions>();

        longitude = getIntent().getDoubleExtra("LATITUDE",0);
        latitude = getIntent().getDoubleExtra("LONGITUDE",0);

        System.out.println("-------------------------------------------------");
        System.out.println("Longitude: " + longitude+ "   latitude: " + latitude);
        System.out.println("-------------------------------------------------");

        toggleButtonEnable();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    // Fonction permettant jute de redessiner (et d'actualiser) les markers sur la map :
    private void drawMarkers() {
        mMap.clear();

        for(int i=0; i < listMarkers.size(); i++)
            mMap.addMarker(listMarkers.get(i));
    }

    // Déplace la caméra de la map à une position précise :
    private void moveCamera(double coordY, double coordX) {
        LatLng position= new LatLng(coordX, coordY);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
    }

    // Fonction décrivant la possibilité pour les bouton d'être actif ou non :
    private void toggleButtonEnable() {
        if (listMarkers.isEmpty()) {
            buttonNextMarker.setEnabled(false);
            buttonDeleteMarker.setEnabled(false);
        }
        else {
            buttonNextMarker.setEnabled(true);
            buttonDeleteMarker.setEnabled(true);
        }

    }

    public void deleteMarker(View view) {
        if (listMarkers.size() >= 1) {
            listMarkers.remove(listMarkers.size() - 1);
            drawMarkers();
        }

        toggleButtonEnable();

    }

    public void goNextMarker(View view) {
        LatLng myLastPosition = listMarkers.get(currentMarkerIndex).getPosition();

        moveCamera(myLastPosition.longitude,myLastPosition.latitude);

        currentMarkerIndex = (currentMarkerIndex >= listMarkers.size()-1) ? 0 : currentMarkerIndex+1;

        toggleButtonEnable();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Une fois que la map est prête, on se déplace à l'endroit de la position de l'utilisateur :
        moveCamera(latitude,longitude);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                listMarkers.add(new MarkerOptions().position(point).title("Vous avez jouer ici à EH"));

                drawMarkers();
                toggleButtonEnable();
            }
        });

        System.out.println("POSITION: " + mMap.getCameraPosition());
    }

}