package class_clash;

public class ExamplePlayer extends Player{

	public ExamplePlayer(int x, int y, Arena startArena) {
		super(x, y, startArena);
	}

	@Override
	protected void runTurn(Arena arena) {
		//Pseudocode until arena is done
		/* player.turnTo(arena.getClosestPlayer());
		 * System.out.println("Winning");
		 */
	}

}
