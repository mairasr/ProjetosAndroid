package com.fiap.time.api;

/**
 * Created by rm31014 on 19/11/2016.
 */
import com.fiap.time.model.Time;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TimeAPI {

    @GET("/v2/57c49ba10f00007111b50c00")
    Call<List<Time>> listAll();
}

