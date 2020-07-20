package core.mutacion;

import java.util.ArrayList;
import java.util.Random;

import gen.Cromosoma;
import gen.CromosomaGramatica;
import utils.Arbol;
import utils.Utils;

public class MutacionTreeHoist implements Mutacion {
	
	Random random = Utils.random;
	@Override
	public Cromosoma[] mutacion(Cromosoma[] poblacion, double probMutacion) {
		int tamPoblacion = poblacion.length;
		Cromosoma[] ret = new Cromosoma[tamPoblacion];

		int tam = 0;
		while (tam < tamPoblacion) {
			if (random.nextDouble() < probMutacion)
				ret[tam] = mutarInd(poblacion[tam]);
			else
				ret[tam] = poblacion[tam].clonar();
			tam++;
		}

		return ret;
	}

	@Override
	public Cromosoma mutarInd(Cromosoma ind) {
		CromosomaGramatica crom2return = (CromosomaGramatica) ind.clonar();
		Arbol arbol2mut = crom2return.getArbol().copia();

		ArrayList<Arbol> funciones = new ArrayList<>();
		arbol2mut.getFunciones(arbol2mut.getHijos(), funciones);
		funciones.removeIf(n -> n.getProfundidad() == arbol2mut.getMax_prof());
		if (funciones.isEmpty())
			return crom2return;

		Arbol hoistedNode = funciones.get(random.nextInt(funciones.size())).copia();
		hoistedNode.setMax_prof(arbol2mut.getMax_prof());
		hoistedNode.setNumNodos(arbol2mut.obtieneNodos(arbol2mut.copia(), 0));
		hoistedNode.setEsRaiz(true);
		hoistedNode.setProfundidad(0);
		crom2return.setArbol(hoistedNode);

		return crom2return;
	}
}
