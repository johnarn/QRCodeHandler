package com.example.qrcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


/**
 * Main QR Code Activity
 * <p>
 * Give the user the options to:
 * 1. Create a new QR Code
 * 2. Scan an existing QR Code with the Camera of the phone
 * 3. Import an existing QR Code from the gallery of the phone
 */
public class main_activity extends AppCompatActivity {

    /*
     * Initialize the views of the main activity
     */
    Button createButton;
    Button scanButton;
    Button importButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        /*
         * Find the exact views by their id
         */
        createButton = findViewById(R.id.createButton);
        scanButton = findViewById(R.id.scanButton);
        importButton = findViewById(R.id.importButton);

        /*
         * Create onClickListener for the create QR Code Button
         */
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                 * Start the create_qr_code Activity
                 */
                startActivity(new Intent(main_activity.this, create_qr_code_activity.class));
            }
        });

        /*
         * Create onClickListener for the scan QR Code Button
         */
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                 * Start the scan_qr_code Activity
                 */
                startActivity(new Intent(main_activity.this, scan_qr_code_activity.class));
            }
        });

        /*
         * Create onClickListener for the import QR Code Button
         */
        importButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                 * Start the import_qr_code Activity
                 */
                startActivity(new Intent(main_activity.this, import_qr_code_activity.class));
            }
        });
    }
}
