package core.cruce;

import java.util.Random;

import gen.Cromosoma;
import utils.Utils;
public class CruceAritmetico implements Cruce{

	private double alfa = 0.6;
	private Random random = Utils.random;
	
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

	public Cromosoma[] crossPair(Cromosoma[] ind) {
		Object[] genesP1 = ind[0].getGenes();
		Object[] genesP2 = ind[1].getGenes();
		Cromosoma h1 = ind[0].clonar();
		Cromosoma h2 = ind[1].clonar();
		for (int i = 0; i < genesP1.length; i++) {
			Double g1 = getAlfa()*((Double)genesP1[i])+(1-getAlfa())*((Double)genesP2[i]); //a*p1i + (1-a)*p2i
			Double g2 = getAlfa()*((Double)genesP2[i])+(1-getAlfa())*((Double)genesP1[i]); //a*p2i + (1-a)*p1i
			h1.setGen(i, g1);
			h2.setGen(i, g2);
		}
		
		return new Cromosoma[] {h1, h2};
	}

	public double getAlfa() {
		return alfa;
	}

	public void setAlfa(double alfa) {
		this.alfa = alfa;
	}

}
