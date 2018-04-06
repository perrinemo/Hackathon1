package fr.wcs.hackathon;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        MediaPlayer soundSplash = MediaPlayer.create(this, R.raw.SonSplashScreen);
        soundSplash.start();

                //  final MediaPlayer fightSound = MediaPlayer.create(this, R.raw.fight);
    }
}
