package core.mutacion;

import static utils.Arbol.insertF;
import static utils.Arbol.insertT;
import static utils.Utils.inicCompleta;
import static utils.Utils.inicCreciente;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

import gen.Cromosoma;
import gen.CromosomaGramatica;
import utils.Arbol;
import utils.Utils;

public class MutacionNodeRestart implements Mutacion {
	
	private Random random = Utils.random;
	Function<Arbol, 
	Function<ArrayList<Arbol>, 
	Function<Arbol, 
	Function<Integer, 
	Consumer<Integer>>>>> insertAs;
	
	Function<Arbol, 
	Consumer<Integer>> initAs;

	public MutacionNodeRestart() {
		
	}
	
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
		CromosomaGramatica c = (CromosomaGramatica)ind.clonar();
		Arbol arbolCromosoma = c.getArbol().copia();

		ArrayList<Arbol> funciones  = new ArrayList<Arbol>();
		ArrayList<Arbol> terminales = new ArrayList<Arbol>();
		arbolCromosoma.getFunciones (arbolCromosoma.getHijos(), funciones);
		arbolCromosoma.getTerminales(arbolCromosoma.getHijos(), terminales);
		funciones.removeIf(a->a.getProfundidad()==arbolCromosoma.getMax_prof());
		if (funciones.isEmpty()) funciones = terminales;

		if (!funciones.isEmpty()) {
			int posNode2mut = random.nextInt(funciones.size());
			Arbol node2mut  = funciones.get(posNode2mut);
			Arbol nuevoNodo = new Arbol(
					arbolCromosoma.getMax_prof(), arbolCromosoma.isUseIF());
			
			initAs = (random.nextDouble() < 0.5)? inicCreciente : inicCompleta;
			initAs.apply(nuevoNodo)
				  .accept(node2mut.getMax_prof()-node2mut.getProfundidad());
			
			insertAs = node2mut.isEsRaiz()? insertF : insertT;
			insertAs.apply(arbolCromosoma)
				 .apply(arbolCromosoma.getHijos())
				 .apply(nuevoNodo)
				 .apply(posNode2mut)
				 .accept(0);

			arbolCromosoma.setNumNodos(arbolCromosoma.obtieneNodos(arbolCromosoma.copia(), 0));

			c.setArbol(arbolCromosoma.copia());
			c.evalua();
		}

		return c;
	}
}
