package it.unibo.antitheft;

import android.graphics.Color;
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
	
	private TextView textViewTestSensor,textX,textY,textZ,testAccelerometer;
	private SensorManager mSensorManager;
	private Sensor accelerometerSensor;  
	private float[] gravity = new float[3];
	private float[] linear_acceleration = new float[3];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm);
		
		textViewTestSensor = (TextView) findViewById(R.id.text_view_test_accelerometer);
		textX = (TextView) findViewById(R.id.text_view_x);
		textY = (TextView) findViewById(R.id.text_view_y);
		textZ = (TextView) findViewById(R.id.text_view_z);
		testAccelerometer = (TextView) findViewById(R.id.test_accelerometer);
		testAccelerometer.setBackgroundColor(Color.RED);
		
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
	    mSensorManager.registerListener(this, 
	    								accelerometerSensor, 
	    								SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
	    super.onPause();
	    mSensorManager.unregisterListener(this);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		//Kalman filter???
		final float alpha = 0.5f;
		
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			
			// Isolate the force of gravity with the low-pass filter.
			gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
			gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
			gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

			// Remove the gravity contribution with the high-pass filter.
			linear_acceleration[0] = event.values[0] - gravity[0];
			linear_acceleration[1] = event.values[1] - gravity[1];
			linear_acceleration[2] = event.values[2] - gravity[2];
			
		
//			float x=event.values[0];
//			float y=event.values[1];
//			float z=event.values[2];
//			
//			textX.setText("X: "+x);
//			textY.setText("Y: "+y);
//			textZ.setText("Z: "+z);	
			float x = (float) ((Math.floor(linear_acceleration[0]* 100))/100);
			float y = (float) ((Math.floor(linear_acceleration[1]* 100))/100);
			float z = (float) ((Math.floor(linear_acceleration[2]* 100))/100);
			textX.setText("X: " + x);
			textY.setText("Y: " + y);
			textZ.setText("Z: " + z);	
			
			if(x > 1 || x < -1 || y > 1 || y < -1 || z > 1 || z < -1){
				testAccelerometer.setText("ECCO");
			} else {
				testAccelerometer.setText("");
			}
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

}
