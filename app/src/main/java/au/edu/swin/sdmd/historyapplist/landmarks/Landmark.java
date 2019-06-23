package au.edu.swin.sdmd.historyapplist.landmarks;

/**
 * This is the Landmark object which stores info about each Landmark. For the moment we are storing
 * coords only, but other fields/getters/setters are included ready to go.
 *
 * @author nronald
 * @date August 2018
 */
public class Landmark {
    double latitude;
    double longitude;
    int image;
    int title;
    int creator;

    private static final int NOT_FOUND = -1;

    public Landmark(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.image = NOT_FOUND;
        this.title = NOT_FOUND;
        this.creator = NOT_FOUND;
    }

    public Landmark(double latitude, double longitude, int image, int title, int creator) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.image = image;
        this.title = title;
        this.creator = creator;
    }

    public String getCoords() {
        return String.format("%f, %f", latitude, longitude);
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

}
