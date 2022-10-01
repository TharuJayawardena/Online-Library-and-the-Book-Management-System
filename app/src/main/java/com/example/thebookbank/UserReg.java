package com.example.thebookbank;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class UserReg extends AppCompatActivity {

    EditText username, password, repassword;
    ImageButton plus, minus;
    TextView quantity,Regfee;
    int noOfFunds;
    Button signup, signin;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reg);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        plus = (ImageButton) findViewById(R.id.plus);
        minus = (ImageButton) findViewById(R.id.minus);
        quantity = (TextView) findViewById(R.id.quantity);
        Regfee = (TextView) findViewById(R.id.Regfee);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //password.setText("******");
                //repassword.setText("******");

                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                String qty = quantity.getText().toString();
                String regfee = Regfee.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(UserReg.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertUserData(user, pass ,qty , regfee);
                            if(insert==true){
                                Toast.makeText(UserReg.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(UserReg.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(UserReg.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(UserReg.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }

                } }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        plus.setOnClickListener(v -> {

            int basefee = 100;
            noOfFunds++;
            displayQuantity();
            int monthfee = basefee * noOfFunds;
            String setnewregfee = String.valueOf(monthfee);
            Regfee.setText(setnewregfee);
        });

        minus.setOnClickListener(v -> {

            int basefee = 100;
            // because we dont want the quantity go less than 0
            if (noOfFunds == 0) {
                Toast.makeText(UserReg.this, "Cant decrease quantity < 0", Toast.LENGTH_SHORT).show();
            } else {
                noOfFunds--;
                displayQuantity();
                int monthfee = basefee * noOfFunds;
                String setnewregfee = String.valueOf(monthfee);
                Regfee.setText(setnewregfee);
            }
        });




    }
    private void displayQuantity(){
        quantity.setText(String.valueOf(noOfFunds));
    }



}
