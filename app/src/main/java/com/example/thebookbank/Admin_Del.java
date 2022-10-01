package com.example.thebookbank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Admin_Del extends AppCompatActivity {
    DBHelper myDb;
    Button btndelete;
    EditText profUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_del);

        myDb = new DBHelper(this);
        profUsername = (EditText) findViewById(R.id.profUsername);
        btndelete = findViewById(R.id.btndelete);

        DeleteData();
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
        AlertDialog dlg = new AlertDialog.Builder(Admin_Del.this)
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
                        Integer deletedRows = myDb.deleteadminData(profUsername.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(Admin_Del.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Admin_Del.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                })
                .create();
        dlg.show();
    }
}