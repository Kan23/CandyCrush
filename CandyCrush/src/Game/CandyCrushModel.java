package Game;
class CandyCrushModel{
	int grid[][] = new int[8][8];
	Integer score = 0;
	boolean marked[][] = new boolean[8][8];
    
	public int getGridCase(int i, int j)
	{
		if(i >= 8 || j >= 8)
			return -1;
		return grid[i][j];
	}
	
	public boolean getMarkedCase(int i, int j)
	{
		if(i >= 8 || j >= 8)
			return false;
		return marked[i][j];
	}
	
	public void setGridCase(int i, int j, int value)
	{
		this.grid[i][j] = value;
	}
	
	public void setMarkedCase(int i, int j, boolean value)
	{
		this.marked[i][j] = value;
	}
	
	void swap(int x1, int y1, int x2, int y2) {
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
