package com.vieyra18022490.categorize_vieyra_18022490_task2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    EditText uname, upass;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uname = (EditText) findViewById(R.id.et_username);
        upass = (EditText) findViewById(R.id.et_password);
        btn = (Button) findViewById(R.id.bt_submit);


    }

    public void movepage(View v) { //method

        String stuname = uname.getText().toString(); //storing the varibles
        String stpword = upass.getText().toString();

        //use if statement to compare the strings the user has entered

        if (stuname.equals("James") && stpword.equals("Password1")) {
            Intent in = new Intent(Login.this, MainActivity.class);
            startActivity(in);
        } else if (stuname.equals("") || stpword.equals("")) {
            Toast.makeText(getBaseContext(), "Enter name and password", Toast.LENGTH_SHORT).show();
            //Intent in = new Intent(Login.this, MainActivity.class);
            //startActivity(in);
        } else {
            Toast.makeText(getBaseContext(), "Wrong name and password", Toast.LENGTH_SHORT).show();

        }
    }
}