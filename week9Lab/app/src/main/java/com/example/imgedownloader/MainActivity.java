package com.example.imgedownloader;

import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    //UI THREAD !!

    EditText txtUrl;
    Button btnDownload;
    ImageView imgView;


    private static final int REQUEST_EXTERNAL_STORAGE =1;
    private static String [] PERMISSIONS_STORAGE = { android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy); // must add to use UI thread .

        setContentView(R.layout.activity_main);
        txtUrl = findViewById(R.id.txtURL);
        imgView = findViewById(R.id.imgView);
        btnDownload = findViewById(R.id.btnDownload);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permission = ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    // We don't have permission so we add one
                    ActivityCompat.requestPermissions(
                            MainActivity.this,
                            PERMISSIONS_STORAGE,
                            REQUEST_EXTERNAL_STORAGE);
                }
                //UI THREAD
//                String fileName = "temp.jpg";
//                String imagePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/" + fileName;
//
//                downloadFile(txtUrl.getText().toString(),imagePath);
//                preview(imagePath);

                else{
//                    DownloadTask backgroundTask = new DownloadTask();
//                    String [] urls = new String[1];
//                    urls[0] = txtUrl.getText().toString();
//
//                    backgroundTask.execute(urls);

                    Thread backgroundThread = new Thread(new DownloadRunnable(txtUrl.getText().toString()));
                    backgroundThread.start();


                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length ==2  && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
//            String fileName = "temp.jpg";
//            String imagePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/" + fileName;
//
//            downloadFile(txtUrl.getText().toString(),imagePath);
//            preview(imagePath);

//            DownloadTask backgroundTask = new DownloadTask();
//            String [] urls = new String[1];
//            urls[0] = txtUrl.getText().toString();
//
//            backgroundTask.execute(urls);

        }else{
            Toast.makeText(this, "External Storage Permission is not granted", Toast.LENGTH_SHORT).show();
        }
    }

    void downloadFile(String strURL, String imagePath){
        try {

            URL url = new URL(strURL);
            URLConnection connection = url.openConnection(); //open connection
            connection.connect(); //call connect method !

            InputStream inputStream = new BufferedInputStream(url.openStream(), 8192); //for reading file
            OutputStream outputStream = new FileOutputStream(imagePath); //for writing file

            byte data[] = new byte[1024]; //
            int count; //this const for how many bytes to read !
            while ((count = inputStream.read(data)) != -1) {
                outputStream.write(data, 0, count);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();  //after doing the job (Reading from bytes) close string!
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private Bitmap rescaleBitmap(String imagePath){
        Bitmap image = BitmapFactory.decodeFile(imagePath);
        float imageWidth = image.getWidth();
        float imageHeight = image.getHeight();

        int rescaleWidth = 400;
        int rescaleHeight = (int) ( (imageHeight*rescaleWidth)/imageWidth);
        Bitmap bitmap = Bitmap.createScaledBitmap(image , rescaleWidth, rescaleHeight  , false);
        imgView.setImageBitmap(bitmap); //
        return  bitmap;
    }

    void preview(String imagePath ){
        Bitmap image = BitmapFactory.decodeFile(imagePath);
        float imageWidth = image.getWidth();
        float imageHeight = image.getHeight();

        int rescaleWidth = 400;
        int rescaleHeight = (int) ( (imageHeight*rescaleWidth)/imageWidth);
        Bitmap b = Bitmap.createScaledBitmap(image , rescaleWidth, rescaleHeight  , false);
        imgView.setImageBitmap(b);
    }


    class DownloadTask extends AsyncTask <String, Integer , Bitmap> {
        ProgressDialog progressDialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMax(100);
            progressDialog.setIndeterminate(false);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setTitle("Downloading");
            progressDialog.setMessage("Please wait..");
            progressDialog.show();
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            progressDialog.setProgress(values[0]);
        }



        @Override
        protected Bitmap doInBackground(String... urls) {

            String fileName= "temp.jpg";

            String imagePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
            downloadFile(urls[0] , imagePath + "/" + fileName);
            return rescaleBitmap(imagePath + "/" +fileName);
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imgView.setImageBitmap(bitmap);
            progressDialog.dismiss();
        }

    }

    class DownloadRunnable implements Runnable{

        String url;

        public DownloadRunnable(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            String fileName= "temp.jpg";

            String imagePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
            downloadFile(url , imagePath + "/" + fileName);

            Bitmap bitmap = rescaleBitmap(imagePath + "/" +fileName);
            runOnUiThread(new UpdateBitmap(bitmap));
        }

        class UpdateBitmap implements  Runnable{
            Bitmap bitmap;

            public UpdateBitmap(Bitmap bitmap) {
                this.bitmap = bitmap;


            }

            @Override
            public void run() {
                imgView.setImageBitmap(bitmap);

            }
    }
}
}