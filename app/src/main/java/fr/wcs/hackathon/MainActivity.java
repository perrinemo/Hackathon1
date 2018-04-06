package fr.wcs.hackathon;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public boolean mP1Selected = false;
    int dur1;
    int combat1;
    int dur2;
    int combat2;
    String img1;
    String img2;
    Button btnGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://cdn.rawgit.com/akabab/superhero-api/0.2.0/api/all.json";
        final TextView player1 = findViewById(R.id.tv_player1);
        final TextView player2 = findViewById(R.id.tv_player2);
        final ListView heroList = findViewById(R.id.lv_heroes);
        final ImageView playerOneImage = findViewById(R.id.iv_player_one);
        final ImageView playerTwoImage = findViewById(R.id.iv_player_two);
        final ArrayList<HeroModel> allHeroesList = new ArrayList<>();
        final ArrayList<HeroModel> femaleHeroinesList = new ArrayList<>();
        final ArrayList<HeroModel> maleHeroesList = new ArrayList<>();
        btnGo = findViewById(R.id.btn_go);


        final ListAdapter allHeoresAdapter = new ListAdapter(MainActivity.this, allHeroesList, new ListAdapter.HeroClickListerner() {
            @Override
            public void onClick(HeroModel hero) {
                if (!mP1Selected) {
                    mP1Selected = true;
                    player1.setText(hero.getName());
                    Glide.with(MainActivity.this).load(hero.getImage()).into(playerOneImage);
                    dur1 = hero.getDurability();
                    combat1 = hero.getCombat();
                    img1 = hero.getImage();

                } else {
                    mP1Selected = false;
                    player2.setText(hero.getName());
                    Glide.with(MainActivity.this).load(hero.getImage()).into(playerTwoImage);
                    dur2 = hero.getDurability();
                    combat2 = hero.getCombat();
                    img2 = hero.getImage();
                    btnGo.setEnabled(true);
                }
            }
        });
        final ListAdapter femaleHeroinesAdapter = new ListAdapter(MainActivity.this, femaleHeroinesList, new ListAdapter.HeroClickListerner() {
            @Override
            public void onClick(HeroModel hero) {
                if (!mP1Selected) {
                    mP1Selected = true;
                    player1.setText(hero.getName());
                    Glide.with(MainActivity.this).load(hero.getImage()).into(playerOneImage);
                    dur1 = hero.getDurability();
                    combat1 = hero.getCombat();
                    img1 = hero.getImage();

                } else {
                    mP1Selected = false;
                    player2.setText(hero.getName());
                    Glide.with(MainActivity.this).load(hero.getImage()).into(playerTwoImage);
                    dur2 = hero.getDurability();
                    combat2 = hero.getCombat();
                    img2 = hero.getImage();
                    btnGo.setEnabled(true);
                }

            }
        });
        final ListAdapter maleHeroesAdapter = new ListAdapter(MainActivity.this, maleHeroesList, new ListAdapter.HeroClickListerner() {
            @Override
            public void onClick(HeroModel hero) {
                if (!mP1Selected) {
                    mP1Selected = true;
                    player1.setText(hero.getName());
                    Glide.with(MainActivity.this).load(hero.getImage()).into(playerOneImage);
                    dur1 = hero.getDurability();
                    combat1 = hero.getCombat();
                    img1 = hero.getImage();
                } else {
                    mP1Selected = false;
                    player2.setText(hero.getName());
                    Glide.with(MainActivity.this).load(hero.getImage()).into(playerTwoImage);
                    dur2 = hero.getDurability();
                    combat2 = hero.getCombat();
                    img2 = hero.getImage();
                    btnGo.setEnabled(true);
                }

            }
        });
        final CheckBox checkfemale = findViewById(R.id.check_female);
        final CheckBox checkmale = findViewById(R.id.check_male);
        final SearchView searchView = findViewById(R.id.searchView);

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(final JSONArray response) {
                        // TODO : traiter la réponse

                        try {
                            for (int j = 0; j < response.length(); j++) {

                                JSONObject heroResponse = response.getJSONObject(j);
                                JSONObject heroImages = heroResponse.getJSONObject("images");
                                JSONObject heroStats = heroResponse.getJSONObject("powerstats");
                                JSONObject heroAppearance = heroResponse.getJSONObject("appearance");
                                String image_url = heroImages.getString("sm");
                                String heroName = heroResponse.getString("name");
                                String heroGender = heroAppearance.getString("gender");
                                int life = heroStats.getInt("durability");
                                int damage = heroStats.getInt("combat");
                                int speed = heroStats.getInt("speed");
                                int intel = heroStats.getInt("intelligence");
                                allHeroesList.add(new HeroModel(image_url, heroName, heroGender, life, damage, speed, intel));


                                if (heroGender.equals("Female")) {
                                    femaleHeroinesList.add(new HeroModel(image_url, heroName, heroGender, life, damage, speed, intel));
                                }
                                else if (heroGender.equals("Male")) {
                                    maleHeroesList.add(new HeroModel(image_url, heroName, heroGender, life, damage, speed, intel));
                                }
                            }
                            heroList.setAdapter(allHeoresAdapter);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }


                        checkfemale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                                if (checkfemale.isChecked()) {

                                    checkmale.setChecked(false);
                                    heroList.setAdapter(femaleHeroinesAdapter);
                                }else {

                                    heroList.setAdapter(allHeoresAdapter);
                                }
                            }
                        });

                        checkmale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                                if (checkmale.isChecked()) {

                                    checkfemale.setChecked(false);
                                    heroList.setAdapter(maleHeroesAdapter);
                                }else {
                                    heroList.setAdapter(allHeoresAdapter);
                                }
                            }
                        });
                        heroList.setAdapter(allHeoresAdapter);
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("VOLLEY_ERROR", "onErrorResponse: " + error.getMessage());
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                allHeoresAdapter.getFilter().filter(newText);
                femaleHeroinesAdapter.getFilter().filter(newText);
                maleHeroesAdapter.getFilter().filter(newText);

                return false;

            }
        });




        FontHelper.setFont(player1, "pix.ttf");
        FontHelper.setFont(player2, "pix.ttf");
        FontHelper.setFont(btnGo, "pix.ttf");
        FontHelper.setFont(checkfemale, "pix.ttf");
        FontHelper.setFont(checkmale, "pix.ttf");


        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CombatActivity.class);
                String name1 = player1.getText().toString();
                String name2 = player2.getText().toString();
                HeroModel hero1 = new HeroModel(img1, name1, dur1, combat1);
                HeroModel hero2 = new HeroModel(img2, name2, dur2, combat2);

                intent.putExtra("hero1", hero1);
                intent.putExtra("hero2", hero2);
                MainActivity.this.startActivity(intent);


            }
        });




    }

}
