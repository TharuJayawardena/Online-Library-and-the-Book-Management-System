package com.example.thebookbank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ViewDeliveryStates extends AppCompatActivity {

    Button viewStates;
    DBHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_delivery_states);


        myDb = new DBHelper(this);



        viewStates = findViewById(R.id.viewStates);



        viewDelivery();
    }

    public void viewDelivery() {
        viewStates.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              Cursor res = myDb.viewDelivery();
                                              if(res.getCount() == 0) {
                                                  delivery("Sorry","No states found");
                                                  return;
                                              }

                                              StringBuilder buffer = new StringBuilder();
                                              while (res.moveToNext()) {
                                                  buffer.append("username  :"+ res.getString(0)+"\n");
                                                  buffer.append("States :"+ res.getString(1)+"\n");
                                                  buffer.append("\n");
                                              }
                                              delivery("Data",buffer.toString());
                                          }
                                      }
        );
    }
    public void delivery(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}