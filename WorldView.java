import java.util.ArrayList;
import java.util.List;

import processing.core.*;

public class WorldView{
	private int view_cols;
	private int view_rows;
	private PApplet applet;
	private WorldModel world;
	private int tile_width;
	private int tile_height;
	private PImage mouse_img;
	private ViewPort viewport;
	private Point mouse_pt;
	private int num_rows;
	private int num_cols;
	private List<PImage> backgroundImage;
	public WorldView(int view_cols, int view_rows, PApplet applet, WorldModel world,
			int tile_width, int tile_height, PImage mouse_img){
		this.viewport = new ViewPort(0, 0, view_cols, view_rows);
	    this.applet = applet;
	    this.mouse_pt = new Point(0, 0);
	    this.world = world;
	    this.tile_width = tile_width;
	    this.tile_height = tile_height;
	    this.num_rows = world.get_num_rows();
	    this.num_cols = world.get_num_cols();
	    this.mouse_img = null;
	    this.backgroundImage = new ArrayList<PImage>();
	    backgroundImage.add(applet.loadImage("grass.bmp"));
	    backgroundImage.add(applet.loadImage("rock.bmp"));
	}
	public void drawBackground(){
        for (int y = 0; y < viewport.getHeight(); y++){
            for (int x = 0; x < viewport.getWidth(); x++){
                //Point wPt= viewport_to_world(new Point(x,y));
                //PImage img = this.world.getBackgroundImage(wPt);
                this.applet.image(backgroundImage.get(0), x*32, y*32);
            }
        }
        this.applet.image(backgroundImage.get(1), 1*32, 3*32);
        this.applet.image(backgroundImage.get(1), 2*32, 4*32);
        this.applet.image(backgroundImage.get(1), 3*32, 5*32);
        this.applet.image(backgroundImage.get(1), 4*32, 6*32);
        this.applet.image(backgroundImage.get(1), 5*32, 6*32);
        this.applet.image(backgroundImage.get(1), 5*32, 1*32);
        this.applet.image(backgroundImage.get(1), 5*32, 2*32);
        this.applet.image(backgroundImage.get(1), 10*32, 8*32);
        this.applet.image(backgroundImage.get(1), 13*32, 14*32);
        this.applet.image(backgroundImage.get(1), 17*32, 3*32);
        this.applet.image(backgroundImage.get(1), 10*32, 4*32);
        this.applet.image(backgroundImage.get(1), 16*32, 5*32);
        this.applet.image(backgroundImage.get(1), 19*32, 6*32);
        this.applet.image(backgroundImage.get(1), 13*32, 6*32);
        this.applet.image(backgroundImage.get(1), 9*32, 1*32);
        this.applet.image(backgroundImage.get(1), 11*32, 2*32);
        this.applet.image(backgroundImage.get(1), 10*32, 8*32);
        this.applet.image(backgroundImage.get(1), 18*32, 14*32);
    }
	public void drawEntities(){
		for(Entity e : this.world.get_entities()){
			if (this.viewport.collidepoint(e.get_position().getx(), e.get_position().getx())){
				Point v_pt = this.world_to_viewport(e.get_position());
				this.applet.image(e.get_image(),
			               v_pt.getx() * this.tile_width,
			            	v_pt.gety() * this.tile_height);
			}
		}
	}
	public void draw_viewport(){
		this.drawBackground();
	    this.drawEntities();
	}
/*
	public void update_view(Point view_delta, PImage mouse_img){
		this.viewport = this.create_shifted_viewport(view_delta,
		this.num_rows, this.num_cols);
		this.mouse_img = mouse_img;
		this.draw_viewport();
		//pygame.display.update();
		this.mouse_move(this.mouse_pt);
	}
	public void update_view_tiles(ArrayList<Point> tiles){
		ArrayList<Integer> rects = new ArrayList<Integer>();
		for (Point tile : tiles){
			if (this.viewport.collidepoint(tile.getx(), tile.gety())){
			Point v_pt = this.world_to_viewport(tile);
			PImage img = this.get_tile_image(v_pt);
			rects.add(this.update_tile(v_pt, img));
			if (this.mouse_pt.getx() == v_pt.getx() && this.mouse_pt.gety() == v_pt.gety()){
				rects.add(this.update_mouse_cursor());
			}
		}
		pygame.display.update(rects);
	}
*/
	public ViewPort update_tile(Point view_tile_pt, PImage surface){
		int abs_x = view_tile_pt.getx() * this.tile_width;
		int abs_y = view_tile_pt.gety() * this.tile_height;
	    this.applet.image(surface, abs_x, abs_y);           //Is this correct?
	    return new ViewPort(abs_x, abs_y, this.tile_width, this.tile_height);
	}
/*
	public PImage get_tile_image(Point view_tile_pt){
		Point pt = this.viewport_to_world(view_tile_pt);
		PImage bgnd = this.world.get_background_image(pt);
		Entity occupant = this.world.get_tile_occupant(pt);
		if (occupant){
			img = pygame.Surface((self.tile_width, self.tile_height));   //What should this do?
		    this.applet.image(bgnd, 0, 0);                            //What should this do?
		    this.applet.image(occupant.get_image(), 0,0);              //What should this do?
		    return img;
		}
		else{
		    return bgnd;
		}
	}
*/
	public Point viewport_to_world(Point pt){
		return new Point(pt.getx() + this.viewport.getLeft(), pt.gety() + this.viewport.getTop());
	}
	public Point world_to_viewport(Point pt){
		return new Point(pt.getx() - this.viewport.getLeft(), pt.gety() - this.viewport.getTop());
	}
	public int clamp(int v, int low, int high){
		int max1;
		int min1;
		if (v > low){
			max1 = v;
		}
		else{
			max1 = low;
		}
		if (max1 < high){
			return max1;
		}
		else{
			return high;
		}
	}
	public ViewPort create_shifted_viewport(ArrayList<Integer> delta, int num_rows, int num_cols){
		int new_x = clamp(this.viewport.getLeft() + delta.get(0), 0, num_cols - this.viewport.getWidth());
		int new_y = clamp(this.viewport.getTop() + delta.get(1), 0, num_rows - this.viewport.getHeight());
		return new ViewPort(new_x, new_y, this.viewport.getWidth(), this.viewport.getHeight());
	}
}
