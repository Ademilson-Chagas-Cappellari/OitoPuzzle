package Puzzle;

public class auxi {
	
	public auxi parent;
	public int[][] matriz;
	public int x, y;
	public int cost;
	public int level;
	
	

	public auxi(int[][] matriz, int x, int y, int x2, int y2, auxi parent, int level) {
		this.parent = parent;
		this.matriz = new int[matriz.length][];
		for (int i = 0; i < matriz.length; i++) {
			this.matriz[i] = matriz[i].clone();
		}
		
		// Troca
			this.matriz[x][y]       = this.matriz[x][y] + this.matriz[x2][y2];
			this.matriz[x2][y2] = this.matriz[x][y] - this.matriz[x2][y2];
			this.matriz[x][y]       = this.matriz[x][y] - this.matriz[x2][y2];
				
			this.cost = Integer.MAX_VALUE;
			this.level = level;
			this.x = x2;
			this.y = y2;
			
		
	}
}
