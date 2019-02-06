package com.example.mapsappfromscratch;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private RecyclerView museumsRecyclerView;
    private MuseumsAdapter museumsAdapter;


    private static final String TAG = "Sheriff" ;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(){
        MainFragment mainFragment = new MainFragment();
        Bundle arguments = new Bundle();

        return mainFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Retrofit retrofit = RetrofitSingleton.getInstance();
        MuseumsService museumsService = retrofit.create(MuseumsService.class);

        museumsRecyclerView = rootView.findViewById(R.id.museums_recyclerview);


        Call<MuseumsList> museumsCall = museumsService.getMuseums();
        museumsCall.enqueue(new Callback<MuseumsList>() {
            @Override
            public void onResponse(Call<MuseumsList> call, Response<MuseumsList> response) {
                Log.d(TAG,"onResponse" + response.body());
                if (response.body() == null){
                    //d Do some error handling
                    Log.d(TAG,"BODY IS NULL");

                } else {
                    //show planets
                    List<Museum> museumList = response.body().getMuseums();
                    museumsAdapter = new MuseumsAdapter(museumList);
                    museumsRecyclerView.setAdapter(museumsAdapter);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(rootView.getContext(),3);
                    museumsRecyclerView.setLayoutManager(gridLayoutManager);

                }

            }

            @Override
            public void onFailure(Call<MuseumsList> call, Throwable t) {

            }
        });


        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




    }
}
