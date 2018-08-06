import Enums.Color;
import Enums.PieceName;
import Enums.hor;
import Enums.vert;
//responsible for providing an interface for making legal moves
public class Controller {
	ChessBoard board;
	BoardCalc calc;

	public Controller(ChessBoard b) {
		board = b;
		calc = new BoardCalc(board);
	}
	
	public void normalMove(hor X, vert Y, hor toX, vert toY) throws Exception{
		if(calc.isLegalNormalMoveExceptChecking(X, Y, toX, toY)){
			Color whosMove = board.whosMove;
			board.normalMoveAction(X,Y,toX,toY);
			if(calc.KingInCheck(whosMove)){			
				board.undo();
				System.out.println("King is in check, let's try again.");
				board.print();
			}
		}else{
			System.out.print("Illegal Move");
		}
	}
	
	public void castle(Color color, boolean KingsSide){
		
	}
	public void enPassant(hor X, vert Y, hor toX, vert toY){
		
	}
	public void promote(hor X, vert Y, hor toX, vert toY, PieceName p){
		
	}
	

}
