package closestPair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ClosestPair {
	public static float distance(Point a, Point b){	// �� ���� �Ÿ��� ���Ѵ�.
		float dis = 0;
		dis = Float.parseFloat(String.valueOf(Math.sqrt(Math.pow((a.getX() - b.getX()),2) + Math.pow((a.getY() - b.getY()),2))));
		return dis;
	}
	
	
	public static float brute_force(ArrayList<Point> points){ // ���� 3�� ������ ���
		if (points.size() < 2) {	// ����� 2���� ������ ���� ���ų� �ϳ��̹Ƿ� ���Ѵ� ���
			return Float.MAX_VALUE;
		}							// 2���� ū ��� �̹Ƿ� 
		Point p1 = points.get(0);	// ���� ��� ����
		Point p2 = points.get(1);
		float minDistance = distance(p1,p2);	// �ּ� ���� ���س���
		for (int i = 0; i < points.size()-1; i++) {	// ������ ��
			for (int j = i + 1; j < points.size(); j++) {
				p1 = points.get(i);
				p2 = points.get(j);
				if(distance(p1,p2) < minDistance){	// ���� ���� ���
					minDistance = distance(p1,p2);	// ����� ����
				}
			}
		}
		return minDistance;						// �ּҰ� ��ȯ
	}
	
	public static float closest_pair(ArrayList<Point> points){
		int n = points.size();	// point�� ���� ���ϰ�
		if (n <= 3) {	// ������ 3�� ������ ���
			return brute_force(points);	
		}
		int mid = n/2;	// �߰� ����
		int i;
		Point midPoint = points.get(mid); // ���Ҽ�
		ArrayList<Point> left = new ArrayList<Point>();
		ArrayList<Point> right = new ArrayList<Point>();
		
		for (i = 0; i < mid; i++) { // ����
			left.add(points.get(i));
		}
		for (int j = i; j < points.size(); j++) { // ������
			right.add(points.get(j));
		}
		
		float r = 0;					// ���� �ּ� ���� 
		float r1 = closest_pair(left);	// ���� �ּ� ����
		
		float r2 = closest_pair(right);	// ������ �ּ� ����
		
		r = Math.min(r1, r2);			// ���� �ּ� ���� 
		
		ArrayList<Point> remain = new ArrayList<Point>(); // ���Ҽ� ������ ���鸸 ����
		for (int j = 0; j < points.size(); j++) {
			float tmpX = points.get(j).getX();
			if(Math.abs(midPoint.getX() - tmpX) < r ){
				remain.add(points.get(j));
			}
		}
		
		Collections.sort(remain, Point.yComparator);	// y������ sort ��������
		
		for (int j = 0; j < remain.size(); j++) {
			Point pivot = remain.get(j);			// �ϳ��� ��������
			for (int k = 0; k < remain.size(); k++) {
				if(j != k){								// �ڱ��ڽ��� ������ ���� ��
					Point compare = remain.get(k); 
					float distance = distance(pivot, compare);
					if(r > distance){				// ���̰��� �ּ� ���̶��
						r = distance;				// �ּҰ� ����
					}
				}
			}
		}
		
		return r;									// �ּҰ� ��ȯ
	}
	
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		FileReader fr = new FileReader("data07_closest.txt"); // �����͸� �ҷ��´�.
		BufferedReader br = new BufferedReader(fr);

		String line = null;
		
		ArrayList<Point> points = new ArrayList<Point>();
		while ((line = br.readLine()) != null) {     // �ҷ��� �����Ϳ��� ������ �о�´�.
			String[] values = line.split(",");		// ,�� �������� ���� �����ش�.		
			Point p = new Point(Float.parseFloat(values[0]),Float.parseFloat(values[1]));
			points.add(p);
		}
		System.out.println("Points");				// ���Ϸ� ���� ���� Point ���� ���
		System.out.println("===============================");
		for (int i = 0; i < points.size(); i++) {
			System.out.println(points.get(i).getX() + ", " + points.get(i).getY());
		}
		System.out.println("===============================");
		
		Collections.sort(points, Point.xComparator);	// x���� �������� sort
		
		for (int i = 0; i < points.size(); i++) {		// ��� ���
			System.out.println(points.get(i).getX() + ", " + points.get(i).getY());
		}
		System.out.println("===============================");
		
		float minDistance = closest_pair(points);		// closest-pair ����
		
		System.out.println("Min Distance : " + minDistance);	// �ּҰ� ���
		
		br.close();
	}

}
