package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.ViewManager;

@SuppressWarnings("serial")
public class CreateView extends JPanel implements ActionListener {
	
	private ViewManager manager;
	private JButton create; // manages interactions between the views, model, and database
	
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
	
	private void initialize() {
		this.setLayout(null);
		JLabel fname = new JLabel("First Name:");
		fname.setBounds(105, 40, 100, 35);
		JTextField FnameView = new JTextField(20);
		FnameView.setBounds(205, 40, 200, 35);
		JTextField LnameView = new JTextField(20);
		JLabel lname = new JLabel("Last Name:");
		lname.setBounds(105, 80, 100, 35);
		LnameView.setBounds(205, 80, 200, 35);
		JLabel date = new JLabel("Date:");
		date.setBounds(105, 120, 100, 35);
		String[] days = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				 "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
				 "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
				 "31"};
		String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
				"Sept", "Oct", "Nov", "Dec"};
		String[] years = new String[109];
		int n = 1910;
		for(int i = 0; i <= 108; i++){
			years[i] = String.valueOf(n);
			n++;
		}
		JComboBox<?> Dates = new JComboBox<Object>(days);
		Dates.setBounds(205, 120, 60, 35);
		JComboBox<?> Month = new JComboBox<Object>(months);
		Month.setBounds(275, 120, 60, 35);
		JComboBox<?> year = new JComboBox<Object>(years);
		year.setBounds(345, 120, 60, 35);
		JLabel phone = new JLabel("Phone Number:");
		phone.setBounds(105, 160, 100, 35);
		JTextField PhoneView1 = new JTextField(20);
		PhoneView1.setBounds(205, 160, 60, 35);
		JTextField PhoneView2 = new JTextField(20);
		PhoneView2.setBounds(275, 160, 60, 35);
		JTextField PhoneView3 = new JTextField(20);
		PhoneView3.setBounds(345, 160, 60, 35);
		JLabel address = new JLabel("Address:");
		address.setBounds(105, 200, 100, 35);
		JTextField AddressView = new JTextField(20);
		AddressView.setBounds(205, 200, 200, 35);
		JLabel city = new JLabel("City:");
		city.setBounds(105, 240, 100, 35);
		JTextField CityView = new JTextField(20);
		CityView.setBounds(205, 240, 200, 35);
		JLabel state = new JLabel("State:");
		state.setBounds(105, 280, 100, 35);
		JTextField StateView = new JTextField(20);//
		StateView.setBounds(205, 280, 200, 35);//
		JLabel pcode = new JLabel("Postal Code:");
		pcode.setBounds(105, 320, 100, 35);
		JTextField PostCodeView = new JTextField(20);
		PostCodeView.setBounds(205, 320, 200, 35);
		JLabel password = new JLabel("Password:");
		password.setBounds(105, 360, 100, 35);
		JPasswordField PinView = new JPasswordField(20);
		PinView.setBounds(205, 360, 200, 35);
		create = new JButton("Create");
		create.setBounds(305, 400, 100, 35);
		create.addActionListener(this);
		
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
		this.add(year);
		this.add(PhoneView1);
		this.add(PhoneView2);
		this.add(PhoneView3);
		this.add(AddressView);
		this.add(CityView);
		this.add(StateView);
		this.add(PostCodeView);
		this.add(PinView);
		this.add(create);
		// TODO
		//
		// this is where you should build the CreateView (i.e., all the components that
		// allow the user to enter his or her information and create a new account).
		//
		// feel free to use my layout in LoginView as an example for laying out and
		// positioning your components.
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
			//implement create button
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