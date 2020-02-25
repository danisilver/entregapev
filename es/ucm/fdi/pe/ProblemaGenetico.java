package es.ucm.fdi.pe;

public abstract class ProblemaGenetico<F,G extends Genotipo<G,F>>{
	Seleccion<G> tipoSeleccion;
	Cruce<G> tipoCruce;
	Mutacion<G> tipoMutacion;
	
	int nIteraciones;
	int generacion;
	
	G[] poblacion ;
	double probCruce;
	double probMutacion;
	private int paso;
	
	public ProblemaGenetico(int nIt) {
		nIteraciones = nIt;
		generacion = 0;
		poblacion = generarPoblacionInicial();
	}
	
	abstract G[] generarPoblacionInicial();

	void iniciarBusqueda() {
		while(nIteraciones<generacion && condTermina() && --paso>0) {
			for(int i=0; i<(poblacion.length/2); i++){
				G[] sel,cruz, mut;
				sel = seleccion(tipoSeleccion, this);
				cruz = cruce(tipoCruce, sel, this);
				mut = mutacion(tipoMutacion, cruz, this);
				reemplaza(mut);
			}
		}
	}
	
	protected abstract void reemplaza(G[] mut);

	abstract double fitnessPoblacion();
	abstract double fitnessIndividuo(G i);
	
	abstract G[] seleccion(Seleccion<G> tsel, ProblemaGenetico<F,G> pg);
	abstract G[] cruce(Cruce<G> tcruce, G[] padres, ProblemaGenetico<F,G> pg);
	abstract G[] mutacion(Mutacion<G> tmutacion, G[] hijos, ProblemaGenetico<F,G> pg);

	abstract boolean condTermina();
	
	public G getMejor() { return null;}
	public G getPeor() { return null;}
	public void buscaNGeneraciones(int paso) {
		
	}
}
