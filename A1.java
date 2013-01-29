import java.util.Scanner;



public class A1 {

	/**
	 * This is the primary class containing the main function for Assignment 1
	 */
	/* Jesse Osiecki
	 * January 22, 2013
	 * 
	 * This program is free software; you can redistribute it and/or modify
	 * it under the terms of the GNU General Public License as published by
	 * the Free Software Foundation; either version 2 of the License, or
	 * (at your option) any later version.
	 * 
	 * This program is distributed in the hope that it will be useful,
	 * but WITHOUT ANY WARRANTY; without even the implied warranty of
	 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	 * GNU General Public License for more details.
	 * 
	 * You should have received a copy of the GNU General Public License
	 * along with this program (but you won't unfortunately); if not, write to the Free Software
	 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
	 * MA 02110-1301, USA.
	 */
	
	//global so the checkAP method can be used easily
	private static double sPerim; private static double sArea; private static double bPerim; private static double bArea;
	
	public static void main(String[] args) {
		// The only main function in the program
		//declare some important variables
		sPerim = 0; sArea = 0; bPerim = 0; bArea = 0;
		boolean runn = true;
		Scanner s = new Scanner(System.in);
		boolean firstRun = true;
		
		while(runn){
			//loop that runs until "end" is passed
			String shapeName = s.next().toLowerCase();
			//set shapename equal to the first thing on the cli and check that agaist shape names
			if(shapeName.equals("triangle")){
				int ax = s.nextInt();
				int ay = s.nextInt();
				int bx = s.nextInt();
				int by = s.nextInt();
				int cx = s.nextInt();
				int cy = s.nextInt();
				double area = triangleArea(ax, ay, bx, by, cx, cy);
				double perim = trianglePerim(ax, ay, bx, by, cx, cy);
				
				//check against the lowest and highest
				if(firstRun){
					sArea = area;
					sPerim = perim;
					firstRun = false;
				}
				else
					checkAP(area, perim);
			}
			else if(shapeName.equals("rectangle")){
				int ax = s.nextInt();
				int ay = s.nextInt();
				int bx = s.nextInt();
				int by = s.nextInt();
				double area = rectArea(ax, ay, bx, by);
				double perim = rectPerim(ax, ay, bx, by);
				if(firstRun){
					sArea = area;
					sPerim = perim;
					firstRun = false;
				}
				else
					checkAP(area, perim);
			}
			else if(shapeName.equals("circle")){
				//TODO Why do we need the center coords when they are neither used for area nor perimeter?
				@SuppressWarnings("unused")
				int ax = s.nextInt();
				@SuppressWarnings("unused")
				int ay = s.nextInt();
				double rad = s.nextDouble();
				double area = circArea(rad);
				double perim = circPerim(rad);
				if(firstRun){
					sArea = area;
					sPerim = perim;
					firstRun = false;
				}
				else
					checkAP(area, perim);
			}
			else if(shapeName.equals("end")){
				runn = false;
			}
			else{
				System.out.println("Please enter a compatible shape");
			}
		}
		System.out.println("The smallest perimeter is: " + sPerim);
		System.out.println("The smallest area is: " + sArea);
		System.out.println("The largest perimeter is: " + bPerim);
		System.out.println("The largest area is: " + bArea);
		s.close();
	}

	private static double circPerim(double rad) {
		return Math.PI * 2 * rad;
	}

	private static double circArea(double rad) {
		return Math.PI * rad * rad;
	}

	private static double rectPerim(int ax, int ay, int bx, int by) {
		double s1 = Math.sqrt((ay - by) * (ay - by));
		double s2 = Math.sqrt((ax - bx) * (ax - bx));
		return 2 * (s1 + s2);
	}

	private static double rectArea(int ax, int ay, int bx, int by) {
		//b/c rectangle, use point given to determine other points and multiply sides together
		double s1 = Math.sqrt((ay - by) * (ay - by));
		double s2 = Math.sqrt((ax - bx) * (ax - bx));
		return s1 * s2;
	}

	private static void checkAP(double area, double perim) {
		// checks the Area and Perimeter against all of the highsest and lowest
		if(area > bArea){
			bArea = area;
		}
		else if(area < sArea){
			sArea = area;
		}
		if(perim > bPerim){
			bPerim = perim;
		}
		else if(perim < sPerim){
			sPerim = perim;
		}
		
	}

	private static double triangleArea(int ax, int ay, int bx, int by, int cx,
			int cy) {
		double area = Math.abs(
				(ax * (by-cy) + 
				bx * (cy - ay) +
				cx * (ay - by))
				/ 2);
		return area;
	}

	private static double trianglePerim(int ax, int ay, int bx, int by, int cx,
			int cy) {
		double perim = Math.sqrt((bx-ax) * (bx-ax) + (by-ay) * (by-ay)) + 
				Math.sqrt((cx-bx) * (cx-bx) + (cy-by) * (cy-by)) + 
				Math.sqrt((ax-cx) * (ax-cx) + (ay-cy) * (ay-cy));
		return perim;
	}

}
