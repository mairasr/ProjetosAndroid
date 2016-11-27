package com.fiap.carango.fragments;


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

import com.fiap.carango.CarroDetalheActivity;
import com.fiap.carango.R;
import com.fiap.carango.adapter.CarroListAdapter;
import com.fiap.carango.api.CarroAPI;
import com.fiap.carango.model.Carro;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarrosFragment extends Fragment implements Callback<List<Carro>> {

    protected RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private String tipo;
    private CarroListAdapter adapter;


    public CarrosFragment() {
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

        View view = inflater.inflate(R.layout.fragment_carros, container, false);

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
        loadCarros();
    }

    private void loadCarros() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.heiderlopes.com.br")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // prepara a chamada no Retrofit 2.0
        CarroAPI carroAPI = retrofit.create(CarroAPI.class);

        Call<List<Carro>> call = carroAPI.findBy(tipo);

        //chamada assincrona
        call.enqueue(this);
    }

    private CarroListAdapter.CarroOnClickListener onClickCarro() {
        return new CarroListAdapter.CarroOnClickListener() {
            @Override
            public void onClickCarro(View view, int idx) {

                Carro c = adapter.getItem(idx);

                Intent i = new Intent(getContext(), CarroDetalheActivity.class);
                i.putExtra("carro",c);
                startActivity(i);
            }
        };
    }


    @Override
    public void onResponse(Call<List<Carro>> call, Response<List<Carro>> response) {
        adapter = new CarroListAdapter(getContext(), response.body(), onClickCarro());
        //adapter = new CarroListAdapter(getContext(),response.body());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFailure(Call<List<Carro>> call, Throwable t) {
        Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

        /*Snackbar.make(recyclerView,t.getMessage(), Snackbar.LENGTH_INDEFINITE)
                .setAction("Action",null)
                .show();*/
    }
}
