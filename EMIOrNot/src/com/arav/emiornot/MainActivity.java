package com.arav.emiornot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	//	Final variables to use when needed
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

		//Declare text variable to club all the TextView values together to be sent to intent.
		CharSequence text;

		//Add each variable by declaring and then adding it to the text.
		EditText editTextAmount = (EditText)findViewById(R.id.amount);
		text = editTextAmount.getText().toString();

		EditText editTextRate = (EditText)findViewById(R.id.rate);
		if (editTextRate.getText().toString().length()==0){
			editTextRate.setError("Rate field cannot be left blank!");
		}
		text = text+editTextRate.getText().toString();


		EditText editTextPeriod = (EditText)findViewById(R.id.period);
		text = text+editTextPeriod.getText().toString();

		EditText editTextFees = (EditText)findViewById(R.id.fees);
		text=text+editTextFees.getText().toString();

		EditText editTextBankRate = (EditText)findViewById(R.id.bankRate);
		text=text+editTextBankRate.getText().toString();


		//TODO Handle exception for null values entered.

		Intent intent = new Intent(this, DisplayActivity.class);
		String amount = editTextAmount.getText().toString();
		String fees = editTextFees.getText().toString();
		String rate = editTextRate.getText().toString();
		String period = editTextPeriod.getText().toString();
		String bankRate = editTextBankRate.getText().toString();

		Log.d("RAVISH", "The Rate value inside MainActivity - assignment is: "+rate);

		//Put variables in the intent bundle
		intent.putExtra("amount", amount);
		intent.putExtra("fees", fees);
		intent.putExtra("rate", rate);
		Log.d("RAVISH", "The Rate value inside MainActivity is: "+rate);
		intent.putExtra("period", period);
		intent.putExtra("bankRate", bankRate);

		//Start the new screen, intent
		startActivity(intent);

	}

}
