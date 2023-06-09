package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class USER_SQDao {
	// ログインできるならtrueを返す
	public String SQ_return(String id) {
		Connection conn = null;
		String SQA = "";

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

			// SELECT文を準備する
			String sql = "select USER_SQ_NAME from USER_SQ where USER_SQ_ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, "0001");

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			SQA = rs.getString("USER_SQ_NAME");

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 結果を返す
		return SQA;
	}
}
