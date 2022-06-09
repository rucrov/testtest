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

import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class forgot_password_part2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_part2);

        TextView enterPassPart2,confirmPassPart2,helper;
        Button changeButton;
        int proverka=3 ,anotherProverka=1;
        enterPassPart2=findViewById(R.id.enterPassPart2);
        confirmPassPart2=findViewById(R.id.confirmPassPart2);
        changeButton=findViewById(R.id.changeButton);
        helper=findViewById(R.id.helper);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.black));
            getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        }
        RequestQueue queue = Volley.newRequestQueue(this);
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "http://co33229.tmweb.ru/";

                if ( enterPassPart2.getText().length()==0||confirmPassPart2.getText().length()==0) {
                    helper.setText(getResources().getString(R.string.inputError));
                }
                else if (!enterPassPart2.getText().toString().equals(confirmPassPart2.getText().toString())){
                    helper.setText((getResources().getString(R.string.dont_match)));
                }
                else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Display the first 500 characters of the response string.

                                    if(response.equals("0")) {
                                        helper.setText(getResources().getString(R.string.password_was_change));

                                        Intent intent = new Intent(forgot_password_part2.this, login_window.class);
                                        startActivity(intent);
                                        finish();

                                    }

                                    else if (response.equals("1")) {
                                        helper.setText(getResources().getString(R.string.error));
                                    }
                                    else helper.setText(response);



                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            helper.setText("That didn't work!");
                        }
                    }) {
                        protected Map<String, String> getParams() {
                            Map<String, String> paramV = new HashMap<>();
                            paramV.put("password", enterPassPart2.getText().toString());
                            paramV.put("proverka", String.valueOf(proverka));
                            paramV.put("anotherProverka", String.valueOf(anotherProverka));
                            paramV.put( "email", email);
                            return paramV;
                        }
                    };

//
                    queue.add(stringRequest);
                }

            }});


    }
}