package com.alok.cogz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.alok.cogz.request.responses.Page;
import com.alok.cogz.constants.LoadingStatus;
import com.alok.cogz.ui.PageAdapter;
import com.alok.cogz.utils.InjectorUtil;
import com.alok.cogz.viewmodels.PageViewModel;
import com.alok.cogz.viewmodels.PageViewModelFactory;

public class MainActivity extends AppCompatActivity {

    PageViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init ViewModel
        PageViewModelFactory factory = InjectorUtil.getInstance(this).providePageViewModelFactory();
        viewModel = new ViewModelProvider(this, factory).get(PageViewModel.class);
        initUi();
    }

    private void initUi() {
        final View progressBar = findViewById(R.id.progress_circular);
        getSupportActionBar().setTitle(R.string.title);

        //Setup RecyclerView
        RecyclerView recyclerView = findViewById(R.id.list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final PageAdapter adapter = new PageAdapter();
        recyclerView.setAdapter(adapter);

        // Swipe to refresh
        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipe_to_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.refresh();
            }
        });

        // Observe data loading status
        viewModel.getDataLoadingStatusLiveData().observe(this, new Observer<LoadingStatus>() {
            @Override
            public void onChanged(LoadingStatus apiStatus) {
                swipeRefreshLayout.setRefreshing(false);
                progressBar.setVisibility(apiStatus == LoadingStatus.LOADING ? View.VISIBLE : View.INVISIBLE);

                if (apiStatus == LoadingStatus.OFFLINE) {
                    Toast.makeText(MainActivity.this, R.string.msg_offline, Toast.LENGTH_SHORT).show();
                } else if (apiStatus == LoadingStatus.ERROR) {
                    Toast.makeText(MainActivity.this, R.string.msg_error, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Observe Page Data
        viewModel.getPageLiveData().observe(this, new Observer<Page>() {
            @Override
            public void onChanged(Page page) {
                adapter.updateData(page.getRows());
                if (!TextUtils.isEmpty(page.getTitle())) {
                    getSupportActionBar().setTitle(page.getTitle());
                } else {
                    getSupportActionBar().setTitle(R.string.title);
                }
            }
        });

        viewModel.fetchData();


    }
}
