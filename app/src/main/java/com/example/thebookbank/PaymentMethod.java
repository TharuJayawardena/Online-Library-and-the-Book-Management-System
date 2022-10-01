package com.example.thebookbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PaymentMethod extends AppCompatActivity {

    ImageButton imageButton;
    ImageButton imageButton2;
    ImageButton imageButton3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);


        imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),EnterMemberID.class);
                startActivity(i);
            }
        });
        imageButton2 = findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),CardDetails.class);
                startActivity(i);
            }
        });
        imageButton3 = findViewById(R.id.imageButton3);
        imageButton3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),EnterOrderID.class);
                startActivity(i);
            }
        });

    }
}