package class_clash;

import java.util.ArrayList;

public class ExampleRobot extends RobotOutline{

	public ExampleRobot(int x, int y) {
		super(x, y);
	}

	@Override
	protected void autonomous() {
		setDirection(Movement.DOWN);
	}
}
