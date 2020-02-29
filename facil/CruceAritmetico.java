package facil;

public class CruceAritmetico implements TipoCruce{

	private double alfa = 0.6;
	
	@Override
	public Cromosoma[] cruce(Cromosoma[] ind, double probCruce) {//para cromosomas reales solo
		double cruce = Math.random();
		if(cruce >= probCruce) return ind;
		
		Object[] genesP1 = ind[0].getGenes();
		Object[] genesP2 = ind[1].getGenes();
		Cromosoma h1 = ind[0].clonar();
		Cromosoma h2 = ind[1].clonar();
		for (int i = 0; i < genesP1.length; i++) {
			Double g1 = alfa*((Double)genesP1[i])+(1-alfa)*((Double)genesP2[i]); //a*p1i + (1-a)*p2i
			Double g2 = alfa*((Double)genesP2[i])+(1-alfa)*((Double)genesP1[i]); //a*p2i + (1-a)*p1i
			h1.setGen(i, g1);
			h2.setGen(i, g2);
		}
		
		return new Cromosoma[] {h1, h2};
	}

}
