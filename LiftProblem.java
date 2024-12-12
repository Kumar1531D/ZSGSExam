package liftproblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class LiftMain {

	public static void main(String[] args) {
		
//		List<Lift> availableLifts = new ArrayList<>(Arrays.asList(new Lift(1,new HashSet<>(Arrays.asList(0,1,2,3,4,5))),
//																new Lift(3,new HashSet<>(Arrays.asList(0,1,2,3,4,5))),
//																new Lift(6,new HashSet<>(Arrays.asList(0,6,7,8,9,10))),
//																new Lift(9,new HashSet<>(Arrays.asList(0,6,7,8,9,10))),
//																new Lift(10,new HashSet<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10)))));
		
		List<Lift> availableLifts = new ArrayList<>(Arrays.asList(new Lift(),new Lift(),new Lift(),new Lift(),new Lift()));
		
		Scanner in = new Scanner(System.in);
		
		while(true) {
			System.out.println("1.Display Lift Positions");
			System.out.println("2.AssignLift");
			
			int choice = in.nextInt();
			
			switch(choice) {
			case 1:
				for(Lift lift : availableLifts) {
					System.out.print(lift.name+" ");
				}
				System.out.println();
				for(Lift lift : availableLifts) {
					System.out.print(lift.currentPos+"  ");
				}
				System.out.println();
				
				break;
				
			case 2:
				int from = 0;
				while(true) {
					System.out.println("Enter the floor you are now...");
					from = in.nextInt();
					
					if(from>=0 && from<=10)
						break;
					
					System.out.println("Invalid floor number!");
				}
				
				int to = 0;
				
				while(true) {
					System.out.println("Enter the floor you are going to...");
					to = in.nextInt();
					
					if(to>=0 && to<=10)
						break;
					System.out.println("Invalid floor number!");
				}
				
				Lift assignedLift = findNearestLift(from,to,availableLifts);
				
				assignedLift.currentPos = to;
				
				System.out.println(assignedLift.name+" is assigned");
				
				break;
				
			default:
				System.out.println("Invalid Choice");
			}
			
		}

	}

	private static Lift findNearestLift(int from, int to, List<Lift> availableLifts) {
		
		List<Lift> currentLift = new ArrayList<>(availableLifts);
		
		Collections.sort(currentLift,(a,b)->Math.abs(a.currentPos-from)-Math.abs(b.currentPos-from));  //To find the nearest lift
		
		int currentMinDistance = Math.abs(currentLift.get(0).currentPos-from);
		
		List<Lift> liftCanGo = new ArrayList<>();
		
		for(Lift l : currentLift) {
			if(Math.abs(l.currentPos-from)==currentMinDistance && l.floorsCanGo.contains(to)) {
				liftCanGo.add(l);
			}
		}
		
		if(liftCanGo.size()==0) {
			return null;
		}
		
		if(liftCanGo.size()==1) {
			return liftCanGo.get(0);
		}
		
		String upOrDown = from<to ? "UP" : "DOWN";
		
		if(upOrDown.equals("DOWN")) {
			for(int i=0;i<liftCanGo.size();i++) {
				if(liftCanGo.get(i).currentPos>=from) {
					return liftCanGo.get(i);
				}
			}
		}
		else {
			for(int i=0;i<liftCanGo.size();i++) {
				if(liftCanGo.get(i).currentPos<=from) {
					return liftCanGo.get(i);
				}
			}
		}
		
		return null;
		
	}
	
	private static Lift findLeastStops(int from, int to, List<Lift> availableLifts) {
		
		List<Lift> canGo1 = new ArrayList<>();
		
		for(int i=0;i<availableLifts.size();i++) {
			if(availableLifts.get(i).floorsCanGo.contains(from)) {
				canGo1.add(availableLifts.get(i));
			}
		}
		
		Lift leastCanGo = findNearestLift(from, to, canGo1);
		
		if(leastCanGo!=null)
			return leastCanGo;
		
		

		
		return leastCanGo;
	}

}
