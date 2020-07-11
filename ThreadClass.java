package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

import javax.mail.internet.InternetAddress;
//import java.util.*;

public class ThreadClass extends TimerTask{
 
	Connection connection;
	private Date dateCalculator(int day)
	{
		final Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DATE, -day);
		return cal.getTime();
		
	}
	public void run()
	{
		DateFormat dtf= new SimpleDateFormat("yyyy-MM-dd");
		String date5=dtf.format(dateCalculator(5));
		String date3=dtf.format(dateCalculator(3));
		String date=dtf.format(dateCalculator(0));
		System.out.println(date5+" "+date3+" "+date);
		
		try{
			Class.forName("org.postgresql.Driver");
			System.out.println();
			System.out.println("Connection is stated");
			String url= "--------------------";
			
			String user= "--------";
			String password= "--------";
			String email;
			String phone;
			int no_of_trans;
			connection= DriverManager.getConnection(url,user,password);
			Statement st3= connection.createStatement();
			ResultSet rs3=st3.executeQuery("select string_agg(u.email::text, ',') as emails, string_agg(u.phone::text, ',') as phone_nos,(select count(*) as no_of_transaction from wheelset_tran where  CURRENT_DATE-to_date(substring(transaction_date,1,10), 'DD-MM-YYYY') between 2 and 5)from  user_master u where  user_type='System Admin' or (user_type='Production'and role in ('All', '%Wheel%'))  ");
			
			while(rs3.next())
			{
				
				SentMail sentMail=new SentMail();
				SentSms sentSMS= new SentSms();
				phone= rs3.getString(2);
				email= rs3.getString(1);
				no_of_trans= rs3.getInt(3);
				
				String subject= "Wheel Shop has not been updated";
				String content= "This is to inform you that Wheel Shop data has not been updated from 3 days";
			/*	String[] arrOfStr = email.split(",");
				String[] arrOfStr1 = phone.split(",");
				if(no_of_trans==0)
				{
					
		        for (String a: arrOfStr){
		            System.out.println(a);

					sentMail.sentMail(a, subject, content);
					//sentSMS.sentSms(phone, subject);
		        }
		       
		        for (String b: arrOfStr1){
		        	System.out.println(b);
		        
		        		sentSMS.sentSms(b, subject);
		        	
		        }
				}*/
				
				String[] arrOfStr1 = phone.split(",");
				if(no_of_trans==0)
				{
					sentMail.sentMail(email, subject, content);
					  for (String b: arrOfStr1){
				        	System.out.println(b);
				        
				        		sentSMS.sentSms(b, subject);
				        	
				        }
				}
					
			}
			rs3.close();
			
			ResultSet rs4=st3.executeQuery("select string_agg(u.email::text, ',') as emails, string_agg(u.phone::text, ',') as phone_nos,(select count(*) as no_of_transaction from shell_tran where  CURRENT_DATE-to_date(substring(transaction_date,1,10), 'DD-MM-YYYY') between 2 and 5)from  user_master u where  user_type='System Admin' or (user_type='Production'and role in ('All', '%Shell%'))   ");
			while(rs4.next())
			{
				
				SentMail sentMail=new SentMail();
				SentSms sentSMS= new SentSms();
				phone= rs4.getString(2);
				email= rs4.getString(1);
				no_of_trans= rs4.getInt(3);
				
				String subject= "Shell Shop has not been updated";
				String content= "This is to inform you that Shell Shop data has not been updated from 3 days";
				
				String[] arrOfStr2 = phone.split(",");
				if(no_of_trans==0)
				{
					sentMail.sentMail(email, subject, content);
					  for (String b: arrOfStr2){
				        	System.out.println(b);
				        
				        		sentSMS.sentSms(b, subject);
				        	
				        }
				}
			}
			rs4.close();	
			
			ResultSet rs5=st3.executeQuery("select string_agg(u.email::text, ',') as emails, string_agg(u.phone::text, ',') as phone_nos,(select count(*) as no_of_transaction from bogieset_trans where  CURRENT_DATE-to_date(substring(trx_date,1,10), 'DD-MM-YYYY') between 2 and 5)from  user_master u where  user_type='System Admin' or (user_type='Production'and role in ('All', '%Bogie%')) ");
			while(rs5.next())
			{
				
				SentMail sentMail=new SentMail();
				SentSms sentSMS= new SentSms();
				phone= rs5.getString(2);
				email= rs5.getString(1);
				no_of_trans= rs5.getInt(3);
				
				String subject= "Bogie Shop has not been updated";
				String content= "This is to inform you that Bogie Shop data has not been updated from 3 days";
				
				String[] arrOfStr3 = phone.split(",");
				if(no_of_trans==0)
				{
					sentMail.sentMail(email, subject, content);
					  for (String b: arrOfStr3){
				        	System.out.println(b);
				        
				        		sentSMS.sentSms(b, subject);
				        	
				        }
				}
			}
			rs5.close();	
			
			
			ResultSet rs6=st3.executeQuery("select string_agg(u.email::text, ',') as emails, string_agg(u.phone::text, ',') as phone_nos,(select count(*) as no_of_transaction from paint_tran where  CURRENT_DATE-to_date(substring(transaction_date,1,10), 'DD-MM-YYYY') between 2 and 5)from  user_master u where  user_type='System Admin' or (user_type='Production'and role in ('All', '%Paint%')) ");
			while(rs6.next())
			{
				
				SentMail sentMail=new SentMail();
				SentSms sentSMS= new SentSms();
				phone= rs6.getString(2);
				email= rs6.getString(1);
				no_of_trans= rs6.getInt(3);
				
				String subject= "Paint Shop has not been updated";
				String content= "This is to inform you that Paint Shop data has not been updated from 3 days";
				
				String[] arrOfStr4 = phone.split(",");
				if(no_of_trans==0)
				{
					sentMail.sentMail(email, subject, content);
					  for (String b: arrOfStr4){
				        	System.out.println(b);
				        
				        		sentSMS.sentSms(b, subject);
				        	
				        }
				}
			}
			rs6.close();
			
			ResultSet rs7=st3.executeQuery("select string_agg(u.email::text, ',') as emails, string_agg(u.phone::text, ',') as phone_nos,(select count(*) as no_of_transaction from furnishing_tran where  CURRENT_DATE-to_date(substring(transaction_date,1,10), 'DD-MM-YYYY') between 2 and 5)from  user_master u where  user_type='System Admin' or (user_type='Production'and role in ('All', '%furn05%','%furnD11%','%furn15%','%furn06C%'))");
			while(rs7.next())
			{
				
				SentMail sentMail=new SentMail();
				SentSms sentSMS= new SentSms();
				phone= rs7.getString(2);
				email= rs7.getString(1);
				no_of_trans= rs7.getInt(3);
				
				String subject= "Furnishing Shop has not been updated";
				String content= "This is to inform you that Furnishing Shop data has not been updated from 3 days";
				
				String[] arrOfStr5 = phone.split(",");
				if(no_of_trans==0)
				{
					sentMail.sentMail(email, subject, content);
					  for (String b: arrOfStr5){
				        	System.out.println(b);
				        
				        		sentSMS.sentSms(b, subject);
				        	
				        }
				}
			}
			rs7.close();
			connection.close();
		}catch(Exception e)
		{
			System.out.println(e);
		}
	finally{
		System.out.println("Finally block called");
	}
}
}
