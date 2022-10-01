package com.example.thebookbank;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class adminReg extends AppCompatActivity {


    EditText Uname, Pword, rePword, email, phone;

    Button btnreg, btnlog;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_reg);



        Uname = (EditText) findViewById(R.id.Uname);
        Pword = (EditText) findViewById(R.id.Pword);
        rePword = (EditText) findViewById(R.id.rePword);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        btnreg = (Button) findViewById(R.id.btnreg);
        btnlog = (Button) findViewById(R.id.btnlog);
        DB = new DBHelper(this);




        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String un = Uname.getText().toString();
                String ps = Pword.getText().toString();
                String reps = rePword.getText().toString();
                String emai = email.getText().toString();
                String pnum = phone.getText().toString();

                if(un.equals("")||ps.equals("")||reps.equals("")||emai.equals("")||pnum.equals(""))
                    Toast.makeText(adminReg.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean isEmail = DB.isEmail(email);
                    if(isEmail== true) {
                        Boolean isPhone = DB.isPhone(phone);
                        if (isPhone == true) {

                            if (ps.equals(reps)) {
                                Boolean checkuser = DB.checkadminusername(un);
                                if (checkuser == false) {
                                    Boolean insert = DB.insertadminData(un, ps, emai, pnum);
                                    //emailValidator(email);
                                    if (insert == true) {
                                        Toast.makeText(adminReg.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), adminLog.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(adminReg.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(adminReg.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(adminReg.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(adminReg.this, "Enter valid phone number !", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(adminReg.this, "Enter valid Email address !", Toast.LENGTH_SHORT).show();
                    }

                } }
        });

        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), adminLog.class);
                startActivity(intent);
            }
        });


    }
}