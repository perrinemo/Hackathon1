package fr.wcs.hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class EndOfFight extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_of_fight);

        Intent intent = getIntent();
        String durab1 = intent.getStringExtra("durab1");
        String durab2 = intent.getStringExtra("durab2");

        String name1 = intent.getStringExtra("name1");
        String name2 = intent.getStringExtra("name2");

        String im1 = intent.getStringExtra("img1");
        String im2 = intent.getStringExtra("img2");

        TextView tvWinner = findViewById(R.id.text_winner);
        TextView tvLooser = findViewById(R.id.text_looser);

        ImageView ivWinner = findViewById(R.id.img_winner);
        ImageView ivLooser = findViewById(R.id.img_looser);

        Button btnReplay = findViewById(R.id.btn_rejouer);


        int dur1 = Integer.parseInt(durab1);
        int dur2 = Integer.parseInt(durab2);

        if (dur1 > dur2) {
            tvWinner.setText(name1);
            tvLooser.setText(name2);
            Glide.with(EndOfFight.this).load(im1).into(ivWinner);
            Glide.with(EndOfFight.this).load(im2).into(ivLooser);
        } else {
            tvLooser.setText(name1);
            tvWinner.setText(name2);
            Glide.with(EndOfFight.this).load(im2).into(ivWinner);
            Glide.with(EndOfFight.this).load(im1).into(ivLooser);
        }

        btnReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndOfFight.this, MainActivity.class);
                startActivity(intent);
            }
        });

        FontHelper.setFont(tvWinner, "pix.ttf");
        FontHelper.setFont(tvLooser, "pix.ttf");
        FontHelper.setFont(btnReplay, "pix.ttf");
    }
}
