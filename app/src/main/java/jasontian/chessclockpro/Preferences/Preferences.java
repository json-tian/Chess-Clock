package jasontian.chessclockpro.Preferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public abstract class Preferences {
    //Type 1 - NONE, 2 - INCREMENT, 3 - DELAY
    public static boolean addSetting(Activity activity, String key, String time1, String type1,
                                     String incre1, String time2, String type2, String incre2,
                                     String icon){
        String temp = time1 + "," + type1 + "," + incre1 + "," + time2 + "," + type2 + "," + incre2
                + "," + icon;
        return putStringInPreferences(activity, temp, key);
    }

    public static String[] getTime(Activity activity, String key){
        String details = getStringFromPreferences(activity,"", key);
        return convertStringToArray(details);
    }
    private static boolean putStringInPreferences(Activity activity, String item, String key){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, item);
        editor.commit();
        return true;
    }
    private static String getStringFromPreferences(Activity activity,String defaultValue,String key){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);

        String temp = sharedPreferences.getString(key, defaultValue);

        return temp;
    }

    private static String[] convertStringToArray(String str){
        String[] arr = str.split(",");
        return arr;
    }
}
