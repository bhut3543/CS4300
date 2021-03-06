

public class CarPost {
	String id,  userId, title;
	int price;
	String description, postTime;
	boolean hasCarfax;
	String addr;
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public CarPost(String id, String userId, String title, int price, String description, String postTime,
			boolean hasCarfax, String addr) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.price = price;
		this.description = description;
		this.postTime = postTime;
		this.hasCarfax = hasCarfax;
		this.addr = addr;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPostTime() {
		return postTime;
	}
	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}
	public boolean isHasCarfax() {
		return hasCarfax;
	}
	public void setHasCarfax(boolean hasCarfax) {
		this.hasCarfax = hasCarfax;
	}
}
