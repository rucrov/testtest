package com.hfad.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationBarView;

import java.util.HashMap;
import java.util.Map;



public class MainActivity extends AppCompatActivity {
    String[] symbol = new String[]{"&","=","<",">",",","`","."};
    int i=0,q=0, w=0;
private int simbolCheck(String text){
for (i=0;i<6;i++){
    if (text.contains(symbol[i])==true){q++;}
}
    if(text.indexOf(' ') != -1){q++;}

return q;
}

private  int  connect_test(int param) {
param=0;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null && networkInfo.isConnected()==true) { param++;


        }
    return param ;}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        TextView login, password,text1,email,sing_in,errorText,confirmPassword,codeRegistration;
        Button button;
        ImageView back;
        final TextView textView = (TextView) findViewById(R.id.text);
        button = findViewById(R.id.button_registration);
        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        email=findViewById(R.id.email);
        back=findViewById(R.id.back);
        errorText=findViewById(R.id.errorText);
        confirmPassword=findViewById(R.id.confirmPassword);
        codeRegistration= findViewById(R.id.codeRegistration);
        int proverka=0,proverkaCode=0;

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.black));
            getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        }


        codeRegistration.setVisibility(View.INVISIBLE);







            String id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

            RequestQueue queue = Volley.newRequestQueue(this);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                    String url = "http://co33229.tmweb.ru/";

//                    if (connect_test(w)>-1){
//                            Intent intent = new Intent(MainActivity.this, check_internet_connection.class);
//                            startActivity(intent);
//                    }
                        if (!password.getText().toString().equals(confirmPassword.getText().toString())){
                        Toast  errortext = Toast.makeText(getApplicationContext(),getResources().getString(R.string.dont_match)
                                , Toast.LENGTH_SHORT);
                        errortext.show();

                    }
                        else if (simbolCheck(password.getText().toString())>0){ Toast  errortext = Toast.makeText(getApplicationContext(),getResources().getString(R.string.symbolCheck)
                            , Toast.LENGTH_SHORT);q=0;

                        errortext.show();}
                    else if (simbolCheck(login.getText().toString())>0){ Toast  errortext = Toast.makeText(getApplicationContext(),getResources().getString(R.string.symbolCheck)
                            , Toast.LENGTH_SHORT);q=0;

                        errortext.show();}
                    else if (password.getText().toString().length()<8){
                        Toast  errortext = Toast.makeText(getApplicationContext(),getResources().getString(R.string.sizePassCheck)
                                , Toast.LENGTH_SHORT);
                        errortext.show();}

                    else if (login.getText().toString().length()<4){
                        Toast  errortext = Toast.makeText(getApplicationContext(),getResources().getString(R.string.loginSizeCheck)
                                , Toast.LENGTH_SHORT);
                        errortext.show();}

                    else if (login.getText().length()>30){
                        Toast  errortext = Toast.makeText(getApplicationContext(),getResources().getString(R.string.loginSizeLong)
                                , Toast.LENGTH_SHORT);
                        errortext.show();}
                    else if (password.getText().length()>30){
                        Toast  errortext = Toast.makeText(getApplicationContext(),getResources().getString(R.string.passSizeLong)
                                , Toast.LENGTH_SHORT);
                        errortext.show();}
                    else if (email.getText().length()>64){
                        Toast  errortext = Toast.makeText(getApplicationContext(),getResources().getString(R.string.emailSizeLong)
                                , Toast.LENGTH_SHORT);
                        errortext.show();}

                   else if (email.getText().length()==0 || login.getText().length()==0||password.getText().length()==0||confirmPassword.getText().length()==0) {
                        Toast  errortext = Toast.makeText(getApplicationContext(),getResources().getString(R.string.inputError)
                                , Toast.LENGTH_SHORT);
                        errortext.show();

                    }



                    else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Display the first 500 characters of the response string.

                                    if(response.equals("0")){
                                        codeRegistration.setVisibility(View.VISIBLE);
                                    textView.setText(getResources().getString(R.string.registration_success));
                                    }
                                        else if (response.equals("1"))
                                        { Toast busy1 = Toast.makeText(getApplicationContext(),getResources().getString(R.string.busy1)
                                                , Toast.LENGTH_SHORT);
                                            busy1.show();}
                                    else if (response.equals("2"))
                                    { Toast busy2 =Toast.makeText(getApplicationContext(),getResources().getString(R.string.busy2)
                                            , Toast.LENGTH_SHORT);
                                        busy2.show();}


                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            textView.setText("That didn't work!");
                        }
                    }) {
                        protected Map<String, String> getParams() {
                            Map<String, String> paramV = new HashMap<>();
                            paramV.put("login", login.getText().toString());
                            paramV.put("password", password.getText().toString());
                            paramV.put("ID_PHONE", id);
                            paramV.put("email", email.getText().toString());
                            paramV.put("proverka", String.valueOf(proverka));
                            paramV.put("proverkaCode", String.valueOf(proverkaCode));
                            return paramV;
                        }
                    };

//
                    queue.add(stringRequest);
                }

            }});

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,login_window.class);
                startActivity(intent);
            }
        });
        ////////переход между страницами////


    }
}