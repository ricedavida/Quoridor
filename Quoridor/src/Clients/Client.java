package Clients;
/*
 * Client.java - program001
 * Authors: David Rice - based on code provided by Dr. Ladd
 */ 

import java.net.Socket;
import java.net.UnknownHostException;

import java.io.IOException;
import java.io.PrintStream;

import java.util.Scanner;

/** This is a simple client that prompts a user for a string, sends that string to a server, and listens for a response.  If the string is 'quit', it will cleanly exit. */
public class Client {
    /** Default port number; used if none is provided. */
    public final static int DEFAULT_PORT_NUMBER = 3939;

    /** Default machine name is the local machine; used if none provided. */
    public final static String DEFAULT_MACHINE_NAME = "localhost";

    /** Command-line switches */
    public final static String ARG_PORT = "--port";
    public final static String ARG_MACHINE = "--machine";
    
    /** Message op-codes */
    public final static String MSG_HELLO = "Hello";
    public final static String MSG_GOODBYE = "Goodbye";

    /** Commands */
    public final static String COMMAND_PREFIX = "\\";
    public final static String COMMAND_BYE = "\\bye";

    public final static String PROMPT = "Message> ";

    /** Name of the machine where the server is running. */
    private String machineName;

    /** Port number of distant machine */
    private int portNumber;
  
    /** Scanner attached to keyboard for reading user input */
    private Scanner keyboard;
    
    /** Quit message for the reverse string program */
    public final String QUITMESSAGE = "quit";
   
    // Creating an instance of the Client class, with a scanner
    public Client(String machineName, int portNumber) {
        this.machineName = machineName;
        this.portNumber = portNumber;
        
    }

    public void run() {
    	try {
    	      Socket socket = new Socket(machineName, portNumber);
    	      PrintStream sout = new PrintStream(socket.getOutputStream());
    	      Scanner sin = new Scanner(socket.getInputStream());

    	      System.out.format("Sending \"%s\"", MSG_HELLO);
    	      sout.println(MSG_HELLO);

    	      System.out.println("Receiving from server");

    	      String serverResponse = sin.nextLine();
    	      System.out.format("Client saw \"%s\"\n", serverResponse);

    	      System.out.print(PROMPT);

    	      while (keyboard.hasNextLine()) {
    	        String msg = keyboard.nextLine();

    	        if (msg.equals(COMMAND_BYE))
    	          break;

    	        sout.println(msg);
    	        serverResponse = sin.nextLine();
    	        System.out.format("Client saw \"%s\"\n", serverResponse);
    	        System.out.print(PROMPT);
    	      }

    	      System.out.format("Sending \"%s\"", MSG_GOODBYE);
    	      sout.println(MSG_GOODBYE);

    	      sout.close();
    	      sin.close();
        } catch (UnknownHostException uhe) {
            // the host name provided could not be resolved
            uhe.printStackTrace();
            System.exit(1);
        } catch (IOException ioe) {
            // there was a standard input/output error (lower-level)
            ioe.printStackTrace();
            System.exit(1);
        }
    }

    // Standard usage message
    private static void usage() {
        System.err.format("usage: java Client [options]\n" +
                "       where options:\n" + "       %s port\n" +
                "       %s machineName\n", ARG_PORT, ARG_MACHINE);
    }

    public static void main(String[] args) {
        int port = DEFAULT_PORT_NUMBER;
        String machine = DEFAULT_MACHINE_NAME;
        
        int argNdx = 0;
        
        while (argNdx < args.length) {
            String curr = args[argNdx];

            if (curr.equals(ARG_PORT)) {
              ++argNdx;

              String numberStr = args[argNdx];
              port = Integer.parseInt(numberStr);
            } else if (curr.equals(ARG_MACHINE)) {
              ++argNdx;
              machine = args[argNdx];
            } else {

              // if there is an unknown parameter, give usage and quit
              System.err.println("Unknown parameter \"" + curr + "\"");
              usage();
              System.exit(1);
            }

            ++argNdx;
          }
        
        Client myClient = new Client(machine, port);
        myClient.run();
    }
}
