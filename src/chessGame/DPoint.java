package chessGame;

import java.io.Serializable;

class DPoint implements Serializable {
	double x, y;

	DPoint() {
		this.x = -1;
		this.y = -1;
	}

	DPoint(double x, double y) {
		this.x = x;
		this.y = y;

	}

	DPoint deltaPoint(DPoint secondPoint) {
		double dx = secondPoint.x - this.x;
		double dy = secondPoint.y - this.y;
		return new DPoint(dx, dy);
	}

	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
}
