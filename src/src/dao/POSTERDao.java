package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.POSTER;

public class POSTERDao {
	// 引数paramで検索項目を指定し、検索結果のリストを返す
	public List<POSTER> select(POSTER param) {
	    Connection conn = null;
	    List<POSTER> posterList = new ArrayList<>();

	    try {
	        // JDPOSTERドライバを読み込む
	        Class.forName("org.h2.Driver");

	        // データベースに接続する
	        conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");


	        // SQL文を準備する
	        String sql1 = "SELECT * FROM POSTER "
	        		+ "WHERE POSTER_ID = ? "
	        		+ "AND CATEGORY_ID = ? "
	        		+ "ORDER BY POSTED_DATE";

	        PreparedStatement pStmt = conn.prepareStatement(sql1);
	        // SQL文を完成させる
	        if (param.getPOSTER_ID() != 0 && param.getPOSTER_ID() != null) {
	            pStmt.setInt(1, param.getPOSTER_ID());
	        }else {
				pStmt.setInt(1, 0);
			}


	        pStmt.setInt(2, param.getCATEGORY_ID());


	        // SQL文を実行し、結果を取得する
	        ResultSet rs = pStmt.executeQuery();


	        // 結果をリストに格納する
	        while (rs.next()) {
	            POSTER poster = new POSTER();
	            poster.setPOSTER_ID(rs.getInt("POSTER_ID"));
	            poster.setTITLE(rs.getString("TITLE"));
	            poster.setCATEGORY_ID(rs.getInt("CATEGORY_ID"));
	            poster.setMAIN_SENTENCE(rs.getString("MAIN_SENTENCE"));
	            poster.setHASHTAGS_ID(rs.getInt("HASHTAGS_ID"));
	            poster.setPOSTED_DATE(rs.getString("POSTED_DATE"));
	            poster.setANIMAL_ID(rs.getString("ANIMAL_ID"));
	            poster.setUSER_ID(rs.getString("USER_ID"));
	            poster.setUSER_NAME_SWITCH(rs.getInt("USER_NAME_SWITCH"));
	            posterList.add(poster);
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

	    return posterList;
	}





	// 引数listで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(POSTER list) {
	    Connection conn = null;
	    boolean result = false;

	    try {
	        // JDPOSTERドライバを読み込む
	        Class.forName("org.h2.Driver");

	        // データベースに接続する
	        conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

	        // SQL文を準備する
	     // SQL文を準備する
	        String sql = "INSERT INTO POSTER (TITLE, CATEGORY_ID, MAIN_SENTENCE, HASHTAGS_ID, POSTED_DATE, ANIMAL_ID, USER_ID, USER_NAME_SWITCH) "
	                + "VALUES (?, ?, ?, ?, NOW(), ?, ?, ?)";
	        PreparedStatement pStmt = conn.prepareStatement(sql);

	        // SQL文を完成させる
	        pStmt.setString(1, list.getTITLE());
	        pStmt.setInt(2, list.getCATEGORY_ID());
	        pStmt.setString(3, list.getMAIN_SENTENCE());
	        pStmt.setInt(4, list.getHASHTAGS_ID());
	        pStmt.setString(5, list.getANIMAL_ID());
	        pStmt.setString(6, list.getUSER_ID());
	        pStmt.setInt(7, list.getUSER_NAME_SWITCH());


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


	// 引数numberで指定されたレコードを削除し、成功したらtrueを返す
	public boolean delete(int POSTER_ID) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDPOSTERドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

			// SQL文を準備する
			String sql = "delete from POSTER where POSTER_ID=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, POSTER_ID);

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
