import Enums.Color;
import Enums.HorDir;
import Enums.HorDir;
import Enums.PieceName;
import Enums.VertDir;
import Enums.VertDir;
import Enums.hor;
import Enums.vert;
//responsible for making calculations
public class BoardCalc {
	
	ChessBoard board;
	
	public boolean isLegalNormalMoveExceptChecking(hor X, vert Y, hor toX,vert toY){
		ChessPiece piece = board.getPiece(X, Y);
		
		//you need to move a valid piece
		if(piece == null){
			System.out.println("you need to select a piece");
			return false;
		}
		//you can't move if it's not your turn!
		if(piece.getColor() != board.whosMove){
			System.out.println("you cant move if it's not your turn");
			return false;
		}

		//you can't move on top of your own piece!
		if(board.getPiece(toX, toY)!= null){
			if(board.getPiece(toX, toY).getColor() == board.whosMove){
				System.out.println("you cant move on your own piece");
				return false;
			}
		}

		//You can only move to a square in a pieces span
		PieceName type = piece.getName();
		switch(type){
			case KING:
				return inKingSpan(X, Y, toX,toY);
			case QUEEN:
				return inQueenSpan(X, Y, toX,toY);
			case KNIGHT:
				return inKnightSpan(X, Y, toX,toY);
			case BISHOP:
				return inBishopSpan(X, Y, toX,toY);
			case ROOK:
				return inRookSpan(X, Y, toX,toY);
			case PAWN:
				return inPawnSpan(X, Y, toX,toY,piece.getNumOfMoves(), piece.getColor());
		}
		return false;
		
	}

	public boolean KingInCheck(Color kingColor) {
		Square position = board.getKingLocation(kingColor);
		Square dummyPosition = new Square(position.X,position.Y);
		Square kingLocation = board.getKingLocation(kingColor);
		ChessPiece cp;
		PieceName name;
		//check for opposite color queen or bishop on diagonal
		for (HorDir H : HorDir.values()){
			for (VertDir V : VertDir.values()){
				cp = getFirstDiagonalBlocker(H, V, kingLocation);


				if((cp != null)&&(cp.getColor() != kingColor)){
					name = cp.getName();
					 if (name.equals(PieceName.QUEEN)||name.equals(PieceName.BISHOP)){
						 return true;
					 }
				 }
			}	
		}
		//check for opposite color rooks or queens on vertical
		for (VertDir V : VertDir.values()){
			cp = getFirstVerticalBlocker(V, kingLocation);
			if((cp != null)&&(cp.getColor() != kingColor)){
				name = cp.getName();
				 if (name.equals(PieceName.ROOK)||name.equals(PieceName.QUEEN)){
					 return true;
				 }
			 }
		}
		//check for opposite color rooks or queens on horizontal

		for (HorDir H : HorDir.values()){
			cp = getFirstHorizontalBlocker(H, kingLocation);
			if((cp != null)&&(cp.getColor() != kingColor)){
				name = cp.getName();
				 if (name.equals(PieceName.ROOK)||name.equals(PieceName.QUEEN)){
					 return true;
				 }
			 }
		}
		return false;
		
	}

	public BoardCalc(ChessBoard b) {
		board= b;
	}
	
	private boolean inPawnSpan(hor x, vert y, hor toX, vert toY, int numOfMoves, Color color) {
		
		int direction = color == (Color.WHITE)? 1:-1;
		//you can attack diagonal
		int xDifAbs = Math.abs(x.toInt()-toX.toInt());
		int yDif = toY.toInt()-y.toInt();
		if (xDifAbs == 1 && yDif== 1*direction&&(board.getPiece(toX, toY).getColor()==color.opposite())){
			if(board.getPiece(toX, toY).getColor()==color.opposite()){
				return true;
			}
		}
		//if not attack then only move forward
		if(x.toInt() != toX.toInt()){
			System.out.print("not in span");
			return false;
		}
		
		if(toY.toInt() == (y.toInt() + 1*direction)){
			return true;
		}
		if(toY.toInt() == (y.toInt() + 2*direction)&&(numOfMoves == 0)){
			return true;
		}
				return false;
	}

	private boolean inRookSpan(hor x, vert y, hor toX, vert toY) {
		
		return isVerticle(x,  y, toX, toY) && !verticleBlockersNotInclusive(x, y, toX, toY)||isHorizontal(x,  y, toX, toY) && !horizontalBlockersNotInclusive(x, y, toX, toY);
	}

	private boolean inBishopSpan(hor x, vert y, hor toX, vert toY) {
		if (isDiagonal(x,  y, toX, toY)){
			try {
				
				return !diagonalBlockersNotInclusive(x, y, toX, toY);
			} catch (Exception e) {}
		}
		return false;
	}

	private boolean inKnightSpan(hor x, vert y, hor toX, vert toY) {
		int xDif = toX.toInt()-x.toInt();
		int yDif =toY.toInt()- y.toInt();
		return (Math.abs(Math.min(xDif, yDif))==1 )&&(Math.abs(Math.max(xDif, yDif))==2);
	}

	private boolean inQueenSpan(hor x, vert y, hor toX, vert toY) {
		// TODO Auto-generated method stub
		return this.inBishopSpan(x, y, toX, toY)||this.inRookSpan(x, y, toX, toY);
	}

	private boolean inKingSpan(hor x, vert y, hor toX, vert toY) {
		if(Math.abs(x.toInt()-toX.toInt())>1){
			return false;
		}
		if(Math.abs(y.toInt()-toY.toInt())>1){
			return false;
		}
		return true;
	}

	public boolean diagonalBlockersNotInclusive(hor X, vert Y, hor toX, vert toY) throws Exception{
		int xDif = toX.toInt() - X.toInt();
		int yDif = toY.toInt() - Y.toInt();
		if(xDif != yDif){
		    throw new Exception("Not Diagonal!");
		}
		boolean up    = yDif > 0;
		boolean right =  xDif > 0;
		
		while(X.toInt() != toX.toInt()){
			X = right ? X.right() : X.left();
			Y = up ? Y.up() : Y.down();
			if(board.getPiece(X,Y)!=null){
				return true;
			}
		}
		return false;
	}
	
	public boolean verticleBlockersNotInclusive(hor X, vert Y, hor toX, vert toY){
		boolean up    = (toY.toInt() - Y.toInt() > 0);
		while(Y.toInt() != toY.toInt()){
			Y = up ? Y.up() : Y.down();
			if(board.getPiece(X,Y)!=null){
				return true;
			}
		}
		return false;
	}
	
	public boolean horizontalBlockersNotInclusive(hor X, vert Y, hor toX, vert toY){
		boolean right = (toX.toInt() - X.toInt() > 0);
		
		while(X.toInt() != toX.toInt()){
			X = right ? X.right() : X.left();
			if(board.getPiece(X,Y)!=null){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isDiagonal(hor X, vert Y, hor toX, vert toY){
		int xDif = X.toInt() - toX.toInt();
		int yDif = Y.toInt() - toY.toInt();

		return xDif == yDif;
	}
	
	public static boolean isVerticle(hor X, vert Y, hor toX, vert toY){
		return (X.toInt() - toX.toInt()) == 0;
		
	}
	
	public static boolean isHorizontal(hor X, vert Y, hor toX, vert toY){
		return (Y.toInt() - toY.toInt()) == 0;
		
	}

	public boolean isCastle(hor x, vert y, hor toX, vert toY) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isPromotion(hor x, vert y, hor toX, vert toY) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public ChessPiece getFirstDiagonalBlocker(HorDir H, VertDir V, Square s){
		hor horizontalEdge = (H == HorDir.LEFT) ? hor.A: hor.H;
		vert verticalEdge = (V == VertDir.UP)? vert.EIGHT: vert.ONE;
		hor X = s.X;
		vert Y = s.Y;

		while(X != horizontalEdge && Y != verticalEdge){
			Y = Y.next(V);
			X = X.next(H);
			if(board.getPiece(X, Y)!= null){
				return board.getPiece(X, Y);
			}
		}
		return null;
		
	}
	
	public ChessPiece getFirstVerticalBlocker(VertDir V, Square s){
		vert verticalEdge = (V == VertDir.UP)? vert.EIGHT: vert.ONE;
		hor X = s.X;
		vert Y = s.Y;
		while( Y != verticalEdge){
			Y = Y.next(V);
			
			if(board.getPiece(X, Y)!= null){
				return board.getPiece(X, Y);
			}
		}
		return null;
		
	}

	public ChessPiece getFirstHorizontalBlocker(HorDir H, Square s){
		hor horizontalEdge = (H == HorDir.LEFT) ? hor.A: hor.H;
		hor X = s.X;
		vert Y = s.Y;
		while(X != horizontalEdge ){
			X = X.next(H);
			if(board.getPiece(X, Y)!= null){
				return board.getPiece(X, Y);
			}
		}
		return null;
	}
	







	

	
}
