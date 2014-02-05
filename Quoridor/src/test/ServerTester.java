/*
 * ServerTester.java
 * Authors: David Rice
 * This class will test Server.java functionality so
 * things don't break.
 */

package test;

import org.junit.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class ServerTester extends TestCase{

	public ServerTester (String name) { 
		super(name);
	}

	// This test will see if the message "hi" is being received
	@Test
	public void testGetServerMessage() throws Exception {
		String str = new String("hi");
		assertEquals("Server sent: hi", getServerMessage(str));
	}
	
	public void testGetServerMessage2() throws Exception {
		String str = new String("Charles");
		assertEquals("Server sent: Charles", getServerMessage(str));
	}
	
	private String getServerMessage(String str) {
		return "Server sent: " + str;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestRunner.runAndWait(new TestSuite(ServerTester.class));
	}

}
