package shapes;

import utility.Location;

public class Diamond implements Shape {

	public Location location;
	private final String TYPE = "DIAMOND";
	
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
