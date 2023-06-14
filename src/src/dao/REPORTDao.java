package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.REPORT;

public class REPORTDao {
	// 引数paramで検索項目を指定し、検索結果のリストを返す
	public List<REPORT> select(REPORT param) {
	    Connection conn = null;
	    List<REPORT> REPORTList = new ArrayList<>();

	    try {
	        // JDREPORTドライバを読み込む
	        Class.forName("org.h2.Driver");

	        // データベースに接続する
	        conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

	        // SQL文を準備する
	        String sql = "SELECT * FROM REPORT "
	                + "WHERE REPORT_ID LIKE ? "
	                + "AND REPLY_ID LIKE ? "
	                + "AND POSTER_ID LIKE ? "
	                + "ORDER BY REPORT_ID";

	        PreparedStatement pStmt = conn.prepareStatement(sql);

	        // SQL文を完成させる
	        pStmt.setInt(1, param.getREPORT_ID());
	        pStmt.setInt(2, param.getREPLY_ID());
	        pStmt.setInt(3, param.getPOSTER_ID());

	        // SQL文を実行し、結果を取得する
	        ResultSet rs = pStmt.executeQuery();

	        // 結果をリストに格納する
	        while (rs.next()) {
	            int reportId = rs.getInt("REPORT_ID");
	            int replyId = rs.getInt("REPLY_ID");
	            int posterId = rs.getInt("POSTER_ID");
	            REPORT report = new REPORT(reportId, replyId, posterId);
	            REPORTList.add(report);
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
	    return REPORTList;
	}


	// 引数listで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(REPORT list) {
	    Connection conn = null;
	    boolean result = false;

	    try {
	        // JDREPORTドライバを読み込む
	        Class.forName("org.h2.Driver");

	        // データベースに接続する
	        conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

	        // SQL文を準備する
	        String sql = "INSERT INTO REPORT (REPLY_ID, POSTER_ID) "
	                + "VALUES (?, ?)";
	        PreparedStatement pStmt = conn.prepareStatement(sql);

	        // SQL文を完成させる
	        pStmt.setInt(1, list.getREPLY_ID());
	        pStmt.setInt(2, list.getPOSTER_ID());

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
