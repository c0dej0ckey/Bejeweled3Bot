package utility;

import java.awt.Color;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import gems.*;

public class GemFactory {
	
	public static Gem[] randomGems = { new Square(), new Circle(), new Diamond(), new Rhombus(), new Triangle(), new GreenHexagon(), new  OrangeHexagon()};
	private static Random randomGen = new Random();
	
	public static final int Red1 = -714443;
	public static final int Red2 = -714443;
	public static final int Red3 = -583626;
	public static final int Red4 = -518090;
	public static final int Red5 = -255432;
	public static final int Red6 = -386504;
	public static final int Red7 = -386247;
	public static final int Red8 = -320711;
	
	public static final int Green1 = -16149224;
	public static final int Green2 = -16149224;
	public static final int Green3 = -16148966;
	public static final int Green4 = -15882720;
	public static final int Green5 = -13641390;
	public static final int Green6 = -11470206;
	public static final int Green7 = -11208056;
	public static final int Green8 = -11208054;
	public static final int Green9 = -11208055;
	
	public static final int Blue1 = -15955641;
	public static final int Blue2 = -15955461;
	public static final int Blue3 = -15889669;
	public static final int Blue4 = -15758341;
	public static final int Blue5 = -15692804;
	public static final int Blue6 = -15628808;
	public static final int Blue7 = -15697682;
	public static final int Blue8 = -15699737;
	public static final int Blue9 = -15889925;
	
	
	public static final int Orange1 = -2141417;
	public static final int Orange2 = -2141417;
	public static final int Orange3 = -2140649; 
	public static final int Orange4 = -1481955;
	public static final int Orange5 = -478132;
	public static final int Orange6 = -329866;
	public static final int Orange7 = -263814;
	public static final int Orange8 = -329093;
	public static final int Orange9 = -329865;
	public static final int Orange10 = -263815;
	
	
	public static final int Grey1 = -2894893;
	public static final int Grey2 = -2894893;
	public static final int Grey3 = -2697514;
	public static final int Grey4 = -1644826;
	public static final int Grey5 = -460552;
	public static final int Grey6 = -328966;
	public static final int Grey7 = -328966;
	public static final int Grey8 = -263173;
	public static final int Grey9 = -1909802;
	public static final int GreyFlame = -1;
	
	public static final int Yellow1 = -332000;
	public static final int Yellow2 = -332000;
	public static final int Yellow3 = -331489;
	public static final int Yellow4 = -330720;
	public static final int Yellow5 = -198622;
	public static final int Yellow6 = -395231;
	public static final int Yellow7 = -460511;
	public static final int Yellow8 = -329180;
	public static final int Yellow9 = -395231;
	public static final int YellowFlame1 = -201;
	public static final int YellowFlame2 = -2012;
	
	public static final int Purple1 = -1242131;
	public static final int Purple2 = -1242131;
	public static final int Purple3 = -1111057;
	public static final int Purple4 = -1110544;
	public static final int Purple5 = -1110031;
	public static final int Purple6 = -913679;
	public static final int Purple7 = -651789;
	public static final int Purple8 = -782093;
	
	public static Set<Integer> redSet;
	public static Set<Integer> greenSet;
	public static Set<Integer> blueSet;
	public static Set<Integer> orangeSet;
	public static Set<Integer> greySet;
	public static Set<Integer> yellowSet;
	public static Set<Integer> purpleSet;
	
	public static final int Orange = -131707;
	public static final int Purple = -389124;
	public static final int Blue = -15437335;
	public static final int Yellow = -197083;
	public static final int Green = -10420839;
	public static final int Red = -189638;
	public static final int Grey = -131587;
	
	
	
	public static void init(){
		
		redSet = new HashSet<Integer>();
		greenSet = new HashSet<Integer>();
		blueSet = new HashSet<Integer>();
		orangeSet = new HashSet<Integer>();
		greySet = new HashSet<Integer>();
		yellowSet = new HashSet<Integer>();
		purpleSet = new HashSet<Integer>();
		addConstants();
	}

	public static Gem generateGemFromColor(Color color, int i , int j){
		Gem gem = new NullGem();
		
		
		if(colorIsYellow(color)){
			gem = new Rhombus();
		}
		else if(colorIsGray(color)){
			gem = new Circle();
		}
		else if(colorIsOrange(color)){
			gem = new OrangeHexagon();
		}
		else if(colorIsPurple(color)){
			gem = new Triangle();
		}
		else if(colorIsGreen(color)){
			gem = new GreenHexagon();
		}
		else if(colorIsBlue(color)){
			gem = new Diamond();
		}
		else if(colorIsRed(color)){
			gem = new Square();
		}
		if(gem instanceof NullGem){
			gem = chooseGemBasedOnColor(color);
		}
		return gem;
	}
	
	
	private static boolean colorIsRed(Color color){
		//return redSet.contains(color.getRGB());
		return color.getRGB() == Red;
	}
	
	private static boolean colorIsGreen(Color color){
		//return greenSet.contains(color.getRGB());
		return color.getRGB() == Green;
	}
	
	private static boolean colorIsBlue(Color color){
		//return blueSet.contains(color.getRGB());
		return color.getRGB() == Blue;
	}
	
	private static boolean colorIsYellow(Color color){
		//return yellowSet.contains(color.getRGB());
		return color.getRGB() == Yellow;
	}
	
	private static boolean colorIsPurple(Color color){
		//return purpleSet.contains(color.getRGB());
		return color.getRGB() == Purple;
	}
	
	private static boolean colorIsGray(Color color){
		//return greySet.contains(color.getRGB());
		return color.getRGB() == Grey;
	}
	
	private static boolean colorIsOrange(Color color){
		//return orangeSet.contains(color.getRGB());
		return color.getRGB() == Orange;
	}
	
	private static Gem chooseGemBasedOnColor(Color color){
		Gem gem = new NullGem();
		if(color.getRed() > 230){
			gem =  new Square();
		}
		if(color.getBlue() > 230){
			gem =  new Diamond();
		}
		if(color.getRed() == color.getBlue()){
			gem = new Triangle();
		}
		
		if(color.getGreen() > 215){
			gem = new GreenHexagon();
		}
		
		if(color.getGreen() == 255 && color.getRed() == 255 && color.getBlue() < 50){
			gem = new Rhombus();
		}
		
		
		if(color.getRed() == color.getGreen() && color.getBlue() < 255 && color.getBlue() > 50){
			gem = new OrangeHexagon();
			
		}
		if(color.getRed() == color.getBlue() && color.getBlue() == color.getGreen()){
			gem = new Circle();
		}
		if(gem instanceof NullGem){
			//blue fire gem
			//generate random gem to return
			int num = randomGen.nextInt(6);
			gem = randomGems[num];
		}
		return gem;
	}
	
	private static void addConstants(){
		redSet.add(Red1);
		redSet.add(Red2);
		redSet.add(Red3);
		redSet.add(Red4);
		redSet.add(Red5);
		redSet.add(Red6);
		redSet.add(Red7);
		redSet.add(Red8);
		
		greenSet.add(Green1);
		greenSet.add(Green2);
		greenSet.add(Green3);
		greenSet.add(Green4);
		greenSet.add(Green5);
		greenSet.add(Green6);
		greenSet.add(Green7);
		greenSet.add(Green8);
		greenSet.add(Green9);
		
		blueSet.add(Blue1);
		blueSet.add(Blue2);
		blueSet.add(Blue3);
		blueSet.add(Blue4);
		blueSet.add(Blue5);
		blueSet.add(Blue6);
		blueSet.add(Blue7);
		blueSet.add(Blue8);
		blueSet.add(Blue9);
		
		
		orangeSet.add(Orange1);
		orangeSet.add(Orange2);
		orangeSet.add(Orange3);
		orangeSet.add(Orange4);
		orangeSet.add(Orange5);
		orangeSet.add(Orange6);
		orangeSet.add(Orange7);
		orangeSet.add(Orange8);
		orangeSet.add(Orange9);
		orangeSet.add(Orange10);
		
		greySet.add(Grey1);
		greySet.add(Grey2);
		greySet.add(Grey3);
		greySet.add(Grey4);
		greySet.add(Grey5);
		greySet.add(Grey6);
		greySet.add(Grey7);
		greySet.add(Grey8);
		greySet.add(Grey9);
		greySet.add(GreyFlame);
		
		purpleSet.add(Purple1);
		purpleSet.add(Purple2);
		purpleSet.add(Purple3);
		purpleSet.add(Purple4);
		purpleSet.add(Purple5);
		purpleSet.add(Purple6);
		purpleSet.add(Purple7);
		purpleSet.add(Purple8);
		
		yellowSet.add(Yellow1);
		yellowSet.add(Yellow2);
		yellowSet.add(Yellow3);
		yellowSet.add(Yellow4);
		yellowSet.add(Yellow5);
		yellowSet.add(Yellow6);
		yellowSet.add(Yellow7);
		yellowSet.add(Yellow8);
		yellowSet.add(Yellow9);
		yellowSet.add(YellowFlame1);
		yellowSet.add(YellowFlame2);
		
	}
	
}
