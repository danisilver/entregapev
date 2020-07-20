package core.cruce;

import gen.*;

public interface Cruce{
	Cromosoma[] cruce(Cromosoma[] ind, double probCruce);
	Cromosoma[] crossPair(Cromosoma[] cs);
}
