
public class PercolationDFSFast extends PercolationDFS {

	/**
	 * Create constructor for PercolationDFSFast
	 * @param n size of simulated square grid
	 */
	public PercolationDFSFast(int n) {
		super(n);
	}

	/**
	 * Calls dfs if newly opened cell is in the top row 
	 * or adjacent to an already full cell
	 */
	@Override
	protected void updateOnOpen(int row, int col) {
		if(row == 0 || inBounds(row-1,col) && isFull(row-1,col) || inBounds(row+1,col) && isFull(row+1,col) || 
				inBounds(row,col-1) && isFull(row,col-1) || inBounds(row,col+1) && isFull(row,col+1)) {
			dfs(row,col);
			return;
		}
	}
}
