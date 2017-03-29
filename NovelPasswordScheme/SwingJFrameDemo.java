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
public class SwingJFrameDemo extends JFrame {
	private JLabel label1 = new JLabel("Enter your first name: ");
	private JLabel label2 = new JLabel("Enter your last name: ");
	private JTextField firstField = new JTextField(20);
	private JTextField lastField = new JTextField(20);
	private JTextField displayResult = new JTextField(60);
	private JButton button1 = new JButton("Generate");
	private JButton button2 = new JButton("Accept");
	private JRadioButton check0 = new JRadioButton("2-word",false);
	private JRadioButton check1 = new JRadioButton("3-word",true);
	private JRadioButton check2 = new JRadioButton("4-word",false);
	private ButtonGroup group = new ButtonGroup();
	private PasswordGenerator generator;

	public SwingJFrameDemo() {
		super("Demo program for novel password scheme");
		displayResult.setText(" - Select Generate - ");
		displayResult.setEditable(false);
		group.add(check0);
		group.add(check1);
		group.add(check2);
		
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
		
		constraint.gridx = 0;
		constraint.gridy = 2;
		add(check0, constraint);
		
		constraint.gridx = 1;
		add(check1, constraint);
		
		constraint.gridx = 2;
		add(check2, constraint);
		
		constraint.gridx = 0;
		constraint.gridwidth = 2;
		constraint.gridy = 3;
		add(displayResult, constraint);
		
		constraint.gridx = 0;
		constraint.gridwidth = 2;
		constraint.gridy = 4;
		
		add(button1, constraint);

		constraint.gridx = 2;
		constraint.gridwidth = 2;
		constraint.gridy = 4;
		add(button2, constraint);

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
				if (check0.isSelected()){
					generator = new PasswordGenerator(2);
				} else if (check1.isSelected()){
					generator = new PasswordGenerator(3);
				} else {
					generator = new PasswordGenerator(4);
				}
				displayResult.setText(generator.getPassword());
			}
		});
		
		// adds window event listener
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				int reply = JOptionPane.showConfirmDialog(SwingJFrameDemo.this,
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
				new SwingJFrameDemo();
			}
		});
	}
}