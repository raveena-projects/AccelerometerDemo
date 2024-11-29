package com.example.accelerometerdemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView x,y,z;
    private Sensor acc;
    private SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        x =findViewById(R.id.x_value);
        y =findViewById(R.id.y_value);
        z =findViewById(R.id.z_value);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorManager!= null){
            acc = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,acc,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x_val = sensorEvent.values[0];
        float y_val = sensorEvent.values[1];
        float z_val = sensorEvent.values[2];
        x.setText("X = "+x_val);
        y.setText("Y = "+y_val);
        z.setText("Z = "+z_val);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}