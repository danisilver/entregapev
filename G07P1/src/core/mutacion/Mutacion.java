package core.mutacion;

import gen.*;
public interface Mutacion{
	Cromosoma[] mutacion(Cromosoma[] ind, double probMutacion);
	public Cromosoma mutarInd(Cromosoma individuo);
}
