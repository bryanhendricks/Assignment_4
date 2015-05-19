import java.util.List;

import processing.core.*;

public class Miners extends Entity{
    protected int resource_limit;
    protected int rate;
    protected int animation_rate;

    protected static int resource_count;
    public Miners(String name, int resource_limit, Point position, int rate,
    		List<PImage> imgs, int animation_rate) {
        super(name, imgs, position);
        this.resource_limit = resource_limit;
        this.rate = rate;
        this.animation_rate = animation_rate;
        this.resource_count = 0;
    }
    public PImage get_image(){
    	return this.imgs.get(current_img);
    }
    public int get_rate(){
        return this.rate;
    }
    public int get_resource_limit(){
        return this.resource_limit;
    }
    public void set_resource_count(int n){
        this.resource_count = n;
    }
    public int get_resource_count(){
        return this.resource_count;
    }
    public int get_animation_rate(){
        return this.animation_rate;
    }
    public String entity_string(){
        return "miner" + this.name + this.point.getx() +
                this.point.gety() + this.resource_limit +
                this.rate + this.animation_rate;
    }

    public Point next_position(WorldModel world, Point dest_pt){
        Point entity_pt = this.get_position();
        int horiz = dest_pt.getx() - entity_pt.getx();
        Point new_pt = new Point(entity_pt.getx() + horiz, entity_pt.gety());
        if (horiz == 0 || world.is_occupied(new_pt)){
            int vert = dest_pt.gety() - entity_pt.gety();
            new_pt = new Point(entity_pt.getx(), entity_pt.gety() + vert);
            if (vert == 0 || world.is_occupied(new_pt)){
                new_pt = new Point(entity_pt.getx(), entity_pt.gety());
            }
        }
        return new_pt;
    }
}