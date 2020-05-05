package com.alok.cogz.request.responses;

import com.alok.cogz.data.Row;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Page {
    @SerializedName("rows")
    private List<Row> rows;
    @SerializedName("title")
    private String title;

    public List<Row> getRows() {
        return rows;
    }

    public String getTitle() {
        return title;
    }
}
