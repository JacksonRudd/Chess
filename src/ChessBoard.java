import java.util.Arrays;

import Enums.Color;
import Enums.PieceName;
import Enums.hor;
import Enums.vert;
//responsible for keeping track of data about the game. 
public class ChessBoard {
	public Color whosMove;
	Printer printer;
	Square BlackKing;
	Square WhiteKing;
	public ChessPiece[][] location;
	
	public ChessBoard last;
	
	public ChessBoard(){
		location = new ChessPiece[8][8];

		whosMove = Color.WHITE;
		printer = new Printer();
		BlackKing = new Square(hor.E,vert.EIGHT);
		WhiteKing = new Square(hor.E,vert.ONE);
		last = null;
		setUp();
	}

	public ChessBoard(ChessBoard board) {
		this.BlackKing = new Square(board.BlackKing.X,board.BlackKing.Y);
		this.WhiteKing = new Square(board.WhiteKing.X,board.WhiteKing.Y);
		this.printer = board.printer;
		this.location = new ChessPiece[8][8];
		this.whosMove = board.whosMove;
		//this.location = board.location.clone();
		for(int i = 0; i<8;i++){
			for(int j = 0; j<8;j++){
				this.location[i][j]= board.location[i][j];
			}
		}
	}

	public ChessPiece getPiece(hor x, vert y){
		return location[x.toInt()][y.toInt()];
	}
	
	public void setPiece(hor x, vert y, ChessPiece cp){
		if(cp!= null && cp.getName() == PieceName.KING){
			if(cp.getColor() == Color.BLACK ){
				BlackKing.X = x;
				BlackKing.Y = y;
			}else{
				WhiteKing.X = x;
				WhiteKing.Y = y;
			}
		}
		
		location[x.toInt()][y.toInt()] = cp;

	}
	public void undo() throws Exception{

		System.out.println("undoing move");
		this.whosMove = last.whosMove;
		this.BlackKing = last.BlackKing;
		this.WhiteKing = last.WhiteKing;
		for(int i = 0; i<8;i++){
			for(int j = 0; j<8;j++){
				this.location[i][j]= this.last.location[i][j];
			}
		}
		this.last = this.last.last;


	}
	
	public Square getKingLocation(Color kingsColor){
		return (kingsColor == Color.WHITE)? WhiteKing : BlackKing;	
	}


	

	private void setUp(){
		
		setPiece(hor.A, vert.TWO, new ChessPiece(Color.WHITE, PieceName.PAWN));
		setPiece(hor.B, vert.TWO, new ChessPiece(Color.WHITE, PieceName.PAWN));
		setPiece(hor.C, vert.TWO, new ChessPiece(Color.WHITE, PieceName.PAWN));
		setPiece(hor.D, vert.TWO, new ChessPiece(Color.WHITE, PieceName.PAWN));
		setPiece(hor.E, vert.TWO, new ChessPiece(Color.WHITE, PieceName.PAWN));
		setPiece(hor.F, vert.TWO, new ChessPiece(Color.WHITE, PieceName.PAWN));
		setPiece(hor.G, vert.TWO, new ChessPiece(Color.WHITE, PieceName.PAWN));
		setPiece(hor.H, vert.TWO, new ChessPiece(Color.WHITE, PieceName.PAWN));
		
		setPiece(hor.A, vert.ONE, new ChessPiece(Color.WHITE, PieceName.ROOK));
		setPiece(hor.B, vert.ONE, new ChessPiece(Color.WHITE, PieceName.KNIGHT));
		setPiece(hor.C, vert.ONE, new ChessPiece(Color.WHITE, PieceName.BISHOP));
		setPiece(hor.D, vert.ONE, new ChessPiece(Color.WHITE, PieceName.QUEEN));
		setPiece(WhiteKing.X, WhiteKing.Y, new ChessPiece(Color.WHITE, PieceName.KING));
		setPiece(hor.F, vert.ONE, new ChessPiece(Color.WHITE, PieceName.BISHOP));
		setPiece(hor.G, vert.ONE, new ChessPiece(Color.WHITE, PieceName.KNIGHT));
		setPiece(hor.H, vert.ONE, new ChessPiece(Color.WHITE, PieceName.ROOK));
		
		setPiece(hor.A, vert.SEVEN, new ChessPiece(Color.BLACK, PieceName.PAWN));
		setPiece(hor.B, vert.SEVEN, new ChessPiece(Color.BLACK, PieceName.PAWN));
		setPiece(hor.C, vert.SEVEN, new ChessPiece(Color.BLACK, PieceName.PAWN));
		setPiece(hor.D, vert.SEVEN, new ChessPiece(Color.BLACK, PieceName.PAWN));
		setPiece(hor.E, vert.SEVEN, new ChessPiece(Color.BLACK, PieceName.PAWN));
		setPiece(hor.F, vert.SEVEN, new ChessPiece(Color.BLACK, PieceName.PAWN));
		setPiece(hor.G, vert.SEVEN, new ChessPiece(Color.BLACK, PieceName.PAWN));
		setPiece(hor.H, vert.SEVEN, new ChessPiece(Color.BLACK, PieceName.PAWN));
		
		setPiece(hor.A, vert.EIGHT, new ChessPiece(Color.BLACK, PieceName.ROOK));
		setPiece(hor.B, vert.EIGHT, new ChessPiece(Color.BLACK, PieceName.KNIGHT));
		setPiece(hor.C, vert.EIGHT, new ChessPiece(Color.BLACK, PieceName.BISHOP));
		setPiece(BlackKing.X, BlackKing.Y, new ChessPiece(Color.BLACK, PieceName.KING));
		setPiece(hor.D, vert.EIGHT, new ChessPiece(Color.BLACK, PieceName.QUEEN));
		setPiece(hor.F, vert.EIGHT, new ChessPiece(Color.BLACK, PieceName.BISHOP));
		setPiece(hor.G, vert.EIGHT, new ChessPiece(Color.BLACK, PieceName.KNIGHT));
		setPiece(hor.H, vert.EIGHT, new ChessPiece(Color.BLACK, PieceName.ROOK));

		printer.printBoard(this);
	}



	public void print() {
		printer.printBoard(this);
		
	}
	
	void normalMoveAction(hor X, vert Y, hor toX, vert toY){
		//a copy of the 
		last = new ChessBoard(this);
		ChessPiece p = getPiece(X,Y);
		setPiece(toX ,toY ,p);
		setPiece(X, Y, null);
		p.increment();
		whosMove = whosMove.opposite();
		print();

	}


}
