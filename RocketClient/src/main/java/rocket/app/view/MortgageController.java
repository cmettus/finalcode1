package rocket.app.view;

import java.net.URL;
import java.util.ResourceBundle;

import eNums.eAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketBase.RateBLL;
import rocketCode.Action;
import rocketData.LoanRequest;


public class MortgageController implements Initializable{

	private MainApp mainApp;


	private double txtIncome;
	private double txtExpenses;
	private int txtCreditScore;
	private double txtHouseCost;
	private int term;



	@FXML
	private Label incomeLabel;

	@FXML
	private Label creditscoreLabel;

	@FXML
	private Label termLable;

	@FXML
	private Label expenseLabel;

	@FXML
	private Label housecostLabel;

	@FXML
	private Label mortgageLabel;

	@FXML
	private Label tooexpensiveLabel;


	//TestFields
	@FXML
	private TextField incomeTextField;

	@FXML
	private TextField expenseTextField;

	@FXML
	private TextField creditscoreTextField;

	@FXML
	private TextField housecostTextField;

	//ComboBox
	@FXML
	private ComboBox<Integer> termComboBox;



	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tooexpensiveLabel.setVisible(false);
		termComboBox.getItems().addAll(15, 30);
	}



	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Object message = null;


		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();


		lq.setIncome(Double.valueOf(incomeTextField.getText()));
		lq.setExpenses(Double.valueOf(expenseTextField.getText()));
		lq.setiTerm(Integer.valueOf(termComboBox.getValue()));
		lq.setiCreditScore(Integer.valueOf(creditscoreTextField.getText()));
		lq.setdAmount(Double.valueOf(housecostTextField.getText()));
		lq.setdPayment(RateBLL.getPayment(lq.getdRate()/1200, lq.getiTerm()*12, lq.getdAmount(), 0, false)/(lq.getiTerm()*12));

		a.setLoanRequest(lq);


		mainApp.messageSend(lq);
	}

	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{

		double payment = Math.abs(lRequest.getdPayment());

		String finalResult = String.format("%1$.02f", String.valueOf(payment));

		if (lRequest.getiTerm() == 30 && payment <  (lRequest.getIncome() -lRequest.getExpenses() * 0.28)
				|| lRequest.getiTerm() == 15 && payment <  (lRequest.getIncome() -lRequest.getExpenses())*0.28) {

			mortgageLabel.setText("The mouthly payment is $" + finalResult);
		} else {
			mortgageLabel.setVisible(false);
			tooexpensiveLabel.setVisible(true);
			tooexpensiveLabel.setText("House cost is too high.");
		}


	}


}