package clientServer;
import java.net.*;
import java.io.*;
import java.lang.*;

public class server {

	public static void main(String[] args) {
		try {
			ServerSocket sock = new ServerSocket(6013);
			
			/* now listen for connections*/
			while(true) {
				Socket client = sock.accept();
				
				
				InputStreamReader inputStreamReader = new InputStreamReader(client.getInputStream());
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(client.getOutputStream());
				BufferedReader bin = new BufferedReader(inputStreamReader);
				//PrintWriter printOut = new PrintWriter(outputStreamWriter, true);
				BufferedWriter bout = new BufferedWriter(outputStreamWriter);
				
				//printOut.println(bin.readLine());

				while (true) {
					
					String line = bin.readLine();

					bout.write("Server: " + line);
					bout.newLine();
					bout.flush();
					
					if(inputStreamReader.read() == -1) {
						inputStreamReader.close();
						outputStreamWriter.close();
						bin.close();
						bout.close();						
						client.close();
						sock.close();
						break;
					}
				}
			}
		} catch (IOException ioe) {
			System.out.println("Connection Terminated!");
		}
	}

}
