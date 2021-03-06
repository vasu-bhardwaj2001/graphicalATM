package controller;

import java.awt.CardLayout;
import java.awt.Container;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import data.Database;
import model.BankAccount;
import view.ATM;
import view.DepositView;
import view.EditView;
import view.HomeView;
import view.InformationView;
import view.LoginView;
import view.transferView;
import view.withdrawView;

public class ViewManager {
	
	private Container views;				// the collection of all views in the application
	private Database db;					// a reference to the database
	private BankAccount account;			// the user's bank account
	private BankAccount destination;		// an account to which the user can transfer funds
	
	/**
	 * Constructs an instance (or object) of the ViewManager class.
	 * 
	 * @param layout
	 * @param container
	 */
	
	public ViewManager(Container views) {
		this.views = views;
		this.db = new Database();
	}
	
	///////////////////// INSTANCE METHODS ////////////////////////////////////////////
	
	/**
	 * Routes a login request from the LoginView to the Database.
	 * 
	 * @param accountNumber
	 * @param pin
	 */
	
	public void login(String accountNumber, char[] pin) {
		account = db.getAccount(Long.valueOf(accountNumber), Integer.valueOf(new String(pin)));
		
		if (account == null) {
			LoginView lv = ((LoginView) views.getComponents()[ATM.LOGIN_VIEW_INDEX]);
			lv.updateErrorMessage("Invalid account number and/or PIN.");
		} else {
			switchTo(ATM.HOME_VIEW);
			HomeView hv = ((HomeView) views.getComponents()[ATM.HOME_VIEW_INDEX]);
			hv.setCurrentAccount(account);
			
			LoginView lv = ((LoginView) views.getComponents()[ATM.LOGIN_VIEW_INDEX]);
			lv.updateErrorMessage("");
			
			DepositView dv = ((DepositView) views.getComponents()[ATM.DEPOSIT_VIEW_INDEX]);
			dv.setCurrentAccount(account);
			dv.updateErrorMessage("");
			
			withdrawView wv = ((withdrawView) views.getComponents()[ATM.WITHDRAW_VIEW_INDEX]);
			wv.setCurrentAccount(account);
			wv.updateErrorMessage("");
			
			transferView tv = ((transferView) views.getComponents()[ATM.TRANSFER_VIEW_INDEX]);
			tv.setCurrentAccount(account);
			tv.updateErrorMessage("");
			
			InformationView iv = ((InformationView) views.getComponents()[ATM.INFORMATION_VIEW_INDEX]);
			iv.setCurrentAccount(account);
			iv.updateErrorMessage("");
			
			EditView eiv = ((EditView) views.getComponents()[ATM.EDIT_VIEW_INDEX]);
			eiv.setCurrentAccount(account);
			eiv.updateErrorMessage("");
			
			//withdrawView wv = ((withdrawView) views.getComponents()[ATM.WITHDRAW_VIEW_INDEX]);
			//wv.setCurrentAccount(account);
			//wv.updateErrorMessage("");
		}
	}
	
	/**
	 * Switches the active (or visible) view upon request.
	 * 
	 * @param view
	 */
	
	public void switchTo(String view) {
		((CardLayout) views.getLayout()).show(views, view);
	}
	
	/**
	 * Routes a shutdown request to the database before exiting the application. This
	 * allows the database to clean up any open resources it used.
	 */
	
	public void shutdown() {
		try {			
			int choice = JOptionPane.showConfirmDialog(
				views,
				"Are you sure?",
				"Shutdown ATM",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE
			);
			
			if (choice == 0) {
				db.shutdown();
				System.exit(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logout() {
		boolean x = db.closeAccount(account);
		if (x) {
			ViewManager ViewManager = new ViewManager(views);
			ViewManager.switchTo(ATM.LOGIN_VIEW);

		}
		else {
			System.out.println("Error");
		}
		
	}
}
