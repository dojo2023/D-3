package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class HASHTAGSDao {
	//入力：挿入したいハッシュタグの名前(String型)
	//処理：受け取った名前が既にHASHTAGSデータベースにあるか探索
	//      なければ挿入、既にあれば何もしない
	//出力：挿入に成功（入力されたハッシュタグの名前が既にある場合も）したらtrue、しなければfalseを返す
	public boolean insert(String hashtag_name) {
		Connection conn = null;
		boolean result = false;

		HASHTAGSDao h_dao = new HASHTAGSDao();
		List<String> h_name = h_dao.select();

		for(int i = 0; i < h_name.size(); i++) {
			if(hashtag_name.equals(h_name.get(i)))
				result = true;
				return result;
		}

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

			// SQL文を準備する
			String sql = "INSERT INTO HASHTAGS(HASHTAGS_NAME) VALUES(?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (hashtag_name != null && !hashtag_name.equals("")) {
				pStmt.setString(1, hashtag_name);
			}
			else {
				pStmt.setString(1, null);
			}

			// SQL文を実行し、結果表を取得する
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


	//入力：なし
	//処理：HASHTAGSデータベースを検索して中にあるHASHTAGSの名前全てをListに入れて取り出す
	//出力：HASHTAGSデータベース内の全てのHASHTAGS_NAMEが格納されたString型のList
	public List<String> select() {
	    Connection conn = null;
	    List<String> hashtag_name = new ArrayList<String>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

			// SQL文を準備する
	        String sql = "SELECT HASHTAGS_NAME FROM HASHTAGS";
	        PreparedStatement pStmt = conn.prepareStatement(sql);

	        // SQL文を実行し、結果を取得する
	        ResultSet rs = pStmt.executeQuery();

	        // 結果を確認する
	        while(rs.next()) {
	        	hashtag_name.add(rs.getString("HASHTAGS_NAME"));
	        }


	    } catch (SQLException e) {
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
	    return hashtag_name;
	}

	//入力：HASHTAGSのID（int型）
	//処理：入力されたIDに対応したHASHTAGSの名前を探索して取り出す
	//出力：指定したHASHTAGSIDに対応したHASHTAGSの名前
	public String get_htagname(int htag_id) {
		Connection conn = null;
		String htag_name = "";

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

			// SQL文を準備する
			String sql = "SELECT HASHTAGS_NAME FROM HASHTAGS WHERE HASHTAGS_ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, htag_id);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();


			// SQL文を実行する
			rs.next();
			htag_name = rs.getString("HASHTAGS_NAME");

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
		return htag_name;
	}
}