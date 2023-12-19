package logic;

public class Timer {
	private int minute;
	private int seconds;
	private int ms;
	
	
	public Timer() {
		reset();
	}
	
	public Timer(int m, int s, int ms) {
		minute = m;
		seconds = s;
		this.ms = ms;
	}
	
	public void increaseTimer(int amount) { //increase the timer by specified amount (milliseconds)
		
		ms += amount;

		while(ms > 100) {
			ms = 0;
			seconds += 1;	
			while(seconds > 60) {
				seconds = 0;	
				minute += 1;
			}
		}

	}
	
	public boolean isTimerEmpty() {
		return minute<=0 && seconds<=0 && ms<=0;
	}
	
	public String toString() {
		return String.format("%02d:%02d:%02d",minute, seconds, ms);
	}
	
	public void reset() {
		this.minute = 0;
		this.seconds = 0;
		this.ms = 0;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public int getMs() {
		return ms;
	}

	public void setMs(int ms) {
		this.ms = ms;
	}
	
	
}
