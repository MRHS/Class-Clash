package class_clash;

public enum Movement {
	NEUTRAL(0,0),
	UP(0,-1),
	DOWN(0,1),
	LEFT(-1,0),
	RIGHT(1,0);
	
	private int xVar;
	private int yVar;
	
	private Movement(int xVar, int yVar){
		this.xVar = xVar;
		this.yVar = yVar;
	}
	
	public int xVar(){
		return xVar;
	}
	
	public int yVar(){
		return yVar;
	}
}
