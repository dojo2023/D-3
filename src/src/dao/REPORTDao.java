package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.REPLY;
import model.REPORT;

public class REPORTDao {
	//入力：なし
	//処理：REPORTデータベースの内容を全て取得する
	//      また取得と同時に返信が通報されていた場合はそれに紐づいた投稿のIDをposter_idに格納する
	//出力：REPORTデータベースの全内容（List<REPORT型>）
	public List<REPORT> select() {
	    Connection conn = null;
	    List<REPORT> REPORT_List = new ArrayList<REPORT>();

	    try {
	        // JDREPORTドライバを読み込む
	        Class.forName("org.h2.Driver");

	        // データベースに接続する
	        conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

	        // SQL文を準備する
	        String sql = "SELECT * FROM REPORT ORDER BY REPORT_ID";

	        PreparedStatement pStmt = conn.prepareStatement(sql);

	        // SQL文を実行し、結果を取得する
	        ResultSet rs = pStmt.executeQuery();

	        // 結果をリストに格納する
	        while (rs.next()) {
	            int reportId = rs.getInt("REPORT_ID");
	            int replyId = rs.getInt("REPLY_ID");
	            int posterId = rs.getInt("POSTER_ID");
	            if(posterId == 0) {
	            	REPLYDao r_dao = new REPLYDao();
	            	List<REPLY> REPLY_List = r_dao.select(replyId, 0);
	            	REPLY REPLY = REPLY_List.get(0);
	            	posterId = REPLY.getPOSTER_ID();
	            }
	            REPORT report = new REPORT(reportId, replyId, posterId);
	            REPORT_List.add(report);
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
	    return REPORT_List;
	}


	//入力：posterIDとreplyID（どちらもint型でどちらかの値は0）
	//      投稿が通報された場合はposterIDに、返信が追放された場合はreplyIDに値を入れる
	//処理：入力された値をREPORTデータベースに新規挿入する
	//出力：挿入成功したらtrue、失敗したらfalseを返す
	public boolean insert(int posterID, int replyID) {
	    Connection conn = null;
	    boolean result = false;

	    try {
	        // JDREPORTドライバを読み込む
	        Class.forName("org.h2.Driver");

	        // データベースに接続する
	        conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

	        // SQL文を準備する
	        String sql = "INSERT INTO REPORT (REPLY_ID, POSTER_ID) VALUES (?, ?)";
	        PreparedStatement pStmt = conn.prepareStatement(sql);

	        // SQL文を完成させる
	        pStmt.setInt(1, replyID);
	        pStmt.setInt(2, posterID);

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
}
