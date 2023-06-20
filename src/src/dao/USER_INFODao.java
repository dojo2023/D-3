package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	/*
	insert関数の振る舞い
	入力：USER_INFOテーブルのうち、新規登録時に必要な6つのデータ(String)
	処理：USER_INFOテーブルに引数の6つのデータが入ったレコードを登録する
	      (他のフィールドは初期値)
	出力：挿入に成功したらtrue、失敗したらfalseが返される

	Servletでのinsertの使い方
	①insertはUSER_INFODaoで定義されているメソッドなのでUSER_INFODaoをインスタンス化する
	  USER_INFODao user_dao(自由名) = new USER_INFODao();
	②インスタンス化したuser_daoでinsertを実行する
	  引数は新規登録する際に必要な6つのデータ
	  USER_INFO user = user_dao.insert("D029", "小神野", "ogamino0817", "password", "1", "千葉");
	以上で指定したデータが入ったレコードがUSER_INFOテーブルに格納される
	*/
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
			pStmt.setInt(7, 1);
			pStmt.setInt(8, 0);
			pStmt.setInt(9, 0);
			pStmt.setString(10, "");
			pStmt.setInt(11, 1);

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

	/*
	select関数の振る舞い
	入力：ユーザの社員番号(String)とID(String)（必ずどちらかは空文字でもう一方には値が入ってる）
	処理：値が入っているほうの引数でUSER_INFOテーブルに検索をかけて該当するレコードを探索
	出力：該当したレコード(USER_INFO)

	Servletでのselectの使い方
	①selectはUSER_INFODaoで定義されているメソッドなのでUSER_INFODaoをインスタンス化する
	  USER_INFODao user_dao(自由名) = new USER_INFODao();
	②インスタンス化したuser_daoでselectを実行する
	  引数はidか社員番号、探索したいデータのみいれもう一方は空文字（以下はidを探索したい例）
	  USER_INFO user = user_dao.select("", 探索したいid);
	以上で指定したid（か社員番号）のレコードがuserに格納される
	*/

	public USER_INFO select(String en, String id) {
		Connection conn = null;
		USER_INFO user = new USER_INFO();

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
			if (en != null && !en.equals("")) {
				pStmt.setString(1, en);
			}else {
				pStmt.setString(1, "%");
			}

			if (id != null && !id.equals("")) {
				pStmt.setString(2, id);
			}else {
				pStmt.setString(2, "%");
			}

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			if(rs.next()) {
					user.setUser_en(rs.getString("USER_EN"));
					user.setUser_name(rs.getString("USER_NAME"));
					user.setUser_id(rs.getString("USER_ID"));
					user.setUser_pw(rs.getString("USER_PW"));
					user.setUser_sq_id(rs.getString("USER_SQ_ID"));
					user.setUser_sa(rs.getString("USER_SA"));
					user.setUser_mode_switch(rs.getInt("USER_MODE_SWITCH"));
					user.setCategory_id(rs.getInt("CATEGORY_ID"));
					user.setHashtags_id(rs.getInt("HASHTAGS_ID"));
					user.setFree_word(rs.getString("FREE_WORD"));
					user.setFavorite_switch(rs.getInt("FAVORITE_SWITCH"));
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
		return user;
	}

	/*
	update関数の振る舞い
	入力：updateしたいidと他データが入ったUSER_INFO(他は初期値)
	処理：idを元にデータを更新したいテーブルをピックアップ
	      その後入力されたUSER_INFOのフィールドのうち値が入っているフィールドをその値に更新
	      （初期値が入っているフィールドはなにもしない）
	出力：更新に成功したらtrue、失敗したらfalseが返される

	Servletでのupdateの使い方
	①空のUSER_INFOをインスタンス化：USER_INFO user(自由な名前でok) = new USER_INFO();
	②今ログインしているユーザのidをセッションスコープから取得する
	  HttpSession session = request.getSession();
	  String id(自由名) = ((LOGIN_USER)session.getAttribute("LOGIN_USER")).getId();
	③入手したidと変更したいデータを作ったuserに格納する（以下はpwを変更する例）
	  user.setUser_id(id)
	  user.setUser_pw(ここに変更したいpwを入れる)
	④作成したuserをupdateに渡す(updateが定義されているdaoも宣言する)
	  USER_INFODao user_dao(自由名) = new USER_INFODao();
	  boolean result(自由名) = user_dao.update(user);
	以上で指定したidを持つユーザのpwの変更完了
	情報の変更が成功していたならresultにはtrueが、失敗していたならfalseが格納されている
	*/
	public boolean update(USER_INFO login_info) {
		Connection conn = null;
		boolean result = false;

		USER_INFODao user_dao = new USER_INFODao();
		USER_INFO user = user_dao.select("", login_info.getUser_id());
		try {
			// JDUSER_INFOドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

			// SQL文を準備する
			String sql = "update USER_INFO set "
					+ "USER_EN = ?,"
					+ "USER_PW = ?,"
					+ "USER_MODE_SWITCH = ?,"
					+ "CATEGORY_ID = ?,"
					+ "HASHTAGS_ID = ?,"
					+ "FREE_WORD = ?,"
					+ "FAVORITE_SWITCH = ? "
					+ "where USER_ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (login_info.getUser_en() != null && !login_info.getUser_en().equals("")) {
				pStmt.setString(1, login_info.getUser_en());
			}
			else {
				pStmt.setString(1, user.getUser_en());
			}

			if (login_info.getUser_pw() != null && !login_info.getUser_pw().equals("")) {
				pStmt.setString(2, login_info.getUser_pw());
			}
			else {
				pStmt.setString(2, user.getUser_pw());
			}

			if(login_info.getUser_mode_switch() != 0) {
				pStmt.setInt(3, login_info.getUser_mode_switch());
			}
			else {
				pStmt.setInt(3, user.getUser_mode_switch());
			}

			if(login_info.getCategory_id() != 0) {
				pStmt.setInt(4, login_info.getUser_mode_switch());
			}
			else {
				pStmt.setInt(4, user.getCategory_id());
			}

			if(login_info.getHashtags_id() != 0) {
				pStmt.setInt(5, login_info.getHashtags_id());
			}
			else {
				pStmt.setInt(5, user.getHashtags_id());
			}

			if (login_info.getFree_word() != null && !login_info.getFree_word().equals("")) {
				pStmt.setString(6, login_info.getFree_word());
			}
			else {
				pStmt.setString(6, user.getFree_word());
			}

			if(login_info.getFavorite_switch() != 0) {
				pStmt.setInt(7, login_info.getFavorite_switch());
			} else {
				pStmt.setInt(7, user.getFavorite_switch());
			}

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