package Core.Fitness;

import Gen.Cromosoma;

public enum TipoFitness implements Fitness {
	MAXIMIZAR() {
		@Override
		public int compare(Cromosoma o1, Cromosoma o2) {
			double l = (double)o1.getFenotipo();
			double r = (double)o2.getFenotipo();
			if (l < r)      return -1;//primero menor
			else if (l > r) return 1;
			else            return 0;
		}
	},
	MINIMIZAR() {
		@Override
		public int compare(Cromosoma o1, Cromosoma o2) {
			double l = (double)o1.getFenotipo();
			double r = (double)o2.getFenotipo();
			if (l < r) 		return 1;
			else if (l > r) return -1;
			else			return 0;
		}
	};
}
