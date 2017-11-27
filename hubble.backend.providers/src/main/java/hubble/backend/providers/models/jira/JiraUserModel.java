package hubble.backend.providers.models.jira;

import java.util.List;

public class JiraUserModel {
	private String self;
	private String name;
	private String key;
	private String email;
	private List<AvatarUrl> avatarUrls;
	private String displayName;
	private boolean active;
	private String timeZone;
	
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<AvatarUrl> getAvatarUrls() {
		return avatarUrls;
	}
	public void setAvatarUrls(List<AvatarUrl> avatarUrls) {
		this.avatarUrls = avatarUrls;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	
}
