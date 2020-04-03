package Gen;

public class CromosomaNDP5 extends CromosomaND {
	
	private int[][] distancias;
	private int[][] flujo;

	public CromosomaNDP5(int ngenes, int[][] distancias, int[][] flujo) {
		super(ngenes);
		this.distancias = distancias;
		this.flujo = flujo;
		for(int i = 0;i< ngenes;++i){
			this.genes[i] = i;
		}
	}

	@Override
	public Object getFenotipo() {
		Double costo = 0d;
		for (int i = 0; i < this.getNumGenes(); ++i) {
			for (int j = 0; j < this.getNumGenes(); ++j) {
				costo += distancias[i][j] + flujo[genes[i]][genes[j]];
			}
		}
		return costo;
	}

	@Override
	public Object[] getGenes() {
		return genes;
	}

	@Override
	public Cromosoma clonar() {
		CromosomaNDP5 crom = new CromosomaNDP5(genes.length, distancias, flujo);
		for (int i = 0; i < getNumGenes(); i++) {
			crom.setGen(i, getGen(i));
		}
		crom.setPuntAcc(getPuntAcc());
		crom.setPuntuacion(getPuntuacion());
		return crom;
	}

	@Override
	public int getGenLen(int i) {
		return genes[i];
	}

}
