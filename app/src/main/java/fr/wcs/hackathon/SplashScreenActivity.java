package fr.wcs.hackathon;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ThemedSpinnerAdapter;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        MediaPlayer soundSplash = MediaPlayer.create(this, R.raw.sonsplashscreen);
        soundSplash.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                SplashScreenActivity.this.startActivity(intent);
                SplashScreenActivity.this.finish();
            }
        }, 5000);
    }

}
