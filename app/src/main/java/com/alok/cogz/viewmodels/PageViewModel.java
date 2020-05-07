package com.alok.cogz.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alok.cogz.repository.PageRepository;
import com.alok.cogz.request.responses.Page;
import com.alok.cogz.constants.LoadingStatus;

public class PageViewModel extends ViewModel {

    private PageRepository pageRepository;

    private MutableLiveData<Page> pageLiveData = new MutableLiveData<>();

    PageViewModel(PageRepository repository) {
        this.pageRepository = repository;
    }

    public LiveData<Page> getPageLiveData() {
        return pageRepository.getPageLiveData();
    }

    public void fetchData() {
        pageRepository.fetchData(false);
    }

    public LiveData<LoadingStatus> getDataLoadingStatusLiveData() {
        return pageRepository.getDataLoadingStatusLiveData();
    }

    public void refresh() {
        pageRepository.fetchData(true);
    }
}
