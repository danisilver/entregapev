package core.fitness;

import gen.Cromosoma;

public enum TipoFitness implements Fitness {
	MAXIMIZAR() {
		@Override
		public int compare(Cromosoma o1, Cromosoma o2) {
			double l = o1.value2optimize();
			double r = o2.value2optimize();
			if (l < r)      return -1;//primero menor
			else if (l > r) return 1;
			else            return 0;
		}
	},
	MINIMIZAR() {
		@Override
		public int compare(Cromosoma o1, Cromosoma o2) {
			double l = o1.value2optimize();
			double r = o2.value2optimize();
			if (l < r) 		return 1;
			else if (l > r) return -1;
			else			return 0;
		}
	};
}
