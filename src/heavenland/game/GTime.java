package heavenland.game;

public class GTime {

	int day, month, year;
	int second, minute, hour;
	
	public GTime(int day, int month, int year, int second, int minute, int hour) {
		
		this.day = day;
		this.month = month;
		this.year = year;
		
		this.second = second;
		this.minute = minute;
		this.hour = hour;
	}
	
	public GTime() {
		
		setDefaultDate();
		resetTime();
	}
	
	public int getHour() {                      
		return hour;        
	}
     
	public int getMinute() {                        
		return minute;        
	}
 
	public int getSecond() {                        
		return second;        
	}
	
	public void setDefaultDate() {
		
		day = 1;
		month = 1;
		year = 1;
	}
	
	public void resetTime() {
		
		second = 0;
		minute = 0;
		hour = 5;
	}
	
	public void incrementHour() {
		if (hour == 23)
			this.hour = 0;
		else
			this.hour++;
	}
	
	public void incrementMinute() {
		if (minute == 59) {
			this.minute = 0;
			incrementHour();
		}
		else
			this.minute++;
	}
	
	public void tick() {
		if (second == 59) {
			this.second = 0;
			incrementMinute();
		}
		else
			this.second++;
	}
	
	@Override
	public String toString() {
		return String.format("%d:%02d:%02d %s", 
				((getHour() == 0 || getHour() == 12) ? 12 : getHour() % 12),
				getMinute(), getSecond(), (getHour() < 12 ? "AM" : "PM"));
	}
}
