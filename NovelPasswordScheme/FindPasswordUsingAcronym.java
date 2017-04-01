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

public class FindPasswordUsingAcronym extends JFrame {
	private JLabel instructionsJL = new JLabel("Below are the first and last letters of your password(not including the number),. Write your whole password in the text field below.");
	private JLabel acronymJL = new JLabel("");
	private JTextField passField = new JTextField(20);
	private JButton button1 = new JButton("OK");
	private String acronym = "";
	private Random prng;
	
	public FindPasswordUsingAcronym(String passWord) {
		super("Demo program for novel password scheme");		
			
		prng = new Random(System.nanoTime());
		
		GenerateAcronym(passWord);	//generate random acronym	
		
		int temp;
  
		// sets layout manager
		setLayout(new GridBagLayout());
  
		// set up on screen objects in ascending y-axis order (top to bottom)
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.insets = new Insets(10, 10, 10, 10);
		constraint.gridx = 0;
		constraint.gridy = 0;

		add(instructionsJL, constraint);

		constraint.gridy = 1;
		acronymJL.setText(acronym);
		add(acronymJL, constraint);
  
		constraint.gridx = 0;
		constraint.gridy = 2;
  
		passField.setTransferHandler(null);
		add(passField, constraint);
  
		constraint.gridy = 3;
		add(button1, constraint);

		// adds menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu("File");
		JMenuItem menuItemExit = new JMenuItem("Exit");
		menuFile.add(menuItemExit);

		menuBar.add(menuFile);

		// adds menu bar to the frame
		setJMenuBar(menuBar);
  
		// add action listeners for buttons
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (passField.getText().equals(passWord))
				{
					//TODO: go to next stage
					String[] tempSplit = passWord.split("\\D");
					for(String t : tempSplit)
					{
						System.out.println(t);
						if(t.matches("\\d\\d"))
						{
							FindPasswordOutOfNumbers fpon = new FindPasswordOutOfNumbers(t);
							dispose();
							return;
						}
					
					}
					System.out.println("Uh-oh, there was somehow no number in the password");					
				}
			}
		});
  
		// adds window event listener
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				int reply = JOptionPane.showConfirmDialog(FindPasswordUsingAcronym.this,
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
	
	private void GenerateAcronym(String pw)
	{
		acronym += pw.charAt(0) + " ";
		int lastIndex = pw.length() - 1;
		int lastValidRandChar = pw.length() - 3;
		
		int temp = prng.nextInt(lastValidRandChar) + 1;
		
		while(pw.charAt(temp) == pw.charAt(0) || pw.charAt(temp) == pw.charAt(lastIndex))
			temp = prng.nextInt(lastValidRandChar) + 1;
		
		acronym += pw.charAt(temp) + " ";		
		acronym += pw.charAt(lastIndex);
	}
	
	/*******TEST PURPOSES****************/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new FindPasswordUsingAcronym("12alreadybeenchewed");
			}
		});
	}/**/
}