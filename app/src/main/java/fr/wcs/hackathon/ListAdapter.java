package fr.wcs.hackathon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by wilder on 05/04/18.
 */

public class ListAdapter extends BaseAdapter implements Filterable{

    private final Context mContext;
    public ArrayList<HeroModel> heroModel;
    private CustomFilter filter;
    private ArrayList<HeroModel> filterList;
    private HeroClickListerner listener;

    public ListAdapter(Context context, ArrayList<HeroModel> heroes, HeroClickListerner listener) {
        this.mContext = context;
        this.heroModel = heroes;
        this.filterList = heroes;
        this.listener = listener;
    }

    public interface HeroClickListerner {
        public void onClick (HeroModel hero);


    }

    @Override
    public int getCount() {
        return heroModel.size();
    }

    @Override
    public Object getItem(int position) {
        return heroModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        final HeroModel hero = heroModel.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list, null);
        }



        final TextView player1 = convertView.findViewById(R.id.tv_player1);
        final TextView player2 = convertView.findViewById(R.id.tv_player2);
        final ImageView playerOneImage = convertView.findViewById(R.id.iv_player_one);
        final ImageView playerTwoImage = convertView.findViewById(R.id.iv_player_two);
        final ArrayList<HeroModel> allHeroesList = new ArrayList<>();
        final ArrayList<HeroModel> femaleHeroinesList = new ArrayList<>();
        final ArrayList<HeroModel> maleHeroesList = new ArrayList<>();
        final CheckBox checkfemale = convertView.findViewById(R.id.check_female);
        final CheckBox checkmale = convertView.findViewById(R.id.check_male);
        final SearchView searchView = convertView.findViewById(R.id.searchView);

        ImageView heroImage = convertView.findViewById(R.id.image_hero);
        TextView heroName = convertView.findViewById(R.id.tv_name);
        TextView heroDurability = convertView.findViewById(R.id.tv_durability);
        TextView heroCombat = convertView.findViewById(R.id.tv_combat);
        TextView heroSpeed = convertView.findViewById(R.id.tv_speed);
        TextView heroIntel = convertView.findViewById(R.id.tv_intel);

        final String name = hero.getName();
        final String image = hero.getImage();


        Glide.with(mContext).load(hero.getImage()).into(heroImage);
        heroName.setText(name);
        heroDurability.setText(String.valueOf(hero.getDurability()));
        heroCombat.setText(String.valueOf(hero.getCombat()));
        heroSpeed.setText(String.valueOf(hero.getSpeed()));
        heroIntel.setText(String.valueOf(hero.getIntelligence()));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(hero);
            }
        });

       /* convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                player1.setText(name);
                //Glide.with(mContext).load(hero.getImage()).into(playerOneImage);


                if (playerOneImage.getContentDescription() == null && playerTwoImage.getContentDescription() == null) {
                    if (checkfemale.isChecked()){
                        player1.setText(femaleHeroinesList.getName());
                        Glide.with(mContext).load(femaleHeroinesList.get(position).getImage()).into(playerOneImage);
                    }
                    else if (checkmale.isChecked()) {
                        player1.setText(maleHeroesList.get(position).getName());
                        Glide.with(mContext).load(maleHeroesList.get(position).getImage()).into(playerOneImage);
                    }
                    else {
                        player1.setText(allHeroesList.get(position).getName());
                        Glide.with(mContext).load(allHeroesList.get(position).getImage()).into(playerOneImage);
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

                if (searchView.getContentDescription() != null) {
                    player1.setText(filteredHeroes.get(position).getName());
                    Glide.with(MainActivity.this).load(filteredHeroes.get(position).getImage()).into(playerOneImage);                }

            }
        });*/

        return convertView;

    }
    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter(filterList, this);
        }
        return filter;
    }

}
