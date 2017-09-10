package com.sploit.lmn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {
    Button btnMultiStart;
    Button btnSingleStart;
    Button btnSetting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btnMultiStart = (Button) findViewById(R.id.btn_multiStart);
        btnSingleStart = (Button) findViewById(R.id.btn_singleStart);
        btnSetting = (Button)findViewById(R.id.btn_setting);

        btnSingleStart.setOnClickListener( new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(StartActivity.this, LMNGameActivity.class);
                mainIntent.putExtra("mode","SINGLE");
                startActivity(mainIntent);
            }
        });

        btnMultiStart.setOnClickListener( new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(StartActivity.this, LMNGameActivity.class);
                mainIntent.putExtra("mode","MULTI");
                startActivity(mainIntent);
            }
        });

    }
}
