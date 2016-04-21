package Bean;

import java.io.IOException;

public class ShapeFactory {

	public Shape getShape(String shapeType) throws IOException{
		if(shapeType == null){
			return null;
		}		
		if(shapeType.equals("BEANONE")){
			return new BeanOne();
		} else if(shapeType.equals("BEANTWO")){
			return new BeanTwo();
		} else if(shapeType.equals("BEANTHREE")){
			return new BeanThree();
		} else if(shapeType.equals("BEANFOUR")){
			return new BeanFour();
		} else if(shapeType.equals("BEANFIVE")){
			return new BeanFive();
		} else if(shapeType.equals("BEANSIX")){
			return new BeanSix();
		} else if(shapeType.equals("WHITE")){
			return new White();
		}
		return null;
	}
}
