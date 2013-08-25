package com.arav.emiornot;

public class CalculationEngine {

	//Implement the case for 0% interest rate offered and the assumed interest rate lost (bank) is 4%

	public Double calculateEMI(int amount, int rate, int period, int bankRate){

		double EMI = 11.02;
		double x = 1200;
		double rateComponent = rate/x;
		//		double bankRateComponent = bankRate/x;

		if (rate != 0){
			EMI = (amount*rateComponent)*(Math.pow((1+rateComponent),period)/(Math.pow((1+rateComponent),period)-1));
		}
		else {
			EMI = amount/period;
		}

		//		double FVSum = 0.00;
		//
		//		for (int i=1; i<=period; i++){
		//			FVSum = FVSum + (EMI*(Math.pow((1+bankRateComponent),i)));
		//		}

		return EMI;

		//This is fine now for calculating EMI.
	}

	public Double calculateFV(double EMI, int rate, int period, int bankRate){

		double x = 1200;
		double bankRateComponent = bankRate/x;
		double FVSum = 0.00;

		for (int i=1; i<=period; i++){
			FVSum = FVSum + (EMI*(Math.pow((1+bankRateComponent),i)));
		}
		return FVSum;
	}

	public Double calculatePV(int amount, int rate, int period, int bankRate){

		//		double EMI = 11.02;
		double PV = 11.02;
		double x = 1200;
		double rateComponent = rate/x;
		//		EMI = (amount*rateComponent)*(Math.pow((1+rateComponent),period)/(Math.pow((1+rateComponent),period)-1));

		PV=amount*(1-(1/(Math.pow((1+rateComponent),period))))/rateComponent;

		//Cleanup WIP.

		return PV;
	}

}
