package jasontian.chessclockpro.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

import jasontian.chessclockpro.Adapters.ListViewAdapter;
import jasontian.chessclockpro.Preferences.Preferences;
import jasontian.chessclockpro.R;
import jasontian.chessclockpro.Preferences.PreferenceManager;

public class SelectionScreen extends AppCompatActivity {

    public ListViewAdapter listViewAdapter;
    public ListView listView;
    public ImageButton trashBTN;
    public ImageButton addBTN;
    public TextView emptyMSG;
    public static PreferenceManager preferenceManager;
    public static Preferences preferences;
    private ArrayList<String> names;
    private ArrayList<String> icons;
    private Activity activity;
    private int mode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectionscreen);
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        listView = findViewById(R.id.lvNewDevices);
        trashBTN = findViewById(R.id.trash);
        addBTN = findViewById(R.id.add);
        emptyMSG = findViewById(R.id.tv_empty);

        activity = this;
        names = new ArrayList<>();
        icons = new ArrayList<>();

        SharedPreferences sharedPreferences = activity.getPreferences(Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String temp = sharedPreferences.getString("start", "");

        if (temp.equals("")) {
            editor.putString("start", "true");
            editor.commit();
            preferenceManager.addSetting(this, "Bullet 1|1");
            preferenceManager.addSetting(this, "Bullet 2|1");
            preferenceManager.addSetting(this, "Blitz 3|2");
            preferenceManager.addSetting(this, "Blitz 5|5");
            preferenceManager.addSetting(this, "Rapid 10|10");
            preferenceManager.addSetting(this, "Rapid 15|10");
            preferenceManager.addSetting(this, "Classical 30|30");
            preferenceManager.addSetting(this, "Classical 60|60");

            preferences.addSetting(this, "Bullet 1|1", "60", "2", "1", "60" ,"2" ,"1", "bullet");
            preferences.addSetting(this, "Bullet 2|1", "120", "2", "1", "120" ,"2" ,"1", "bullet");
            preferences.addSetting(this, "Blitz 3|2", "180", "2", "2", "180" ,"2" ,"2", "blitz");
            preferences.addSetting(this, "Blitz 5|5", "300", "2", "5", "300" ,"2" ,"5", "blitz");
            preferences.addSetting(this, "Rapid 10|10", "600", "2", "10", "600" ,"2" ,"10", "rapid");
            preferences.addSetting(this, "Rapid 15|10", "900", "2", "10", "900" ,"2" ,"10", "rapid");
            preferences.addSetting(this, "Classical 30|30", "1800", "2", "30", "1800" ,"2" ,"30", "classical");
            preferences.addSetting(this, "Classical 60|60", "3600", "2", "60", "3600" ,"2" ,"60", "classical");
        }

        String[] times = preferenceManager.getTime(this);
        for (int i = 1; i < times.length; i++) {
            names.add(times[i]);
            Log.d(times[i], times[i]);
            icons.add(preferences.getTime(this, times[i])[6]);
        }

        if (names.size() == 0) {
            emptyMSG.setVisibility(View.VISIBLE);
        }

        listViewAdapter = new ListViewAdapter(getApplicationContext(), R.layout.list_adapter_view, names, icons);
        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView.setEnabled(false);

                final int pos = position;
                if (mode == 0) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setCancelable(true);
                        builder.setTitle("Delete '" + listView.getItemAtPosition(pos).toString() + "' ?");
                        builder.setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        preferenceManager.removeSetting(activity, listView.getItemAtPosition(pos).toString());
                                        //preferences.removeSetting(activity, listView.getItemAtPosition(pos).toString());
                                        listViewAdapter.removeItem(pos);
                                        listViewAdapter.notifyDataSetChanged();
                                        mode = 0;
                                        if (listViewAdapter.getCount() == 0) {
                                            emptyMSG.setVisibility(View.VISIBLE);
                                        }
                                    }
                                });
                        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();

                    listView.setEnabled(true);
                } else {
                    Intent intent = new Intent(getApplicationContext(), ClockActivity.class);
                    String[] information = preferences.getTime(activity, names.get(position));
                    intent.putExtra("time1", Integer.parseInt(information[0]));
                    intent.putExtra("incre1", Integer.parseInt(information[2]));
                    intent.putExtra("increType1", Integer.parseInt(information[1]));
                    intent.putExtra("time2", Integer.parseInt(information[3]));
                    intent.putExtra("increType2", Integer.parseInt(information[4]));
                    intent.putExtra("incre2", Integer.parseInt(information[5]));
                    finish();
                    startActivity(intent);
                }
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                updateList();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.trash:
                if (mode == 0)
                    mode = 1;
                else if (mode == 1)
                    mode = 0;
                updateList();
                return true;

            case R.id.add:
                Intent intent = new Intent(getApplicationContext(), TimePick.class);
                finish();
                startActivity(intent);
                return true;

            //case R.id.action_settings:
                //return true;

            default:
                return super.onOptionsItemSelected(item);

        }
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

    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    public void updateList() {
        if (mode == 1) {
            Log.d("test", String.valueOf(mode));
            for (int i = 0; i < listViewAdapter.getCount(); i ++) {
                //ImageView temp = listViewAdapter.getView(i, null, listView).findViewById(R.id.modify);
                ImageView temp = getViewByPosition(i, listView).findViewById(R.id.modify);
                temp.setImageResource(android.R.color.transparent);
            }
        } else {
            Log.d("test", String.valueOf(mode));
            for (int i = 0; i < listViewAdapter.getCount(); i ++) {
                //ImageView temp = listViewAdapter.getView(i, null, listView).findViewById(R.id.modify);
                ImageView temp = getViewByPosition(i, listView).findViewById(R.id.modify);

                int resID = getResId("delete", R.drawable.class);
                temp.setImageResource(resID);
            }
        }
    }
}