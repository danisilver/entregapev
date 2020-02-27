package facil;

public class CruceUniforme implements TipoCruce{

	@Override
	public Cromosoma[] cruce(Cromosoma[] ind, double probCruce) {
		Cromosoma cromosoma1 = ind[0];
		Cromosoma cromosoma2 = ind[1];
		Cromosoma h1, h2;
		Object[] genesP1 = cromosoma1.getGenes();
		Object[] genesP2 = cromosoma2.getGenes();
		h1 = cromosoma1.clonar();
		h2 = cromosoma2.clonar();
		for (int i = 0; i < genesP1.length; i++) {
			double rand = Math.random();
			if(rand < probCruce) {
				h1.setGen(i, genesP2[i]);
				h2.setGen(i, genesP1[i]);
			} else{
				h1.setGen(i, genesP1[i]);
				h2.setGen(i, genesP2[i]);
			}
		}
		return new Cromosoma[] {h1,h2};
	}

}
