package Core.Mutacion;

import Gen.*;
public interface TipoMutacion{
	Cromosoma[] mutacion(Cromosoma[] ind, double probMutacion);
}
