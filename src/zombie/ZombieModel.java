package zombie;

import java.awt.Color;
import java.util.Random;

public class ZombieModel {
	
	private int tempx;
	private int tempy;
	private Human human;
	private final Color[][] matrix;
	private final int width;
	private final int height;
	private final int dotSize;
	private Random rand = new Random();
	
	public ZombieModel(int widthArg, int heightArg, int dotSizeArg) {
		width = widthArg;
		height = heightArg;
		dotSize = dotSizeArg;
		matrix = new Color[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				matrix[i][j] = Color.BLACK;
			}
		}
	}

	public Human getHuman() { return human; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getDotSize() { return dotSize; }
	public Color getColor(int x, int y) { return matrix[x][y]; }
	public void setColor(int x, int y, Color color) { matrix[x][y] = color; }
	
	public void initialize() {
		initRocks();
		initRiver();
		initTrees();
		
		human = new Human(this);
	}
	

	
	private void initRiver() {
		int tempx = rand.nextInt(width - 5);
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < height; j++) {
				this.setColor(tempx, j, Color.BLUE);
			}
			tempx++;
		}
	}
	
	private void initTrees() {
		for(int i = 0; i < 40; i++) {
			int tempx = rand.nextInt(width - 2) + 1;		
			int tempy = rand.nextInt(height - 2) + 1;
			boolean qualified = false;
			while(qualified == false) {
				if(this.getColor(tempx, tempy) == Color.BLACK && this.getColor(tempx, tempy+1) == Color.BLACK && this.getColor(tempx, tempy-1) == Color.BLACK && this.getColor(tempx+1, tempy) == Color.BLACK && this.getColor(tempx-1, tempy) == Color.BLACK) {
					qualified = true;
				} else {
					tempx = rand.nextInt(width-2)+1;		
					tempy = rand.nextInt(height-2)+1;
				}
			}
			this.setColor(tempx, tempy, Color.GREEN);
			this.setColor(tempx+1, tempy, Color.GREEN);
			this.setColor(tempx-1, tempy, Color.GREEN);
			this.setColor(tempx, tempy+1, Color.GREEN);
			this.setColor(tempx, tempy-1, Color.GREEN);
		}
	}
	
	public void initRocks() {
		Random rockx= new Random();
		Random rocky= new Random();
		Random diam= new Random();
		for(int i=0; i<6;){
			double rx=rockx.nextInt(width-5);
			double ry=rocky.nextInt(height-5);
			double diameter=diam.nextInt(4)+4;
			boolean boo=true;
			for(int j=0;j<width;j++){
				for(int k=0;k<height;k++){	
					double dist=Math.sqrt((j-rx)*(j-rx) +(k-ry)*(k-ry));
					if((dist<diameter) && (matrix[j][k]==Color.GRAY)){
						boo=false;
					}
				}
			}
			if(boo){
				i++;
				for(int j=0;j<width;j++){
					for(int k=0;k<height;k++){	
						double dist=Math.sqrt((j-rx)*(j-rx) +(k-ry)*(k-ry));
						if(dist<diameter && matrix[j][k]==Color.BLACK && boo){
							matrix[j][k]=Color.GRAY;
						}
					}
				}
			}
		}
	}
}
