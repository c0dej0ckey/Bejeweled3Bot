package utility;

import gems.*;

public class GemGrid {
	
	private Gem[][] gridOfGems;
	private final int LENGTH_X = 8;
	private final int LENGTH_Y = 8;
	
	public GemGrid(){
		gridOfGems = new Gem[LENGTH_X][LENGTH_Y];
	
	}
	
	public void setGemLocationInGrid(int x, int y, Gem shape){
		gridOfGems[x][y] = shape;
		
		
	}
	
	public Gem getGemInGrid(int x, int y){
		return gridOfGems[x][y];
	}
	
	public Gem[][] getGemGrid(){
		return this.gridOfGems;
	}
	
	public int getGridXLength(){
		return LENGTH_X;
	}
	
	public int getGridYLength(){
		return LENGTH_Y;
	}

}
