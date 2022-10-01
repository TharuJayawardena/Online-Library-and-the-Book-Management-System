package com.example.thebookbank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeliveryStates extends AppCompatActivity {

    EditText username,StatesAdd;
    Button sub,update,delete,Viewlist;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_states);

        username = (EditText) findViewById(R.id.username);
        StatesAdd = (EditText) findViewById(R.id.StatesAdd);
        sub = (Button) findViewById(R.id.sub);
        update = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);
        Viewlist = (Button) findViewById(R.id.Viewlist);
        DB = new DBHelper(this);

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String uname = username.getText().toString();
                String sta = StatesAdd.getText().toString();


                if(uname.equals("")||sta.equals(""))
                    Toast.makeText(DeliveryStates.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean insert = DB.AddStates(uname, sta);
                    if(insert==true){
                        Toast.makeText(DeliveryStates.this, "States Submitted", Toast.LENGTH_SHORT).show();
                                /*Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);*/
                    }else{
                        Toast.makeText(DeliveryStates.this, "States failed", Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });
        ViewList();
        DeleteStates();
        StatesUpdate();

    }

    public void StatesUpdate() {
        update.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Alert2("");

                                      }
                                  }
        );
    }
    public void DeleteStates() {
        delete.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Alert3("");

                                      }
                                  }
        );
    }

    public void ViewList() {
        Viewlist.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Cursor res = DB.ViewDeliveryList();
                                            if(res.getCount() == 0) {
                                                // show message
                                                delivery("Sorry","No states found");
                                                return;
                                            }
                                            StringBuilder buffer = new StringBuilder();
                                            while (res.moveToNext()) {
                                                buffer.append("username  :"+ res.getString(0)+"\n");
                                                buffer.append("Location :"+ res.getString(1)+"\n");
                                                buffer.append("ContactNumber :"+ res.getString(2)+"\n");
                                                buffer.append("\n");
                                            }
                                            delivery("Data",buffer.toString());
                                        }
                                    }
        );
    }
    private void Alert2(String message){
        AlertDialog dlg = new AlertDialog.Builder(DeliveryStates.this)
                .setTitle("Update details")
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
                        String user =   username.getText().toString();
                        String stateadd = StatesAdd .getText().toString();




                        //myDb.Updatedata(pass,user);
                        boolean isUpdate = DB.StatesUpdate(username.getText().toString(),StatesAdd .getText().toString());


                        //boolean isUpdate = myDb.Updatedata(pass,user);
                        if(isUpdate == true)
                            Toast.makeText(DeliveryStates.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(DeliveryStates.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                })
                .create();
        dlg.show();
    }
    private void Alert3(String message){
        AlertDialog dlg = new AlertDialog.Builder(DeliveryStates.this)
                .setTitle("Delete details")
                .setMessage("Do yoy sure?")

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Integer deletedRows = DB.DeleteStates( username.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(DeliveryStates.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(DeliveryStates.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                })
                .create();
        dlg.show();
    }
    public void delivery(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }



}