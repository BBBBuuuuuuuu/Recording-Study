package comment;

import java.util.Date;

public class CommentInfo {

	private String id;
	private String pwd;
	private String content;
	private int article_no;
	private Date regdate;

	public CommentInfo(String id, String content, int article_no, Date regdate) {
		this.id = id;
		this.content = content;
		this.article_no = article_no;
		this.regdate = regdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getArticle_no() {
		return article_no;
	}

	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

}
