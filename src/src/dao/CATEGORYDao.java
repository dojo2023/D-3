package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CATEGORYDao {
	// 引数paramで検索項目を指定し、検索結果のリストを返す
	public List<String> select() {
		Connection conn = null;
		List<String> CATEGORYList = new ArrayList<>();

		try {
			// JDCATEGORYドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

			// SQL文を準備する
			String sql = "SELECT CATEGORY_NAME FROM CATEGORY "
					+ "ORDER BY CATEGORY_ID";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を実行し、結果を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果をリストに格納する
			int i = 0;
			while (rs.next()) {
				CATEGORYList.add(rs.getString(i));
				i++;
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース接続を閉じる
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return CATEGORYList;
	}



	// 引数listで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(String newname) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDCATEGORYドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

			// SQL文を準備する
			String sql = "INSERT INTO CATEGORY (CATEGORY_NAME) "
					+ "VALUES (?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, newname);

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 結果を返す
		return result;
	}

	// CATEGORY_IDを参照してCATEGORY＿NAMEを表示させるときの処理
	public String post_select(int CATEGORY_ID) {
		Connection conn = null;
		String category_name = "";

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

			// SQL文を準備する
			String sql = "SELECT * FROM CATEGORY WHERE CATEGORY_ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, CATEGORY_ID);

			// SQL文を実行し、結果を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果を確認する
			rs.next();
			category_name = rs.getString("CATEGORY_NAME");
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 結果を返す
		return category_name;

	}

}
