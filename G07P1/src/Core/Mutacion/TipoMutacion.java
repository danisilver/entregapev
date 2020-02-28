package Core.Mutacion;

import Gen.Cromosoma;

public interface TipoMutacion{
	Cromosoma[] mutacion(Cromosoma[] ind, double probMutacion);
}
