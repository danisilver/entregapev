package core.cruce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

import gen.Cromosoma;
import utils.Utils;

public class CruceOXPP implements Cruce{
	

	private int numGens2Xchng = 2;
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

	private Cromosoma[] crossPair(Cromosoma[] ind) {
		int numgenes = ind[0].getGenes().length;
		
		ArrayList<Integer> genesc1 = new ArrayList<>();
		ArrayList<Integer> genesc2 = new ArrayList<>();
		for (int i = 0; i < numgenes; i++) {
			genesc1.add(-1);
			genesc2.add(-1);
		}
		
		HashSet<Integer> indExchange = new HashSet<>();
		while(indExchange.size()<getNumGens2Xchng()) {
			indExchange.add(random.nextInt(numgenes));
		}
		
		for(Integer i4e:indExchange) {
			genesc1.set(i4e, (Integer)ind[1].getGen(i4e));
			genesc2.set(i4e, (Integer)ind[0].getGen(i4e));
		}
		
		int sig = (Collections.max(indExchange)+1)%numgenes;
		
		fillWithParentGens(ind[0], genesc1, sig);
		fillWithParentGens(ind[1], genesc2, sig);
		
		Cromosoma c1 = ind[0].clonar();
		Cromosoma c2 = ind[0].clonar();
		
		for (int i = 0; i < numgenes; i++) {
			c1.setGen(i, genesc1.get(i));
			c2.setGen(i, genesc2.get(i));
		}
		
		return new Cromosoma[] {c1,c2};
	}

	private void fillWithParentGens(Cromosoma parent, ArrayList<Integer> genesc1, int sig) {
		while(genesc1.contains(-1)) {
			Integer gen = (Integer)parent.getGen(sig);
			if(!genesc1.contains(gen)) 
				genesc1.set(sig, gen);
			sig=(sig+1)%genesc1.size();
		}
	}

	public int getNumGens2Xchng() {
		return numGens2Xchng;
	}

	public void setNumGens2Xchng(int numGens2Xchng) {
		this.numGens2Xchng = numGens2Xchng;
	}

}
