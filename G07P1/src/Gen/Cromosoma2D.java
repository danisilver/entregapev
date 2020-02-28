package Gen;

public abstract class Cromosoma2D extends Cromosoma{
	double xmax;
	double xmin;
	double ymax;
	double ymin;
	public int lgenx;
	public int lgeny;
	
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
	
	public abstract Object getFenotipo();
}
