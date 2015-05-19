import java.util.ArrayList;
import java.lang.Math;
import processing.core.*;

public class WorldModel{
	private int num_rows;
	private int num_cols;
	private Grid background;
	private Grid occupancy;
	private ArrayList<Entity> entities;
	public WorldModel(int num_rows, int num_cols, Entity background){
		this.background = new Grid(num_rows, num_cols, background);
		this.num_rows = num_rows;
		this.num_cols = num_cols;
		this.occupancy = new Grid(num_rows, num_cols, null);
		this.entities = new ArrayList<Entity>();
		//this.action_queue = OrderedList();
	}
	public void draw_world(PApplet applet){
		for(Entity currentEntity : entities){
			applet.image(currentEntity.get_image(), currentEntity.get_position().getx()*32,
					currentEntity.get_position().gety()*32);
		}
	}
	public ArrayList<Entity> get_entities(){
		return this.entities;
	}
	public int get_num_rows(){
		return this.num_rows;
	}
	public int get_num_cols(){
		return this.num_cols;
	}
	public boolean within_bounds(Point pt){
		return (pt.getx() >= 0 && pt.getx() < this.num_cols &&
				pt.gety() >= 0 && pt.gety() < this.num_rows);
	}
	public boolean is_occupied(Point pt){
		return (this.within_bounds(pt) &&
				occupancy.get_cell(pt) != null);
	}
	public double distance_sq(Point p1, Point p2){
		return Math.sqrt(Math.pow(p1.getx() - p2.getx(), 2) + Math.pow(p1.gety() - p2.gety(), 2));
	}
	public Entity find_nearest(Point pt, Class type){
        ArrayList<Entity> entList = new ArrayList<Entity>();
        ArrayList<Double> distList = new ArrayList<Double>();
        for (Entity e : this.entities){
            if (type.isInstance(e)) {
                entList.add(e);
                distList.add(distance_sq(pt, e.get_position()));
            }
        }
        return nearest_entity(entList, distList);
	}
	public Entity nearest_entity(ArrayList<Entity> entList, ArrayList<Double> distList){
		if (entList.size() > 0){
			int i = 0;
			double nearestDist = distList.get(i);
			Entity nearestEnt = entList.get(i);
			while(i < distList.size()){
				double newDist = distList.get(i);
				if (newDist < nearestDist){
					nearestDist = newDist;
					nearestEnt = entList.get(i);
				}
				i++;
			}
			return nearestEnt;
		}
		return null;
	}
	public void add_entity(Entity entity){
		Point pt = entity.get_position();
		if(this.within_bounds(pt)){
			Entity old_entity = occupancy.get_cell(pt);
			if (old_entity != null){
				old_entity.clear_pending_actions();
			}
			occupancy.set_cell(pt, entity);
			this.entities.add(entity);
		}
	}
	public ArrayList<Point> move_entity(Entity entity, Point pt){
		ArrayList<Point> tiles = new ArrayList<Point>();
		if(this.within_bounds(pt)){
			Point old_pt = entity.get_position();
			this.occupancy.set_cell(old_pt, null);
			tiles.add(old_pt);
			this.occupancy.set_cell(pt, entity);
			tiles.add(pt);
			entity.set_position(pt);
		}
		return tiles;
	}
	public void remove_entity(Entity entity){
		this.remove_entity_at(entity.get_position());
	}
	public void remove_entity_at(Point pt){
		if (this.within_bounds(pt) && this.occupancy.get_cell(pt) != null){
			Entity entity = this.occupancy.get_cell(pt);
			entity.set_position(new Point(-1, -1));
			this.entities.remove(entity);
			this.occupancy.set_cell(pt, null);
		}
	}
/*
	public void schedule_action(Object action, long time){
		this.action_queue.insert(action, time);
	}
	public void unschedule_action(Object action){
		this.action_queue.remove(action);
	}
	public ArrayList<Point> update_on_time(long ticks){
		ArrayList<Point> tiles = new ArrayList<Point>();
		Type next = self.action_queue.head();
		while (next && next.ord < ticks){
			this.action_queue.pop();
			tiles.extend(next.item(ticks));
			next = this.action_queue.head();
		}
		return tiles;
	}
*/
	
	public PImage get_background_image(Point pt){
		if (this.within_bounds(pt)){
			return this.occupancy.get_cell(pt).get_image();      //Is this correct?
		}
		return null;
	}
	public Entity get_background(Point pt){
		if (this.within_bounds(pt)){
			return this.occupancy.get_cell(pt);
		}
		return null;
	}
	public void set_background(Point pt, Entity bgnd){
		if (this.within_bounds(pt)){
			this.occupancy.set_cell(pt, bgnd);
		}
	}
	public Entity get_tile_occupant(Point pt){
		if (this.within_bounds(pt)){
			return this.occupancy.get_cell(pt);
		}
		return null;
	}
}