package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class USER_SQDao {
	// ログインできるならtrueを返す
	public String SQ_return(int id) {
		Connection conn = null;
		boolean loginResult = false;
		String SQ = "";

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

			// SELECT文を準備する
			String sql = "select USER_SQ_NAME from USER_SQ where USER_SQ_ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			Integer I_id = Integer.valueOf(id);
			String S_id = I_id.toString();
			pStmt.setString(1, S_id);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			SQ = rs.getString("USER_SQ_NAME");

		}
		catch (SQLException e) {
			e.printStackTrace();
			loginResult = false;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			loginResult = false;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					loginResult = false;
				}
			}
		}

		// 結果を返す
		return SQ;
	}
}
