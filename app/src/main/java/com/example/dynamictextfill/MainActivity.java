package com.example.dynamictextfill;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dynamic_text_fill.DynamicTextFill;


public class MainActivity extends AppCompatActivity  {

    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();


    String Text,Text2;
    ImageView Image;
    Button displayText;
    EditText simpleEditText,simpleEditText2;
    Bitmap bitmapDisplay;
    Bitmap icon;
    DynamicTextFill m =new DynamicTextFill();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    Log.i("i","38");
         icon = BitmapFactory.decodeResource(getApplication().getResources(),
                R.drawable.sparkles);
        simpleEditText = (EditText) findViewById(R.id.simpleEditText);
        simpleEditText2 = (EditText) findViewById(R.id.simpleEditText2);

        displayText = (Button) findViewById(R.id.displayText);
        Image=(ImageView)findViewById(R.id.image);

        Log.i("i","42");

        displayText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (simpleEditText.getText().toString() != null)
                {
                    Text = simpleEditText.getText().toString();
                    Text2=simpleEditText2.getText().toString();
                    int i=Integer.parseInt(Text2);
                    bitmapDisplay= m.Draw(icon,Text,i);
                    Image.setImageBitmap(bitmapDisplay);
                    Log.i("i","65");
                }
            }
        });



    }
}

