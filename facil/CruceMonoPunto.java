
package facil;

public class CruceMonoPunto implements TipoCruce{
	@Override
	public Cromosoma[] cruce(Cromosoma[] ind, double probCruce) {
		double cruce = Math.random();
		if(cruce >= probCruce) return ind;
		double r = Math.random();
		double prob = 1d/(ind[0].getGenes().length); //numero de puntos de cruce
		double probacc = 0;
		int i=1;//primer pto corte
		while(r < probacc && probacc<=1) {
			probacc+=prob;
			i++;
		}
		
		Cromosoma h1 = ind[0].clonar();
		Cromosoma h2 = ind[0].clonar();
		for (int j = 0; j < ind[0].getGenes().length; j++) { //XXXYYY
			if(j<i) {
				h1.setGen(j, ind[1].getGen(j));
				h2.setGen(j, ind[0].getGen(j));
			} else {
				h1.setGen(j, ind[0].getGen(j));
				h2.setGen(j, ind[1].getGen(j));
			}
		}

		return new Cromosoma[]{h1, h2};
	}

}
