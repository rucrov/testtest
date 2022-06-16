package com.hfad.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class login_window extends AppCompatActivity {
    private static final String SAVED_LOGIN = "saved_login";
    private static final String SAVED_PASS = "saved_pass";
    SharedPreferences saveLogin,savePassword;


    TextView sing_up,loginLogin,passwordPassword,forgot_your_password,textView;
    Button button_login;
    CheckBox checkBox;
    int proverka=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_window);
textView=findViewById(R.id.text);
        sing_up=findViewById(R.id.sing_up);
        loginLogin=findViewById(R.id.loginLogin);
        passwordPassword=findViewById(R.id.passwordPassword);
        button_login=findViewById(R.id.button_login);
        forgot_your_password=findViewById(R.id.forgot_your_password);
        checkBox=findViewById(R.id.checkBox);


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
//                                try {
//                                    JSONObject jsonResponse  = new JSONObject(response);
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                                JSONObject userInfo
                                if(response.equals("0")){
                                    textView.setText(getResources().getString(R.string.login_success));

                                    if (checkBox.isChecked()){save_login_password(response);qwert();}

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
    private void save_login_password( String qwe){

        saveLogin = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = saveLogin.edit();
        ed.putString(SAVED_LOGIN, qwe);
        ed.commit();

        savePassword = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor et = savePassword.edit();
        et.putString(SAVED_PASS, qwe);
        et.commit();



    }
    private void qwert(){
        String savedText=saveLogin.getString(SAVED_LOGIN,"");

        textView.setText(savedText);
    }
}
