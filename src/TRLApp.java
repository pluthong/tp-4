import java.util.Scanner;

public class TRLApp {

	static int choice;
	static String patronID;
	static String copyID;
	static Scanner userInput = new Scanner(System.in);
	static Scanner choiceInput = new Scanner(System.in);

	public static void main(String[] args) {

		// start TRL application
		startTRL();

	}

	public static void startTRL() {

		do {
			// Display menu graphics
			System.out.println("=================================");
			System.out.println("|   TRL Main Menu               |");
			System.out.println("=================================");
			System.out.println("| Options:                      |");
			System.out.println("|        [1] Check out          |");
			System.out.println("|        [2] Check in           |");
			System.out.println("|        [3] Exit               |");
			System.out.println("=================================");
			System.out.print("> Enter your option here: ");

			try {

				choice = choiceInput.nextInt();// User inputs a choice (integer).
				choiceInput.nextLine();
				switch (choice) {
				case 1:
					displayCheckOutMenu(); // display menu checkout
					break;
				case 2:
					displayCheckInMenu();
					break;
				case 3:
					System.out.println("> Exit selected");
					break;
				default:
					System.out.println("> Invalid selection");
					break;
				}
			} catch (Exception ex) {
				System.out.println("> Invalid selection");
				choiceInput.next(); // clear invalid input from scanner
			}
		} while (choice != 3); // Exit if choice is 3

		System.out.println("> You have exited the Texbook Rental Library! ");
	}

	public static void displayCheckOutMenu() {

		do {
			// Display menu graphics
			System.out.println("=================================");
			System.out.println("|   Checkout Menu               |");
			System.out.println("=================================");
			System.out.println("| Options:                      |");
			System.out.println("|        [1] Enter patron ID    |");
			System.out.println("|        [2] Back to main menu  |");
			System.out.println("=================================");
			System.out.print("> Enter your option here: ");

			try {
				choice = choiceInput.nextInt();// User inputs a choice (integer).
				switch (choice) {
				case 1:
					checkPatron();
					break;
				case 2:
					break;
				default:
					System.out.println("> Invalid selection");
					break;
				}
			} catch (Exception ex) {
				System.out.println("> Invalid selection");
				choiceInput.next();
			}
		} while (choice != 2); // exit for choice = 2
	}

	public static void checkPatron() {
		System.out.print("> Enter patron ID: ");
		patronID = choiceInput.next();// Worker inputs a patronID (string).
		System.out.println(Controller.verifyPatron(patronID));
		if (Controller.getPatron() != null) {
			checkOutCopy();
		}
	}

	public static void checkOutCopy() {
		do {
			System.out.print("> Check out copy ID [q -> to Quit] : ");
			try {
				copyID = choiceInput.next(); // Worker inputs a bookID (string)
				if (!copyID.equals("q")) // while worker no entering q
					System.out.println(Controller.startCheckOut(copyID));
				else
					choice = 2;
			} catch (Exception ex) {
				System.out.println("> Invalid selection");
			}
		} while (!copyID.equals("q"));
	}

	public static void displayCheckInMenu() {

		do {
			// Display menu graphics
			System.out.println("=================================");
			System.out.println("|   Checkin Menu                |");
			System.out.println("=================================");
			System.out.println("| Options:                      |");
			System.out.println("|        [1] Enter copy ID      |");
			System.out.println("|        [2] Back to main menu  |");
			System.out.println("=================================");
			System.out.print("> Enter your option here: ");

			try {
				choice = choiceInput.nextInt();// User inputs a choice (integer).
				switch (choice) {
				case 1:
					checkInCopy();
					break;
				case 2:
					break;
				default:
					System.out.println("> Invalid selection");
					break;
				}
			} catch (Exception ex) {
				System.out.println("> Invalid selection");
				choiceInput.next();
			}

		} while (choice != 2); // exit for choice = 2
	}

	public static void checkInCopy() {
		do {
			System.out.print("> Check in copy ID [q -> to Quit] : ");
			try {
				copyID = choiceInput.next(); // Worker inputs a copyID (string)
				if (!copyID.equals("q")) // while worker no entering q
					System.out.println(Controller.startCheckIn(copyID));
				else
					choice = 2;
			} catch (Exception ex) {
				System.out.println("> Invalid selection");
			}
		} while (!copyID.equals("q"));
	}

}
