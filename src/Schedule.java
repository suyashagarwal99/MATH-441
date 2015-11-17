
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Schedule {

	public String[][] schedule;
    public coordinates latlon[]; 
	private int days;
	private int teams;
	private List<String> homeSchedule;
	private int numberAwayGamesAllowed = 2;
	private int numberDaysAway = 4;
	
	
	public Schedule() {
		this.days = 11;
		this.teams = 5;
		
		this.schedule = new String[days+1][teams];
		this.latlon = new coordinates[31]; //I start my array from 1 instead of 0. 
		coordinates z = new coordinates("CHI", 41.8806, 87.6742);
		latlon[1] = z;
		z = new coordinates("ANA", 33.8078, 117.8767);
		latlon[2] = z;
		z = new coordinates("COL", 39.7486, 105.0075);
		latlon[3] = z;
		z = new coordinates("ARZ", 33.5319, 112.2611);
		latlon[4] = z;
		z = new coordinates("DAL", 32.7906, 96.8103);
		latlon[5] = z;
		z = new coordinates("CGY", 51.0375, 114.0519);
		latlon[6] = z;
		z = new coordinates("MIN", 44.9447, 93.1011);
		latlon[7] = z;
		z = new coordinates("EDM", 53.5714, 113.4561);
		latlon[8] = z;		
		z = new coordinates("NSH", 36.1592, 86.7786);
		latlon[9] = z;
		z = new coordinates("LAK", 34.0431, 118.2672);
		latlon[10] = z;
		z = new coordinates("STL", 38.6267, 90.2025);
		latlon[11] = z;
		z = new coordinates("SJS", 37.3328, 121.9011);
		latlon[12] = z;
		z = new coordinates("WPG", 49.8928, 97.1436);
		latlon[13] = z;
		z = new coordinates("VAN", 49.2778, 123.1089);
		latlon[14] = z;
		z = new coordinates("BOS", 42.0909, 71.2643);
		latlon[15] = z;
		z = new coordinates("CAR", 35.8033, 78.7219);
		latlon[16] = z;
		z = new coordinates("BUF", 42.8750, 78.8764);
		latlon[17] = z;
		z = new coordinates("CBJ", 39.9693, 83.0061);
		latlon[18] = z;
		z = new coordinates("DET", 42.3253, 83.0514);
		latlon[19] = z;
		z = new coordinates("NJD", 40.7336, 74.1711);
		latlon[20] = z;
		z = new coordinates("FLA", 26.1583, 80.3256);
		latlon[21] = z;
		z = new coordinates("NYI", 40.6826, 73.9747);
		latlon[22] = z;
		z = new coordinates("MTL", 45.4961, 73.5694);
		latlon[23] = z;
		z = new coordinates("NYR", 40.7506, 73.9936);
		latlon[24] = z;
		z = new coordinates("OTT", 45.2969, 75.9272);
		latlon[25] = z;
		z = new coordinates("PHI", 39.9011, 75.1719);
		latlon[26] = z;
		z = new coordinates("TBL", 27.9428, 82.4519);
		latlon[27] = z;
		z = new coordinates("PIT", 40.4394, 79.9892);
		latlon[28] = z;
		z = new coordinates("TOR", 43.6433, 79.3792);
		latlon[29] = z;
		z = new coordinates("WSH", 38.8981, 77.0208);
		latlon[30] = z;
		
	}
	
	public void setAllToFree() {
		for (int i=0; i<=days; i++) {
			for (int j=0; j<teams; j++){
				schedule[i][j] = "FREE";
			}
		}
	}
	
	public int getNumberTeams() {
		return teams;
	}
	
	public int getNumberDays() {
		return days;
	}
	
	public String getElement(int day, int team){
		return schedule[day][team];
	}
	
	public boolean checkValidSchedule(){
		//make sure the team is not playing against itself
		for (int j = 0; j<teams; j++){
			for (int i = 1; i<=days; i++){
				if (this.getElement(i, j) == this.getElement(0, j)){
					return false;
				}
			}
		}
		//Check that every team hosts every other team once
		List<String> opponents = new ArrayList<String>();
		for (int j = 0; j<teams; j++){
			opponents.clear();
			for (int i = 0; i<=days; i++){
				if (!this.getElement(i, j).equals("X") && !this.getElement(i, j).equals("FREE")){
					opponents.add(this.getElement(i, j));
				}
			}
			if (opponents.size() != (teams)){
			return false;
			}
			if (!opponents.contains("VAN") || !opponents.contains("CAL")
					|| !opponents.contains("EDM")){
				
				return false;
			}
		
		}
		for (int j = 0; j < teams; j++) {
			String homeTeam = this.getElement(0, j);
			homeSchedule.clear();
			for (int i=0; i <= days; i++) {
				//Case where they're playing a home game
				if (!this.getElement(i, j).equals("FREE") && !this.getElement(i, j).equals("X")) {
					homeSchedule.add(homeTeam);
				}
				//Case where they're playing an away game
				else if (!this.getElement(i,j).equals("FREE")) {
					for (int h=0; h< teams; h++) {
						if (this.getElement(i, h).equals(homeTeam)) {
							homeSchedule.add(this.getElement(0, h));
						}
					}
				}	
			}
			int countAwayGames = 0;
			for (int i = 0; i < homeSchedule.size(); i++) {
				if (!homeSchedule.get(i).equals(homeTeam)){
					countAwayGames++;
					if (countAwayGames > numberAwayGamesAllowed) {
						return false;
					}
				}
				else countAwayGames = 0;
			}
			
		}
		
		return true;
	}
	
	public double distanceBetweenCities(String cityA, String cityB) {
	    int i1 = 1;
	    int i2 = 1;
	    int i = 1;
	    while(i<=30){
	    	if(latlon[i].city == cityA){i1 = i;}
	    	if(latlon[i].city == cityB){i2 = i;}
	    	i++;}
		
		return calculated(i1,i2);
		
	}
	
	public double teamScheduleDistance(List<String> travel) {
		int size = travel.size();
		double distance = 0;
		for (int i=0; i<size - 1; i++) {
			distance = distance + this.distanceBetweenCities(travel.get(i), travel.get(i+1));
		}
		return distance;
		
	}
	
	public double distanceTravelled() {
		homeSchedule = new ArrayList<String>();
		double distance = 0;
		for (int j = 0; j < teams; j++) {
			String homeTeam = this.getElement(0, j);
			homeSchedule.clear();
			for (int i=0; i <= days; i++) {
				//Case where they're playing a home game
				if (!this.getElement(i, j).equals("FREE") && !this.getElement(i, j).equals("X")) {
					homeSchedule.add(homeTeam);
				}
				//Case where they're playing an away game
				else if (!this.getElement(i,j).equals("FREE")) {
					for (int h=0; h< teams; h++) {
						if (this.getElement(i, h).equals(homeTeam)) {
							homeSchedule.add(this.getElement(0, h));
						}
					}
				}
			}
			distance = distance + this.teamScheduleDistance(homeSchedule);
			
		}
		return distance;
	}
	
	public void printSchedule() {
		System.out.println(Arrays.deepToString(this.schedule));
	}
	
	public double calculated(int i1, int i2){
		double c = 0.0174603175;
		double R = 6371000; // metres
		double p1 = latlon[i1].lat*c;
		double p2 = latlon[i2].lat*c;
		double p = (latlon[i2].lat - latlon[i1].lat)*c;
		double q = (latlon[i2].lon - latlon[i1].lon)*c;

		double a = Math.sin(p/2) * Math.sin(p/2) +
		        Math.cos(p1) * Math.cos(p2) *
		        Math.sin(q/2) * Math.sin(q/2);
		double c2 = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

		double d = R * c2; d = d*.001; 
	return d; }
	
}
