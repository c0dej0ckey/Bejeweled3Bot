package utility;

import shapes.*;

public class Grid {

	private Shape[][] ShapeGrid;
	private final int LENGTH_X = 8;
	private final int LENGTH_Y = 8;
	
	public Grid(){
		ShapeGrid = new Shape[LENGTH_X][LENGTH_Y];
	
	}
	
	public void setShapeLocationInGrid(int x, int y, Shape shape){
		ShapeGrid[x][y] = shape;
		
		
	}
	
	public Shape getShapeInGrid(int x, int y){
		return ShapeGrid[x][y];
	}
	
	public Shape[][] getShapeGrid(){
		return this.ShapeGrid;
	}
	
	public int getGridXLength(){
		return LENGTH_X;
	}
	
	public int getGridYLength(){
		return LENGTH_Y;
	}
	
	
}
