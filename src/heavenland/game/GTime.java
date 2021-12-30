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
	
	public int getDay() {                      
		return day;        
	}
     
	public int getMonth() {                        
		return month;        
	}
 
	public int getYear() {                        
		return year;        
	}
	
	public void setDefaultDate() {
		
		day = 1;
		month = 1;
		year = 1;
	}
	
	public void resetTime() {
		
		second = 0;
		minute = 0;
		hour = 6;
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
	
	public void incrementYear() {
		this.year++;
	}
	
	public void incrementMonth() {
		if (month == 2) {
			this.month = 1;
			incrementYear();
		}
		else
			this.month++;
	}
	
	public void incrementDay() {
		if (day == 28) {
			this.day = 1;
			incrementMonth();
		}
		else
			this.day++;
	}
	
	public String toStringGame() {
		return String.format("%02d:%02d %s", 
				((getHour() == 0 || getHour() == 12) ? 12 : getHour() % 12),
				getMinute()/5*5, (getHour() < 12 ? "AM" : "PM"));
	}
	
	@Override
	public String toString() {
		return String.format("%02d:%02d:%02d %s", 
				((getHour() == 0 || getHour() == 12) ? 12 : getHour() % 12),
				getMinute(), getSecond(), (getHour() < 12 ? "AM" : "PM"));
	}
	
}
