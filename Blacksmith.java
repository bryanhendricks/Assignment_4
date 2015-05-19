import java.util.ArrayList;
import java.util.List;

import processing.core.*;

public class Blacksmith extends Interactables{
    private int resource_distance;
    private int current_img;
    private int resource_count;
    private ArrayList<Object> pending_actions = new ArrayList<Object>();
    public Blacksmith(String name, Point position, List<PImage> imgs, int resource_limit,
    		int rate, int resource_distance){
        super(name, rate, position, imgs, resource_distance);
        this.resource_distance = 1;
        this.current_img = 0;
        this.resource_count = 0;
        this.pending_actions = new ArrayList<>();
    }
    public void set_resource_count(int n) {
        this.resource_count = n;
    }
    public int get_resource_count() {
        return this.resource_count;
    }
    public PImage get_image(){
    	return this.imgs.get(0);
    }
}