package bookstoreonline.model;

public class Rating {
	protected int rate_id;
	protected String content;
	protected String datepost;
	protected int rating;
	public Rating() {
		
	}
	public Rating(int rate_id,String content,String datepost,int rating) {
		this.rate_id = rate_id;
		this.content = content;
		this.datepost = datepost;
		this.rating = rating;
	}
	public int getRate_id() {
		return rate_id;
	}
	public void setRate_id(int rate_id) {
		this.rate_id = rate_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDatepost() {
		return datepost;
	}
	public void setDatepost(String datepost) {
		this.datepost = datepost;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
}
