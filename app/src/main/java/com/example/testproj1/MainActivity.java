package com.example.testproj1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private enum Country {
        REUNION,
        FRANCE,
        AUSTRALIE
    }

    public enum Theme {
        MATIN,
        MIDI,
        SOIR
    }

    private final int DEBUT_HORAIRE_MATIN = 6;
    private final int DEBUT_HORAIRE_MIDI = 10;
    private final int DEBUT_HORAIRE_SOIR = 18;

    private int realHour = 0;

    View MainVue;

    // Récupération de la dernière coordonnée via la géolocalisation :
    private double lastLongitude = 0;
    private double lastLatitude = 0;

    ImageView imageBG;

    private MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainVue = (View)findViewById(R.id.MainVue);

        if(mPlayer == null) {
            stop();
            mPlayer = MediaPlayer.create(this, R.raw.yp);
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                public void onCompletion(MediaPlayer mp) {
                                                    stop();
                                                }
                                            }
            );
            mPlayer.start();
        }

        // On met une couleur de fond par défaut, au cas où la récupération de position de marche pas :
        MainVue.setBackgroundColor(getResources().getColor(R.color.colorPrimary)); //On change la couleur du background ici en couleur du soir (bleu)

        // On demande la permission pour avoir la géoloclisation
        demandePermission();
    }

    // Gestion d'arrêt de la musique :
    public void stop() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
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

                lastLongitude = longitude;
                lastLatitude = latitude;

                System.out.println("--------------> Longitude: " + longitude + "Latitude: " + latitude + " <--------");

                // Action à faire quand on à la latitude et la longitude :
                realHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + getGMT(getCountry(longitude,latitude));
                System.out.println("CALENDAR = " + Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
                System.out.println("REAL HOUR = " + realHour);
                System.out.println("PAYS = " + getCountry(longitude,latitude));

                // On charge le bon thème en fonction de la véritable heure obtenu :
                switch (getTheme(realHour)) {
                    case MATIN:
                        System.out.println("ON EST IC11111111111111111111111111111111111111111111111111111111");
                        MainVue.setBackgroundResource(R.drawable.matin);
                        break;

                    case MIDI:
                        System.out.println("ON EST IC222222222222222222222222222222222222222222222222222222222222");
                        MainVue.setBackgroundResource(R.drawable.midi);
                        break;

                    case SOIR:
                        System.out.println("ON EST IC333333333333333333333333333333333333333333333333333333333333");
                        MainVue.setBackgroundResource(R.drawable.soir);
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

        if (compareLongitude)
            return Country.REUNION;

        compareLongitude = (longitude >= -34 && longitude <= -31);

        if (compareLongitude)
            return Country.AUSTRALIE;

        compareLongitude = (longitude >= 47 && longitude <= 49);

        if (compareLongitude)
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
                intent.putExtra("THEME",Theme.MATIN);
                break;

            case MIDI:
                intent.putExtra("THEME", Theme.MIDI);
                break;

            case SOIR:
                intent.putExtra("THEME", Theme.SOIR);
                break;
        }

        startActivity(intent);
    }

    public void GoMap(View v) {
        Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
        intent.putExtra("LONGITUDE",lastLongitude);
        intent.putExtra("LATITUDE",lastLatitude);
        startActivity(intent);
    }

    public void Quit(View V){
        finish();
    }
}
