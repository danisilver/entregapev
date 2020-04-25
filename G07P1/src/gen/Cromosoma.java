package gen;

public abstract class Cromosoma{
	private double tolerancia;
	private double puntuacion;
	private double puntAcc;
	public abstract Object getGen(int i);
	public abstract void setGen(int i, Object g);
	public abstract Object getFenotipo();
	public abstract double value2optimize();
	public abstract Object[] getGenes();
	public abstract Cromosoma clonar();
	public abstract int getGenLen(int i);
	public abstract int getNumGenes();
//	public abstract Object string2gen(String rep);
//	public abstract String gen2string(Object gen);

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

}
