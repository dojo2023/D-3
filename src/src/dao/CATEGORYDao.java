package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CATEGORY;

public class CATEGORYDao {
	// 引数paramで検索項目を指定し、検索結果のリストを返す
	public List<CATEGORY> select(CATEGORY param) {
	    Connection conn = null;
	    List<CATEGORY> CATEGORYList = new ArrayList<>();

	    try {
	        // JDCATEGORYドライバを読み込む
	        Class.forName("org.h2.Driver");

	        // データベースに接続する
	        conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

	        // SQL文を準備する
	        String sql = "SELECT * FROM CATEGORY "
	                + "WHERE CATEGORY_ID LIKE ? "
	                + "AND CATEGORY_NAME LIKE ? "
	                + "ORDER BY CATEGORY_ID";

	        PreparedStatement pStmt = conn.prepareStatement(sql);

	        // SQL文を完成させる
	        if (param.getCATEGORY_ID() != null) {
	            pStmt.setString(1, "%" + param.getCATEGORY_ID() + "%");
	        } else {
	            pStmt.setString(1, "%");
	        }
	        if (param.getCATEGORY_NAME() != null) {
	            pStmt.setString(2, "%" + param.getCATEGORY_NAME() + "%");
	        } else {
	            pStmt.setString(2, "%");
	        }

	        // SQL文を実行し、結果を取得する
	        ResultSet rs = pStmt.executeQuery();

	        // 結果をリストに格納する
	        while (rs.next()) {
	            CATEGORY CATEGORY = new CATEGORY();
	            CATEGORY.setCATEGORY_ID(rs.getString("CATEGORY_ID"));
	            CATEGORY.setCATEGORY_NAME(rs.getString("CATEGORY_NAME"));
	            CATEGORYList.add(CATEGORY);
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

	    return CATEGORYList;
	}



	// 引数listで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(CATEGORY list) {
	    Connection conn = null;
	    boolean result = false;

	    try {
	        // JDCATEGORYドライバを読み込む
	        Class.forName("org.h2.Driver");

	        // データベースに接続する
	        conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

	        // SQL文を準備する
	        String sql = "INSERT INTO CATEGORY (CATEGORY_ID, CATEGORY_NAME) "
	                + "VALUES (?, ?)";
	        PreparedStatement pStmt = conn.prepareStatement(sql);

	        // SQL文を完成させる
	        pStmt.setString(1, list.getCATEGORY_ID());
	        pStmt.setString(2, list.getCATEGORY_NAME());

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
