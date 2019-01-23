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

import controller.ViewManager;
import data.Database;
import model.BankAccount;

@SuppressWarnings("serial")
public class HomeView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	private JButton logoutButton;	
	private JButton powerButton;
	private JButton deposit;
	private JButton withdraw;
	private JButton transfer;
	private JButton information;
	private JLabel info;
	private JLabel accountnum;
	public JLabel balance;
	public JLabel balance2;
	public BankAccount Account = null;
	/**
	 * Constructs an instance (or objects) of the HomeView class.
	 * 
	 * @param manager
	 */
	
	public HomeView(ViewManager manager) {
		super();
		
		this.manager = manager;
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Initializes the HomeView components.
	 */
	
	private void initialize() {
		this.setLayout(null);
		initPowerButton();
		JPanel log = new JPanel(new CardLayout());
		ViewManager manager = new ViewManager(log);
		initLogoutButton();
		//initUserinfo();
		info = new JLabel ("Your name is " + Account.getUser().getFirstName() + ' ' + Account.getUser().getLastName() + "."); 
		info.setBounds(200, 0, 300, 10);
		accountnum = new JLabel("Your Account Number is " + Account.getAccountNumber() + ".");
		accountnum.setBounds(200, 15, 300, 10);
		this.add(info); 
		this.add(accountnum); 
		balance = new JLabel("Your Current Balance is " + Account.getBalance() + ".");
		balance.setBounds(200, 30, 300, 10);
		this.add(balance); 
		
		deposit = new JButton ("Deposit Money");
		deposit.setBounds(100, 150, 150, 50);
		deposit.addActionListener(this);
		
		withdraw = new JButton ("Withdraw Money");
		withdraw.setBounds(100, 250, 150, 50);
		withdraw.addActionListener(this);
		
		transfer = new JButton ("Transfer Money");
		transfer.setBounds(300, 150, 150, 50);
		transfer.addActionListener(this);
		
		information = new JButton ("User Info");
		information.setBounds(300, 250, 150, 50);
		information.addActionListener(this);
		
		
		this.add(deposit);
		this.add(withdraw);
		this.add(transfer);
		this.add(information);
		this.add(new javax.swing.JLabel("HomeView", javax.swing.SwingConstants.CENTER));
		  
		// TODO
		//
		// this is where you should build the HomeView (i.e., all the components that
		// allow the user to interact with the ATM - deposit, withdraw, transfer, etc.).
		//
		// feel free to use my layout in LoginView as an example for laying out and
		// positioning your components.
	}
	
	public void initUserinfo() {
		//DepositView DepositView = new DepositView(manager);
		balance.setText(null);
		balance2 = new JLabel("Your Current Balance is " + Account.getBalance() + ".");
		balance2.setBounds(200, 30, 300, 10);
		this.add(balance2);
		
		
	}
	
	public void clearUserInfo() {
		balance = new JLabel("");
		balance.setBounds(200, 30, 300, 10);
		this.add(balance); 
	}

	/*
	 * HomeView is not designed to be serialized, and attempts to serialize will throw an IOException.
	 * 
	 * @param oos
	 * @throws IOException
	 */
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The HomeView class is not serializable.");
	}
	private void initLogoutButton() {	
		logoutButton = new JButton("Logout");
		logoutButton.setBounds(200, 400, 100, 35);
		logoutButton.addActionListener(this);
		
		this.add(logoutButton);
	}
	
	
	private void initPowerButton() {
		powerButton = new JButton();
		powerButton.setBounds(5, 5, 50, 50);
		powerButton.addActionListener(this);
		
		try {
			Image image = ImageIO.read(new File("images/power-off.png"));
			powerButton.setIcon(new ImageIcon(image));
		} catch (Exception e) {
			powerButton.setText("OFF");
		}
		
		this.add(powerButton);
	}
	///////////////////// OVERRIDDEN METHODS //////////////////////////////////////////
	
	/*
	 * Responds to button clicks and other actions performed in the HomeView.
	 * 
	 * @param e
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		
		if (source.equals(logoutButton)) {
			manager.logout();
		} 
		if (source.equals(powerButton)) {
			manager.shutdown();
		}
		if (source.equals(deposit)) {
			clearUserInfo();
			manager.switchTo(ATM.DEPOSIT_VIEW);
		}
		if (source.equals(withdraw)) {
			clearUserInfo();
			manager.switchTo(ATM.WITHDRAW_VIEW);
		}
		if (source.equals(transfer)) {
			clearUserInfo();
			manager.switchTo(ATM.TRANSFER_VIEW);
		}
		if (source.equals(information)) {
			clearUserInfo();
			manager.switchTo(ATM.INFORMATION_VIEW);
		}
		// d
		//
		// this is where you'll setup your action listener, which is responsible for
		// responding to actions the user might take in this view (an action can be a
		// user clicking a button, typing in a textfield, etc.).
		//
		// feel free to use my action listener in LoginView.java as an example.
	}


	public void setCurrentAccount(BankAccount Account) {
		this.Account = Account;
		initialize();
		
		JPanel views = new JPanel(new CardLayout());
		ViewManager manager = new ViewManager(views);
		
		// add child views to the parent container

		views.add(new DepositView(manager), ATM.DEPOSIT_VIEW);
		//views.add(new WithdrawView(manager));
		//views.add(new TransferView(manager));

		
	}
	}