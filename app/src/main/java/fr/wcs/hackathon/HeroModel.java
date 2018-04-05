package fr.wcs.hackathon;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wilder on 05/04/18.
 */

public class HeroModel implements Parcelable {

    private Drawable image;
    private String name;
    private String genre;
    private int durability;
    private int combat;
    private int speed;
    private int inteligence;


    public HeroModel(Drawable image, String name, String genre, int durability, int combat) {
        this.image = image;
        this.name = name;
        this.genre = genre;
        this.durability = durability;
        this.combat = combat;
    }

    public Drawable getImage() {return image;}
    public void setImage(Drawable image) {this.image = image;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getGenre() {return genre;}
    public void setGenre(String genre) {this.genre = genre;}

    public int getDurability() {return durability;}
    public void setDurability(int durability) {this.durability = durability;}

    public int getCombat() {return combat;}
    public void setCombat(int combat) {this.combat = combat;}

    public int getSpeed() {return speed;}
    public void setSpeed(int speed) {this.speed = speed;}

    public int getInteligence() {return inteligence;}
    public void setInteligence(int inteligence) {this.inteligence = inteligence;}



    protected HeroModel(Parcel in) {
        name = in.readString();
        genre = in.readString();
        durability = in.readInt();
        combat = in.readInt();
    }

    public static final Creator<HeroModel> CREATOR = new Creator<HeroModel>() {
        @Override
        public HeroModel createFromParcel(Parcel in) {
            return new HeroModel(in);
        }

        @Override
        public HeroModel[] newArray(int size) {
            return new HeroModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(genre);
        parcel.writeInt(durability);
        parcel.writeInt(combat);
    }
}
