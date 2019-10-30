package com.example.testproj1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.testproj1.Personnages.Personnage;

import org.w3c.dom.Text;

public class FicheActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    // Texts de base (Informations --> Ne changera pas)
    TextView pvText, manaText;
    TextView atkpText, atkmText;
    TextView defpText, defmText;

    // Texts qui contient les valeurs des statistiques --> Changera en fonction de l'état de la partie + personnage concerné
    TextView nameText;
    TextView pvValueText, manaValueText;
    TextView atkpValueText, atkmValueText;
    TextView defpValueText, defmValueText;

    Personnage InfoPersonnage;

    MainActivity.Theme theme;

    private View mainView;

    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche);

        // On récupère la classe:
        InfoPersonnage = (Personnage)getIntent().getSerializableExtra("infos");

        mainView = (View)findViewById(R.id.FicheView);
        theme = (MainActivity.Theme)getIntent().getSerializableExtra("THEME");

        switch (theme){
            case MATIN :
                mainView.setBackgroundColor(getResources().getColor(R.color.couleurMatin));
                break;
            case MIDI :
                mainView.setBackgroundColor(getResources().getColor(R.color.couleurMidi));
                break;
            case SOIR:
                mainView.setBackgroundColor(getResources().getColor(R.color.couleurSoir));
                break;
        }

        // On charge les texts view:
        loadTexts();

        // On met les valeurs temps réel de la partie:
        setValueTexts(InfoPersonnage);

        mDetector = new GestureDetectorCompat(this,this);
        mDetector.setOnDoubleTapListener(this);
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
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }
}

//test commit