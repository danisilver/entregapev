package Gen;

public abstract class Cromosoma2D extends Cromosoma{
	double xmax;
	double xmin;
	double ymax;
	double ymin;
	private int lgenx;
	private int lgeny;
	
	Integer x, y;
	
	@Override
	public Object getGen(int i) {
		if(i==0) return x;
		if(i==1) return y;
		else throw new IllegalArgumentException();
	}

	@Override
	public void setGen(int i, Object g) {
		if(i==0) x = (Integer)g;
		if(i==1) y = (Integer)g;
	}
	
	@Override
	public int getNumGenes() {
		return 2;
	}
	
	public abstract Object getFenotipo();

	public int getLgenx() {
		return lgenx;
	}

	public void setLgenx(int lgenx) {
		this.lgenx = lgenx;
	}

	public int getLgeny() {
		return lgeny;
	}

	public void setLgeny(int lgeny) {
		this.lgeny = lgeny;
	}
}
