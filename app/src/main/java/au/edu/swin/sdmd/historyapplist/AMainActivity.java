package au.edu.swin.sdmd.historyapplist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * This app demonstrates a few ways of setting up lists. The main activity simply contains links to
 * other activities. From this code you will see how to:
 * 1. use a simple list consisting of a text label only (BasicListActivity), which introduces the
 * concepts of adapter and data source;
 * 2. implement custom rows including text and a picture (CustomRowActivity);
 * 3. implement a ViewHolder pattern for improved scrolling (DViewHolderActivity); and
 * 4. turf that all out and just use RecyclerView (ERecyclerViewActivity), a new list widget.
 *
 * @author nronald
 * @date August 2018
 *
 */

public class AMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickSimpleList(View v) {
        Intent i = new Intent(getApplicationContext(), BasicListActivity.class);
        startActivity(i);
    }

    public void clickCustomRow(View v) {
        Intent i = new Intent(getApplicationContext(), CustomRowActivity.class);
        startActivity(i);
    }

    public void clickViewHolder(View v) {
        Intent i = new Intent(getApplicationContext(), DViewHolderActivity.class);
        startActivity(i);
    }

    public void clickRecyclerView(View v) {
        Intent i = new Intent(getApplicationContext(), ERecyclerViewActivity.class);
        startActivity(i);
    }
}
