package com.example.thebookbank;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class request_book extends AppCompatActivity {

    EditText bName,author,username;
    Button btnSubmit,btnUpdate;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_book);

        username = (EditText) findViewById(R.id.username);
        bName = (EditText) findViewById(R.id.bName);
        author = (EditText) findViewById(R.id.author);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        DB = new DBHelper(this);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String book = bName.getText().toString();
                String auth = author.getText().toString();

                if (user.equals("") || book.equals("") || auth.equals(""))
                    Toast.makeText(request_book.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean insert = DB.insertData(user, book ,auth);
                    Toast.makeText(request_book.this, "Request submitted", Toast.LENGTH_SHORT).show();
                }

            }

        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {


                                             String user = username.getText().toString();
                                             String bookName = bName.getText().toString();
                                             String Author = author.getText().toString();

                                             //myDb.Updatedata(pass,user);
                                             boolean isUpdate = DB.Updatedata( username.getText().toString(),bName.getText().toString(),author.getText().toString());


                                             //boolean isUpdate = myDb.Updatedata(pass,user);
                                             if(isUpdate == true)
                                                 Toast.makeText(request_book.this,"Data Update",Toast.LENGTH_LONG).show();
                                             else
                                                 Toast.makeText(request_book.this,"Data not Updated",Toast.LENGTH_LONG).show();
                                         }
                                     }
        );
    }
}

