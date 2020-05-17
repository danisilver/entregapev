package core.cruce;

import java.util.ArrayList;
import java.util.Random;

import gen.Cromosoma;
import gen.CromosomaGramatica;
import utils.Arbol;
import utils.Utils;

public class CruceNodeXchng implements Cruce{

	private Random random = Utils.random;
	private static final double PROB_FUNC = 0.9;
	
	class PadresHijos{
		CromosomaGramatica p1, p2;
		CromosomaGramatica h1, h2;
	};
	
	ArrayList<PadresHijos> mutaciones = new ArrayList<>();

	@Override
	public Cromosoma[] cruce(Cromosoma[] poblacion, double probCruce) {
		int tamPoblacion = poblacion.length;
		Cromosoma ret[] = new Cromosoma[tamPoblacion];
		int tam = 0;
		
		while(tam < tamPoblacion) {
			if(random.nextDouble()<probCruce) {
				Cromosoma[] crossed = cruzar(
						poblacion[random.nextInt(tamPoblacion)], 
						poblacion[random.nextInt(tamPoblacion)]);
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
	
	public Cromosoma[] cruzar(Cromosoma p1, Cromosoma p2){
		CromosomaGramatica padre1 =(CromosomaGramatica) p1;
		CromosomaGramatica padre2 =(CromosomaGramatica) p2;
		
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
		Arbol temp2 = nodos_selec2.get(puntoCruce2).copia();
		
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
		
		hijo1.evalua();
		hijo2.evalua();
		
//		System.out.println(padre1.getArbol());
//		System.out.println(hijo1.getArbol());
//		System.out.println(padre2.getArbol());
//		System.out.println(hijo2.getArbol());
//		
//		System.out.println("???????????'");
		
		mutaciones.add(new PadresHijos() {{
			p1 = padre1;
			p2 = padre2;
			h1 = hijo1;
			h2 = hijo2;
		}});
		return new Cromosoma[] {hijo1, hijo2};
	}
	
	private void corte(CromosomaGramatica hijo, Arbol temp, int puntoCruce, boolean esRaiz) {
		
		if(!esRaiz){ //dependiendo de qué tipo era el nodo que ya no va a estar, se inserta el nuevo
			hijo.getArbol().insertTerminal(hijo.getArbol().getHijos(), temp, puntoCruce, 0);
		}else{
			hijo.getArbol().insertFuncion(hijo.getArbol().getHijos(), temp, puntoCruce, 0);
		}
		
	}

	private ArrayList<Arbol> obtieneNodos(Arbol arbol) {
		ArrayList<Arbol> nodos = new ArrayList<Arbol>();
		
		//Obtenemos una porbabilidad al azar
		if(seleccionaFunciones()){//Si devuelve true, el corte se hará en una función
			arbol.getFunciones(arbol.getHijos(), nodos);
			if(nodos.size() == 0){//Si no existen funciones, se seleccionan los terminales
				arbol.getTerminales(arbol.getHijos(), nodos);
			}
		}else{//Si devuelve false, el corte se hará por un terminal
			arbol.getTerminales(arbol.getHijos(), nodos);
		}
		
		return nodos;
	}
	
	private boolean seleccionaFunciones(){
		double prob = random.nextDouble();
		
		if(prob < PROB_FUNC){
			return true;
		}else{
			return false;
		}
	}

}


/*
		insertAs = (temp1.isEsRaiz())?Arbol.insertF:Arbol.insertT;
		insertAs.apply(hijo1.getArbol())
				.apply(hijo1.getArbol().getHijos())
				.apply(temp2)
				.apply(puntoCruce1)
				.accept(0);
		
		insertAs = (temp2.isEsRaiz())?Arbol.insertF:Arbol.insertT;
		insertAs.apply(hijo2.getArbol())
				.apply(hijo2.getArbol().getHijos())
				.apply(temp1)
				.apply(puntoCruce2)
				.accept(0);
	private 
	Function<Arbol, 
	Function<ArrayList<Arbol>, 
	Function<Arbol, 
	Function<Integer, 
	Consumer<Integer>>>>> insertAs;
 * */
 