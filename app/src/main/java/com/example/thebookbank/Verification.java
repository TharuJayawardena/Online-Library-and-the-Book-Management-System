package com.example.thebookbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Verification extends AppCompatActivity {
    DBHelper myDb;
    EditText editVerifivationCode;
    Button button;
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);



        myDb = new DBHelper(this);

        editVerifivationCode = (EditText) findViewById(R.id.text3);


        button = findViewById(R.id.button4);
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
        editVerifivationCode.setText("******");
        Intent intent = new Intent(this, ThankYou.class);
        startActivity(intent);
    }
    private boolean CheckAllFields() {
        if (editVerifivationCode .length() < 6) {
            editVerifivationCode .setError("Code must have six digits");
            return false;
        }


        // after all validation return true.
        return true;
    }
}