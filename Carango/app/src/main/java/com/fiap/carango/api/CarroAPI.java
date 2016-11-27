package com.fiap.carango.api;

/**
 * Created by rm31014 on 19/11/2016.
 */
import com.fiap.carango.model.Carro;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CarroAPI {

    @GET("/carros/tipo/{tipo}")
    Call<List<Carro>> findBy(@Path("tipo") String tags);
}

