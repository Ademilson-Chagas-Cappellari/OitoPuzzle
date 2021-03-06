package Puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


public class Principal {
	
	public int dimensao = 3;
	
	//Inferior, esquerda, superior, direita.
	int[] linha = {1, 0, -1, 0};
	int[] coluna = {0, -1, 0, 1};
	
	public int calculador(int[][] inicial, int[][] meta) {
		int cont = 0;
		int n = inicial.length;
		for(int i =0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(inicial[i][j] != 0 && inicial[i][j] != meta[i][j]) {
					cont++;
				}
			}
		}
		return cont;
	}
	
	
	//Imprimir a matriz
	public void imprMatriz(int[][] matriz) {

		int cont = 1;
		System.out.println(cont++);
		
		for(int i = 0; i < matriz.length; i++) {
			for(int j = 0; j < matriz.length; j++) {
				cont = 0;
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println();
		}
	}


	public boolean isSafe(int x, int y) {
		return (x >= 0 && x < dimensao && y >= 0 && y < dimensao);
	}
	
	public void imprCaminho(auxi root) {
		if(root == null) {
			return;
		}
		imprCaminho(root.parent);
		imprMatriz(root.matriz);
		System.out.println();
	}

	//? solucion?vel 
	public boolean soluci(int[][] matriz) {
		int conta = 0;
		List<Integer> array = new ArrayList<Integer>();
		for(int i = 0; i < matriz.length; i++) {
			for(int j =0; j <matriz.length; j++) {
				array.add(matriz[i][j]);
			}
		}
		
		// Criando outro Array
		Integer[] outroArray = new Integer[array.size()];
		array.toArray(outroArray);
		for(int i = 0; i < outroArray.length - 1; i++) {
			for(int j = i +1; j < outroArray.length; j++) {
				if(outroArray[i] != 0 && outroArray[j] != 0 && outroArray[i] > outroArray[j]) {
					conta++;
				}
			}
		}
		return conta % 2 == 0;
	}
	
	//Resolver o problema
	public void resolve(int[][] inicial, int[][] meta, int x, int y) {
		PriorityQueue<auxi> pq = new PriorityQueue<auxi>((a, b) -> (a.cost + a.level) - (b.cost + b.level));
		auxi root = new auxi(inicial, x, y, x, y, null, y);
		root.cost = calculador(inicial, meta);
		pq.add(root);
		
		while(!pq.isEmpty()) {
			auxi min = pq.poll();
			if(min.cost == 0) {
				imprCaminho(min);
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				if(isSafe(min.x + linha[i], min.y + coluna[i])) {
					auxi child = new auxi(min.matriz, min.x, min.y, min.x + linha[i], min.y + coluna[i], min, i);
					child.cost = calculador(child.matriz, meta);
					pq.add(child);
				}
			}
			
		}
	}
	
	// Passando uma matriz inicial a ser resolvido e a meta a ser alcan?ada  
	public static void main(String args[]) {
		int[][] inicial = { {1, 8, 2}, {0, 4, 3}, {7, 6, 5} };
		int[][] meta    = { {1, 2, 3}, {4, 5, 6}, {7, 8, 0} };
		
		//Teste se ? possivel de ser resolvido
		int x = 1, y = 0;
		Principal principal = new Principal();
		if (principal.soluci(inicial)) {
			principal.resolve(inicial, meta, x, y);
		}else {
			System.out.println("A matriz inicial n?o pode ser resolvida");
		}
	}
		
	
}







