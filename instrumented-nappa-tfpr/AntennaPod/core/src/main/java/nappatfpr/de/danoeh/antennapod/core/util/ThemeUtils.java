package nappatfpr.de.danoeh.antennapod.core.util;

import android.content.Context;
import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import android.util.Log;
import android.util.TypedValue;

import nappatfpr.de.danoeh.antennapod.core.R;
import nappatfpr.de.danoeh.antennapod.core.preferences.UserPreferences;

public class ThemeUtils {
    private ThemeUtils(){}

    private static final String TAG = "ThemeUtils";

    public static int getSelectionBackgroundColor() {
        int theme = UserPreferences.getTheme();
        if (theme == R.style.Theme_AntennaPod_Dark) {
            return R.color.selection_background_color_dark;
        } else if (theme == R.style.Theme_AntennaPod_TrueBlack){
            return R.color.selection_background_color_trueblack;
        } else if (theme == R.style.Theme_AntennaPod_Light) {
            return R.color.selection_background_color_light;
        } else {
            Log.e(TAG,
                    "getSelectionBackgroundColor could not match the current theme to any color!");
            return R.color.selection_background_color_light;
        }
    }

    public static @ColorInt int getColorFromAttr(Context context, @AttrRes int attr) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attr, typedValue, true);
        return typedValue.data;
    }
}