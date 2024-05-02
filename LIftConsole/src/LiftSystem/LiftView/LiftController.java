package LiftSystem.LiftView;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import LiftSystem.model.Lift;

public class LiftController {
	List<Lift> liftList = new ArrayList<Lift>();

	public void init() {
		show();
	}

	public void show() {
		for (int i = 0; i < 5; i++) {
			Lift lift;
			if (i < 2) {
				lift = new Lift("L" + (i + 1), 0, 0, 5);
				lift.setCapacity(6);
			} else if (i > 1 && i < 4) {
				lift = new Lift("L" + (i + 1), 0, 6, 10);
				lift.setCapacity(7);
			} else {
				lift = new Lift("L" + (i + 1), 0, 0, 10);
				lift.setCapacity(10);
			}
			liftList.add(lift);
		}
		Scanner scanner = new Scanner(System.in);
		exit: while (true) {
			System.out.print("\nLift  : ");
			for (int i = 0; i < liftList.size(); i++) {
				System.out.print(liftList.get(i).getName() + "  ");
			}
			System.out.println();
			System.out.print("Floor : ");
			for (int i = 0; i < liftList.size(); i++) {
				if (liftList.get(i).isMaintanence()) {
					System.out.print("-1" + "  ");
				} else {
					System.out.print(liftList.get(i).getCurrentPosition() + "   ");
				}
			}
			System.out.println("\n");
			System.out.println("Choices : '1'.Assign Lifts '2'.Set capacity for Lift '3'.Set Maintenance lift '4'.Get capacity of Lift '10'.exit");
			try {
				System.out.print("Enter the choice : ");
				int choice = scanner.nextInt();
				switch (choice) {
				case 1:
					assignLift();
					break;
				case 2:
					setCapacityForLift();
					break;
				case 3:
					setMaintenanceLift();
					break;
				case 4:
					getCapacityOfTheLift();
					break;
				case 10:
					break exit;
				default:
					System.out.println("Enter a valid option!");
				}
			} catch (InputMismatchException e) {
				System.out.println("Enter a number input!");
				scanner.nextLine();
			}
		}
	}

	private void getCapacityOfTheLift() {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("Enter the lift number:");
			int liftNum = scanner.nextInt();
			if(liftNum < 1 || liftNum > 5) {
				System.out.println("Lift number is between 1 to 5");
				return;
			}
			System.out.println("Lift no "+liftNum+ " Capacity is :"+liftList.get(liftNum-1).getCapacity());
		} catch (InputMismatchException e) {
			System.out.println("Enter a the valid input!");
		}
	}

	private void setMaintenanceLift() {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("Enter the lift number:");
			int liftNum = scanner.nextInt();
			if(liftNum < 1 || liftNum > 5) {
				System.out.println("Lift number is between 1 to 5");
				return;
			}
			System.out.println("Enter the true or false:");
			boolean maintanence = scanner.nextBoolean();
			liftList.get(liftNum-1).setMaintanence(maintanence);
			System.out.println("Maintenace updated successfully");
		} catch (InputMismatchException e) {
			System.out.println("Enter a the valid input!");
		}
		
	}

	private void setCapacityForLift() {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("Enter the lift number:");
			int liftNum = scanner.nextInt();
			if(liftNum < 1 || liftNum > 5) {
				System.out.println("Lift number is between 1 to 5");
				return;
			}
			System.out.println("Enter the capacity:");
			int capacity = scanner.nextInt();
			liftList.get(liftNum-1).setCapacity(capacity);
			System.out.println("Capacity updated successfully");
		} catch (InputMismatchException e) {
			System.out.println("Enter a number input!");
		}

	}

	public void assignLift() {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.print("Enter the current floor : ");
			int currentFloor = scanner.nextInt();
			if (currentFloor > 10 || currentFloor < 0) {
				System.out.println("Enter the floors between 1 to 10 only!");
				return;
			}
			System.out.print("Enter the Destination floor : ");
			int destinationFloor = scanner.nextInt();
			if (destinationFloor > 10 || destinationFloor < 0) {
				System.out.println("Enter the floors between 1 to 10 only!");
				return;
			}
			int min = Integer.MAX_VALUE;
			int minIndex = 0;

			if (currentFloor < destinationFloor) {
				for (int i = 0; i < liftList.size(); i++) {
					Lift lift = liftList.get(i);
					if (destinationFloor <= lift.getTo() && (currentFloor >= lift.getFrom() || currentFloor == 0) && !lift.isMaintanence()) {
						int diff = Math.abs(lift.getCurrentPosition() - currentFloor);
						if (diff < min) {
							minIndex = i;
							min = diff;
						}
					}
				}
			} else {
				for (int i = 0; i < liftList.size(); i++) {
					Lift lift = liftList.get(i);
//					if (currentFloor <= lift.getTo() && currentFloor >= lift.getFrom() && (destinationFloor <= lift.getTo() || destinationFloor == 0)) {
					if (!lift.isMaintanence() &&  currentFloor <= lift.getTo() && (destinationFloor >=  lift.getFrom() || destinationFloor == 0)) {
						//if (lift.getCurrentPosition() != 0) {
							int diff = Math.abs(lift.getCurrentPosition() - currentFloor);
//							int steps = currentFloor - lift.getFrom();
//							System.out.println(steps);
							if (diff < min) {
								minIndex = i;
								min = diff;
							}
						//}
					}
				}
			}
			Lift lift = liftList.get(minIndex);
			if (lift.getCapacity() == 0) {
				System.out.println("The lift is overloaded. you cant take lift!");
				return;
			}
//			if(lift.isMaintanence()) {
//				System.out.println("The lift is in maintenance!");
//				return;
//			}
			lift.setCurrentPosition(destinationFloor);
			lift.setCapacity(lift.getCapacity() - 1);
			System.out.println(liftList.get(minIndex).getName() + " is assigned");
		} catch (InputMismatchException e) {
			System.out.println("Enter a number input!");
		}
	}
}
