package au.edu.swin.sdmd.historyapplist;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import au.edu.swin.sdmd.historyapplist.landmarks.Landmark;
import au.edu.swin.sdmd.historyapplist.landmarks.Landmarks;

/**
 * Finally we use a RecyclerView widget to create our list.
 *
 * @author nronald
 * @date August 2018
 */

public class ERecyclerViewActivity extends AppCompatActivity {
    Landmarks mLandmarks;
    RecyclerView.LayoutManager mLayoutManager;
    RVAdapter mAdapter;
    RecyclerView rvLandmarks;
    List<String> mLandmarkNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initialiseUI();
    }

    void initialiseUI() {
        mLandmarks = Landmarks.get(getApplicationContext());
        mLandmarkNames = mLandmarks.getLandmarkNames();

        /**
         * RecyclerViews need a layout manager as well as an adapter.
         */
        rvLandmarks = findViewById(R.id.rvLandmarks);
        mLayoutManager = new LinearLayoutManager(this);
        rvLandmarks.setLayoutManager(mLayoutManager);

        // specify an adapter
        mAdapter = new RVAdapter(mLandmarkNames);
        rvLandmarks.setAdapter(mAdapter);
    }


    /**
     * This is our new shiny adapter. RecyclerViews have different methods, but essentially cover
     * all that we did for the ViewHolder (with stashing and retrieving views, and a separate
     * method for binding).
     */
    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {
        List<String> mValues;

        public RVAdapter(List<String> values) {
            mValues = values;
        }

        /**
         * This method creates a ViewHolder if needed. The click listener is also set here.
         * @param parent
         * @param viewType
         * @return
         */
        @NonNull
        @Override
        public RVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View v = (View) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_row, parent, false);
            v.setOnClickListener(oclMyListener);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }


        /**
         * This is the binding code.
         * @param holder
         * @param position
         */
        @Override
        public void onBindViewHolder(@NonNull RVAdapter.ViewHolder holder, int position) {
            String name = mValues.get(position);
            holder.mTextView.setText(name);
            if (name.startsWith("Swinburne College"))
                holder.mImage.setImageResource(R.drawable.college);
            else if (name.startsWith("Glenferrie Station"))
                holder.mImage.setImageResource(R.drawable.station);
            else if (name.startsWith("Theatre"))
                holder.mImage.setImageResource(R.drawable.theatre);
            else
                holder.mImage.setImageResource(android.R.drawable.ic_menu_mapmode);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;
            public ImageView mImage;

            public ViewHolder(View v) {
                super(v);
                mTextView = v.findViewById(R.id.item_title);
                mImage = v.findViewById(R.id.item_image);
            }
        }
    }

    /**
     * Item clicks need to be handled differently; there are several approaches but this way
     * attaches the listener to the row in the adapter (see above).
     */
    View.OnClickListener oclMyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = rvLandmarks.getChildLayoutPosition(v);
            String name = mLandmarkNames.get(position);
            displaySelectedItemInfo(name);
        }
    };

    void displaySelectedItemInfo(String selectedItem) {
        Landmark lm = mLandmarks.getLandmark(selectedItem);
        Toast.makeText(this.getApplicationContext(),
                String.format("%s: %s", selectedItem, lm.getCoords()),
                Toast.LENGTH_SHORT).show();
    }

}
