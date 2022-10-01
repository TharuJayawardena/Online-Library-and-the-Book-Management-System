package com.example.thebookbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Books> modelList;
    RecyclerView recyclerView;
    BookOrder mAdapter;
    Button req;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        req = findViewById(R.id.req);

        // creating an arraylist

        modelList = new ArrayList<>();
        modelList.add(new Books("Harry Potter", getString(R.string.harry), R.drawable.harry));
        modelList.add(new Books("The Kite Runner", getString(R.string.kite), R.drawable.kite));
        modelList.add(new Books("Amma", getString(R.string.amma), R.drawable.amma));
        modelList.add(new Books("Gamperaliya", getString(R.string.gam), R.drawable.gam));
        modelList.add(new Books("Madolduwa", getString(R.string.madol), R.drawable.madol));
        modelList.add(new Books("Village By the sea", getString(R.string.village), R.drawable.village));


        // recyclerview
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));
        // adapter
        mAdapter = new BookOrder(this, modelList);
        recyclerView.setAdapter(mAdapter);

        req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), request_book.class);
                startActivity(intent);
            }
        });
    }


}