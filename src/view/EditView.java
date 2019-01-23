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
public class EditView extends JPanel implements ActionListener {
	
	
	private ViewManager manager; // manages interactions between the views, model, and database
	private JButton edit; 
	private JButton cancel; 
	private JButton powerButton;
	public BankAccount Account;
	public User user;
	public JComboBox<?> Dates;
	public JComboBox<?> Month;
	public JComboBox<?> Year;
	public JTextField PhoneView1;
	public JTextField PhoneView2;
	public JTextField PhoneView3;
	public JTextField AddressView;
	public JTextField CityView;
	public JComboBox<?> StateView;
	public JTextField PostCodeView;
	public JPasswordField PinView;
	private JLabel errorMessageLabel;	
	private long Number;
	
	/**
	 * Constructs an instance (or object) of the CreateView class.
	 * 
	 * @param manager
	 */
	
	public EditView(ViewManager manager) {
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
		JLabel date = new JLabel("Date: " + Account.getUser().getFormattedDob());
		JLabel phone = new JLabel("Phone Number: ");
		phone.setBounds(105, 160, 400, 35);
		PhoneView1 = new JTextField(20);
		PhoneView1.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	            if (PhoneView1.getText().length() >= 3 ) // limit to 3 characters
	                e.consume();
	        }
	    });
		PhoneView1.setBounds(205, 160, 60, 35);
		PhoneView2 = new JTextField(20);
		PhoneView2.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	            if (PhoneView2.getText().length() >= 3 ) // limit to 3 characters
	                e.consume();
	        }
	    });
		PhoneView2.setBounds(275, 160, 60, 35);
		PhoneView3 = new JTextField(20);
		PhoneView3.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	            if (PhoneView3.getText().length() >= 4 ) // limit to 4 characters
	                e.consume();
	        }
	    });
		PhoneView3.setBounds(345, 160, 60, 35);
		JLabel address = new JLabel("Address:");
		address.setBounds(105, 200, 100, 35);
		AddressView = new JTextField(20);
		AddressView.setText(Account.getUser().getStreetAddress());
		AddressView.setBounds(205, 200, 200, 35);
		JLabel city = new JLabel("City:");
		city.setBounds(105, 240, 100, 35);
		CityView = new JTextField(20);
		CityView.setText(Account.getUser().getCity());
		CityView.setBounds(205, 240, 200, 35);
		JLabel state = new JLabel("State:");
		state.setBounds(105, 280, 100, 35);
		String[] states = {"AL", "AS", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN"
				, "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM",
				"NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};
		StateView = new JComboBox<Object>(states);
		StateView.setSelectedItem(Account.getUser().getState());
		StateView.setBounds(205, 280, 200, 35);//
		JLabel pcode = new JLabel("Postal Code:");
		pcode.setBounds(105, 320, 100, 35);
		PostCodeView = new JTextField(20);
		PostCodeView.setText(Account.getUser().getZip());
		PostCodeView.setBounds(205, 320, 200, 35);
		JLabel password = new JLabel("PIN");
		password.setBounds(105, 360, 100, 35);
		PinView = new JPasswordField(20);
		PinView.setBounds(205, 360, 200, 35);
		edit = new JButton("Save");
		edit.setBounds(0, 400, 200, 35);
		edit.addActionListener(this);
		cancel = new JButton("cancel");
		cancel.setBounds(300, 400, 200, 35);
		cancel.addActionListener(this);
		
		this.add(fname);
		this.add(lname);
		this.add(date);
		this.add(phone);
		this.add(address);
		this.add(city);
		this.add(state);
		this.add(pcode);
		this.add(PhoneView1);
		this.add(PhoneView2);
		this.add(PhoneView3);
		this.add(AddressView);
		this.add(CityView);
		this.add(StateView);
		this.add(PostCodeView);
		this.add(PinView);
		this.add(edit);
		this.add(cancel);
		this.add(accountnum);
		this.add(password);
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
		Database Database = new Database();
		if (source.equals(edit)) {
			Account.getUser().setPhone(Long.parseLong(PhoneView1.getText() + PhoneView2.getText() + PhoneView3.getText()));
			Account.getUser().setCity(CityView.getText());
			Account.getUser().setState((String) StateView.getSelectedItem());
			Account.getUser().setStreetAddress(AddressView.getText());
			Account.getUser().setZip(PostCodeView.getText());
			String pin = new String(PinView.getPassword());
			Account.getUser().setPin(Account.getUser().getPin() , Integer.parseInt(pin));
			Database.updateAccount(Account);
			manager.switchTo(ATM.INFORMATION_VIEW);
		}
		if (source.equals(cancel)) {
			manager.switchTo(ATM.INFORMATION_VIEW);
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