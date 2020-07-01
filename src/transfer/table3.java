package transfer;

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

public class table3 extends JPanel {

   private final String ID="yun";
   private final String PASS="qwer";
   private JFrame frame;
   private JTextField idField;
   private JPasswordField passField;
   private JScrollPane scrollPane;
   private JButton addBtn = null;
   private JButton   saveBtn = null;
   private JButton editBtn = null;
   private JButton deleteBtn = null;
   private JPanel  currPanel;     
   
   private   String driver = "oracle.jdbc.driver.OracleDriver";
   private   String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
   private   String user = "user3";
   private   String pass ="oracle";
   private String colNames[] = {"   ̵ "," ̸ ","    "};
   private DefaultTableModel model = new DefaultTableModel(colNames, 0);
   
   private Connection con = null;
   private PreparedStatement pstmt = null;
   private ResultSet rs=null;
   private JTable table;
   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               table3 window = new table3();
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
   public table3() {
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
      LoginPanel.setBounds(0, 0, 938, 635);
      frame.getContentPane().add(LoginPanel);
      
      idField = new JTextField();
      idField.setFont(new Font("Arial", Font.PLAIN, 26));
      idField.setBounds(600, 411, 260, 43);
      LoginPanel.add(idField);
      idField.setColumns(10);
      idField.setBorder(null);   //Border     
      
      passField = new JPasswordField();
      passField.setFont(new Font("Arial", Font.PLAIN, 26));
      passField.setBounds(600, 491, 260, 43);
      passField.setBorder(null);
      LoginPanel.add(passField);
      
      //리스트 패널 만들기    
      JPanel listpanel = new JPanel();
      listpanel.setBounds(0, 0, 784, 561);
      frame.getContentPane().add(listpanel);
      listpanel.setLayout(null);
      listpanel.setVisible(false);
      
      table = new JTable(model);
      table.setBounds(150, 88, 607, 440);
      listpanel.add(table);
      
      JLabel tableTitle = new JLabel("\uAC70\uB798 \uB0B4\uC5ED");
      tableTitle.setBounds(400, 24, 195, 66);
      tableTitle.setFont(new Font("    ", Font.BOLD, 28));
      listpanel.add(tableTitle);
      
      JButton addBtn_1 = new JButton("add");
      addBtn_1.setBounds(14, 159, 104, 56);
      addBtn_1.setFont(new Font("    ", Font.BOLD, 18));
      listpanel.add(addBtn_1);
      
      JButton saveBtn_1 = new JButton("save");
      saveBtn_1.setBounds(14, 236, 104, 56);
      saveBtn_1.setFont(new Font("    ", Font.BOLD, 18));
      listpanel.add(saveBtn_1);
      
      JButton editBtn_1 = new JButton("edit");
      editBtn_1.setBounds(14, 316, 104, 56);
      editBtn_1.setFont(new Font("    ", Font.BOLD, 18));
      listpanel.add(editBtn_1);
      
      JButton deleteBtn_1 = new JButton("exit");
      deleteBtn_1.setBounds(14, 391, 104, 56);
      deleteBtn_1.setFont(new Font("    ", Font.BOLD, 18));
      listpanel.add(deleteBtn_1);
      
      JButton logInBtn = new JButton("login");
      logInBtn.setBounds(551, 572, 338, 38);
//      frame.getContentPane().add(logInBtn);
      logInBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            //ID   PW   ġ Ȯ     
            if(ID.equals(idField.getText()) && PASS.equals(passField.getText())){
               // α          ޼        ϱ 
               LoginPanel.setVisible(false);
               // α            α     г         
               listpanel.setVisible(true);
               JOptionPane.showMessageDialog(null,"Log In Success");
//               currPanel = sumPanel;
            }else{
               JOptionPane.showMessageDialog(null,"You Failed to Log In");
            }
         }
      });
      // α      ư  ̹     ֱ 
      logInBtn.setIcon(new ImageIcon("C:\\workspace\\SWING1\\image\\button.jpg"));
      // α      ư Ŭ      ̹      ȭ
      logInBtn.setPressedIcon(new ImageIcon("C:\\workspace\\SWING1\\image\\btnClicked.jpg"));
      
      select();
      LoginPanel.add(logInBtn);
      
      //add  г      
      JPanel addPanel = new JPanel();
      addPanel.setBounds(0, 0, 938, 635);
      frame.getContentPane().add(addPanel);
      addPanel.setLayout(null);
      addPanel.setVisible(false);
      JLabel addLbl = new JLabel("New label");
      addLbl.setBounds(0, 0, 62, 18);
      addPanel.add(addLbl);
   }
   
   //   ̺                    Ŭ     DB            
   private void select() {
      String query = "SELECT ID, NAME, GENDER FROM SWING";
      try {
         Class.forName(driver);
         con = DriverManager.getConnection(url,user,pass);
         pstmt = con.prepareStatement(query);
         rs=pstmt.executeQuery();
         
         while(rs.next()) {
            model.addRow(new Object[] {rs.getString("id"), rs.getString("name"),
                  rs.getString("gender")});   
         }
      } catch (Exception e) {
         System.out.println(e.getMessage());
      }   finally {
         try {
            rs.close();
            pstmt.close();
            con.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }
}