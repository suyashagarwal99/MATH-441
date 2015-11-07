import java.util.List;

public class Team {

	private List<String> homeSchedule;
	private int homeIndex;
	private String teamName;
	
	public Team(String team, int homeIndex) {
		this.teamName = team;
		this.homeIndex = homeIndex;
	}
	
	private List<String> getHomeSchedule() {
		return homeSchedule;
	}
	
	private String getTeamName() {
		return teamName;
	}
	
	private int getHomeIndex() {
		return homeIndex;
	}
	
	
}