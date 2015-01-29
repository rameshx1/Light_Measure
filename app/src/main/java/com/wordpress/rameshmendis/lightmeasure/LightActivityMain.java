package com.wordpress.rameshmendis.lightmeasure;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.app.Activity;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.net.URLEncoder;

import static android.os.SystemClock.elapsedRealtime;


public class LightActivityMain extends ActionBarActivity implements SensorEventListener  {

    private SensorManager mSensorManager; //Sensor Manager object for accelerometer
    private Sensor mLight; //Accelerometer object
    float luxNum = 0;
    final String myTag = "DocsUpload";
    TextView lux_level;
    boolean timeReady = false;
    long timeWait=0;
    long time=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_activity_main);

        lux_level = (TextView)LightActivityMain.this.findViewById(R.id.lux_level);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);//Initialise the accelerometer
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorManager.registerListener(this, mLight,SensorManager.SENSOR_DELAY_NORMAL);

    }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
                lux_level.setText("" + event.values[0]);
                luxNum = event.values[0];
                pushData(luxNum);
            }
        }


    protected void pushData(float value1){

        String fullUrl1 = "https://docs.google.com/forms/d/1-gO-8MAuuNmvz3v3Lw-Z7JjeV-oz-esoPmNaWN8Ri_Q/formResponse"; //Form Entry
       // HttpRequest mReq = new HttpRequest(); //Instantiate HTTP Request
        String col1 = Float.toString(value1);
        String data1 = "entry_1169134425=" + URLEncoder.encode(col1); //Entry Field
        //mReq.sendPost(fullUrl1, data1);
        new HttpRequest().execute(fullUrl1,data1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_light_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
