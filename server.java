import java.io.*;
import java.util.*;
import java.net.*;

public class server {
public static void main(String[] args){
	int n;String s1;
	Scanner s2=new Scanner(System.in);
	System.out.println("Server is ready to go!");

		while(true){

			try{
		ServerSocket ss=new ServerSocket(6666);
		Socket s=ss.accept();
		DataInputStream dis=new DataInputStream(s.getInputStream()); //syntax for recieving input from client


		String	str=(String)dis.readUTF();
		System.out.println("Client: "+str);


		//TimerTask tasknew = new TimerTask();
		   Thread t=new Thread();

		   // scheduling the task at interval
		   t.sleep(1000);

		DataOutputStream dout=new DataOutputStream(s.getOutputStream());
		System.out.print("Server:");
		s1=s2.nextLine();
		if(s1.equals("exit")) break;
		dout.writeUTF(s1);
		dout.flush();

		dout.close();

	    ss.close();}
	    catch(Exception e){System.out.println(e);}}
	}

}

