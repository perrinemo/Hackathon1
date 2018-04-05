package fr.wcs.hackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CombatActivity extends AppCompatActivity {

    TextView textLifeP1;
    TextView textLifeP2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);

        textLifeP1 = findViewById(R.id.text_life_p1);
        textLifeP2 = findViewById(R.id.text_life_p2);

        ImageView ivP1 = findViewById(R.id.img_player_one);
        ImageView ivP2 = findViewById(R.id.img_player_two);

        final int lifeP1 = 92;
        final int lifeP2 = 52;

        textLifeP1.setText(String.valueOf(lifeP1));
        textLifeP2.setText(String.valueOf(lifeP2));

        final int combatP1 = 3;
        final int combatP2 = 7;

        final HeroModel player1 = new HeroModel(lifeP1, combatP1);
        final HeroModel player2 = new HeroModel(lifeP2, combatP2);

        final Button btnFight = findViewById(R.id.btn_fight);

        btnFight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fight(player1, player2);
            }

        });

        FontHelper.setFont(btnFight, "pix.ttf");
        FontHelper.setFont(textLifeP1, "pix.ttf");
        FontHelper.setFont(textLifeP2, "pix.ttf");
    }

    public void fight(HeroModel player1, HeroModel player2) {
            textLifeP2.setText(String.valueOf(player2.getDurability()));
            player2.setDurability(player2.getDurability() - player1.getCombat());
            if (player2.getDurability() <= 0) {
                textLifeP2.setText(String.valueOf(0));
            } else {
                player1.setDurability(player1.getDurability() - player2.getCombat());
                textLifeP1.setText(String.valueOf(player1.getDurability()));
                if (player1.getDurability() <= 0) {
                    textLifeP1.setText(String.valueOf(0));

            }
        }
    }
}


