package Enums;

public enum hor {
	A , B , C ,D , E, F, G, H;
	
	public int toInt(){
		switch(this){
		case A:
			return 0;
		case B:
			return 1;
		case C:
			return 2;
		case D: 
			return 3;
		case E:
			return 4;
		case F:
			return 5;
		case G:
			return 6;	
		case H:
			return 7;
		}
		return 0;
	}
	
	public hor right(){
		switch(this){
		case A:
			return B;
		case B:
			return C;
		case C:
			return D;
		case D: 
			return E;
		case E:
			return F;
		case F:
			return G;
		case G:
			return H;	
		case H:
			return null;
		}
		return null;
	}
	
	public hor left(){
		switch(this){
		case A:
			return null;
		case B:
			return A;
		case C:
			return B;
		case D: 
			return C;
		case E:
			return D;
		case F:
			return E;
		case G:
			return F;	
		case H:
			return G;
		}
		return null;
	}

	public hor next(HorDir h2) {
		if (h2== HorDir.LEFT){
			return left();
		}		
		return right();
	}
	
}
