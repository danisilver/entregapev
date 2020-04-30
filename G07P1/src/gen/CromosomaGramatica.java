package gen;

import static utils.Utils.inicCompleta;
import static utils.Utils.inicCreciente;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

import utils.Arbol;

public class CromosomaGramatica extends Cromosoma {

	public static final String funciones[] = { "AND", "OR", "NOT", "IF" };
	public static String terminales[] = { "A0", "A1", "D0", "D1", "D2", "D3" }; 
	private Arbol  arbol;
	private double fitness;
	private double fitness_bruto; // Aptitud antes de transformarla
	private int    nOutputs;
	private int    nAddrInputs;
	Function<Arbol,
	Consumer<Integer>> initAs;
	private int nInputs;
	
	private CromosomaGramatica() { }

	/**
	 * nOutputs = 1 << 2 + (1 << 2)
	 * nOutputs = 1 << nInputs + (1 << nInputs)
	 */
	public CromosomaGramatica(int profundidad, int tipoCreacion, boolean useIf, int nAddrInputs) {
		this.nAddrInputs = nAddrInputs;
		arbol = new Arbol(profundidad, useIf);
		setnOutputs(1 << nAddrInputs + (1 << nAddrInputs));
		nInputs = (nAddrInputs + (1 << nAddrInputs));
		if(terminales.length < nInputs) 
			terminales = getTerminales();
		
		initAs = (tipoCreacion==0)? inicCreciente : inicCompleta;
		initAs.apply(arbol).accept(0);
		
		evalua();
	}

	public double evalua() {
		ArrayList<String> func = getArbol().toArray();
		int fallos = getnOutputs();
		for (int i = 0; i < getnOutputs(); i++) {
			int _selInput = i >> (1 << nAddrInputs);
			int nDataInputs = nInputs - nAddrInputs;
			int _muxData  = ((i << (nAddrInputs)) & (getnOutputs()-1)) >> nAddrInputs;
			int muxOut_i  = ((_muxData & (1<<nDataInputs-1-_selInput)) > 0)? 1:0;
			if(muxOut_i == evaluar(func, 0, i)) fallos--;
		}
		fitness_bruto = fallos;
		fitness = fallos;
		return fallos;
	}
	
	private int evaluar(ArrayList<String> func, int index, int input){
		switch (func.get(index)) {
		case "IF":
			return (evaluar(func, index+1, input) == 1)? evaluar(func, index+2, input):evaluar(func, index+3, input);
		case "AND":
			boolean _and = evaluar(func, index+1, input) == 1 && evaluar(func, index+2, input) == 1;
			return _and? 1:0;
		case "OR":
			boolean _or = evaluar(func, index+1, input) == 1 || evaluar(func, index+2, input) == 1;
			return _or?  1:0;
		case "NOT":
			return (evaluar(func, index+1, input) == 1)? 0:1;
		default://traducir A0..n o D0..n a 1 o 0 del input
			String terminalstr = func.get(index).substring(1);
			int terminal = Integer.parseInt(terminalstr);
			if(func.get(index).startsWith("D")) terminal += nAddrInputs;
			int leftMostT = nInputs - 1;
			terminal = leftMostT - terminal;
			return (input & (1<<terminal))>>terminal;
		}
	}
	
	@Override
	public Cromosoma clonar() {
		CromosomaGramatica c = new CromosomaGramatica();
		c.fitness_bruto = this.fitness_bruto;
		c.arbol	   = this.arbol.copia();
		c.fitness  = this.fitness;
		c.setPuntuacion(getPuntuacion());
		c.setPuntAcc(getPuntAcc());
		c.nAddrInputs = this.nAddrInputs;
		c.nInputs = this.nInputs;
		c.setnOutputs(this.getnOutputs());
		return c;
	}
	
	public String[] getTerminales()	   { 
		String[] ret = new String[nInputs];
		for (int i = 0; i < nInputs; i++) {
			ret[i] = (i<nAddrInputs)? "A"+i : "D"+(i-nAddrInputs);
		}
		return ret;
	}
	
	public Arbol getArbol()            { return arbol; }
	public void  setArbol(Arbol arbol) { this.arbol = arbol; }
	@Override public Object[] getGenes()     { return new Arbol[] { getArbol() }; }
	@Override public int    getGenLen(int i) { return arbol.getMax_prof(); }
	@Override public int    getNumGenes()    { return 1; }
	@Override public Object getGen(int i)    { return getArbol(); }
	@Override public Object getFenotipo()    { return getArbol().toString(); }
	@Override public double value2optimize() { return fitness; }
	@Override public String toString() 		 { return arbol.toString(); }
	@Override public void   setGen(int i, Object g) { arbol = (Arbol) g; }

	public int getnOutputs() {
		return nOutputs;
	}

	public void setnOutputs(int nOutputs) {
		this.nOutputs = nOutputs;
	}
}