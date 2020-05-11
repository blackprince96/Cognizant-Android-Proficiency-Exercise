package com.alok.cogz.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class PageRepositoryImplTest {

    @Mock
    PageRepositoryImpl pageRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getPageLiveData() {
        doReturn(pageRepository.getPageLiveData()).when(pageRepository).getPageLiveData();
        System.out.println(pageRepository.getPageLiveData());
    }

    @Test
    public void getDataLoadingStatusLiveData() {
        doReturn(pageRepository.getDataLoadingStatusLiveData()).when(pageRepository).getDataLoadingStatusLiveData();
        System.out.println(pageRepository.getDataLoadingStatusLiveData());
    }
}