package com.example.thebookbank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.thebookbank.DBHelper;

public class Profile_view extends AppCompatActivity {
    DBHelper myDb;
    EditText profUsername, profPassword;
    Button btnview,btnupdate,btndelete;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);


        myDb = new DBHelper(this);

        profUsername = (EditText) findViewById(R.id.profUsername);
        profPassword = (EditText) findViewById(R.id.profPassword);

        // btnview = findViewById(R.id.btnview);
        btnupdate = findViewById(R.id.btnupdate);
        btndelete = findViewById(R.id.btndelete);

        //viewAll();
        Updatedata();
        DeleteData();
    }
    public void Updatedata() {

        btnupdate.setOnClickListener(new View.OnClickListener() {

                                         @Override
                                         public void onClick(View v) {
                                            ualert("");


                                         }
                                     }
        );
    }

    public void DeleteData() {
        btndelete.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                            alert("");

                                         }
                                     }
        );
    }

    private void alert(String message){
        AlertDialog dlg = new AlertDialog.Builder(Profile_view.this)
                .setTitle("Delete Profile")
                .setMessage("Are you sure?")

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Integer deletedRows = myDb.deleteUserData(profUsername.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(Profile_view.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Profile_view.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                })
                .create();
        dlg.show();
    }

    private void ualert(String message){
        AlertDialog dlg = new AlertDialog.Builder(Profile_view.this)
                .setTitle("Update Profile")
                .setMessage("Are you sure?")

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        String user = profUsername.getText().toString();
                        String pass = profPassword.getText().toString();

                        //myDb.Updatedata(pass,user);
                        boolean isUpdate = myDb.UpdateUserdata( profUsername.getText().toString(),profPassword .getText().toString());


                        //boolean isUpdate = myDb.Updatedata(pass,user);
                        if(isUpdate == true)
                            Toast.makeText(Profile_view.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Profile_view.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                })
                .create();
        dlg.show();
    }

}