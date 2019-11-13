import java.util.*;

public class PercolationBFS extends PercolationDFSFast{
	
	/**
	 * Create constructor for PercolationBFS
	 * @param n size of simulated square grid
	 */
	public PercolationBFS(int n) {
		super(n);
	}
	
	/**
	 * Use BFS approach to mark cells as full
	 * @param row specifies row
	 * @param col specifies column
	 */
	@Override
	protected void dfs(int row, int col) {
		if (! inBounds(row,col)) return;
		if (isFull(row, col) || !isOpen(row, col)) return;
		
		myGrid[row][col] = FULL;

		Queue<Integer> q = new LinkedList<>();
		q.add(row*myGrid.length + col);
		
		int[] rowDelta = {-1,1,0,0};
		int[] colDelta = {0,0,-1,1};
		
		while(q.size() != 0) {
			Integer value = q.remove();
			for(int k = 0; k < rowDelta.length; k++) {
				int nRow = value/myGrid.length + rowDelta[k];
				int nCol = value%myGrid.length + colDelta[k];
				if(inBounds(nRow,nCol) && isOpen(nRow,nCol)) {
					if(!isFull(nRow, nCol)) {
						myGrid[nRow][nCol] = FULL;
						q.add(nRow*myGrid.length + nCol);	
					}
				}
			}
		}
	}
	
}
