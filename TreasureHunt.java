package zsgsExam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TreasureHunt {
	
	static int min = Integer.MAX_VALUE;
	static List<int[]> path = new ArrayList<>();
	
	public static int findMinPath(int matrix[][],int positionOfAi,int positionOfAj,int goldI,int goldJ ) {
		
		rec(matrix,positionOfAi,positionOfAj,goldI,goldJ,0,new ArrayList<>());
		
//		System.out.println("Minimum number of steps to reach the Gold is : "+min);
		int temp = min;
		min = Integer.MAX_VALUE;
		
		return temp;
		
		
	}
	
	public static void rec(int matrix[][],int positionOfAi,int positionOfAj,int goldI,int goldJ,int temp,List<int[]> tempPath ) {
		if(goldI<0 || goldI>=matrix.length || goldJ<0 || goldJ>=matrix[0].length || matrix[goldI][goldJ] == -1) {
			return;
		}
		
		if((positionOfAi==goldI && positionOfAj==goldJ)) {
			if(temp<min) {
				min = temp;
				tempPath.add(new int[] {goldI,goldJ});
				path = new ArrayList<>(tempPath);
			}
			return;
		}
		
		matrix[goldI][goldJ] = -1;
		
		tempPath.add(new int[] {goldI,goldJ});
		
		rec(matrix,positionOfAi,positionOfAj,goldI,goldJ-1,temp+1,tempPath);
		rec(matrix,positionOfAi,positionOfAj,goldI,goldJ+1,temp+1,tempPath);
		rec(matrix,positionOfAi,positionOfAj,goldI+1,goldJ,temp+1,tempPath);
		rec(matrix,positionOfAi,positionOfAj,goldI-1,goldJ,temp+1,tempPath);
		
		tempPath.remove(tempPath.size()-1);
		
		matrix[goldI][goldJ] = 0;
	
		
	}
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter the Dimensions of the Dungeon (Row x Column) : ");
		
		int m = in.nextInt();
		int n = in.nextInt();
		
		int matrix[][] = new int[m][n];
		
		System.out.println("Enter the position of the Adventurer : ");
		int posI = in.nextInt();
		int posJ = in.nextInt();
		
		System.out.println("Enter the position of the Monster : ");
		int monI = in.nextInt();
		int monJ = in.nextInt();
		
		System.out.println("Enter the position of the Gold : ");
		int goldI = in.nextInt();
		int goldJ = in.nextInt();
		
		
		int minStepForAdventure = findMinPath(matrix, posI-1, posJ-1, goldI-1, goldJ-1);
		List<int[]> pathForAdventure = new ArrayList<>(path);
		int minStepForMonster = findMinPath(matrix, monI-1, monJ-1, goldI-1, goldJ-1);
		
		if(minStepForAdventure>minStepForMonster) {
			System.out.println("No possible solution");
		}
		else {
			System.out.println("Minimum Number of steps : "+minStepForAdventure);
			
			for(int i=pathForAdventure.size()-1;i>=0;i--) {
				System.out.print("("+(pathForAdventure.get(i)[0]+1)+" "+(pathForAdventure.get(i)[1]+1)+")"+"->");
			}
		}
		
	}

}
