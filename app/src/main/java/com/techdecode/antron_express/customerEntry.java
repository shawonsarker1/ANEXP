package com.techdecode.antron_express;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class customerEntry extends AppCompatActivity {
    String URL = "http://192.168.7.41/andriod/customer_insert.php";
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_entry);
        EditText cust_name,cust_add,cust_phone,cust_email,cust_contact,cust_alter,cust_city,cust_dis,cust_country;
        Button insert;

        cust_name = (EditText) findViewById(R.id.nameInput);
        cust_add=(EditText) findViewById(R.id.address);
        cust_phone=(EditText) findViewById(R.id.mobile);
        cust_email=(EditText) findViewById(R.id.emailInput);
        cust_contact=(EditText) findViewById(R.id.contactperson);
        insert = (Button) findViewById(R.id.submitButton);
        cust_alter=(EditText)findViewById(R.id.contactpersonno);
        cust_city=(EditText)findViewById(R.id.city);
        cust_dis=(EditText)findViewById(R.id.district);
        cust_country=(EditText)findViewById(R.id.Country);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (cust_name.getText().toString().length() == 0 || cust_add.getText().toString().length() == 0 || cust_phone.getText().toString().length() == 0 || cust_contact.getText().toString().length() == 0 || cust_country.getText().toString().length() == 0 || cust_dis.getText().toString().length() == 0 || cust_city.getText().toString().length() == 0) {

                    if (cust_name.getText().toString().length() == 0)
                        cust_name.setError("Username is required!");
                    else if (cust_add.getText().toString().length() == 0)
                        cust_add.setError("Password is required!");
                    else if (cust_phone.getText().toString().length() == 0)
                        cust_phone.setError("Phone is required!");
                    else if (cust_contact.getText().toString().length() == 0)
                        cust_contact.setError("Contact Person is required!");
                    else if (cust_phone.getText().toString().length() == 0)
                        cust_phone.setError("Phone is required!");
                    else if (cust_city.getText().toString().length() == 0)
                        cust_city.setError("City is required!");

                    else if (cust_dis.getText().toString().length() == 0)
                        cust_dis.setError("District is required!");
                    else if (cust_country.getText().toString().length() == 0)
                        cust_country.setError("District is required!");

                } else {
                    StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Toast.makeText(customerEntry.this, "Data Insert Sucessfully!", Toast.LENGTH_LONG).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> parameters = new HashMap<String, String>();
                            parameters.put("SLNAME", cust_name.getText().toString());
                            parameters.put("ADDR1", cust_add.getText().toString());
                            parameters.put("CONTACTP", cust_contact.getText().toString());
                            parameters.put("TELEPHONE", cust_phone.getText().toString());
                            parameters.put("EMAILNO", cust_email.getText().toString());
                            parameters.put("ALTERNO", cust_alter.getText().toString());
                            parameters.put("city", cust_city.getText().toString());
                            parameters.put("district", cust_dis.getText().toString());
                            parameters.put("country", cust_country.getText().toString());


                            return parameters;
                        }
                    };
                    requestQueue.add(request);

                    Intent refresh = new Intent(customerEntry.this, customerEntry.class);
                    startActivity(refresh);//Start the same Activity
                    finish();
                }
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout,menu);
        return true;

    }
}