package hubble.backend.providers.models.jira;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Watch {
	private String self;
	private int watchCount;
	private boolean isWatching;
	
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	public int getWatchCount() {
		return watchCount;
	}
	public void setWatchCount(int watchCount) {
		this.watchCount = watchCount;
	}
	public boolean isWatching() {
		return isWatching;
	}
	public void setWatching(boolean isWatching) {
		this.isWatching = isWatching;
	}
}

