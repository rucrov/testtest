package com.hfad.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class login_window extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_window);
        final TextView textView = (TextView) findViewById(R.id.text);
        TextView sing_up,loginLogin,passwordPassword,forgot_your_password;
        Button button_login;
        sing_up=findViewById(R.id.sing_up);
        loginLogin=findViewById(R.id.loginLogin);
        passwordPassword=findViewById(R.id.passwordPassword);
        button_login=findViewById(R.id.button_login);
        forgot_your_password=findViewById(R.id.forgot_your_password);
        int proverka=1;
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.black));
            getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        }
        sing_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login_window.this,MainActivity.class);
                startActivity(intent);


            }
        });

        String id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        RequestQueue queue = Volley.newRequestQueue(this);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "http://co33229.tmweb.ru/";


                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("0")){
                                    textView.setText(getResources().getString(R.string.login_success));
                                }
                                else if (response.equals("1"))
                                { textView.setText(getResources().getString(R.string.login_error));}

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                         textView.setText("That didn't work!");
                    }
                }){
                    protected Map<String,String> getParams(){
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("login", loginLogin.getText().toString());
                        paramV.put("password", passwordPassword.getText().toString());
                        paramV.put( "ID_PHONE", id);
                        paramV.put("proverka", String.valueOf(proverka));
                        return paramV;
                    }
                };

//
                queue.add(stringRequest);
            }
        });

        forgot_your_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login_window.this, forgot_password_part1.class);
                startActivity(intent);
            }
        });


    }
}