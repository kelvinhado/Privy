package com.kelvinhado.privy.data.source.remote;

/**
 * Created by kelvin on 30/09/2017.
 */

import com.kelvinhado.privy.data.source.remote.pojo.RatpPrivyPojo;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RatpPriviesService {
    @GET("api/records/1.0/search/?dataset=sanisettesparis2011&rows=1000")
    Observable<RatpPrivyPojo> listPrivies();
}
