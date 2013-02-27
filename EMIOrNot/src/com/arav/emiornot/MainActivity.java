package com.arav.emiornot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	//	public final static String EXTRA_AMOUNT = "come.arav.emiornot.MESSAGE";
	//	public final static String EXTRA_PERIOD = "come.arav.emiornot.MESSAGE";
	//	public final static String EXTRA_RATE = "come.arav.emiornot.MESSAGE";
	//	public final static String EXTRA_FEE = "come.arav.emiornot.MESSAGE";

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
		//		Context context = getApplicationContext();
		CharSequence text = "Test!";
		//		int duration = Toast.LENGTH_SHORT;

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

		//		Toast toast = Toast.makeText(context, text, duration);

		Intent intent = new Intent(this, DisplayActivity.class);
		//		Bundle extras = new Bundle();
		//		editTextAmount = (EditText) findViewById(R.id.amount);
		String amount = editTextAmount.getText().toString();

		//		editTextFees = (EditText) findViewById(R.id.fees);
		String fees = editTextFees.getText().toString();

		//		editTextRate = (EditText) findViewById(R.id.rate);
		String rate = editTextRate.getText().toString();

		//		editTextPeriod = (EditText) findViewById(R.id.period);
		String period = editTextPeriod.getText().toString();

		//put variables in the bundle
		intent.putExtra("amount", amount);
		intent.putExtra("fees", fees);
		intent.putExtra("rate", rate);
		intent.putExtra("period", period);

		//pass the bundle to the intent

		//		intent.putExtras(extras);

		//		Toast.makeText(context, message, duration);
		//		toast.show();

		startActivity(intent);

	}

}
