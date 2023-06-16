package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.POSTER;

public class POSTERDao {
	//入力：投稿IDとカテゴリID(int)
	//      検索したいほうのみにデータを入力し、もう一方には0を入れて入力する
	//      例：29というidの人を検索したい→select(29, 0)
	//処理：入力されたデータのうち値が入っている（0以外のデータが入っている）方を検索する
	//      POSTERデータベース内を検索し、該当したデータを全て取り出す
	//出力：検索した値と一致したPOSTERデータをリストで返す（List<POSTER>）
	public List<POSTER> select(int poster_id, int category_id) {
	    Connection conn = null;
	    List<POSTER> posterList = new ArrayList<POSTER>();
	    try {
	        // JDPOSTERドライバを読み込む
	        Class.forName("org.h2.Driver");

	        // データベースに接続する
	        conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

	        //sql文のひな形を宣言
	        String sql = "";
	        PreparedStatement pStmt = conn.prepareStatement(sql);

	        //投稿IDとカテゴリIDを検索するときで用いるsql文を場合分けして作成
	        if(poster_id == 0) {
	        	sql = "select * from POSTER where CATEGORY_ID = ? order by POSTED_DATE";
	        	pStmt = conn.prepareStatement(sql);
	        	pStmt.setInt(1, category_id);

	        } else if(category_id == 0) {
	        	sql = "select * from POSTER where POSTER_ID = ? order by POSTED_DATE";
	        	pStmt = conn.prepareStatement(sql);
	        	pStmt.setInt(1, poster_id);
	        }

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





	//入力：POSTER型のデータ
	//処理：入力されたPOSTERのデータをPOSTERデータベースに格納する
	//出力：格納成功したらtrue、失敗したらfalseを返す
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
	        String sql = "INSERT INTO POSTER (TITLE, CATEGORY_ID, MAIN_SENTENCE, HASHTAGS_ID, ANIMAL_ID, USER_ID, USER_NAME_SWITCH, POSTED_DATE) "
	                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement pStmt = conn.prepareStatement(sql);

	        // SQL文を完成させる
	        pStmt.setString(1, list.getTITLE());
	        pStmt.setInt(2, list.getCATEGORY_ID());
	        pStmt.setString(3, list.getMAIN_SENTENCE());
	        pStmt.setInt(4, list.getHASHTAGS_ID());
	        pStmt.setString(5, list.getANIMAL_ID());
	        pStmt.setString(6, list.getUSER_ID());
	        pStmt.setInt(7, list.getUSER_NAME_SWITCH());

	        LocalDateTime now_date = LocalDateTime.now();
	        DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("yyyy/MM/dd/ HH:mm:ss");
	        String date = dtformat.format(now_date);

	        pStmt.setString(8, date);


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


	//入力：消去したいPOSTERデータのID
	//処理：入力されたPOSTERIDに対応したデータをPOSTERデータベースから消去する
	//出力：消去成功したらtrue、失敗したらfalseを返す
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
