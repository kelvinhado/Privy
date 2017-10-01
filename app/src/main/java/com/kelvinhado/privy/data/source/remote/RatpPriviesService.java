package com.kelvinhado.privy.data.source.remote;

/**
 * Created by kelvin on 30/09/2017.
 */

import com.kelvinhado.privy.data.source.remote.pojo.RatpPrivyPojo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RatpPriviesService {
    @GET("api/records/1.0/search/?dataset=sanisettesparis2011&rows=10")
    Call<RatpPrivyPojo> listPrivies();
}
