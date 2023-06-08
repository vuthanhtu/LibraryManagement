package bookstoreonline.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Book {
	protected int book_id;
	protected String title;
	protected String author;
	protected String dor;
	protected String publisher;
	protected int page;
	protected String description;
	protected float price;
	protected int amount;
	protected String photo;
	protected int categoryid;
	protected Category category;
	public Book() {
		
	}
	/*Hien thi trang chu*/
	public Book(int book_id,String title,String author,String dor,String publisher,int page,float price,int amount,String photo,int categoryid,Category category) throws ParseException{
		this.book_id = book_id;
		this.title = title;
		this.author = author;
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dor);
		this.dor = new SimpleDateFormat("dd/MM/yyyy").format(date);
		this.publisher = publisher;
		this.page = page;
		this.price = price;
		this.amount = amount;
		this.photo = photo;
		this.categoryid = categoryid;
		this.category = category;
	}
	
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDor() {
		return dor;
	}
	public void setDor(String dor) {
		this.dor = dor;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
}
