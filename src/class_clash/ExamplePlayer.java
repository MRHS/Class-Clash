package class_clash;

public class ExamplePlayer extends Player{

	public ExamplePlayer(int x, int y) {
		super(x, y);
	}

	@Override
	protected void runTurn(Arena arena) {
		//Pseudocode until arena is done
		/* player.turnTo(arena.getClosestPlayer(this));
		 * System.out.println("Winning");
		 */
	}

}
