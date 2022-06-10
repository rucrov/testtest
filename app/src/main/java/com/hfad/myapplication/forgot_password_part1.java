package com.hfad.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
import java.util.concurrent.TimeUnit;

public class forgot_password_part1 extends AppCompatActivity {
int i=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fogot_password_part1);

        int proverka=2;

        TextView textForgotPassword,enterYourEmail,code,textView4;
        Button sendEmail;
        sendEmail=findViewById(R.id.sendEmail);
        textForgotPassword=findViewById(R.id.textForgotPassword);
        enterYourEmail=findViewById(R.id.enterYourEmail);
        code=findViewById(R.id.code);
        textView4=findViewById(R.id.textView4);
        textForgotPassword.setText(getResources().getString(R.string.forgot_email));
        RequestQueue queue = Volley.newRequestQueue(this);

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.black));
            getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        }

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (enterYourEmail.length()==0){ textForgotPassword.setText(getResources().getString(R.string.enter_email));
                }
                else {textForgotPassword.setText(getResources().getString(R.string.text)+" "+enterYourEmail.getText().toString()+" "+getResources().getString(R.string.text2));
                { String url = "http://co33229.tmweb.ru/";
                    i++;

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                   if (response.equals("0")){
                                       Intent intent = new Intent(forgot_password_part1.this,forgot_password_part2.class);
                                       intent.putExtra("email",enterYourEmail.getText().toString());
                                       startActivity(intent);
                                       finish();
                                }
                                else if (response.equals("1"))
                                { textView4.setText(getResources().getString(R.string.notCorrectCode));}
                                else textView4.setText(response);


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView4.setText("That didn't work!");
                    }
                }){
                    protected Map<String,String> getParams(){
                        Map<String, String> paramV = new HashMap<>();

                        paramV.put("email", enterYourEmail.getText().toString());
                        paramV.put("proverka", String.valueOf(proverka));
                        paramV.put("i", String.valueOf(i));
                        paramV.put("code", code.getText().toString());
                        i=1;
                        return paramV;

                    }
                };

//
                queue.add(stringRequest);
            }



            }}
        });




    }
}