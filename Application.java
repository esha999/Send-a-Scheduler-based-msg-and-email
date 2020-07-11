package demo;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("i am running");
		Timer time=new Timer();
		ThreadClass t1=new ThreadClass();
	
		TimerTask tt = new TimerTask(){
			public void run(){
				Calendar cal = Calendar.getInstance(); //this is the method you should use, not the Date(), because it is desperated.
 
				int hour = cal.get(Calendar.HOUR_OF_DAY);//get the hour number of the day, from 0 to 23
 
				if(hour == 13){
					time.schedule(t1,0,1000*3600*24);
				}
			}
		};
		time.schedule(tt, 1000, 1000*5);
		
		
	}

}
