package ss;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

class TestingGameOfLifeCircularQueueAndBoard {
	GameOfLifeBoard gameBoard;
	final int GRID_SIZE = 20;
	
	@BeforeEach
	public void setUp() {
		gameBoard = new GameOfLifeBoard();
	}

	@Test
    public void testInitializeGrid() {
        gameBoard.initializeGrid();

        int[][] grid = gameBoard.grid; // Accessing the package-private field

        // Check that all elements are either 0 or 1
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                assertTrue(grid[i][j] == 0 || grid[i][j] == 1);
            }
        }
    }
	
	@Test
	public void testCountLiveNeighbors() {
	    // Manually set a pattern with known live neighbors
	    int[][] testGrid = {
	            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
	    };

	    gameBoard.grid = testGrid;

	    // Test countLiveNeighbors for a cell in the center of the pattern
	    int liveNeighbors = gameBoard.countLiveNeighbors(5,5);
	    assertEquals(4, liveNeighbors);
	}

	@Test
    public void testPeek() {
        CircularQueue<String> queue = new CircularQueue<>();

        queue.enqueue("A");
        queue.enqueue("B");

        // Check the front element without dequeuing
        assertEquals("A", queue.peek());
        assertFalse(queue.isEmpty());
    }
	 
	@Test
    public void testFullQueueException() {
        CircularQueue<Integer> queue = new CircularQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);  // Attempt to enqueue into a full queue
    }
	 
	@Test
    public void testClear() {
        CircularQueue<Double> queue = new CircularQueue<>();

        queue.enqueue(1.0);
        queue.enqueue(2.0);

        // Clear the queue
        queue.clear();

        assertTrue(queue.isEmpty());
    }

	
}
