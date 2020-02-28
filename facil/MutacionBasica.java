package facil;

public class MutacionBasica implements TipoMutacion{//mutacion bit a bit de cada gen
	
	public Cromosoma mutacion(Cromosoma crom, double probMutacion) {
		
		Cromosoma nuevo = crom.clonar();
		
		Object[] genes = crom.getGenes();
		for (int i = 0; i < genes.length; i++) {
			Integer gen = (Integer)genes[i];
			String brep = Cromosoma.fillZeros(Integer.toBinaryString(gen), crom.getGenLen(i));
			StringBuffer sb = new StringBuffer();
			for(char c:brep.toCharArray()){
				double r = Math.random();
				if(r<probMutacion) {
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

	@Override
	public Cromosoma[] mutacion(Cromosoma[] ind, double probMutacion) {
		Cromosoma[] ret = new Cromosoma[2];
		ret[0] = mutacion(ind[0], probMutacion);
		ret[1] = mutacion(ind[1], probMutacion);
		return ret;
	}

}
