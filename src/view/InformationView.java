package view;

import java.awt.CardLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;
import data.Database;
import model.BankAccount;
import model.User;

@SuppressWarnings("serial")
public class InformationView extends JPanel implements ActionListener {
	
	
	private ViewManager manager; // manages interactions between the views, model, and database
	private JButton edit; 
	private JButton cancel; 
	private JButton powerButton;
	public BankAccount Account;
	public User user;
	private JLabel errorMessageLabel;	
	private long Number;
	
	/**
	 * Constructs an instance (or object) of the CreateView class.
	 * 
	 * @param manager
	 */
	
	public InformationView(ViewManager manager) {
		super();
		
		this.manager = manager;
		this.errorMessageLabel = new JLabel("", SwingConstants.CENTER);
		//initialize();
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Initializes the CreateView components.
	 */
	
	@SuppressWarnings("static-access")
	private void initialize() {
		Database Database = new Database();
		initPowerButton2();
		this.setLayout(null);
		Number = Account.getAccountNumber();
		JLabel accountnum = new JLabel ("your account number is:" + Number);
		accountnum.setBounds(200,1,400,20);
		JLabel fname = new JLabel("First Name: " + Account.getUser().getFirstName());
		fname.setBounds(105, 40, 400, 35);
		JLabel lname = new JLabel("Last Name: " + Account.getUser().getLastName());
		lname.setBounds(105, 80, 400, 35);
		JLabel date = new JLabel("Date: "  + Account.getUser().getFormattedDob());
		date.setBounds(105, 120, 400, 35);
		JLabel phone = new JLabel("Phone Number: "  + Account.getUser().getFormattedPhone());
		phone.setBounds(105, 160, 400, 35);
		JLabel address = new JLabel("Address: " + Account.getUser().getStreetAddress() + " " + Account.getUser().getFormattedAddress());
		address.setBounds(105, 200, 400, 35);
//		JLabel city = new JLabel("City:");
//		city.setBounds(105, 240, 100, 35);
//		JLabel state = new JLabel("State:");
//		state.setBounds(105, 280, 100, 35);
//		JLabel pcode = new JLabel("Postal Code:");
//		pcode.setBounds(105, 320, 100, 35);
		edit = new JButton("edit information");
		edit.setBounds(0, 400, 246, 35);
		edit.addActionListener(this);
		cancel = new JButton("edit information");
		cancel.setBounds(0, 400, 246, 35);
		cancel.addActionListener(this);
		
		this.add(fname);
		this.add(lname);
		this.add(date);
		this.add(phone);
		this.add(address);
//		this.add(city);
//		this.add(state);
//		this.add(pcode);
		this.add(edit);
		this.add(cancel);
		this.add(accountnum);
		// TODO
		//
		// this is where you should build the CreateView (i.e., all the components that
		// allow the user to enter his or her information and create a new account).
		//
		// feel free to use my layout in LoginView as an example for laying out and
		// positioning your components.
	}
	
	private void initPowerButton2() {
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

	/*
	 * CreateView is not designed to be serialized, and attempts to serialize will throw an IOException.
	 * 
	 * @param oos
	 * @throws IOException
	 */
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The CreateView class is not serializable.");
	}
	
	///////////////////// OVERRIDDEN METHODS //////////////////////////////////////////
	
	/*
	 * Responds to button clicks and other actions performed in the CreateView.
	 * 
	 * @param e
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source.equals(edit)) {
			manager.switchTo(ATM.EDIT_VIEW);
		}
		if (source.equals(cancel)) {
			manager.switchTo(ATM.HOME_VIEW);
		}
		if (source.equals(powerButton)) {
			manager.shutdown();
		}
		// TODO
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

		
	}
	

	public void updateErrorMessage(String errorMessage) {
		errorMessageLabel.setText(errorMessage);
		
	}
}