package hubble.backend.providers.models.jira;

import java.util.List;

public class Project {
	private String self;
	private String id;
	private String key;
	private String name;
	private List<AvatarUrl> avatarUrls;
	
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<AvatarUrl> getAvatarUrls() {
		return avatarUrls;
	}
	public void setAvatarUrls(List<AvatarUrl> avatarUrls) {
		this.avatarUrls = avatarUrls;
	}
}
