package Game;
// this class will store the setting which will be determined in the setttings panel
// and will be used in basic setup
public class Settings {
	int trafficDensity;
	int questionDifficulty;
	int volume;
	//constructor
	public Settings(int trafficDensity, int questionDifficulty, int volume) {
		this.trafficDensity = trafficDensity;
		this.questionDifficulty = questionDifficulty;
		this.volume = volume;
	}
	// getters and setters
	public int getTrafficDensity() {
		return trafficDensity;
	}
	public void setTrafficDensity(int trafficDensity) {
		this.trafficDensity = trafficDensity;
	}
	public int getQuestionDifficulty() {
		return questionDifficulty;
	}
	public void setQuestionDifficulty(int questionDifficulty) {
		this.questionDifficulty = questionDifficulty;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
}
