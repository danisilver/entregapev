package core.cruce;

import java.util.ArrayList;
import java.util.Random;

import gen.Cromosoma;
import gen.CromosomaGramatica;
import utils.Arbol;
import utils.Utils;

public class CruceNodeXchng implements Cruce{

	private Random random = Utils.random;
	private double probFoT = 0.9; // prob de mutar funcion o terminal
	

	@Override
	public Cromosoma[] cruce(Cromosoma[] poblacion, double probCruce) {
		int tamPoblacion = poblacion.length;
		Cromosoma ret[] = new Cromosoma[tamPoblacion];
		int tam = 0;
		
		while(tam < tamPoblacion) {
			if(random.nextDouble()<probCruce) {
				Cromosoma[] crossed = crossPair(
						new Cromosoma[] {
								poblacion[random.nextInt(tamPoblacion)], 
								poblacion[random.nextInt(tamPoblacion)]
						});
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
	
	@Override
	public Cromosoma[] crossPair(Cromosoma[] ind){
		CromosomaGramatica padre1 =(CromosomaGramatica) ind[0];
		CromosomaGramatica padre2 =(CromosomaGramatica) ind[1];
		
		CromosomaGramatica hijo1, hijo2;
		
		
		ArrayList<Arbol> nodos_selec1 = new ArrayList<Arbol>();
		ArrayList<Arbol> nodos_selec2 = new ArrayList<Arbol>();
		
		nodos_selec1 = obtieneNodos(padre1.getArbol().copia());
		nodos_selec2 = obtieneNodos(padre2.getArbol().copia());
		
		int puntoCruce1 = (int) (random.nextDouble()*nodos_selec1.size());
		int puntoCruce2 = (int) (random.nextDouble()*nodos_selec2.size());
		
		hijo1 = (CromosomaGramatica)padre1.clonar();
		hijo2 = (CromosomaGramatica)padre2.clonar();
		
		Arbol temp1 = nodos_selec1.get(puntoCruce1).copia();
		int proft1 = temp1.getProfundidad();
		Arbol temp2 = nodos_selec2.get(puntoCruce2).copia();
		int proft2 = temp2.getProfundidad();
		temp1.setProfundidad(proft2); //intercambiamos profundidades para dibujar bien
		temp2.setProfundidad(proft1);
		
		corte(hijo1, temp2, puntoCruce1, temp1.isEsRaiz());
		corte(hijo2, temp1, puntoCruce2, temp2.isEsRaiz());
		
		int nodos = hijo1
				.getArbol()
				.obtieneNodos(hijo1.getArbol(), 0);
		hijo1.getArbol().setNumNodos(nodos);
		nodos = hijo2
				.getArbol()
				.obtieneNodos(hijo2.getArbol(), 0);
		hijo2.getArbol().setNumNodos(nodos);
		
		return new Cromosoma[] {hijo1, hijo2};
	}
	
	private void corte(CromosomaGramatica hijo, Arbol temp, int puntoCruce, boolean esRaiz) {
		
		if(!esRaiz){ //dependiendo de qué tipo era el nodo que ya no va a estar, se inserta el nuevo
			Arbol arbol = hijo.getArbol();
			arbol.insertTerminal(
					hijo.getArbol().getHijos(), 
					temp, 
					puntoCruce, 
					0);
			hijo.setArbol(arbol);
		}else{
			Arbol arbol = hijo.getArbol();
			arbol.insertFuncion(
						hijo.getArbol().getHijos(), 
						temp, 
						puntoCruce, 
						0);
			hijo.setArbol(arbol);
		}
		
	}

	private ArrayList<Arbol> obtieneNodos(Arbol arbol) {
		ArrayList<Arbol> nodos = new ArrayList<Arbol>();
		
		if(random.nextDouble() < probFoT){//Si devuelve true, el corte se hará en una función
			arbol.getFunciones(arbol.getHijos(), nodos);
			if(nodos.size() == 0){//Si no existen funciones, se seleccionan los terminales
				arbol.getTerminales(arbol.getHijos(), nodos);
			}
		}else{//Si devuelve false, el corte se hará por un terminal
			arbol.getTerminales(arbol.getHijos(), nodos);
		}
		
		return nodos;
	}

}
