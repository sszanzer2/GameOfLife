package ss;

public interface GridInterface {
	void initializeGrid();
    void printGrid();
    void updateGrid();
    int countLiveNeighbors(int row, int col);
}
