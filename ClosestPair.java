package closestPair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ClosestPair {
	public static float distance(Point a, Point b){	// 두 점의 거리를 구한다.
		float dis = 0;
		dis = Float.parseFloat(String.valueOf(Math.sqrt(Math.pow((a.getX() - b.getX()),2) + Math.pow((a.getY() - b.getY()),2))));
		return dis;
	}
	
	
	public static float brute_force(ArrayList<Point> points){ // 점이 3개 이하인 경우
		if (points.size() < 2) {	// 사이즈가 2보다 작으면 점이 없거나 하나이므로 무한대 출력
			return Float.MAX_VALUE;
		}							// 2보다 큰 경우 이므로 
		Point p1 = points.get(0);	// 비교할 대상 선언
		Point p2 = points.get(1);
		float minDistance = distance(p1,p2);	// 최소 길이 구해놓고
		for (int i = 0; i < points.size()-1; i++) {	// 각각을 비교
			for (int j = i + 1; j < points.size(); j++) {
				p1 = points.get(i);
				p2 = points.get(j);
				if(distance(p1,p2) < minDistance){	// 가장 작은 경우
					minDistance = distance(p1,p2);	// 결과값 저장
				}
			}
		}
		return minDistance;						// 최소값 반환
	}
	
	public static float closest_pair(ArrayList<Point> points){
		int n = points.size();	// point의 갯수 구하고
		if (n <= 3) {	// 갯수가 3개 이하인 경우
			return brute_force(points);	
		}
		int mid = n/2;	// 중간 지점
		int i;
		Point midPoint = points.get(mid); // 분할선
		ArrayList<Point> left = new ArrayList<Point>();
		ArrayList<Point> right = new ArrayList<Point>();
		
		for (i = 0; i < mid; i++) { // 왼쪽
			left.add(points.get(i));
		}
		for (int j = i; j < points.size(); j++) { // 오른쪽
			right.add(points.get(j));
		}
		
		float r = 0;					// 최종 최소 길이 
		float r1 = closest_pair(left);	// 왼쪽 최소 길이
		
		float r2 = closest_pair(right);	// 오른쪽 최소 길이
		
		r = Math.min(r1, r2);			// 최종 최소 길이 
		
		ArrayList<Point> remain = new ArrayList<Point>(); // 분할선 사이의 점들만 남김
		for (int j = 0; j < points.size(); j++) {
			float tmpX = points.get(j).getX();
			if(Math.abs(midPoint.getX() - tmpX) < r ){
				remain.add(points.get(j));
			}
		}
		
		Collections.sort(remain, Point.yComparator);	// y값으로 sort 오름차순
		
		for (int j = 0; j < remain.size(); j++) {
			Point pivot = remain.get(j);			// 하나를 기준으로
			for (int k = 0; k < remain.size(); k++) {
				if(j != k){								// 자기자신을 제외한 값과 비교
					Point compare = remain.get(k); 
					float distance = distance(pivot, compare);
					if(r > distance){				// 사이값이 최소 값이라면
						r = distance;				// 최소값 지정
					}
				}
			}
		}
		
		return r;									// 최소값 반환
	}
	
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		FileReader fr = new FileReader("data07_closest.txt"); // 데이터를 불러온다.
		BufferedReader br = new BufferedReader(fr);

		String line = null;
		
		ArrayList<Point> points = new ArrayList<Point>();
		while ((line = br.readLine()) != null) {     // 불러온 데이터에서 한줄을 읽어온다.
			String[] values = line.split(",");		// ,를 기준으로 값을 나눠준다.		
			Point p = new Point(Float.parseFloat(values[0]),Float.parseFloat(values[1]));
			points.add(p);
		}
		System.out.println("Points");				// 파일로 부터 받은 Point 집합 출력
		System.out.println("===============================");
		for (int i = 0; i < points.size(); i++) {
			System.out.println(points.get(i).getX() + ", " + points.get(i).getY());
		}
		System.out.println("===============================");
		
		Collections.sort(points, Point.xComparator);	// x값을 기준으로 sort
		
		for (int i = 0; i < points.size(); i++) {		// 결과 출력
			System.out.println(points.get(i).getX() + ", " + points.get(i).getY());
		}
		System.out.println("===============================");
		
		float minDistance = closest_pair(points);		// closest-pair 실행
		
		System.out.println("Min Distance : " + minDistance);	// 최소값 출력
		
		br.close();
	}

}
