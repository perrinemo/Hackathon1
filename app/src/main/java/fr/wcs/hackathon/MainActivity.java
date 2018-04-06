package fr.wcs.hackathon;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
        final String p1 = player1.getText().toString();
        final String p2 = player2.getText().toString();

        final ListAdapter allHeoresAdapter = new ListAdapter(MainActivity.this, allHeroesList, new ListAdapter.HeroClickListerner() {
            @Override
            public void onClick(HeroModel hero) {
                if (!mP1Selected) {
                    mP1Selected = true;
                    player1.setText(hero.getName());
                    Glide.with(MainActivity.this).load(hero.getImage()).into(playerOneImage);
                } else {
                    player2.setText(hero.getName());
                    Glide.with(MainActivity.this).load(hero.getImage()).into(playerTwoImage);
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
                } else {
                    player2.setText(hero.getName());
                    Glide.with(MainActivity.this).load(hero.getImage()).into(playerTwoImage);
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
                } else {
                    player2.setText(hero.getName());
                    Glide.with(MainActivity.this).load(hero.getImage()).into(playerTwoImage);
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

      /*  heroList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (playerOneImage.getContentDescription() == null && playerTwoImage.getContentDescription() == null) {
                    if (checkfemale.isChecked()){
                        player1.setText(femaleHeroinesList.get(position).getName());
                        Glide.with(MainActivity.this).load(femaleHeroinesList.get(position).getImage()).into(playerOneImage);
                    }
                    else if (checkmale.isChecked()) {
                        player1.setText(maleHeroesList.get(position).getName());
                        Glide.with(MainActivity.this).load(maleHeroesList.get(position).getImage()).into(playerOneImage);
                    }
                    else {
                        player1.setText(allHeroesList.get(position).getName());
                        Glide.with(MainActivity.this).load(allHeroesList.get(position).getImage()).into(playerOneImage);
                    }
                }
                else if (playerOneImage.getContentDescription() != null && playerTwoImage.getContentDescription() == null) {
                    if (checkfemale.isChecked()){
                        player1.setText(femaleHeroinesList.get(position).getName());
                        Glide.with(MainActivity.this).load(femaleHeroinesList.get(position).getImage()).into(playerOneImage);
                    }
                    else if (checkmale.isChecked()) {
                        player1.setText(maleHeroesList.get(position).getName());
                        Glide.with(MainActivity.this).load(maleHeroesList.get(position).getImage()).into(playerOneImage);
                    }
                    else {
                        player1.setText(allHeroesList.get(position).getName());
                        Glide.with(MainActivity.this).load(allHeroesList.get(position).getImage()).into(playerOneImage);
                    }
                    player2.setText(allHeroesList.get(position).getName());
                    Glide.with(MainActivity.this).load(allHeroesList.get(position).getImage()).into(playerTwoImage);
                }

               /* if (searchView.getContentDescription() != null) {
                    player1.setText(filteredHeroes.get(position).getName());
                    Glide.with(MainActivity.this).load(filteredHeroes.get(position).getImage()).into(playerOneImage);                }

            }
        });*/

    }



}
