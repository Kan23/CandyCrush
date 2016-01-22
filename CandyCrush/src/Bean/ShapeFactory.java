package Bean;

import java.io.IOException;

public class ShapeFactory {

	public Shape getShape(String shapeType) throws IOException{
		if(shapeType == null){
			return null;
		}		
		if(shapeType.equals("BOO")){
			return new Boo();
		} else if(shapeType.equals("BOWSERJR")){
			return new BowserJr();
		} else if(shapeType.equals("MARIO")){
			return new Mario();
		} else if(shapeType.equals("PEACH")){
			return new Peach();
		} else if(shapeType.equals("TOAD")){
			return new Toad();
		} else if(shapeType.equals("YOSHI")){
			return new Yoshi();
		} else if(shapeType.equals("WHITE")){
			return new White();
		}
		return null;
	}
}
