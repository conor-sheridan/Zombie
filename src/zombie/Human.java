package zombie;

import java.awt.Color;
import java.util.Random;

public class Human {
	private ZombieModel model;
	private int x;
	private int y;
	private Color prevColor = Color.BLACK;
	private Direction dir;
	private Random ram = new Random();
	
	public Human(ZombieModel modelAvg) {
		model = modelAvg;
		x = ram.nextInt(model.getWidth());
		y = ram.nextInt(model.getHeight());
		boolean qualified = false;
		while(qualified == false) {
			if(model.getColor(x, y) == Color.BLACK) {
				qualified = true;
			} else {
				x = ram.nextInt(model.getWidth());
				y = ram.nextInt(model.getHeight());
			}
		}
		model.setColor(x,y,Color.WHITE);
		dir = dir.NORTH;
		//randomly generate location on canvas
		//until location is qualified
		//draw human on location
		//initialize dir
	}
	public Direction getDirection() { return dir; }
	public void setDirection(Direction dirAvg) {
		dir = dirAvg;
	}
	public void update() {
		if(dir == dir.NORTH) {
			if(y != 0 && (model.getColor(x, y-1) == Color.BLACK)) {
				model.setColor(x, y, prevColor);
				prevColor = model.getColor(x, y-1);
				model.setColor(x, y-1, Color.WHITE);
				y--;
			}	
		} else if(dir == dir.EAST) {
			if(x != model.getWidth() - 1 && (model.getColor(x+1, y) == Color.BLACK)) {
				model.setColor(x, y, prevColor);
				prevColor = model.getColor(x+1, y);
				model.setColor(x+1, y, Color.WHITE);
				x++;
			}
		} else if(dir == dir.SOUTH) {
			if(y != model.getHeight() - 1 && (model.getColor(x, y+1) == Color.BLACK)) {
				model.setColor(x, y, prevColor);
				prevColor = model.getColor(x, y+1);
				model.setColor(x, y+1, Color.WHITE);
				y++;
			}
		} else {
			if(x != 0 && (model.getColor(x-1, y) == Color.BLACK)) {
				model.setColor(x, y, prevColor);
				prevColor = model.getColor(x-1, y);
				model.setColor(x-1, y, Color.WHITE);
				x--;
			}
		}
		
		//update newx and newy based on dir (use switch)
		//check whether the new location is on the canvas and is qualified
		//If yes, move the human and update the human's location
	}
	
}


