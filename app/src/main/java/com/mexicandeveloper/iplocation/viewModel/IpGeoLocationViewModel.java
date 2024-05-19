package com.mexicandeveloper.iplocation.viewModel;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mexicandeveloper.iplocation.model.IPGeolocationEntity;
import com.mexicandeveloper.iplocation.repository.Repository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class IpGeoLocationViewModel extends ViewModel {
    private static final String TAG = "ViewModel";

    private final Repository repository;
    private MutableLiveData<IPGeolocationEntity> locationInfo = new MutableLiveData<>();

    private MutableLiveData<String> errorMessage = new MutableLiveData<>();

    @Inject
    public IpGeoLocationViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<IPGeolocationEntity> iPGeolocationEntity() {
        return locationInfo;
    }

    public MutableLiveData<String> errorMessage() {
        return errorMessage;
    }

    public void getIPLocation(String ip) {
        repository.getIPLocation(ip)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<IPGeolocationEntity>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull IPGeolocationEntity ipGeolocationEntity) {
                        locationInfo.setValue(ipGeolocationEntity);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        errorMessage.setValue(e.getMessage());
                    }
                });
    }
}
