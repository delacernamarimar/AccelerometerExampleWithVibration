package com.example.accelerometerexample;

import com.example.accelerometerexample.ShakeDetector.OnShakeListener;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ShakeDetector mShakeDetector;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    TextView randomNum;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        randomNum=(TextView) findViewById(R.id.textView1);

        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector(
        		new OnShakeListener() {
            @Override
            public void onShake() {
                // Do stuff!
            	Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
            	long pattern[]={0,800,200,1200,300,2000};
            	v.vibrate(pattern,-1);
            	
            	int random = (int)(Math.random() * (5+1));
          
            	String[] codeNum={"cac42d","db12a4","c2d15s","e21da5","dgh21w","a82s24"};
            	String lucky=codeNum[random];
            	Toast.makeText(MainActivity.this, "the lucky number is "+lucky, Toast.LENGTH_SHORT).show();
            	randomNum.setText(lucky);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }   
}
