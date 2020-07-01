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
    private String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";        // @호스트 IP : 포트 : SID
    private String colNames[] = {"아이디","이름","성별","섯다점수","최근접속날짜"};  // 테이블 컬럼 값들
    private DefaultTableModel model = new DefaultTableModel(colNames, 0); //  테이블 데이터 모델 객체 생성
    ]
    
}
