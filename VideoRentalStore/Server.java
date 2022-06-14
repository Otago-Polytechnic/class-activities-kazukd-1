package VideoRentalStore;
import VideoRentalStore.Customer;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.*;
import java.util.Arrays;

import com.mysql.jdbc.PreparedStatement;

public class Server{
	 private ServerThread servers[];
	static int serversStatus[];
	static final int PORT = 8888;
	 private int nserver=0;
	private ServerSocket server;
	
	public void execute()
	{
		int ServerThreadMax=2;
		servers = new ServerThread[ServerThreadMax];
		serversStatus = new int[ServerThreadMax];
		
		for(int number: serversStatus) serversStatus[number]=0; // 0: not conneccted 1: connected
		for(int number: serversStatus) System.out.println(serversStatus[number]);
		nserver = 0;
		try{
		while (true)
		{
			if(Arrays.stream(serversStatus).sum() < ServerThreadMax){
				System.out.println("sum"+Arrays.stream(serversStatus).sum() );
				for(int i=0; i < ServerThreadMax;i++) {
					System.out.println(serversStatus[i]);
					if(serversStatus[i]==0) {
						nserver = i;
						System.out.println("kita" + nserver);
						break;
					}
				}
				System.out.println("nserver"+nserver);
				serversStatus[nserver]=1; // 1: Connected
				System.out.println("Server " + nserver + " waiting");
				servers[nserver] = new ServerThread(server.accept(), nserver);
				servers[nserver].start();
			}else{
				// no message automatically connect when server is not busy
				/*
				PrintWriter out = new PrintWriter(server.accept().getOutputStream(), true);
				out.println("Server is busy now. Please wait or try later.");
				*/
			}
		}
		}
		catch (Exception e)
		{
			System.out.println(e+"Error in socket1!");
		}
	}
	public Server(){
		try{
		
		server= new ServerSocket(PORT,5,InetAddress.getLocalHost());
		//System.out.println(InetAddress.getLocalHost());
		//Maximum 10 running servers
		execute();

	}
	 catch(Exception e)
	{
		System.out.println("Error in socket2!");
	}
	}
private class ServerThread extends Thread
	{
	private Socket incoming;
    private int ServerID;
	   public ServerThread( Socket socket, int number )
	   {
			incoming=socket;
		   ServerID=number;
	   }
		public void run()
		{
			
			try{
			DatabaseControl dbControl=new DatabaseControl();
			System.out.println("Client "+ServerID+" Connected!");
			BufferedReader in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
			PrintWriter out = new PrintWriter(incoming.getOutputStream(), true);
			// send data to the client
			PrintStream ps = new PrintStream(incoming.getOutputStream());
			//ps.println("Server ID:" + ServerID + " connected successful!");
			ps.println("1;"+ ServerID);
			boolean done = false;
			while (!done)
			{
				String line = in.readLine();
				String[] tokens = line.split(";");
				if (line == null) done = true;
				else
				{
					System.out.println("Echo client " +ServerID+ ":"+ line);
					
					switch( tokens[0]) {
						case "C_all":
							
							String customers[] = dbControl.getCustomers();
							System.out.println("Request customer all data");
							//System.out.println(customers[0]);
							//ps.println("Server: accept request");
							//ps.println(customers.length);
							for(String s:customers) ps.print(s);
							ps.println("END");
							break;
						
						case "C_update":
							//DatabaseControl dbControl2=new DatabaseControl();
							System.out.println(line);
							
							//customer.setCustomerId(10);
							//customer.setFirstName(tokens[2]);
							//customer.setLastName(tokens[3]);
							
							System.out.println("before update");
							//Customer c = customer;
							
							dbControl.updateCustomer(tokens);
							
							
							break;
							
						case "C_del":
							dbControl.deleteCustomer(tokens);
							break;
							
						case "quit": 
							System.out.println("switch quit");
							break;
					
						default: break;
						
					}
					if (tokens[0].equals("quit"))
					{
						done = true;
						serversStatus[ServerID]=0; //0: not connected
						Thread.sleep(200); // if delete , error occur
						System.out.println("Client "+ ServerID + " BYE");
					}

				}
			}
			incoming.close();
			System.out.println("BYE");
			}
			catch (Exception e)
			{
				serversStatus[ServerID]=0; //0: not connected
				try{
					incoming.close();
				}catch (Exception e2){
					System.out.print(e2);
				}
				System.out.println("Error in socket3!");
			}
		}
 
	}
	public static void main(String args[])
	{
		Server application = new Server();
		//application.execute();
		
	}
}
