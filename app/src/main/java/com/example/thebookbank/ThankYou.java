package com.example.thebookbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThankYou extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), UserHHome.class);
                startActivity(i);
            }
        });
    }
}
       // button = findViewById(R.id.button);
       // button.setOnClickListener( new View.OnClickListener() {


           // Intent intent = new Intent(this, UserHHome.class);

           // startActivity(intent);
       // }








