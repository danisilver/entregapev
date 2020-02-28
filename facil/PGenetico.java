package facil;

import java.util.Arrays;
import java.util.Comparator;

public class PGenetico {
	private int tamPoblacion;
	private int nIteraciones;
	private double probC;
	private double probM;
	private Cromosoma[] poblacion;
	private TipoSeleccion ts;
	private TipoCruce tc;
	private TipoMutacion tm;
	private double elitismo;
	private int generacionActual;
	private int cuentaAtras;

	public PGenetico(int tamPoblacion, int nIteraciones, double probC, double probM, double elitismo, Cromosoma[] poblacion) {
		this.setTamPoblacion(tamPoblacion);
		this.setNumIteraciones(nIteraciones);
		this.setProbCruce(probC);
		this.setProbMutacion(probM);
		this.setPctjElitismo(elitismo);
		this.setPoblacion(poblacion);
		Cromosoma.tolerancia = 0.001;
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
		while(generacionActual<nIteraciones && --cuentaAtras>=0) {
			Cromosoma[] nueva = new Cromosoma[getTamPoblacion()];
			Cromosoma[] elite = seleccionarElite(); 
			for(int i=0; i<poblacion.length/2; i++){
				Cromosoma[] sel,cruz, mut;
				sel = getTipoSeleccion().seleccion(getPoblacion());
				cruz = getTipoCruce().cruce(sel, getProbCruce());
				mut = getTipoMutacion().mutacion(cruz, getProbMutacion());
				nueva[i]=mut[0];
				nueva[poblacion.length-i-1]=mut[1];
			}
			setPoblacion(nueva);
			evaluarPoblacion(poblacion);
			agregarElite(elite);
			generacionActual++;
		}
	}
	
	private void agregarElite(Cromosoma[] elite) {
		for (int i = 0; i < elite.length; i++) {
			poblacion[getTamPoblacion()-i-1]=elite[i];
		}
	}

	private Cromosoma[] seleccionarElite() {
		int numElite = Math.min((int) (poblacion.length * getPctjElitismo()), getTamPoblacion());
		Cromosoma[] elite = new Cromosoma[numElite];
		if(numElite > 0) {
			for (int i = 0; i < numElite; i++) {
				elite[i]=poblacion[i]; //la poblacion ya esta ordenada
			}
		}
		return elite;
	}

	public Cromosoma getMejorPoblacion() {
		return poblacion[poblacion.length-1];
	}

	private void evaluarPoblacion(Cromosoma[] nueva) {
		double peor, mejor;
		mejor = peor = (double) nueva[0].getFenotipo();
		for(Cromosoma c:nueva) {
			double actual = (double) c.getFenotipo();
			if(actual< peor) peor = actual;
			else if(mejor< actual) mejor = actual;
		}
		for(Cromosoma c:nueva) {
			c.setPuntuacion(normalize((double)c.getFenotipo(), peor, mejor));
		}
		
		Arrays.sort(nueva, new Comparator<Cromosoma>() {
			@Override
			public int compare(Cromosoma o1, Cromosoma o2) {
				if(o1.getPuntuacion()<o2.getPuntuacion())
					return -1;
				else if(o1.getPuntuacion()>o2.getPuntuacion()) 
					return 1;
				else return 0;
			}
		});
		
		double puntAcc = 0;
		for (Cromosoma c:nueva) {
			puntAcc += c.getPuntuacion() / nueva.length;
			c.setPuntAcc(puntAcc);
		}
	}
	
	static double normalize(double value, double min, double max) {
	    return ((value - min) / (max - min));
	}

	public Cromosoma[] getPoblacion() {
		return poblacion;
	}
	
	public void setPoblacion(Cromosoma[] poblacion) {
		this.poblacion = poblacion;
		if(poblacion!=null) evaluarPoblacion(poblacion);
	}

	public int getTamPoblacion() {
		return tamPoblacion;
	}

	public void setTamPoblacion(int tamPoblacion) {
		this.tamPoblacion = tamPoblacion;
	}

	public int getNumIteraciones() {
		return nIteraciones;
	}

	public void setNumIteraciones(int nIteraciones) {
		this.nIteraciones = nIteraciones;
	}

	public double getProbCruce() {
		return probC;
	}

	public void setProbCruce(double probC) {
		this.probC = probC;
	}

	public double getProbMutacion() {
		return probM;
	}

	public void setProbMutacion(double probM) {
		this.probM = probM;
	}

	public TipoSeleccion getTipoSeleccion() {
		return ts;
	}

	public void setTipoSeleccion(TipoSeleccion ts) {
		this.ts = ts;
	}

	public TipoCruce getTipoCruce() {
		return tc;
	}

	public void setTipoCruce(TipoCruce tc) {
		this.tc = tc;
	}

	public TipoMutacion getTipoMutacion() {
		return tm;
	}

	public void setTipoMutacion(TipoMutacion tm) {
		this.tm = tm;
	}

	public double getPctjElitismo() {
		return elitismo;
	}

	public void setPctjElitismo(double elitismo) {
		this.elitismo = elitismo;
	}

	public int getGeneracionActual() {
		return generacionActual;
	}
}
