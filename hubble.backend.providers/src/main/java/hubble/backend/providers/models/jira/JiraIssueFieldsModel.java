package hubble.backend.providers.models.jira;

import java.util.Date;
import java.util.List;

public class JiraIssueFieldsModel {
	private IssueType issuetype;
	private List<IssueComponent> components;
	private long timespent;
	private long timeoriginalestimate;
	private String description;
	private Project project;
	private List<FixVersion> fixVersions;
	private int aggregatetimespent;
	private String resolution;
	private String customfield_10005;
	private int aggregatetimeestimate;
	private Date resolutiondate;
	private int workRatio;
	private String summary;
	private Date lastViewed;
	private Watch watches;
	private JiraUserModel creator;
	private List<Subtasks> subtasks;
	private Date created;
	private JiraUserModel reporter;
	private String customfield_10000;
	private Progress aggregateprogress;
	private Priority priority;
	private List<IssueLabel> labels;
	private String customfield_10004;
	private String environment;
	private long timeestimate;
	private long aggregatetimeoriginalestimate;
	private List<IssueVersion> versions;
	private Date duedate;
	private Progress progress;
	private List<IssueLink> issuelinks;
	private Vote votes;
	private JiraUserModel assignee;
	private Date updated;
	private IssueStatus status;

        public IssueType getIssuetype() {
            return issuetype;
        }

        public void setIssuetype(IssueType issuetype) {
            this.issuetype = issuetype;
        }

        public List<IssueComponent> getComponents() {
            return components;
        }

        public void setComponents(List<IssueComponent> components) {
            this.components = components;
        }

        public long getTimespent() {
            return timespent;
        }

        public void setTimespent(long timespent) {
            this.timespent = timespent;
        }

        public long getTimeoriginalestimate() {
            return timeoriginalestimate;
        }

        public void setTimeoriginalestimate(long timeoriginalestimate) {
            this.timeoriginalestimate = timeoriginalestimate;
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

        public int getAggregatetimespent() {
            return aggregatetimespent;
        }

        public void setAggregatetimespent(int aggregatetimespent) {
            this.aggregatetimespent = aggregatetimespent;
        }

        public String getResolution() {
            return resolution;
        }

        public void setResolution(String resolution) {
            this.resolution = resolution;
        }

        public String getCustomfield_10005() {
            return customfield_10005;
        }

        public void setCustomfield_10005(String customfield_10005) {
            this.customfield_10005 = customfield_10005;
        }

        public int getAggregatetimeestimate() {
            return aggregatetimeestimate;
        }

        public void setAggregatetimeestimate(int aggregatetimeestimate) {
            this.aggregatetimeestimate = aggregatetimeestimate;
        }

        public Date getResolutiondate() {
            return resolutiondate;
        }

        public void setResolutiondate(Date resolutiondate) {
            this.resolutiondate = resolutiondate;
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

        public String getCustomfield_10000() {
            return customfield_10000;
        }

        public void setCustomfield_10000(String customfield_10000) {
            this.customfield_10000 = customfield_10000;
        }

        public Progress getAggregateprogress() {
            return aggregateprogress;
        }

        public void setAggregateprogress(Progress aggregateprogress) {
            this.aggregateprogress = aggregateprogress;
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

        public String getCustomfield_10004() {
            return customfield_10004;
        }

        public void setCustomfield_10004(String customfield_10004) {
            this.customfield_10004 = customfield_10004;
        }

        public String getEnvironment() {
            return environment;
        }

        public void setEnvironment(String environment) {
            this.environment = environment;
        }

        public long getTimeestimate() {
            return timeestimate;
        }

        public void setTimeestimate(long timeestimate) {
            this.timeestimate = timeestimate;
        }

        public long getAggregatetimeoriginalestimate() {
            return aggregatetimeoriginalestimate;
        }

        public void setAggregatetimeoriginalestimate(long aggregatetimeoriginalestimate) {
            this.aggregatetimeoriginalestimate = aggregatetimeoriginalestimate;
        }

        public List<IssueVersion> getVersions() {
            return versions;
        }

        public void setVersions(List<IssueVersion> versions) {
            this.versions = versions;
        }

        public Date getDuedate() {
            return duedate;
        }

        public void setDuedate(Date duedate) {
            this.duedate = duedate;
        }

        public Progress getProgress() {
            return progress;
        }

        public void setProgress(Progress progress) {
            this.progress = progress;
        }

        public List<IssueLink> getIssuelinks() {
            return issuelinks;
        }

        public void setIssuelinks(List<IssueLink> issuelinks) {
            this.issuelinks = issuelinks;
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
