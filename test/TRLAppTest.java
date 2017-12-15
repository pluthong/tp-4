import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.portable.InputStream;

public class TRLAppTest {


	
	@Before
	public void init() {
	
	}
	
	@Test
	public void test() {
		String[] args = {"1"};	
		String input = "1";
		java.io.InputStream stdin = System.in;
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		//TRLApp.main(args);
		// RESTABLISH System.in
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		System.setIn(stdin);
	
		
	}

}
