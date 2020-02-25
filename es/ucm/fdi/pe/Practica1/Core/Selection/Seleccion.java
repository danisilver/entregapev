package Core.Selection;

import Gen.Cromosoma;

public abstract class Seleccion {
	
	protected int funcion;
	protected int tipo;
	
	public Seleccion(int func){
		funcion = func;
	}
	
	public Seleccion() {
		// TODO Auto-generated constructor stub
	}

	public abstract Cromosoma[] selecciona(Cromosoma[] poblacion, int tamPob);
	
}
