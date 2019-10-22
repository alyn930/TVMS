package com.example.tvms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login_enforcer extends AppCompatActivity implements View.OnClickListener {

    EditText username, password;
    ProgressDialog progressDialog;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_enforcer);

        username= this.findViewById(R.id.username);
        password = this.findViewById(R.id.password);
        btnLogin = this.findViewById(R.id.btn_loginEnfocer);
        progressDialog = new ProgressDialog(this);

        SharedPreferenceManager.getInstance(this).logout();
        if(EnforcerSharedPreferenceManager.getInstance(this).isLoggedIn()){
            startActivity(new Intent(login_enforcer.this, home_page.class));
            finish();
        }

        btnLogin.setOnClickListener(this);
    }

    public void userLogin(){
        final String user = username.getText().toString();
        final String pass = password.getText().toString();

        progressDialog.setMessage("Loggin in...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.LOGIN_ENFORCER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            progressDialog.dismiss();
                            JSONObject jsonObject = new JSONObject(response);
                            if(!jsonObject.getBoolean("error")){
                                EnforcerSharedPreferenceManager.getInstance(getApplicationContext())
                                        .enforcer_Login(
                                                jsonObject.getString("enforcer_id"),
                                                jsonObject.getString("enforcer_lname"),
                                                jsonObject.getString("enforcer_fname"),
                                                jsonObject.getString("enforcer_mi"),
                                                jsonObject.getString("enforcer_addressProv"),
                                                jsonObject.getString("enforcer_addressCity"),
                                                jsonObject.getString("enforcer_mobile"),
                                                jsonObject.getString("enforcer_tel"),
                                                jsonObject.getString("enforcer_gender"),
                                                jsonObject.getString("enforcer_email"),
                                                jsonObject.getString("enforcer_password"),
                                                jsonObject.getString("enforcer_type"),
                                                jsonObject.getString("enforcer_birthdate"),
                                                jsonObject.getString("agency_id")
                                        );
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), home_page.class));
                                finish();
                            }else {
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            }
                        }catch(JSONException e)
                        {
                            progressDialog.dismiss();
                            if(user == EnforcerSharedPreferenceManager.getInstance(getApplicationContext()).getUsername() ||
                                    pass== EnforcerSharedPreferenceManager.getInstance(getApplicationContext()).getSecurityKey() ){

//                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                                finish();
                                Toast.makeText(getApplicationContext(), "Already Signed In", Toast.LENGTH_LONG).show();
                            }
                            Toast.makeText(getApplicationContext(), "ERROR_CODE:4.2", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "ERROR:4.2", Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", user);
                params.put("password", pass);
                return params;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if(v == btnLogin){
            userLogin();
        }
    }
}
