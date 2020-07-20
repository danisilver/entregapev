package core.cruce;

import java.util.ArrayList;
import java.util.Random;

import gen.Cromosoma;
import utils.Utils;

public class CrucePMX implements Cruce{

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
		Cromosoma c1 = ind[0]; 
		Cromosoma c2 = ind[1];
		Integer[] genesp1 = (Integer[])c1.getGenes(); 
		Integer[] genesp2 = (Integer[])c2.getGenes();
		int numgenes = c1.getGenes().length;
		
		int pto1 = random.nextInt(numgenes-1); 
		int pto2 = pto1 + random.nextInt(numgenes-pto1);
		
		ArrayList<Integer> genes1 = new ArrayList<Integer>(numgenes);
		ArrayList<Integer> genes2 = new ArrayList<Integer>(numgenes);
		
		for (int i = 0; i < numgenes; i++) { genes1.add(-1); genes2.add(-1);}
		
		for(int i=pto1;i<=pto2;i++) {
			genes1.set(i, (Integer)genesp2[i]); 
			genes2.set(i, (Integer)genesp1[i]);
		}
		
		for(int i=0;i<numgenes;i++){
			if(genes1.get(i)==-1) {
				if(!genes1.contains(genesp1[i])) genes1.set(i, (Integer)genesp1[i]);
				else genes1.set(i, getGenHomologo(genesp1, genesp2, genes1, i));
			}
			if(genes2.get(i)==-1) {
				if(!genes2.contains(genesp2[i])) genes2.set(i, (Integer)genesp2[i]); 
				else genes2.set(i, getGenHomologo(genesp2, genesp1, genes2, i));
			}
		};
		
		Cromosoma child1 = c1.clonar();
		Cromosoma child2 = c2.clonar();
		for (int i = 0; i < numgenes; i++) {
			child1.setGen(i, Integer.valueOf(genes1.get(i)));
			child2.setGen(i, Integer.valueOf(genes2.get(i)));
		}
		
		return new Cromosoma[] {child1, child2};
	}

	private Integer getGenHomologo(Integer[] genesp1, Integer[] genesp2, ArrayList<Integer> genes1, int i) {
		Integer homologo, genHomologo;
		homologo = i;
		genHomologo=genesp1[homologo];
		
		while(genes1.contains(genHomologo)) {
			homologo=getPosOfGen(genHomologo, genesp2);	
			genHomologo=genesp1[homologo];
		}
		return genHomologo;
	}
	
	private Integer getPosOfGen(Integer g, Integer[] genes) {
		for (int i = 0; i < genes.length; i++) {
			if(genes[i]==g) return i;
		}
		return 0;
	}

}