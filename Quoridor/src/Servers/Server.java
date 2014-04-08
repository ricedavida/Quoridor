/*
 * Server.java
 * Authors: David Rice - based on code provided by Dr. Ladd
 */ 
package Servers;
import java.io.IOException;
import java.io.PrintStream;

import java.net.ServerSocket;
import java.net.Socket;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** This is a simple server that will take a string from a client, and pass it to all other clients */
public class Server {
    /** Default port number; used if none is provided. */
    public final static int DEFAULT_PORT_NUMBER = 3939;

    /** Default machine name is the local machine; used if none provided. */
    public final static String DEFAULT_MACHINE_NAME = "localhost";

    /** Command-line switches */
    public final static String ARG_PORT = "--port";
    public final static String ARG_MACHINE = "--machine";

    /** Port number of distant machine */
    private int portNumber;
    
    protected Thread runningThread = null;
    protected ExecutorService threadPool =
        Executors.newFixedThreadPool(10);
	
    // creating an instance of Server
    public Server(int portNumber) {
        this.portNumber = portNumber;
    }

    public void run() {
    	synchronized(this){
            this.runningThread = Thread.currentThread();
        }
        try {
            ServerSocket server = new ServerSocket(portNumber);
            System.out.format("Server now accepting connections on port %d\n",
                    portNumber);

            Socket client;

            while ((client = server.accept()) != null) {
                System.out.format("Connection from %s\n", client);

                Scanner cin = new Scanner(client.getInputStream());
                PrintStream cout = new PrintStream(client.getOutputStream());

                String clientMessage = "";
                
                // this is where the server will take in a client String,
                // reverse it and send it back
                while (cin.hasNextLine()) {
                    clientMessage = cin.nextLine();
                    cout.println("Server sent: " + clientMessage);
                }
                
                this.threadPool.shutdown();
                cout.close();
                cin.close();
                }
            } catch (IOException ioe) {

                // there was a standard input/output error (lower-level from uhe)
                ioe.printStackTrace();
                System.exit(1);
            }
        }
   
        // This method will take in a string and return that string in reverse
        private String reverseString(String str) {
            String returnStr = "";
            for (int i = str.length()-1 ; i >= 0 ; i--) {
                returnStr += str.charAt(i);
            }

            return returnStr;
        }
       
        // usage message
        private static void usage() {
            System.err.print("usage: java Client [options]\n" +
                    "       where options:\n" + "       --port port\n");
        }

        public static void main(String[] args) {
            int port = DEFAULT_PORT_NUMBER;

            /* Parsing parameters. argNdx will move forward across the
             * indices; remember for arguments that have their own parameters, you
             * must advance past the value for the argument too.
             */
            int argNdx = 0;

            while (argNdx < args.length) {
                String curr = args[argNdx];

                if (curr.equals(ARG_PORT)) {
                    ++argNdx;

                    String numberStr = args[argNdx];
                    port = Integer.parseInt(numberStr);
                } else {

                    // if there is an unknown parameter, give usage and quit
                    System.err.println("Unknown parameter \"" + curr + "\"");
                    usage();
                    System.exit(1);
                }

                ++argNdx;
            }

            Server myServer = new Server(port);
            myServer.run();
        }
    }
