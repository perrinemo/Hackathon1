package fr.wcs.hackathon;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://cdn.rawgit.com/akabab/superhero-api/0.2.0/api/all.json";
        final TextView player1 = findViewById(R.id.tv_player1);
        final ListView heroList = findViewById(R.id.lv_heroes);
        final ImageView playerOneImage = findViewById(R.id.img_player_one);
        final ArrayList<HeroModel> allHeroesList = new ArrayList<>();
        final ArrayList<HeroModel> femaleHeroinesList = new ArrayList<>();
        final ArrayList<HeroModel> maleHeroesList = new ArrayList<>();
        final ListAdapter allHeoresAdapter = new ListAdapter(MainActivity.this, allHeroesList);
        final ListAdapter femaleHeroinesAdapter = new ListAdapter(MainActivity.this, femaleHeroinesList);
        final ListAdapter maleHeroesAdapter = new ListAdapter(MainActivity.this, maleHeroesList);
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
    }



}
