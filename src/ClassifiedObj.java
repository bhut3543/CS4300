
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
	String color;
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getDriveType() {
		return driveType;
	}
	public void setDriveType(String driveType) {
		this.driveType = driveType;
	}
	public String getBodyStyle() {
		return bodyStyle;
	}
	public void setBodyStyle(String bodyStyle) {
		this.bodyStyle = bodyStyle;
	}
	String driveType;
	String bodyStyle;
	String odometer;
	public String getOdometer() {
		return odometer;
	}
	public void setOdometer(String odometer) {
		this.odometer = odometer;
	}
	public ClassifiedObj(String id, String userId, String year, String make, String model, String title, String color, String driveType, String bodyStyle, String odometer) {
		super();
		this.id = id;
		this.userId = userId;
		this.year = year;
		this.make = make;
		this.model = model;
		this.title = title;
		this.color = color;
		this.driveType = driveType;
		this.bodyStyle = bodyStyle;
		this.odometer = odometer;
	}

}
