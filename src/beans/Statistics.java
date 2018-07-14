package beans;

import java.util.Date;

public class Statistics {
    private int id;

    private int url_id;

    private int visites;

    private Date date;

    public Statistics()
    {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUrl_id() {
        return url_id;
    }

    public void setUrl_id(int url_id) {
        this.url_id = url_id;
    }

    public int getVisites() {
        return visites;
    }

    public void setVisites(int visites) {
        this.visites = visites;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
