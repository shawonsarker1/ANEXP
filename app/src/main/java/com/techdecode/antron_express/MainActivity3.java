package com.techdecode.antron_express;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {
      private CardView podview,customerview,Pview,WB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        podview=findViewById(R.id.podcardview);
        customerview=findViewById(R.id.customercardview);
        WB=findViewById(R.id.WBview);
        Pview=findViewById(R.id.PODview);
        podview.setOnClickListener(this);
        customerview.setOnClickListener(this);
        Pview.setOnClickListener(this);
        WB.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.podcardview){
            Toast.makeText(MainActivity3.this,  "Pick Up Form Will Open!Thanks"  ,
                    Toast.LENGTH_LONG).show();
            Intent ap = new Intent(MainActivity3.this, MainActivity2.class);
            startActivity(ap);

        }


                    else if(v.getId()==R.id.customercardview){

            Toast.makeText(MainActivity3.this,  "Customer Form Will Open!Thanks"  ,
                    Toast.LENGTH_LONG).show();
            Intent ap = new Intent(MainActivity3.this, customerEntry.class);
            startActivity(ap);

        }
        else if(v.getId()==R.id.PODview){

            Toast.makeText(MainActivity3.this,  "POD Form Will Open!Thanks"  ,
                    Toast.LENGTH_LONG).show();
            Intent ap = new Intent(MainActivity3.this, MainActivity_pod.class);
            startActivity(ap);

        }
        else if(v.getId()==R.id.PODview){

            Toast.makeText(MainActivity3.this,  "POD Form Will Open!Thanks"  ,
                    Toast.LENGTH_LONG).show();
            Intent ap = new Intent(MainActivity3.this, MainActivity_pod.class);
            startActivity(ap);

        }
        else{
            if(v.getId()==R.id.WBview){
                Toast.makeText(MainActivity3.this, "WB Report Form Will Open!Thanks",
                        Toast.LENGTH_LONG).show();
                Intent ap = new Intent(MainActivity3.this, MainActivity_report.class);
                startActivity(ap);

            }
        }

    }
}