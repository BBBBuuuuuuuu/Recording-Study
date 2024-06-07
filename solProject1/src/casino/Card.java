package casino;

public class Card {

	private int shape; // 1 : 스페이드, 2 : 다이아, 3 : 하트, 4: 클로버
	private int num; // 1 : A, 11 : J, 12 : Q, 13 : K
	
	public Card() {};
	public Card(int shape, int num) {
		setShape(shape);
		setNum(num);
	}

	public int getShape() {
		return shape;
	}

	public void setShape(int shape) {
		this.shape = shape;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String shapeDTG(int shape) {
		String s = null;
		if(shape == 1) {
			s = "S";
		}else if(shape == 2) {
			s = "D";
		}else if(shape == 3) {
			s = "H";
		}else if(shape == 4) {
			s = "C";
		}
		return s;
		
	}
	public String printCard(int shape, int num) {
		String s = shapeDTG(shape);
		String msg ="<"+s+num+">"; 
		return msg;
	}
	
}
