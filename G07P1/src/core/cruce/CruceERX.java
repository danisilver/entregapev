package core.cruce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import gen.Cromosoma;
import utils.Utils;

public class CruceERX implements Cruce{

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
		Integer[] genesp1 = (Integer[])ind[0].getGenes();
		Integer[] genesp2 = (Integer[])ind[1].getGenes();
		
		int numgenes = ind[0].getGenes().length;
		
		Integer[][] adjMatrix = new Integer[numgenes][];
		
		for (int i = 0; i < numgenes; i++) {
			
			int j = 0;
			while(genesp1[j]!=(1+i)) j++;
			
			HashSet<Integer> setI = new HashSet<>();
			int anterior  = (j+numgenes-1)%numgenes;
			int siguiente = (j+numgenes+1)%numgenes;
			setI.add(genesp1[anterior]);
			setI.add(genesp1[siguiente]);
			
			j = 0;
			while(genesp2[j]!=(1+i)) j++;
			
			anterior  = (j+numgenes-1)%numgenes;
			siguiente = (j+numgenes+1)%numgenes;
			
			setI.add(genesp2[anterior]);
			setI.add(genesp2[siguiente]);
			adjMatrix[i] = setI.toArray(new Integer[1]);
		}
		
		ArrayList<Integer> hijo1 = new ArrayList<>(); 
		ArrayList<Integer> hijo2 = new ArrayList<>();
		
		int sel = random.nextInt(2);
		Integer firstGenFirstChild = (Integer)ind[sel].getGen(0);
		Integer firstGenSecondChild = (Integer)ind[(sel+1)%2].getGen(0);
		fillGenesAdjacentes(numgenes, adjMatrix, hijo1, firstGenFirstChild);
		fillGenesAdjacentes(numgenes, adjMatrix, hijo2, firstGenSecondChild);
		
		Cromosoma c1 = ind[0].clonar();
		Cromosoma c2 = ind[1].clonar();
		for (int i = 0; i < numgenes; i++) {
			c1.setGen(i, hijo1.get(i));
			c2.setGen(i, hijo2.get(i));
		}
		
		return new Cromosoma[] {c1, c2};
	}

	private void fillGenesAdjacentes( int numgenes, Integer[][] adjMatrix, 
			ArrayList<Integer> hijo1, Integer sigGen) {
		
		Integer next = sigGen;	//cant override parameter
		hijo1.add(next);
		
		while(hijo1.size()<numgenes) {
			Set<Integer> set = Arrays.asList(adjMatrix[next-1])//hijo -> numAdjacentes de hijo
					.stream()
					.map(indice->adjMatrix[indice-1].length)
					.collect(Collectors.toSet());
			int min = Collections.min(set);
			List<Integer> minimos = Arrays.asList(adjMatrix[next-1])//hijos numAdjacentes de tamaño min
					.stream()
					.filter(indice->(adjMatrix[indice-1].length==min))
					.collect(Collectors.toList());
			List<Integer> resto = Arrays.asList(adjMatrix[next-1])
					.stream()
					.filter(indice->(adjMatrix[indice-1].length!=min))
					.collect(Collectors.toList());
			if(minimos.size()==1 && !hijo1.contains(minimos.get(0))) {
				hijo1.add(minimos.get(0));
				next = minimos.get(0);
			} else if(!hijo1.containsAll(minimos)) {
				int elegido = random.nextInt(minimos.size());
				while(hijo1.contains(minimos.get(elegido))) {
					elegido = random.nextInt(minimos.size());
				}
				hijo1.add(minimos.get(elegido));
				next = minimos.get(elegido);
			} else if(hijo1.containsAll(resto) || resto.isEmpty()) {
				int n = 1 + random.nextInt(numgenes);
				while(hijo1.contains(n)) 
					n = 1 + random.nextInt(numgenes);
				hijo1.add(n);
				next = n;
			} else {
				int elegido = random.nextInt(resto.size());

				while(hijo1.contains(resto.get(elegido))) {
					elegido = random.nextInt(resto.size());
				}
				hijo1.add(resto.get(elegido));
				next = resto.get(elegido);
			} 
		}
	}


}
