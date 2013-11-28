package com.arav.emiornot;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
//import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DisplayActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		// Show the Up button in the action bar.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// Show the Up button in the action bar.
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}

		//Get the intent bundle passed from the calling activity.
		Intent intent = getIntent();

		//Set variables for this intent(screen) by assigning the values passed.
		String setAmount = intent.getStringExtra("amount");
		String setFees = intent.getStringExtra("fees");
		String setRate = intent.getStringExtra("rate");

		//Log.d("RAVISH", "The Rate value inside is: "+setRate);

		String setPeriod = intent.getStringExtra("period");
		String setBankRate = intent.getStringExtra("bankRate");

		//Set values to the display text fields in the screen.
		TextView textViewAmount = (TextView) findViewById(R.id.textView6);
		textViewAmount.setText(setAmount);

		TextView textViewRate = (TextView) findViewById(R.id.textView7);
		textViewRate.setText(setRate);

		TextView textViewFees = (TextView) findViewById(R.id.textView9);
		textViewFees.setText(setFees);

		TextView textViewPeriod = (TextView) findViewById(R.id.textView8);
		textViewPeriod.setText(setPeriod);

		TextView test = (TextView) findViewById(R.id.textView10);
		String sum = calculateSum(setAmount, setRate, setFees, setPeriod, setBankRate);
		test.setText(sum);

		//		Temporary testing with Toast to see the output.
		//		Toast.makeText(getApplicationContext(), setAmount.toString(), Toast.LENGTH_SHORT).show();

		//Start the action.
		setupActionBar();
	}

	//Temporary method to calculate sum of all the values passed - Testing purpose
	@SuppressLint("DefaultLocale")
	private String calculateSum(String setAmount, String setRate,
			String setFees, String setPeriod, String setBankRate) {

		int finalSum = 0;

		int amount = Integer.parseInt(setAmount);
		int fees = Integer.parseInt(setFees);
		int period = Integer.parseInt(setPeriod);
		int rate = Integer.parseInt(setRate);
		int bankRate = Integer.parseInt(setBankRate);

		finalSum = amount+rate+period+fees+bankRate;

		//Converting sum to a String to display.
		//Variable sum will be used only to display the desired result. Does not have any mathematical meaning.

		String sum = Integer.toString(finalSum);

		//Test returning value from calculation engine

		CalculationEngine ce = new CalculationEngine();

		//Get PV
		//		double pv = ce.calculatePV(amount, rate, period, bankRate);
		//		sum = Double.toString(pv);

		//Get EMI
		double emi = ce.calculateEMI(amount, rate, period, bankRate);
		sum = Double.toString(emi);

		//Get Final FV
		double FVSum = ce.calculateFV(emi, rate, period, bankRate);
		//sum = Double.toString(FVSum);

		//Round-down the display amount
		sum = String.format("%.2f", FVSum);

		//		double totalPayment = emi*period;
		//		double result = totalPayment-pv;
		//		sum = Double.toString(result);

		//Analysis Part

		double totalPaymentMade = emi*period;

		if(FVSum>totalPaymentMade){
			sum = "Analysis Result: Take the EMI Option. You'll save $"+String.format("%.2f",(FVSum-totalPaymentMade));
		}
		else if(FVSum<totalPaymentMade){
			sum = "Analysis Result: Use your cash and pay it. EMI will set you back by $"+String.format("%.2f",(totalPaymentMade-FVSum));
		}
		else{
			sum = "Both options seem Financially correct!";
		}

		return sum;
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
