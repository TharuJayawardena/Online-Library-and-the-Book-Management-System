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

public class Feedback extends AppCompatActivity {
    DBHelper myDb;
    EditText editName, editFeedback;
    Button button,button1,button2,button3,button4;
    boolean isAllFieldsChecked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        myDb = new DBHelper(this);


        editName= (EditText) findViewById(R.id.name);
        editFeedback = (EditText) findViewById(R.id.feed);
        button = findViewById(R.id.button17);
        button1 = findViewById(R.id.button18);
        button2 = findViewById(R.id.button19);
        button3 = findViewById(R.id.button13);
        button4 = findViewById(R.id.button21);

        button2.setOnClickListener( new View.OnClickListener(){
            public void onClick (View v){

                isAllFieldsChecked = CheckAllFields();
                if (isAllFieldsChecked) {
                    next_page(v);
                }
            }
        });
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
        button2 = findViewById(R.id.button19);
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),PaymentDetails.class);
                startActivity(i);
            }
        });
    }

    public void next_page(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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

    public void AddData() {
        button.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {


                                          isAllFieldsChecked = CheckAllFields();
                                          if (isAllFieldsChecked) {
                                              boolean isInserted = myDb.insertFeedbackdata(editName.getText().toString(),
                                                      editFeedback.getText().toString());
                                              if (isInserted == true)
                                                  Toast.makeText(Feedback.this, "Data Inserted", Toast.LENGTH_LONG).show();
                                              else
                                                  Toast.makeText(Feedback.this, "Data not Inserted", Toast.LENGTH_LONG).show();

                                          }
                                         /* boolean isInserted = myDb.insertFeedbackdata(editName.getText().toString(),
                                                  editFeedback.getText().toString());
                                          if (isInserted == true)
                                              Toast.makeText(Feedback.this, "Data Inserted", Toast.LENGTH_LONG).show();
                                          else
                                              Toast.makeText(Feedback.this, "Data not Inserted", Toast.LENGTH_LONG).show();*/

                                      }
                                  }
        );
    }
    public void UpdateData() {
        button3.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Alert1("");

                    }
                }
        );
    }

    public void viewAll() {
        button1.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Cursor res = myDb.getFeedbackData();
                                           if(res.getCount() == 0) {
                                               // show message
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
        AlertDialog dlg = new AlertDialog.Builder(Feedback.this)
                .setTitle("Delete Feedbacks")
                .setMessage("Do you rearly want to delete Feedback?")

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Integer deletedRows = myDb.DeleteFeedData( editName.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(Feedback.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Feedback.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                })
                .create();
        dlg.show();
    }
    private void Alert1(String message){
        AlertDialog dlg = new AlertDialog.Builder(Feedback.this)
                .setTitle("Update Feedback")
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
                        String name =    editName.getText().toString();
                        String feedback = editFeedback.getText().toString();



                        //myDb.Updatedata(pass,user);
                        boolean isUpdate = myDb.updateFeedbackData(editName.getText().toString(), editFeedback .getText().toString());


                        //boolean isUpdate = myDb.Updatedata(pass,user);
                        if(isUpdate == true)
                            Toast.makeText(Feedback.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Feedback.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                })
                .create();
        dlg.show();
    }
    private boolean CheckAllFields() {
        if ( editName .length() == 0) {
            editName .setError("This field is required");
            return false;
        }

        if (editFeedback.length() == 0) {
            editFeedback.setError("Must contatin 10 digits");
            return false;
        }



        // after all validation return true.
        return true;
    }

}
