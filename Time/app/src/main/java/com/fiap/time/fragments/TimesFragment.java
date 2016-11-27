package com.fiap.time.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fiap.time.TimeDetalheActivity;
import com.fiap.time.R;
import com.fiap.time.adapter.TimeListAdapter;
import com.fiap.time.api.TimeAPI;
import com.fiap.time.model.Time;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimesFragment extends Fragment implements Callback<List<Time>> {

    protected RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private String tipo;
    private TimeListAdapter adapter;


    public TimesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.tipo = getArguments().getString("tipo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_times, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadTimes();
    }

    private void loadTimes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.mocky.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // prepara a chamada no Retrofit 2.0
        TimeAPI timeAPI = retrofit.create(TimeAPI.class);

        Call<List<Time>> call = timeAPI.listAll();

        //chamada assincrona
        call.enqueue(this);
    }

    private TimeListAdapter.TimeOnClickListener onClickTime() {
        return new TimeListAdapter.TimeOnClickListener() {
            @Override
            public void onClickTime(View view, int idx) {

                Time t = adapter.getItem(idx);

                Intent i = new Intent(getContext(), TimeDetalheActivity.class);
                i.putExtra("time",t);
                startActivity(i);
            }
        };
    }


    @Override
    public void onResponse(Call<List<Time>> call, Response<List<Time>> response) {
        adapter = new TimeListAdapter(getContext(), response.body(), onClickTime());
        //adapter = new TimeListAdapter(getContext(),response.body());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFailure(Call<List<Time>> call, Throwable t) {
        Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

        /*Snackbar.make(recyclerView,t.getMessage(), Snackbar.LENGTH_INDEFINITE)
                .setAction("Action",null)
                .show();*/
    }
}
