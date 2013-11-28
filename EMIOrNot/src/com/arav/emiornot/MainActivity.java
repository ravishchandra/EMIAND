package com.arav.emiornot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
		text = text+editTextRate.getText().toString();

		EditText editTextPeriod = (EditText)findViewById(R.id.period);
		text = text+editTextPeriod.getText().toString();
		//String strPeriod = editTextPeriod.getText().toString();

		EditText editTextFees = (EditText)findViewById(R.id.fees);
		text = text+editTextFees.getText().toString();


		EditText editTextBankRate = (EditText)findViewById(R.id.bankRate);
		text=text+editTextBankRate.getText().toString();

		//Seems like useless junk - setError. Update: Works with return statement.
		if (editTextAmount.getText().toString().trim().length()==0){
			editTextAmount.setError("Amount cannot be left blank!");
			editTextAmount.requestFocus();
			return;
		}
		if (validateEndSign(editTextAmount.getText().toString())){
			editTextAmount.setError("Are you missing something?");
			editTextAmount.requestFocus();
			return;
		}
		if (editTextAmount.getText().toString().startsWith("+")){
			editTextAmount.setText(editTextAmount.getText().toString().substring(1));
		}
		int intAmount = Integer.parseInt(editTextAmount.getText().toString());
		if (intAmount<1){
			editTextAmount.setError("Amount cannot be 0 or negative!");
			editTextAmount.requestFocus();
			return;
		}
		if (editTextRate.getText().toString().trim().length()==0){
			editTextRate.setError("Rate cannot be left blank!");
			editTextRate.requestFocus();
			return;
		}
		if (validateEndSign(editTextRate.getText().toString())){
			editTextRate.setError("Are you missing something?");
			editTextRate.requestFocus();
			return;
		}
		if (editTextRate.getText().toString().startsWith("+")){
			editTextRate.setText(editTextRate.getText().toString().substring(1));
		}
		int intRate = Integer.parseInt(editTextRate.getText().toString());
		if (intRate<0){
			editTextRate.setError("Rate cannot be negative!");
			editTextRate.requestFocus();
			return;
		}
		if (editTextPeriod.getText().toString().trim().length()==0){
			editTextPeriod.setError("Period cannot be left blank!");
			editTextPeriod.requestFocus();
			return;
		}
		if (validateEndSign(editTextPeriod.getText().toString())){
			editTextPeriod.setError("Are you missing something?");
			editTextPeriod.requestFocus();
			return;
		}
		if (editTextPeriod.getText().toString().startsWith("+")){
			editTextPeriod.setText(editTextPeriod.getText().toString().substring(1));
		}
		int intPeriod = Integer.parseInt(editTextPeriod.getText().toString());
		if (intPeriod<1){
			editTextPeriod.setError("Period cannot be 0 or negative!");
			editTextPeriod.requestFocus();
			return;
		}
		if (editTextBankRate.getText().toString().trim().length()==0){
			editTextBankRate.setError("Bank Rate cannot be left blank!");
			editTextBankRate.requestFocus();
			return;
		}
		if (validateEndSign(editTextBankRate.getText().toString())){
			editTextBankRate.setError("Are you missing something?");
			editTextBankRate.requestFocus();
			return;
		}
		if (editTextBankRate.getText().toString().startsWith("+")){
			editTextBankRate.setText(editTextBankRate.getText().toString().substring(1));
		}
		int intBankRate = Integer.parseInt(editTextBankRate.getText().toString());
		if (intBankRate<0){
			editTextBankRate.setError("Bank Rate cannot be negative!");
			editTextBankRate.requestFocus();
			return;
		}
		if (validateEndSign(editTextFees.getText().toString())){
			editTextFees.setError("Are you missing something?");
			editTextFees.requestFocus();
			return;
		}
		if (editTextFees.getText().toString().startsWith("+")){
			editTextFees.setText(editTextFees.getText().toString().substring(1));
		}
		if (editTextFees.getText().toString().startsWith("-")){
			editTextFees.setError("A negative fee? Really?");
			editTextFees.requestFocus();
			return;
		}

		//		//Temporary validations. Need to convert them to Courton.
		//		//Validation for Period
		//		if (strPeriod.equals("0")){
		//			Toast.makeText(getApplicationContext(), "Period cannot be 0!",
		//					Toast.LENGTH_LONG).show();
		//			return;
		//		}
		//		//Validation for Rate
		//		if (editTextRate.getText().toString().trim().length()<1){
		//			Toast.makeText(getApplicationContext(), "Rate cannot be left blank!",
		//					Toast.LENGTH_LONG).show();
		//			return;
		//		}

		Intent intent = new Intent(this, DisplayActivity.class);
		String amount = editTextAmount.getText().toString();
		String fees;
		String rate = editTextRate.getText().toString();
		String period = editTextPeriod.getText().toString();
		String bankRate = editTextBankRate.getText().toString();
		if (editTextFees.getText().toString().trim().length()==0){
			fees="0";
		}
		else {
			fees = editTextFees.getText().toString();
		}

		//		Log.d("RAVISH", "The Rate value inside MainActivity - assignment is: "+rate);

		//Put variables in the intent bundle
		intent.putExtra("amount", amount);
		intent.putExtra("fees", fees);
		intent.putExtra("rate", rate);
		//		Log.d("RAVISH", "The Rate value inside MainActivity is: "+rate);
		intent.putExtra("period", period);
		intent.putExtra("bankRate", bankRate);

		//Start the new screen, intent
		startActivity(intent);

	}

	private boolean validateEndSign(String inputEditText){
		if (inputEditText.endsWith("+") || inputEditText.endsWith("-")){
			return true;
		}
		else 
			return false;
	}

	//	private String excludePostiveSign(String inputEditText){
	//		String outputEditText;
	//		if (inputEditText.startsWith("+")){
	//			outputEditText = inputEditText.substring(1);
	//			return outputEditText;
	//		}
	//		return inputEditText;
	//	}

}
