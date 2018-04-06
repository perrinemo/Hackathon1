package fr.wcs.hackathon;

import android.content.Context;
import android.widget.Filter;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by wilder on 06/04/18.
 */
public class CustomFilter extends Filter{

    private ArrayList<HeroModel> filterList;
    private ListAdapter adapter;


    public CustomFilter(ArrayList<HeroModel> filterList, ListAdapter adapter) {

        this.filterList = filterList;
        this.adapter = adapter;
    }

    /** Création de la searchview qui va nous permettre de chercher un monstre par son nom
     * La searchview créait une nouvelle liste de monstres en les filtrant
     */
    public ArrayList<HeroModel> filteredHeroes;
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();

        if (constraint != null && constraint.length() > 0) {
            constraint = constraint.toString().toUpperCase();
            filteredHeroes = new ArrayList<>();

            for (int i = 0; i < filterList.size(); i++) {
                if (filterList.get(i).getName().toUpperCase().contains(constraint)) {
                    filteredHeroes.add(filterList.get(i));
                }
            }

            results.count = filteredHeroes.size();
            results.values = filteredHeroes;
        } else {
            results.count = filterList.size();
            results.values = filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.heroModel = (ArrayList<HeroModel>) results.values;
        adapter.notifyDataSetChanged();
    }


}

