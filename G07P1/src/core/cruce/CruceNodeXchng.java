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
		
		hijo1.evalua();
		hijo2.evalua();
		
//		JFrame t1 = Utils.showTree(padre1.getArbol());
//		t1.setLocation(0, 0);
//		JFrame t2 = Utils.showTree(padre2.getArbol());
//		t2.setLocation(0, 400);
//		JFrame t3 = Utils.showTree(hijo1.getArbol());
//		t3.setLocation(700, 0);
//		JFrame t4 = Utils.showTree(hijo2.getArbol());
//		t4.setLocation(700, 400);
		
		mutaciones.add(new PadresHijos() {{
			p1 = padre1;
			p2 = padre2;
			h1 = hijo1;
			h2 = hijo2;
		}});
		
//		t1.dispose();
//		t2.dispose();
//		t3.dispose();
//		t4.dispose();
		int profp1 = padre1.getArbol().getProfundidad();
		int profp2 = padre2.getArbol().getProfundidad();
		int profh1 = hijo1.getArbol().getProfundidad();
		int profh2 = hijo1.getArbol().getProfundidad();
		
		if(profh2>profp1 || profh2>profp2 || profh1>profp1 || profh1>profp2) {
			profp1=profp1;	
		}
		System.out.println();
		return new Cromosoma[] {hijo1, hijo2};
	}
	
	private void corte(CromosomaGramatica hijo, Arbol temp, int puntoCruce, boolean esRaiz) {
		
		if(!esRaiz){ //dependiendo de qué tipo era el nodo que ya no va a estar, se inserta el nuevo
			hijo.getArbol()
				.insertTerminal(
						hijo.getArbol().getHijos(), 
						temp, 
						puntoCruce, 
						0);
		}else{
			hijo.getArbol()
				.insertFuncion(
						hijo.getArbol().getHijos(), 
						temp, 
						puntoCruce, 
						0);
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
