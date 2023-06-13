package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//入力：アニマルid(1-100の整数)、出力：idに対応したアニマル名

public class ANIMALDao {
	// 引数paramで検索項目を指定し、検索結果のリストを返す
	public String select(String animal_id) {
	    Connection conn = null;
	    String animal_name = "";

	    try {
	        // JDCATEGORYドライバを読み込む
	        Class.forName("org.h2.Driver");

	        // データベースに接続する
	        conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

	        // SQL文を準備する
	        String sql = "SELECT * FROM ANIMAL WHERE ANIMAL_ID LIKE ? ";

	        PreparedStatement pStmt = conn.prepareStatement(sql);

	        // SQL文を完成させる
	        pStmt.setString(1, animal_id);

	        // SQL文を実行し、結果を取得する
	        ResultSet rs = pStmt.executeQuery();

	        // 結果をリストに格納する
	        rs.next();
	        animal_name = rs.getString("ANIMAL_NAME");


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

	    return animal_name;
	}
}