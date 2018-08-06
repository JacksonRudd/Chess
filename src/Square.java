import Enums.hor;
import Enums.vert;

public class Square {
	public Square(hor e, vert v) {
		X = e;
		Y = v;
				
	}
	hor X;
	vert Y;
	
	public void up(){
		Y = Y.up();
	}
	public void down(){
		Y = Y.down();
	}
	public void left(){
		X = X.left();
	}
	public void right(){
		X = X.right();
	}
	
}
