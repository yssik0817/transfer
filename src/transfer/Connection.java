package transfer;

import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;

public class Connection {
	
	private static Connection con;
	private static java.sql.Statement sql;
	private ResultSet rs;
	
	public static Connection DbConnection() {
		try {
			 Class.forName("mysql.jdbc.Driver");
		} catch (Exception e) {
			// TODO: handle exception
		}
	private String driver = "oracle.jdbc.driver.OracleDriver";       
    private String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";        // @ȣ��Ʈ IP : ��Ʈ : SID
    private String colNames[] = {"���̵�","�̸�","����","��������","�ֱ����ӳ�¥"};  // ���̺� �÷� ����
    private DefaultTableModel model = new DefaultTableModel(colNames, 0); //  ���̺� ������ �� ��ü ����
    ]
    
}
