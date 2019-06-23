package au.edu.swin.sdmd.historyapplist;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import au.edu.swin.sdmd.historyapplist.landmarks.Landmark;
import au.edu.swin.sdmd.historyapplist.landmarks.Landmarks;

/**
 * In this  activity we will use a ViewHolder pattern to store the views in the rows ready for
 * display. This cuts down on the amount of time spent calling findViewById().
 *
 * @author nronald
 * @date August 2018
 */

public class DViewHolderActivity extends AppCompatActivity {
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
        lvLandmarks.setAdapter(new ViewHolderAdapter(this, mLandmarks.getLandmarkNames()));
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
     * Note the adapter has changed slightly; the list of values is now being passed in.
     */
    class ViewHolderAdapter extends ArrayAdapter<String> {
        Context mContext;
        List<String> mValues;

        public ViewHolderAdapter(Context context, List<String> values) {
            super(context, R.layout.custom_row, R.id.item_title, values);
            mContext = context;
            mValues = values;
        }

        /**
         * getView in particular has changed to use the ViewHolder pattern.
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView;
            ViewHolder viewHolder;

            /**
             * If the row did not previously exist, then create a ViewHolder for it and stash the
             * views. Otherwise grab the views already stashed.
             */
            if (convertView == null) {
                rowView = LayoutInflater.from(mContext).inflate(R.layout.custom_row, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.name = rowView.findViewById(R.id.item_title);
                viewHolder.image = rowView.findViewById(R.id.item_image);
                rowView.setTag(viewHolder);
            } else {
                rowView = convertView;
                viewHolder = (ViewHolder) convertView.getTag();
            }

            // Binding values to the views
            String name = mValues.get(position);
            viewHolder.name.setText(name);
            if (name.startsWith("Swinburne College"))
                viewHolder.image.setImageResource(R.drawable.college);
            else if (name.startsWith("Glenferrie Station"))
                viewHolder.image.setImageResource(R.drawable.station);
            else if (name.startsWith("Theatre"))
                viewHolder.image.setImageResource(R.drawable.theatre);
            else
                viewHolder.image.setImageResource(android.R.drawable.ic_menu_mapmode);
            return rowView;
        }


    }

    /**
     * The ViewHolder object holds the actual views, not the values.
     */
    static class ViewHolder {
        TextView name;
        ImageView image;
    }

}
