package fr.wcs.hackathon;


import android.media.MediaPlayer;

import android.content.Intent;
import android.service.notification.NotificationListenerService;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class CombatActivity extends AppCompatActivity {

    TextView textLifeP1;
    TextView textLifeP2;
    TextView textDegatP1;
    TextView textDegatP2;
    ImageView ivP1;
    ImageView ivP2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);
        MediaPlayer fightsound = MediaPlayer.create(this, R.raw.fightsound);
        MediaPlayer combatSound = MediaPlayer.create(this, R.raw.combat);
        fightsound.start();
        combatSound.start();
        Button btnFight = findViewById(R.id.btn_fight);


        textLifeP1 = findViewById(R.id.text_life_p1);
        textLifeP2 = findViewById(R.id.text_life_p2);

        final LinearLayout combatPlayer1 = findViewById(R.id.combat_p1);
        final LinearLayout combatPlayer2 = findViewById(R.id.combat_p2);


        textLifeP1 = findViewById(R.id.text_life_p1);
        textLifeP2 = findViewById(R.id.text_life_p2);
        textDegatP1 = findViewById(R.id.text_degat_p1);
        textDegatP2 = findViewById(R.id.text_degat_p2);

        ivP1 = findViewById(R.id.img_player_one);
        ivP2 = findViewById(R.id.img_player_two);

        final int lifeP1 = 92;
        final int lifeP2 = 52;

        textLifeP1.setText(String.valueOf(lifeP1));
        textLifeP2.setText(String.valueOf(lifeP2));

        final int combatP1 = 5;
        final int combatP2 = 7;



        btnFight = findViewById(R.id.btn_fight);

       /* btnFight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                combatPlayer1.setVisibility(View.VISIBLE);
                combatPlayer2.setVisibility(View.VISIBLE);
                if (player1.getDurability() > 0 && player2.getDurability() > 0) {
                    fight(player1, player2);
                } else {
                    // TODO : ajouter l'intent vers la page winner/looser
                }
            }
        });*/


        FontHelper.setFont(btnFight, "pix.ttf");
        FontHelper.setFont(textLifeP1, "pix.ttf");
        FontHelper.setFont(textLifeP2, "pix.ttf");
        FontHelper.setFont(textDegatP1, "pix.ttf");
        FontHelper.setFont(textDegatP2, "pix.ttf");
    }

    public void fight(HeroModel player1, HeroModel player2) {
/*
        LinearLayout linearLayoutP1 = findViewById(R.id.combat_p1);
        LinearLayout linearLayoutP2 = findViewById(R.id.combat_p2);


        int y = ivP1.getWidth();
        int x = ivP1.getHeight();

        Random r = new Random();
        int i2 = r.nextInt(800 - y);

        Random t = new Random();
        int i1 = t.nextInt(500 - x);

        linearLayoutP1.setX(i1);
        linearLayoutP1.setY(i2);

*/

        ImageView boom1 = findViewById(R.id.img_degat_p1);
        ImageView boom2 = findViewById(R.id.img_degat_p2);

        ImageView critique1 = findViewById(R.id.img_critique_1);
        ImageView critique2 = findViewById(R.id.img_critique_2);

        Random degats = new Random();
        int p1Degat = degats.nextInt(player1.getCombat());
        int p2Degat = degats.nextInt(player2.getCombat());

        player2.setDurability(player2.getDurability() - p1Degat);
        if (p1Degat == 0) {
            textDegatP2.setText(R.string.esquive);
            boom2.setVisibility(View.GONE);
            critique2.setVisibility(View.INVISIBLE);
        } else if (p1Degat == player1.getCombat() -1) {
            textDegatP2.setText(R.string.coup_critique);
            boom2.setVisibility(View.GONE);
            critique2.setVisibility(View.VISIBLE);
        } else {
            textDegatP2.setText(getString(R.string.degat, String.valueOf(p1Degat)));
            boom2.setVisibility(View.VISIBLE);
            critique2.setVisibility(View.INVISIBLE);
        }
        textLifeP2.setText(String.valueOf(player2.getDurability()));

        if (player2.getDurability() <= 0) {
            textLifeP2.setText(String.valueOf(0));
        } else {
            player1.setDurability(player1.getDurability() - p2Degat);

            if (p2Degat == 0) {
                textDegatP1.setText(R.string.esquive);
                boom1.setVisibility(View.GONE);
                critique1.setVisibility(View.INVISIBLE);
            } else if (p2Degat == player2.getCombat() - 1) {
                textDegatP1.setText(R.string.coup_critique);
                boom1.setVisibility(View.GONE);
                critique1.setVisibility(View.VISIBLE);
            } else {
                textDegatP1.setText(getString(R.string.degat, String.valueOf(p2Degat)));
                boom1.setVisibility(View.VISIBLE);
                critique1.setVisibility(View.INVISIBLE);
            }
            textLifeP1.setText(String.valueOf(player1.getDurability()));

            if (player1.getDurability() <= 0) {
                textLifeP1.setText(String.valueOf(0));
            }
        }
    }
}


