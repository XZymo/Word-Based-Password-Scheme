package NovelPasswordScheme;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.ButtonGroup;

/**
 * This program demonstrates how to work with JFrame in Swing.
 * @author www.codejava.net
 *
 */
public class TestingPassword extends JFrame {
	private JLabel label1 = new JLabel("Enter your first name: ");
	private JLabel label2 = new JLabel("Enter your last name: ");
	private JTextField firstField = new JTextField(20);
	private JTextField lastField = new JTextField(20);
	private JButton acceptButton = new JButton("Accept");
	private DBControl db = new DBControl();
	private PasswordGenerator generator;

	int type;
	
	public TestingPassword() {
		super("TEST 3 PASSWORDS");
		
		// sets layout manager
		setLayout(new GridBagLayout());
		
		// set up on screen objects in ascending y-axis order (top to bottom)
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.insets = new Insets(10, 10, 10, 10);
		constraint.gridx = 0;
		constraint.gridy = 0;

		add(label1, constraint);

		constraint.gridx = 1;
		add(firstField, constraint);
		
		constraint.gridx = 0;
		constraint.gridy = 1;
		
		add(label2, constraint);
		
		constraint.gridx = 1;
		add(lastField, constraint);
		
		constraint.gridx = 2;
		constraint.gridwidth = 2;
		constraint.gridy = 4;
		add(acceptButton, constraint);

		// adds menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu("File");
		JMenuItem menuItemExit = new JMenuItem("Exit");
		menuFile.add(menuItemExit);

		menuBar.add(menuFile);

		// adds menu bar to the frame
		setJMenuBar(menuBar);
		
		// add action listeners for buttons
		acceptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (firstField.getText().isEmpty()){
					JOptionPane.showMessageDialog(TestingPassword.this, "Must enter a first name!");
					return;
				}
				if (lastField.getText().isEmpty()){
					JOptionPane.showMessageDialog(TestingPassword.this, "Must enter a last name!");
					return;
				}
				int reply = JOptionPane.showConfirmDialog(TestingPassword.this,
						"By clicking yes, you will begin your password test where you must enter the corresponding password within 3 tries. Are you ready?",
						"Confirm",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (reply == JOptionPane.YES_OPTION) {
					String[] pswrds = db.getPasswords(firstField.getText(),lastField.getText());
				//	EnterPassword5Times epft = new EnterPassword5Times(id,displayResult.getText());
					dispose();
				} else {
					return;
				}
			}
		});
		
		// adds window event listener
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				int reply = JOptionPane.showConfirmDialog(TestingPassword.this,
						"Are you sure you want to quit?",
						"Exit",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (reply == JOptionPane.YES_OPTION) {
					dispose();
				} else {
					return;
				}
			}
		});

		// sets icon image
		String iconPath = "/NovelPasswordScheme/android.png";
		Image icon = new ImageIcon(getClass().getResource(iconPath)).getImage();
		setIconImage(icon);

		pack();

		// centers on screen
		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new TestingPassword();
			}
		});
	}
}