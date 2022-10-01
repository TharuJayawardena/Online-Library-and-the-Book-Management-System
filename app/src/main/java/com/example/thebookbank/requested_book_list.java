package com.example.thebookbank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class requested_book_list extends AppCompatActivity {
    DBHelper myDb;

    Button btnview,btndelete;
    EditText username;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requested_book_list);
        myDb = new DBHelper(this);
        btnview = findViewById(R.id.btnview);
        btndelete = findViewById(R.id.btndelete);
        username = findViewById(R.id.username);

        viewAll();
        DeleteData();

    }

    public void viewAll() {
        btnview.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Cursor res = myDb.getdata();
                                           if(res.getCount() == 0) {
                                               // show message
                                               showMessage("Error","No data found");
                                               return;
                                           }

                                           StringBuilder buffer = new StringBuilder();
                                           while (res.moveToNext()) {
                                               buffer.append("Username  :"+ res.getString(0)+"\n");
                                               buffer.append("Bookname :"+ res.getString(1)+"\n");
                                               buffer.append("Author :"+ res.getString(2)+"\n");
                                               buffer.append("\n");
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

    public void DeleteData() {
        btndelete.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {

                                             Integer deletedRows = myDb.deleteData(username.getText().toString());
                                             if(deletedRows > 0)
                                                 Toast.makeText(requested_book_list.this,"Data Deleted",Toast.LENGTH_LONG).show();
                                             else
                                                 Toast.makeText(requested_book_list.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                                         }
                                     }
        );
    }
}