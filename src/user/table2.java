package user;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

public class table2 extends JPanel {

	private final String ID="yun";
	private final String PASS="qwer";
	private JFrame frame;
	private JTextField idField;
	private JPasswordField passField;
	private JScrollPane scrollPane;
	private JButton addBtn = null;
	private JButton	saveBtn = null;
	private JButton editBtn = null;
	private JButton deleteBtn = null;
	private JPanel  currPanel;	//���� �������� �г� ����
	
	private	String driver = "oracle.jdbc.driver.OracleDriver";
	private	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private	String user = "user3";
	private	String pass ="oracle";
	private String colNames[] = {"아이디","이름","성별"};
	private DefaultTableModel model = new DefaultTableModel(colNames, 0);
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					table2 window = new table2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public table2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setLayout(null);
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
	//로그인 패널 생성
	ImagePanel LoginPanel = new ImagePanel(new ImageIcon("C:\\workspace\\transfer\\image\\welcome.jpg").getImage());
	
			frame.setSize(LoginPanel.getDim());
			frame.setPreferredSize(LoginPanel.getDim());
			LoginPanel.setBounds(0, 0, 1032, 700);
			frame.getContentPane().add(LoginPanel);
			
			idField = new JTextField();
			idField.setFont(new Font("Arial", Font.PLAIN, 26));
			idField.setBounds(600, 487, 260, 43);
			LoginPanel.add(idField);
			idField.setColumns(10);
			idField.setBorder(null);	//Border 없애기
			
			passField = new JPasswordField();
			passField.setFont(new Font("Arial", Font.PLAIN, 26));
			passField.setBounds(600, 557, 260, 43);
			passField.setBorder(null);	//Border 없애기
			LoginPanel.add(passField);
			
			JButton logInBtn = new JButton("login");
			logInBtn.setBounds(560, 624, 338, 38);
			//		frame.getContentPane().add(logInBtn);
					logInBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							//ID�� PW ��ġ Ȯ��	
							if(ID.equals(idField.getText()) && PASS.equals(passField.getText())){
								//로그인 패널 가리기
								LoginPanel.setVisible(false);
								JOptionPane.showMessageDialog(null,"Log In Success");
								MenuJTabaleExam3 start = new MenuJTabaleExam3();
								start.menujta;
							}else{
								JOptionPane.showMessageDialog(null,"You Failed to Log In");
							}
						}
					});
					//�α��� ��ư �̹��� �ֱ�
					logInBtn.setIcon(new ImageIcon("C:\\workspace\\SWING1\\image\\button.jpg"));
					//�α��� ��ư Ŭ���� �̹��� ��ȭ
					logInBtn.setPressedIcon(new ImageIcon("C:\\workspace\\SWING1\\image\\btnClicked.jpg"));
					LoginPanel.add(logInBtn);
	
	}


}
