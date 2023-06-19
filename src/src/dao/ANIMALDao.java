package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.POSTER;
import model.REPLY;

//入力：アニマルid(1-100のString)
//処理：入力されたアニマルidに対応したアニマル名を探索してピックアップ
//出力：idに対応したアニマル名
public class ANIMALDao {
	// 引数paramで検索項目を指定し、検索結果のリストを返す
	public String get_animalname(String animal_id) {
	    Connection conn = null;
	    String animal_name = "";

	    try {
	        // JDCATEGORYドライバを読み込む
	        Class.forName("org.h2.Driver");

	        // データベースに接続する
	        conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/gendaDB", "sa", "");

	        // SQL文を準備する
	        String sql = "SELECT ANIMAL_NAME FROM ANIMAL WHERE ANIMAL_ID LIKE ? ";

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



	//入力：POSTER_idとuser_id
	//処理：投稿とその投稿に紐づいている返信を全てとってきて
	//      その中から入力されたuser_idがいるか調べて
	//      いたらそのanimal_idを返していなければ新しい重複しないanimal_idを返す
	//出力：animal_id(String)

	public String get_animalID(int poster_id, String user_id) {

		String animal_id = "";
		List<Integer> numed_list = new ArrayList<Integer>();
		Integer animal_num = 0;

		POSTERDao p_dao = new POSTERDao();
		List<POSTER> poster_list = p_dao.select(poster_id, 0);
		POSTER poster = poster_list.get(0);
		if(user_id.equals(poster.getUSER_ID())) {
			animal_id = poster.getANIMAL_ID();
			return animal_id;
		} else {
			animal_num = Integer.valueOf(poster.getANIMAL_ID());
			numed_list.add(animal_num);
		}

		boolean dis = true;
		REPLYDao r_dao = new REPLYDao();
		List<REPLY> reply_list = r_dao.select(0, poster_id);
		REPLY reply = new REPLY();
		for(int i = 0; i < reply_list.size(); i++) {
			reply = reply_list.get(i);
			if(user_id.equals(reply.getUSER_ID())) {
				animal_id = reply.getANIMAL_ID();
				return animal_id;
			} else {
				animal_num = Integer.valueOf(reply.getANIMAL_ID());
				for(int j = 0; j < numed_list.size(); j++) {
					if(animal_num == numed_list.get(j)) {
						dis = false;
						break;
					}
					if(dis) {
						numed_list.add(animal_num);
					} else {
						dis = true;
					}
				}
			}
		}
		numed_list.sort(Collections.reverseOrder());

		int count = 0;
		List<Integer> int_list = new ArrayList<Integer>();
		for(int i = 1; i < 101; i++) {
            int_list.add(i);
        }
        for(int i = 0; i < numed_list.size(); i++) {
        	int_list.remove(numed_list.get(i));
        	count++;
        }
        Random rand = new Random();
        int range = 100 - count;
        int num = rand.nextInt(range);
        animal_id = String.valueOf(num);
        return animal_id;
	}
}