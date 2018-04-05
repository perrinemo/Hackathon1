package fr.wcs.hackathon;

import android.graphics.Typeface;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by perrine on 05/04/18.
 */

public class FontHelper {


    public static void setFont(TextView textView, String fontName) {
        if (fontName != null) {
            try {
                Typeface typeface = Typeface.createFromAsset(textView.getContext().getAssets(),
                        "fonts/" + fontName);
                textView.setTypeface(typeface);
            } catch (Exception e) {
                Log.e("FONT " + fontName + " not found", e.getMessage());
            }
        }
    }



/**
 * Pour changer la police, se mettre dans la bonne activity et faire :
 *
 * Helper.setFont(**le nom du texte à changer**, "pix.ttf");
 */

}
