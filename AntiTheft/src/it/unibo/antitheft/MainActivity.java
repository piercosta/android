package it.unibo.antitheft;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	protected Button btnAlarmActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnAlarmActivity = (Button) findViewById(R.id.button2_alarm_activity);
		btnAlarmActivity.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				openAlarmActivity();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/** Called when the user clicks the Send button */
	public void openTrackingActivity() {
		Intent intent = new Intent(this, TrackingActivity.class);
	    startActivity(intent);
	}
	public void openAlarmActivity() {
		Intent intent = new Intent(this, AlarmActivity.class);
	    startActivity(intent);
	}

}
