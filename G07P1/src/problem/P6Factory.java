package problem;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.HashMap;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import core.cruce.Cruce;
import core.cruce.CruceNodeXchng;
import core.fitness.Fitness;
import core.mutacion.Mutacion;
import core.mutacion.MutacionFuncionalSimple;
import core.mutacion.MutacionNodeRestart;
import core.mutacion.MutacionPermutarArgs;
import core.mutacion.MutacionTerminalSimple;
import core.selection.PoliMcPheeBloating;
import core.selection.Seleccion;
import core.selection.SeleccionEstocastica;
import core.selection.SeleccionRanking;
import core.selection.SeleccionRuleta;
import core.selection.SeleccionTorneo;
import core.selection.SeleccionTruncamiento;
import core.selection.TarpeianBloating;
import gen.Cromosoma;
import gen.CromosomaGramatica;
import model.MainModel;
import model.Observer;
import view.View;

/**
 * @author daniel
 * gramatica: 
 * 
	PROGRAM ::= OP0 | OP1 ;
	OP0     ::= if OP1 out OP0;
	OP1     ::= and IN OP1 | or IN OP1 | not IN | IN;
	OUT     ::= d0 | d1 | d2 | d3 | d4 ;
	IN      ::= a0 | a1 ;
 * 
 */
public class P6Factory implements ProblemFactory{

	private static View view;
	private Observer obs;

	@Override
	public Cromosoma createCromosoma(String tipo, HashMap<String, Object> props) {
		if (tipo.equalsIgnoreCase("Gramatica")) {
			Integer nAddrInputs = (Integer) props.get("nAddrInputs"); 
			String _useIf		= props.get("useIF").toString();
			String _profundidad = props.get("profundidad").toString();
			String _tipoCreacion= props.get("tipoCreacion").toString();
			Boolean useIf		= Boolean.valueOf(_useIf);
			Integer profundidad	= Integer.parseInt(_profundidad);
			Integer tipoCreacion= Integer.parseInt(_tipoCreacion);
			return new CromosomaGramatica(profundidad, tipoCreacion, useIf, nAddrInputs);
		}
		return null;
	}

	@Override
	public Cruce createCruce(String tipo, HashMap<String, Object> props) {
		if (tipo.equalsIgnoreCase("NodeXchng")) {
			return new CruceNodeXchng();
		}
		return null;
	}

	private Seleccion adapt2bloating(Seleccion sel, HashMap<String, Object> props) {
		String bloating = "";
		if(props.containsKey("bloating")) 
			bloating = props.get("bloating").toString();
		if(bloating.equalsIgnoreCase("Tarpeian")) {
			return new TarpeianBloating(sel);
		} else if(bloating.equalsIgnoreCase("PoliMcPhee")) {
			return new PoliMcPheeBloating(sel);
		} else {
			return sel;
		}
	}
	
	@Override
	public Seleccion createSeleccion(String tipo, HashMap<String, Object> props) {
		
		if(tipo.equalsIgnoreCase("Ruleta")) {
			return adapt2bloating(new SeleccionRuleta(), props);
		}
		else if(tipo.equalsIgnoreCase("Estocastico")) {
			Integer kindividuos = (Integer)props.get("kindividuos");
			SeleccionEstocastica seleccionEstocastica = new SeleccionEstocastica();
			seleccionEstocastica.setKindividuos(kindividuos);
			return adapt2bloating(seleccionEstocastica, props);
		}
		else if(tipo.equalsIgnoreCase("Torneo")) {
			Integer tamMuestra 	= (Integer) props.get("tamMuestra");
			Double umbral 		= (Double) props.get("umbral");
			SeleccionTorneo seleccionTorneo = new SeleccionTorneo();
			seleccionTorneo.setTamMuestra(tamMuestra);
			seleccionTorneo.setUmbral(umbral);
			return adapt2bloating(seleccionTorneo, props);
		}
		else if(tipo.equalsIgnoreCase("Ranking")) {
			Double beta 		= (Double) props.get("beta");
			SeleccionRanking seleccionRanking = new SeleccionRanking();
			seleccionRanking.setBeta(beta);
			return adapt2bloating(seleccionRanking, props);
		}
		else if(tipo.equalsIgnoreCase("Truncamiento")) {
			Double trunc 		= (Double) props.get("trunc");
			SeleccionTruncamiento seleccionTruncamiento = new SeleccionTruncamiento();
			seleccionTruncamiento.setTrunc(trunc);
			return adapt2bloating(seleccionTruncamiento, props);
		}
		return null;
	}

	@Override
	public Mutacion createMutacion(String tipo, HashMap<String, Object> props) {
		if (tipo.equalsIgnoreCase("FuncionalSimple")) {
			return new MutacionFuncionalSimple();
		} else if(tipo.equalsIgnoreCase("NodeRestart")) {
			return new MutacionNodeRestart();
		} else if(tipo.equalsIgnoreCase("PermutarArgs")) {
			return new MutacionPermutarArgs();
		} else if(tipo.equalsIgnoreCase("TerminalSimple")) {
			return new MutacionTerminalSimple();
		}
		return null;
	}

	@Override
	public Fitness createFitness(String tipo, HashMap<String, Object> props) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public View createView(HashMap<String, Object> props) {
		if(view!=null) return view;
		
		MainModel model = (MainModel) props.get("mainmodel");
		
		obs = new NAddrInputsObserver(props);
		obs.update();
		model.addPropObserver("nAddrInputs", obs);
		view = new View() {
			JPanel jPanel = new JPanel(new BorderLayout());
			@Override
			public Component getComponent() {
				return jPanel;
			}
		};
		return view;
	}
	
	@Override
	public Cromosoma[] createPoblacionInicial(
			String tipo, HashMap<String,Object> props, int tam) {
		String crom = props.get("tipoCromosoma").toString();
		String _tipoCreacion = props.get("tipoCreacion").toString();
		Integer tipoCreacion = Integer.parseInt(_tipoCreacion);
		if(tipoCreacion!=2) {
			return ProblemFactory.super.createPoblacionInicial(tipo, props, tam);
		} else { //Ramped and Half
			String _profundidad = props.get("profundidad").toString();
			Integer profundidad = Integer.parseInt(_profundidad);
			Integer profActual = 2;
			Cromosoma[] poblInicial = new Cromosoma[tam];
			for (int i = 0; i < tam; i++) {
				if(profActual > profundidad) profActual = profundidad;
				if((i%(tam/profundidad))==0) profActual++;
				props.put("tipoCreacion",
						(i%2==0)?Integer.valueOf(0):Integer.valueOf(1));
				Cromosoma nuevo = createCromosoma(crom, props);
				poblInicial[i]=nuevo;
			}
			props.put("tipoCreacion", tipoCreacion);
			
			return poblInicial;
		}
	}
	
	private final class NAddrInputsObserver extends SwingWorker<Integer,String[]> implements Observer {
		private final HashMap<String, Object> props;
		int nInputs, nOutputs/*, nDataInputs*/;
		Integer nAddrInputs = -1;
		NAddrInputsObserver worker;
		private String[] columns;
		private DefaultTableModel dataModel;
		
		private NAddrInputsObserver(HashMap<String, Object> props) {
			this.props = props;
		}

		@Override public void update() {
			Integer nAddrInputs = (Integer) props.get("nAddrInputs");
			if(this.nAddrInputs==nAddrInputs) return;
			if(this.worker==null)
				this.worker = new NAddrInputsObserver(props);
			else if(this.worker.isDone())
				this.worker = new NAddrInputsObserver(props);
			if(this.worker.getProgress()>0 && this.worker.getProgress()<100) {
				this.worker.cancel(true);
				this.worker = new NAddrInputsObserver(props);
			}
			this.worker.initWorker();
			this.worker.execute();
		}

		private void initWorker() {
			Integer nAddrInputs = (Integer) props.get("nAddrInputs");
			if(this.nAddrInputs==nAddrInputs) return;
			this.nAddrInputs = nAddrInputs;
			nInputs = (nAddrInputs + (1 << nAddrInputs));
			nOutputs = 1<<nInputs;
			columns = new String[nInputs+1];
			for (int i = 0; i < nInputs; i++) {
				if(i<nAddrInputs) columns[i] = "A"+(nAddrInputs-i-1);
				else columns[i] = "D"+(i-nAddrInputs);
			}
			columns[nInputs] = "Salida";
			dataModel = new DefaultTableModel(columns, 0);
		}

		@Override
		protected Integer doInBackground() throws Exception {
			String[][] data = new String[nOutputs][];
			
			for (int i = 0; i < nOutputs; i++) {
				data[i] = new String[nInputs+1]; 
				int _selInput = i >> (1 << nAddrInputs);
				int nDataInputs = nInputs - nAddrInputs;
				int _muxData  = ((i << (nAddrInputs)) & (nOutputs-1)) >> nAddrInputs;
				int muxOut_i  = ((_muxData & (1<<nDataInputs-1-_selInput)) > 0)? 1:0;
				data[i][nInputs] = String.valueOf(muxOut_i);
				String sel = utils.Utils.fillZeros(Integer.toBinaryString(_selInput), nAddrInputs);
				String dat = utils.Utils.fillZeros(Integer.toBinaryString(_muxData), nDataInputs);
				for (int j = 0; j < nInputs; j++) {
					if(j<nAddrInputs) data[i][j]= ""+sel.charAt((nAddrInputs-j-1));
					else data[i][j] = ""+dat.charAt(j-nAddrInputs);
				}
				publish(data[i]);
				if(i%2000==0) 
					Thread.sleep(1000);
				Thread.yield();
			}
			
			publish(data[nOutputs]);
			return 100;
		}
		
		@Override
		protected void process(List<String[]> chunks) {
			for (int i = dataModel.getRowCount(); i < chunks.size(); i++) {
				dataModel.addRow(chunks.get(i));
			}
			JTable jTable = new JTable(dataModel);
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
			jTable.setDefaultRenderer(String.class, centerRenderer);
			JScrollPane scrollPane = new JScrollPane(jTable);
			JComponent panelView = (JComponent)view.getComponent();
			panelView.removeAll();
			panelView.add(scrollPane,BorderLayout.CENTER);
			panelView.revalidate();
			panelView.repaint();
		}
	}

}
