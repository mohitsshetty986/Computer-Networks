import java.io.*;
import java.util.*;

class calc
{
	int c=0;
	String tobin(String ip)
	{
		int len,l;
		String split_ip[] = ip.split("\\."); 
		String split_bip[] = new String[4];
		String bip = "";
		for(int i=0;i<4;i++)
		{
			l=0;
 		split_bip[i] = Integer.toBinaryString(Integer.parseInt(split_ip[i]));
		len=split_bip[i].length();
		if(len!=8)
		{
			l=8-len;
			while(l!=0)
			{
			split_bip[i]="0"+split_bip[i];
			l--;
			}		
		}
		bip += split_bip[i];
		}
		
 	//	System.out.println(" binary representation is "+bip);
 		return bip;
	}
	
	void chkclass(String bip)
	{
		char a[]=bip.toCharArray();
		if(a[0]=='0')
		{
		System.out.println("class A");
		c=8;
		}
		if(a[0]=='1'&&a[1]=='0')
		{
		System.out.println("class B");
		c=16;
		}
		if(a[0]=='1'&&a[1]=='1'&&a[2]=='0')
		{
		System.out.println("class C");
		c=24;
		}
		
	}
	
	void fa(String bip,String bsub)
	{
	char a[]=bip.toCharArray();
	char b[]=bsub.toCharArray();
	char c[]=new char[32];	
	for(int i=0;i<=31;i++)
	{
		if(a[i]=='1'&&b[i]=='1')
		c[i]='1';
		else
		c[i]='0';
		
	}
	String s1=new String(c);
          String f1=s1.substring(0, 8);
        String f2=s1.substring(9, 16);
        String f3=s1.substring(17, 23);
        String f4=s1.substring(24);
        
           System.out.print(Integer.parseInt(f1,2)+".");
             System.out.print(Integer.parseInt(f2,2)+".");
         System.out.print(Integer.parseInt(f3,2)+".");
        
          System.out.print(Integer.parseInt(f4,2));
             
        
        
	}
	
	void la(String bip,String bsub)
	{
	char a[]=bip.toCharArray();
	char b[]=bsub.toCharArray();
	char c[]=new char[32];	
	for(int i=0;i<=31;i++)
	{
		if(b[i]=='1')
		b[i]='0';
		else
		b[i]='1';
		
	}
	
	for(int i=0;i<=31;i++)
	{
		if(a[i]=='0'&&b[i]=='0')
		c[i]='0';
		else
		c[i]='1';
		
	}
	
	String s1=new String(c);
	
        String f1=s1.substring(0, 8);
        String f2=s1.substring(9, 16);
        String f3=s1.substring(17, 23);
        String f4=s1.substring(24);
        
            System.out.print(Integer.parseInt(f1,2)+".");
             System.out.print(Integer.parseInt(f2,2)+".");
         System.out.print(Integer.parseInt(f3,2)+".");
        
          System.out.print(Integer.parseInt(f4,2));
        
	
	}
	
	
	
	
}	
	
class ip
{
	public static void main(String[]args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the ip address: ");
 		String ip = sc.nextLine();
 		calc c=new calc();
 		String bip=c.tobin(ip);
 		c.chkclass(bip);
 		System.out.print("Enter the subnet mask: ");
 		String sub = sc.nextLine();
 		String bsub=c.tobin(sub);
 		System.out.println("\n first address :");
 		c.fa(bip,bsub);
 		
 		System.out.println("\n last address :");
 		c.la(bip,bsub);
	}
}
