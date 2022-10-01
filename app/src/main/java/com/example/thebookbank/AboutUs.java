package com.example.thebookbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AboutUs extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        button = findViewById(R.id.button20);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Feedback.class);
                startActivity(i);
            }
        });
    }

}