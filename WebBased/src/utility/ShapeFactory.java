package utility;

import java.awt.Color;

import shapes.*;

public class ShapeFactory {

	public static Shape generateShapeFromColor(Color color){
		Shape shape = new NullShape();
		
		
		if(colorIsYellow(color)){
			shape = new Rhombus();
		}
		else if(colorIsGray(color)){
			shape = new Circle();
		}
		else if(colorIsOrange(color)){
			shape = new OrangeHexagon();
		}
		else if(colorIsPurple(color)){
			shape = new Triangle();
		}
		else if(colorIsGreen(color)){
			shape = new GreenHexagon();
		}
		else if(colorIsBlue(color)){
			shape = new Diamond();
		}
		else if(colorIsRed(color)){
			shape = new Square();
		}
		
		return shape;
	}
	
	
	private static boolean colorIsRed(Color color){
		return 175 <= color.getRed() && color.getRed() <= 215;
	}
	
	private static boolean colorIsGreen(Color color){
		return 245 <= color.getGreen() && color.getGreen() <= 254;
	}
	
	private static boolean colorIsBlue(Color color){
		return 254 <= color.getBlue() && color.getBlue() <= 254;
	}
	
	private static boolean colorIsYellow(Color color){
		return color.getBlue() < 20 && 80 <= color.getGreen() && color.getGreen() <= 150
		&& 155 <= color.getRed() && color.getRed() <= 200;
	}
	
	private static boolean colorIsPurple(Color color){
		return color.getRed() == color.getBlue();
	}
	
	private static boolean colorIsGray(Color color){
		return 145 <= color.getBlue() && color.getBlue() <= 190 &&
		145 <= color.getGreen() && color.getGreen() <= 190 &&
		145 <= color.getRed() && color.getRed() <= 190;
	}
	
	private static boolean colorIsOrange(Color color){
		return 216 <= color.getRed() && color.getRed() <= 254 &&
		216 <= color.getGreen() && color.getGreen() <= 254;
	}
	
	
}
