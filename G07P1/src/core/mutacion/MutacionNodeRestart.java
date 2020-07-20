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

	@Override
	public Cromosoma mutarInd(Cromosoma ind) {
		CromosomaGramatica crom2return = (CromosomaGramatica)ind.clonar();
		Arbol arbol2mut = crom2return.getArbol().copia();
		
		ArrayList<Arbol> funciones  = new ArrayList<>();
		ArrayList<Arbol> terminales = new ArrayList<>();
		arbol2mut.getFunciones (arbol2mut.getHijos(), funciones);
		arbol2mut.getTerminales(arbol2mut.getHijos(), terminales);
		funciones.removeIf(n->n.getProfundidad()==arbol2mut.getMax_prof());
		if (funciones.isEmpty()) funciones = terminales;

		if (!funciones.isEmpty()) {
			int posNode2mut = random.nextInt(funciones.size());
			Arbol node2mut  = funciones.get(posNode2mut);
			Arbol nuevoNodo = new Arbol(
					arbol2mut.getMax_prof(), arbol2mut.isUseIF());
			
			initAs = (random.nextDouble() < 0.5)? inicCreciente : inicCompleta;
			initAs.apply(nuevoNodo)
				  .accept(node2mut.getProfundidad());
			
			insertAs = node2mut.isEsRaiz()? insertF : insertT;
			insertAs.apply(arbol2mut)
				 .apply(arbol2mut.getHijos())
				 .apply(nuevoNodo)
				 .apply(posNode2mut)
				 .accept(0);

			arbol2mut.setNumNodos(arbol2mut.obtieneNodos(arbol2mut.copia(), 0));

			crom2return.setArbol(arbol2mut.copia());
		}
		
		return crom2return;
	}
}
