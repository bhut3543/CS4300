
public class CarInfo {
	String color, bodyStyle, engine, driveType;
	int odometer, horsepower;
	
	public CarInfo(String color, String bodyStyle, String engine, String driveType, int odometer, int horsepower) {
		super();
		this.color = color;
		this.bodyStyle = bodyStyle;
		this.engine = engine;
		this.driveType = driveType;
		this.odometer = odometer;
		this.horsepower = horsepower;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBodyStyle() {
		return bodyStyle;
	}

	public void setBodyStyle(String bodyStyle) {
		this.bodyStyle = bodyStyle;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getDriveType() {
		return driveType;
	}

	public void setDriveType(String driveType) {
		this.driveType = driveType;
	}

	public int getOdometer() {
		return odometer;
	}

	public void setOdometer(int odometer) {
		this.odometer = odometer;
	}

	public int getHorsepower() {
		return horsepower;
	}

	public void setHorsepower(int horsepower) {
		this.horsepower = horsepower;
	}

}
