package com.example.thebookbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddtoDelivery extends AppCompatActivity {

    EditText username,Location,ContactNumber;
    Button BtnDeliveryAdd;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addto_delivery);

        username = (EditText) findViewById(R.id.username);
        Location = (EditText) findViewById(R.id.Location);
        ContactNumber = (EditText) findViewById(R.id.ContactNumber);
        BtnDeliveryAdd = (Button) findViewById(R.id.BtnDeliveryAdd);
        DB = new DBHelper(this);

        BtnDeliveryAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String uname = username.getText().toString();
                String loc = Location.getText().toString();
                String Pnum = ContactNumber.getText().toString();


                if(uname.equals("")||loc.equals("")||Pnum.equals(""))
                    Toast.makeText(AddtoDelivery.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean isPhone = DB.isPhone(ContactNumber);
                    if (isPhone == true) {
                    Boolean insert = DB.Adddelivery(uname, loc ,Pnum);
                    if(insert==true){
                        Toast.makeText(AddtoDelivery.this, "Delivery Submitted", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),PaymentDetails.class);
                                startActivity(intent);
                    }else{
                        Toast.makeText(AddtoDelivery.this, "failed", Toast.LENGTH_SHORT).show();
                    } } else {
                        Toast.makeText(AddtoDelivery.this, "Invalid Mobile Number !", Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });

    }
}