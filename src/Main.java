import Enums.hor;
import Enums.vert;

public class Main {
	public static void main(String[] args) throws Exception{
		Controller cont = new Controller(new ChessBoard());

		cont.normalMove(hor.B, vert.ONE, hor.C, vert.THREE);
		cont.normalMove(hor.D, vert.SEVEN, hor.D, vert.FIVE);
		cont.normalMove(hor.G, vert.ONE, hor.F, vert.THREE);
		cont.normalMove(hor.D, vert.FIVE, hor.D, vert.FOUR);
		cont.normalMove(hor.H, vert.ONE, hor.G, vert.ONE);
		cont.normalMove(hor.D, vert.FOUR, hor.C, vert.THREE);
		cont.normalMove(hor.E, vert.ONE, hor.E, vert.TWO);







	}
}
