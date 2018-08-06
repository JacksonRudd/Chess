import Enums.Color;
import Enums.hor;
import Enums.vert;
//responsible for displaying game
public class Printer {
	
	private final String black = "----";
	private final String white = "||||";
	
	public void printIllegalMove(){
		System.out.println("Illegal");
	}
	
	public void printBoard(ChessBoard board){
		hor X = hor.A;
		vert Y = vert.EIGHT;
		while(Y!=null){
			//increment Y if X is null
			if(X == null){
				System.out.println("   |" + (Y.toInt()+1));
				X = hor.A;
				Y = Y.down();
				continue;
			}
			printSquare(X,Y,board);
			X = X.right();
		}
		System.out.println("________________________________");
		System.out.print(" A  "+" B  "+" C  "+" D  "+" E  "+" F  "+" G  "+" H  ");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
			
		
	}

	private void printSquare(hor X, vert Y,ChessBoard board){
		ChessPiece cp = board.getPiece(X, Y);
		if(cp != null){
			printPiece(cp);
		} else {
			printEmptySquare(X,Y);
		}
	}
	
	
	private void printPiece(ChessPiece cp){
		System.out.print(" ");
		switch(cp.getName()){
			case QUEEN:
				System.out.print("Q");
				break;
			case BISHOP:
				System.out.print("B");
				break;
			case PAWN:
				System.out.print("P");
				break;
			case ROOK:
				System.out.print("R");
				break;
			case KNIGHT:
				System.out.print("N");
				break;
			case KING:
				System.out.print("K");
				break;
		}
		
		
		
		
		if(cp.getColor() == Color.BLACK){
			System.out.print("! ");
		}else{
			System.out.print("  ");
		}
	}
	
	
	private void printEmptySquare(hor X, vert Y){
		if((X.toInt() + Y.toInt()) %2 == 0){
			System.out.print(black);
		}else{
			System.out.print(white);
		}
	}
	
	public void printNoPiece() {
		System.out.println("There is no piece there. Try moving Again.");		
	}
}
