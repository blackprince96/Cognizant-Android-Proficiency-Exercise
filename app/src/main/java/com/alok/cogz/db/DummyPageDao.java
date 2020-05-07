package com.alok.cogz.db;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alok.cogz.request.responses.Page;

public class DummyPageDao {
    private MutableLiveData<Page> pageMutableLiveData;

    DummyPageDao() {
        pageMutableLiveData = new MutableLiveData<>();
    }

    public void setPage(Page page) {
        pageMutableLiveData.setValue(page);
    }

    public LiveData<Page> getPage() {
        return pageMutableLiveData;
    }
}
