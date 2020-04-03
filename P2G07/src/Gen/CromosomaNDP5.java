package Gen;

import java.util.ArrayList;

public class CromosomaNDP5 extends CromosomaND {
	
	private Integer[][] distancias;
	private Integer[][] flujo;

	public CromosomaNDP5(int ngenes, Integer[][] distancias, Integer[][] flujo) {
		super(ngenes);
		this.distancias = distancias;
		this.flujo = flujo;
		for(int i = 0;i< ngenes;++i){
			genes.add(i);
		}
	}

	@Override
	public Object getFenotipo() {
		Double costo = 0d;
		for (int i = 0; i < this.getNumGenes(); ++i) {
			for (int j = 0; j < this.getNumGenes(); ++j) {
				costo += distancias[i][j] * flujo[(Integer)genes.get(i)][(Integer)genes.get(j)];
			}
		}
		return costo;
	}

	@Override
	public ArrayList<Object> getGenes() {
		return genes;
	}

	@Override
	public Cromosoma clonar() {
		CromosomaNDP5 crom = new CromosomaNDP5(genes.size(), distancias, flujo);
		crom.genes.clear();
		for(var obj : genes){
			crom.genes.add(obj);
		}
		crom.setPuntAcc(getPuntAcc());
		crom.setPuntuacion(getPuntuacion());
		return crom;
	}

}
