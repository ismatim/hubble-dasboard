package hubble.backend.providers.models.jira;

import java.util.Date;
import java.util.List;

public class JiraIssueFieldsModel {
	private IssueType issueType;
	private List<IssueComponent> components;
	private long timeSpent;
	private long timeOriginalEstimate;
	private String description;
	private Project project;
	private List<FixVersion> fixVersions;
	private int aggregateTimeSpent;
	private String resolution;
	private String customField10005;
	private int aggregateTimeEstimate;
	private long resolutionDate;
	private int workRatio;
	private String summary;
	private Date lastViewed;
	private Watch watches;
	private JiraUserModel creator;
	private List<Subtasks> subtasks;
	private Date created;
	private JiraUserModel reporter;
	private String customField10000;
	private Progress aggregateProgress;
	private Priority priority;
	private List<IssueLabel> labels;
	private String customField10004;
	private String environment;
	private long timeEstimate;
	private long aggregateTimeOriginalEstimate;
	private List<IssueVersion> versions;
	private Date dueDate;
	private Progress progress;
	private List<IssueLink> issueLinks;
	private Vote votes;
	private JiraUserModel assignee;
	private Date updated;
	private IssueStatus status;
	
	public IssueType getIssueType() {
		return issueType;
	}
	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}
	public List<IssueComponent> getComponents() {
		return components;
	}
	public void setComponents(List<IssueComponent> components) {
		this.components = components;
	}
	public long getTimeSpent() {
		return timeSpent;
	}
	public void setTimeSpent(long timeSpent) {
		this.timeSpent = timeSpent;
	}
	public long getTimeOriginalEstimate() {
		return timeOriginalEstimate;
	}
	public void setTimeOriginalEstimate(long timeOriginalEstimate) {
		this.timeOriginalEstimate = timeOriginalEstimate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public List<FixVersion> getFixVersions() {
		return fixVersions;
	}
	public void setFixVersions(List<FixVersion> fixVersions) {
		this.fixVersions = fixVersions;
	}
	public int getAggregateTimeSpent() {
		return aggregateTimeSpent;
	}
	public void setAggregateTimeSpent(int aggregateTimeSpent) {
		this.aggregateTimeSpent = aggregateTimeSpent;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getCustomField10005() {
		return customField10005;
	}
	public void setCustomField10005(String customField10005) {
		this.customField10005 = customField10005;
	}
	public int getAggregateTimeEstimate() {
		return aggregateTimeEstimate;
	}
	public void setAggregateTimeEstimate(int aggregateTimeEstimate) {
		this.aggregateTimeEstimate = aggregateTimeEstimate;
	}
	public long getResolutionDate() {
		return resolutionDate;
	}
	public void setResolutionDate(long resolutionDate) {
		this.resolutionDate = resolutionDate;
	}
	public int getWorkRatio() {
		return workRatio;
	}
	public void setWorkRatio(int workRatio) {
		this.workRatio = workRatio;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Date getLastViewed() {
		return lastViewed;
	}
	public void setLastViewed(Date lastViewed) {
		this.lastViewed = lastViewed;
	}
	public Watch getWatches() {
		return watches;
	}
	public void setWatches(Watch watches) {
		this.watches = watches;
	}
	public JiraUserModel getCreator() {
		return creator;
	}
	public void setCreator(JiraUserModel creator) {
		this.creator = creator;
	}
	public List<Subtasks> getSubtasks() {
		return subtasks;
	}
	public void setSubtasks(List<Subtasks> subtasks) {
		this.subtasks = subtasks;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public JiraUserModel getReporter() {
		return reporter;
	}
	public void setReporter(JiraUserModel reporter) {
		this.reporter = reporter;
	}
	public String getCustomField10000() {
		return customField10000;
	}
	public void setCustomField10000(String customField10000) {
		this.customField10000 = customField10000;
	}
	public Progress getAggregateProgress() {
		return aggregateProgress;
	}
	public void setAggregateProgress(Progress aggregateProgress) {
		this.aggregateProgress = aggregateProgress;
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	public List<IssueLabel> getLabels() {
		return labels;
	}
	public void setLabels(List<IssueLabel> labels) {
		this.labels = labels;
	}
	public String getCustomField10004() {
		return customField10004;
	}
	public void setCustomField10004(String customField10004) {
		this.customField10004 = customField10004;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public long getTimeEstimate() {
		return timeEstimate;
	}
	public void setTimeEstimate(long timeEstimate) {
		this.timeEstimate = timeEstimate;
	}
	public long getAggregateTimeOriginalEstimate() {
		return aggregateTimeOriginalEstimate;
	}
	public void setAggregateTimeOriginalEstimate(long aggregateTimeOriginalEstimate) {
		this.aggregateTimeOriginalEstimate = aggregateTimeOriginalEstimate;
	}
	public List<IssueVersion> getVersions() {
		return versions;
	}
	public void setVersions(List<IssueVersion> versions) {
		this.versions = versions;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Progress getProgress() {
		return progress;
	}
	public void setProgress(Progress progress) {
		this.progress = progress;
	}
	public List<IssueLink> getIssueLinks() {
		return issueLinks;
	}
	public void setIssueLinks(List<IssueLink> issueLinks) {
		this.issueLinks = issueLinks;
	}
	public Vote getVotes() {
		return votes;
	}
	public void setVotes(Vote votes) {
		this.votes = votes;
	}
	public JiraUserModel getAssignee() {
		return assignee;
	}
	public void setAssignee(JiraUserModel assignee) {
		this.assignee = assignee;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public IssueStatus getStatus() {
		return status;
	}
	public void setStatus(IssueStatus status) {
		this.status = status;
	}
	
	
}
