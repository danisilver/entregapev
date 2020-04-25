package core.mutacion;

import gen.*;
public interface Mutacion{
	Cromosoma[] mutacion(Cromosoma[] ind, double probMutacion);
}
