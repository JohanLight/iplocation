package com.mexicandeveloper.iplocation.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mexicandeveloper.iplocation.model.IPGeolocationEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;


@Dao
public interface IpLocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertIPLocation(IPGeolocationEntity tuple);

    @Query("SELECT * FROM ip_geo_location_table WHERE ipQuery = :ipQuery")
    Single<IPGeolocationEntity> retrieveLocation(String ipQuery);

    @Query("Delete FROM ip_geo_location_table WHERE ipQuery = :ipQuery")
    void deleteLocation(String ipQuery);

    @Query("Delete FROM ip_geo_location_table")
    void deleteAllLocation();

    @Query("SELECT * FROM ip_geo_location_table")
    LiveData<List<IPGeolocationEntity>> getAllLocation();
}
