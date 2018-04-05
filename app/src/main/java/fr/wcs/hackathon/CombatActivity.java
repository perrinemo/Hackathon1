package fr.wcs.hackathon;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);

        textLifeP1 = findViewById(R.id.text_life_p1);
        textLifeP2 = findViewById(R.id.text_life_p2);
        textDegatP1 = findViewById(R.id.text_degat_p1);
        textDegatP2 = findViewById(R.id.text_degat_p2);

        ImageView ivP1 = findViewById(R.id.img_player_one);
        ImageView ivP2 = findViewById(R.id.img_player_two);

        final int lifeP1 = 92;
        final int lifeP2 = 52;

        textLifeP1.setText(String.valueOf(lifeP1));
        textLifeP2.setText(String.valueOf(lifeP2));

        final int combatP1 = 15;
        final int combatP2 = 7;

        final HeroModel player1 = new HeroModel(lifeP1, combatP1);
        final HeroModel player2 = new HeroModel(lifeP2, combatP2);

        final Button btnFight = findViewById(R.id.btn_fight);

        btnFight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player1.getDurability() > 0 && player2.getDurability() > 0) {
                    fight(player1, player2);
                } else {
                    // TODO : ajouter l'intent vers la page winner/looser
                }
            }
        });


        FontHelper.setFont(btnFight, "pix.ttf");
        FontHelper.setFont(textLifeP1, "pix.ttf");
        FontHelper.setFont(textLifeP2, "pix.ttf");
        FontHelper.setFont(textDegatP1, "pix.ttf");
        FontHelper.setFont(textDegatP2, "pix.ttf");
    }

    public void fight(HeroModel player1, HeroModel player2) {

        LinearLayout linearLayoutP1 = findViewById(R.id.combat_p1);
        LinearLayout linearLayoutP2 = findViewById(R.id.combat_p2);

/*
        DisplayMetrics displayMetrics = linearLayoutP1.getResources().getDisplayMetrics();
        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        int y = (int) dpHeight;
        int x = (int) dpWidth;

        Random r = new Random();
        int i2 = r.nextInt(y);

        Random t = new Random();
        int i1 = t.nextInt(x / 2);

        linearLayoutP1.setX(i1);
        linearLayoutP1.setY(i2);
*/


        textLifeP2.setText(String.valueOf(player2.getDurability()));
        player2.setDurability(player2.getDurability() - player1.getCombat());
        textDegatP2.setText(getString(R.string.degat, String.valueOf(player1.getCombat())));
        if (player2.getDurability() <= 0) {
            textLifeP2.setText(String.valueOf(0));
        } else {
            player1.setDurability(player1.getDurability() - player2.getCombat());
            textLifeP1.setText(String.valueOf(player1.getDurability()));
            textDegatP1.setText(getString(R.string.degat, String.valueOf(player2.getCombat())));
            if (player1.getDurability() <= 0) {
                textLifeP1.setText(String.valueOf(0));
            }
        }
    }
}


