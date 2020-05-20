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
		CromosomaGramatica crom2return = (CromosomaGramatica) ind;
		Arbol arbol2mut = crom2return.getArbol().copia();
		
		ArrayList<Arbol> funciones = new ArrayList<Arbol>();
		arbol2mut.getFunciones(arbol2mut.getHijos(), funciones);
		
		funciones.removeIf(f->!List.of("AND", "OR").contains(f.getValor()));
		if(funciones.size() < 1) return crom2return;

		int sel = random.nextInt(funciones.size());
		Arbol fun2mut = funciones.get(sel);
		switch (fun2mut.getValor()) {
		case "AND": fun2mut.setValor("OR");  break;
		case "OR" : fun2mut.setValor("AND"); break;
		}

		arbol2mut.insertFuncion(arbol2mut.getHijos(), fun2mut, sel, 0);
		crom2return.setArbol(arbol2mut.copia());
		
		return crom2return;
	}

}
