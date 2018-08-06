package Enums;

public enum vert {
	ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT;
	

	public  int toInt(){
		switch(this){
			case ONE:
				return 0;
			case TWO:
				return 1;
			case THREE:
				return 2;
			case FOUR: 
				return 3;
			case FIVE:
				return 4;
			case SIX:
				return 5;
			case SEVEN:
				return 6;
			case EIGHT: 
				return 7;			
		}
		return 0;
	}
	public vert up(){
		switch(this){
		case ONE:
			return TWO;
		case TWO:
			return THREE;
		case THREE:
			return FOUR;
		case FOUR: 
			return FIVE;
		case FIVE:
			return SIX;
		case SIX:
			return SEVEN;
		case SEVEN:
			return EIGHT;
		case EIGHT: 
			return null;			
		}
		return null;
	}
	public vert down(){
		switch(this){
			case ONE:
				return null;
			case TWO:
				return ONE;
			case THREE:
				return TWO;
			case FOUR: 
				return THREE;
			case FIVE:
				return FOUR;
			case SIX:
				return FIVE;
			case SEVEN:
				return SIX;
			case EIGHT: 
				return SEVEN;			
		}
		return null;
	}
	public vert next(VertDir v) {
		if(v == VertDir.UP){
			return this.up();
		}
		return this.down();
		
	}
	
}
