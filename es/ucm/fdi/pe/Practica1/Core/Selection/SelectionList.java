package Core.Selection;

import java.util.List;

public class SelectionList {

	
	public static List<Seleccion> Selecciones;
	
	public SelectionList() {
		
		Selecciones.add(new Estocastico());
		Selecciones.add(new Ruleta());
		Selecciones.add(new Torneo());
		
	}
}
