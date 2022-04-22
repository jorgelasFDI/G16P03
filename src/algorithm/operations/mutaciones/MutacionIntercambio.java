package algorithm.operations.mutaciones;

import algorithm.individuos.Individuo;

public class MutacionIntercambio extends Mutacion {
	
	public static final String name = "Mutaci�n por intercambio";

	public MutacionIntercambio() {
		super.name = name; 
	}

	@Override
	public void mutar(Individuo individuo) {
				
		//2� Seleccionamos los 2 puntos al azar
		int punto1 = random.nextInt(individuo.getSize());
		int punto2 = random.nextInt(individuo.getSize());
					
		//3� Se intercambian los valores de las posiciones
		individuo.swapBit(punto1, punto2);
	}

}
