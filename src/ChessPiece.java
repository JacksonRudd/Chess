import Enums.Color;
import Enums.PieceName;

public class ChessPiece {

	Color color;
	PieceName name;
	int numOfMoves;
	
	
	
	public ChessPiece(Color c, PieceName name) {
		super();
		this.color = c;
		this.name = name;
	}

	public PieceName getName() {
		return name;
	}
	public int getNumOfMoves() {
		return numOfMoves;
	}
	public void increment(){
		numOfMoves += 1;
	}
	public Color getColor() {
		return color;
	}

}
