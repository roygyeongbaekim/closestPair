package closestPair;

import java.util.Comparator;

public class Point {
	private float x,y;
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Point(){
		this.x = 0;
		this.y = 0;
	}
	
	public Point(float x, float y){
		this.x = x;
		this.y = y;
	}


	// x값을 기준으로 sort하기 위한 함수
	public static Comparator<Point> xComparator = new Comparator<Point>(){
		public int compare(Point p1, Point p2){
			if (p1.getX() < p2.getX()) {
				return -1;
			}
			if (p1.getX() < p2.getX()) {
				return 1;
			}
			
			return 0;
		}
	};
	
	// y값을 기준으로 sort하기 위한 함수
	public static Comparator<Point> yComparator = new Comparator<Point>(){
		public int compare(Point p1, Point p2){
			if (p1.getY() < p2.getY()) {
				return -1;
			}
			if (p1.getY() < p2.getY()) {
				return 1;
			}
			return 0;
		}
	};
}
