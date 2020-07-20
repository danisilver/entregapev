package core.cruce;

import java.util.*;

import gen.Cromosoma;
import utils.Utils;

public class CrucePropioP5 implements Cruce{//permutaciones solo

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

	@Override
	public Cromosoma[] crossPair(Cromosoma[] ind) {
		Cromosoma h1 = ind[0].clonar();
		Cromosoma h2 = ind[0].clonar();
		
		List<Integer> genesH1 = new ArrayList<>();
		List<Integer> genesH2 = new ArrayList<>();
		fillH(ind[0], ind[1], genesH1);
		fillH(ind[1], ind[0], genesH2);
		
		for (int i = 0; i < ind[0].getNumGenes(); i++) {
			h1.setGen(i, genesH1.get(i));
			h2.setGen(i, genesH2.get(i));
		}
		
		return new Cromosoma[]{h1, h2};
	}

	private void fillH(Cromosoma p1, Cromosoma p2, List<Integer> genesH1) {
		Cromosoma[] ind = new Cromosoma[] {p1,p2};
		int numgenes = ind[0].getNumGenes();
		
		int pos = random.nextInt(numgenes);
		int who = 0;
		Set<Integer> genesH = new LinkedHashSet<>();
		while(genesH.size()<numgenes) {
			Integer g = (Integer)ind[who].getGen((++pos)%numgenes);
			if(genesH.add(g)) who=(who+1)%2;
		}
		
		genesH1.addAll(genesH);

	}

}

//double tot = p1.getPuntuacion()+p2.getPuntuacion();
//int np1 = (int)(p1.getPuntuacion()/tot)*numgenes;
//
//Set<Integer> genesH = new LinkedHashSet<>();
//int pos = random.nextInt(numgenes);
//while(genesH.size()<np1)
//	genesH.add((Integer)ind[0].getGen(++pos%numgenes));
//
//pos = random.nextInt(numgenes);
//while(genesH.size()<numgenes)
//	genesH.add((Integer)ind[1].getGen(++pos%numgenes));