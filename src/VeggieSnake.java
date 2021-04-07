import java.util.ArrayList;
import java.util.List;

public class VeggieSnake {
	
	 private List<SnakeSection> sections;	 
	 private boolean isAlive = true;
	 private SnakeDirection direction;
	 
	 //snake's head has index(0)
	 public VeggieSnake(int x, int y) {
		 sections = new ArrayList<>();
		 SnakeSection head = new SnakeSection(x, y);
		 sections.add(head);
		 sections.add(new SnakeSection(x - 1, y));
		 sections.add(new SnakeSection(x - 2, y));
	 }
	 
	 public void move() {
		
		if(isAlive()) {
		     if(direction == SnakeDirection.UP)
				 move(0, -1);
			 else if(direction == SnakeDirection.RIGHT)
				 move(1, 0);
			 else if(direction == SnakeDirection.DOWN)
				 move(0, 1);
			 else if(direction == SnakeDirection.LEFT)
				 move(-1, 0);	
		}
	 }
	 
	 public void move(int a, int b) {
		 SnakeSection new_section = new SnakeSection(sections.get(0).getX() + a, sections.get(0).getY() + b);
		 
		 checkBorders(new_section);
		 checkBody(new_section);

		if(Field.game.getSoybean().getX() == new_section.getX() && Field.game.getSoybean().getY() == new_section.getY()) {
				 Field.game.eatSoybean();
				 sections.add(0, new_section);
			 } else {
				 sections.add(0, new_section);
				 sections.remove(sections.size() - 1);
			 }
	 }
	 
	 public void checkBorders(SnakeSection head) {
		 if(head.getX() >= Field.game.getWidth() || head.getX() < 0 || head.getY() >= Field.game.getHeight() || head.getY() < 0)
			 isAlive = false;
	 }
	 
	 public void checkBody(SnakeSection head) {
		 if(sections.contains(head))
			 isAlive = false;
	 }
	 
	 public int getX() {		 
		return sections.get(0).getX();		 
	 }
	 
	 public int getY() {
		 return sections.get(0).getY();
	 }
	 
	 
	public SnakeDirection getDirection() {
		return direction;
	}
	
	public void setDirection(SnakeDirection direction) {
		this.direction = direction;
	}
	
	public List<SnakeSection> getSections() {
		return sections;
	}
	
	public boolean isAlive() {
		return isAlive;
	}
}
