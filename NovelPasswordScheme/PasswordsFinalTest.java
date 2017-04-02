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

import java.util.Random;
import java.util.ArrayList;

public class PasswordsFinalTest extends JFrame {
	private JLabel instructionsJL = new JLabel("");
	private JLabel countJL = new JLabel("1 / 3");
	private JTextField passField = new JTextField(25);
	private JButton button1 = new JButton("OK");
	private DBControl db = new DBControl();
	
	private int failResults[] = new int[3], passCount = 1, failCount = 0, passwordCount = 0;
	private double timeResults[] = new double[3];
	private long start, stop;
	
	private Random prng;
	
	private ArrayList<Integer> randList = new ArrayList<Integer>(3);	//used for selecting random password

	public PasswordsFinalTest(String fName, String lName, String[] passwords) {
		super("Password Rehearsal 1");
		
		PasswordsFinalTest.this.getRootPane().setDefaultButton(button1);
		
		for(int i = 0; i < 3; i++){
			failResults[i] = 1;
			timeResults[i] = 0.0;
			randList.add(i);
		}
		
		prng = new Random(System.nanoTime());
  
		// sets layout manager
		setLayout(new GridBagLayout());
  
		// set up on screen objects in ascending y-axis order (top to bottom)
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.insets = new Insets(10, 10, 10, 10);
		constraint.gridx = 0;
		constraint.gridy = 0;

		add(instructionsJL, constraint); 
  
		constraint.gridx = 0;
		constraint.gridy = 1;
		passField.setTransferHandler(null);
		add(passField, constraint);
  
		constraint.gridx = 1;
		add(countJL, constraint);
  
		constraint.gridx = 0;
		//constraint.gridwidth = 2;
		constraint.gridy = 4;
  
		add(button1, constraint);

		// adds menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu("File");
		JMenuItem menuItemExit = new JMenuItem("Exit");
		menuFile.add(menuItemExit);

		menuBar.add(menuFile);

		// adds menu bar to the frame
		setJMenuBar(menuBar);
		
		passwordCount = generateNum();
		
		if(passwordCount > -1)
			instructionsJL.setText("Enter password #" + Integer.toString(passwordCount + 1));
  
		// add event listeners for buttons
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				
				if(passwordCount > -1)
				{
					if(failResults[passwordCount] < 3){
						if(passField.getText().equals(passwords[passwordCount])){
							//System.out.println("Correct password: " + passwords[passwordCount] + " : " + passField.getText());
							++passCount;
							stop = System.nanoTime();
							timeResults[passwordCount] = (stop-start) * 1e-9;
							start = stop;
							passField.setText("");
							passwordCount = generateNum();
							if(passwordCount > -1)
							{
								countJL.setText(Integer.toString(failResults[passwordCount]) + " / 3");							
								instructionsJL.setText("Enter password #" + Integer.toString(passwordCount + 1));	
							}	
							else
							{
								instructionsJL.setText("You finished all the tests! Yay!");
								countJL.setVisible(false);
								passField.setVisible(false);
								button1.setText("Submit");
							}
							passCount = 0;	
							
						} else {						
							++failResults[passwordCount];
							//System.out.println("Incorrect password: " + passwords[passwordCount] + " : " + passField.getText() + " Fail count: " + failResults[passwordCount]);
							countJL.setText(Integer.toString(failResults[passwordCount]) + " / 3");
							passField.setText("");
						}
					} else {
						++failResults[passwordCount];
						//System.out.println("Incorrect password: " + passwords[passwordCount] + " : " + passField.getText() + " Fail count: " + failResults[passwordCount]);
						stop = System.nanoTime();
						timeResults[passwordCount] = (stop-start) * 1e-9;
						start = stop;
						passField.setText("");
						passwordCount = generateNum();
						if(passwordCount > -1)
						{
							countJL.setText(Integer.toString(failResults[passwordCount]) + " / 3");							
							instructionsJL.setText("Enter password #" + Integer.toString(passwordCount + 1));	
						}
						else
						{
							instructionsJL.setText("You finished all the tests! Yay!");
							countJL.setVisible(false);
							passField.setVisible(false);
							button1.setText("Submit");
						}
						passCount = 0;	
					}
				}
				else
				{
					db.addTestResults(fName, lName, passwords[0], passwords[1], passwords[2], failResults[0], timeResults[0], failResults[1], timeResults[1], failResults[2], timeResults[2]);
					dispose();
				}
					
			}
		});
  
		// adds window event listener
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				int reply = JOptionPane.showConfirmDialog(PasswordsFinalTest.this,
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
		
		// start timer
		start = System.nanoTime();
	}
	
	private int generateNum(){
		int size = randList.size();
		if(size > 0)
		{
			int rand = prng.nextInt(size);
			int temp = randList.get(rand);
			randList.remove(rand);
			return temp;
		}
		return -1;
		
	}
	
	/***** TEST USAGE *****
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				String[] testPass = new String[3];
				testPass[0] = "smokeweed80";
				testPass[1] = "theansweris42";
				testPass[2] = "therearefourwords44";
				new PasswordsFinalTest("John", "Smith", testPass);
			}
		});
	}
	/**/
}