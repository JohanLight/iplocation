package com.mexicandeveloper.iplocation.repository;


import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;

import com.mexicandeveloper.iplocation.db.IpLocationDao;
import com.mexicandeveloper.iplocation.model.IPGeolocationEntity;
import com.mexicandeveloper.iplocation.model.LocationResponse;
import com.mexicandeveloper.iplocation.network.LocationIpService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

public class Repository {

    private IpLocationDao ipLocationDao;
    private LocationIpService locationIpService;

    private Observable<LocationResponse> locationResponseObservable;

    @Inject
    public Repository(IpLocationDao ipLocationDao, LocationIpService locationIpService) {
        this.locationIpService = locationIpService;
        this.ipLocationDao = ipLocationDao;
    }


        public Single<IPGeolocationEntity> getIPLocation(String ip) {
            return ipLocationDao.retrieveLocation(ip)
                    .onErrorResumeNext(new Function<Throwable, SingleSource<? extends IPGeolocationEntity>>() {
                        @Override
                        public SingleSource<? extends IPGeolocationEntity> apply(Throwable throwable) throws Exception {
                            return fetchDataFromNetwork(ip);
                        }
                    })
                    .flatMap(new Function<IPGeolocationEntity, SingleSource<? extends IPGeolocationEntity>>() {
                        @Override
                        public SingleSource<? extends IPGeolocationEntity> apply(IPGeolocationEntity dataEntity) throws Exception {
                            if (dataEntity == null) {
                                return fetchDataFromNetwork(ip);
                            } else {
                                return Single.just(dataEntity);
                            }
                        }
                    });
        }

        public Single<IPGeolocationEntity> fetchDataFromNetwork(String ip) {
            return locationIpService.getLocation(ip)
                    .onErrorResumeNext(new Function<Throwable, SingleSource<? extends LocationResponse>>() {
                        @Override
                        public SingleSource<? extends LocationResponse> apply(Throwable throwable) throws Exception {
                            // Handle network error here, you can return a custom error or fallback data
                            return Single.error(new Exception("Network error"));
                        }
                    })
                    .flatMap(new Function<LocationResponse, SingleSource<? extends IPGeolocationEntity>>() {
                        @Override
                        public SingleSource<? extends IPGeolocationEntity> apply(LocationResponse apiResponse) throws Exception {

                            if(apiResponse.status.equals("fail")){
                                return Single.error(new Exception(apiResponse.message));
                            }
                            IPGeolocationEntity newDataEntity = apiResponse.toEntity();
                            ipLocationDao.insertIPLocation(newDataEntity)
                                    .onErrorComplete()
                                    .subscribe();
                            return Single.just(newDataEntity);
                        }
                    });
        }

    public void insertLocationDao(IPGeolocationEntity ipGeolocationEntity) {
        ipLocationDao.insertIPLocation(ipGeolocationEntity);
    }

    public void deleteLocation(String ip) {
        ipLocationDao.deleteLocation(ip);
    }

    public void deleteAll() {
        ipLocationDao.deleteAllLocation();
    }

    public LiveData<List<IPGeolocationEntity>> getAllLocation() {
        return ipLocationDao.getAllLocation();
    }
}
