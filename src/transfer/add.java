package transfer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

private String driver = "oracle.jdbc.driver.OracleDriver";
private String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
private String user = "user3";
private String pass = "oracle";
private Connection con = null;
private PreparedStatement pstmt = null;
private ResultSet rs=null;

public class add {
	int result = 0;
	String query = "UPDATE SWING SET ID=?, NAME=?, GENDER=?";

	  try {
		  Class.forName(driver);
	      con = DriverManager.getConnection(url,user,pass);
	      pstmt = con.prepareStatement(query);
	      rs=pstmt.executeQuery();
	         
	      pstmt.setString(1, user.id.getText());
	      pstmt.setString(2, user.name.getText());
	      pstmt.setString(3, user.gender.getText());

          // 실행하기
          result = pstmt.executeUpdate();

      } catch (SQLException e) {
          System.out.println(e + "=> userUpdate fail");
      } finally {
    	  rs.close();
          pstmt.close();
          con.close();
      }

      return result;
  }
}