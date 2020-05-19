package main;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import core.cruce.Cruce;
import core.fitness.Fitness;
import core.mutacion.Mutacion;
import core.selection.Seleccion;
import gen.Cromosoma;
import utils.Utils;

public class PGenetico {
	private int    generacionActual;
	private int    cuentaAtras;
	private int    nIteraciones;
	private int    tamPoblacion;
	private double probC;
	private double probM;
	private double elitismo;
	private double fevalMedia;
	private Seleccion ts;
	private Cruce     tc;
	private Mutacion  tm;
	private Fitness   tf;
	private Cromosoma[] poblacion;
	private Cromosoma   mejorIndividuoIteracion;
	private Cromosoma   mejorIndividuoGlobal;

	public PGenetico(int nIteraciones, 
			double probC, double probM, double elitismo, Cromosoma[] poblacion) {
		this.setPoblacion(poblacion);
		this.setNumIteraciones(nIteraciones);
		this.setProbCruce(probC);
		this.setProbMutacion(probM);
		this.setPctjElitismo(elitismo);
		generacionActual = 0;
		cuentaAtras=0;
	}
	
	public void reiniciarBusqueda() {
		generacionActual = 0;
		cuentaAtras=0;
	}
	
	public void buscarNiter(int iter) {
		cuentaAtras=iter;
		buscar();
	}
	
	public void buscar() {
		if(mejorIndividuoGlobal==null) mejorIndividuoGlobal = poblacion[0];
		evaluarPoblacion(this.poblacion, tf);
		while(generacionActual<nIteraciones && --cuentaAtras>=0) {
			Cromosoma[] elite = seleccionarElite(); 
			Cromosoma[] sel, xsel, mut;
			sel  = getTipoSeleccion().seleccion(getPoblacion());
			xsel = getTipoCruce().cruce(sel, getProbCruce());
			mut  = getTipoMutacion().mutacion(xsel, getProbMutacion());

			setPoblacion(mut);
			agregarElite(elite);
			mejorIndividuoIteracion = poblacion[tamPoblacion-1];
			mejorIndividuoGlobal = Collections.max(
					List.of(mejorIndividuoIteracion, mejorIndividuoGlobal),
					tf);
			generacionActual++;
		}
	}
	
	private void agregarElite(Cromosoma[] elite) {
		for (int i = 0; i < elite.length; i++) {
			poblacion[i]=elite[i];
		}
		evaluarPoblacion(poblacion, tf);
	}

	private Cromosoma[] seleccionarElite() {
		int _numElite = (int) (poblacion.length * getPctjElitismo());
		int numElite  = _numElite % getTamPoblacion();
		Cromosoma[] elite = new Cromosoma[numElite];
		if(numElite > 0) {
			for (int i = 0; i < numElite; i++) {
				elite[i]=poblacion[poblacion.length-i-1]; 
			}
		}
		return elite;
	}

	public Cromosoma getMejorPoblacion() {
		return mejorIndividuoIteracion;
	}

	public void evaluarPoblacion(Cromosoma[] nueva, Fitness tf) {
		Arrays.sort(nueva, tf);

		double evalMin = nueva[0].value2optimize();
		double evalMax = nueva[nueva.length-1].value2optimize();
		for(Cromosoma c:nueva) {
			c.setPuntuacion(
					Utils.normalize(
							c.value2optimize(), 
							evalMin, evalMax));
		}
		
		double total = 0d;
		for(Cromosoma c:nueva)
			total += c.getPuntuacion();
		
		double puntAcc = 0;
		for (Cromosoma c:nueva) {
			puntAcc += c.getPuntuacion() / total;
			c.setPuntAcc(puntAcc);
		}
		
		double evTotal = 0d;
		for(Cromosoma c:nueva)
			evTotal+=c.value2optimize();
		
		fevalMedia	 = evTotal / tamPoblacion;
		
	}
	
	public Cromosoma[] getPoblacion() {
		return poblacion;
	}
	
	public void setPoblacion(Cromosoma[] poblacion) {
		if(poblacion.length%2==1) {
			this.poblacion = Arrays.copyOf(poblacion, poblacion.length-1);
			this.tamPoblacion = poblacion.length;
		} else {
			this.poblacion = poblacion;
			this.tamPoblacion = poblacion.length;
		}
	}

	public int    getTamPoblacion()     { return tamPoblacion; }
	public int    getNumIteraciones()   { return nIteraciones; }
	public int    getGeneracionActual() { return generacionActual; }
	public double getProbCruce()        { return probC; }
	public double getPctjElitismo()     { return elitismo; }
	public double getProbMutacion()     { return probM; }
	public Double getMaxFound()         { return mejorIndividuoGlobal.value2optimize(); }
	public Double getMaxIter()          { return mejorIndividuoIteracion.value2optimize(); }
	public Cromosoma getMaxIterCrom() 	{ return mejorIndividuoIteracion;}
	public Cromosoma getMaxGlobalCrom()	{ return mejorIndividuoGlobal;}
	public Double getAverage()          { return fevalMedia; }
	public Cruce  getTipoCruce()        { return tc; }
	public Seleccion getTipoSeleccion() { return ts; }
	public Mutacion  getTipoMutacion()  { return tm; }
	public Fitness   getTipoFitness()   { return tf; }
	public void setNumIteraciones(int nIteraciones) { this.nIteraciones = nIteraciones; }
	public void setProbCruce     (double probC)     { this.probC = probC; }
	public void setProbMutacion  (double probM)     { this.probM = probM; }
	public void setPctjElitismo  (double elitismo)  { this.elitismo = elitismo; }
	public void setTipoFitness(Fitness tf)          { this.tf = tf; }
	public void setTipoSeleccion(Seleccion ts)      { this.ts = ts; }
	public void setTipoCruce(Cruce tc)              { this.tc = tc; }
	public void setTipoMutacion(Mutacion tm)        { this.tm = tm; }
}
