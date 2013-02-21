package com.arav.emiornot;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "come.arav.emiornot.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void analyzeEMI(View view){
		//Analyze the EMI. Take objects from other classes for main

		//Testing. Display the form input details
		Context context = getApplicationContext();
		CharSequence text = "Test!";
		int duration = Toast.LENGTH_SHORT;

		//Try to club it all together
		//String[] inputArray = null;

		EditText editTextAmount = (EditText)findViewById(R.id.amount);
		text = editTextAmount.getText().toString();
		//inputArray[0] = text;

		EditText editTextRate = (EditText)findViewById(R.id.rate);
		text = text+editTextRate.getText().toString();

		EditText editTextPeriod = (EditText)findViewById(R.id.period);
		text = text+editTextPeriod.getText().toString();

		EditText editTextFees = (EditText)findViewById(R.id.fees);
		text=text+editTextFees.getText().toString();

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();

	}

}
