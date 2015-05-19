import java.util.ArrayList;
import java.util.List;
import processing.core.*;
import java.lang.Math;

public class MinerFull extends Miners{
    private ArrayList<Integer> pending_actions = new ArrayList<Integer>();   //Type?
    public MinerFull(String name, int resource_limit, Point position,
                     int rate, List<PImage> imgs, int animation_rate){
        super(name, resource_limit, position, rate, imgs, animation_rate);
        this.pending_actions = new ArrayList<Integer>();
    }
/*
    public MinerNotFull try_transform_miner_full(WorldModel world) {
        MinerNotFull new_entity = new MinerNotFull(
                this.get_name(), this.get_resource_limit(),
                this.get_position(), this.get_rate(),
                this.get_images(), this.get_animation_rate());
        return new_entity;
    }

    public boolean miner_to_smith(WorldModel world, Blacksmith smith) {
        Point entity_pt = this.get_position();
        if (smith != null){
            return false;
        }
        Point smith_pt = smith.get_position();
        if (Math.sqrt(Math.pow((entity_pt.getx() - smith_pt.getx()), 2) +
        		Math.pow((entity_pt.gety() - smith_pt.gety()), 2)) == 1){
            smith.set_resource_count(smith.get_resource_count() +
                    this.get_resource_count());
            this.set_resource_count(0);
            return true;
        }
        else {
            Point new_pt = this.next_position(world, smith_pt);
            return false;
        }
    }
*/
}