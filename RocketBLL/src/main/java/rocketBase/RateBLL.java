package rocketBase;

import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.*;

import rocketDomain.RateDomainModel;

import exceptions.RateException;
import rocketData.LoanRequest;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	public static double getRate(int GivenCreditScore) throws RateException
	{

		
		ArrayList<RateDomainModel> houseRates = RateDAL.getAllRates();
		
		double givenRate = 0;
		for(int j = 0; j<houseRates.size(); j++){
			if(GivenCreditScore >= houseRates.get(j).getiMinCreditScore()){
				givenRate = 0.01*houseRates.get(j).getdInterestRate(); //makes a percent
			}
		}
		
		if(givenRate ==0){
			RateException notQualified = new RateException(houseRates.get(0)); 
			return 0;
		}

		else{
			return givenRate;
		}
		
		
	}
	
	
	
	public static double getPayment(double r, double n, double p, double f, boolean t)
	{		
		return FinanceLib.pmt(r/12, n, p, f, t)*(-1.0);
	}
	
		public static boolean IncomeCheck(LoanRequest lq){
			boolean check=false;
			if((((lq.getIncome()/12*.28)>lq.getdPayment())&&((((lq.getIncome()/12)-lq.getExpenses())*.36)>lq.getdPayment())))
					check=true;
			return check;
		}
}

