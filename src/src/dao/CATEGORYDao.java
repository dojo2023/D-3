package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CATEGORYDao {
	//入力：なし
	//処理：カテゴリデータベースのカテゴリ名を全てselectする
	//出力：カテゴリデータベース内の全てのカテゴリ名が格納されたString型のList
	public List<String> select() {
		Connection conn = null;
		List<String> CATEGORYList = new ArrayList<String>();

		try {
			// JDCATEGORYドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

			// SQL文を準備する
			String sql = "SELECT CATEGORY_NAME FROM CATEGORY ORDER BY CATEGORY_ID";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を実行し、結果を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果をリストに格納する

			while (rs.next()) {
				CATEGORYList.add(rs.getString("CATEGORY_NAME"));
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

	//入力：カテゴリ名（String）
	//処理：入力されたカテゴリ名が既にあるか検索し、なければ新しいカテゴリとして追加する
	//出力：追加成功ならtrue、失敗（入力されたカテゴリ名が既にあった場合も）したならfalseが返される
	public boolean insert(String new_name) {
		Connection conn = null;
		boolean result = false;

		CATEGORYDao category_dao = new CATEGORYDao();
		List<String> category = category_dao.select();
		String category_name;

		for(int i = 0; i < category.size(); i++) {
			category_name = category.get(i);
			if(new_name.equals(category_name)) {
				return result;
			}
		}

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
			pStmt.setString(1, new_name);

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

	//入力：カテゴリID(String)
	//処理：入力されたカテゴリIDに対応したカテゴリ名をカテゴリデータベースから探索する
	//出力：入力されたカテゴリIDに対応したカテゴリ名(String)
	public String get_categoryname(int CATEGORY_ID) {
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
