package it.unibo.antitheft;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.TextView;

public class AlarmActivity extends Activity implements SensorEventListener {
	
	private TextView textViewTestSensor,textX,textY,textZ;
	private SensorManager mSensorManager;
	private Sensor accelerometerSensor;  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm);
		
		textViewTestSensor = (TextView) findViewById(R.id.text_view_test_accelerometer);
		textX = (TextView) findViewById(R.id.text_view_x);
		textY = (TextView) findViewById(R.id.text_view_y);
		textZ = (TextView) findViewById(R.id.text_view_z);
		
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		
		if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
			textViewTestSensor.setText("Accelerometer detected");
			accelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		}
		else {
			textViewTestSensor.setText("Accelerometer not detected");
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alarm, menu);
		return true;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	    mSensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
	    super.onPause();
	    mSensorManager.unregisterListener(this);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			float x=event.values[0];
			float y=event.values[1];
			float z=event.values[2];
			
			textX.setText("X: "+x);
			textY.setText("Y: "+y);
			textZ.setText("Z: "+z);		
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

}
