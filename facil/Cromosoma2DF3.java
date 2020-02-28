package facil;

public class Cromosoma2DF3 extends Cromosoma2D{
	
	public Cromosoma2DF3() {
		xmax = 10;
		xmin =-10;
		ymax = 10;
		ymin =-10;
		lgenx = (int) Math.ceil(Math.log(1+(xmax-xmin)/tolerancia)/Math.log(2));
		lgeny = (int) Math.ceil(Math.log(1+(ymax-ymin)/tolerancia)/Math.log(2));
	}
	
	@Override
	public Object getGen(int i) {
		if(i==0) return x;
		else if (i==1) return y;
		else throw new IllegalArgumentException();
	}

	@Override
	public void setGen(int i, Object g) {
		if(i==0) x=(Integer)g;
		else if (i==1) y=(Integer)g;
	}

	@Override
	public Object getFenotipo() {
		double cx = xmin + x*(xmax-xmin)/(Math.pow(2,lgenx)-1);
		double cy = ymin + y*(ymax-ymin)/(Math.pow(2,lgeny)-1);
		double res=0;
		for (int i = 0; i < 5 ; i++) {
			res+=i*Math.cos(((i+1)*cx)+i)*i*Math.cos(((i+1)*cy)+i);
		}
		return -res;
	}

	@Override
	public Object[] getGenes() {
		return new Object[] {x,y};
	}

	@Override
	public Cromosoma clonar() {
		return new Cromosoma2DF3();
	}
	
	@Override
	public int getGenLen(int i) {
		if(i==0) return lgenx;
		if(i==1) return lgeny;
		return 0;
	}

}
