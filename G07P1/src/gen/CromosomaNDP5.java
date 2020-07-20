package gen;

public class CromosomaNDP5 extends CromosomaND {
	
	private int[][] distancias;
	private int[][] flujo;
	private boolean evaluated = false;
	private double savedFenotype;

	public CromosomaNDP5(int ngenes, int[][] distancias, int[][] flujo) {
		super(ngenes);
		this.distancias = distancias;
		this.flujo = flujo;
	}

	@Override
	public Object getFenotipo() {
		if(evaluated) return savedFenotype;
		Double costo = 0d;
		for (int i = 0; i < distancias.length; i++) {
			for (int j = 0; j < distancias[0].length; j++) {
				costo += distancias[i][j] * flujo[(Integer) getGen(i)-1][(Integer)getGen(j)-1];
			}
		}
		savedFenotype = costo;
		evaluated = true;
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
	public void setGen(int i, Object g) {
		super.setGen(i, g);
		evaluated = false;
	}

	@Override
	public int getGenLen(int i) {
		return genes[i];
	}
	
	@Override
	public double value2optimize() {
		return (double) getFenotipo();
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for (int i = 0; i < genes.length-1; i++) {
			sb.append(genes[i]-1+"; ");
		}
		sb.append(genes[genes.length-1]-1+"}");
		return sb.toString()+" v2o:"+value2optimize();
	}
	
}
