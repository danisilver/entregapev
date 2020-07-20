package core.cruce;

import java.util.Random;

import gen.Cromosoma;
import utils.Utils;

public class CruceContador implements Cruce {
	private Random random = Utils.random;
	private Cruce tc;
	private int numCruces = 0;
	public CruceContador(Cruce tc) { this.tc = tc; }

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
	
	@Override
	public Cromosoma[] crossPair(Cromosoma[] cs) {
		numCruces++; 
		return tc.crossPair(cs);
	}

	public int getNumCruces() { return numCruces; }
	public void setNumCruces(int numCruces) { this.numCruces = numCruces; }

}
