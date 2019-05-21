/***
 * This class defines the model to store data retrieved from the API.
 *
 * @author Tuan M. Nguyen
 */
package tuanmnguyen.AndroidTestTask.model;

import java.util.Date;

public class Episode {

    // Attributes
    private int id;
    private String url;
    private String name;
    private int season;
    private int number;
    private Date airdate;
    private Date airtime;
    private String airstamp;
    private int runtime;
    private String image_medium;
    private String image_original;
    private String summary;

    // Constructors
    public Episode() { }

    public Episode(int id, String url, String name, int season, int number, Date airdate, Date airtime, String airstamp, int runtime, String image_medium, String image_original, String summary) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.season = season;
        this.number = number;
        this.airdate = airdate;
        this.airtime = airtime;
        this.airstamp = airstamp;
        this.runtime = runtime;
        this.image_medium = image_medium;
        this.image_original = image_original;
        this.summary = summary;
    }

    // Setters & Getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getAirdate() {
        return airdate;
    }

    public void setAirdate(Date airdate) {
        this.airdate = airdate;
    }

    public Date getAirtime() {
        return airtime;
    }

    public void setAirtime(Date airtime) {
        this.airtime = airtime;
    }

    public String getAirstamp() {
        return airstamp;
    }

    public void setAirstamp(String airstamp) {
        this.airstamp = airstamp;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getImageMedium() {
        return image_medium;
    }

    public void setImageMedium(String image_medium) {
        this.image_medium = image_medium;
    }

    public String getImageOriginal() {
        return image_original;
    }

    public void setImageOriginal(String image_original) {
        this.image_original = image_original;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
