package class_clash;

public enum Direction {
	NEUTRAL(0,0),
	NORTH(0,-1),
	SOUTH(0,1),
	WEST(-1,0),
	EAST(1,0),
	NORTH_EAST(1,-1),
	NORTH_WEST(-1,-1),
	SOUTH_EAST(1,1),
	SOUTH_WEST(-1,1);
	
	private int xVar;
	private int yVar;
	
	private Direction(int xVar, int yVar) {
		this.xVar = xVar;
		this.yVar = yVar;
	}
	
	public int xVar() {
		return xVar;
	}
	
	public int yVar() {
		return yVar;
	}
}
