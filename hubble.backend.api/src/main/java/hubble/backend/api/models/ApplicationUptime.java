package hubble.backend.api.models;

public class ApplicationUptime {

    private String date;
    private Integer uptime;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getUptime() {
        return uptime;
    }

    public void setUptime(Integer uptime) {
        this.uptime = uptime;
    }
}
