import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

public class ControllerTest {
 
	@Before
	public void init() {
	
	}
	
	@Test
	public void test_ctrl_searchPatron_yes() {
		String msg = Controller.searchPatron("P1");
		assertThat(msg,CoreMatchers.containsString("Patron : ID -> P1, Name -> ERIC"));
	}
	
	@Test
	public void test_ctrl_startCheckOut_ok() {
		Controller.searchPatron("P3");
		String msg = Controller.startCheckOut("C2");
		assertThat(msg,CoreMatchers.containsString("Checked copy ID -> C2"));
	}
	
	@Test
	public void test_ctrl_startCheckOut_alreadyOut() {
		Controller.searchPatron("P1");
		Controller.startCheckOut("C5");
		Controller.searchPatron("P2");
		String msg = Controller.startCheckOut("C5");
		assertThat(msg,CoreMatchers.containsString("Failed, copy ID [C5] already checked out to patron"));
	}
	
	@Test
	public void test_ctrl_startCheckOut_notFound() {
		String copyID = "C12";
		String msg = Controller.startCheckOut(copyID);	
		assertEquals("> Copy ID [C12] not found", msg);
	}
	
	@Test
	public void test_ctrl_startCheckOut_dueDate() {
		Controller.searchPatron("P2");
		String msg = Controller.startCheckOut("C9");
		System.out.print(msg);
		assertThat(msg,CoreMatchers.containsString("Checked copy ID -> C9, due date -> "));
	}
	
	@Test
	public void test_ctrl_startCheckIn_CopyNotFound() {
		String msg = Controller.startCheckIn("C12");	
		assertEquals("> Copy ID [C12] not found", msg);
	}
	
	@Test
	public void test_ctrl_startCheckIn_CopyFound() {
		Controller.searchPatron("P1");
		Controller.startCheckOut("C1");
		String msg = Controller.startCheckIn("C1");	
		assertThat(msg,CoreMatchers.containsString("Checked in copy ID -> C1"));
	}
	
	@Test
	public void test_ctrl_startCheckIn_CopyFound_empty() {
		Controller.searchPatron("P1");	
		Controller.startCheckIn("C3");
		String msg = Controller.startCheckIn("C5");
		assertThat(msg,CoreMatchers.containsString("patron ID -> P1, Current Checkouts -> empty"));
	}
	
	@Test
	public void test_ctrl_startCheckIn_patronNotFound() {
		Controller.searchPatron("P1");
		Controller.startCheckOut("C3");
		String msg = Controller.startCheckIn("C1");	
		assertEquals("> Check-in process failed for Copy ID [C1]", msg);
	}
	
	@Test
	public void test_ctrl_getCurrentCopy() {
		Controller.searchPatron("P1");
		Controller.startCheckOut("C3");
		Copy copy = Controller.getCurrentCopy();
		assertEquals("C3", copy.getCopyID());
	}
	
	@Test
	public void test_ctrl_getCurrentPatron() {
		Controller.searchPatron("P2");
		Patron patron = Controller.getCurrentPatron();
		assertEquals("P2",patron.getPatronID());
	}
	
	@Test
	public void test_ctrl_getAllEvent() {
		Controller.searchPatron("P1");
		String msg = Controller.getAllEvent();
		assertThat(msg,CoreMatchers.containsString("Event ID :E6 - entity :patron - descr : get patron P1"));
	}
	
	@Test
	public void test_ctrl_searchPatron_found() {
		String msg = Controller.searchPatron("P3");
		assertThat(msg,CoreMatchers.containsString("Patron : ID -> P3, Name -> YU"));
	}
	
	@Test
	public void test_ctrl_searchPatron_notFound() {
		String msg = Controller.searchPatron("P4");
		assertEquals("> Patron ID [P4] not found", msg);
	}
	
	@Test
	public void test_ctrl_searchCopy_found() {
		String msg = Controller.searchCopy("C1");
		assertThat(msg,CoreMatchers.containsString("Copy ID -> C1"));
	}
	
	@Test
	public void test_ctrl_searchCopy_notFound() {
		String msg = Controller.searchCopy("P4");
		assertEquals("> Copy ID [P4] not found", msg);
	}

}
