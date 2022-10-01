package com.example.thebookbank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class Rating extends AppCompatActivity {

    EditText feedback;
    RatingBar ratingbar;
    Button button;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        addListenerOnButtonClick();
    }
    public void addListenerOnButtonClick(){
        ratingbar=(RatingBar)findViewById(R.id.ratingBar);
        button=(Button)findViewById(R.id.button);
        feedback=(EditText) findViewById(R.id.feedback);
        //Performing action on Button Click
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {

                //Getting the rating and displaying it on the toast
                String rating=String.valueOf(ratingbar.getRating());
                Toast.makeText(getApplicationContext(), rating, Toast.LENGTH_LONG).show();

            }

        });
    }
}