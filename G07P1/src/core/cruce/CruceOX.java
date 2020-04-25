package core.cruce;

import java.util.ArrayList;
import java.util.Random;

import gen.Cromosoma;
import utils.Utils;

public class CruceOX implements Cruce{

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

	private Cromosoma[] crossPair(Cromosoma[] cromosomas) {
		Cromosoma c1 = cromosomas[0]; 
		Cromosoma c2 = cromosomas[1];
		Object[] genesp1 = c1.getGenes(); 
		Object[] genesp2 = c2.getGenes();
		int numgenes = c1.getGenes().length;
		
		int pto1 = random.nextInt(numgenes-1); 
		int pto2 = pto1 + random.nextInt(numgenes-pto1);
		
		ArrayList<Integer> genes1 = new ArrayList<Integer>(numgenes);
		ArrayList<Integer> genes2 = new ArrayList<Integer>(numgenes);
		
		for (int i = 0; i < numgenes; i++) { 
			genes1.add(-1);
			genes2.add(-1);
		}
		
		for(int i=pto1;i<=pto2;i++) {
			genes1.set(i, (Integer)genesp2[i]); 
			genes2.set(i, (Integer)genesp1[i]);
		}
		
		int sig = pto2+1;
		for(int j=sig; j<(sig+numgenes); j++){
			int i = j%numgenes;
			if(genes1.get(i)==-1) {
				int k = i;
				while(genes1.contains(genesp1[k]))
					k = (k+1) %numgenes;
				genes1.set(i, (Integer)genesp1[k]);
			}
			if(genes2.get(i)==-1) {
				int k = i;
				while(genes2.contains(genesp2[k]))
					k = (k+1) %numgenes;
				genes2.set(i, (Integer)genesp2[k]);
			}
		};
		
		Cromosoma child1 = c1.clonar();
		Cromosoma child2 = c2.clonar();
		for (int i = 0; i < numgenes; i++) {
			child1.setGen(i, genes1.get(i));
			child2.setGen(i, genes2.get(i));
		}
		
		return new Cromosoma[] {child1, child2};
	}

}
