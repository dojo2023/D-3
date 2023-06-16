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

import model.REPLY;

public class REPLYDao {
	//入力：返信IDと投稿ID(int)
	//      検索したいほうのみにデータを入力し、もう一方には0を入れて入力する
	//      例：29という返信idの人を検索したい→select(29, 0)
	//処理：入力されたデータのうち値が入っている（0以外のデータが入っている）方を検索する
	//      REPLYデータベース内を検索し、該当したデータを全て取り出す
	//出力：検索した値と一致したREPLYデータをリストで返す（List<REPLY>）
	public List<REPLY> select(int reply_id, int poster_id) {
	    Connection conn = null;
	    List<REPLY> REPLYList = new ArrayList<REPLY>();

	    try{
	        // JDBCドライバを読み込む
	        Class.forName("org.h2.Driver");

	        // データベースに接続する
	        conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");
	        String sql = "";
	        PreparedStatement pStmt = conn.prepareStatement(sql);

	        if(reply_id == 0) {
	        	sql = "SELECT * FROM REPLY WHERE POSTER_ID = ?";
	        	pStmt = conn.prepareStatement(sql);
	        	pStmt.setInt(1, poster_id);
	        } else if(poster_id == 0) {
	        	sql = "SELECT * FROM REPLY WHERE REPLY_ID = ?";
	        	pStmt = conn.prepareStatement(sql);
	        	pStmt.setInt(1, reply_id);
	        }

     // SQL文を実行し、結果を取得する
        ResultSet rs = pStmt.executeQuery();

     // 結果をリストに格納する
        while (rs.next()) {
        	REPLY REPLY = new REPLY();
            REPLY.setREPLY_ID(rs.getInt("REPLY_ID"));
            REPLY.setPOSTER_ID(rs.getInt("POSTER_ID"));
            REPLY.setREPLY_SENTENCE(rs.getString("REPLY_SENTENCE"));
            REPLY.setREPLIED_DATE(rs.getString("REPLIED_DATE"));
            REPLY.setUSER_NAME_SWITCH(rs.getInt("USER_NAME_SWITCH"));
            REPLY.setUSER_ID(rs.getString("USER_ID"));
            REPLY.setANIMAL_ID(rs.getString("ANIMAL_ID"));
            REPLYList.add(REPLY);
        }


	    }catch (ClassNotFoundException | SQLException e) {
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

	    return REPLYList;

	}

	//入力：REPLY型のデータ
	//処理：入力されたREPLYのデータをREPLYデータベースに格納する
	//出力：格納成功したらtrue、失敗したらfalseを返す
	public boolean insert(REPLY list) {
	    Connection conn = null;
	    boolean result = false;

	    try {
	        // JDPOSTERドライバを読み込む
	        Class.forName("org.h2.Driver");

	        // データベースに接続する
	        conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

	        // SQL文を準備する
	        String sql = "INSERT INTO REPLY (POSTER_ID, REPLY_SENTENCE,"
	        		+ "USER_NAME_SWITCH, USER_ID, ANIMAL_ID, REPLIED_DATE) "
	                + "VALUES (?, ?, ?, ?, ?, ?)";
	        PreparedStatement pStmt = conn.prepareStatement(sql);

	     // SQL文を完成させる
	        pStmt.setInt(1, list.getPOSTER_ID());
	        pStmt.setString(2, list.getREPLY_SENTENCE());
	        pStmt.setInt(3, list.getUSER_NAME_SWITCH());
	        pStmt.setString(4, list.getUSER_ID());
	        pStmt.setString(5, list.getANIMAL_ID());

	        LocalDateTime now_date = LocalDateTime.now();
	        DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("yyyy/MM/dd/ HH:mm:ss");
	        String date = dtformat.format(now_date);

	        pStmt.setString(6, date);

	     // SQL文を実行する
	        if (pStmt.executeUpdate() == 1) {
	            result = true;
	        }
	    }catch (ClassNotFoundException | SQLException e) {
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

	//入力：消去したいREPLYデータのID
	//処理：入力されたREPLY_IDに対応したデータをREPLYデータベースから消去する
	//出力：消去成功したらtrue、失敗したらfalseを返す
		public boolean delete(int REPLY_ID) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDPOSTERドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

				// SQL文を準備する
				String sql = "delete from REPLY where REPLY_ID = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				pStmt.setInt(1, REPLY_ID);

				// SQL文を実行する
				if (pStmt.executeUpdate() == 1) {
					result = true;
				}
			}catch (SQLException e) {
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