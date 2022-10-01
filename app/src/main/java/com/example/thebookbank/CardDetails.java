package com.example.thebookbank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class CardDetails extends AppCompatActivity {

    DBHelper myDb;
    EditText editCardNo, editExpDate, editCode, editAmount;
    Button button,button1,button2,button3,button4;
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);
        myDb = new DBHelper(this);


        editCardNo = (EditText) findViewById(R.id.cdno);
        editExpDate = (EditText) findViewById(R.id.expdate);
        editCode = (EditText) findViewById(R.id.code);
        editAmount = (EditText) findViewById(R.id.amount);
        button1 = findViewById(R.id.button3);
        button = findViewById(R.id.button8);
        button2 = findViewById(R.id.button14);
        //button3 = findViewById(R.id.button13);
        button4 = findViewById(R.id.button16);


        button1.setOnClickListener( new View.OnClickListener(){
            public void onClick (View v){

                isAllFieldsChecked = CheckAllFields();
                if (isAllFieldsChecked) {
                    next_page(v);
                }
            }
        });
        AddData();
        UpdateData();
        //viewAll();
        DeleteData();

    }
    public void next_page(View v) {
        Intent intent = new Intent(this, Verification.class);
        startActivity(intent);
    }
    public void AddData() {
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                isAllFieldsChecked = CheckAllFields();
                //editCardNo.setText("****************");
               // editCode.setText("***");
                if (isAllFieldsChecked) {
                    editCode.setText("***");
                    boolean isInserted = myDb.InsertCardData(editCardNo.getText().toString(),
                            editExpDate.getText().toString(),
                            editCode.getText().toString(), editAmount.getText().toString());

                    if (isInserted == true)
                        Toast.makeText(CardDetails.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(CardDetails.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void UpdateData() {
        button2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Alert1("");

                    }
                }
        );
    }

    public void DeleteData() {
        button4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Alert("");

                    }
                }
        );
    }
    private void Alert(String message){
        AlertDialog dlg = new AlertDialog.Builder(CardDetails.this)
                .setTitle("Delete CardDetails")
                .setMessage("Do you rearly want to delete carddetails?")

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Integer deletedRows = myDb.deleteCarddata( editCardNo.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(CardDetails.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(CardDetails.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                })
                .create();
        dlg.show();
    }
    private void Alert1(String message){
        AlertDialog dlg = new AlertDialog.Builder(CardDetails.this)
                .setTitle("Update Details")
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
                        String cardno =    editCardNo.getText().toString();
                        String expdate = editExpDate.getText().toString();
                        String code =    editCode.getText().toString();
                        String amountt = editAmount.getText().toString();


                        //myDb.Updatedata(pass,user);
                        boolean isUpdate = myDb.UpdateCardData(editCardNo.getText().toString(), editExpDate.getText().toString(),editCode.getText().toString(),editAmount.getText().toString());


                        //boolean isUpdate = myDb.Updatedata(pass,user);
                        if(isUpdate == true)
                            Toast.makeText(CardDetails.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(CardDetails.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                })
                .create();
        dlg.show();
    }




    private boolean CheckAllFields() {
        if (editCardNo .length() < 16) {
            editCardNo .setError("This field must have 16 digits ");
            return false;
        }

        if (editExpDate.length() == 0) {
            editExpDate.setError("This field is required");
            return false;
        }

        if (editCode .length() < 3) {
            editCode.setError("The 3 digits after the card number on the signature panel of your card");
            return false;
        }

        if ( editAmount.length() == 0) {
            editAmount.setError("Amount is required");
            return false;
        }

        // after all validation return true.
        return true;
    }

}