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
	        String sql = "SELECT * FROM POSTER "
	                + "WHERE POSTER_ID LIKE ? "
	                + "AND TITLE LIKE ? "
	                + "AND CATEGORY_ID LIKE ? "
	                + "AND MAIN_SENTENCE LIKE ? "
	                + "AND HASHTAGS_ID LIKE ? "
	                + "AND POSTED_DATE LIKE ? "
	                + "AND ANIMAL_ID LIKE ? "
	                + "AND USER_ID LIKE ? "
	                + "AND USER_NAME_SWITCH = ? "
	                + "ORDER BY POSTER_ID";

	        PreparedStatement pStmt = conn.prepareStatement(sql);

	        // SQL文を完成させる
	        if (param.getPOSTER_ID() != null) {
	            pStmt.setString(1, "%" + param.getPOSTER_ID() + "%");
	        } else {
	            pStmt.setString(1, "%");
	        }
	        if (param.getTITLE() != null) {
	            pStmt.setString(2, "%" + param.getTITLE() + "%");
	        } else {
	            pStmt.setString(2, "%");
	        }
	        if (param.getCATEGORY_ID() != null) {
	            pStmt.setString(3, "%" + param.getCATEGORY_ID() + "%");
	        } else {
	            pStmt.setString(3, "%");
	        }
	        if (param.getMAIN_SENTENCE() != null) {
	            pStmt.setString(4, "%" + param.getMAIN_SENTENCE() + "%");
	        } else {
	            pStmt.setString(4, "%");
	        }
	        if (param.getHASHTAGS_ID() != null) {
	            pStmt.setString(5, "%" + param.getHASHTAGS_ID() + "%");
	        } else {
	            pStmt.setString(5, "%");
	        }
	        if (param.getPOSTED_DATE() != null) {
	            pStmt.setString(6, "%" + param.getPOSTED_DATE() + "%");
	        } else {
	            pStmt.setString(6, "%");
	        }
	        if (param.getANIMAL_ID() != null) {
	            pStmt.setString(7, "%" + param.getANIMAL_ID() + "%");
	        } else {
	            pStmt.setString(7, "%");
	        }
	        if (param.getUSER_ID() != null) {
	            pStmt.setString(8, "%" + param.getUSER_ID() + "%");
	        } else {
	            pStmt.setString(8, "%");
	        }
	        pStmt.setInt(9, param.getUSER_NAME_SWITCH());

	        // SQL文を実行し、結果を取得する
	        ResultSet rs = pStmt.executeQuery();

	        // 結果をリストに格納する
	        while (rs.next()) {
	            POSTER poster = new POSTER();
	            poster.setPOSTER_ID(rs.getString("POSTER_ID"));
	            poster.setTITLE(rs.getString("TITLE"));
	            poster.setCATEGORY_ID(rs.getString("CATEGORY_ID"));
	            poster.setMAIN_SENTENCE(rs.getString("MAIN_SENTENCE"));
	            poster.setHASHTAGS_ID(rs.getString("HASHTAGS_ID"));
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
	        String sql = "INSERT INTO POSTER (POSTER_ID, TITLE, CATEGORY_ID, MAIN_SENTENCE, HASHTAGS_ID, POSTED_DATE, ANIMAL_ID, USER_ID, USER_NAME_SWITCH) "
	                + "VALUES (?, ?, ?, ?, ?, NOW(), ?, ?, ?)";
	        PreparedStatement pStmt = conn.prepareStatement(sql);

	        // SQL文を完成させる
	        pStmt.setString(1, list.getPOSTER_ID());
	        pStmt.setString(2, list.getTITLE());
	        pStmt.setString(3, list.getCATEGORY_ID());
	        pStmt.setString(4, list.getMAIN_SENTENCE());
	        pStmt.setString(5, list.getHASHTAGS_ID());
	        pStmt.setString(6, list.getANIMAL_ID());
	        pStmt.setString(7, list.getUSER_ID());
	        pStmt.setInt(8, list.getUSER_NAME_SWITCH());

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
	public boolean delete(String POSTER_ID) {
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
			pStmt.setString(1, POSTER_ID);

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
