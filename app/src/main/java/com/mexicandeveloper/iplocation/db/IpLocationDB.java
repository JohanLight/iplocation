package com.mexicandeveloper.iplocation.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mexicandeveloper.iplocation.model.IPGeolocationEntity;

@Database(entities = {IPGeolocationEntity.class}, version = 1, exportSchema = false)
public abstract class IpLocationDB extends RoomDatabase {
    public abstract IpLocationDao ipLocationDao();
}
