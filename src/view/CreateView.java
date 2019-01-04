package view;

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

import controller.ViewManager;
import data.Database;
import model.BankAccount;
import model.User;

@SuppressWarnings("serial")
public class CreateView extends JPanel implements ActionListener {
	
	
	private ViewManager manager; // manages interactions between the views, model, and database
	private JButton create; 
	private JButton submit; 
	private JButton powerButton;
	public BankAccount bankAccount;
	public User user;
	public JTextField FnameView;
	public JTextField LnameView;
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
	private long Number;
	
	/**
	 * Constructs an instance (or object) of the CreateView class.
	 * 
	 * @param manager
	 */
	
	public CreateView(ViewManager manager) {
		super();
		
		this.manager = manager;
		initialize();
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
		Number = Database.highacct() + 1;
		JLabel accountnum = new JLabel ("your account number is:" + Number);
		accountnum.setBounds(200,1,300,20);
		JLabel fname = new JLabel("First Name:");
		fname.setBounds(105, 40, 100, 35);
		FnameView = new JTextField(20);
		FnameView.setBounds(205, 40, 200, 35);
		LnameView = new JTextField(20);
		JLabel lname = new JLabel("Last Name:");
		lname.setBounds(105, 80, 100, 35);
		LnameView.setBounds(205, 80, 200, 35);
		JLabel date = new JLabel("Date:");
		date.setBounds(105, 120, 100, 35);
		String[] days = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				 "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
				 "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
				 "31"};
		String[] months = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",};
		String[] years = new String[109];
		int n = 1910;
		for(int i = 0; i <= 108; i++){
			years[i] = String.valueOf(n);
			n++;
		}
		Dates = new JComboBox<Object>(days);
		Dates.setBounds(205, 120, 60, 35);
		Month = new JComboBox<Object>(months);
		Month.setBounds(275, 120, 60, 35);
		Year = new JComboBox<Object>(years);
		Year.setBounds(345, 120, 60, 35);
		JLabel phone = new JLabel("Phone Number:");
		phone.setBounds(105, 160, 100, 35);
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
		AddressView.setBounds(205, 200, 200, 35);
		JLabel city = new JLabel("City:");
		city.setBounds(105, 240, 100, 35);
		CityView = new JTextField(20);
		CityView.setBounds(205, 240, 200, 35);
		JLabel state = new JLabel("State:");
		state.setBounds(105, 280, 100, 35);
		String[] states = {"AL", "AS", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN"
				, "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM",
				"NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};
		StateView = new JComboBox<Object>(states);//
		StateView.setBounds(205, 280, 200, 35);//
		JLabel pcode = new JLabel("Postal Code:");
		pcode.setBounds(105, 320, 100, 35);
		PostCodeView = new JTextField(20);
		PostCodeView.setBounds(205, 320, 200, 35);
		JLabel password = new JLabel("PIN");
		password.setBounds(105, 360, 100, 35);
		PinView = new JPasswordField(20);
		PinView.setBounds(205, 360, 200, 35);
		create = new JButton("Create");
		create.setBounds(0, 400, 246, 35);
		create.addActionListener(this);
		submit= new JButton("Cancel");
		submit.setBounds(250, 400, 246, 35);
		submit.addActionListener(this);
		
		this.add(fname);
		this.add(lname);
		this.add(date);
		this.add(phone);
		this.add(address);
		this.add(city);
		this.add(state);
		this.add(pcode);
		this.add(password);
		this.add(FnameView);
		this.add(LnameView);
		this.add(Dates);
		this.add(Month);
		this.add(Year);
		this.add(PhoneView1);
		this.add(PhoneView2);
		this.add(PhoneView3);
		this.add(AddressView);
		this.add(CityView);
		this.add(StateView);
		this.add(PostCodeView);
		this.add(PinView);
		this.add(create);
		this.add(submit);
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
		
		if (source.equals(create)) {
			Database Database = new Database();
			String fnameget = FnameView.getText();
			String lnameget = LnameView.getText();
			String dayget = (String) Dates.getSelectedItem();
			String monthget = (String) Month.getSelectedItem();
			String yearget = (String) Year.getSelectedItem();
			String dobget = new String(yearget+monthget+dayget);
			String phoneget1 = PhoneView1.getText();
			String phoneget2 = PhoneView2.getText();
			String phoneget3 = PhoneView3.getText();
			String phoneget = new String(phoneget1 + phoneget2 + phoneget3);
			String addressget = AddressView.getText();
			String cityget = CityView.getText(); 
			String stateget = (String) StateView.getSelectedItem();
			String zipget = PostCodeView.getText();
			char[] passwordget =  PinView.getPassword();
			String password = new String(passwordget);
				//password += passwordget.clone();
			System.out.println(fnameget + " "+lnameget+" "+dobget +" "+password +" "+ phoneget);
			user = new User(Integer.parseInt(password), Integer.parseInt(dobget), Long.parseLong(phoneget), fnameget, lnameget, addressget, cityget, stateget, zipget);
			bankAccount = new BankAccount('N', Number, 123.45, user);
			Number++;
			Database.insertAccount(bankAccount);
			FnameView.setText(null);
			LnameView.setText(null);
			PhoneView2.setText(null);
			PhoneView1.setText(null);
			PhoneView3.setText(null);
			Dates.setSelectedIndex(0);
			Month.setSelectedIndex(0);
			Year.setSelectedIndex(0);
			StateView.setSelectedIndex(0);
			AddressView.setText(null);
			CityView.setText(null);
			PostCodeView.setText(null);
			PinView.setText(null);
			manager.switchTo(ATM.LOGIN_VIEW);
		}
		if (source.equals(submit)) {
			FnameView.setText(null);
			LnameView.setText(null);
			PhoneView2.setText(null);
			PhoneView1.setText(null);
			PhoneView3.setText(null);
			Dates.setSelectedIndex(0);
			Month.setSelectedIndex(0);
			Year.setSelectedIndex(0);
			StateView.setSelectedIndex(0);
			AddressView.setText(null);
			CityView.setText(null);
			PostCodeView.setText(null);
			PinView.setText(null);
			manager.switchTo(ATM.LOGIN_VIEW);
			
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
}