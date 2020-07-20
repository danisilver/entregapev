package core.mutacion;

import java.util.ArrayList;
import java.util.Random;

import gen.Cromosoma;
import gen.CromosomaGramatica;
import static gen.CromosomaGramatica.*;
import utils.Arbol;
import utils.Utils;

public class MutacionTerminalSimple implements Mutacion {
	
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
	
	@Override
	public Cromosoma mutarInd(Cromosoma ind) {
		CromosomaGramatica crom2return = (CromosomaGramatica) ind;
		Arbol arbol = crom2return.getArbol().copia();

		ArrayList<Arbol> terminalesCromosoma = new ArrayList<Arbol>();
		arbol.getTerminales(arbol.getHijos(), terminalesCromosoma);

		int term2mutPos  = random.nextInt(terminalesCromosoma.size());
		String termNuevo = terminales[random.nextInt(terminales.length)];
		Arbol term2mut   = terminalesCromosoma.get(term2mutPos);
		while (term2mut.getValor().equals(termNuevo)) 
			termNuevo    = terminales[random.nextInt(terminales.length)];
		term2mut.setValor(termNuevo);
		arbol.insertTerminal(arbol.getHijos(), term2mut, term2mutPos, 0);
		crom2return.setArbol(arbol.copia());
		
		return crom2return;
	}

}
