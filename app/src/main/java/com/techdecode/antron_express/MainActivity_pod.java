package com.techdecode.antron_express;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.techdecode.antron_express.models.User;
import com.techdecode.antron_express.models.User2;
import com.techdecode.antron_express.models.User_AWB;
import com.techdecode.antron_express.models.User_district;
import com.techdecode.antron_express.models.User_emp;
import com.techdecode.antron_express.models.User_status;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity_pod extends AppCompatActivity {
    String URL="http://192.168.0.103/andriod/AWB.php";
    String district_url="http://192.168.0.103/andriod/DISTRICT.php";
    String employee_url="http://192.168.0.103/andriod/employee.php";
    String update_url= "http://192.168.0.103/andriod/update_awb.php";
    String status= "http://192.168.0.103/andriod/awb_status.php";

    Spinner spinner,spinner_district,spinner_emp,spinner_status;
    EditText Delivery_person,phone;
    RequestQueue requestQueue;

    Button insert;
    List<User_AWB> users;
    List<User_district>district;
    List<User_emp> user_emp;
    List<User_status> user_status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pod);
        spinner = (Spinner) findViewById(R.id.spin_re);
        spinner_district = (Spinner) findViewById(R.id.spin_area);
        spinner_emp=(Spinner) findViewById(R.id.spin_emp);
        spinner_status=(Spinner) findViewById(R.id.spin_status);
        insert = (Button) findViewById(R.id.submitButton);
        Delivery_person = (EditText) findViewById(R.id.nameInput);
        phone=(EditText) findViewById(R.id.mobile);
        users = new ArrayList<>();
        district= new ArrayList<User_district>();
        user_emp=new ArrayList<User_emp>();
        user_status=new ArrayList<User_status>();
        loadSpinnerData(URL);
        //loadSpinnerData2(URL);
        loadSpinnerData3(district_url);
        loadSpinnerData4(employee_url);
        loadSpinnerData5(status);


        requestQueue = Volley.newRequestQueue(getApplicationContext());
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                    StringRequest request = new StringRequest(Request.Method.POST, update_url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Toast.makeText(MainActivity_pod.this, "Data Insert Sucessfully!", Toast.LENGTH_LONG).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> parameters = new HashMap<String, String>();

                            parameters.put("awb",users.get(spinner.getSelectedItemPosition()).getName());
                            parameters.put("DIS_VALUE",district.get(spinner_district.getSelectedItemPosition()).getId());
                            parameters.put("emp",user_emp.get(spinner_emp.getSelectedItemPosition()).getName2());
                            parameters.put("phone",phone.getText().toString());
                            parameters.put("person",Delivery_person.getText().toString());
                            parameters.put("status_value",user_status.get(spinner_status.getSelectedItemPosition()).getId());

                             //Log.e("Log", "Shawon" + user_emp.get(spinner_emp.getSelectedItemPosition()).getName2());



                            return parameters;
                        }
                    };
                    requestQueue.add(request);

                }

        });




    }

    private void loadSpinnerData(String URL) {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);


                    JSONArray jsonArray = jsonObject.getJSONArray("awb");

                    int i;
                    for (i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        String b = jsonObject1.getString("AWB_BILL");

                        users.add(new User_AWB(b));



                    }



                    if (users.size() > 0) {
                        ArrayAdapter<User_AWB> adapter = new ArrayAdapter<User_AWB>(MainActivity_pod.this,
                                android.R.layout.simple_spinner_item, users);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);
                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {

            @Override

            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();

            }

        });


        int socketTimeout = 30000;

        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);

        requestQueue.add(stringRequest);

    }
    private void loadSpinnerData3(String district_url) {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, district_url, new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);


                    JSONArray jsonArray = jsonObject.getJSONArray("District");

                    int i;
                    for (i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        String b = jsonObject1.getString("DISTRICT_NAME");
                        String id = jsonObject1.getString("DISTRICT_ID");


                        district.add(new User_district(id, b));
                        //users_2.add(new User2(id, b));


                    }

                    if (district.size() > 0) {
                        ArrayAdapter<User_district> adapter = new ArrayAdapter<User_district>(MainActivity_pod.this, android.R.layout.simple_spinner_item, district);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_district.setAdapter(adapter);
                        spinner_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                                String idd1 = district.get(position).getId();
                                String desc1 = district.get(position).getName();


                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {

            @Override

            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();

            }

        });


        int socketTimeout = 30000;

        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);

        requestQueue.add(stringRequest);

    }

    private void loadSpinnerData4(String employee_url) {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, employee_url, new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);


                    JSONArray jsonArray = jsonObject.getJSONArray("Employee");

                    int i;
                    for (i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        String b = jsonObject1.getString("EMPNAME");
                        String id = jsonObject1.getString("SYP_USER_NAME");


                        user_emp.add(new User_emp(b,id));
                        //users_2.add(new User2(id, b));


                    }

                    if (user_emp.size() > 0) {
                        ArrayAdapter<User_emp> adapter = new ArrayAdapter<User_emp>(MainActivity_pod.this, android.R.layout.simple_spinner_item, user_emp);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_emp.setAdapter(adapter);
                        spinner_emp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                                String idd1 = user_emp.get(position).getName2();
                                String desc1 = user_emp.get(position).getName();


                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {

            @Override

            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();

            }

        });








        int socketTimeout = 30000;

        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);

        requestQueue.add(stringRequest);

    }



    private void loadSpinnerData5(String status) {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, status, new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);


                    JSONArray jsonArray = jsonObject.getJSONArray("Status");

                    int i;
                    for (i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        String b = jsonObject1.getString("STATUS_NAME");
                        String id = jsonObject1.getString("STATUS_ID");


                        user_status.add(new User_status(id,b));
                        //users_2.add(new User2(id, b));


                    }

                    if (user_status.size() > 0) {
                        ArrayAdapter<User_status> adapter = new ArrayAdapter<User_status>(MainActivity_pod.this, android.R.layout.simple_spinner_item, user_status);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_status.setAdapter(adapter);
                        spinner_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                                String idd1 = user_status.get(position).getName();
                                String desc1 = user_status.get(position).getId();


                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {

            @Override

            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();

            }

        });

        int socketTimeout = 30000;

        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);

        requestQueue.add(stringRequest);

    }






}