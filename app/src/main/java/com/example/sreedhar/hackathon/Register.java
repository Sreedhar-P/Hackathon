package com.example.sreedhar.hackathon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity
{
    EditText emai,passwd;
    String s1,s2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        emai=(EditText)findViewById(R.id.email);
        passwd=(EditText)findViewById(R.id.psw);
    }

    public void register(View view) {
        s1=emai.getText().toString();
        s2=passwd.getText().toString();
        DB d=new DB(Register.this);
        boolean i=d.insertData(s1,s2);
        if (i==true)
        {
            Toast.makeText(this,"Registered successfully",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Not Registered !",Toast.LENGTH_SHORT).show();
        }
    }
}
