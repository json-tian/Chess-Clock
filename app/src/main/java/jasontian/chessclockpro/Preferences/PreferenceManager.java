package jasontian.chessclockpro.Preferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class PreferenceManager {

    public static boolean addSetting(Activity activity, String item){
        String[] pref = getStringFromPreferences(activity,"", "times").split(",");
        ArrayList<String> test = new ArrayList<>(Arrays.asList(pref));
        if(test.get(0) != "") {
            test.set(0, String.valueOf(Integer.parseInt(test.get(0)) + 1));
            test.add(item);
        } else {
            test.set(0, "1");
            test.add(item);
        }
        String temp = "";
        for (int i = 0; i < test.size(); i ++) {
            temp += test.get(i);
            temp += ",";
        }
        temp = temp.substring(0, temp.length()-1);

        return putStringInPreferences(activity, temp, "times");
    }

    public static boolean removeSetting(Activity activity, String item) {
        String[] pref = getStringFromPreferences(activity, "", "times").split(",");
        ArrayList<String> newPref = new ArrayList<>(Arrays.asList(pref));

        for (int i = 1; i < pref.length; i ++ ) {
            if (newPref.get(i).equals(item)) {
                newPref.remove(i);
                Log.d("REMOVALLLLLLLLLLLLLLLLL", item);
                break;
            }
        }
        String temp = "";
        temp += String.valueOf(Integer.parseInt(pref[0]) - 1) + ",";
        for (int i = 1; i < newPref.size(); i ++) {
            temp += newPref.get(i);
            temp += ",";
        }
        temp = temp.substring(0, temp.length()-1);

        return putStringInPreferences(activity, temp, "times");
    }

    public static boolean exists(Activity activity, String item) {
        String[] pref = getStringFromPreferences(activity, "", "times").split(",");
        for (int i = 1; i < pref.length; i ++) {
            if (pref[i].equals(item))
                return true;
        }
        return false;
    }

    public static String[] getTime(Activity activity){
        String times = getStringFromPreferences(activity,"", "times");
        return convertStringToArray(times);
    }

    private static boolean putStringInPreferences(Activity activity, String item, String key){
        SharedPreferences sharedPreferences = android.preference.PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, item);
        editor.commit();
        return true;
    }

    private static String getStringFromPreferences(Activity activity,String defaultValue,String key){
        SharedPreferences sharedPreferences = android.preference.PreferenceManager.getDefaultSharedPreferences(activity);
        String temp = sharedPreferences.getString(key, defaultValue);
        return temp;
    }

    private static String[] convertStringToArray(String str){
        String[] arr = str.split(",");
        return arr;
    }
}
