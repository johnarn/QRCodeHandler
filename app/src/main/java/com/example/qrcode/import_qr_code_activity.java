package com.example.qrcode;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

/**
 *
 *
 * Import QR Code Activity
 *
 * Activity for importing existing QR Code from the gallery of the phone
 *
 *
 */
public class import_qr_code_activity extends AppCompatActivity {

    /*
     * Initialize views from the layout
     */
    Button importButton;
    ImageView imageView;
    TextView textView;

    private int RESULT_LOAD_IMG = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.import_qr_code_activity);

        /*
         * Find the views from the layout
         */
        importButton = findViewById(R.id.selectImageButton);
        imageView = findViewById(R.id.iv);
        textView = findViewById(R.id.txtView);


        /*
         * on Click Listener for the import Button
         */
        importButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                 * Start a new Intent to scan the storage for images
                 */
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMG);
            }
        });
    }


    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {

            /*
             * Find the URI of the image that the user has selected
             */
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            /*
             * Create a cursor to access the image that the user has selected
             */
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            /*
             * Find the image absolute path inside the storage of the android phone
             */
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);

            /*
             * Close the cursor
             */
            cursor.close();

            /*
             * Set the QR Code that the user has selected at the imageView
             */
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

            /*
             * Create a bitmap from the selected Image
             */
            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);

            /*
             * Get the width and height of the Image
             */
            int width = bitmap.getWidth(), height = bitmap.getHeight();

            /*
             * Create a array with all the pixels of the Image
             */
            int[] pixels = new int[width * height];

            /*
             * Get the pixels of the bitmap
             */
            bitmap.getPixels(pixels, 0, width, 0, 0, width, height);

            /*
             * Create a binary Bitmap and a MultiFormat reader to read the qr code
             */
            RGBLuminanceSource source = new RGBLuminanceSource(width, height, pixels);
            BinaryBitmap bBitmap = new BinaryBitmap(new HybridBinarizer(source));
            MultiFormatReader reader = new MultiFormatReader();
            try{
                /*
                 * Get the result of the binary bitmap to show to the user
                 */
                Result result = reader.decode(bBitmap);

                /*
                 * Set the text of the bitmap to the textView
                 */
                textView.setText(result.toString());
            }
            catch (NotFoundException e){
                e.printStackTrace();
            }
        }else {
            /*
             * If anything goes wrong inform the user
             */
            Toast.makeText(import_qr_code_activity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }

}
