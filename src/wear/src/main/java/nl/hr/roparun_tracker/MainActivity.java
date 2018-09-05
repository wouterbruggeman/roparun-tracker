package nl.hr.roparun_tracker;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.support.wearable.activity.WearableActivity;
import android.widget.Button;
import android.widget.TextView;
import android.os.Vibrator;

public class MainActivity extends WearableActivity implements SensorEventListener{

    TextView heartrateText;
    TextView stepCounterText;
    SensorManager mSensorManager;
    Sensor mHeartRateSensor;
    Sensor mStepCounterSensor;

    SensorEventListener sensorEventListener;
    int calibrationStepCount = -1;
    Button secondButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Enables Always-on
        //setAmbientEnabled();

        //Create text views
        heartrateText = findViewById(R.id.heartrateTextId);
        stepCounterText = findViewById(R.id.stepCounterTextId);

        //Create sensor managers etc.
        mSensorManager = ((SensorManager) getSystemService(SENSOR_SERVICE));
        mHeartRateSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        mStepCounterSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        //Set sensor delay

        mSensorManager.registerListener(this, mHeartRateSensor, mSensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mStepCounterSensor, mSensorManager.SENSOR_DELAY_FASTEST);


        //Create button and listener
        secondButton = findViewById(R.id.testButton);
        /*secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSecondWindow = new Intent(getApplicationContext(), secondActivity.class);
                startActivity(goToSecondWindow);
            }
        });*/

        this.showHeartRate(-1);
        this.showStepCount(-1);
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        switch(event.sensor.getType()){
            case Sensor.TYPE_HEART_RATE:
                this.showHeartRate((int)event.values[0]);
                break;
            case Sensor.TYPE_STEP_COUNTER:
                this.showStepCount((int)event.values[0]);
                break;
            default:
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //heartrateText.setText("Accuracy changed!");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //Show the heartrate
    private void showHeartRate(int heartrate){
        String heartrateString = "Heartrate: " + heartrate;
        heartrateText.setText(heartrateString);
    }

    //Show the stepCount
    private void showStepCount(int stepCount) {
        //Calibrate the value
        if(calibrationStepCount == -1){
            calibrationStepCount = stepCount;
        }
        stepCount = stepCount - calibrationStepCount;

        //Show the value
        String stepCountString = "Step Count: " + stepCount;
        stepCounterText.setText(stepCountString);
    }
}
