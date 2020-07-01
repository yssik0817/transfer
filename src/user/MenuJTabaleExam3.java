package user;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
 
 
public class MenuJTabaleExam extends JFrame implements ActionListener {
    JMenu m = new JMenu("관리");
    JMenuItem insert = new JMenuItem("가입");
    JMenuItem update = new JMenuItem("수정");
    JMenuItem delete = new JMenuItem("삭제");
    JMenuItem quit = new JMenuItem("종료");
    JMenuBar mb = new JMenuBar();
 
    String[] name = { "id", "name", "age", "addr" };
 
    DefaultTableModel dt = new DefaultTableModel(name, 0);
    JTable jt = new JTable(dt);
    JScrollPane jsp = new JScrollPane(jt);
 
    /*
     * South 영역에 추가할 Componet들
     */
    JPanel search = new JPanel();
    String[] comboName = { "  ALL  ", "  ID  ", " name ", " addr " };
 
    JComboBox combo = new JComboBox(comboName);
    JTextField jtf = new JTextField(20);
    JButton serach = new JButton("검색");
 
    userDAO dao = new userDAO();
	private JTextField idField;
	private JPasswordField passField;
 
    /**
     * 화면구성 및 이벤트등록
     */
    public MenuJTabaleExam() {
       
        super("GUI 회원관리프로그램 - DB연동");
 
        //메뉴아이템을 메뉴에 추가
        m.add(insert);
        m.add(update);
        m.add(delete);
        m.add(quit);
        //메뉴를 메뉴바에 추가
        mb.add(m);
        mb.setVisible(false);
        
        //윈도우에 메뉴바 세팅
        setJMenuBar(mb);
        search.setBounds(0, 307, 703, 33);
       
        // South영역
        search.setBackground(Color.LIGHT_GRAY);
        search.add(combo);
        search.add(jtf);
        search.add(serach);
        getContentPane().setLayout(null);
        search.setVisible(false);
        
        //로그인 패널 만들기
        JPanel LoginPanel = new JPanel();
        LoginPanel.setBounds(0, 0, 703, 340);
        getContentPane().add(LoginPanel);
        jsp.setBounds(0, 0, 703, 307);
 
        final String ID="yun";
        final String PASS="qwer";
        LoginPanel.setLayout(null);
        
        passField = new JPasswordField();
        passField.setFont(new Font("Arial", Font.PLAIN, 26));
        passField.setBounds(353, 196, 147, 30);
        passField.setBorder(null);
        LoginPanel.add(passField);
        
        idField = new JTextField();
        idField.setFont(new Font("Arial", Font.PLAIN, 26));
        idField.setBounds(353, 146, 147, 30);
        LoginPanel.add(idField);
        idField.setColumns(10);
        idField.setBorder(null);   //Border     
        
        JLabel idlbl = new JLabel("I D : ");
        idlbl.setFont(new Font("굴림", Font.BOLD, 19));
        idlbl.setBounds(282, 148, 49, 30);
        LoginPanel.add(idlbl);
        
        JLabel lblPw = new JLabel("PW : ");
        lblPw.setFont(new Font("굴림", Font.BOLD, 19));
        lblPw.setBounds(282, 196, 49, 30);
        LoginPanel.add(lblPw);
        
        JLabel label = new JLabel("회원 관리 프로그램");
        label.setForeground(Color.RED);
        label.setFont(new Font("굴림", Font.BOLD, 26));
        label.setBounds(282, 39, 290, 63);
        LoginPanel.add(label);
        
        //로그인 버튼
        JButton logInBtn = new JButton("login");
        logInBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 if(ID.equals(idField.getText()) && PASS.equals(passField.getText())){
        			 LoginPanel.setVisible(false);  
        			 search.setVisible(true);
        			 mb.setVisible(true);
        			 JOptionPane.showMessageDialog(null,"Log In Success");
        		 }else{
                     JOptionPane.showMessageDialog(null,"You Failed to Log In");
        		 }
        	}	 
        });
        logInBtn.setIcon(new ImageIcon("C:\\workspace\\transfer\\image\\button.jpg"));
        logInBtn.setPressedIcon(new ImageIcon("C:\\workspace\\transfer\\image\\btnClicked.jpg"));
        
        logInBtn.setBounds(327, 246, 158, 38);
        LoginPanel.add(logInBtn);
        
        getContentPane().add(jsp);
        getContentPane().add(search);
 
        setSize(719, 400);
        setVisible(true);
 
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        // 이벤트등록
        insert.addActionListener(this);
        update.addActionListener(this);
        delete.addActionListener(this);
        quit.addActionListener(this);
        serach.addActionListener(this);
 
        // 모든레코드를 검색하여 DefaultTableModle에 올리기
        dao.userSelectAll(dt);
       
        //첫번행 선택.
        if (dt.getRowCount() > 0)
            jt.setRowSelectionInterval(0, 0);
 
    }// 생성자끝
 
    /**
     * main메소드 작성
     */
    public static void main(String[] args) {
        new MenuJTabaleExam();
    }
 
    /**
     * 가입/수정/삭제/검색기능을 담당하는 메소드
     * */
 
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == insert) {// 가입 메뉴아이템 클릭
            new UserJDailogGUI(this, "가입");
 
        } else if (e.getSource() == update) {// 수정 메뉴아이템 클릭
            new UserJDailogGUI(this, "수정");
 
        } else if (e.getSource() == delete) {// 삭제 메뉴아이템 클릭
            // 현재 Jtable의 선택된 행과 열의 값을 얻어온다.
            int row = jt.getSelectedRow();
            System.out.println("선택행 : " + row);
 
            Object obj = jt.getValueAt(row, 0);// 행 열에 해당하는 value
            System.out.println("값 : " + obj);
 
            if (dao.userDelete(obj.toString()) > 0) {
                UserJDailogGUI.messageBox(this, "레코드 삭제되었습니다.");
               
                //리스트 갱신
                dao.userSelectAll(dt);
                if (dt.getRowCount() > 0)
                    jt.setRowSelectionInterval(0, 0);
 
            } else {
                UserJDailogGUI.messageBox(this, "레코드가 삭제되지 않았습니다.");
            }
 
        } else if (e.getSource() == quit) {// 종료 메뉴아이템 클릭
            System.exit(0);
 
        } else if (e.getSource() == serach) {// 검색 버튼 클릭
            // JComboBox에 선택된 value 가져오기
            String fieldName = combo.getSelectedItem().toString();
            System.out.println("필드명 " + fieldName);
 
            if (fieldName.trim().equals("ALL")) {// 전체검색
                dao.userSelectAll(dt);
                if (dt.getRowCount() > 0)
                    jt.setRowSelectionInterval(0, 0);
            } else {
                if (jtf.getText().trim().equals("")) {
                    UserJDailogGUI.messageBox(this, "검색단어를 입력해주세요!");
                    jtf.requestFocus();
                } else {// 검색어를 입력했을경우
                    dao.getUserSearch(dt, fieldName, jtf.getText());
                    if (dt.getRowCount() > 0)
                        jt.setRowSelectionInterval(0, 0);
                }
            }
        }
 
    }//actionPerformed()----------
}