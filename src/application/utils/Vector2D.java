package application.utils;

public class Vector2D {
	private int x, y;

	public Vector2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public static int calcDistance(final Vector2D v1, final Vector2D v2) {
		return (int) Math.sqrt(Math.pow(v1.x - v2.x, 2) + Math.pow(v1.y - v2.y, 2));
	}

	public static Vector2D add(final Vector2D v1, final Vector2D v2) {
		return new Vector2D(v1.x + v2.x, v1.y + v2.y);
	}

	@Override
	public String toString() {
		return "( x: " + x + " | y: " + y + " )";
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Vector2D) {
			Vector2D v = (Vector2D) o;
			return v.x == x && v.y == y;
		}
		return false;
	}
}
