package gui;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JToggleButton;

public class AnnotationPanel {

	public JFrame frame;
	public JTextField dialogueField;
	public JButton btnASI;
	public JButton btnQYN;
	public JButton btnQWH;
	public JButton btnQD;
	public JButton btnQC;
	public JButton btnEC;
	public JButton btnST;
	public JButton btnAD;
	public JButton btnACK;
	public JButton btnBC;
	public JButton btnAY;
	public JButton btnAN;
	public JButton btnDT;
	public JButton btnRQS;
	public JButton btnASS;
	public JButton btnH;
	public JButton btnG;
	public JButton btnP;
	public JButton btnASM;
	public JButton btnCON;
	public JButton btnNEXT;
	public JButton btnDM;
	public JLabel lblPID;
	public JLabel lblROLE;
	public JButton btnGO;
	public JButton btnPREV;
	public JList listPREV;
	public DefaultListModel listModel;
	public JScrollPane scrollPane;
	public JScrollBar scrollBar;
	public JLabel lblID;
	public JButton btnDECID;
	public JButton btnINCID;
	public JToggleButton tglbtnENID;
	/**
	 * Launch the application.
	 */
//	public static void (String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AnnotationPanel window = new AnnotationPanel();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public AnnotationPanel() {
		initialize();
		frame.getRootPane().setDefaultButton(btnGO);
		
		
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1094, 570);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		dialogueField = new JTextField();
		dialogueField.setBounds(0, 410, 915, 77);
		frame.getContentPane().add(dialogueField);
		dialogueField.setColumns(10);
		
		btnASI = new JButton("Assert-Identified");
		btnASI.setBounds(429, 123, 143, 45);
		frame.getContentPane().add(btnASI);
		
		btnQYN = new JButton("Yes-No-Question");
		btnQYN.setBounds(582, 67, 143, 45);
		frame.getContentPane().add(btnQYN);
		
		btnQWH = new JButton("Wh-Question");
		btnQWH.setBounds(582, 123, 143, 45);
		frame.getContentPane().add(btnQWH);
		
		btnQD = new JButton("Disjunctive-Question");
		btnQD.setBounds(582, 179, 143, 45);
		frame.getContentPane().add(btnQD);
		
		btnQC = new JButton("Clarification-Question");
		btnQC.setBounds(582, 11, 143, 45);
		frame.getContentPane().add(btnQC);
		
		btnEC = new JButton("Echo-Confirmation");
		btnEC.setBounds(582, 347, 143, 45);
		frame.getContentPane().add(btnEC);
		
		btnST = new JButton("Self-Talk");
		btnST.setBounds(735, 233, 143, 45);
		frame.getContentPane().add(btnST);
		
		btnAD = new JButton("Action-Directive");
		btnAD.setBounds(429, 11, 143, 45);
		frame.getContentPane().add(btnAD);
		
		btnACK = new JButton("Acknowledgement");
		btnACK.setBounds(429, 67, 143, 45);
		frame.getContentPane().add(btnACK);
		
		btnBC = new JButton("Backchannel");
		btnBC.setBounds(429, 291, 143, 45);
		frame.getContentPane().add(btnBC);
		
		btnAY = new JButton("Yes-Answer");
		btnAY.setBounds(735, 67, 143, 45);
		frame.getContentPane().add(btnAY);
		
		btnAN = new JButton("No-Answer");
		btnAN.setBounds(735, 11, 143, 45);
		frame.getContentPane().add(btnAN);
		
		btnDT = new JButton("Describe-Target");
		btnDT.setBounds(429, 347, 143, 45);
		frame.getContentPane().add(btnDT);
		
		btnRQS = new JButton("Request-Skip");
		btnRQS.setBounds(582, 233, 143, 45);
		frame.getContentPane().add(btnRQS);
		
		btnASS = new JButton("Assert-Skipping");
		btnASS.setBounds(429, 233, 143, 45);
		frame.getContentPane().add(btnASS);
		
		btnH = new JButton("Hedge");
		btnH.setBounds(735, 289, 143, 45);
		frame.getContentPane().add(btnH);
		
		btnG = new JButton("Other");
		btnG.setBounds(925, 123, 143, 45);
		frame.getContentPane().add(btnG);
		
		btnP = new JButton("Pause");
		btnP.setBounds(735, 346, 143, 46);
		frame.getContentPane().add(btnP);
		
		btnASM = new JButton("Assertion-Matcher");
		btnASM.setBounds(429, 179, 143, 45);
		frame.getContentPane().add(btnASM);
		
		btnCON = new JButton("Confliction");
		btnCON.setBounds(925, 179, 143, 45);
		frame.getContentPane().add(btnCON);
		
		btnNEXT = new JButton("Next");
		btnNEXT.setBounds(360, 498, 89, 23);
		frame.getContentPane().add(btnNEXT);
		
		lblPID = new JLabel("PlayerID:");
		lblPID.setBounds(10, 365, 206, 34);
		frame.getContentPane().add(lblPID);
		
		lblROLE = new JLabel("Role: ");
		lblROLE.setBounds(293, 365, 126, 34);
		frame.getContentPane().add(lblROLE);
		
		btnPREV = new JButton("Previous");
		btnPREV.setBounds(27, 498, 89, 23);
		frame.getContentPane().add(btnPREV);
		
		btnGO = new JButton("Confirm");
		btnGO.setBounds(925, 457, 143, 45);
		frame.getContentPane().add(btnGO);
		
		listModel = new DefaultListModel();
		scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 24, 342, 142);
		frame.getContentPane().add(scrollPane);
		listPREV = new JList(listModel);
		scrollPane.setViewportView(listPREV);
		scrollBar = scrollPane.getVerticalScrollBar();
		
		btnDM = new JButton("Disclose Marker");
		btnDM.setBounds(582, 291, 143, 45);
		frame.getContentPane().add(btnDM);
		
		lblID = new JLabel("ID: ");
		lblID.setBounds(735, 500, 66, 18);
		frame.getContentPane().add(lblID);
		
		btnDECID = new JButton("-");
		btnDECID.setBounds(636, 498, 89, 23);
		frame.getContentPane().add(btnDECID);
		
		btnINCID = new JButton("+");
		btnINCID.setBounds(811, 498, 89, 23);
		frame.getContentPane().add(btnINCID);
		
		tglbtnENID = new JToggleButton("ID ON/OFF");
		tglbtnENID.setBounds(933, 410, 121, 23);
		frame.getContentPane().add(tglbtnENID);
	}
}
