package com.alok.cogz.utils;

import android.content.Context;

import com.alok.cogz.db.DummyPageDao;
import com.alok.cogz.db.DummyPageDatabase;
import com.alok.cogz.repository.PageRepository;
import com.alok.cogz.request.DropboxAPI;
import com.alok.cogz.request.ServiceGenerator;
import com.alok.cogz.viewmodels.PageViewModelFactory;

/**
 * Manage all app level dependencies in this class
 */
public class InjectorUtil {
    private static InjectorUtil instance;
    private Context context;

    public synchronized static InjectorUtil getInstance(Context context) {
        if (instance == null) {
            instance = new InjectorUtil(context);
        }
        return instance;
    }

    private InjectorUtil(Context context) {
        this.context = context.getApplicationContext();
    }

    public PageViewModelFactory providePageViewModelFactory() {
        DropboxAPI apiService = ServiceGenerator.getInstance().getDropboxApi();
        DummyPageDao pageDao = DummyPageDatabase.getInstance().getDummyPageDao();
        return new PageViewModelFactory(PageRepository.getInstance(context, apiService, pageDao));
    }
}

