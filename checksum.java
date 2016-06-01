import java.util.*;
class checksum
{
public static void main(String[] args)
{
 int i,schar,checksum = 0,checksum1=0;
 String s;
 String sum = new String();
 Scanner sc=new Scanner(System.in);
 System.out.println("Enter a string:");
 s =sc.nextLine();
 for(i=0;i<s.length();i+=2)
 {
 if (s.length() % 2 == 0)
 {
    schar=(int)(s.charAt(i));
    sum =Integer.toHexString(schar);
    schar=(int)(s.charAt(i+1));
    sum = sum+Integer.toHexString(schar);
    System.out.println(s.charAt(i) + "" + s.charAt(i + 1) + " : "+ sum);
    schar = Integer.parseInt(sum, 16);
 }
  else
   {
    schar = (int) (s.charAt(i));
    sum = "00" + Integer.toHexString(schar);
    schar = Integer.parseInt(sum, 16);
    System.out.println(s.charAt(i) + " : " + sum);
   }
    checksum += schar;
    sum = Integer.toHexString(checksum);
 }
    if (sum.length() > 4)
    {
    int carry = Integer.parseInt(("" + sum.charAt(0)), 16);
    sum=sum.substring(1, 5);
    checksum = Integer.parseInt(sum, 16);
    checksum += carry;  }
 checksum = Integer.parseInt("FFFF", 16)-checksum;
 System.out.println("checksum:"+Integer.toHexString(checksum));
 System.out.println("Enter the data sent:");
 s=sc.nextLine();
 System.out.println("Enter the checksum generated:");
 checksum=Integer.parseInt((sc.next()),16);
 System.out.println("checksum:"+Integer.toHexString(checksum));
  for(i=0;i<s.length();i+=2)
 {
 if (s.length() % 2 == 0)
 {
    schar=(int)(s.charAt(i));
    sum =Integer.toHexString(schar);
    schar=(int)(s.charAt(i+1));
    sum = sum+Integer.toHexString(schar);
    schar = Integer.parseInt(sum, 16);
 }
  else
   {
    schar = (int) (s.charAt(i));
    sum = "00" + Integer.toHexString(schar);
    schar = Integer.parseInt(sum, 16);
   }
    checksum += schar;
    sum= Integer.toHexString(checksum);
 }
    if (sum.length() > 4)
    {
    int carry = Integer.parseInt(("" + sum.charAt(0)), 16);
    sum=sum.substring(1, 5);
    checksum = Integer.parseInt(sum, 16);
    checksum += carry;  }
 checksum = Integer.parseInt("FFFF", 16)-checksum;
 checksum1 = Integer.parseInt("FFFF", 16)-checksum1;
 int checksum2=checksum1+checksum;
 checksum2 = Integer.parseInt("FFFF", 16)-checksum2;
 System.out.println("checksum2="+Integer.toHexString(checksum2));
 if(checksum2==0)
 	System.out.println("No error");
 else
 	System.out.println("Error!!");

}
}
