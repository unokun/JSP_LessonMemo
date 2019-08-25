package sample;

public class Target {
	int sum(int x, int y) {
		return x + y;
	}

	@SuppressWarnings("unused")
	private int sump(int x, int y) {
		return x + y;
	}

	int add(int x, int y) {
		Target2 target2 = createTarget2();
		if (target2.isEqual(x, y)) {
			return 2 * (x + y);
		}
		return (x + y);

	}

	Target2 createTarget2() {
		return new Target2();
	}
}

