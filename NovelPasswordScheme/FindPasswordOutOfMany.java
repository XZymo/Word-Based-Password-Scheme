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

public class FindPasswordOutOfMany extends JFrame {
	private JLabel instructionsJL = new JLabel("Find and select your password: ");
	private JLabel countJL = new JLabel("1 / 5");
	private JButton button1 = new JButton("OK");
	private JRadioButton[] checkBoxes = new JRadioButton[6];
	private ButtonGroup group = new ButtonGroup();
	private DBControl db = new DBControl();
	private PasswordGenerator generator;
	private Random prng;

	private int passCount = 1, failCount = 0, answer;

	public FindPasswordOutOfMany(int id, String password) {
		super("Password Rehearsal 2");
		
		FindPasswordOutOfMany.this.getRootPane().setDefaultButton(button1);

		// sets layout manager
		setLayout(new GridBagLayout());
		
		prng = new Random(System.nanoTime());
		checkBoxes[0] = new JRadioButton("",true);
		for (int i = 1; i < 6; ++i){
			checkBoxes[i] = new JRadioButton("",false);
		}
		
		generateOptions(password);
		for (JRadioButton b : checkBoxes) group.add(b);

		// set up on screen objects in ascending y-axis order (top to bottom)
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.insets = new Insets(10, 10, 10, 10);
		constraint.gridx = 0;
		constraint.gridy = 0;

		add(instructionsJL, constraint); 
  
		constraint.gridx = 1;
		add(countJL, constraint);
		
		constraint.gridx = 0;
		constraint.gridy = 2;
		add(checkBoxes[0], constraint);
		
		constraint.gridx = 1;
		constraint.gridy = 2;
		add(checkBoxes[1], constraint);

		constraint.gridx = 2;
		constraint.gridy = 2;
		add(checkBoxes[2], constraint);
		
		constraint.gridx = 0;
		constraint.gridy = 3;
		add(checkBoxes[3], constraint);
		
		constraint.gridx = 1;
		constraint.gridy = 3;
		add(checkBoxes[4], constraint);
		
		constraint.gridx = 2;
		constraint.gridy = 3;
		add(checkBoxes[5], constraint);
		
		constraint.gridx = 1;
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
					for (int i = 0; i < 6; ++i){
						if (checkBoxes[i].isSelected() && i == answer){
							++passCount;
							countJL.setText(Integer.toString(passCount) + " / 5");
							generateOptions(password);
							return;
						}
					}
					++failCount;
				} else {
					for (int i = 0; i < 6; ++i){
						if (checkBoxes[i].isSelected() && i == answer){
							db.updateTest2(id,failCount);
							FindPasswordUsingAcronym fpua = new FindPasswordUsingAcronym(id, password);
							dispose();
						}
					}
					++failCount;
				}
			}
		});
  
		// adds window event listener
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				int reply = JOptionPane.showConfirmDialog(FindPasswordOutOfMany.this,
				"Are you sure you want to quit?",
				"Exit",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
				if (reply == JOptionPane.YES_OPTION) {
					db.deleteFromUsers(id);
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
	
	private void generateOptions(String pswrd){
		for (JRadioButton b : checkBoxes){
			generator = new PasswordGenerator(prng.nextInt(4-2)+2);
			b.setText(generator.getPassword());
		}
		answer = prng.nextInt(6);
		checkBoxes[answer].setText(pswrd);
	}
	/***** TEST USAGE *****
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new FindPasswordOutOfMany("fsf","ffs","pass");
			}
		});
	}
	/**/
}