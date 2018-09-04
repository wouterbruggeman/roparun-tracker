package nl.hr.roparun_tracker;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;

public class MainActivity extends WearableActivity implements SensorEventListener{

    TextView heartrateText;
    SensorManager mSensorManager;
    Sensor mHeartRateSensor;
    SensorEventListener sensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Enables Always-on
        setAmbientEnabled();

        //Create text view
        heartrateText = findViewById(R.id.heartrateTextId);

        //Create sensor managers etc.
        mSensorManager = ((SensorManager) getSystemService(SENSOR_SERVICE));
        mHeartRateSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        mSensorManager.registerListener(this, mHeartRateSensor, SensorManager.SENSOR_DELAY_NORMAL);

        //Set sensor delay
        mSensorManager.registerListener(sensorEventListener, mHeartRateSensor, mSensorManager.SENSOR_DELAY_FASTEST);

        heartrateText.setText("Heartrate should show up here...");
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        if (event.sensor.getType() == Sensor.TYPE_HEART_RATE){
            String heartrate = "Heartrate: " + (int) event.values[0];
            heartrateText.setText(heartrate);
        }else{
            heartrateText.setText("Sensor not working...");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        heartrateText.setText("Accuracy changed!");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
