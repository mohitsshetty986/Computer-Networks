import java.util.*;
import java.io.*;
import java.net.*;

public class client {
public static void main(String[] args) {

int n;Scanner s2=new Scanner(System.in);
String s1;
System.out.println("Client on the desk...!");

	           while(true){
	           	try{

                Socket s=new Socket("localhost",6666);
				DataOutputStream dout=new DataOutputStream(s.getOutputStream());
				System.out.print("Client:");
				s1=s2.nextLine();
				if(s1.equals("exit")){s.close(); break;}
				dout.writeUTF(s1);
				 Thread t=new Thread();
				 t.sleep(5000);
				dout.flush();


				//dout.close();



				DataInputStream dis=new DataInputStream(s.getInputStream());


				String	str=(String)dis.readUTF();
				if(str.equals("exit")) {s.close();break;}
				System.out.println("Server:"+str);

				//s.close();
				dout.close();
				s.close();}
				catch(Exception e){System.out.println(e);}
			}

}
}
