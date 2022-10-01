package com.example.thebookbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

public class libBook extends AppCompatActivity {
    String bName;
    String bDetail;
    int bPhoto;

    public libBook(String bName, String bDetail, int bPhoto) {
        this.bName = bName;
        this.bDetail = bDetail;
        this.bPhoto = bPhoto;
    }

    public String getbName() {
        return bName;
    }

    public String getbDetail() {
        return bDetail;
    }

    public int getbPhoto() {
        return bPhoto;
    }
}