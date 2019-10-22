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

public class login_driver extends AppCompatActivity implements View.OnClickListener {

    EditText username, password;
    Button loginDriver;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_driver);
        username = this.findViewById(R.id.username);
        password = this.findViewById(R.id.password);
        loginDriver = this.findViewById(R.id.btn_loginDriver);
        progressDialog = new ProgressDialog(this);
        loginDriver.setOnClickListener(this);
        EnforcerSharedPreferenceManager.getInstance(this).logout();
        if(SharedPreferenceManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, home_page.class));
            return;
        }

    }

    public void userLogin(){
        final String user = username.getText().toString();
        final String pass = password.getText().toString();
        progressDialog.setMessage("Logging In....");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.LOGIN_DRIVER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            if(!jsonObject.getBoolean("error")){

                                SharedPreferenceManager.getInstance(getApplicationContext())
                                        .driver_Login(
                                                jsonObject.getString("driver_id"),
                                                jsonObject.getString("license_id"),
                                                jsonObject.getString("driver_pincode"),
                                                jsonObject.getString("driver_lname"),
                                                jsonObject.getString("driver_fname"),
                                                jsonObject.getString("driver_mi"),
                                                jsonObject.getString("driver_gender"),
                                                jsonObject.getString("driver_birthdate"),
                                                jsonObject.getString("driver_addressProv"),
                                                jsonObject.getString("driver_addressCity"),
                                                jsonObject.getString("driver_mobile"),
                                                jsonObject.getString("driver_tel"),
                                                jsonObject.getString("driver_type"),
                                                jsonObject.getString("driver_email"),
                                                jsonObject.getString("driver_password")
                                        );
                                Toast.makeText(getApplicationContext(),jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), home_page.class));
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(),jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            }
                        }catch (JSONException e){
                            progressDialog.dismiss();
                            if(user == SharedPreferenceManager.getInstance(getApplicationContext()).getUsername() ||
                                    pass== SharedPreferenceManager.getInstance(getApplicationContext()).getSecurityKey() ){

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
                        Toast.makeText(getApplicationContext(),"Something went wrong", Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username", user);
                params.put("password", pass);

                return params;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if(v == loginDriver){
            userLogin();
        }
    }
}
