package core.mutacion;

import java.util.Random;

import gen.Cromosoma;
import gen.CromosomaGramatica;
import utils.Arbol;
import utils.Utils;

public class MutacionPermutarArgs implements Mutacion{

	private Random random = Utils.random;
	
	@Override
	public Cromosoma[] mutacion(Cromosoma[] poblacion, double probMutacion) {
		int tamPoblacion = poblacion.length;
		Cromosoma[] ret = new Cromosoma[tamPoblacion];
		
		int tam = 0;
		while(tam < tamPoblacion) {
			if(random.nextDouble() < probMutacion)
				ret[tam] = mutarInd(poblacion[tam]);
			else
				ret[tam] = poblacion[tam].clonar();
			tam++;
		}
		
		return ret;
	}
	
	public Cromosoma mutarInd(Cromosoma ind) {
		CromosomaGramatica c = (CromosomaGramatica) ind.clonar();
		Arbol a = c.getArbol();
		int k = random.nextInt(a.getNumHijos() + 1);
		Arbol node2mut = a.at(k);
		if (node2mut.getNumHijos() > 1) {
			int pos1 = random.nextInt(node2mut.getNumHijos());
			int pos2 = pos1;
			while (pos2 == pos1)
				pos2 = random.nextInt(node2mut.getNumHijos());
			Arbol tmp = node2mut.getHijos().get(pos1);
			node2mut.getHijos().set(pos1, node2mut.getHijos().get(pos2));
			node2mut.getHijos().set(pos2, tmp);
		}
		c.evalua();
		return c;
	}
	/*ArrayList<Arbol> hijos = new ArrayList<>(a.getHijos());
		hijos.removeIf(h->h.getNumHijos()<=1);*/

}
