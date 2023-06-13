package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.REPLY;

public class REPLYDao {
	// 引数paramで検索項目を指定し、検索結果のリストを返す
	public List<REPLY> select(REPLY param) {
	    Connection conn = null;
	    List<REPLY> REPLYList = new ArrayList<>();

	    try{
	        // JDBCドライバを読み込む
	        Class.forName("org.h2.Driver");

	        // データベースに接続する
	        conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

	        // SQL文を準備する(返信の内容を表示する)
	        String sql = "SELECT * FROM REPLY "
	                + "WHERE REPLY_ID LIKE ? "
	                + "AND POSTER_ID LIKE ? "
	                + "AND REPLY_SENTENCE LIKE ? "
	                + "AND REPLIED_DATE LIKE ? "
	                + "AND USER_NAME_SWITCH = ? "
	                + "AND USER_ID LIKE ? "
	                + "AND ANIMAL_ID LIKE ? ";

	        PreparedStatement pStmt = conn.prepareStatement(sql);

	        // SQL文を完成させる
            pStmt.setString(1, "%" + param.getREPLY_ID() + "%");

            pStmt.setString(2, "%" + param.getPOSTER_ID() + "%");

        if (param.getREPLY_SENTENCE() != null) {
            pStmt.setString(3, "%" + param.getREPLY_SENTENCE() + "%");
        } else {
            pStmt.setString(3, "%");
        }

        if (param.getREPLIED_DATE() != null) {
            pStmt.setString(4, "%" + param.getREPLIED_DATE() + "%");
        } else {
            pStmt.setString(4, "%");
        }

        if (param.getREPLIED_DATE() != null) {
            pStmt.setString(5, "%" + param.getREPLIED_DATE() + "%");
        } else {
            pStmt.setString(5, "%");
        }

        if (param.getUSER_ID() != null) {
            pStmt.setString(6, "%" + param.getUSER_ID() + "%");
        } else {
            pStmt.setString(6, "%");
        }

        if (param.getANIMAL_ID() != null) {
            pStmt.setString(7, "%" + param.getANIMAL_ID() + "%");
        } else {
            pStmt.setString(7, "%");
        }

        pStmt.setString(8, "%" + param.getUSER_NAME_SWITCH() + "%");



     // SQL文を実行し、結果を取得する
        ResultSet rs = pStmt.executeQuery();

     // 結果をリストに格納する
        while (rs.next()) {
        	REPLY REPLY = new REPLY();
            REPLY.setREPLY_ID(rs.getInt("REPLY_ID"));
            REPLY.setPOSTER_ID(rs.getInt("POSTER_ID"));
            REPLY.setREPLY_SENTENCE(rs.getString("REPLY_SENTENCE"));
            REPLY.setREPLY_SENTENCE(rs.getString("REPLIED_DATE"));
            REPLY.setREPLY_SENTENCE(rs.getString("USER_NAME_SWITCH"));
            REPLY.setREPLY_SENTENCE(rs.getString("USER_ID"));
            REPLY.setREPLY_SENTENCE(rs.getString("ANIMAL_ID"));
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

	// 返信を投稿する（REPLY_ID、POSTER_IDが投稿時に付与される。
	//また、USER_NAME_SWITCHの数値によってUSER_ID、ANIMAL_IDが付与される。）
	public boolean insert(REPLY list) {
	    Connection conn = null;
	    boolean result = false;

	    try {
	        // JDPOSTERドライバを読み込む
	        Class.forName("org.h2.Driver");

	        // データベースに接続する
	        conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

	        // SQL文を準備する
	        String sql = "INSERT INTO REPLY (REPLY_ID, POSTER_ID, REPLY_SENTENCE, REPLIED_DATE, USER_NAME_SWITCH, USER_ID, ANIMAL_ID) "
	                + "VALUES (?, ?, ?, NOW(), ?, ?, ?)";
	        PreparedStatement pStmt = conn.prepareStatement(sql);

	     // SQL文を完成させる
	        pStmt.setInt(1, list.getREPLY_ID());
	        pStmt.setInt(2, list.getPOSTER_ID());
	        pStmt.setString(3, list.getREPLY_SENTENCE());
	        pStmt.setInt(4, list.getUSER_NAME_SWITCH());
	        pStmt.setString(5, list.getUSER_ID());
	        pStmt.setString(6, list.getANIMAL_ID());

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

	// 引数で指定されたレコードを削除し、成功したらtrueを返す(投稿の削除)
	// （REPLY_IDで削除したい投稿を指定し、削除する）
		public boolean delete(int REPLY_ID) {
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


