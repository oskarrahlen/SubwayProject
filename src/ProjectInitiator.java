import java.util.Date;

import java.util.Timer;

import java.util.TimerTask;

public class ProjectInitiator {
	Front front = new Front();
	SubwayInfo info = new SubwayInfo();
	
	LoopTask task = new LoopTask();
    Timer timer = new Timer("TaskName");
    
	public static void main(String[] args) {
		
		ProjectInitiator executingTask = new ProjectInitiator();
	    executingTask.start();
	
	
	}
	
	void start(){
		 long delay = 60*1000; // delay in milliseconds
		    
		    timer.cancel();
		    timer = new Timer("TaskName");
		    Date executionDate = new Date(); // no params = now
		    timer.scheduleAtFixedRate(task, executionDate, delay);
	}
	
	private class LoopTask extends TimerTask {
	    public void run() {
	    	System.out.println("Updating");
	        front.setSubwayLabels(info.updateDepartures());
	    }
	    }

}
