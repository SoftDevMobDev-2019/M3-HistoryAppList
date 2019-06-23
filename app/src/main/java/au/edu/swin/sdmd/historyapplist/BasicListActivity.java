package au.edu.swin.sdmd.historyapplist;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import au.edu.swin.sdmd.historyapplist.landmarks.Landmark;
import au.edu.swin.sdmd.historyapplist.landmarks.Landmarks;

/**
 * This activity extends ListActivity, so there is no toolbar, just a list; some helper methods are
 * also included.
 * The list widget must be called @android:id/list.
 * For an activity which is just a list, this is the easiest way of showing data.
 * When an item is clicked, a Toast with coords is shown.
 *
 * @author nronald
 * @date August 2018
 */

public class BasicListActivity extends ListActivity {
    Landmarks mLandmarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);
        initialiseUI();
    }

    /**
     * Here we retrieve our data and set the list adapter. The adapter is a a default adapter
     * which uses a provided layout file (simple_list_item_1) that shows a TextView. A List of
     * <String> also needs to be passed so the list has something to display.
     */
    void initialiseUI() {
        mLandmarks = Landmarks.get(getApplicationContext());
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                mLandmarks.getLandmarkNames()));
    }

    @Override
    public void onListItemClick(ListView l, View view, int position, long id) {
        String selectedItem = (String) getListAdapter().getItem(position);
        displaySelectedItemInfo(selectedItem);
    }

    /**
     * Displays some info about a selected item
     * @param selectedItem the landmark to toast
     */
    void displaySelectedItemInfo(String selectedItem) {
        Landmark lm = mLandmarks.getLandmark(selectedItem);
        Toast.makeText(this.getApplicationContext(),
                String.format("%s: %s", selectedItem, lm.getCoords()),
                Toast.LENGTH_SHORT).show();
    }
}