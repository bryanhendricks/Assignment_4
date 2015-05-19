import java.util.ArrayList;
import java.util.List;

import processing.core.*;

public class Main extends PApplet {
    private List<PImage> minerimgs;
    private int current_image;
    
    private WorldModel worldEnt;
    private WorldView worldView;
    private WorldModel worldBG;
    private WorldView worldViewBG;
    private Entity background;
    private List<PImage> grass;
    private PImage mouseImg;
    private MinerNotFull miner1;
    private long next_time1;
    private long next_time2;
    private int newX;
    private List<PImage> blacksmith;
    private Blacksmith blacksmith1;
    
    

    public void setup(){
    	next_time1 = System.currentTimeMillis() + 200;
    	next_time2 = System.currentTimeMillis() + 1000;
    	
    	newX = 2;
    	
    	
        size(640,480);
        mouseImg = loadImage("none.bmp");
        background = new Entity("background", grass, new Point(1, 1));
        worldEnt = new WorldModel(15, 20, background);
        worldView = new WorldView(20, 15, this, worldEnt, 32, 32, mouseImg);
        worldBG = new WorldModel(15, 20, background);
        worldViewBG = new WorldView(20, 15, this, worldBG, 32, 32, mouseImg);
        
        
        
    	
    	
    	
    	
        minerimgs = new ArrayList<PImage>();
        minerimgs.add(loadImage("miner1.bmp"));
        minerimgs.add(loadImage("miner2.bmp"));
        minerimgs.add(loadImage("miner3.bmp"));
        minerimgs.add(loadImage("miner4.bmp"));
        minerimgs.add(loadImage("miner5.bmp"));
        miner1 = new MinerNotFull("miner1", 2, new Point(2, 2), 1000, minerimgs, 200);
        worldEnt.add_entity(miner1);
        
        grass = new ArrayList<PImage>();
        grass.add(loadImage("grass.bmp"));
        
        blacksmith = new ArrayList<PImage>();
        blacksmith.add(loadImage("blacksmith.bmp"));
        blacksmith1 = new Blacksmith("blacksmith1", new Point(13, 13), blacksmith, 2, 200, 1);
        worldEnt.add_entity(blacksmith1);
    }
    
    public void keyPressed() {
        switch (key){
            case 'w':
                
                break;
            case 'a':
                
                break;
            case 's':
                
                break;
            case 'd':
                
                break;
            default:
                break;
        }
    }

    public void draw(){
    	long time = System.currentTimeMillis();
        if (time >= next_time1) {
            miner1.next_image();
            next_time1 = time + 200;
        }

        if (time >= next_time2) {
            next_time2 = time + 800;
            newX++;
            worldEnt.move_entity(miner1, new Point(newX, 2));
        }
    	
    	worldViewBG.drawBackground();
    	worldEnt.draw_world(this);
        
        
        
    }
    
    public static void main(String[] args) {
        PApplet.main("Main");
    }
}