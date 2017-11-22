
import java.util.ArrayList;

public class Patron
{
	private String name; // name of the patron
	private String patronID; // It's sound like Library card ID to identify patron within the library
	private ArrayList<Copy> copiesOut; // patron withdrawal copies from Library
	private ArrayList<Hold> holds;

	// get Name
	public String getName() {
		return name;
	}

	// set Name
	public void setName(String name) {
		this.name = name;
	}

	// get Patron
	public String getPatronID() {
		return patronID;
	}

	// set Patron
	public void setPatronID(String patronID) {
		this.patronID = patronID;
	}

	// get CopiesOut
	public ArrayList<Copy> getCopiesOut() {
		return copiesOut;
	}
	
	// get holds
	public ArrayList<Hold> getHolds() {
		return this.holds;
	}
	
	public Patron()
	{
	}
	
	// constructor initializes patron given patronID and name
	public Patron(String id, String name)
	{
		this.name = name;
		this.patronID = id;
		this.copiesOut = new ArrayList<>();
		this.holds = new ArrayList<>();
	}
   
	// method returns true if checked copy out to patron
	// otherwise false 
	public boolean checkCopyOut(Copy c)
	{
		// if copy c is already out to patron
		if(this.copiesOut.contains(c))
			return false;
		// otherwise add copy c to patron's copiesOut
		this.copiesOut.add(c);
		return true;
	}
	
	// method returns true if added hold
	public void addHold(Hold h)
	{
		// add hold to patron's holds
		this.holds.add(h);
	}
	
	// method returns true if checked copy in from patron
	// otherwise false 
	public boolean checkCopyIn(Copy c)
	{
		// if copy c isn't out to patron
		if(!(this.copiesOut.contains(c)))
			return false;
		// otherwise remove copy c from patron's copiesOut
		this.copiesOut.remove(c);
		return true;
	}
	
	// this method compares if the given Object is equal to patron
	@Override
	public boolean equals(Object o)
	{
		// finish this: two are equals iff same patron ID

		if(!(o instanceof Patron)) 
			return false;
		Patron p = (Patron)o;
		// compare p.patronID with this.patronID
		return p.patronID.equals(this.patronID);
	}

	// returns a string representation of the object Patron
	public String toString()
	{
		String info = "";
		//info += System.lineSeparator();
		info += "Patron ID : " + this.patronID;
		info += System.lineSeparator();
		info += "Patron name : " + this.name;
		if(!copiesOut.isEmpty()) {
			info += System.lineSeparator();
			info += "#copy : " + copiesOut.size();
			info += System.lineSeparator();
			info += "Copies out: [";
			for (Copy copy : copiesOut) 
				info += copy.getCopyID() + ", ";
			
			info = trimEndChar(info.trim());
			info += "]";
		}
		
		if(!holds.isEmpty()) {
			info += System.lineSeparator();
			info += "#hold : " + holds.size();
			info += System.lineSeparator();
			
			for (Hold hold : holds) {
				info += "---- Hold ID: ";
				info += hold.getHoldID();
				info += System.lineSeparator();
				info += "------ description: ";
				info += hold.getDescr();
				info += System.lineSeparator();
				info += "------ placed on: ";
				info += AppUtil.convertDateToString(hold.getPlacedOn(), "MM/dd/yyyy");
				info += System.lineSeparator();
			}
			
		}
		
		return info;
	}
	
	// Return the passed string without the removed last character
	public String trimEndChar(String str) {
		return (str.length() == 0 || str == null ? "" : str.substring(0, str.length() - 1));
	}

}
