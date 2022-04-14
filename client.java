package clientServer;
import java.net.*;
import java.io.*;
import java.lang.*;
import java.util.*;

public class client {

	public static void main(String[] args) {
		try {
			/*make connection to server socket*/
			Socket sock = new Socket("127.0.0.1", 6013);
			Scanner scan = new Scanner(System.in);
			
			InputStreamReader inputStreamReader = new InputStreamReader(sock.getInputStream());
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(sock.getOutputStream());
			BufferedReader bin = new BufferedReader(inputStreamReader);
			//PrintWriter printOut = new PrintWriter(outputStreamWriter, true);
			BufferedWriter bout = new BufferedWriter(outputStreamWriter);
			
			System.out.println("Type something:");
			
			int x = 0;	
			
			while(true){
				String msgToSend;			
				if(x == 0) {
					msgToSend = scan.nextLine();
					x++;
					//printOut.println(msgToSend);
				} else msgToSend = " " +  scan.nextLine();
				
				bout.write(msgToSend);
				bout.newLine();
				bout.flush();
				
				System.out.println(bin.readLine());

				if (msgToSend.equals(" close") || msgToSend.equals("close")){
					System.out.println("Socket Closed!");
					break;
				}
				System.out.println("Type something:");
			}			
			/* close the socket connection */
			sock.close();
			scan.close();
		} catch (IOException e) {
			System.err.println(e);
		}

	}

}