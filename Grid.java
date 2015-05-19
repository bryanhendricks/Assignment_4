public class Grid {
    protected int width;
    protected int height;
    private Entity[][] cells;
    public Grid(int width, int height, Entity occupancy) {
        this.width = width;
        this.height = height;
        this.cells = new Entity[height][width];
        int row = 0;
        int col = 0;
        while(row < this.height){
        	while(col < this.width){
        		this.cells[row][col] = occupancy;
        		col++;
        	}
        	row++;
        }
    }
    public void set_cell(Point point, Entity entity) {
        this.cells[point.getx()][point.gety()] = entity;
    }
    public Entity get_cell(Point point) {
        return this.cells[point.getx()][point.gety()];
    }

    int EMPTY=0;
    int GATHERER=1;
    int GENERATOR=2;
    int RESOURCE=3;

}