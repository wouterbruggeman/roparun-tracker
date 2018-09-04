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
    TextView pedometerText;
    SensorManager mSensorManager;
    Sensor mHeartRateSensor;
    Sensor mStepCounterSensor;

    SensorEventListener sensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Enables Always-on
        setAmbientEnabled();

        //Create text views
        heartrateText = findViewById(R.id.heartrateTextId);
        pedometerText = findViewById(R.id.stepCounterTextId);

        //Create sensor managers etc.
        mSensorManager = ((SensorManager) getSystemService(SENSOR_SERVICE));
        mHeartRateSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        mStepCounterSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        //Set sensor delay
        mSensorManager.registerListener(this, mHeartRateSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        switch(event.sensor.getType()){
            case Sensor.TYPE_HEART_RATE:
                String heartrate = "Heartrate: " + (int) event.values[0];
                heartrateText.setText(heartrate);
                break;
            case Sensor.TYPE_STEP_COUNTER:
                String stepCount = "Step Count: " + (int) event.values[0];
                pedometerText.setText(stepCount);
                break;
            default:
                break;
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
