package core.cruce;

import java.util.Random;

import gen.Cromosoma;
import utils.Utils;

public class CruceUniforme implements Cruce{

	private Random random = Utils.random;
	private double probXchngGen = 0.4;
	
	@Override
	public Cromosoma[] cruce(Cromosoma[] poblacion, double probCruce) {
		int tamPoblacion = poblacion.length;
		Cromosoma ret[] = new Cromosoma[tamPoblacion];
		int tam = 0;
		
		while(tam < tamPoblacion) {
			if(random.nextDouble()<probCruce) {
				Cromosoma[] crossed = crossPair(new Cromosoma[] { poblacion[tam], poblacion[tam+1]});
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
		Cromosoma cromosoma1 = ind[0];
		Cromosoma cromosoma2 = ind[1];
		Cromosoma h1, h2;
		Object[] genesP1 = cromosoma1.getGenes();
		Object[] genesP2 = cromosoma2.getGenes();
		h1 = cromosoma1.clonar();
		h2 = cromosoma2.clonar();
		for (int i = 0; i < genesP1.length; i++) {//XYXYXXY
			double probXchng = random.nextDouble();
			if(probXchng < getProbXchngGen()) {
				h1.setGen(i, genesP2[i]);
				h2.setGen(i, genesP1[i]);
			} else{
				h1.setGen(i, genesP1[i]);
				h2.setGen(i, genesP2[i]);
			}
		}
		return new Cromosoma[] {h1,h2};
	}

	public double getProbXchngGen() {
		return probXchngGen;
	}

	public void setProbXchngGen(double probXchngGen) {
		this.probXchngGen = probXchngGen;
	}

}
