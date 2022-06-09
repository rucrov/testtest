package com.hfad.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class check_internet_connection extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_internet_connection);

        TextView testText;
        Button button;

        button = findViewById(R.id.button);
        //testText = findViewById(R.id.testText);
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.purple));
            getWindow().setStatusBarColor(getResources().getColor(R.color.purple));

        }

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null && networkInfo.isConnected()==true) {
            //testText.setText("connect");
            Intent intent = new Intent(check_internet_connection.this, login_window.class);
            startActivity(intent);
            finish();
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo!=null && networkInfo.isConnected()==true){
                    //testText.setText("connect");
                    Intent intent = new Intent(check_internet_connection.this,login_window.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    //testText.setText("no connection");
                }
            }
        });

    }
}