package com.alok.cogz.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alok.cogz.constants.LoadingStatus;
import com.alok.cogz.db.DummyPageDao;
import com.alok.cogz.request.DropboxAPI;
import com.alok.cogz.request.responses.Page;
import com.alok.cogz.utils.ConnectivityInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Implementation of PageRepository, Also persist the page data locally
 */
public class PageRepositoryImpl implements PageRepository, Callback<Page> {

    private static PageRepository instance;
    private Context context;

    public synchronized static PageRepository getInstance(Context context, DropboxAPI apiService, DummyPageDao dummyPageDao) {
        if (instance == null) {
            instance = new PageRepositoryImpl(context, apiService, dummyPageDao);
        }
        return instance;
    }

    private DummyPageDao dummyPageDao;
    private DropboxAPI pageAPIService;

    private MutableLiveData<LoadingStatus> dataLoadingStatus;

    private PageRepositoryImpl(Context context, DropboxAPI apiService, DummyPageDao dummyPageDao) {
        this.context = context;
        this.pageAPIService = apiService;
        this.dummyPageDao = dummyPageDao;
        dataLoadingStatus = new MutableLiveData<>(LoadingStatus.IDLE);
    }

    /**
     * Data can be fetched from remote or local cache.. as required
     *
     * @param forceRefresh force fetch data from remote if this is true
     */
    @Override
    public void fetchData(boolean forceRefresh) {
        dataLoadingStatus.setValue(LoadingStatus.LOADING);
        if (forceRefresh || dummyPageDao.getPage().getValue() == null) {// fetch data from remote
            fetchRemoteData();
        } else {
            fetchLocalData();
        }
    }

    @Override
    public LiveData<Page> getPageLiveData() {
        return dummyPageDao.getPage();
    }

    @Override
    public LiveData<LoadingStatus> getDataLoadingStatusLiveData() {
        return dataLoadingStatus;
    }

    private void fetchRemoteData() {
        if (ConnectivityInfo.isOnline(context)) {
            pageAPIService.getPageData().enqueue(this);
        } else {
            dataLoadingStatus.setValue(LoadingStatus.OFFLINE);
        }
    }

    private void fetchLocalData() {
        // reset the same value
        Page page = dummyPageDao.getPage().getValue();
        dummyPageDao.setPage(page);
        dataLoadingStatus.setValue(LoadingStatus.FINISHED);
    }

    @Override
    public void onResponse(Call<Page> call, Response<Page> response) {
        // persist data to dummyPageDao this will also update the observer
        dummyPageDao.setPage(response.body());
        dataLoadingStatus.setValue(LoadingStatus.FINISHED);
    }

    @Override
    public void onFailure(Call<Page> call, Throwable t) {
        dataLoadingStatus.setValue(LoadingStatus.ERROR);
        Log.e("DataRepository", "onFailure error " + t.getMessage());
    }
}