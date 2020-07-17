package core.cruce;

import java.util.Random;

import gen.Cromosoma;
import utils.Utils;

public class CruceBLXalpha implements Cruce{
	private Random random = Utils.random;
	private double alpha = 0.5;
	@Override
	public Cromosoma[] cruce(Cromosoma[] poblacion, double probCruce) {//para cromosomas reales solo
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

	private Cromosoma[] crossPair(Cromosoma[] ind) {
		Object[] genesP1 = ind[0].getGenes();
		Object[] genesP2 = ind[1].getGenes();
		Cromosoma h1 = ind[0].clonar();
		Cromosoma h2 = ind[1].clonar();
		
		for (int i = 0; i < genesP1.length; i++) {
			Double cmax = Math.max((Double)genesP1[i], (Double)genesP2[i]);
			Double cmin = Math.min((Double)genesP1[i], (Double)genesP2[i]);
			Double interval = cmax - cmin;
			Double leftB = cmin - interval*alpha;
			Double rightB = cmax + interval*alpha;
			Double gh1 = leftB + (rightB - leftB) * random.nextDouble();
			Double gh2 = leftB + (rightB - leftB) * random.nextDouble();
			h1.setGen(i, gh1);
			h2.setGen(i, gh2);
		}
		
		return new Cromosoma[] {h1, h2};
	}

	public double getAlpha() {
		return alpha;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

}
