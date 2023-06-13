package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class HASHTAGSDao {
	// 引数paramで検索項目を指定し、検索結果のリストを返す
	public boolean insert(String hashtagname) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

			// SQL文を準備する
			String sql = "INSERT INTO HASHTAGS(HASHTAGS_NAME) VALUES(?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (hashtagname != null && !hashtagname.equals("")) {
				pStmt.setString(1, hashtagname);
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


	// ユーザーが入力した♯があるかの確認、探索
	public boolean search(String hashtagname) {
	    Connection conn = null;
	    boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

			// SQL文を準備する
	        String sql = "SELECT COUNT(*) FROM HASHTAGS WHERE HASHTAGS_NAME = ?";
	        PreparedStatement pStmt = conn.prepareStatement(sql);

	        // SQL文を完成させる
	        if (hashtagname != null && !hashtagname.equals("")) {
	            pStmt.setString(1, hashtagname);
	        } else {
	            pStmt.setString(1, null);
	        }

	        // SQL文を実行し、結果を取得する
	        ResultSet rs = pStmt.executeQuery();

	     // 結果を確認する
	        if (rs.next()) {
	            int count = rs.getInt(1);
	            if (count > 0) {
	                result = true;
	            }
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
	    return result;
	}

	// 掲示板に表示したらポスターデーターベースに接続して＃IDを持ってきて、
	//その＃IDから＃の名前をもってくるのは、♯データーベースに接続。
	//＃IDを入力したらそれに対応した関数を持ってくる
	//掲示板が表示されたら、＃も同時に表示させたい
	public String hashtagnamereturn(String hashtagid) {
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

			// SQL文を準備する
			String sql = "SELECT * FROM HASHTAGS WHERE HASHTAGS_ID LIKE ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
	        pStmt.setString(1, hashtagid);



			// SQL文を実行し、結果表を取得する,String hashtagname = "";初期化
			ResultSet rs = pStmt.executeQuery();
			rs.next();
			String hashtagname = rs.getString("HASHTAGS_NAME");
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
	return hashtagname;
	}
