package ss;

public class GameOfLifeBoard implements GridInterface {
	//Made it final so that if you wanted to change the size of the board you just have to change it once here
    private static final int GRID_SIZE = 30;
    protected int[][] grid;
    private CircularQueue<Cell> updateQueue;

    public GameOfLifeBoard() {
        grid = new int[GRID_SIZE][GRID_SIZE];
        initializeGrid();
        updateQueue = new CircularQueue<>();
    }

    @Override
    public void initializeGrid() {
        // Initialize the grid with random values (0 or 1)
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = Math.random() < 0.5 ? 0 : 1;
            }
        }
    }

    @Override
    public void printGrid() {
        // Print the visual representation of the grid
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                System.out.print(grid[i][j] == 0 ? " " : "█");
            }
            System.out.println();
        }
        System.out.println("---------------------");
    }

    // Method to update the grid based on the Game of Life rules
    public void updateGrid() {
        updateLiveCells();
        updateDeadCells();
        applyUpdates();
    }

    // Method to update live cells based on rules
    private void updateLiveCells() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                int liveNeighbors = countLiveNeighbors(i, j);
                
                // Under-population or Over-population: queue the cell for update
                if (grid[i][j] == 1 && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    queueCellForUpdate(i, j, 0); // Change the state to dead
                }
            }
        }
    }

    // Method to update dead cells based on rules
    private void updateDeadCells() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                int liveNeighbors = countLiveNeighbors(i, j);

                // Reproduction: queue the cell for update
                if (grid[i][j] == 0 && liveNeighbors == 3) {
                    queueCellForUpdate(i, j, 1); // Change the state to alive
                }
            }
        }
    }

    // Method to queue a cell for update and update its state
    private void queueCellForUpdate(int row, int col, int state) {
        grid[row][col] = state;
        updateQueue.enqueue(new Cell(row, col, state));
    }

    // Method to apply updates to the grid based on the queued cells
    private void applyUpdates() {
        while (!updateQueue.isEmpty()) {
            Cell cell = updateQueue.dequeue();
            grid[cell.row][cell.col] = cell.state;
        }
    }

    // Method to count live neighbors for a given cell
    @Override
    public int countLiveNeighbors(int row, int col) {
        int count = 0;
        //start with -1 in the loops to consider the cells in the neighborhood surrounding the given cell. The loop runs for i and j
        //values from -1 to 1, creating a 3x3 neighborhood centered around the given cell. 
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int neighborRow = (row + i + GRID_SIZE) % GRID_SIZE;
                int neighborCol = (col + j + GRID_SIZE) % GRID_SIZE;

                // Check for live neighbors, excluding the center cell
                if (!(i == 0 && j == 0) && grid[neighborRow][neighborCol] == 1) {
                    count++;
                }
            }
        }
        return count;
    }
}
