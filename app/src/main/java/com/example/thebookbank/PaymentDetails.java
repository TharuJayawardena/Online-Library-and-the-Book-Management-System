package com.example.thebookbank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;





public class PaymentDetails extends AppCompatActivity {
    DBHelper myDb;
    EditText editMemberID, editContactNo, editMail, editAmount;
    Button button,button1,button2,button3,button4;
    boolean isAllFieldsChecked = false;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        myDb = new DBHelper(this);


        editMemberID = (EditText) findViewById(R.id.memberID);
        editContactNo = (EditText) findViewById(R.id.contactno);
        editMail = (EditText) findViewById(R.id.E_mail);
        editAmount = (EditText) findViewById(R.id.Amountt);
        button = findViewById(R.id.Pay);
        button1 = findViewById(R.id.button2);
        button2 = findViewById(R.id.button11);
        button3 = findViewById(R.id.button15);
        button4 = findViewById(R.id.button12);

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
        viewAll();
        DeleteData();
    }
    public void next_page(View v) {
        Intent intent = new Intent(this, PaymentMethod.class);
        startActivity(intent);
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
    boolean isemail(EditText text) { //check valid Email
        CharSequence editMail = text.getText().toString();
        return (!TextUtils.isEmpty(editMail) && Patterns.EMAIL_ADDRESS.matcher(editMail).matches());
    }


    public void AddData() {
        button.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {
                                          if(isemail(editMail) == false) {
                                              editMail.setError("enter valid email");


                                          }
                                          else {
                                              isAllFieldsChecked = CheckAllFields();

                                              if (isAllFieldsChecked) {

                                                  boolean isInserted = myDb.insertPaymentData(editMemberID.getText().toString(),
                                                          editContactNo.getText().toString(),
                                                          editMail.getText().toString(), editAmount.getText().toString());


                                                  if (isInserted == true)
                                                      Toast.makeText(PaymentDetails.this, "Data Inserted", Toast.LENGTH_LONG).show();
                                                  else
                                                      Toast.makeText(PaymentDetails.this, "Data not Inserted", Toast.LENGTH_LONG).show();


                                              }
                                          }


                                      }
                                  }
        );
    }

    public void viewAll() {
        button3.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Cursor res = myDb.getFeedbackData();
                                           if(res.getCount() == 0) {
                                               showMessage("Error","No data found");
                                               return;
                                           }

                                           StringBuilder buffer = new StringBuilder();
                                           while (res.moveToNext()) {
                                               buffer.append("name  :"+ res.getString(0)+"\n");
                                               buffer.append("feedback :"+ res.getString(1)+"\n");


                                           }

                                           // Show all data
                                           showMessage("FeedBacks",buffer.toString());
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
    private void Alert(String message){
        AlertDialog dlg = new AlertDialog.Builder(PaymentDetails.this)
                .setTitle("Delete Details")
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
                        Integer deletedRows = myDb.deletePaymentData( editMemberID.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(PaymentDetails.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(PaymentDetails.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                })
                .create();
        dlg.show();
    }
    private void Alert1(String message){
        AlertDialog dlg = new AlertDialog.Builder(PaymentDetails.this)
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
                        String memberid =    editMemberID.getText().toString();
                        String contactno = editContactNo.getText().toString();
                        String mail =    editMail.getText().toString();
                        String amount = editAmount.getText().toString();



                        boolean isUpdate = myDb.updatePaymentData(editMemberID.getText().toString(), editContactNo .getText().toString(),editMail.getText().toString(),editAmount .getText().toString());


                        if(isUpdate == true)
                            Toast.makeText(PaymentDetails.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(PaymentDetails.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                })
                .create();
        dlg.show();
    }


    public void DeleteData() {
        button4.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             Alert("");

                                         }
                                     }
        );
    }


    private boolean CheckAllFields() {
        if ( editMemberID .length() == 0) {
            editMemberID .setError("This field is required");
            return false;
        }

        if (editContactNo.length() < 10) {
            editContactNo.setError("Must contatin 10 digits");
            return false;
        }

        if (editMail.length() == 0 ) {
            editMail.setError("Must contain @ sign and the valid domain name");
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