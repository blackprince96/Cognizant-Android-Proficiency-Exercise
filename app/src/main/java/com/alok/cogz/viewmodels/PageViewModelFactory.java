package com.alok.cogz.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.alok.cogz.repository.PageRepository;

public class PageViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private PageRepository pageRepository;
    public PageViewModelFactory(PageRepository pageRepository){
        this.pageRepository = pageRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass){
        return (T)new PageViewModel(pageRepository);
    }

}