package com.mexicandeveloper.iplocation.network;


import com.mexicandeveloper.iplocation.model.LocationResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LocationIpService {
    @GET("{ip}")
    Single<LocationResponse> getLocation(@Path("ip") String ip);
}
