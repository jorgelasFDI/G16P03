package algorithm.operations.mutaciones;

import algorithm.individuos.Individuo;

public class MutacionInversion extends Mutacion {
	
	public static final String name = "Mutaci�n por inversi�n";

	public MutacionInversion() {
		super.name = name; 
	}

	@Override
	public void mutar(Individuo individuo) {

		Individuo prev = new Individuo(individuo);
		
		int punto1 = random.nextInt(individuo.getSize());
		int punto2 = random.nextInt(individuo.getSize());
		int ini, fin;
		
		if(punto1 < punto2) {ini = punto1;    fin = punto2;}
		
		else {ini = punto2;    fin = punto1;}
		
		//2� Se recorren desde el primer punto del individuo hasta el segundo punto
		int m = 0;
		for(int j = ini; j <= fin; j++) {
			//3� Se invierte cada elemento
			individuo.setGen(j, prev.getGen(fin - m));
			m++;
		}
	}

}
