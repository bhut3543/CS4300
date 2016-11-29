
public class ClassifiedObj {
	String id;
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
	String userId;
	String year;
	String make;
	String model;
	String title;
	public ClassifiedObj(String id, String userId, String year, String make, String model, String title) {
		super();
		this.id = id;
		this.userId = userId;
		this.year = year;
		this.make = make;
		this.model = model;
		this.title = title;
	}

}
