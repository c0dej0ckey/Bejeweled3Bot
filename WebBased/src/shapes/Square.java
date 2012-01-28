package shapes;

import utility.Location;

public class Square implements Shape {

	private Location location;
	private final String TYPE = "SQUARE";
	
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
