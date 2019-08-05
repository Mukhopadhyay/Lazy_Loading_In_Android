package com.example.lazyloadingofflineimplementation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 *
 * Custom Adapter Class for RecyclerView
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<Objects> objects;
    private Context context;    //Not required

    public MyAdapter(ArrayList<Objects> objects, Context context) {
        this.objects = objects;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivIcon, ivStar;
        TextView tvName, tvRandom, tvTime;
        boolean isClicked;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            isClicked = false;

            //Setting the views
            ivIcon = itemView.findViewById(R.id.ivIcon);
            ivStar = itemView.findViewById(R.id.ivStar);
            tvName = itemView.findViewById(R.id.tvName);
            tvRandom = itemView.findViewById(R.id.tvRandom);
            tvTime = itemView.findViewById(R.id.tvTime);

            /**
             * Star icon click
             */
            ivStar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!isClicked) {
                        ivStar.setImageResource(R.drawable.star_clicked);
                        isClicked = true;
                    }
                    else {
                        ivStar.setImageResource(R.drawable.star_unclicked);
                        isClicked = false;
                    }
                }
            });

        }
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, viewGroup, false);

        return new ViewHolder(view);
    }

    /**
     *
     * @param viewHolder
     * @param i
     * Binds the data to the view
     */
    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder viewHolder, int i) {

        /**
         * Setting the Icon based on the type assigned to the objects
         */
        if(objects.get(i).getType().equalsIgnoreCase("phone")) {
            viewHolder.ivIcon.setImageResource(R.drawable.phone);
        }
        else if(objects.get(i).getType().equalsIgnoreCase("mail")) {
            viewHolder.ivIcon.setImageResource(R.drawable.mail);
        }
        else {
            viewHolder.ivIcon.setImageResource(R.drawable.message);
        }

        viewHolder.ivStar.setImageResource(R.drawable.star_unclicked);
        viewHolder.tvName.setText(objects.get(i).getName());
        viewHolder.tvTime.setText(objects.get(i).getTime());
        viewHolder.tvRandom.setText(objects.get(i).getRandom());

    }

    @Override
    public int getItemCount() {
        return objects.size();
    }
}
