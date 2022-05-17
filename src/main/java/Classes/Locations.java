package Classes;

public class Locations {
    public final String name;
    public final double latitude;
    public final double longtitude;

    public Locations(String name, double latitude, double longtitude) {
        this.name = name;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    // create an image and save it as route.jpg
    public void displayRouteTo(Locations destination) {
    }

    @Override
    public String toString() {
        return this.name + "\n" + this.latitude  + "\n" + this.longtitude;
    }


}
