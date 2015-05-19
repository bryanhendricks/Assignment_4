public class ViewPort{
	private int minX;
	private int minY;
	private int maxX;
	private int maxY;
	public ViewPort(int minX, int minY, int maxX, int maxY){
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
	}
	public int getHeight(){
		return maxY - minY;
	}
	public int getWidth(){
		return maxX - minX;
	}
	public int getLeft(){
		return minX;
	}
	public int getTop(){
		return minY;
	}
	public boolean collidepoint(int x, int y){
		//What does this method do?
		return true;
	}
}