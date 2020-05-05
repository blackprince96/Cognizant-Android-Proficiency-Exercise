package com.alok.cogz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.alok.cogz.request.responses.Page;
import com.alok.cogz.ui.PageAdapter;
import com.alok.cogz.viewmodels.PageViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final PageAdapter adapter = new PageAdapter();
        recyclerView.setAdapter(adapter);
        PageViewModel viewModel = new ViewModelProvider(this).get(PageViewModel.class);

        viewModel.setPageDataObserver(this, new Observer<Page>() {
            @Override
            public void onChanged(Page page) {
                adapter.updateData(page.getRows());
                getSupportActionBar().setTitle(page.getTitle());
            }
        });

        viewModel.fetchRemoteData();
    }
}
