package com.techdecode.antron_express;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.techdecode.antron_express.models.User;
import com.techdecode.antron_express.models.User2;
import com.techdecode.antron_express.models.User_district;
import com.techdecode.antron_express.models.User_emp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
    Spinner spinner,spinner2,spinner_district,spinner_emp;

    String URL="http://192.168.0.103/andriod/gl_sl_code.php";
    String showUrl="http://192.168.0.103/andriod/insert_value.php";
    String district_url="http://192.168.0.103/andriod/DISTRICT.php";
    String employee_url="http://192.168.0.103/andriod/employee.php";
    Button insert;
    EditText address_de,city_2,state_2,con_name_2,contact_num,email2,address_se,city_1,state_1,con_name_1,contact_num1,email1,Weight,ctns,dimension,Commoodity,DG,sep,d_weight;
    RequestQueue requestQueue;
private ArrayList<String> data=new ArrayList<>();
private ArrayList<String> data1=new ArrayList<>();
private ArrayList<String> data2=new ArrayList<>();
private ArrayList<String> data3=new ArrayList<>();

private TableLayout table;

Button b1;


    ArrayList<String> CountryName;
    ArrayList<String> _ids = new ArrayList<String>();

    List<User> users;
    List<User2> users_2;
    List<User_district> district;
    List<User_emp> user_emp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        address_de = (EditText) findViewById(R.id.address_De);
        city_2=(EditText) findViewById(R.id.city_town2);
        state_2=(EditText) findViewById(R.id.state_de);
        con_name_2=(EditText) findViewById(R.id.contact_nam);
        contact_num=(EditText) findViewById(R.id.contact_num);
        email2=(EditText) findViewById(R.id.email_id);


        address_se = (EditText) findViewById(R.id.address_se);
        city_1=(EditText) findViewById(R.id.city_town);
        state_1=(EditText) findViewById(R.id.state_se);
        con_name_1=(EditText) findViewById(R.id.contact_nam1);
        contact_num1=(EditText) findViewById(R.id.contact_num1);
        email1=(EditText) findViewById(R.id.email_id1);

        Weight  = (EditText) findViewById(R.id.weight);
        ctns=(EditText) findViewById(R.id.ctns);
        dimension=(EditText) findViewById(R.id.dimen);
        Commoodity=(EditText) findViewById(R.id.cc);
        DG=(EditText) findViewById(R.id.dg);
        sep=(EditText) findViewById(R.id.sep);
        d_weight=(EditText) findViewById(R.id.de_weight);




        CountryName = new ArrayList<>();

        users = new ArrayList<>();
        users_2 = new ArrayList<User2>();
        district= new ArrayList<User_district>();
        user_emp=new ArrayList<User_emp>();
        spinner = (Spinner) findViewById(R.id.spin_re);
        spinner2 = (Spinner) findViewById(R.id.spin_de);
        spinner_district = (Spinner) findViewById(R.id.spin_area);
        spinner_emp=(Spinner) findViewById(R.id.spin_emp);
        insert = (Button) findViewById(R.id.insert);
        b1 = (Button) findViewById(R.id.btn1);

        loadSpinnerData(URL);
        loadSpinnerData2(URL);
        loadSpinnerData3(district_url);
        loadSpinnerData4(employee_url);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dimension.getText().toString().length() == 0 || Commoodity.getText().toString().length() == 0||DG.getText().toString().length() == 0) {

                    if (dimension.getText().toString().length() == 0)
                        dimension.setError("Dimension is required!");

                    else if (Commoodity.getText().toString().length() == 0)
                        Commoodity.setError("Commoodity is required!");

                    else if (DG.getText().toString().length() == 0)
                        DG.setError("DG/NON DG is required!");

                }
                else{
                    add();


                }





            }
        });

        requestQueue = Volley.newRequestQueue(getApplicationContext());

/*
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String country = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();

                Toast.makeText(getApplicationContext(), "OPEN", Toast.LENGTH_LONG).show();

            }

            @Override

            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });*/
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.POST, showUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Toast.makeText(getBaseContext(), "Data Insert Sucessfully!" ,Toast.LENGTH_LONG ).show();
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> parameters  = new HashMap<String, String>();
                        parameters.put("name",users.get(spinner.getSelectedItemPosition()).getId());
                        parameters.put("pass",users.get(spinner.getSelectedItemPosition()).getName());
                        parameters.put("DIS_VALUE",district.get(spinner_district.getSelectedItemPosition()).getId());
                        parameters.put("emp",user_emp.get(spinner_emp.getSelectedItemPosition()).getName2());
                       // Log.e("Log", "Shawon" + district.get(spinner_district.getSelectedItemPosition()).getName() );
                        parameters.put("Delivery",users_2.get(spinner2.getSelectedItemPosition()).getId());
                        parameters.put("Deliveryad",address_de.getText().toString());
                        parameters.put("Deliverycity",city_2.getText().toString());
                        parameters.put("Deliverystate",state_2.getText().toString());
                        parameters.put("Deliveryconname",con_name_2.getText().toString());
                        parameters.put("Deliveryconnum",contact_num.getText().toString());
                        parameters.put("Deliveryemail",email2.getText().toString());
                        parameters.put("shipperad",address_se.getText().toString());
                        parameters.put("shippercity",city_1.getText().toString());
                        parameters.put("shipperstate",state_1.getText().toString());
                        parameters.put("shipperconname",con_name_1.getText().toString());
                        parameters.put("shipperconnum",contact_num1.getText().toString());
                        parameters.put("shipperemail",email1.getText().toString());
                        parameters.put("weight",Weight.getText().toString());
                        parameters.put("ctns",ctns.getText().toString());
                        parameters.put("SPEACIAL",sep.getText().toString());

                        for (int z=0;z<data.size();z++)
                        {

                            parameters.put("dimension["+z+"]",data.get(z).toString());
                            parameters.put("Commoodity["+z+"]",data1.get(z).toString());
                            parameters.put("DG["+z+"]",data2.get(z).toString());
                            parameters.put("D_weight["+z+"]",data3.get(z).toString());


                        }


                        return parameters;
                    }
                };
                requestQueue.add(request);
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


            MenuInflater menuInflater=getMenuInflater();
            menuInflater.inflate(R.menu.menu_layout,menu);
            return true;

    }

    private void loadSpinnerData(String URL) {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);


                    JSONArray jsonArray = jsonObject.getJSONArray("result");

                    int i;
                    for (i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        String b = jsonObject1.getString("SLNAME");
                        String id = jsonObject1.getString("SLCODE");
                        String ad=jsonObject1.getString("ADDR1");
                        String CONTACTP=jsonObject1.getString("CONTACTP");
                        String ALT_PHONE=jsonObject1.getString("ALT_PHONE");

                        users.add(new User(id, b,ad,CONTACTP,ALT_PHONE));



                    }

                   //String id = setSpinner();
                  //System.out.println(id);


                 /*  ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity2.this, android.R.layout.simple_spinner_item, CountryName);
                   adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                   spinner.setAdapter(adapter);
                   spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                       @Override
                       public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                          //fun(id);
                       }

                       @Override
                       public void onNothingSelected(AdapterView<?> parent) {

                       }
                   });*/

                    if (users.size() > 0) {
                        ArrayAdapter<User> adapter = new ArrayAdapter<User>(MainActivity2.this,
                                android.R.layout.simple_spinner_item, users);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);
                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                                String idd = users.get(position).getId();
                                String desc = users.get(position).getName();
                                String add_user = users.get(position).getAdd();
                                String contact_user = users.get(position).getCONTACTP();
                                String alt_user = users.get(position).getALT_PHONE();

                               // Log.e("Log", "add_user " + add_user + " desc " + desc);

                                address_se.setText(add_user);
                                con_name_1.setText(contact_user);
                                contact_num1.setText(alt_user);

                              //  Log.e("Log", "id " + idd + " desc " + desc);
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

    private void loadSpinnerData2(String URL) {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);


                    JSONArray jsonArray = jsonObject.getJSONArray("result");

                    int i;
                    for (i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        String b = jsonObject1.getString("SLNAME");
                        String id = jsonObject1.getString("SLCODE");
                        String ad=jsonObject1.getString("ADDR1");
                        String CONTACTP=jsonObject1.getString("CONTACTP");
                        String ALT_PHONE=jsonObject1.getString("ALT_PHONE");

                        users_2.add(new User2(id, b,ad,CONTACTP,ALT_PHONE));
                        //users_2.add(new User2(id, b));


                    }

                    //String id = setSpinner();
                    //System.out.println(id);


                 /*  ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity2.this, android.R.layout.simple_spinner_item, CountryName);
                   adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                   spinner.setAdapter(adapter);
                   spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                       @Override
                       public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                          //fun(id);
                       }

                       @Override
                       public void onNothingSelected(AdapterView<?> parent) {

                       }
                   });*/

                    if (users_2.size() > 0) {
                        ArrayAdapter<User2> adapter = new ArrayAdapter<User2>(MainActivity2.this,
                                android.R.layout.simple_spinner_item, users_2);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        spinner2.setAdapter(adapter);
                        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                                String idd = users_2.get(position).getId();
                                 String desc1 = users_2.get(position).getName();
                                String add_user1 = users_2.get(position).getAdd();
                                String contact_user1 = users_2.get(position).getCONTACTP();
                                String alt_user1 = users_2.get(position).getALT_PHONE();

                                address_de.setText(add_user1);
                                con_name_2.setText(contact_user1);
                                contact_num.setText(alt_user1);

                                // Log.e("Log", "id " + idd + " desc " + desc);
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

    private void loadSpinnerData3(String URL) {

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
                        ArrayAdapter<User_district> adapter = new ArrayAdapter<User_district>(MainActivity2.this, android.R.layout.simple_spinner_item, district);
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

    private void loadSpinnerData4(String URL) {

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
                        ArrayAdapter<User_emp> adapter = new ArrayAdapter<User_emp>(MainActivity2.this, android.R.layout.simple_spinner_item, user_emp);
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


    public void add(){
          int tot;
          int h=Integer.parseInt(dimension.getText().toString());
          int l=Integer.parseInt(Commoodity.getText().toString());
           int w=Integer.parseInt(DG.getText().toString());
           tot=((h*l*w)/5000);
           data.add(String.valueOf(h));
           data1.add(String.valueOf(l));
           data2.add(String.valueOf(w));
           data3.add(String.valueOf(tot));

           TableLayout table=(TableLayout)findViewById(R.id.tb1);
        TableRow row=new TableRow(this);
        TextView t1=new TextView(this);
        TextView t2=new TextView(this);
        TextView t3=new TextView(this);
        TextView t4=new TextView(this);


         int sum;
        sum = 0;

        for(int i=0;i<data.size();i++){
             String k=data.get(i);
             String u=data1.get(i);
             String p=data2.get(i);
             String to=data3.get(i);
             t1.setText(k);
             t2.setText(u);
             t3.setText(p);
             t4.setText(to);

               sum=sum+Integer.parseInt(data3.get(i).toString());


         }
         row.addView(t1);
        row.addView(t2);
        row.addView(t3);
        row.addView(t4);
        table.addView(row);

       d_weight.setText(String.valueOf(sum));
       
    }

}


