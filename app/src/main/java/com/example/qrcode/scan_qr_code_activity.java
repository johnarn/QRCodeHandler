package com.example.qrcode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

/**
 * Scan QR Code Activity
 * <p>
 * Give the user the option to scan an existing QR Code with the
 * Camera of the phone. Camera auto adjust and when detects a QR Code vibrates
 * and shows the user the text that the QR Code has.
 */
public class scan_qr_code_activity extends AppCompatActivity {

    /*
     * Initialize the views from the layout
     */
    SurfaceView surfaceView;
    CameraSource cameraSource;
    TextView textView;
    BarcodeDetector barcodeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_qr_code_activity);

        /*
         * Find the views by their ids
         */
        surfaceView = findViewById(R.id.camerapreview);
        textView = findViewById(R.id.textView);

        /*
         * Create a Barcode Detector to help detect the QR Codes
         */
        barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();

        /*
         * Create a preview of the camera
         */
        cameraSource = new CameraSource.Builder(this, barcodeDetector).setRequestedPreviewSize(640, 480).setAutoFocusEnabled(true).build();

        /*
         * Surface View helps to adjust at the right place the camera
         */
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {

                /*
                 * If the App doesn't have the permission to access the camera get it from the user
                 */
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "permissions not granted", Toast.LENGTH_LONG).show();
                    requestPermission();
                }
                try {
                    /*
                     * Start the camera
                     */
                    cameraSource.start(surfaceHolder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                /*
                 * Stop the camera
                 */
                cameraSource.stop();
            }
        });

        /*
         * When the barcode finds a detection
         */
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {

                /*
                 * Find the QR Codes that have been detected by the camera
                 */
                final SparseArray<Barcode> qrCodes = detections.getDetectedItems();

                /*
                 * If the camera has detect at least one QR Code
                 */
                if (qrCodes.size() != 0) {
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            /*
                             * Vibrate the phone
                             */
                            Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(1000);

                            /*
                             * show the text of the QR Code to user
                             */
                            textView.setText(qrCodes.valueAt(0).displayValue);
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });

    }


    /**
     * Request CAMERA permission from the user
     */
    private void requestPermission() {
        /*
         * Ask the User to give the camera permission
         */
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            new AlertDialog.Builder(this).setTitle("Permission needed").setMessage("The permission is needed").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCompat.requestPermissions(scan_qr_code_activity.this, new String[]{Manifest.permission.CAMERA}, 1);

                }
            }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                /*
                 * If the CAMERA permission has been granted
                 */
                Toast.makeText(this, "Permission granted", Toast.LENGTH_LONG).show();
            } else {

                /*
                 * IF the CAMERA permission has not been granted
                 */
                Toast.makeText(this, "Permission not granted", Toast.LENGTH_LONG).show();

            }
        }
    }
}
