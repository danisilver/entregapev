package Gen;

public class CromosomaNDP5 extends CromosomaND {
	
	private Double[][] distancias;
	private Double[][] flujo;

	public CromosomaNDP5(int ngenes, Double[][] distancias, Double[][] flujo) {
		super(ngenes);
		this.distancias = distancias;
		this.flujo = flujo;
	}

	@Override
	public Object getFenotipo() {
		Double costo = 0d;
		for (int i = 0; i < distancias.length; i++) {
			for (int j = 0; j < distancias[0].length; j++) {
				costo += distancias[i][j] + flujo[(Integer) getGen(i)][(Integer)getGen(j)];
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
