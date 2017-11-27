package hubble.backend.providers.models.jira;

public class IssueType {
	private String self;
	private int id;
	private String description;
	private String iconUrl;
	private String name;
	private boolean subtask;
	private int avatarId;
	
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isSubtask() {
		return subtask;
	}
	public void setSubtask(boolean subtask) {
		this.subtask = subtask;
	}
	public int getAvatarId() {
		return avatarId;
	}
	public void setAvatarId(int avatarId) {
		this.avatarId = avatarId;
	}
}
