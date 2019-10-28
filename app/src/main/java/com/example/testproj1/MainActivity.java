package com.example.testproj1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private enum Country {
        REUNION,
        FRANCE,
        AUSTRALIE
    }

    private enum Theme {
        MATIN,
        MIDI,
        SOIR
    }

    private final int DEBUT_HORAIRE_MATIN = 6;
    private final int DEBUT_HORAIRE_MIDI = 10;
    private final int DEBUT_HORAIRE_SOIR = 18;

    private int realHour = 0;

    View MainVue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainVue = (View)findViewById(R.id.MainVue);

        MainVue.setBackgroundColor(getResources().getColor(R.color.colorPrimary)); //On change la couleur du background ici en couleur du soir (bleu)
        demandePermission();
    }

    // ----- Géolocalisation ----
    public void demandePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Si on a pas la permission
            System.out.println("---------PERMISSION NOT GRANTED YET------------");

            // Permission is not granted. Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Explique ici pourquoi on à besoin de cette permission

                System.out.println("EXPLAIN WHY");

            } else {
                //Si aucune explication n'est nécéssaire :
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

            }
        }

        else {
            // If we have the permission :
            obtainLocation();
        }
    }

    public void obtainLocation() {
        System.out.println("ON EST DANS LA FONCTION : OBTAIN_LOCATION");

        String locationContext = Context.LOCATION_SERVICE;
        LocationManager locationManager = (LocationManager)this.getSystemService(locationContext);

        String provider = LocationManager.GPS_PROVIDER;

        try {
            Location location = locationManager.getLastKnownLocation(provider);

            if (location != null) {
                System.out.println("C'EST BON, ON A LES COORDONNEES!");

                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                System.out.println("--------------> Longitude: " + longitude + "Latitude: " + latitude + " <--------");

                // Action à faire quand on à la latitude et la longitude :
                realHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + getGMT(getCountry(longitude,latitude));
                System.out.println("REAL HOUR = " + realHour);

                // On charge le bon thème en fonction de la véritable heure obtenu :
                switch (getTheme(realHour)) {
                    case MATIN:
                        MainVue.setBackgroundColor(getResources().getColor(R.color.couleurMatin));
                        break;

                    case MIDI:
                        MainVue.setBackgroundColor(getResources().getColor(R.color.couleurMidi));
                        break;

                    case SOIR:
                        MainVue.setBackgroundColor(getResources().getColor(R.color.couleurSoir));
                        break;
                }

            }
            else
                System.out.println(" -------------------- LOCATION == NULL  -------------------- ");

        } catch(SecurityException e) { System.out.println("On ne peut pas utiliser Location!"); }
    }



    public int getGMT(Country country) {
        switch (country) {
            case REUNION: return 4;

            case FRANCE: return 1;

            case AUSTRALIE: return 10;

            default: return 0;
        }
    }

    public Country getCountry(double longitude, double latitude) {

        boolean compareLongitude = (longitude >= 55.0 && longitude <= 56);
        boolean compareLatitude =  (latitude >= -22.0 && latitude <= 20.7);

        if (compareLongitude && compareLatitude)
            return Country.REUNION;

        compareLongitude = (longitude >= -34 && longitude <= -31);
        compareLatitude = (latitude >= 150 && latitude <= 152);

        if (compareLongitude && compareLatitude)
            return Country.AUSTRALIE;

        compareLongitude = (longitude >= 47 && longitude <= 49);
        compareLatitude = (latitude >= 0 && latitude <= 2);

        if (compareLongitude && compareLatitude)
            return Country.FRANCE;

        return Country.REUNION;

    }

    public Theme getTheme(int hour) {
        if (hour >= DEBUT_HORAIRE_MATIN && hour < DEBUT_HORAIRE_MIDI)
            return Theme.MATIN;

        else if (hour >= DEBUT_HORAIRE_MIDI && hour < DEBUT_HORAIRE_SOIR)
            return Theme.MIDI;
        else
            return Theme.SOIR;
    }

    // ----------

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    obtainLocation();

                } else {
                    // On a pas la permission !!
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    // Methodes Onclick des boutons:
    public void Commence(View v){
        Intent intent = new Intent(getApplicationContext(), SelectionPersonnage.class);

        // On va envoyer en extra la bonne information selon l'heure réelle obtenu :
        switch (getTheme(realHour)) {
            case MATIN:
                intent.putExtra("THEME","MATIN");
                break;

            case MIDI:
                intent.putExtra("THEME", "MIDI");
                break;

            case SOIR:
                intent.putExtra("THEME", "SOIR");
                break;
        }


        startActivity(intent);
    }

    public void Quit(View V){
        finish();
    }
}
