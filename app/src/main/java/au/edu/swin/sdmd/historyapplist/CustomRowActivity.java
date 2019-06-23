package au.edu.swin.sdmd.historyapplist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import au.edu.swin.sdmd.historyapplist.landmarks.Landmark;
import au.edu.swin.sdmd.historyapplist.landmarks.Landmarks;

/**
 * In this Activity, we've switched back to AppCompatActivity to demonstrate another way of setting
 * up a list. In this case we no longer have the helper functions.
 *
 * @author nronald
 * @date August 2018
 */

public class CustomRowActivity extends AppCompatActivity {
    Landmarks mLandmarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_row);
        initialiseUI();
    }

    void initialiseUI() {
        mLandmarks = Landmarks.get(getApplicationContext());
        ListView lvLandmarks = findViewById(R.id.lvLandmarks);
        lvLandmarks.setAdapter(new CustomRowAdapter());
        lvLandmarks.setOnItemClickListener(itemClicked);
    }

    AdapterView.OnItemClickListener itemClicked = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String selectedItem = (String) parent.getItemAtPosition(position);
            displaySelectedItemInfo(selectedItem);
        }
    };

    void displaySelectedItemInfo(String selectedItem) {
        Landmark lm = mLandmarks.getLandmark(selectedItem);
        Toast.makeText(this.getApplicationContext(),
                String.format("%s: %s", selectedItem, lm.getCoords()),
                Toast.LENGTH_SHORT).show();
    }


    /**
     * This is an adapter that extends the original adapter we used.
     */
    class CustomRowAdapter extends ArrayAdapter<String> {
        public CustomRowAdapter() {
            super(CustomRowActivity.this, R.layout.custom_row, R.id.item_title,
                    mLandmarks.getLandmarkNames());
        }

        /**
         * This function creates rows as needed. Note the text is already set from the list
         * provided in the constructor.
         * @param position row to construct
         * @param convertView original row in that position
         * @param parent the ListView
         * @return the row to display
         */
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = super.getView(position, convertView, parent);
            ImageView icon = (ImageView) row.findViewById(R.id.item_image);
            String name = mLandmarks.getLandmarkNames().get(position);
            Log.i("ROW CREATED", name);
            if (name.startsWith("Swinburne College"))
                icon.setImageResource(R.drawable.college);
            else if (name.startsWith("Glenferrie Station"))
                icon.setImageResource(R.drawable.station);
            else if (name.startsWith("Theatre"))
                icon.setImageResource(R.drawable.theatre);
            else
                icon.setImageResource(android.R.drawable.ic_menu_mapmode);
            return row;
        }
    }



}
