package bookstoreonline.model;

public class Order extends Book{
	private int order_id;
	private int uid;
	private int quantity;
	private String date;
	public Order() {
		
	}
	
	public Order(int order_id, int uid, int quantity, String date) {
		super();
		this.order_id = order_id;
		this.uid = uid;
		this.quantity = quantity;
		this.date = date;
	}

	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
