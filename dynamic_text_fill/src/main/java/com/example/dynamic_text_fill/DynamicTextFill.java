package com.example.dynamic_text_fill;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class DynamicTextFill extends AppCompatActivity implements SensorEventListener {

    Bitmap bitmapOriginal ,bitmapFinal;

    public Bitmap Draw (Bitmap bitmap,String string, int Int) {
        bitmapOriginal = bitmap;
        int l=bitmapOriginal.getDensity();
        String str1 = Integer.toString(l);
        Log.i("zu" ,str1);
        int w=bitmapOriginal.getWidth();
        int h=bitmapOriginal.getHeight();
        Bitmap bitmap1 = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        Canvas canvas1 = new Canvas(bitmap1);
        canvas1.drawColor(Color.WHITE);


        Canvas canvas = new Canvas(bitmap1);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
      //  int color = ContextCompat.getColor(context, R.color.black);
        Log.i("i","31");
        paint.setColor(Color.BLACK); // Text Color
        paint.setTextSize(Int); //Text Size
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText( string , (bitmap1.getWidth()) / 2, (bitmap1.getWidth()) / 2, paint);
        bitmapFinal= textEffect(bitmapOriginal,bitmap1);
        return bitmapFinal;
    }

    public static Bitmap textEffect(Bitmap image, Bitmap text) {

        Bitmap img = Bitmap.createBitmap(image.getWidth(),
                image.getHeight(),Bitmap.Config.ARGB_8888);

        Log.i("i","47");
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {

                int textPixel = text.getPixel(x,y);
                int imagePixel=image.getPixel(x,y);

                if((textPixel == Color.BLACK) ){
                Log.i("i","hi");
                img.setPixel(x, y,imagePixel);
                }
                else
                    Log.i("i","59");

            }
        }
        return img;
    }

    //Code for phone motion starts here

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private boolean isColor = false;
    private long lastUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Log.i("zu1" ,"hi?98");

        lastUpdate = System.currentTimeMillis();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            Log.i("zu1" ,"hi?111");

            getAccelerometer(event); }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;
        // Movement
        float x = values[0];
        float y = values[1];
        float z = values[2];

        float accelationSquareRoot = (x * x + y * y + z * z)
                / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);

        long actualTime = System.currentTimeMillis();


        if (accelationSquareRoot >= 2) //it will be executed if you shuffle
        {
            if (actualTime - lastUpdate < 200) {
                 return ;
            }
            lastUpdate = actualTime;//updating lastUpdate for next shuffle
            if (isColor){
                Log.i("zu1" ,"hi?");

                bitmapFinal.setDensity(300);
                int l=bitmapOriginal.getDensity();
                String str1 = Integer.toString(l);
                Log.i("zu1" ,str1);
            }
            else {
                bitmapFinal.setDensity(100);
                int l=bitmapOriginal.getDensity();
                String str1 = Integer.toString(l);
                Log.i("zu2" ,str1);
            }
            isColor = !isColor;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // register this class as a listener for the orientation and
        // accelerometer sensors
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }


}
