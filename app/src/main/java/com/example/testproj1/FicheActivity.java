package com.example.testproj1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GestureDetectorCompat;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testproj1.Personnages.Personnage;

public class FicheActivity<OSTL> extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener, SensorEventListener {


    private final int PERMISSIONS_REQUEST_CAMERA = 2;

    private float x = 0;
    private Sensor mAccelerometer;
    private SensorManager manager;
    private boolean accelSupported;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private GestureDetector gestureDetector;
    // Texts de base (Informations --> Ne changera pas)
    TextView pvText, manaText;
    TextView atkpText, atkmText;
    TextView defpText, defmText;

    // Texts qui contient les valeurs des statistiques --> Changera en fonction de l'état de la partie + personnage concerné
    TextView nameText;
    TextView pvValueText, manaValueText;
    TextView atkpValueText, atkmValueText;
    TextView defpValueText, defmValueText;
    TextView DiceResultText;

    Personnage InfoPersonnage;

    MainActivity.Theme theme;

    private View mainView;

    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;

    private Button ButtonCamera;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche);

        // On récupère la classe:
        InfoPersonnage = (Personnage)getIntent().getSerializableExtra("infos");

        mainView = (View)findViewById(R.id.FicheView);
        theme = (MainActivity.Theme)getIntent().getSerializableExtra("THEME");

        // Gestion Appareil Photo :
        ButtonCamera = (Button)findViewById(R.id.buttonCamera);
        imageView = (ImageView)findViewById(R.id.imageCharacter);

        switch (theme) {
            case MATIN:
                mainView.setBackgroundResource(R.drawable.matin);
                break;

            case MIDI:
                mainView.setBackgroundResource(R.drawable.midi);
                break;

            case SOIR:
                mainView.setBackgroundResource(R.drawable.soir);
                break;
        }

        // On charge les texts view:
        loadTexts();

        // On met les valeurs temps réel de la partie:
        setValueTexts(InfoPersonnage);

        mDetector = new GestureDetectorCompat(this,this);
        mDetector.setOnDoubleTapListener(this);

        manager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        mAccelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        demandePermission();

    }

    public void loadTexts() {
        // On met en place les Texts View:
        nameText = (TextView)findViewById(R.id.nameText);
        pvText = (TextView)findViewById(R.id.PVText);
        manaText = (TextView)findViewById(R.id.manaText);
        atkpText = (TextView)findViewById(R.id.ATKPText);
        atkmText = (TextView)findViewById(R.id.ATKMText);
        defpText = (TextView)findViewById(R.id.DEFPText);
        defmText = (TextView)findViewById(R.id.DEFMText);

        pvValueText = (TextView)findViewById(R.id.PVValue);
        manaValueText = (TextView)findViewById(R.id.ManaValue);
        atkpValueText = (TextView)findViewById(R.id.ATKPValue);
        atkmValueText = (TextView)findViewById(R.id.ATKMValue);
        defpValueText = (TextView)findViewById(R.id.DEFPValue);
        defmValueText = (TextView)findViewById(R.id.DEFMValue);
        DiceResultText = (TextView)findViewById(R.id.DiceResult);
    }

    public void setValueTexts(Personnage personnage) {
        nameText.setText(personnage.getName());
        pvValueText.setText(""+personnage.getPV());
        manaValueText.setText(""+personnage.getMana());
        atkpValueText.setText(""+personnage.getATKP().toString());
        atkmValueText.setText(""+personnage.getATKM().toString());
        defpValueText.setText(""+personnage.getDEFP());
        defmValueText.setText(""+personnage.getDEFM());
    }

    // ----- Appareil Photo ----
    public void demandePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            // Si on a pas la permission
            System.out.println("---------PERMISSION FOR CAMERA NOT GRANTED YET------------");

            // Permission is not granted. Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                // Explique ici pourquoi on à besoin de cette permission

                System.out.println("EXPLAIN WHY");

            } else {
                //Si aucune explication n'est nécéssaire :
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA}, PERMISSIONS_REQUEST_CAMERA);

            }
        }

        else {
            // If we have the permission :
            useCamera();


        }
    }

    public void useCamera() {
        System.out.println("ON A ACCES A LA CAMERA!");
    }

    public void openCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    useCamera();

                } else {
                    // On a pas la permission !!
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }


    // ---------------- Implements pour Double Tap & Accelerometre -------------------- :

    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    public boolean onDoubleTap(MotionEvent event) {
        this.finish();
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
        if (event1.getX() - event2.getX() > SWIPE_MIN_DISTANCE &&
                Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY)
        {
            DiceResultText.setText("Attaque magique : "+ InfoPersonnage.AttaqueM());
        }
            return false;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float currentx = sensorEvent.values[0];
            if (x == 0)
            {
                x = currentx;
            }
            if (x >= currentx+4) {
                DiceResultText.setText("Attaque physique" + InfoPersonnage.AttaqueP());
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onResume() {
        super.onResume();
        accelSupported = manager.registerListener(
                this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    public void onPause() {
        if (accelSupported)
            manager.unregisterListener(this, mAccelerometer);
        super.onPause();
    }
}

