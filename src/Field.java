import java.awt.event.KeyEvent;

public class Field {
	
    private int width;
    private int height;
    
    private VeggieSnake veggieSnake;
    private Soybean soybean;

    public static Field game;

    public Field(int width, int height, VeggieSnake veggieSnake) {
        this.width = width;
        this.height = height;
        this.veggieSnake = veggieSnake;
        game = this;
    }
    
    public static void main(String[] args) throws InterruptedException {
        game = new Field(20, 20, new VeggieSnake(10, 10));
        game.veggieSnake.setDirection(SnakeDirection.RIGHT);
        game.createSoybean();
        game.run();
    }

    public void run() throws InterruptedException {

        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        while (veggieSnake.isAlive()) {
            if (keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();
                //press 'q' to exit the game
                if (event.getKeyChar() == 'q') return;
                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    veggieSnake.setDirection(SnakeDirection.LEFT);               
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    veggieSnake.setDirection(SnakeDirection.RIGHT);                 
                else if (event.getKeyCode() == KeyEvent.VK_UP)
                    veggieSnake.setDirection(SnakeDirection.UP);                
                else if (event.getKeyCode() == KeyEvent.VK_DOWN)
                    veggieSnake.setDirection(SnakeDirection.DOWN);
            }
            veggieSnake.move();
            print();
            sleep();
        }
        System.out.println("GAME OVER!");
    }

    public void print() {
    	int[][] matrix = new int[height][width];
    	
    	for(SnakeSection s : veggieSnake.getSections())
    		matrix[s.getY()][s.getX()] = 1; //snake's body = 1   	
    	matrix[veggieSnake.getY()][veggieSnake.getX()] = 2; //snake's head = 2
    	matrix[soybean.getY()][soybean.getX()] = 3; //soybean = 3
    	
    	StringBuilder sb = new StringBuilder();
    	
    	for(int i = 0; i < height; i++) {
    		for(int j = 0; j < width; j++) {
    			if(matrix[i][j] == 1)
    				sb.append("o");
    			else if(matrix[i][j] == 2)
    				sb.append("O");
    			else if(matrix[i][j] == 3)
    				sb.append("~");
    			else
    				sb.append(".");
    		}
    		sb.append("\n");
    	}   	
    	System.out.println(sb.toString());
    }

    public void eatSoybean() {
        createSoybean();
    }

    public void createSoybean() {
        int x = (int) (Math.random() * width);
        int y = (int) (Math.random() * height);

        soybean = new Soybean(x, y);
    }

	public void sleep() throws InterruptedException {
		int delay = 300;
		
		Thread.sleep(delay);
		
		if(veggieSnake.getSections().size() <= 15) {
			for(int i = 1; i <= 15; i++) {
				delay -= 10;
				Thread.sleep(delay);
			}
		} else {
			Thread.sleep(100);
		}
	}
	
    public Soybean getSoybean() {
        return soybean;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
