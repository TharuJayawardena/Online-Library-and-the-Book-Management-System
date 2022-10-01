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

public class PaymentHandling extends AppCompatActivity {

    DBHelper myDb;
    EditText editMemberID, editAmount;
    Button button,button1,button2;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_handling);


        myDb = new DBHelper(this);

        editMemberID = (EditText) findViewById(R.id.ID);
        editAmount = (EditText) findViewById(R.id.am);

        button = findViewById(R.id.button5);
        button1 = findViewById(R.id.button6);
        button2 = findViewById(R.id.button7);
        viewAll();
        Updatedata();
        DeleteData();
    }
    public void Updatedata() {
        button1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Alert1("");

                    }
                }
        );
    }

    public void DeleteData() {
        button2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Alert("");

                    }
                }
        );
    }

    public void viewAll() {
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Cursor res = myDb.getPaymentdata();
                                          if(res.getCount() == 0) {
                                              // show message
                                              showMessage("Error","No data found");
                                              return;
                                          }

                                          StringBuilder buffer = new StringBuilder();
                                          while (res.moveToNext()) {
                                              buffer.append("memberid  :"+ res.getString(0)+"\n");
                                              buffer.append("contactno :"+ res.getString(1)+"\n");
                                              buffer.append("email :"+ res.getString(2)+"\n");
                                              buffer.append("amount :"+ res.getString(3)+"\n\n");


                                          }

                                          // Show all data
                                          showMessage("PaymentDetails",buffer.toString());
                                      }
                                  }
        );
    }
    private void Alert1(String message){
        AlertDialog dlg = new AlertDialog.Builder(PaymentHandling.this)
                .setTitle("Update details")
                .setMessage("Do you rearly want to update details?")

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        String memberid =    editMemberID.getText().toString();
                        String amount = editAmount .getText().toString();




                        //myDb.Updatedata(pass,user);
                        boolean isUpdate = myDb.UpdateHandlingdata(editMemberID.getText().toString(), editAmount .getText().toString());


                        //boolean isUpdate = myDb.Updatedata(pass,user);
                        if(isUpdate == true)
                            Toast.makeText(PaymentHandling.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(PaymentHandling.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                })
                .create();
        dlg.show();
    }
    private void Alert(String message){
        AlertDialog dlg = new AlertDialog.Builder(PaymentHandling.this)
                .setTitle("Delete details")
                .setMessage("Do you rearly want to delete Details?")

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Integer deletedRows = myDb.deletePaymentData( editMemberID.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(PaymentHandling.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(PaymentHandling.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                })
                .create();
        dlg.show();
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}