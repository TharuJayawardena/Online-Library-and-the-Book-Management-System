package com.example.thebookbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class adminLog extends AppCompatActivity {

    EditText Auname, Apword;
    Button Alog,Areg;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_log);


        Auname = (EditText) findViewById(R.id.Auname);
        Apword = (EditText) findViewById(R.id.Apword);
        Alog = (Button) findViewById(R.id.Alog);
        Areg = (Button) findViewById(R.id.Areg);
        DB = new DBHelper(this);

        Alog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uname = Auname.getText().toString();
                String apass = Apword.getText().toString();

                if(uname.equals("")||apass.equals(""))
                    Toast.makeText(adminLog.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkadminusernamepassword(uname, apass);
                    if(checkuserpass==true){
                        Toast.makeText(adminLog.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), AdminHome.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(adminLog.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        Areg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), adminReg.class);
                startActivity(intent);
            }
        });
    }


}