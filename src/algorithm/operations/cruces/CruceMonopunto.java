package algorithm.operations.cruces;
import algorithm.individuos.Individuo;

public class CruceMonopunto extends Cruce {

    public static final String name = "Cruce Monopunto";

	public CruceMonopunto() {
		super.name = name; 
	}

    @Override
    public void cruzar(Individuo individuoPrev, Individuo individuo) {
        int indice = random.nextInt(individuo.getSize() - 1);
        for (int j = 0; j <= indice; j++) {
            individuoPrev.swapGen(j, individuo);
        }
    }

}
