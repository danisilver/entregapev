
package Core.Cruce;

import Gen.*;

public class CruceMonoPunto2D implements TipoCruce{
	@Override
	public Cromosoma[] cruce(Cromosoma[] ind, double probCruce) {
		Cromosoma2DF1 cromosoma1 = (Cromosoma2DF1)ind[0];
		Cromosoma2DF1 cromosoma2 = (Cromosoma2DF1)ind[1];
		
		Integer c1gen1 = (Integer)cromosoma1.getGen(0);
		Integer c1gen2 = (Integer)cromosoma1.getGen(1);
		
		Integer c2gen1 = (Integer)cromosoma2.getGen(0);
		Integer c2gen2 = (Integer)cromosoma2.getGen(1);
		
		int lgenx = cromosoma1.lgenx;
		int lgeny = cromosoma1.lgeny;
		
		double muta = Math.random();
		if(muta >= probCruce) return ind;
		
		double prob, pacc, r;
		int i;
		prob = 1f/(cromosoma1.lgenx-1);
		pacc = 0;
		r = Math.random();
		i = 0;
		while(i<lgenx){
			if(r<=pacc) break;
			pacc+=prob;
			i++;
		}
		String h1rep = Cromosoma.fillZeros(Integer.toBinaryString(c1gen1), lgenx);
		String h2rep = Cromosoma.fillZeros(Integer.toBinaryString(c2gen1), lgenx);
		String h1 = h1rep.substring(0,i).concat(h2rep.substring(i,lgenx));
		String h2 = h2rep.substring(0,i).concat(h1rep.substring(i,lgenx));
		Integer a = Integer.parseInt(h1,2);
		Integer b = Integer.parseInt(h2,2);

		prob = 1f/(lgeny-1);
		pacc=0;
		r = Math.random();
		i = 0;
		while(i<lgeny){
			if(r<=pacc) break;
			pacc+=prob;
			i++;
		}
		h1rep = Cromosoma.fillZeros(Integer.toBinaryString(c1gen2), lgeny);
		h2rep = Cromosoma.fillZeros(Integer.toBinaryString(c2gen2), lgeny);
		h1 = h1rep.substring(0,i).concat(h2rep.substring(i,lgeny));
		h2 = h2rep.substring(0,i).concat(h1rep.substring(i,lgeny));
		Integer c = Integer.parseInt(h1,2);
		Integer d = Integer.parseInt(h2,2);
		Cromosoma2DF1 hijo1 = new Cromosoma2DF1();
		hijo1.setGen(0, a);
		hijo1.setGen(1, c);
		Cromosoma2DF1 hijo2 = new Cromosoma2DF1();
		hijo2.setGen(0, b);
		hijo2.setGen(1, d);
		return new Cromosoma[]{hijo1, hijo2};
	}

}
