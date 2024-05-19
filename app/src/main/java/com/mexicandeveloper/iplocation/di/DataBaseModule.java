package com.mexicandeveloper.iplocation.di;

import android.app.Application;

import androidx.room.Room;

import com.mexicandeveloper.iplocation.db.IpLocationDB;
import com.mexicandeveloper.iplocation.db.IpLocationDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DataBaseModule {
    @Provides
    @Singleton
    public static IpLocationDB provideIpLocationDB(Application application) {
        return Room.databaseBuilder(application, IpLocationDB.class, "IP Geolocation DB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static IpLocationDao provideIpLocationDao(IpLocationDB ipLocationDB) {
        return ipLocationDB.ipLocationDao();
    }

}
