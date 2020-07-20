package core.mutacion;

import java.util.Random;

import gen.Cromosoma;
import utils.Utils;

public class MutacionBasica implements Mutacion{//mutacion bit a bit de cada gen
	
	private Random random = Utils.random;
	private double probFlipBit = 0.15d;
	
	@Override
	public Cromosoma[] mutacion(Cromosoma[] poblacion, double probMutacion) {
		
		int tamPoblacion = poblacion.length;
		Cromosoma[] ret = new Cromosoma[tamPoblacion];
		
		int tam = 0;
		while(tam < tamPoblacion) {
			if(random.nextDouble() < probMutacion)
				ret[tam] = mutarInd(poblacion[tam]);
			else
				ret[tam] = poblacion[tam].clonar();
			tam++;
		}
		
		return ret;
	}

	@Override
	public Cromosoma mutarInd(Cromosoma crom) {
		Cromosoma nuevo = crom.clonar();
		
		Object[] genes = crom.getGenes();
		for (int i = 0; i < genes.length; i++) {
			Integer gen = (Integer)genes[i];
			String brep = Utils.fillZeros(Integer.toBinaryString(gen), crom.getGenLen(i));
			StringBuffer sb = new StringBuffer();
			for(char c:brep.toCharArray()){
				double prob = random.nextDouble();
				if(prob < getProbFlipBit()) {
					if(c=='0') sb.append('1');
					else sb.append('0');
				} else {
					sb.append(c);
				}
			}
			nuevo.setGen(i, Integer.parseInt(sb.toString(),2));
		}
		
		return nuevo;
	}

	public double getProbFlipBit() {
		return probFlipBit;
	}

	public void setProbFlipBit(double probFlipBit) {
		this.probFlipBit = probFlipBit;
	}

}
