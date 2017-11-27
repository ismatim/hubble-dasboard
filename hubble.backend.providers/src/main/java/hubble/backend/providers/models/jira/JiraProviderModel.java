package hubble.backend.providers.models.jira;

import java.util.List;

public class JiraProviderModel {
	
	private String expand;
	private int startAt;
	private int maxResults;
	private int total;
	private List<JiraIssueModel> issues;
	
	public String getExpand() {
		return expand;
	}
	
	public void setExpand(String expand) {
		this.expand = expand;
	}
	
	public int getStartAt() {
		return startAt;
	}
	
	public void setStartAt(int startAt) {
		this.startAt = startAt;
	}
	
	public int getMaxResults() {
		return maxResults;
	}
	
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}
	
	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public List<JiraIssueModel> getIssues() {
		return issues;
	}
	
	public void setIssues(List<JiraIssueModel> issues) {
		this.issues = issues;
	}
}
