package transfer;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Table extends JPanel{
	// DB���� ���� ȭ������ ���̺� �� ��������(select) , �����ϱ�(insert), �����ϱ�(update), �����ϱ�(delete)
	
	     private static final long serialVersionUID = 1L;
	
	     private JButton jBtnAddRow = null;    // ���̺� ���� �߰� ��ư
	     private JButton jBtnSaveRow = null;    // ���̺� ���� ���� ��ư
	     private JButton jBtnEditRow = null;    // ���̺� ���� ���� ��ư
	     private JButton jBtnDelRow = null;        // ���̺� ���� ���� ��ư
	     private JTable table;   
	     private JScrollPane scrollPane;        // ���̺� ��ũ�ѹ� �ڵ����� �����ǰ� �ϱ�
	
	     private String driver = "oracle.jdbc.driver.OracleDriver";       
	     private String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";        // @ȣ��Ʈ IP : ��Ʈ : SID
	     private String colNames[] = {"���̵�","�̸�","����","��������","�ֱ����ӳ�¥"};  // ���̺� �÷� ����
	     private DefaultTableModel model = new DefaultTableModel(colNames, 0); //  ���̺� ������ �� ��ü ����
	
	     private Connection con = null;
	     private PreparedStatement pstmt = null;
	     private ResultSet rs = null;   // ���Ϲ޾� ����� ��ü ���� ( select���� ������ �� �ʿ� )
	
	     public void JdbcJtableTest01() {
	
	         setLayout(null);        // ���̾ƿ� ��ġ������ ����
	         table = new JTable(model);  // ���̺� �𵨰�ü ����
	         table.addMouseListener(new JTableMouseListener());        // ���̺� ���콺������ ����
	         scrollPane = new JScrollPane(table);            // ���̺� ��ũ�� ����� �ϱ�
	         scrollPane.setSize(500, 200);
	         add(scrollPane);       
	         initialize();   
	         select();
	     }
	
	     private class JTableMouseListener implements MouseListener{    // ���콺�� ��������Ȯ���ϱ�
	
	         public void mouseClicked(java.awt.event.MouseEvent e) {    // ���õ� ��ġ�� ���� ���
	
	             JTable jtable = (JTable)e.getSource();
	             int row = jtable.getSelectedRow();                // ���õ� ���̺��� �ప
	             int col = jtable.getSelectedColumn();         // ���õ� ���̺��� ����
	
	             System.out.println(model.getValueAt(row, col));   // ���õ� ��ġ�� ���� ���� ���
	         }
	
	         public void mouseEntered(java.awt.event.MouseEvent e) {
	         }
	
	         public void mouseExited(java.awt.event.MouseEvent e) {   
	         }
	
	         public void mousePressed(java.awt.event.MouseEvent e) {
	         }
	
	         public void mouseReleased(java.awt.event.MouseEvent e) {
	         }
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
	
	     private void initialize() {            // �׼��̺�Ʈ�� ��ư ������Ʈ ����
	
	     // ���̺� ���� ���� �߰��ϴ� �κ�
	         jBtnAddRow = new JButton();
	         jBtnAddRow.addActionListener(new ActionListener() {   
	
	             public void actionPerformed(ActionEvent e) {
	                 System.out.println(e.getActionCommand());        // ���õ� ��ư�� �ؽ�Ʈ�� ���
	                 DefaultTableModel model2 = (DefaultTableModel)table.getModel();
	                 model2.addRow(new String[]{"","","","",""});            // �����̺��� �ʱⰪ
	             }
	
	         });
	
	         jBtnAddRow.setBounds(30,222,120, 25);
	         jBtnAddRow.setText("�߰�");
	         add(jBtnAddRow);
	
	     // ���̺� ���� �����ϴ� �κ�
	
	         jBtnSaveRow = new JButton();
	         jBtnSaveRow.addActionListener(new ActionListener() {
	
	             public void actionPerformed(ActionEvent e) {
	                 System.out.println(e.getActionCommand());        // ���õ� ��ư�� �ؽ�Ʈ�� ���
	                 DefaultTableModel model2 = (DefaultTableModel)table.getModel();
	
	                 int row = table.getSelectedRow();
	                 if(row<0) return;     // ������ �ȵ� ���¸� -1����
	                 String query = "insert into user_info(id, name, gender, sutdascore, LATEST)"
	                       + "values (?,?,?,?,SYSDATE)";
	
	                 try{
	                     Class.forName(driver);  // ����̹� �ε�
	                     con = DriverManager.getConnection(url, "aaaa", "aaaa"); // DB ����
	                     pstmt = con.prepareStatement(query);  
	
	                     // ����ǥ�� 4�� �̹Ƿ� 4�� ���� �Է�������Ѵ�.
	
	                     pstmt.setString(1, (String) model2.getValueAt(row, 0));
	                     pstmt.setString(2, (String) model2.getValueAt(row, 1));
	                     pstmt.setString(3, (String) model2.getValueAt(row, 2));
	                     pstmt.setString(4, (String) model2.getValueAt(row, 3));
	
	                     int cnt = pstmt.executeUpdate();
	
	                     //pstmt.executeUpdate(); create insert update delete
	                     //pstmt.executeQuery(); select
	
	                 }catch(Exception eeee){
	                     System.out.println(eeee.getMessage());
	
	                 }finally{
	                     try {
	                         pstmt.close();
	                         con.close();
	                     } catch (Exception e2) {}
	                 }
	
	                 model2.setRowCount(0);         // ��ü ���̺� ȭ���� ������
	                 select();          // ���� �� �ٽ� ��ü ������ �޾ƿ�.
	
	             }
	
	         });
	
	         jBtnSaveRow.setBounds(182,222,120, 25);
	         jBtnSaveRow.setText("����");
	         add(jBtnSaveRow);
	
	         // ���õ� ���̺� ���� �����ϴ� �κ�
	
	         jBtnEditRow = new JButton();
	         jBtnEditRow.addActionListener(new ActionListener() {
	
	             public void actionPerformed(ActionEvent e) {       
	
	                 System.out.println(e.getActionCommand());   // ���õ� ��ư�� �ؽ�Ʈ�� ���
	                 DefaultTableModel model2 = (DefaultTableModel)table.getModel();
	                 int row = table.getSelectedRow();
	                 if(row<0) return;     // ������ �ȵ� ���¸� -1����
	
	                 String query = "UPDATE user_info SET name=?, gender=?, sutdascore=? "
	                                 +"where id=?";
	
	                 try{
	                     Class.forName(driver);  // ����̹� �ε�
	                     con = DriverManager.getConnection(url, "aaaa", "aaaa"); // DB ����
	                     pstmt = con.prepareStatement(query);  
	
	                     // ����ǥ�� 4�� �̹Ƿ� 4�� ���� �Է�������Ѵ�.
	
	                     pstmt.setString(1, (String) model2.getValueAt(row, 1));
	                     pstmt.setString(2, (String) model2.getValueAt(row, 2));
	                     pstmt.setString(3, (String) model2.getValueAt(row, 3));
	                     pstmt.setString(4, (String) model2.getValueAt(row, 0));
	
	                     int cnt = pstmt.executeUpdate();
	
	                     //pstmt.executeUpdate(); create insert update delete
	                     //pstmt.executeQuery(); select
	
	                 }catch(Exception eeee){
	                     System.out.println(eeee.getMessage());
	
	                 }finally{
	                     try {
	                         pstmt.close();
	                         con.close();
	                     } catch (Exception e2) {}
	                 }
	                 model2.setRowCount(0);         // ��ü ���̺� ȭ���� ������
	                 select();          // ���� �Ĵٽ� ��ü ������ �޾ƿ�.
	             }
	
	         });
	
	         jBtnEditRow.setBounds(182,270,120, 25);
	         jBtnEditRow.setText("����");
	         add(jBtnEditRow);
	
	     // ���õ� ���̺� ���� �����ϴ� �κ�
	
	         jBtnDelRow = new JButton();
	         jBtnDelRow.addActionListener(new ActionListener() {
	             public void actionPerformed(java.awt.event.ActionEvent e) {
	                 System.out.println(e.getActionCommand());        // ���õ� ��ư�� �ؽ�Ʈ�� ���
	                 DefaultTableModel model2 = (DefaultTableModel)table.getModel();
	                 int row = table.getSelectedRow();
	                 if(row<0) return; // ������ �ȵ� ���¸� -1����
	                 String query = "delete from user_info where id= ?";
	
	                 try{
	
	                     Class.forName(driver);  // ����̹� �ε�
	                     con = DriverManager.getConnection(url, "aaaa", "aaaa"); // DB ����
	                     pstmt = con.prepareStatement(query);  
	
	                     // ����ǥ�� 1�� �̹Ƿ� 4�� ���� �Է�������Ѵ�.
	
	                     pstmt.setString(1, (String) model2.getValueAt(row, 0));
	                     int cnt = pstmt.executeUpdate();
	
	                     //pstmt.executeUpdate(); create insert update delete
	
	                     //pstmt.executeQuery(); select
	
	                 }catch(Exception eeee){
	                     System.out.println(eeee.getMessage());
	
	                 }finally{
	                     try {
	                         pstmt.close();con.close();
	                     } catch (Exception e2) {}
	                 }
	                 model2.removeRow(row);    // ���̺� ���� ���� ����
	             }
	
	         });
	
	         jBtnDelRow.setBounds(new Rectangle(320, 222, 120, 25));
	         jBtnDelRow.setText("����");
	         add(jBtnDelRow);
	
	     }
	      
	
	 public static void main(String[] args) {
	
	     JdbcJtableTest01 panel = new JdbcJtableTest01();
	
	     JFrame win = new JFrame();
	     win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     win.add(panel);
	     win.setSize(540,400);
	     win.setVisible(true);
	
	 }
	
	}

}
