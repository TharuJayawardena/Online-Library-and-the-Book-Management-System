package com.example.thebookbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity {

    List<libBook> modelList;
    RecyclerView recyclerView;
    BorrowAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        // creating an arraylist

        modelList = new ArrayList<>();
        modelList.add(new libBook("Jane eyre", getString(R.string.jane), R.drawable.jane ));
        modelList.add(new libBook("හත්පන", getString(R.string.hathpana), R.drawable.hathpana));
        modelList.add(new libBook("Houes os Secrets", getString(R.string.houseofsecrets), R.drawable.houseofsecrets));
        modelList.add(new libBook("බොද මීදුම්", getString(R.string.bondamedum), R.drawable.bondamedum));
        modelList.add(new libBook("බලන්ගොඩ මහ නාහිමි සබැදි කතා 2", getString(R.string.buddhist2), R.drawable.buddhist2));
        modelList.add(new libBook("Oliver Twist", getString(R.string.oliver), R.drawable.oliver));
        modelList.add(new libBook("අම්මා", getString(R.string.ammaa), R.drawable.amma));
        modelList.add(new libBook("කුඩ හොරා", getString(R.string.kudahora), R.drawable.kudahora));
        modelList.add(new libBook("Alexander Hamilton", getString(R.string.hamilton), R.drawable.hamilton));

        // recyclerview
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));
        // adapter
        mAdapter = new BorrowAdapter(this, modelList);
        recyclerView.setAdapter(mAdapter);




    }
}