package core.cruce;

import java.util.*;
import java.util.stream.*;

import gen.Cromosoma;
import utils.Utils;

public class CruceOXOP implements Cruce {
	private Random random = Utils.random;
	private int n2take = 4;

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
		//take n positions, mark then in the parent
		//and insert them in the order they where taken
		List<Object> genesP1 = Arrays.asList(ind[0].getGenes());
		List<Object> genesP2 = Arrays.asList(ind[1].getGenes());
		
		if(getN2take()>numgenes/2) setN2take(numgenes/2);
		
		ArrayList<Object> genesc1 = new ArrayList<>(genesP2);
		ArrayList<Object> genesc2 = new ArrayList<>(genesP1);
		
		descendiente(numgenes, genesP1, genesP2, genesc1);
		descendiente(numgenes, genesP2, genesP1, genesc2);
		
		Cromosoma c1 = ind[0].clonar();
		Cromosoma c2 = ind[0].clonar();
		
		for (int i = 0; i < numgenes; i++) {
			c1.setGen(i, genesc1.get(i));
			c2.setGen(i, genesc2.get(i));
		}
		
		return new Cromosoma[] {c1,c2};
	}

	private void descendiente(int numgenes, List<Object> genesP1, List<Object> genesP2, ArrayList<Object> genesc1) {
		List<Integer> range = IntStream.range(0, numgenes)
			    .boxed().collect(Collectors.toList());
		Collections.shuffle(range, random);
		List<Integer> selected = range.subList(0, getN2take());
		Integer[] selarr = new Integer[getN2take()];
		Arrays.sort(selected.toArray(selarr));
		
		List<Integer> genesS = Arrays.asList(selarr)
				.stream().map(g->(Integer)genesP1.get(g))
				.collect(Collectors.toList());
		
		List<Integer> posOnParen = genesS
				.stream().map(g->genesP2.indexOf(g))
				.collect(Collectors.toList());
		
		Collections.sort(posOnParen);
		
		for (int i = 0; i < selarr.length; i++) {
			genesc1.set(posOnParen.get(i), genesS.get(i));
		}
	}

	public int getN2take() {
		return n2take;
	}

	public void setN2take(int n2take) {
		this.n2take = n2take;
	}
}
