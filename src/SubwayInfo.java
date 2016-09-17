import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.ParseException;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/*
 * Provides information about when the next subway leaves aspudden station. 
 * Gets information by sending HTTP requests to trafikverkets servers.
 * Author: Oskar R�hl�n
 */
public class SubwayInfo {
	private int numberOfDepartures = 10;
	SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");  
	private String[] departures;
	
	public long[][] getSubwayInfo(){
		System.out.println("collecting subway info");
		try {
			HttpResponse<JsonNode> response = Unirest.get("https://api.resrobot.se/v2/departureBoard?key=3115da74-d2c0-4ae5-a417-47ffb530a6f1&"
					+ "id=740021720&maxJourneys=" + numberOfDepartures + "&format=json&direction=740004046").asJson();
			return searchForDepatureTime( response.getBody().toString());
			
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private long[][] searchForDepatureTime(String body){
		
		// split the bodystring
		String[] parts = body.split("depTime\":\"");
		
		// make a new list that will hold the departuretimes in a string format
		String[] departureTimes = new String[numberOfDepartures];
		
		// add departuretimes to our list.  
		for(int i = 1; i <= numberOfDepartures; i++){
			
			departureTimes[i -1] = parts[i].substring(0, 8);
		}
		this.departures = departureTimes;
		return formatDepartureTimes(departureTimes);
	}

	private long[][] formatDepartureTimes(String[] departureTimes) {
		// our departures. the [0][0] is how many hours until the first departure [0][1] how many minutes [0][2] how many seconds
		long departures[][] = new long[numberOfDepartures][3];
		
		
		// Get the local time
		Calendar cal = Calendar.getInstance();
		
		// compare departure time with local time and store it in the 'departures'. For every departure that is in 'departureTimes'
		for(int i = 0; i < departureTimes.length; i++){
			long[] diff = getDiff(format.format(cal.getTime()), departureTimes[i]);
			departures[i][0] = diff[0]; //diff hours
			departures[i][1] = diff[1]; //diff minutes
			departures[i][2] = diff[2]; // diff seconds
		}
		//this.departures = departures;
		
			return departures;
		}
	
	public long[] getDiff(String now, String departure){
		Date d1 = null;
		Date d2 = null;
			
			try {
				d1 = format.parse(now);
				d2 = format.parse(departure);
				
			} catch (java.text.ParseException e) {
				
				e.printStackTrace();
			}
			
			// compare the departuretime with our local time 
			long diff = d2.getTime() - d1.getTime();
			long diffSeconds = diff / 1000 % 60;  
			long diffMinutes = diff / (60 * 1000) % 60;        
			long diffHours = diff / (60 * 60 * 1000);   
			long[] diffList = {diffHours, diffMinutes, diffSeconds};
			
			return diffList;
	}
	
	public long[][] updateDepartures(){
		if(departures == null){
			getSubwayInfo();
		}
		if(this.departures.length < 5){
			getSubwayInfo();
			
		}
		
		long[][] departures = formatDepartureTimes(this.departures);
		
			if(departures[0][1] < 0){
				
				long newDep[][] = new long[departures.length -1][3];
				
				for(int b = 1; b<departures.length; b++){
					newDep[b-1][0] = departures[b][0];
					newDep[b-1][1] = departures[b][1];
					newDep[b-1][2] = departures[b][2];
				}
				String[] newStringDep = new String[this.departures.length -1];
				for(int i = 1; i < this.departures.length; i++){
					newStringDep[i-1] = this.departures[i];
					
				}
				this.departures = newStringDep;
			
		}
		
		return departures;
	}

}
