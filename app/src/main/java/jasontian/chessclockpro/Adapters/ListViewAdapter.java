package jasontian.chessclockpro.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

import jasontian.chessclockpro.R;

public class ListViewAdapter extends ArrayAdapter<String> {

    private LayoutInflater mLayoutInflater;
    private static ArrayList<String> names;
    private static ArrayList<String> icons;
    private int mViewResourceId;

    public ListViewAdapter(Context context, int tvResourceId, ArrayList<String> names, ArrayList<String> icons){
        super(context, tvResourceId, names);
        this.names = names;
        this.icons = icons;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = tvResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mLayoutInflater.inflate(mViewResourceId, null);
        String name = names.get(position);
        String icon = icons.get(position);
        TextView setting = convertView.findViewById(R.id.name);
        ImageView image = convertView.findViewById(R.id.icon);
        setting.setText(name);

        int resID = getResId(icon, R.drawable.class);
        image.setImageResource(resID);
        return convertView;
    }

    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void removeItem(int position) {
        names.remove(position);
        icons.remove(position);
    }

}
