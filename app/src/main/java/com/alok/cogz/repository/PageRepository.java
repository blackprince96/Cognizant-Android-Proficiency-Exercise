package com.alok.cogz.repository;

import androidx.lifecycle.LiveData;
import com.alok.cogz.request.responses.Page;
import com.alok.cogz.constants.LoadingStatus;
/**
 * Page Repository acts as a single source for Page Data,
 */
public interface PageRepository {
    void fetchData(boolean forceRefresh);

    LiveData<Page> getPageLiveData();

    LiveData<LoadingStatus> getDataLoadingStatusLiveData();

}
