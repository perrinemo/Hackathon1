package fr.wcs.hackathon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by wilder on 05/04/18.
 */

public class ListAdapter extends ArrayAdapter<HeroModel>{
    ListAdapter(Context context, ArrayList<HeroModel> heroes) {
        super(context, 0, heroes);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        HeroModel hero = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
        }

        ImageView heroImage = convertView.findViewById(R.id.image_hero);
        TextView heroName = convertView.findViewById(R.id.tv_name);
        TextView heroDurability = convertView.findViewById(R.id.tv_durability);
        TextView heroCombat = convertView.findViewById(R.id.tv_combat);
        TextView heroSpeed = convertView.findViewById(R.id.tv_speed);
        TextView heroIntel = convertView.findViewById(R.id.tv_intel);


        heroImage.setImageResource(hero.getImage());
        heroName.setText(hero.getName());
        heroDurability.setText(String.valueOf(hero.getDurability()));
        heroCombat.setText(String.valueOf(hero.getCombat()));
        heroSpeed.setText(String.valueOf(hero.getSpeed()));
        heroIntel.setText(String.valueOf(hero.getInteligence()));
        return convertView;

    }
}
