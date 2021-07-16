package com.techdecode.antron_express;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.techdecode.antron_express.models.User;
import com.techdecode.antron_express.models.User_Way_bill;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity_report extends AppCompatActivity {
    public final int REQUEST_CODE_ASK_PERMISSIONS = 11;
    String URL = "http://192.168.0.103/andriod/way_bill.php";
    EditText address_de, city_2, state_2, con_name_2, contact_num, email2, address_se, city_1, state_1, con_name_1, contact_num1, email1, Weight, ctns, dimension, Commoodity, DG, sep, d_weight,nameq;
    RequestQueue requestQueue;
    Spinner spinner;
    Button btnCreate;
    ImageView imageView;
    List<User_Way_bill> users;
    int pageWidth = 1200;
    Bitmap bmp,scaledbmp;
    private static final int STORAGE_CODE = 100;
    String[] informationArray=new String[]{"Company:","Address 1:","Country:","Contact Person:","Contact Number:"};
    String[] informationArray1=new String[100];
    String[] informationArray2=new String[100];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_report);
        address_se = (EditText) findViewById(R.id.address_se);
        city_1 = (EditText) findViewById(R.id.city_town);
        state_1 = (EditText) findViewById(R.id.state_se);
        con_name_1 = (EditText) findViewById(R.id.contact_nam1);
        contact_num1 = (EditText) findViewById(R.id.contact_num1);
        Weight = (EditText) findViewById(R.id.weight);
        spinner = (Spinner) findViewById(R.id.spin_re);
        ctns = (EditText) findViewById(R.id.ctns);
        nameq = (EditText) findViewById(R.id.name);
        btnCreate = findViewById(R.id.create);
        imageView = (ImageView)findViewById(R.id.imageview);
        users = new ArrayList<User_Way_bill>();
        bmp= BitmapFactory.decodeResource(getResources(),R.drawable.logo_12);
        scaledbmp=Bitmap.createScaledBitmap(bmp,150,30,false);
        loadSpinnerData(URL);
        btnCreate.setOnClickListener(view -> {
                    if (CheckingPermissionIsEnabledOrNot()) {
                        barcode();
                        createPdf();

                    }
                    // If, If permission is not enabled then else condition will execute.
                    else {
                        //Calling method to enable permission.
                        RequestMultiplePermission();
                    }
                }
        );

    }

    private void loadSpinnerData(String URL) {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);


                    JSONArray jsonArray = jsonObject.getJSONArray("Way");

                    int i;
                    for (i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        String b = jsonObject1.getString("AW_BILL");
                        String id = jsonObject1.getString("CONSIGNE_ADDRESS");
                        String ad = jsonObject1.getString("CONSIGNE_CITY");
                        String CONTACTP = jsonObject1.getString("CON_CONTACT_NAM");
                        String ALT_PHONE = jsonObject1.getString("CON_CONTACT_NUM");
                        String weight = jsonObject1.getString("WEIGHT");
                        String ctns = jsonObject1.getString("CTNS");
                        String name=jsonObject1.getString("NAME");

                        users.add(new User_Way_bill(b, id, ad, CONTACTP, ALT_PHONE, weight, ctns,name));


                    }


                    if (users.size() > 0) {
                        ArrayAdapter<User_Way_bill> adapter = new ArrayAdapter<User_Way_bill>(MainActivity_report.this,
                                android.R.layout.simple_spinner_item, users);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);
                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                                String add = users.get(position).getAdress();
                                String desc = users.get(position).getCity();
                                String contact_nam = users.get(position).getContact_nam();
                                String contact_num = users.get(position).getContact_num();
                                String weight = users.get(position).getWeight();
                                String ctn = users.get(position).getCtn();
                                String name = users.get(position).getName();
                                String bill = users.get(spinner.getSelectedItemPosition()).getBill();



                                // Log.e("Log", "add_user " + add_user + " desc " + desc);

                                address_se.setText(add);
                                con_name_1.setText(contact_nam);
                                contact_num1.setText(contact_num);
                                Weight.setText(weight);
                                ctns.setText(ctn);
                                city_1.setText(desc);
                                nameq.setText(name);

                                informationArray1[0]=nameq.getText().toString();
                                informationArray1[1]=address_se.getText().toString();
                                informationArray1[2]=contact_num1.getText().toString();
                                informationArray1[3]=con_name_1.getText().toString();
                                informationArray1[4]=address_se.getText().toString();


                                informationArray2[0]=nameq.getText().toString();
                                informationArray2[1]=address_se.getText().toString();
                                informationArray2[2]=contact_num1.getText().toString();
                                informationArray2[3]=con_name_1.getText().toString();
                                informationArray2[4]=address_se.getText().toString();


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

    private void createPdf() {

                PdfDocument myPdfDocument = new PdfDocument();
                Paint myPaint = new Paint();
                Paint titlePaint = new Paint();


                PdfDocument.PageInfo myPageInfo1 = new PdfDocument.PageInfo.Builder(250, 500, 1).create();
                PdfDocument.Page myPage1 = myPdfDocument.startPage(myPageInfo1);
                Canvas canvas = myPage1.getCanvas();





        myPaint.setTextAlign(Paint.Align.CENTER);
        myPaint.setTextSize(12.0f);
      //  canvas.drawText("Antron Express", myPageInfo1.getPageWidth()/2,30,myPaint);
        canvas.drawBitmap(scaledbmp,60,10,myPaint);

      /*  myPaint.setTextSize(6.0f);
        myPaint.setColor(Color.rgb(122,119,119));
        canvas.drawText("Mouchak Tower (2nd Floor), 83 B, New Circular Road Malibagh Mor,Dhaka-1217, Bangladesh",myPageInfo1.getPageWidth()/2,40,myPaint);

       */

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(9.0f);
        myPaint.setColor(Color.rgb(122,119,119));
        canvas.drawText("Shipper Information",10,70,myPaint);

        int startXPosition2=10;
        int endXPosition2=myPageInfo1.getPageWidth()-20;
        int startYPosition2=75;


        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(8.0f);
        myPaint.setColor(Color.BLACK);
        canvas.drawLine(startXPosition2,startYPosition2+3,endXPosition2,startYPosition2+3,myPaint);


        int startXPosition=10;
        int endXPosition=myPageInfo1.getPageWidth()-10;
        int startYPosition=90;

        for(int i=0;i<5;i++)
        {
            canvas.drawText(informationArray[i],startXPosition,startYPosition,myPaint);
            //canvas.drawLine(startXPosition,startYPosition+3,endXPosition,startYPosition+3,myPaint);
            startYPosition+=20;


        }

        int startXPositione=75;
        int endXPositione=myPageInfo1.getPageWidth()-10;
        int startYPositione=90;

        for(int i=0;i<5;i++) {
            canvas.drawText(informationArray1[i], startXPositione, startYPositione, myPaint);
          //  canvas.drawLine(startXPositione,startYPositione+3,endXPositione,startYPositione+3,myPaint);
            startYPositione+=20;

        }

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(9.0f);
        myPaint.setColor(Color.rgb(122,119,119));
        canvas.drawText("Conginee Information",10,200,myPaint);


        int startXPosition4=10;
        int endXPosition4=myPageInfo1.getPageWidth()-20;
        int startYPosition4=205;

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(8.0f);
        myPaint.setColor(Color.BLACK);
        canvas.drawLine(startXPosition4,startYPosition4+3,endXPosition4,startYPosition4+3,myPaint);

        int startXPosition1=10;
        int endXPosition1=myPageInfo1.getPageWidth()-10;
        int startYPosition1=220;

        for(int i=0;i<5;i++)
        {
            canvas.drawText(informationArray[i],startXPosition1,startYPosition1,myPaint);
           // canvas.drawLine(startXPosition1,startYPosition1+5,endXPosition1,startYPosition1+5,myPaint);
            startYPosition1+=20;

        }

        int startXPositionc=75;
        int endXPositionc=myPageInfo1.getPageWidth()-10;
        int startYPositionc=220;

        for(int i=0;i<5;i++)
        {
            canvas.drawText(informationArray2[i],startXPositionc,startYPositionc,myPaint);
           // canvas.drawLine(startXPositionc,startYPositionc+5,endXPositionc,startYPositionc+5,myPaint);
            startYPositionc+=20;

        }




        myPaint.setStyle(Paint.Style.STROKE);
        myPaint.setStrokeWidth(1);
        canvas.drawRect(10,320,myPageInfo1.getPageWidth()-10,380,myPaint);
     //   canvas.drawLine(80,200,85,300,myPaint);
        //canvas.drawLine(163,200,163,300,myPaint);

        myPaint.setStrokeWidth(0);
        myPaint.setStyle(Paint.Style.FILL);



        canvas.drawText("Weight",15,330,myPaint);
        canvas.drawText(""+Weight.getText(),15,350,myPaint);
        canvas.drawText(" No of Ctns",55,330,myPaint);
        canvas.drawText(""+Weight.getText(),55,350,myPaint);
        canvas.drawText("D.Weight",130,330,myPaint);
        canvas.drawText(""+ctns.getText(),130,350,myPaint);
        canvas.drawText("Custom Value",180,330,myPaint);
        canvas.drawText(""+ctns.getText(),190,350,myPaint);


        myPaint.setStyle(Paint.Style.STROKE);
        myPaint.setStrokeWidth(1);
        canvas.drawRect(10,390,myPageInfo1.getPageWidth()-10,450,myPaint);
        //   canvas.drawLine(80,200,85,300,myPaint);
        //canvas.drawLine(163,200,163,300,myPaint);

        myPaint.setStrokeWidth(0);
        myPaint.setStyle(Paint.Style.FILL);
        BitmapDrawable bitmapDrawable= (BitmapDrawable)imageView.getDrawable();
        Bitmap bitmap=bitmapDrawable.getBitmap();

        canvas.drawBitmap(bitmap,75,410,myPaint);
        canvas.drawText("AWB : "+users.get(spinner.getSelectedItemPosition()).getBill(),82,440,myPaint);







        /*directory path*/

                myPdfDocument.finishPage(myPage1);
                String directory_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/mypdf/";
                File file = new File(directory_path);
                Log.e("main", "error " + directory_path);
                if (!file.exists()) {
                    file.mkdirs();
                }
                double random_double = Math.random() * (999999 - 999 + 1) + 999;
                String targetPdf = directory_path + String.valueOf(random_double) + ".pdf";
                File filePath = new File(targetPdf);
                try {
                    myPdfDocument.writeTo(new FileOutputStream(filePath));
                    Toast.makeText(this, "Done", Toast.LENGTH_LONG).show();
                } catch (IOException e) {

                    Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
                }
                // close the document
                myPdfDocument.close();


     /*
                File file = new File(Environment.getExternalStorageDirectory(), "/Hello.pdf");
                Log.d("PDFCreator", "PDF Path: " + file);


                try {
                    myPdfDocument.writeTo(new FileOutputStream(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getBaseContext(), "Data Insert Sucessfully!", Toast.LENGTH_LONG).show();

                myPdfDocument.close();*/
            }
    private void barcode() {

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = multiFormatWriter.encode(users.get(spinner.getSelectedItemPosition()).getBill(), BarcodeFormat.CODE_128,100,20,null);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

    }


        public boolean CheckingPermissionIsEnabledOrNot(){

            int FirstPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
            int SecondPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);

            return FirstPermissionResult == PackageManager.PERMISSION_GRANTED &&
                    SecondPermissionResult == PackageManager.PERMISSION_GRANTED;
        }

        private void RequestMultiplePermission() {

            // Creating String Array with Permissions.
            ActivityCompat.requestPermissions(MainActivity_report.this, new String[]
                    {
                            READ_EXTERNAL_STORAGE,
                            WRITE_EXTERNAL_STORAGE,
                    }, 999);

        }

        @Override
        public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
            switch (requestCode) {

                case 999:

                    if (grantResults.length > 0) {

                        boolean ReadStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                        boolean WriteStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                        if (ReadStorage && WriteStorage) {


                        } else {

                            Toast.makeText(MainActivity_report.this, "Permission Denied", Toast.LENGTH_LONG).show();

                        }
                    }

                    break;
            }
        }



    
}

