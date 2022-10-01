package com.example.thebookbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserHHome extends AppCompatActivity {

    Button btnprof, btnLib,btnBook,btnrate,btndelivery,btnmem,btnfeedback,btnaboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_hhome);

        btnprof = (Button) findViewById(R.id.btnprof);
        btnLib = (Button) findViewById(R.id.btnLib);
        btnBook = (Button) findViewById(R.id.btnBook);
        btnrate = (Button) findViewById(R.id.btnrate);
        btndelivery = (Button) findViewById(R.id.btndelivery);
        btnmem = (Button) findViewById(R.id.btnmem);
        btnfeedback = (Button) findViewById(R.id.btnfeedback);
        btnaboutUs = (Button) findViewById(R.id.btnaboutUs);

        btnprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Profile_view.class);
                startActivity(intent);
            }
        });

        btnLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BookActivity.class);
                startActivity(intent);
            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Rating.class);
                startActivity(intent);
            }
        });

        btndelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ViewDeliveryStates.class);
                startActivity(intent);
            }
        });

        btnmem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PaymentMethod.class);
                startActivity(intent);
            }
        });

        btnfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Feedback.class);
                startActivity(intent);
            }
        });

        btnaboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AboutUs.class);
                startActivity(intent);
            }
        });
    }
}