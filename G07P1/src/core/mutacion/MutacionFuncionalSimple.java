package core.mutacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gen.Cromosoma;
import gen.CromosomaGramatica;
import utils.Arbol;
import utils.Utils;

public class MutacionFuncionalSimple implements Mutacion {
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
		CromosomaGramatica c = (CromosomaGramatica) ind;
		Arbol arbolCromosoma = c.getArbol().copia();
		
		ArrayList<Arbol> funciones = new ArrayList<Arbol>();
		arbolCromosoma.getFunciones(arbolCromosoma.getHijos(), funciones);
		
		funciones.removeIf(f->List.of("AND", "OR").contains(f.getValor()));
		if(funciones.size() < 1) return c;

		int sel = random.nextInt(funciones.size());
		Arbol arbolFuncion = funciones.get(sel);
		switch (arbolFuncion.getValor()) {
		case "AND":
			arbolFuncion.setValor("OR");
			break;
		case "OR":
			arbolFuncion.setValor("AND");
		}

		arbolCromosoma.insertFuncion(arbolCromosoma.getHijos(), arbolFuncion, sel, 0);
		c.setArbol(arbolCromosoma.copia());
		c.evalua();
		
		return c;
	}

}
