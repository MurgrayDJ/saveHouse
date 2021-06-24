package com.murgray.savehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText emailEditText = findViewById(R.id.emailEditText);
                EditText pwEditText = findViewById(R.id.pwEditText);

                if(emailEditText.getText().toString().equals("admin@murgray.com") &&
                        pwEditText.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(),
                            "Redirecting...",Toast.LENGTH_SHORT).show();
                    Intent startIntent = new Intent(getApplicationContext(), userPage.class);
                    startActivity(startIntent);
                }else{
                    Toast.makeText(getApplicationContext(), "Incorrect Credentials",Toast.LENGTH_SHORT).show();
                    emailEditText.setBackgroundColor(Color.RED);
                    pwEditText.setBackgroundColor(Color.RED);

                }
            }
        });


    }
}
