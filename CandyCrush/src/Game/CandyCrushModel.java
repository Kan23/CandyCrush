package Game;
class CandyCrushModel{
	private final int sizeGrid = 8; 
	
	int grid[][] = new int[sizeGrid][sizeGrid];
	Integer score = 0;
	boolean marked[][] = new boolean[sizeGrid][sizeGrid];
    
	public int getGridCase(int i, int j)
	{
		if(i >= sizeGrid || j >= sizeGrid)
			return -1;
		return grid[i][j];
	}
	
	public boolean getMarkedCase(int i, int j)
	{
		if(i >= sizeGrid || j >= sizeGrid)
			return false;
		return marked[i][j];
	}
	
	public void setGridCase(int i, int j, int value)
	{
		if(i >= sizeGrid || j >= sizeGrid)
			return;
		this.grid[i][j] = value;
	}
	
	public void setMarkedCase(int i, int j, boolean value)
	{
		if(i >= sizeGrid || j >= sizeGrid)
			return;
		this.marked[i][j] = value;
	}
	
	void swap(int x1, int y1, int x2, int y2) {
		if(x1 >= sizeGrid || x2 >= sizeGrid || y1 >= sizeGrid || y2 >= sizeGrid)
			return;
        int tmp = grid[x1][y1];
        grid[x1][y1] = grid[x2][y2];
        grid[x2][y2] = tmp;
    }

	public void addScore() {
		score++;
	}

	public Integer getScore() {
		return score;
	}
}
