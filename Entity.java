import processing.core.*;
import java.util.List;

public class Entity{
    protected String name;
    protected List<PImage> imgs;
    protected Point point;
    protected static int current_img;
    public Entity(String name, List<PImage> imgs, Point point){
        this.name = name;
        this.imgs = imgs;
        this.point = point;
        this.current_img = 0;
    }

    int BLOB_RATE_SCALE = 4;
    int BLOB_ANIMATION_RATE_SCALE = 50;
    int BLOB_ANIMATION_MIN = 1;
    int BLOB_ANIMATION_MAX = 3;

    int ORE_CORRUPT_MIN = 20000;
    int ORE_CORRUPT_MAX = 30000;

    int QUAKE_STEPS = 10;
    int QUAKE_DURATION = 1100;
    int QUAKE_ANIMATION_RATE = 100;

    int VEIN_SPAWN_DELAY = 500;
    int VEIN_RATE_MIN = 8000;
    int VEIN_RATE_MAX = 17000;

    public void set_position(Point point){
        this.point = point;
    }
    public Point get_position(){
        return this.point;
    }
    public String get_name(){
        return this.name;
    }
    public void next_image() {
        current_img = (current_img + 1) % imgs.size();
    }
    public PImage get_image(){
    	return this.imgs.get(current_img);
    }
    public List<PImage> get_images(){
        return this.imgs;
    }
    public void clear_pending_actions(){
    	//TODO: Figure out how pending actions work
    }
}