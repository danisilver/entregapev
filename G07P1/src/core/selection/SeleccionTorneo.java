package core.selection;

import java.util.Random;

import gen.Cromosoma;
import utils.Utils;

public class SeleccionTorneo implements Seleccion{

	private int tamMuestra = 3;
	// cuando umbral < 1 el torneo es probabilistico
	// cuando umbral == 1 el torneo es deterministico (siempre el mejor)
	private double umbral = 1;
	
	private Random random = Utils.random;
	
	@Override
	public Cromosoma[] seleccion(Cromosoma[] poblacion) {
		int tamPoblacion = poblacion.length;
		int i = 0;
		Cromosoma[] ret = new Cromosoma[tamPoblacion];
		while(i < tamPoblacion) {
			ret[i] = torneo(poblacion); 
			i++;
		}
		
		return ret;
	}
	private Cromosoma torneo(Cromosoma[] poblacion) {
		int i = 0;
		Cromosoma mejor, peor;
		mejor = peor = poblacion[0];
		double mejorPunt, peorPunt;
		mejorPunt = 0;
		peorPunt = 1;
		while(i < getTamMuestra()) {
			int indice = random.nextInt(poblacion.length);
			if(poblacion[indice].getPuntuacion()>mejorPunt) {
				mejor = poblacion[indice];
				mejorPunt = mejor.getPuntuacion();
			}
			if(poblacion[indice].getPuntuacion()<peorPunt) {
				peor = poblacion[indice];
				peorPunt = peor.getPuntuacion();
			}
			i++;
		}
		
		if(random.nextDouble() < getUmbral()) return mejor;
		else return peor;
	}
	public int getTamMuestra() {
		return tamMuestra;
	}
	public void setTamMuestra(int tamMuestra) {
		this.tamMuestra = tamMuestra;
	}
	public double getUmbral() {
		return umbral;
	}
	public void setUmbral(double umbral) {
		this.umbral = umbral;
	}

}
