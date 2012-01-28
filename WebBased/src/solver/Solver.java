package solver;

import java.awt.AWTException;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;

import shapes.*;

import utility.Grid;
import utility.ShapeFactory;

public class Solver {

	private Grid grid;
	private final int BEGIN_X_POINT = 995;
	private final int BEGIN_Y_POINT = 445;
	
	
	public Solver(Grid grid){
		this.grid = grid;
	}
	
	public void generateGrid() throws AWTException{
		
			
			Robot robot = new Robot();
			for(int i = 0; i < grid.getGridXLength(); i++){
				for(int j = 0; j < grid.getGridYLength(); j++){
					Color colorAtPointOnScreen = robot.getPixelColor(BEGIN_X_POINT + (j * 40), BEGIN_Y_POINT + (i * 40)); //(int)point.getX(), (int)point.getY());
					Shape shape = ShapeFactory.generateShapeFromColor(colorAtPointOnScreen);
					grid.setShapeLocationInGrid(i, j, shape);
				}
				
			}
		
			solveGrid();
			
		
		
	}
	
	public void solveGrid() throws AWTException{
		//iterate over each element, look for the 16 patterns at each element
		Shape[][] shapesInGrid = grid.getShapeGrid();
		for(int i = 0; i < grid.getGridXLength(); i++) {
			for(int j = 0; j < grid.getGridYLength(); j++) {
				Shape shape = grid.getShapeInGrid(i, j);
				
				//should be broken up into 4 diff cases handling 0,7 0,0 7,0 7,7
				
				//otherwise check all casses
				if(isIndexZero(i) && isIndexZero(j)){
					//check case 1,3,5,7,8,10,11,13,14,16
					checkCaseOne(shape, i, j);
					checkCaseThree(shape, i, j);
					checkCaseFive(shape, i, j);
					checkCaseSeven(shape, i, j);
					checkCaseEight(shape, i, j);
					checkCaseTen(shape, i, j);
					checkCaseThirteen(shape, i, j);
					checkCaseFourteen(shape, i, j);
					checkCaseSixteen(shape, i, j);
					
				}
				else if(isIndexZero(i) && isIndexSeven(j)){
					checkCaseTwo(shape, i, j);
					checkCaseFour(shape, i, j);
					checkCaseSix(shape, i, j);
					checkCaseSeven(shape, i, j);
					checkCaseEight(shape, i, j);
					
				}
				else if(isIndexSeven(i) && isIndexZero(j)){
					//2,3,6,9,12,13,14,15
					checkCaseNine(shape, i, j);
					checkCaseTwelve(shape, i, j);
					checkCaseThirteen(shape, i, j);
					checkCaseFourteen(shape, i, j);
					checkCaseFifteen(shape, i, j);
				}
				else if(isIndexSeven(i) && isIndexSeven(j) ||
						isIndexSeven(i) && j == 6 ||
						isIndexZero(i) && j == 6 ){
					//NOTHING!
					
				}
				
				else if(isIndexZero(i)) {
					//1,2,3,4,5,6,7,8,10,12,13,14,16
					checkCaseOne(shape, i, j);
					checkCaseTwo(shape, i, j);
					checkCaseThree(shape, i, j);
					checkCaseFour(shape, i, j);
					checkCaseFive(shape, i, j);
					checkCaseSix(shape, i, j);
					checkCaseSeven(shape, i, j);
					checkCaseEight(shape, i, j);
					
					//checkCaseTwelve(shape, i, j);
					
					
					if(j <= 4 ){
						checkCaseThirteen(shape, i, j);
						checkCaseFourteen(shape, i, j);
					}
					if(j <= 5){
						checkCaseTen(shape, i, j);
						checkCaseEleven(shape, i, j);
						checkCaseSixteen(shape, i, j);
					}
				}
				else if(isIndexZero(j)){
					//1,3,5,7,8,9,10,11,12,131,14,15,16
					
					
					checkCaseNine(shape, i, j);
					checkCaseTen(shape, i, j);
					checkCaseEleven(shape, i, j);
					checkCaseTwelve(shape, i, j);
					checkCaseFifteen(shape, i, j);
					checkCaseSixteen(shape, i, j);
					
					if(i <= 4){
						checkCaseThirteen(shape, i, j);
						checkCaseFourteen(shape, i, j);
						checkCaseSeven(shape, i, j);
						checkCaseEight(shape, i, j);
					}
					if(i <= 5){
						checkCaseOne(shape, i, j);
						checkCaseThree(shape, i, j);
						checkCaseFive(shape, i, j);
					}
					
					
				}
				else if(isIndexSeven(i)){
					//cant check combs to the right
					//9,12,13,14,15
					checkCaseNine(shape, i, j);
					checkCaseTwelve(shape, i, j);
					checkCaseFifteen(shape, i, j);
					
					if(j <= 4){
						checkCaseThirteen(shape, i, j);
						checkCaseFourteen(shape, i, j);
					}
					
				}
				else if(isIndexSeven(j)){
					//2,4,6, 7,8
					
					if(i <= 4) {
						checkCaseSeven(shape, i, j);
						checkCaseEight(shape, i, j);
						checkCaseTwo(shape, i, j);
						checkCaseFour(shape, i, j);
						checkCaseSix(shape, i, j);
					}
				}
				
				else {
					if(i <= 4){
						checkCaseSeven(shape, i, j);
						checkCaseEight(shape, i, j);
					}
					if(j <= 4){
						checkCaseThirteen(shape, i, j);
						checkCaseFourteen(shape, i, j);
					}
					
					if(i <= 5) {
						checkCaseOne(shape, i, j);
						checkCaseTwo(shape, i, j);
						checkCaseFive(shape, i, j);
						checkCaseSix(shape, i, j);
						if(j <= 5){
						checkCaseFifteen(shape, i, j);
						checkCaseSixteen(shape, i, j);
						}
					}
					if(j <= 5) {
						checkCaseNine(shape, i, j);
						checkCaseTen(shape, i, j);
						checkCaseEleven(shape, i, j);
						checkCaseTwelve(shape, i, j);
						if(i <= 5){
						checkCaseThree(shape, i, j);
						checkCaseFour(shape, i, j);
						}
					}
					
					if(i <= 6){
						
					}
					if(j <= 6){
						
					}
					
				}

			}
			
		}
	}
	
	private void checkCaseOne(Shape shape, int x, int y) throws AWTException {
		Shape middleShape = grid.getShapeInGrid(x+1, y+1);
		Shape endShape = grid.getShapeInGrid(x+2, y+1);
		if(shape.getType() == middleShape.getType() && middleShape.getType() == endShape.getType() && shape.getType() == endShape.getType()){
			Robot robot = new Robot();
			robot.mouseMove(BEGIN_X_POINT + (y * 40), BEGIN_Y_POINT + (x * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + (y * 40), BEGIN_Y_POINT + ((x+1) * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
		
	}
	
	private void checkCaseTwo(Shape shape, int x, int y) throws AWTException{
		
		Shape middleShape = grid.getShapeInGrid(x+1, y-1);
		Shape endShape = grid.getShapeInGrid(x+2, y-1);
		if(shape.getType() == middleShape.getType() && middleShape.getType() == endShape.getType() && shape.getType() == endShape.getType()){
			Robot robot = new Robot();
			robot.mouseMove(BEGIN_X_POINT + (y * 40), BEGIN_Y_POINT + (x * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + ((y-1) * 40), BEGIN_Y_POINT + (x * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
		
	}
	
	private void checkCaseThree(Shape shape, int x, int y) throws AWTException {
		Shape middleShape = grid.getShapeInGrid(x + 1, y + 1);
		Shape endShape = grid.getShapeInGrid(x+2, y);
		if(shape.getType() == middleShape.getType() && middleShape.getType() == endShape.getType() && shape.getType() == endShape.getType()){
			Robot robot = new Robot();
			robot.mouseMove(BEGIN_X_POINT + (y* 40), BEGIN_Y_POINT + ((x+1) * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + ((y + 1) * 40), BEGIN_Y_POINT + ((x + 1) * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
	}
	
	private void checkCaseFour(Shape shape, int x, int y) throws AWTException {
		Shape middleShape = grid.getShapeInGrid(x + 1, y - 1);
		Shape endShape = grid.getShapeInGrid(x + 2, y);
		if(shape.getType() == middleShape.getType() && middleShape.getType() == endShape.getType() && shape.getType() == endShape.getType()){
			Robot robot = new Robot();
			robot.mouseMove(BEGIN_X_POINT + ((y-1)* 40), BEGIN_Y_POINT + ((x+1) * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			//robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + (y * 40), BEGIN_Y_POINT + ((x+1) * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
	}
	
	private void checkCaseFive(Shape shape, int x, int y) throws AWTException {
		Shape middleShape = grid.getShapeInGrid(x+1, y);
		Shape endShape = grid.getShapeInGrid(x+2, y+1);
		if(shape.getType() == middleShape.getType() && middleShape.getType() == endShape.getType() && shape.getType() == endShape.getType()){
			Robot robot = new Robot();
			robot.mouseMove(BEGIN_X_POINT + (y* 40), BEGIN_Y_POINT + ((x+2) * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + ((y+1) * 40), BEGIN_Y_POINT + ((x+2) * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
	}
	
	private void checkCaseSix(Shape shape, int x, int y) throws AWTException {
		Shape middleShape = grid.getShapeInGrid(x+1, y);
		Shape endShape = grid.getShapeInGrid(x+2, y-1);
		if(shape.getType() == middleShape.getType() && middleShape.getType() == endShape.getType() && shape.getType() == endShape.getType()){
			Robot robot = new Robot();
			robot.mouseMove(BEGIN_X_POINT + (y* 40), BEGIN_Y_POINT + ((x+2) * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + ((y-1) * 40), BEGIN_Y_POINT + ((x+2) * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
	}

	private void checkCaseSeven(Shape shape, int x, int y) throws AWTException{
		Shape middleShape = grid.getShapeInGrid(x+1, y);
		Shape endShape = grid.getShapeInGrid(x+3, y);
		if(shape.getType() == middleShape.getType() && middleShape.getType() == endShape.getType() && shape.getType() == endShape.getType()){
		Robot robot = new Robot();
		robot.mouseMove(BEGIN_X_POINT + (y* 40), BEGIN_Y_POINT + ((x+2) * 40));
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		robot.mouseMove(BEGIN_X_POINT + (y* 40), BEGIN_Y_POINT + ((x+3) * 40));
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		}
}

	private void checkCaseEight(Shape shape, int x, int y) throws AWTException {
		Shape middleShape = grid.getShapeInGrid(x+2, y);
		Shape endShape = grid.getShapeInGrid(x+3, y);
		if(shape.getType() == middleShape.getType() && middleShape.getType() == endShape.getType() &&
				shape.getType() == endShape.getType()){
			Robot robot = new Robot();
			robot.mouseMove(BEGIN_X_POINT + (y * 40), BEGIN_Y_POINT + (x * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + ((y) * 40), BEGIN_Y_POINT + ((x+1) * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
	}
	
	private void checkCaseNine(Shape shape, int x, int y) throws AWTException {
		Shape middleShape = grid.getShapeInGrid(x, y+1);
		Shape endShape = grid.getShapeInGrid(x-1, y+2);
		if(shape.getType() == middleShape.getType() && middleShape.getType() == endShape.getType() &&
				shape.getType() == endShape.getType()){
			Robot robot = new Robot();
			robot.mouseMove(BEGIN_X_POINT + ((y+2) * 40), BEGIN_Y_POINT + (x * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + ((y+2) * 40), BEGIN_Y_POINT + ( (x-1) * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
	}
	
	private void checkCaseTen(Shape shape, int x, int y) throws AWTException {
		Shape middleShape = grid.getShapeInGrid(x, y+ 1);
		Shape endShape = grid.getShapeInGrid(x + 1, y + 2);
		if(shape.getType() == middleShape.getType() && middleShape.getType() == endShape.getType() &&
				shape.getType() == endShape.getType()){
			Robot robot = new Robot();
			robot.mouseMove(BEGIN_X_POINT + ((y+2) * 40), BEGIN_Y_POINT + (x * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + ((y+2) * 40), BEGIN_Y_POINT + ( (x+1) * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
	}
	
	private void checkCaseEleven(Shape shape, int x, int y) throws AWTException {
		Shape middleShape = grid.getShapeInGrid(x + 1, y + 1);
		Shape endShape = grid.getShapeInGrid(x + 1, y + 2);
		if(shape.getType() == middleShape.getType() && middleShape.getType() == endShape.getType() &&
				shape.getType() == endShape.getType()){
			Robot robot = new Robot();
			robot.mouseMove(BEGIN_X_POINT + (y * 40), BEGIN_Y_POINT + ((x+1) * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + (y * 40), BEGIN_Y_POINT + ( x * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
	}
	
	private void checkCaseTwelve(Shape shape, int x, int y) throws AWTException {
		Shape middleShape = grid.getShapeInGrid(x - 1, y + 1);
		Shape endShape = grid.getShapeInGrid(x - 1, y + 2);
		if(shape.getType() == middleShape.getType() && middleShape.getType() == endShape.getType() &&
				shape.getType() == endShape.getType()){
			Robot robot = new Robot();
			robot.mouseMove(BEGIN_X_POINT + (y * 40), BEGIN_Y_POINT + (x * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + (y * 40), BEGIN_Y_POINT + ( (x-1) * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
	}
	
	private void checkCaseThirteen(Shape shape, int x, int y) throws AWTException {
		Shape middleShape = grid.getShapeInGrid(x, y + 1);
		Shape endShape = grid.getShapeInGrid(x, y + 3);
		if(shape.getType() == middleShape.getType() && middleShape.getType() == endShape.getType() &&
				shape.getType() == endShape.getType()){
			Robot robot = new Robot();
			robot.mouseMove(BEGIN_X_POINT + ((y+2) * 40), BEGIN_Y_POINT + (x * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + ((y+3) * 40), BEGIN_Y_POINT + (x * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
	}
	
	private void checkCaseFifteen(Shape shape, int x, int y) throws AWTException {
		Shape middleShape = grid.getShapeInGrid(x - 1, y + 1);
		Shape endShape = grid.getShapeInGrid(x, y + 2);
		if(shape.getType() == middleShape.getType() && middleShape.getType() == endShape.getType() &&
				shape.getType() == endShape.getType()){
			Robot robot = new Robot();
			robot.mouseMove(BEGIN_X_POINT + ((y+1) * 40), BEGIN_Y_POINT + (x * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + ((y+1) * 40), BEGIN_Y_POINT + ((x-1) * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
	}
	
	private void checkCaseSixteen(Shape shape, int x, int y) throws AWTException {
		Shape middleShape = grid.getShapeInGrid(x + 1, y + 1);
		Shape endShape = grid.getShapeInGrid(x, y + 2);
		if(shape.getType() == middleShape.getType() && middleShape.getType() == endShape.getType() &&
				shape.getType() == endShape.getType()){
			Robot robot = new Robot();
			robot.mouseMove(BEGIN_X_POINT + ((y+1) * 40), BEGIN_Y_POINT + ((x + 1) * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + ((y+1) * 40), BEGIN_Y_POINT + ( x * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
	}
	
	private void checkCaseFourteen(Shape shape, int x, int y) throws AWTException{
		Shape middleShape = grid.getShapeInGrid(x, y+2);
		Shape endShape = grid.getShapeInGrid(x, y+3);
		
		if(shape.getType() == middleShape.getType() && middleShape.getType() == endShape.getType() &&
				shape.getType() == endShape.getType()){
			Robot robot = new Robot();
			robot.mouseMove(BEGIN_X_POINT + ((y) * 40), BEGIN_Y_POINT + ((x) * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + ((y+1) * 40), BEGIN_Y_POINT + ( x * 40));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
	}
	
	private boolean isIndexZero(int index){
		return index == 0;
	}
	
	private boolean isIndexSeven(int index) {
		return index == 7;
	}
	

}
