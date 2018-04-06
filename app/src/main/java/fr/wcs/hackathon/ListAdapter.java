package fr.wcs.hackathon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
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

    public ListAdapter(Context context, ArrayList<HeroModel> heroes) {
        this.mContext = context;
        this.heroModel = heroes;
        this.filterList = heroes;
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

    public View getView(int position, View convertView, ViewGroup parent) {

        final HeroModel hero = heroModel.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list, null);
        }

        ImageView heroImage = convertView.findViewById(R.id.image_hero);
        TextView heroName = convertView.findViewById(R.id.tv_name);
        TextView heroDurability = convertView.findViewById(R.id.tv_durability);
        TextView heroCombat = convertView.findViewById(R.id.tv_combat);
        TextView heroSpeed = convertView.findViewById(R.id.tv_speed);
        TextView heroIntel = convertView.findViewById(R.id.tv_intel);


        Glide.with(mContext).load(hero.getImage()).into(heroImage);
        heroName.setText(hero.getName());
        heroDurability.setText(String.valueOf(hero.getDurability()));
        heroCombat.setText(String.valueOf(hero.getCombat()));
        heroSpeed.setText(String.valueOf(hero.getSpeed()));
        heroIntel.setText(String.valueOf(hero.getIntelligence()));

        FontHelper.setFont(heroName, "pix.ttf");
        FontHelper.setFont(heroDurability, "pix.ttf");
        FontHelper.setFont(heroCombat, "pix.ttf");
        FontHelper.setFont(heroSpeed, "pix.ttf");
        FontHelper.setFont(heroIntel, "pix.ttf");

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
