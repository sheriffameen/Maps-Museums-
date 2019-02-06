package com.example.mapsappfromscratch;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.concurrent.FutureTask;

public class MuseumsAdapter extends RecyclerView.Adapter<MuseumsViewholder> {
    private List<Museum> museumList;

    public MuseumsAdapter(List<Museum> museumList) {
        this.museumList = museumList;
    }

    @NonNull
    @Override
    public MuseumsViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View childView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.museums_itemview,viewGroup,false);
        return new MuseumsViewholder(childView);
    }

    @Override
    public void onBindViewHolder(@NonNull MuseumsViewholder museumsViewholder, int i) {
        museumsViewholder.onBind(museumList.get(i));

    }

    @Override
    public int getItemCount() {
        return museumList.size();
    }
}
