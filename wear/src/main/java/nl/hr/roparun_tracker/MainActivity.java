package nl.hr.roparun_tracker;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;

public class MainActivity extends WearableActivity {

    private TextView mTextView;
    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView hometext = (TextView) findViewById(R.id.hometext);
        hometext.setText("Please wait...");
        

        hometext.setText();

        // Enables Always-on
        setAmbientEnabled();
    }
}
