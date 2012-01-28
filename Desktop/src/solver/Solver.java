package solver;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import gems.*;
import utility.*;


public class Solver {

	private GemGrid grid;
	private final int BEGIN_X_POINT = 302;
	private final int BEGIN_Y_POINT = 96;
	
	
	public Solver(GemGrid grid){
		this.grid = grid;
		GemFactory.init();
	}
	
	public void generateGrid() throws AWTException{
			Robot robot = new Robot();
			robot.delay(2000);
			for(int i = 0; i < grid.getGridXLength(); i++){
				for(int j = 0; j < grid.getGridYLength(); j++){
					
					Color colorAtPointOnScreen = robot.getPixelColor(BEGIN_X_POINT + (j * 64), BEGIN_Y_POINT + (i * 64)); //(int)point.getX(), (int)point.getY());
					
				
					//System.out.println(colorAtPointOnScreen.toString());
					
					Gem gem = GemFactory.generateGemFromColor(colorAtPointOnScreen, i, j);
					grid.setGemLocationInGrid(i, j, gem);
				}
				
			}
			Gem[][] gems = grid.getGemGrid();
			solveGrid();
			
		
		
	}
	
	public void solveGrid() throws AWTException{
		//iterate over each element, look for the 16 patterns at each element
		for(int i = 0; i < grid.getGridXLength(); i++) {
			for(int j = 0; j < grid.getGridYLength(); j++) {
				Gem gem = grid.getGemInGrid(i, j);
				
				//should be broken up into 4 diff cases handling 0,7 0,0 7,0 7,7
				
				//otherwise check all casses
				if(isIndexZero(i) && isIndexZero(j)){
					//check case 1,3,5,7,8,10,11,13,14,16
					checkCaseOne(gem, i, j);
					checkCaseThree(gem, i, j);
					checkCaseFive(gem, i, j);
					checkCaseSeven(gem, i, j);
					checkCaseEight(gem, i, j);
					checkCaseTen(gem, i, j);
					checkCaseThirteen(gem, i, j);
					checkCaseFourteen(gem, i, j);
					checkCaseSixteen(gem, i, j);
					
				}
				else if(isIndexZero(i) && isIndexSeven(j)){
					checkCaseTwo(gem, i, j);
					checkCaseFour(gem, i, j);
					checkCaseSix(gem, i, j);
					checkCaseSeven(gem, i, j);
					checkCaseEight(gem, i, j);
					
				}
				else if(isIndexSeven(i) && isIndexZero(j)){
					//2,3,6,9,12,13,14,15
					checkCaseNine(gem, i, j);
					checkCaseTwelve(gem, i, j);
					checkCaseThirteen(gem, i, j);
					checkCaseFourteen(gem, i, j);
					checkCaseFifteen(gem, i, j);
				}
				else if(isIndexSeven(i) && isIndexSeven(j) ||
						isIndexSeven(i) && j == 6 ||
						isIndexZero(i) && j == 6 ){
					//NOTHING!
					
				}
				
				else if(isIndexZero(i)) {
					//1,2,3,4,5,6,7,8,10,12,13,14,16
					checkCaseOne(gem, i, j);
					checkCaseTwo(gem, i, j);
					checkCaseThree(gem, i, j);
					checkCaseFour(gem, i, j);
					checkCaseFive(gem, i, j);
					checkCaseSix(gem, i, j);
					checkCaseSeven(gem, i, j);
					checkCaseEight(gem, i, j);
					
					//checkCaseTwelve(gem, i, j);
					
					
					if(j <= 4 ){
						checkCaseThirteen(gem, i, j);
						checkCaseFourteen(gem, i, j);
					}
					if(j <= 5){
						checkCaseTen(gem, i, j);
						checkCaseEleven(gem, i, j);
						checkCaseSixteen(gem, i, j);
					}
				}
				else if(isIndexZero(j)){
					//1,3,5,7,8,9,10,11,12,131,14,15,16
					
					
					checkCaseNine(gem, i, j);
					checkCaseTen(gem, i, j);
					checkCaseEleven(gem, i, j);
					checkCaseTwelve(gem, i, j);
					checkCaseFifteen(gem, i, j);
					checkCaseSixteen(gem, i, j);
					
					if(i <= 4){
						checkCaseThirteen(gem, i, j);
						checkCaseFourteen(gem, i, j);
						checkCaseSeven(gem, i, j);
						checkCaseEight(gem, i, j);
					}
					if(i <= 5){
						checkCaseOne(gem, i, j);
						checkCaseThree(gem, i, j);
						checkCaseFive(gem, i, j);
					}
					
					
				}
				else if(isIndexSeven(i)){
					//cant check combs to the right
					//9,12,13,14,15
					checkCaseNine(gem, i, j);
					checkCaseTwelve(gem, i, j);
					checkCaseFifteen(gem, i, j);
					
					if(j <= 4){
						checkCaseThirteen(gem, i, j);
						checkCaseFourteen(gem, i, j);
					}
					
				}
				else if(isIndexSeven(j)){
					//2,4,6, 7,8
					
					if(i <= 4) {
						checkCaseSeven(gem, i, j);
						checkCaseEight(gem, i, j);
						checkCaseTwo(gem, i, j);
						checkCaseFour(gem, i, j);
						checkCaseSix(gem, i, j);
					}
				}
				
				else {
					if(i <= 4){
						checkCaseSeven(gem, i, j);
						checkCaseEight(gem, i, j);
					}
					if(j <= 4){
						checkCaseThirteen(gem, i, j);
						checkCaseFourteen(gem, i, j);
					}
					
					if(i <= 5) {
						checkCaseOne(gem, i, j);
						checkCaseTwo(gem, i, j);
						checkCaseFive(gem, i, j);
						checkCaseSix(gem, i, j);
						if(j <= 5){
						checkCaseFifteen(gem, i, j);
						checkCaseSixteen(gem, i, j);
						}
					}
					if(j <= 5) {
						checkCaseNine(gem, i, j);
						checkCaseTen(gem, i, j);
						checkCaseEleven(gem, i, j);
						checkCaseTwelve(gem, i, j);
						if(i <= 5){
						checkCaseThree(gem, i, j);
						checkCaseFour(gem, i, j);
						}
					}
					
					if(i <= 6){
						
					}
					if(j <= 6){
						
					}
					
				}

			}
			
		}
		generateGrid();
		
	}
	
	private void checkCaseOne(Gem gem, int x, int y) throws AWTException {
		Gem middleGem = grid.getGemInGrid(x+1, y+1);
		Gem endGem = grid.getGemInGrid(x+2, y+1);
		if(gem.getType() == middleGem.getType() && middleGem.getType() == endGem.getType() && gem.getType() == endGem.getType()){
			Robot robot = new Robot();
			 
			robot.mouseMove(BEGIN_X_POINT + (y * 64), BEGIN_Y_POINT + (x * 64));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + ((y+1) * 64), BEGIN_Y_POINT + (x * 64));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
		
	}
	
	private void checkCaseTwo(Gem gem, int x, int y) throws AWTException{
		
		Gem middleGem = grid.getGemInGrid(x+1, y-1);
		Gem endGem = grid.getGemInGrid(x+2, y-1);
		if(gem.getType() == middleGem.getType() && middleGem.getType() == endGem.getType() && gem.getType() == endGem.getType()){
			Robot robot = new Robot();
			 
			robot.mouseMove(BEGIN_X_POINT + (y * 64), BEGIN_Y_POINT + (x * 64));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + ((y-1) * 64), BEGIN_Y_POINT + (x * 64));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
		
	}
	
	private void checkCaseThree(Gem gem, int x, int y) throws AWTException {
		Gem middleGem = grid.getGemInGrid(x + 1, y + 1);
		Gem endGem = grid.getGemInGrid(x+2, y);
		if(gem.getType() == middleGem.getType() && middleGem.getType() == endGem.getType() && gem.getType() == endGem.getType()){
			Robot robot = new Robot();
			 
			robot.mouseMove(BEGIN_X_POINT + (y* 64), BEGIN_Y_POINT + ((x+1) * 64));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + ((y + 1) * 64), BEGIN_Y_POINT + ((x + 1) * 64));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
	}
	
	private void checkCaseFour(Gem Gem, int x, int y) throws AWTException {
		Gem middleGem = grid.getGemInGrid(x + 1, y - 1);
		Gem endGem = grid.getGemInGrid(x + 2, y);
		if(Gem.getType() == middleGem.getType() && middleGem.getType() == endGem.getType() && Gem.getType() == endGem.getType()){
			Robot robot = new Robot();
			 
			robot.mouseMove(BEGIN_X_POINT + ((y-1)* 64), BEGIN_Y_POINT + ((x+1) * 64));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			//robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + (y * 64), BEGIN_Y_POINT + ((x+1) * 64));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
	}
	
	private void checkCaseFive(Gem Gem, int x, int y) throws AWTException {
		Gem middleGem = grid.getGemInGrid(x+1, y);
		Gem endGem = grid.getGemInGrid(x+2, y+1);
		if(Gem.getType() == middleGem.getType() && middleGem.getType() == endGem.getType() && Gem.getType() == endGem.getType()){
			Robot robot = new Robot();
			 
			robot.mouseMove(BEGIN_X_POINT + (y* 64), BEGIN_Y_POINT + ((x+2) * 64));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + ((y+1) * 64), BEGIN_Y_POINT + ((x+2) * 64));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
	}
	
	private void checkCaseSix(Gem Gem, int x, int y) throws AWTException {
		Gem middleGem = grid.getGemInGrid(x+1, y);
		Gem endGem = grid.getGemInGrid(x+2, y-1);
		if(Gem.getType() == middleGem.getType() && middleGem.getType() == endGem.getType() && Gem.getType() == endGem.getType()){
			Robot robot = new Robot();
			 
			robot.mouseMove(BEGIN_X_POINT + (y* 64), BEGIN_Y_POINT + ((x+2) * 64));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + ((y-1) * 64), BEGIN_Y_POINT + ((x+2) * 64));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
	}

	private void checkCaseSeven(Gem Gem, int x, int y) throws AWTException{
		Gem middleGem = grid.getGemInGrid(x+1, y);
		Gem endGem = grid.getGemInGrid(x+3, y);
		if(Gem.getType() == middleGem.getType() && middleGem.getType() == endGem.getType() && Gem.getType() == endGem.getType()){
		Robot robot = new Robot();
		 
		robot.mouseMove(BEGIN_X_POINT + (y* 64), BEGIN_Y_POINT + ((x+2) * 64));
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		robot.mouseMove(BEGIN_X_POINT + (y* 64), BEGIN_Y_POINT + ((x+3) * 64));
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		}
}

	private void checkCaseEight(Gem Gem, int x, int y) throws AWTException {
		Gem middleGem = grid.getGemInGrid(x+2, y);
		Gem endGem = grid.getGemInGrid(x+3, y);
		if(Gem.getType() == middleGem.getType() && middleGem.getType() == endGem.getType() &&
				Gem.getType() == endGem.getType()){
			Robot robot = new Robot();
			 
			robot.mouseMove(BEGIN_X_POINT + (y * 64), BEGIN_Y_POINT + (x * 64));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + ((y) * 64), BEGIN_Y_POINT + ((x+1) * 64));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
	}
	
	private void checkCaseNine(Gem Gem, int x, int y) throws AWTException {
		Gem middleGem = grid.getGemInGrid(x, y+1);
		Gem endGem = grid.getGemInGrid(x-1, y+2);
		if(Gem.getType() == middleGem.getType() && middleGem.getType() == endGem.getType() &&
				Gem.getType() == endGem.getType()){
			Robot robot = new Robot();
			 
			robot.mouseMove(BEGIN_X_POINT + ((y+2) * 64), BEGIN_Y_POINT + (x * 64));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + ((y+2) * 64), BEGIN_Y_POINT + ( (x-1) * 64));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
	}
	
	private void checkCaseTen(Gem Gem, int x, int y) throws AWTException {
		Gem middleGem = grid.getGemInGrid(x, y+ 1);
		Gem endGem = grid.getGemInGrid(x + 1, y + 2);
		if(Gem.getType() == middleGem.getType() && middleGem.getType() == endGem.getType() &&
				Gem.getType() == endGem.getType()){
			Robot robot = new Robot();
			 
			robot.mouseMove(BEGIN_X_POINT + ((y+2) * 64), BEGIN_Y_POINT + (x * 64));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + ((y+2) * 64), BEGIN_Y_POINT + ( (x+1) * 64));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
	}
	
	private void checkCaseEleven(Gem Gem, int x, int y) throws AWTException {
		Gem middleGem = grid.getGemInGrid(x + 1, y + 1);
		Gem endGem = grid.getGemInGrid(x + 1, y + 2);
		if(Gem.getType() == middleGem.getType() && middleGem.getType() == endGem.getType() &&
				Gem.getType() == endGem.getType()){
			Robot robot = new Robot();
			 
			robot.mouseMove(BEGIN_X_POINT + (y * 64), BEGIN_Y_POINT + ((x+1) * 64));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + (y * 64), BEGIN_Y_POINT + ( x * 64));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
	}
	
	private void checkCaseTwelve(Gem Gem, int x, int y) throws AWTException {
		Gem middleGem = grid.getGemInGrid(x - 1, y + 1);
		Gem endGem = grid.getGemInGrid(x - 1, y + 2);
		if(Gem.getType() == middleGem.getType() && middleGem.getType() == endGem.getType() &&
				Gem.getType() == endGem.getType()){
			Robot robot = new Robot();
			 
			robot.mouseMove(BEGIN_X_POINT + (y * 64), BEGIN_Y_POINT + (x * 64));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + (y * 64), BEGIN_Y_POINT + ( (x-1) * 64));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
	}
	
	private void checkCaseThirteen(Gem Gem, int x, int y) throws AWTException {
		Gem middleGem = grid.getGemInGrid(x, y + 1);
		Gem endGem = grid.getGemInGrid(x, y + 3);
		if(Gem.getType() == middleGem.getType() && middleGem.getType() == endGem.getType() &&
				Gem.getType() == endGem.getType()){
			Robot robot = new Robot();
			 
			robot.mouseMove(BEGIN_X_POINT + ((y+2) * 64), BEGIN_Y_POINT + (x * 64));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + ((y+3) * 64), BEGIN_Y_POINT + (x * 64));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
	}
	
	private void checkCaseFifteen(Gem Gem, int x, int y) throws AWTException {
		Gem middleGem = grid.getGemInGrid(x - 1, y + 1);
		Gem endGem = grid.getGemInGrid(x, y + 2);
		if(Gem.getType() == middleGem.getType() && middleGem.getType() == endGem.getType() &&
				Gem.getType() == endGem.getType()){
			Robot robot = new Robot();
			 
			robot.mouseMove(BEGIN_X_POINT + ((y+1) * 64), BEGIN_Y_POINT + (x * 64));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + ((y+1) * 64), BEGIN_Y_POINT + ((x-1) * 64));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
	}
	
	private void checkCaseSixteen(Gem Gem, int x, int y) throws AWTException {
		Gem middleGem = grid.getGemInGrid(x + 1, y + 1);
		Gem endGem = grid.getGemInGrid(x, y + 2);
		if(Gem.getType() == middleGem.getType() && middleGem.getType() == endGem.getType() &&
				Gem.getType() == endGem.getType()){
			Robot robot = new Robot();
			 
			robot.mouseMove(BEGIN_X_POINT + ((y+1) * 64), BEGIN_Y_POINT + ((x + 1) * 64));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + ((y+1) * 64), BEGIN_Y_POINT + ( x * 64));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
	}
	
	private void checkCaseFourteen(Gem Gem, int x, int y) throws AWTException{
		Gem middleGem = grid.getGemInGrid(x, y+2);
		Gem endGem = grid.getGemInGrid(x, y+3);
		
		if(Gem.getType() == middleGem.getType() && middleGem.getType() == endGem.getType() &&
				Gem.getType() == endGem.getType()){
			Robot robot = new Robot();
			robot.mouseMove(BEGIN_X_POINT + ((y) * 64), BEGIN_Y_POINT + ((x) * 64));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mouseMove(BEGIN_X_POINT + ((y+1) * 64), BEGIN_Y_POINT + ( x * 64));
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
