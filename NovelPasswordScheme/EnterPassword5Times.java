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


public class EnterPassword5Times extends JFrame {
	private JLabel instructionsJL = new JLabel("Enter your password 5 times: ");
	private JLabel countJL = new JLabel("1 / 5");
	private JTextField passField = new JTextField(25);
	private JButton button1 = new JButton("OK");
	private DBControl db = new DBControl();
 
	int passCount = 1, failCount = 0;
	long start, stop;

	public EnterPassword5Times(int id, String password) {
		super("Password Rehearsal 1");
		
		EnterPassword5Times.this.getRootPane().setDefaultButton(button1);
  
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
  
		// add event listeners for buttons
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(passCount < 5){
					if(passField.getText().equals(password)){
						++passCount;
						countJL.setText(Integer.toString(passCount) + " / 5");
						passField.setText("");
					} else ++failCount;
				} else {
					if(passField.getText().equals(password)){
						stop = System.nanoTime();
						double time = (stop-start) * 10e-9;
						db.updateTest1(id,failCount,time);
						FindPasswordOutOfMany fpom = new FindPasswordOutOfMany(id, password);
						dispose();
					} else ++failCount;
				}
			}
		});
  
		// adds window event listener
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				int reply = JOptionPane.showConfirmDialog(EnterPassword5Times.this,
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
	/***** TEST USAGE *****
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new EnterPassword5Times(1,pass);
			}
		});
	}
	/**/
}