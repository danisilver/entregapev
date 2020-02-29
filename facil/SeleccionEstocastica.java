package facil;

public class SeleccionEstocastica implements TipoSeleccion{

	int kindividuos = 2;
	int[] kindices;
	
	@Override
	public Cromosoma[] seleccion(Cromosoma[] poblacion) {
		Cromosoma[] ret = new Cromosoma[kindividuos];
		kindices = new int[kindividuos];
		double r = Math.random();
		for (int i = 0; i < kindividuos; i++) {
			int j = i+1; //j:1..k
			kindices[i] = (int)((r+j-1)/kindividuos);
		}
		
		for (int i = 0; i < kindices.length; i++) {
			ret[i] = poblacion[kindices[i]];
		}
		return ret;
	}
}
