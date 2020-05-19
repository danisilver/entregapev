package utils;

import static gen.CromosomaGramatica.funciones;
import static gen.CromosomaGramatica.terminales;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

public class Arbol {
	private String valor;
	private ArrayList<Arbol> hijos;
	private int     numHijos;
	private int     numNodos;
	private int     max_prof;
	private int     profundidad;
	private boolean useIF;
	private boolean esHoja;
	private boolean esRaiz;

	private Random random = Utils.random;
	
	public Arbol() {
		hijos 	 = new ArrayList<Arbol>();
		valor    = "";
		numHijos = 0 ;
		numNodos = 0 ;
	}
	
	public Arbol(String v) {
		hijos    = new ArrayList<Arbol>();
        valor    = v;
        numHijos = 0;
	}

	public Arbol(int max_prof, boolean useIF) {
		this.hijos 	  = new ArrayList<Arbol>();
		this.setUseIF(useIF);
		this.setMax_prof(max_prof);
		this.valor    = "";
		this.numHijos = 0 ;
		this.numNodos = 0 ;
		this.profundidad = 0;
	}

	// Devuelve el arbol en forma de array
	public ArrayList<String> toArray() {
		ArrayList<String> array = new ArrayList<String>();
		toArrayAux(array, this);
		return array;
	}

	// Insertar un valor en el arbol (nodo simple)
	public Arbol insert(String v, int index) {
		Arbol a = new Arbol(v);
		if (index == -1) {
			getHijos().add(a);
			setNumHijos(getHijos().size());
		} else
			getHijos().set(index, a);
		return a;
	}

	// Insertar un arbol en otro arbol.
	public void insert(Arbol a, int index) {
		if (index == -1) {
			getHijos().add(a);
			setNumHijos(getHijos().size());
		} else
			getHijos().set(index, a);
	}

	public Arbol at(int index) {
		return at(this, 0, index);
	}

	private Arbol at(Arbol a, int pos, int index) {
		Arbol s = null;
		if (pos >= index)
			s = a;
		else if (a.getNumHijos() > 0) {
			for (int i = 0; i < a.getNumHijos(); i++)
				if (s == null)
					s = at(a.getHijos().get(i), pos + i + 1, index);
		}
		return s;
	}

	private void toArrayAux(ArrayList<String> array, Arbol a) {
		array.add(a.getValor());
		for (int i = 0; i < a.getHijos().size(); i++) {
			toArrayAux(array, a.getHijos().get(i));
		}
	}

	public int inicializacionCompleta(int profundidad, int nodos) {
		int nHijos = 2;
		if (profundidad < getMax_prof()) {
			setProfundidad(profundidad);
			int func = random.nextInt(
					isUseIF()?funciones.length:(funciones.length-1));
			setValor(funciones[func]);
			setEsRaiz(true);
			if (getValor().equals("IF"))
				nHijos = 3;
			if (getValor().equals("NOT"))
				nHijos = 1;
			for (int i = 0; i < nHijos; i++) {
				Arbol hijo = new Arbol(getMax_prof(), isUseIF());
				setEsRaiz(true);
				nodos++;
				nodos = hijo.inicializacionCompleta(profundidad + 1, nodos);
				getHijos().add(hijo);
				setNumHijos(getNumHijos() + 1);
			}
		} else {
			setProfundidad(profundidad);
			int terminal;
			this.setEsHoja(true);
			terminal = random.nextInt(terminales.length);
			setValor(terminales[terminal]);
			setEsHoja(true);
			setNumHijos(0);
		}
		setNumNodos(nodos);
		return nodos;
	}

	public void inicializacionCreciente(int p){
		int n = 0;
		
		if(p < getMax_prof()){
			setProfundidad(p);
			int func = 0;
			
			if(isUseIF()){
				func = random.nextInt(funciones.length);
			}else{
				func = random.nextInt(funciones.length-1);
			}
			
			this.valor = funciones[func];
			esRaiz = true;
			n = creaHijos(p, n);
		}else{
			setProfundidad(p);
			int terminal;
			terminal = random.nextInt(terminales.length);
			valor = terminales[terminal];
			esHoja = true;
		}
		setNumNodos(n);
	}
	
	private int inicializacionCrecienteAux(int p, int nodos){
		int n = nodos;		
		
		if(p < getMax_prof()){
			setProfundidad(p);
			int rango;
			
			if(isUseIF()){
				rango = funciones.length + terminales.length;
				
				int pos = random.nextInt(rango);
				
				if(pos >= funciones.length){
					pos -= funciones.length;
					valor = terminales[pos];
					esHoja = true;
					
				}else{
					valor = funciones[pos];
					esRaiz = true;
					n = creaHijos(p, n);
				}
			}
			else{
				rango = funciones.length + terminales.length - 1;
				
				int pos = random.nextInt(rango);
				
				if(pos >= (funciones.length-1)){
					pos -= funciones.length-1;
					valor = terminales[pos];
					esHoja = true;
				}else{
					valor = funciones[pos];
					esRaiz = true;
					n = creaHijos(p, n);
				}
			}
		}
		else{
			setProfundidad(p);
			int terminal;
			terminal = random.nextInt(terminales.length);
			valor = terminales[terminal];
			esHoja = true;
		}
		setNumNodos(n);
		return n;
	}

	public int creaHijos(int p, int nodos) {
		int n = nodos;
		int nHijos = 2;
		if (getValor().equals("IF"))
			nHijos = 3;
		if (getValor().equals("NOT"))
			nHijos = 1;
		for (int i = 0; i < nHijos; i++) {
			Arbol hijo = new Arbol(getMax_prof(), isUseIF());
			// hijo.setPadre(this);
			n++;
			n = hijo.inicializacionCrecienteAux(p + 1, n);
			hijos.add(hijo);
			numHijos++;
		}
		return n;
	}

	/**
	 * Devuelve los nodos hoja del árbol
	 * 
	 * @param hijos Hijos del árbol a analizar
	 * @param nodos Array donde se guardan los terminales
	 */
	public void getTerminales(ArrayList<Arbol> hijos, ArrayList<Arbol> nodos) {
		for (int i = 0; i < hijos.size(); i++) {
			if (hijos.get(i).isEsHoja()) {
				nodos.add(hijos.get(i).copia());
			} else {
				getTerminales(hijos.get(i).getHijos(), nodos);
			}
		}
	}

	public int insertTerminal(ArrayList<Arbol> list_hijos, Arbol terminal, int index, int pos) {
		int p = pos;
		for (int i = 0; i < list_hijos.size() && p != -1; i++) {
			if (list_hijos.get(i).esHoja && (p == index)) {	 // terminal.padre = list_hijos.get(i).padre;
				list_hijos.set(i, terminal.copia());
				p = -1;
			} else if (list_hijos.get(i).esHoja && (p != index)) {
				p++;
			} else {
				p = insertTerminal(list_hijos.get(i).hijos, terminal, index, p);
			}
		}
		return p;
	}
	public static
	Function<Arbol, 
	Function<ArrayList<Arbol>, 
	Function<Arbol, 
	Function<Integer, 
	Consumer<Integer>>>>> insertT = a-> b-> c-> d-> e-> a.insertTerminal(b, c, d.intValue(), e.intValue());

	
	public int insertFuncion(ArrayList<Arbol> list_hijos, Arbol terminal, int index, int pos) {
		int p = pos;
		for (int i = 0; i < list_hijos.size() && p != -1; i++) {
			if (list_hijos.get(i).esRaiz && (p == index)) {
				// terminal.padre = list_hijos.get(i).padre;
				list_hijos.set(i, terminal.copia());
				p = -1;
			} else if (list_hijos.get(i).esRaiz && (p != index)) {
				p++;
				p = insertFuncion(list_hijos.get(i).hijos, terminal, index, p);
			}
		}
		return p;
	}
	public static
	Function<Arbol, 
	Function<ArrayList<Arbol>, 
	Function<Arbol, 
	Function<Integer, 
	Consumer<Integer>>>>> insertF = a-> b-> c-> d-> e-> a.insertFuncion (b, c, d.intValue(), e.intValue());

	/**
	 * Devuelve los nodos internos del árbol
	 * 
	 * @param hijos Hijos del árbol a analizar
	 * @param nodos Array donde se guardan las funciones
	 */
	public void getFunciones(ArrayList<Arbol> hijos, ArrayList<Arbol> nodos) {
		for (int i = 0; i < hijos.size(); i++) {
			if (hijos.get(i).isEsRaiz()) {
				nodos.add(hijos.get(i).copia());
				getFunciones(hijos.get(i).hijos, nodos);
			}
		}
	}

	public Arbol copia() {
		Arbol copia = new Arbol(this.getMax_prof(), this.isUseIF());
		copia.setEsHoja(this.esHoja);
		copia.setEsRaiz(this.esRaiz);
		copia.setNumHijos(this.numHijos);
		copia.setNumNodos(this.numNodos);
		copia.setProfundidad(this.profundidad);
		copia.setValor(this.getValor());
		ArrayList<Arbol> aux = new ArrayList<Arbol>();
		aux = copiaHijos();
		copia.setHijos(aux);
		return copia;
	}

	private ArrayList<Arbol> copiaHijos() {
		ArrayList<Arbol> array = new ArrayList<Arbol>();
		for (int i = 0; i < this.hijos.size(); i++) {
			array.add(this.hijos.get(i).copia());
		}
		return array;
	}

	public int obtieneNodos(Arbol nodo, int n) {
		if (nodo.esHoja)
			return n;
		if (nodo.getValor().equals("IF")) {
			n = obtieneNodos(nodo.hijos.get(0), n + 1);
			n = obtieneNodos(nodo.hijos.get(1), n + 1);
			n = obtieneNodos(nodo.hijos.get(2), n + 1);
		} else if (nodo.getValor().equals("AND") || nodo.getValor().equals("OR")) {
			n = obtieneNodos(nodo.hijos.get(0), n + 1);
			n = obtieneNodos(nodo.hijos.get(1), n + 1);
		} else {
			n = obtieneNodos(nodo.hijos.get(0), n + 1);
		}
		return n;
	}
	
	@Override
	public String toString() {
		String s =  "(" + valor
				+ " " + hijosToString(hijos) + " "
				+ ")";
		return s;
	}
	
	private String hijosToString(ArrayList<Arbol> h) {
		String cad = "";
		for(int i  = 0; i < h.size(); i++){
			if(h.get(i).esRaiz){
				cad += "(" + h.get(i).valor + " " + hijosToString(h.get(i).hijos);
				cad += ")";
			} else {
				cad += h.get(i).valor + " ";
			}
		}
		return cad;
	}

	public int     getProfundidad() { return profundidad; }
	public int     getMax_prof() { return max_prof; }
	public int     getNumHijos() { return numHijos; }
	public int     getNumNodos() { return numNodos; }
	public String  getValor()    { return valor; }
	public boolean isEsHoja()    { return esHoja; }
	public boolean isEsRaiz()    { return esRaiz; }
	public boolean isUseIF()     { return useIF; }
	public void    setProfundidad(int profundidad)  { 
		this.profundidad = profundidad; 
		for(Arbol a:this.hijos) {
			a.setProfundidad(this.profundidad+1);
		}
	}
	public void    setHijos(ArrayList<Arbol> hijos) { this.hijos = hijos; }
	public void    setMax_prof(int max_prof) { this.max_prof = max_prof; }
	public void    setNumHijos(int numHijos) { this.numHijos = numHijos; }
	public void    setNumNodos(int numNodos) { this.numNodos = numNodos; }
	public void    setEsHoja(boolean esHoja) { this.esHoja = esHoja; }
	public void    setEsRaiz(boolean esRaiz) { this.esRaiz = esRaiz; }
	public void    setUseIF(boolean useIF)   { this.useIF = useIF; }
	public void    setValor(String valor)    { this.valor = valor; }
	public ArrayList<Arbol> getHijos() { return hijos; }

}
