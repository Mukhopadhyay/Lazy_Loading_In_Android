package com.example.lazyloadingofflineimplementation;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private LinearLayoutManager linearLayoutManager;

    private ArrayList<Objects> objects;

    UtilityClass utilityClass = new UtilityClass();

    /**
     *
     * currentItems = Number of items currently being displayed
     * totalItems = Number of items in the list, i.e., fed into the adapter
     * scrolledOutItems = How many items of the list are scrolled out, i.e, now currently on above the viewport
     *
     */

    //Variables to indicate if new items are required or not
    int currentItems, totalItems, scrolledOutItems;

    //Variable to indicate if we are currently scrolling or not
    Boolean isScrolling = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle("Lazy Loading");

        //Declaring RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        objects = new ArrayList<Objects>();

        linearLayoutManager = new LinearLayoutManager(this);

        //Custom class to load the ArrayList with data
        utilityClass.setArrayList(objects, 15);
        //Setting up Custom Adapter
        myAdapter = new MyAdapter(objects, this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);

        /**
         *
         * On Scroll listener to implement lazy loading the RecyclerView
         *
         */

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                {
                    /**
                     * Finding out if we are currently scrolling or not, if yes,
                     * setting the boolean to true
                     */
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //How many items are visible
                currentItems = linearLayoutManager.getChildCount();
                //Total number of items in the rv adapter
                totalItems = linearLayoutManager.getItemCount();
                //Number of elements that are scrolled up from the top
                scrolledOutItems = linearLayoutManager.findFirstVisibleItemPosition();

                if (isScrolling && (currentItems + scrolledOutItems == totalItems)) {

                    /**
                     *
                     * If we are scrolling and currentItems and scrolledOutItems'
                     * sum equates to the total number of item count, that means
                     * we have reached the end of the list in the adapter, so,
                     * we are fetching more data
                     *
                     */


                    //This is where the web services will take place
                    utilityClass.fetchDate(1000, 5, objects, myAdapter);

                }
            }
        });

    }
}
