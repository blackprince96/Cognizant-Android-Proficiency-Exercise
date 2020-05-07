package com.alok.cogz.ui;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alok.cogz.R;
import com.alok.cogz.data.Row;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class PageAdapter extends RecyclerView.Adapter<PageAdapter.RowViewHolder> {

    List<Row> dataList;

    public PageAdapter() {
        dataList = new ArrayList<>();
    }

    public void updateData(List<Row> list) {
        this.dataList = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_items, null);
        return new RowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowViewHolder holder, int position) {
        holder.bind(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class RowViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvDescription;
        ImageView ivPoster;

        public RowViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_row_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            ivPoster = itemView.findViewById(R.id.imageView);
        }

        void bind(Row row) {
            for (int i = 0; i <= dataList.size(); i++) {
                if (!TextUtils.isEmpty(row.getTitle())) {
                    tvTitle.setText(row.getTitle());
                } else tvTitle.setText("Title...");

                if (!TextUtils.isEmpty(row.getDescription())) {
                    tvDescription.setText(row.getDescription());
                } else tvDescription.setText("description...");

                Glide.with(itemView.getContext()).load(row.getImageHref()).placeholder(R.drawable.ic_launcher_background).into(ivPoster);
            }
        }
    }
}
