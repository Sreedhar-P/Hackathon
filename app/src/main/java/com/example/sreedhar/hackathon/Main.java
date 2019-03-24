package com.example.sreedhar.hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends AppCompatActivity
{
    EditText email,password;
    TextView t;
    String s1,s2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        email=(EditText)findViewById(R.id.emailL);
        password=(EditText)findViewById(R.id.pswL);
        t=(TextView) findViewById(R.id.forget);
    }

    public void login(View view) {
        s1=email.getText().toString();
        s2=password.getText().toString();
        DB a=new DB(this);
        boolean q=a.check(s1,s2);
        if (q==true)
        {
            Toast.makeText(this,"Success!",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,MapsActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,"Not!",Toast.LENGTH_SHORT).show();
        }
    }

    public void reg(View view) {
        startActivity(new Intent(this,Register.class));
    }

    public void forget(View view) {
        s1=""+email.getText().toString();
        if (s1=="")
        {
            Toast.makeText(this,"Please enter email!!",Toast.LENGTH_SHORT).show();
        }
        else{
        DB a=new DB(this);
        String q=a.get(s1);
        if (q!=null)
        {
            t.setText(q);
        }}
    }
}
