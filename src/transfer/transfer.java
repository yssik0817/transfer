package transfer;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class transfer {

	private JFrame frame;
	private JTextField IdTextField;
	private JTextField PwTextField;

	//����Ŭ ���� �غ�
	private String driver = "oracle.jdbc.driver.OracleDriver";       
    private String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";        // @ȣ��Ʈ IP : ��Ʈ : SID
    private String colNames[] = {"���̵�","�̸�","����","��������","�ֱ����ӳ�¥"};  // ���̺� �÷� ����
    private DefaultTableModel model = new DefaultTableModel(colNames, 0); //  ���̺� ������ �� ��ü ����

    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;   // ���Ϲ޾� ����� ��ü ���� ( select���� ������ �� �ʿ� )

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					transfer window = new transfer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
    private void select(){        // ���̺� ���̱� ���� �˻�
        String query = "select id, name, gender, sutdascore, latest from user_info";    
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url, "aaaa", "aaaa");
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery(); // ���Ϲ޾ƿͼ� �����͸� ����� ��ü����

            while(rs.next()){            // ���� ���� �����ͼ� ���̺����� �߰�
                model.addRow(new Object[]{rs.getString("id"),
               		 rs.getString("name"),
                        rs.getString("gender"),rs.getString("sutdascore"),
                        rs.getString("latest")});
            }

        }catch(Exception e){
            System.out.println(e.getMessage());

        }finally{
            try {
                rs.close();
                pstmt.close();
                con.close();   // ��ü ������ �ݴ� ������ ����� ��ü�� �ݾ��ش�.
            } catch (Exception e2) {}
        }
    }

	
	
	/**
	 * Create the application.
	 */
	public transfer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1044, 615);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//�̹��� �ҷ�����
		ImagePanel welcomePanel = 
				new ImagePanel(new ImageIcon("C:\\workspace\\SWING1\\image\\welcome.jpg").getImage());
		welcomePanel.setBounds(0, 0, 1026, 578);
		frame.getContentPane().add(welcomePanel);
		welcomePanel.setLayout(null);
		
				//����ȭ���� loginPanel
		
		JPanel listPanel = new JPanel();
		listPanel.setBounds(0, 0, 1026, 568);
		frame.getContentPane().add(listPanel);
		listPanel.setLayout(null);
		listPanel.setVisible(false);
		
		JLabel subjectlb = new JLabel("\uAC70\uB798\uB0B4\uC5ED");
		subjectlb.setFont(new Font("����", Font.BOLD, 22));
		subjectlb.setBounds(423, 50, 151, 71);
		listPanel.add(subjectlb);
		
	
		JLabel lbId = new JLabel("I D :");
		lbId.setBounds(692, 363, 38, 24);
		lbId.setFont(new Font("Arial", Font.PLAIN, 20));
		welcomePanel.add(lbId);
		
		JLabel lbPW = new JLabel("P W :");
		lbPW.setBounds(692, 418, 50, 24);
		lbPW.setFont(new Font("Arial", Font.PLAIN, 20));
		welcomePanel.add(lbPW);
		
		idField = new JTextField();
		idField.setBounds(776, 365, 116, 24);
		idField.setColumns(10);
		welcomePanel.add(idField);
		
		passField = new JTextField();
		passField.setBounds(776, 420, 116, 24);
		passField.setColumns(10);
		welcomePanel.add(passField);
		
		JButton logInBtn = new JButton("logIn");
		logInBtn.setBounds(752, 472, 63, 27);
		welcomePanel.add(logInBtn);
		
		logInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id.getText().equals("yun")&&
						Arrays.equals(password.getPassword(), "1234".toCharArray())) {
					System.out.println("Hello Yun");
				welcomePanel.setVisible(false);
				listPanel.setVisible(true);
			}
		});
	}
}
