package facil;

public class Cromosoma2DF2 extends Cromosoma2D{
	public Cromosoma2DF2() {
		xmax = 10;
		xmin =-10;
		ymax = 10;
		ymin =-10;
		lgenx = (int) Math.ceil(Math.log(1+(xmax-xmin)/tolerancia)/Math.log(2));
		lgeny = (int) Math.ceil(Math.log(1+(ymax-ymin)/tolerancia)/Math.log(2));
	}

	@Override
	public Object getFenotipo() {
		double cx = xmin + x*(xmax-xmin)/(Math.pow(2,lgenx)-1);
		double cy = ymin + y*(ymax-ymin)/(Math.pow(2,lgeny)-1);
		
		double res=0;
		res -= (Math.abs(
					Math.sin(cx)
					*Math.cos(cy)
					*Math.exp((Math.abs(1-(Math.sqrt(cx*cx + cy*cy)/Math.PI))))));
		return -res;
	}

	@Override
	public Object[] getGenes() {
		return new Object[]{x,y};
	}

	@Override
	public Cromosoma clonar() {
		return new Cromosoma2DF2();
	}
	
	@Override
	public int getGenLen(int i) {
		if(i==0) return lgenx;
		if(i==1) return lgeny;
		return 0;
	}
}
