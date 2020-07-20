
package core.cruce;


import java.util.Random;

import gen.Cromosoma;
import utils.Utils;
public class CruceMonoPunto implements Cruce{
	
	private Random random = Utils.random;
	
	@Override
	public Cromosoma[] cruce(Cromosoma[] poblacion, double probCruce) {
		int tamPoblacion = poblacion.length;
		Cromosoma ret[] = new Cromosoma[tamPoblacion];
		int tam = 0;
		
		while(tam < tamPoblacion) {
			if(random.nextDouble()<probCruce) {
				Cromosoma[] crossed = crossPair(new Cromosoma[] {poblacion[tam], poblacion[tam+1]});
				ret[tam] = crossed[0];
				ret[tam+1] = crossed[1];
			} else {
				ret[tam] = poblacion[tam];
				ret[tam+1] = poblacion[tam+1];
			}
			tam+=2;
		}
		return ret;
	}

	public Cromosoma[] crossPair(Cromosoma[] ind) {
		double r = random.nextDouble();
		double prob2TakeSingle = 1d/(ind[0].getGenes().length); //numero de puntos de cruce
		double probacc = 0;
		int i=1;//primer pto corte
		while(r < probacc && probacc<=1) {
			probacc+=prob2TakeSingle;
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
