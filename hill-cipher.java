public class HillCipher
{
public static void main(String[] args)
{ int[][] key={{3,3},{2,5}};
String msg="CRYPTOGRAPHY";
if(msg.length()%2!=0)
msg+="X";
String enc="";
for(int i=0;i<msg.length();i+=2)
{
int a=msg.charAt(i)-'A'; int b=msg.charAt(i+1)-'A';
enc += (char)((key[0][0]*a+key[0][1]*b)%26+'A');
enc += (char)((key[1][0]*a+key[1][1]*b)%26+'A');

}
System.out.println("Original : "+msg);
System.out.println("Encrypted: "+enc);
}
}
