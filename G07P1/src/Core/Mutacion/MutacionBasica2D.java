package Core.Mutacion;

import Gen.Cromosoma;
import Gen.Cromosoma2DF1;

public class MutacionBasica2D implements TipoMutacion{
	
	public Cromosoma mutacion(Cromosoma crom, double probMutacion) {
		Cromosoma2DF1 c2d = (Cromosoma2DF1) crom;
		Integer gen1 = (Integer)c2d.getGen(0);
		Integer gen2 = (Integer)c2d.getGen(1);
		
		String brep = Cromosoma.fillZeros(Integer.toBinaryString(gen1), c2d.lgenx);
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
		Integer nx = Integer.parseInt(sb.toString(),2);

		brep = Cromosoma.fillZeros(Integer.toBinaryString(gen2), c2d.lgeny);
		sb = new StringBuffer();
		for(char c:brep.toCharArray()){
			double r = Math.random();
			if(r<probMutacion) {
				if(c=='0') sb.append('1');
				else sb.append('0');
			} else {
				sb.append(c);
			}
		}
		Integer ny = Integer.parseInt(sb.toString(),2);
		return new Cromosoma2DF1() {{ setGen(0, nx); setGen(1, ny); }};
	}

	@Override
	public Cromosoma[] mutacion(Cromosoma[] ind, double probMutacion) {
		Cromosoma[] ret = new Cromosoma[2];
		ret[0] = mutacion(ind[0], probMutacion);
		ret[1] = mutacion(ind[1], probMutacion);
		return ret;
	}

}
