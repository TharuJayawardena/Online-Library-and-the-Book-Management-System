package com.example.thebookbank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminHome extends AppCompatActivity {
    DBHelper myDb;
    EditText profUsername, profPassword;
    Button btnview,btnupdate,btndelete,btnadminView,btnReqBooks,btnRemove, btnPayment,btndilivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        myDb = new DBHelper(this);

        profUsername = (EditText) findViewById(R.id.profUsername);
        profPassword = (EditText) findViewById(R.id.profPassword);

        btnview = findViewById(R.id.btnview);
        btnupdate = findViewById(R.id.btnupdate);
        btndelete = findViewById(R.id.btndelete);
        btnadminView = findViewById(R.id.btnadminView);
        btnReqBooks = findViewById(R.id.btnReqBooks);
        btnRemove = findViewById(R.id.btnRemove);
        btnPayment = findViewById(R.id.btnpayment);
        btndilivery = findViewById(R.id.btndilivery);



        viewAll();
        btnadminView();

        btnReqBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), requested_book_list.class);
                startActivity(intent);
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Admin_Del.class);
                startActivity(intent);
            }
        });
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PaymentHandling.class);
                startActivity(intent);
            }
        });
        btndilivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DeliveryStates.class);
                startActivity(intent);
            }
        });
    }

    public void viewAll() {
        btnview.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Cursor res = myDb.getUserdata();
                                           if(res.getCount() == 0) {
                                               // show message
                                               showMessage("Error","No data found");
                                               return;
                                           }

                                           StringBuilder buffer = new StringBuilder();
                                           while (res.moveToNext()) {
                                               buffer.append("username  :"+ res.getString(0)+"\n");
                                               buffer.append("password :"+ res.getString(1)+"\n");
                                               buffer.append("quantity :"+ res.getString(2)+"\n");
                                               buffer.append("Regfee :"+ res.getString(3)+"\n\n");


                                           }

                                           // Show all data
                                           showMessage("Data",buffer.toString());
                                       }
                                   }
        );
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void btnadminView() {
        btnadminView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Cursor res = myDb.getadmindata();
                                                if (res.getCount() == 0) {
                                                    // show message
                                                    showMessage("Error", "No data found");
                                                    return;
                                                }
                                                StringBuilder buffer = new StringBuilder();
                                                while (res.moveToNext()) {
                                                    buffer.append("Uname  :" + res.getString(0) + "\n");
                                                    buffer.append("Pword :" + res.getString(1) + "\n");
                                                    buffer.append("email :" + res.getString(2) + "\n");
                                                    buffer.append("phone :" + res.getString(3) + "\n\n");
                                                }

                                                // Show all data
                                                showMessage("Data", buffer.toString());
                                            }
                                        }
        );


    }

}