package Core.Selection;

import Gen.Cromosoma;

public abstract class Seleccion {
	
	protected int funcion;
	
	public Seleccion(int func){
		funcion = func;
	}
	
	public abstract Cromosoma[] selecciona(Cromosoma[] poblacion, int tamPob);
	
}
