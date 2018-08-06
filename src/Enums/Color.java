package Enums;

public enum Color {
	WHITE, BLACK;
	
	public Color opposite(){
		if(this == Color.WHITE){
			return Color.BLACK;
		}
		return Color.WHITE;
		
	}
}
