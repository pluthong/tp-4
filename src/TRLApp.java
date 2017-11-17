import java.util.Map;
public class TRLApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// ***************************************************************************
		// ** Library check out scenario 1: There is a hold on Patron record        **
		// ***************************************************************************
		System.out.println("#################################################################");
		System.out.println("########## scenario 1: hold on patron record          ###########");
		System.out.println("#################################################################");
		System.out.println(System.lineSeparator());
		// get Patron p1 from fake DB
		Patron p1 = FakeDB.getPatron("P1");
		FakeDB.registerEvent("E01","FakeDB","Retrieve patron p1");

		// check if patron has hold
		if(p1.getHolds().size() != 0) {
			System.out.println(p1.toString());
			FakeDB.registerEvent("E02","Patron","check if patron has hold");
		}

		// ***************************************************************************
		// ** Library check out scenario 2: Patron  gets the copy c1 from Library   **
		// ***************************************************************************

		System.out.println("#################################################################");
		System.out.println("########## scenario 2: checking copy c1 out to Patron ###########");
		System.out.println("#################################################################");
		System.out.println(System.lineSeparator());
		// get Copy c1 from fake DB.
		Copy c1 = FakeDB.getCopy("C1");
		FakeDB.registerEvent("E03","FakeDB","get Copy c1 from fake DB.");

		// print the state of copy c1
		System.out.println("********** print the state of Copy c1 before checking it out to patron P2 **********");
		System.out.println(c1.toString());

		// get Patron p2 from fake DB
		Patron p2 = FakeDB.getPatron("P2");
		FakeDB.registerEvent("E04","FakeDB","get Patron p2 from fake DB");
		
		// print the state of patron p2
		System.out.println(System.lineSeparator());
		System.out.println("********** print the state of patron P2 before checking c1 out to Patron P2 **********");
		System.out.println(p2.toString());
		FakeDB.registerEvent("E05","Patron","print the state of patron p2");
		
		// Copy c1 out to Patron p2
		c1.setOutTo(p2);
		FakeDB.registerEvent("E06","Copy","Copy c1 out to Patron p2");
		
		// print the state of Copy c1
		System.out.println(System.lineSeparator());
		System.out.println("********** print the state of copy c1 after checking it out to patron P2 **********");
		System.out.println(c1.toString());
		FakeDB.registerEvent("E07","Copy","print the state of Copy c1");

		// checking copy c1 out to patron P2
		boolean checkedOutCopy = p2.checkCopyOut(c1);
		FakeDB.registerEvent("E08","Patron","checking copy c1 out to patron P2");

		if(checkedOutCopy) {
			System.out.println(System.lineSeparator());
			System.out.println("********** print the state of patron P2 after checking copy c1 out to him **********");
			System.out.println(p2.toString());
			FakeDB.registerEvent("E09","Patron","print the state of patron P2 after checking copy c1 out to him");
		}
		else
			System.out.println("Insertion copy " + c1.getCopyID() + " failed"); // failed


		// ***************************************************************************		
		// ** Scenario 3: checking copy c2 out to patron P2 **************************
		// ***************************************************************************
		System.out.println(System.lineSeparator());
		System.out.println("#################################################################");
		System.out.println("########## scenario 3: checking copy c2 out to Patron ###########");
		System.out.println("#################################################################");

		// get copy c2 from fake DB.
		Copy c2 = FakeDB.getCopy("C2");
		FakeDB.registerEvent("E10","Copy","get copy c2 from fake DB.");
		
		// print the state of Copy c2
		System.out.println(System.lineSeparator());
		System.out.println("********** print the state of Copy c2 before checking it out to patron P2 **********");
		System.out.println(c2.toString());
		FakeDB.registerEvent("E11","Copy","print the state of Copy c2");

		// Copy c2 out to Patron p2
		c2.setOutTo(p2);

		// print the state of copy c2
		System.out.println(System.lineSeparator());
		System.out.println("********** print the state of Copy c2 after checking it out to patron P2 **********");
		System.out.println(c2.toString());
		FakeDB.registerEvent("E12","Copy","print the state of Copy c2 after checking it out to patron P2");

		// patron p2 get the copy c2
		checkedOutCopy = p2.checkCopyOut(c2);
		FakeDB.registerEvent("E13","Patron","patron p2 get the copy c2");

		if(checkedOutCopy) {
			System.out.println(System.lineSeparator());
			System.out.println("********** print the state of Patron p2 after the checked out Copy c2 **********");
			System.out.println(p2.toString());
			FakeDB.registerEvent("E14","Patron","print the state of Patron p2 after the checked out Copy c2");
		}
		else
			System.out.println("Insertion copy " + c2.getCopyID() + " failed"); // failed
		
		// ***************************************************************************		
		// ** Scenario 4: print all events                  **************************
		// ***************************************************************************
		System.out.println(System.lineSeparator());
		System.out.println("#################################################################");
		System.out.println("########## Scenario 4: print all events               ###########");
		System.out.println("#################################################################");
		
		Map<String, Event> map = FakeDB.getStoreEvent();
	    
	    for (Map.Entry<String, Event> entry : map.entrySet()) {
	        String key = entry.getKey();
	        Event event = entry.getValue();
	        System.out.println("Event ID :" + key + " - entity :" + event.getEntity() + " - descr : " + event.getDescr());
	        // ...
	    }

	}

}
