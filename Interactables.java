import java.util.List;
import processing.core.*;

public class Interactables extends Entity {
    private int rate;
    private int resource_distance;
    public Interactables(String name, int rate, Point position, List<PImage> imgs, int resource_distance){
        super(name, imgs, position);
        this.rate = rate;
        this.resource_distance = resource_distance;
    }
    public int get_rate(){
        return this.rate;
    }
    public int get_resource_distance(){
        return this.resource_distance;
    }
    public String entity_string(){
        return "vein" + this.name + this.point.getx() +
                this.point.gety() + this.rate +
                this.resource_distance;
    }
}