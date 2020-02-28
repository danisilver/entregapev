package Main;

import java.util.Arrays;
import java.util.Comparator;

import Gen.Cromosoma;
import Core.*;
import Core.Cruce.TipoCruce;
import Core.Mutacion.TipoMutacion;
import Core.Selection.TipoSeleccion;

public class PGenetico {
	private int tamPoblacion;
	private int nIteraciones;
	private double probC;
	private double probM;
	private double tol;
	private Cromosoma[] poblacion;
	private TipoSeleccion ts;
	private TipoCruce tc;
	private TipoMutacion tm;
	private double elitismo;

	public PGenetico(int tamPoblacion, int nIteraciones, double probC, double probM, double tol, double elitismo, Cromosoma[] poblacion) {
		this.tamPoblacion = tamPoblacion;
		this.nIteraciones = nIteraciones;
		this.probC = probC;
		this.probM = probM;
		this.tol = tol;
		this.elitismo = elitismo;
		this.setPoblacion(poblacion);
		evaluarPoblacion(poblacion);
	}
	
	public void buscar() {
		int generacionActual = 0;
		while(nIteraciones<generacionActual ) {
			Cromosoma[] nueva = new Cromosoma[tamPoblacion];
			Cromosoma[] elite = seleccionarElite(); 
			for(int i=0; i<poblacion.length/2; i++){
				Cromosoma[] sel,cruz, mut;
				sel = ts.seleccion(getPoblacion());
				cruz = tc.cruce(sel, probC);
				mut = tm.mutacion(cruz, probM);
				nueva[i]=mut[0];
				nueva[poblacion.length-i-1]=mut[1];
			}
			evaluarPoblacion(nueva);
			setPoblacion(nueva);
			agregarElite(elite);
		}
	}
	
	private void agregarElite(Cromosoma[] elite) {
		// TODO 
	}

	private Cromosoma[] seleccionarElite() {
		int nElite = (int) (poblacion.length * elitismo);
		if(nElite > 0) {
			
		}
		return null;
	}

	public Cromosoma getMejorPoblacion() {
		return poblacion[0];
	}

	private void evaluarPoblacion(Cromosoma[] nueva) {
		double peor, mejor;
		mejor = peor = (double) poblacion[0].getFenotipo();
		for(Cromosoma c:nueva) {
			double actual = (double) c.getFenotipo();
			if(actual< peor) peor = actual;
			else if(mejor< actual) mejor = actual;
			c.setTolerancia(tol);
		}
		double puntAcc = 0;
		for(Cromosoma c:nueva) {
			c.setPuntuacion(normalize((double)c.getFenotipo(), peor, mejor));
			puntAcc += c.getPuntuacion() / nueva.length;
			c.setPuntAcc(puntAcc);
		}
		
		Arrays.sort(poblacion, new Comparator<Cromosoma>() {
			@Override
			public int compare(Cromosoma o1, Cromosoma o2) {
				if(o1.getPuntuacion()<o2.getPuntuacion())
					return -1;
				else if(o1.getPuntuacion()>o2.getPuntuacion()) 
					return 1;
				else return 0;
			}
		});
	}
	
	static double normalize(double value, double min, double max) {
	    return 1 - ((value - min) / (max - min));
	}

	public Cromosoma[] getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(Cromosoma[] poblacion) {
		this.poblacion = poblacion;
	}
}
