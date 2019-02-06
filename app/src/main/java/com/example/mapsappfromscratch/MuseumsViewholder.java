package com.example.mapsappfromscratch;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MuseumsViewholder extends RecyclerView.ViewHolder {
    public static final String MUSEUM_NAME = "museum name";
    public static final String ADDRESS = "address";
    public static final String CITY = "city";
    public static final String STATE = "state";
    public static final String ZIP = "zip";
    public TextView museumNameTextView;

    public MuseumsViewholder(@NonNull View itemView) {
        super(itemView);

        museumNameTextView = itemView.findViewById(R.id.name_display_textview);
    }


    public void onBind(final Museum museum){
        museumNameTextView.setText(museum.getName());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), MapsActivity.class);
                Bundle bundle = new Bundle();

                String museumName = museum.getName();
                String address = museum.getAddress();
                String city = museum.getCity();
                String state = museum.getState();
                String zip = museum.getZip();

                bundle.putString(MUSEUM_NAME,museumName);
                bundle.putString(ADDRESS,address);
                bundle.putString(CITY,city);
                bundle.putString(STATE,state);
                bundle.putString(ZIP,zip);

                intent.putExtras(bundle);

                itemView.getContext().startActivity(intent);

            }
        });



    }
}
