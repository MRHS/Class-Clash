package class_clash;

public class ExamplePlayer2 extends Player{

	public ExamplePlayer2(int x, int y) {
		super(x, y);
	}

	@Override
	protected void runTurn(Arena arena) {
		setDirection(Direction.SOUTH_WEST);
	}

}
