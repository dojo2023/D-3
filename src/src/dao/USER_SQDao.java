package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//入力：秘密の質問ID(String)
//処理：IDに対応した秘密の質問文を探索する
//出力：秘密の質問の文(String)
public class USER_SQDao {
	// ログインできるならtrueを返す
	public String SQ_return(String id) {
		Connection conn = null;
		String SQ = "";

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

			// SELECT文を準備する
			String sql = "SELECT * FROM USER_SQ WHERE USER_SQ_ID LIKE ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			rs.next();
			SQ = rs.getString("USER_SQ_NAME");


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
		return SQ;
	}
}