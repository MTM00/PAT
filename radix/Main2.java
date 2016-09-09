package radix;

import java.math.BigInteger;
import java.util.Scanner;

public class Main2 {
	public static void main(String args[])
	{
		String s1,s2,s3;
		
		BigInteger ans,ss,n;
		int flag;
		Scanner cin = new Scanner(System.in);
		while(cin.hasNext())
		{
			s1=cin.next();
			s2=cin.next();
			flag=cin.nextInt();
			n=cin.nextBigInteger();
			if(flag==2)
			{
				s3=s1;
				s1=s2;
				s2=s3;
			}
			
			int len=s1.length();
		    ans=BigInteger.ZERO;
		    for(int i=0; i<=len-1; i++)
		    {
		        int tmp;
		        if(s1.charAt(i)>='a'&&s1.charAt(i)<='z') tmp=s1.charAt(i)-'a'+10;
		        else tmp=s1.charAt(i)-'0';
		        ans=ans.multiply(n).add(BigInteger.valueOf(tmp));
		    }
		    
		    //System.out.println(ans);
		    BigInteger res=BigInteger.ZERO;
		    
		    int t;
		    len=s2.length();

		    String pp="100000000000000000000";  //10^20
		    BigInteger l,mid;
		    l=BigInteger.ONE;
		    BigInteger r=new BigInteger(pp);
		    
		    
		    while(r.compareTo(l)>=0)  
		    {
		        ss=BigInteger.ZERO;
		        
		        mid=l.add(r).divide(BigInteger.valueOf(2));
		        int fla=0;
			    for(int i=0; i<=len-1; i++)
			    {
			        int tmp;
			        if(s2.charAt(i)>='a'&&s2.charAt(i)<='z') tmp=s2.charAt(i)-'a'+10;
			        else tmp=s2.charAt(i)-'0';
			  
			        if(BigInteger.valueOf(tmp).compareTo(mid)>=0)
		            {
		                fla=1;
		                break;
		            }
			        ss=ss.multiply(mid).add(BigInteger.valueOf(tmp));
		        }
			    
		        if(fla==1) 
		        {
		        	l=mid.add(BigInteger.ONE);
		        }
		        if(ss.compareTo(ans)==0)
		        {
		        	res=mid;
		        	r=mid.add(BigInteger.valueOf(-1));
		        }
		        else if(ss.compareTo(ans)>0)
		        {
		        	r=mid.add(BigInteger.valueOf(-1));
		        }
		        else 
		        {
		        	l=mid.add(BigInteger.ONE);
		        }
		    }
		    
		    if(res.compareTo(BigInteger.ZERO)>0) System.out.println(res);
		    else System.out.println("Impossible");
		}

	}
}
