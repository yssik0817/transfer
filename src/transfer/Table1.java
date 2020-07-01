package transfer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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



public class Table1 extends JPanel {
	public Table1() {
	}
	private JTable table;
	private JScrollPane scrollPane;
	private JButton addBtn = null;
	private JButton	saveBtn = null;
	private JButton editBtn = null;
	private JButton deleteBtn = null;
	
	
	private	String driver = "oracle.jdbc.driver.OracleDriver";
	private	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private	String user = "user3";
	private	String pass ="oracle";
	private String colNames[] = {"아이디","이름","성별"};
	private DefaultTableModel model = new DefaultTableModel(colNames, 0);
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs=null;
	
	public void Table1() {
		setLayout(null);
		table = new JTable(model);
		scrollPane = new JScrollPane(table);
		scrollPane.setSize(500,200);
		add(scrollPane);
		initialize();
		select();
	}
	
	private void initialize() {
		addBtn = new JButton();
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());
				DefaultTableModel model2= new DefaultTableModel();
				model2.addRow(new String [] {"",""});
			}
		});
		addBtn.setBounds(30,222,120,25);
		addBtn.setText("추가");
		add(addBtn);
	}

	private void select() {
		String query = "SELECT ID, NAME, GENDER FROM SWING";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,"user3","oracle");
			pstmt = con.prepareStatement(query);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				model.addRow(new Object[] {rs.getString("id"), rs.getString("name"),
						rs.getString("gender")});	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Table1 panel = new Table1();
		JFrame win = new JFrame();
		
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		win.getContentPane().add(panel);
		win.setSize(500,400);
		win.setVisible(true);
	}
	
	
	
}
