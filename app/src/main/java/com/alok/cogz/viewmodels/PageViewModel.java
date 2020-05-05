package com.alok.cogz.viewmodels;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.alok.cogz.request.ServiceGenerator;
import com.alok.cogz.request.responses.Page;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageViewModel extends ViewModel implements Callback<Page> {

    private MutableLiveData<Page> pageLiveData = new MutableLiveData<>();

    public void setPageDataObserver(LifecycleOwner owner, Observer<Page> observer) {
        pageLiveData.observe(owner, observer);
    }

    public void fetchRemoteData() {
        ServiceGenerator.getInstance().getDropboxApi().getPageData().enqueue(this);
    }

    @Override
    public void onResponse(Call<Page> call, Response<Page> response) {

        pageLiveData.setValue(response.body());
    }

    @Override
    public void onFailure(Call<Page> call, Throwable t) {
        Log.i("ViewModel", "onFailure error " + t.getMessage());
    }
}
