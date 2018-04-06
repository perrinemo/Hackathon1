package fr.wcs.hackathon;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CombatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);

        final MediaPlayer fightSound = MediaPlayer.create(this, R.raw.fight);
        Button btnFight = findViewById(R.id.btn_fight);
        btnFight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fightSound.start();
            }
        });

        final TextView textLifeP1 = findViewById(R.id.text_life_p1);
        final TextView textLifeP2 = findViewById(R.id.text_life_p2);

        ImageView ivP1 = findViewById(R.id.img_player_one);
        ImageView ivP2 = findViewById(R.id.img_player_two);

        final int lifeP1 = 92;
        final int lifeP2 = 52;

        textLifeP1.setText(String.valueOf(lifeP1));
        textLifeP2.setText(String.valueOf(lifeP2));

        final int combatP1 = 13;
        final int combatP2 = 27;

        Button fight = findViewById(R.id.btn_fight);

        fight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




        FontHelper.setFont(fight, "pix.ttf");
        FontHelper.setFont(textLifeP1, "pix.ttf");
        FontHelper.setFont(textLifeP2, "pix.ttf");
    }
}
