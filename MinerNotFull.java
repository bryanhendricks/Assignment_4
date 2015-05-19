import java.util.ArrayList;
import java.util.List;
import processing.core.*;
import java.lang.Math;

public class MinerNotFull extends Miners{
    private ArrayList<Integer> pending_actions = new ArrayList<Integer>();   //Type?
    public MinerNotFull(String name, int resource_limit, Point position,
                     int rate, List<PImage> imgs, int animation_rate){
        super(name, resource_limit, position, rate, imgs, animation_rate);
        this.pending_actions = new ArrayList<Integer>();
    }
    public int get_resource_distance() {
        //return this.resource_distance;         //Should this be 1?
        return 1;
    }
/*
    public boolean miner_to_ore(WorldModel world, Ore ore){
        Point entity_pt = this.get_position();
        if (ore != null) {
            return false;
        }
        Point ore_pt = ore.get_position();
        if (Math.sqrt(Math.pow((entity_pt.getx() - ore_pt.getx()), 2) +
        		Math.pow((entity_pt.gety() - ore_pt.gety()), 2)) == 1){
            this.set_resource_count(1 + this.get_resource_count());
            ore.remove_entity(world);
            return true;
        }
        else{
            Point new_pt = this.next_position(world, ore_pt);
            return false;
        }
    }
*/
}