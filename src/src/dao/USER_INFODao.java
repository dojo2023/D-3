package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.USER_INFO;

public class USER_INFODao {
	//ログインできるならtrueを返す
	public boolean isLoginOK(String id, String pw) {
		Connection conn = null;
		boolean loginResult = false;

		try {
			//JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			//データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

			//SELECT文を準備する
			String sql = "select count(*) from USER_INFO where USER_ID = ? and USER_PW = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);
			pStmt.setString(2, pw);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// ユーザーIDとパスワードが一致するユーザーがいたかどうかをチェックする
			rs.next();
			if (rs.getInt("count(*)") == 1) {
				loginResult = true;
			}
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
		return loginResult;
	}


	// [アカウント新規登録]引数login_infoで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(String en, String name, String id, String pw, String sq, String sa) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

			// SQL文を準備する(名前, 社員番号, 秘密の質問id,秘密の質問の答え,ID, PW)
			String sql = "insert into USER_INFO(USER_NAME,USER_EN,USER_SQ_ID,USER_SA,USER_ID,"
					+ "USER_PW,USER_MODE_SWITCH,CATEGORY_ID,HASHTAGS_ID,FREE_WORD,FAVORITE_SWITCH)"
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";  //中身の指定
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (name != null && !name.equals("")) {
				pStmt.setString(1, name);
			}
			else {
				pStmt.setString(1, null);
			}

			if (en != null && en.equals("")) {
				pStmt.setString(2, en);
			}
			else {
				pStmt.setString(2, null);
			}

			if (sq != null && !sq.equals("")) {
				pStmt.setString(3, sq);
			}
			else {
				pStmt.setString(3, null);
			}

			if (sa != null && !sa.equals("")) {
				pStmt.setString(4, sa);
			}
			else {
				pStmt.setString(4, null);
			}

			if (id != null && !id.equals("")) {
				pStmt.setString(5, id);
			}
			else {
				pStmt.setString(5, null);
			}

			if (pw != null && !pw.equals("")) {
				pStmt.setString(6, pw);
			}
			else {
				pStmt.setString(6, null);
			}
			pStmt.setInt(7, 0);
			pStmt.setInt(8, 0);
			pStmt.setInt(9, 0);
			pStmt.setString(10, "");
			pStmt.setInt(11, 0);

			//SQL文を実行する
			if(pStmt.executeUpdate() == 1) {
				result = true;
			}
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
		return result;
	}

	//[秘密の質問]引数paramで検索項目を指定し、検索結果のリストを返す
	public List<USER_INFO> select(USER_INFO param) {
		Connection conn = null;
		List<USER_INFO> userList = new ArrayList<USER_INFO>();

		try {
			// JDUSER_INFOドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

			// SQL文を準備する
			String sql = "select * from USER_INFO "
					+ "WHERE USER_EN LIKE ? "
					+ "AND USER_ID  LIKE ? "
					+ " ORDER BY USER_EN";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (param.getUser_en() != null && !param.getUser_en().equals("")) {
				pStmt.setString(1, "%" + param.getUser_en() + "%");
			}else {
				pStmt.setString(1, "%");
			}

			if (param.getUser_id() != null && !param.getUser_id().equals("")) {
				pStmt.setString(2, "%" + param.getUser_id() + "%");
			}else {
				pStmt.setString(2, "%");
			}

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				USER_INFO card = new USER_INFO(
						rs.getString("USER_EN"), //NOT NULL
						rs.getString("USER_NAME"),//NOT NULL
						rs.getString("USER_ID"),
						rs.getString("USER_PW"),//NOT NULL
						rs.getString("USER_SQ_ID"),
						rs.getString("USER_SA"),//NOT NULL
						rs.getInt("USER_MODE_SWITCH"),
						rs.getInt("CATEGORY_ID"),//NOT NULL
						rs.getInt("HASHTAGS_ID"),
						rs.getString("FREE_WORD"),
						rs.getInt("FAVORITE_SWITCH")
						);
				userList.add(card);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			userList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			userList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					userList = null;
				}
			}
		}

		// 結果を返す
		return userList;
	}


	//[USER_INFOのデータの再設定]引数login_infoで指定されたレコードを更新し、成功したらtrueを返す
	public boolean update(USER_INFO login_info) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDUSER_INFOドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

			// SQL文を準備する
			String sql = "update USER_INFO set "
					+ "USER_EN=?, "
					+ "USER_PW=?,"
					+ "USER_MODE_SWITCH=?, "
					+ "CATEGORY_ID=?, "
					+ "HASHTAGS_ID=? "
					+ "FREE_WORD=? "
					+ "FAVORITE_SWITCH=? "
					+ "where USER_ID=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (login_info.getUser_en() != null && !login_info.getUser_en().equals("")) {
				pStmt.setString(1, login_info.getUser_en());
			}
			else {
				pStmt.setString(1, null);
			}

			if (login_info.getUser_pw() != null && !login_info.getUser_pw().equals("")) {
				pStmt.setString(2, login_info.getUser_pw());
			}
			else {
				pStmt.setString(2, null);
			}


			pStmt.setInt(3, login_info.getUser_mode_switch());
			pStmt.setInt(4, login_info.getCategory_id());
			pStmt.setInt(5, login_info.getHashtags_id());

			if (login_info.getFree_word() != null && !login_info.getFree_word().equals("")) {
				pStmt.setString(6, login_info.getFree_word());
			}
			else {
				pStmt.setString(6, null);
			}

			pStmt.setInt(7, login_info.getFavorite_switch());
			pStmt.setString(8, login_info.getUser_id());

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
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
		return result;
	}
}