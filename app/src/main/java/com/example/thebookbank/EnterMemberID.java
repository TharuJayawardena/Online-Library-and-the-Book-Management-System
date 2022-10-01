package com.example.thebookbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnterMemberID extends AppCompatActivity {

    DBHelper myDb;
    EditText editMemberID;
    Button button;
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_member_id);


        myDb = new DBHelper(this);

        editMemberID = (EditText) findViewById(R.id.Mid);


        button = findViewById(R.id.button9);
        button.setOnClickListener( new View.OnClickListener(){
            public void onClick (View v){

                isAllFieldsChecked = CheckAllFields();
                if (isAllFieldsChecked) {
                    next_page(v);
                }
            }
        });

    }
    public void next_page(View v) {
        Intent intent = new Intent(this, ThankYou.class);
        startActivity(intent);
    }
    private boolean CheckAllFields() {
        if (editMemberID .length() == 0) {
            editMemberID .setError("This field is required");
            return false;
        }


        // after all validation return true.
        return true;
    }
}