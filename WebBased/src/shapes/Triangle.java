package shapes;

import utility.Location;

public class Triangle implements Shape {

	private Location location;
	private final String TYPE = "TRIANGLE";
	
	@Override
	public void setLocation(Location location) {
		this.location = location;

	}

	@Override
	public void Draw() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getType() {
		return TYPE;
	}

}
