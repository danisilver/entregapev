package Gen;

public abstract class Cromosoma{
	public static double tolerancia;
	private double puntuacion;
	private double puntAcc;
	public abstract Object getGen(int i);
	public abstract void setGen(int i, Object g);
	public abstract Object getFenotipo();
	public abstract Object[] getGenes();
	public abstract Cromosoma clonar();
	
	public static String fillZeros(String ind, int nbits) {
		StringBuffer pad = new StringBuffer(nbits);
		nbits -= ind.length();
		for(int i=0; i<nbits; i++) pad.append('0');
		return pad.append(ind).toString();
	}
	public double getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}
	public double getPuntAcc() {
		return puntAcc;
	}
	public void setPuntAcc(double puntAcc) {
		this.puntAcc = puntAcc;
	}
	public double getTolerancia() {
		return tolerancia;
	}
	public void setTolerancia(double tolerancia) {
		this.tolerancia = tolerancia;
	}
	public abstract int getGenLen(int i);

}
