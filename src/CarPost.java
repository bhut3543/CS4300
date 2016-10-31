

public class CarPost {
	public CarPost(String id, String userId, String year, String make, String model, String title, int price,
			String description, String postTime, boolean hasCarfax) {
		super();
		this.id = id;
		this.userId = userId;
		this.year = year;
		this.make = make;
		this.model = model;
		this.title = title;
		this.price = price;
		this.description = description;
		this.postTime = postTime;
		this.hasCarfax = hasCarfax;
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



	public String getYear() {
		return year;
	}



	public void setYear(String year) {
		this.year = year;
	}



	public String getMake() {
		return make;
	}



	public void setMake(String make) {
		this.make = make;
	}



	public String getModel() {
		return model;
	}



	public void setModel(String model) {
		this.model = model;
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



	String id,  userId, year, make, model, title;
	int price;
	String description, postTime;
	boolean hasCarfax;
	


	public CarPost(String id, String userId, String year, String make, String model, String title) {
		super();
		this.id = id;
		this.userId = userId;
		this.year = year;
		this.make = make;
		this.model = model;
		this.title = title;
	}
}
