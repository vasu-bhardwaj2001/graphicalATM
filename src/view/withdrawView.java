	package view;

	import java.awt.CardLayout;
	import java.awt.Image;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.io.File;
	import java.io.IOException;
	import java.io.ObjectOutputStream;

	import javax.imageio.ImageIO;
	import javax.swing.ImageIcon;
	import javax.swing.JButton;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;
import data.Database;
import model.BankAccount;

import controller.ViewManager;

public class withdrawView extends JPanel implements ActionListener {
	private ViewManager manager;			// manages interactions between the views, model, and database
	private JButton submitButton;
	private JButton cancelButton;
	private JButton powerButton;			// button that powers off the ATM
	private JTextField inputfield;
	private JLabel errorMessageLabel;		// label for potential error messages
	public BankAccount Account = null;
	
	public withdrawView(ViewManager manager) {
		super();
		this.manager = manager;
		this.errorMessageLabel = new JLabel("", SwingConstants.CENTER);
	}
	
	private void initialize() {
		this.setLayout(null);
		JPanel log = new JPanel(new CardLayout());
		JLabel info = new JLabel ("Your name is " + Account.getUser().getFirstName() + ' ' + Account.getUser().getLastName() + "."); 
		info.setBounds(200, 0, 300, 10);
		JLabel accountnum = new JLabel("Your Account Number is " + Account.getAccountNumber() + ".");
		accountnum.setBounds(200, 15, 300, 10);
		JLabel balance = new JLabel("Your Current Balance is " + Account.getBalance() + ".");
		balance.setBounds(200, 30, 300, 10);
		submitButton = new JButton("SUBMIT");
		submitButton.setBounds(100, 300, 100, 50);
		submitButton.addActionListener(this);
		
		cancelButton = new JButton("CANCEL");
		cancelButton.setBounds(200, 300, 100, 50);
		cancelButton.addActionListener(this);
		
		//powerButton = new JButton();
		JLabel deposit = new JLabel("Enter Withdrawl Amount:");
		deposit.setBounds(200,200,100,50);
		inputfield = new JTextField();
		inputfield.setBounds(100, 150, 200, 50);
		this.add(info); 
		this.add(accountnum); 
		this.add(balance); 
		this.add(deposit);
		this.add(submitButton);
		this.add(cancelButton);
		this.add(inputfield);
		this.add(new javax.swing.JLabel("DepositView", javax.swing.SwingConstants.CENTER));
	}
	public void updateErrorMessage(String errorMessage) {
		errorMessageLabel.setText(errorMessage);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Database Database = new Database();
		Object source = e.getSource();
		
		if (source.equals(cancelButton)) {
			inputfield.setText(null);
		} 
		if (source.equals(powerButton)) {
			manager.shutdown();
		}
		if (source.equals(submitButton)) {
			int add = Integer.parseInt(inputfield.getText());
			Account.removeBalance(add);
			manager.switchTo(ATM.HOME_VIEW);
		}

		
	}
	public void setCurrentAccount(BankAccount Account) {
		this.Account = Account;
		initialize();
		
		JPanel views = new JPanel(new CardLayout());
		ViewManager manager = new ViewManager(views);
		
		// add child views to the parent container

		
	}
	
}