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

	//오라클 연동 준비
	private String driver = "oracle.jdbc.driver.OracleDriver";       
    private String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";        // @호스트 IP : 포트 : SID
    private String colNames[] = {"아이디","이름","성별","섯다점수","최근접속날짜"};  // 테이블 컬럼 값들
    private DefaultTableModel model = new DefaultTableModel(colNames, 0); //  테이블 데이터 모델 객체 생성

    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;   // 리턴받아 사용할 객체 생성 ( select에서 보여줄 때 필요 )

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

	
    private void select(){        // 테이블에 보이기 위해 검색
        String query = "select id, name, gender, sutdascore, latest from user_info";    
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url, "aaaa", "aaaa");
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체생성

            while(rs.next()){            // 각각 값을 가져와서 테이블값들을 추가
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
                con.close();   // 객체 생성한 반대 순으로 사용한 객체는 닫아준다.
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
		
		//이미지 불러오기
		ImagePanel welcomePanel = 
				new ImagePanel(new ImageIcon("C:\\workspace\\SWING1\\image\\welcome.jpg").getImage());
		welcomePanel.setBounds(0, 0, 1026, 578);
		frame.getContentPane().add(welcomePanel);
		welcomePanel.setLayout(null);
		
				//시작화면은 loginPanel
		
		JPanel listPanel = new JPanel();
		listPanel.setBounds(0, 0, 1026, 568);
		frame.getContentPane().add(listPanel);
		listPanel.setLayout(null);
		listPanel.setVisible(false);
		
		JLabel subjectlb = new JLabel("\uAC70\uB798\uB0B4\uC5ED");
		subjectlb.setFont(new Font("굴림", Font.BOLD, 22));
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
