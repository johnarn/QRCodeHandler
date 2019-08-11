package com.example.qrcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class main_activity extends AppCompatActivity {

    Button createButton;
    Button scanButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


        createButton = (Button) findViewById(R.id.createButton);
        scanButton = (Button) findViewById(R.id.scanButton);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(main_activity.this, create_qr_code_activity.class));
            }
        });
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(main_activity.this, scan_qr_code_activity.class));
            }
        });


    }
}
