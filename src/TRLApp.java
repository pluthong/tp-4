
public class TRLApp {

	public static void main(String[] args) {
	
		Controller ctrl = new Controller();
		
		// ***************************************************************************
		// ** Library check out scenario 1: There is a hold on Patron record        **
		// ***************************************************************************
		System.out.println("#################################################################");
		System.out.println("########## scenario 1: hold on patron record          ###########");
		System.out.println("#################################################################");
		System.out.println(System.lineSeparator());
		System.out.println("********** print the state of Patron **********");
		
		// get patron from fake DB
		System.out.println(ctrl.getPatron("P1"));
		
		// ***************************************************************************
		// ** Library check out scenario 2: Patron  gets the copy c1 from Library   **
		// ***************************************************************************
		System.out.println("#################################################################");
		System.out.println("########## scenario 2: checking copy c1 out to Patron ###########");
		System.out.println("#################################################################");
		System.out.println(System.lineSeparator());
		
		// get patron from fake DB
		System.out.println("********** print the state of Patron **********");
		System.out.println(ctrl.getPatron("P2"));
		
		// get copy from fake DB
		System.out.println(System.lineSeparator());
		System.out.println("********** print the state of Copy c1 before checking it out to patron P2 **********");
		System.out.println(ctrl.getCopy("C1"));
		
		// check out copy out to patron
		System.out.println(System.lineSeparator());
		System.out.println("********** print the state of Patron **********");
		System.out.println(ctrl.checkOutCopy("C1"));

		// ***************************************************************************		
		// ** Scenario 3: checking copy c2 out to patron P2 **************************
		// ***************************************************************************
		System.out.println(System.lineSeparator());
		System.out.println("#################################################################");
		System.out.println("########## scenario 3: checking copy c2 out to Patron ###########");
		System.out.println("#################################################################");
		System.out.println(System.lineSeparator());
		// get copy from fake DB
		System.out.println("********** print the state of Copy c2 before checking it out to patron  **********");
		System.out.println(ctrl.getCopy("C2"));

		// check out copy out to patron
		System.out.println(System.lineSeparator());
		System.out.println("********** print the state of Patron **********");
		System.out.println(ctrl.checkOutCopy("C2"));

		// ***************************************************************************		
		// ** Scenario 4: print all events                  **************************
		// ***************************************************************************
		System.out.println(System.lineSeparator());
		System.out.println("#################################################################");
		System.out.println("########## Scenario 4: print all events               ###########");
		System.out.println("#################################################################");
		
		System.out.println(ctrl.getAllEvent());

	}

}
