package model;

import java.io.Serializable;

public class USER_INFO implements Serializable {
	private String user_en;
	private String user_name;
	private String user_id;
	private String user_pw;
	private String user_sq_id;
	private String user_sa;
	private String user_mode_switch;
	private String category_id;
	private String hashtags_id;
	private String free_word;
	private String favorite_switch;

	//コンストラクタ
	public USER_INFO(String user_en, String user_name, String user_id, String user_pw, String user_sq_id, String user_sa,
			String user_mode_switch, String category_id, String hashtags_id, String free_word, String favorite_switch) {
		super();
		this.user_en = user_en;
		this.user_name = user_name;
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_sq_id = user_sq_id;
		this.user_sa = user_sa;
		this.user_mode_switch = user_mode_switch;
		this.category_id = category_id;
		this.hashtags_id = hashtags_id;
		this.free_word = free_word;
		this.favorite_switch = favorite_switch;
	}

	//コンストラクタ（初期値なし）
		public USER_INFO() {
			super();
			this.user_en = "";
			this.user_name = "";
			this.user_id = "";
			this.user_pw = "";
			this.user_sq_id = "";
			this.user_sa = "";
			this.user_mode_switch = "";
			this.category_id = "";
			this.hashtags_id = "";
			this.free_word = "";
			this.favorite_switch = "";
		}

		public String getUser_en() {
			return user_en;
		}

		public void setUser_en(String user_en) {
			this.user_en = user_en;
		}

		public String getUser_name() {
			return user_name;
		}

		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}

		public String getUser_id() {
			return user_id;
		}

		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}

		public String getUser_pw() {
			return user_pw;
		}

		public void setUser_pw(String user_pw) {
			this.user_pw = user_pw;
		}

		public String getUser_sq_id() {
			return user_sq_id;
		}

		public void setUser_sq_id(String user_sq_id) {
			this.user_sq_id = user_sq_id;
		}

		public String getUser_sa() {
			return user_sa;
		}

		public void setUser_sa(String user_sa) {
			this.user_sa = user_sa;
		}

		public String getUser_mode_switch() {
			return user_mode_switch;
		}

		public void setUser_mode_switch(String user_mode_switch) {
			this.user_mode_switch = user_mode_switch;
		}

		public String getCategory_id() {
			return category_id;
		}

		public void setCategory_id(String category_id) {
			this.category_id = category_id;
		}

		public String getHashtags_id() {
			return hashtags_id;
		}

		public void setHashtags_id(String hashtags_id) {
			this.hashtags_id = hashtags_id;
		}

		public String getFree_word() {
			return free_word;
		}

		public void setFree_word(String free_word) {
			this.free_word = free_word;
		}

		public String getFavorite_switch() {
			return favorite_switch;
		}

		public void setFavorite_switch(String favorite_switch) {
			this.favorite_switch = favorite_switch;
		}
	}

