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
	public boolean insert(USER_INFO login_info) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

			// SQL文を準備する(名前, 社員番号, 秘密の質問の答え,ID, PW,)
			String sql = "insert into USER_INFO values (?, ?, ?, ?, ?,)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (login_info.getUser_name() != null && !login_info.getUser_name().equals("")) {
				pStmt.setString(1, login_info.getUser_name());
			}
			else {
				pStmt.setString(1, null);
			}

			if (login_info.getUser_en() != null && !login_info.getUser_en().equals("")) {
				pStmt.setString(2, login_info.getUser_en());
			}
			else {
				pStmt.setString(2, null);
			}

			if (login_info.getUser_sa() != null && !login_info.getUser_sa().equals("")) {
				pStmt.setString(3, login_info.getUser_sa());
			}
			else {
				pStmt.setString(3, null);
			}

			if (login_info.getUser_id() != null && !login_info.getUser_id().equals("")) {
				pStmt.setString(4, login_info.getUser_id());
			}
			else {
				pStmt.setString(4, null);
			}

			if (login_info.getUser_pw() != null && !login_info.getUser_pw().equals("")) {
				pStmt.setString(5, login_info.getUser_pw());
			}
			else {
				pStmt.setString(5, null);
			}

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

		//[秘密の質問]
		public List<USER_INFO> select(USER_INFODao param) {
			Connection conn = null;
			List<USER_INFO> cardList = new ArrayList<USER_INFO>();

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

				// SQL文を準備する
				String sql = "select * from  WHERE NUMBER LIKE ? AND NAME LIKE ? AND ADDRESS LIKE ? ORDER BY NUMBER";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				if (param.getUser_en() != null) {
					pStmt.setString(1, param.getUser_en());
				}
				else {
					pStmt.setString(1, null);
				}
				if (param.getName() != null) {
					pStmt.setString(2, "%" + param.getName() + "%");
				}
				else {
					pStmt.setString(2, "%");
				}
				if (param.getAddress() != null) {
					pStmt.setString(3, "%" + param.getAddress() + "%");
				}
				else {
					pStmt.setString(3, "%");
				}

				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				while (rs.next()) {
					Bc card = new Bc(
					rs.getString("NUMBER"),
					rs.getString("COMPANY"),
					rs.getString("DEPARTMENT"),
					rs.getString("POSITION"),
					rs.getString("NAME"),
					rs.getString("ZIPCODE"),
					rs.getString("ADDRESS"),
					rs.getString("PHONE"),
					rs.getString("FAX"),
					rs.getString("EMAIL"),
					rs.getString("REMARKS")
					);
					cardList.add(card);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
				cardList = null;
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				cardList = null;
			}
			finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
						cardList = null;
					}
				}
			}

			// 結果を返す
			return cardList;
		}
}

