package space.abdilazov.gruop39.models;

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable {

    private String Title;
    private Date createAd;

    public News() {
    }

    public News(String title, Date createAd) {
        Title = title;
        this.createAd = createAd;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Date getCreateAd() {
        return createAd;
    }

}
