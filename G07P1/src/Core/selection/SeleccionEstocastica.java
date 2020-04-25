package core.selection;

import java.util.Random;

import gen.Cromosoma;
import utils.Utils;

public class SeleccionEstocastica implements Seleccion{

	private int kindividuos = 3;
	private Random random = Utils.random;
	
	@Override
	public Cromosoma[] seleccion(Cromosoma[] poblacion) {
		
		int tamPoblacion = poblacion.length;
		Cromosoma[] nuevaPob = new Cromosoma[tamPoblacion];
		
		Cromosoma[] ret = selectKInd(poblacion);
		nuevaPob[0] = ret[0].clonar();
		int tam = 1;
		while(tam<tamPoblacion) {
			//solo actualizamos ret cuando ya hemos insertado los anteriores
			if(tam%getKindividuos()==0) 
				ret = selectKInd(poblacion);
			nuevaPob[tam] = ret[tam%getKindividuos()].clonar();
			tam++;
		}
		return nuevaPob;
	}

	private Cromosoma[] selectKInd(Cromosoma[] poblacion) {
		int tamPoblacion = poblacion.length;
		
		Cromosoma[] ret = new Cromosoma[getKindividuos()];
		double r = random.nextDouble();
		
		double dist = 1d / getKindividuos();
		double prob = r*dist;
		int i, j;
		i = j = 0;
		while (i < getKindividuos()) {
			while(prob > poblacion[j].getPuntAcc()) 
				j++;
			prob+=dist;
			ret[i] = poblacion[tamPoblacion-1-j];
			i++;
		}
		return ret;
	}

	public int getKindividuos() {
		return kindividuos;
	}

	public void setKindividuos(int kindividuos) {
		this.kindividuos = kindividuos;
	}
}
