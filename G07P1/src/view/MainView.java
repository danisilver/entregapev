package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

import org.math.plot.Plot2DPanel;

import model.MainModel;
import problem.ConcreteFactory;
import utils.CustomHyperLinkListener;
import utils.ModernScrollPane;
import utils.Utils.TitleClickAdapter;

public class MainView extends JPanel implements View{
	//take the mainmodel and render view

	private static final long serialVersionUID = 1L;
	private JFrame window;
	private MainModel model;
	
	public JPanel panel_6;//plot2d
	public JPanel panel_7;//plot3d
	
	public JProgressBar progressBar;
	public JButton btnPaso;
	public JButton btnEjecutar;
	public JSpinner spinnerTamPoblacion;
	public JSpinner spinnerNGeneraciones;
	public JTextField tfTolerancia;
	public JSpinner spinnerPorcntjElitismo;
	public JSpinner spinnerProbCruce;
	public JSpinner spinnerProbMutacion;
	public JComboBox<Object> cbFuncionSeleccionada;
	public JComboBox<Object> cbTipoSeleccion;
	public JComboBox<Object> cbTipoCruce;
	public JComboBox<Object> cbTipoMutacion;
	public JComboBox<Object> cbTipoCromosoma;
	public JComboBox<Object> cbDatosOptimizar;
	public JComboBox<Object> cbGramaticaInit;
	public JCheckBox checkboxInstrIF;
	public JSpinner spinnerMaxDepth;
	public JSpinner spinnerNaddrInputs;
	public JSpinner spinnerNumVariables;
	public JTextArea jtaLog;
	public JSpinner spinnerMinX;
	public JSpinner spinnerMaxX;
	public JSpinner spinnerMinY;
	public JSpinner spinnerMaxY;
	public JCheckBox checkboxRandomSeed;
	public JFormattedTextField tfSeed;
	public JSpinner spinnerKindividuos;
	public JSpinner spinnerBeta;
	public JSpinner spinnerUmbral;
	public JSpinner spinnerTamMuestra;
	public JSpinner spinnerTrunc;
	public JSpinner spinnerAlfa;
	public JSpinner spinnerNumGens2Xchng;
	public JSpinner spinnerProbXchngGen;
	public JSpinner spinnerProbFlipBit;
	public JSpinner spinnerNumGen2Perm;
	public JSpinner spinnerNumGens2Ins;
	public JSpinner spinnerNumG2Inv;
	private JLabel lblMinX;
	private JLabel lblMaxX;
	private JLabel lblMinY;
	private JLabel lblMaxY;
	private JLabel lblKindividuos;
	private JLabel lblTamMuestra;
	private JLabel lblUmbral;
	private JLabel lblBeta;
	private JLabel lblTrunc;
	private JLabel lblProbXchngGen;
	private JLabel lblAlfa;
	private JLabel lblNumGens2Xchng;
	private JLabel lblProbFlipBit;
	private JLabel lblNumGen2Perm;
	private JLabel lblNumGens2Ins;
	private JLabel lblNumG2Inv;
	private JLabel lblNumVariables;
	private JLabel lblDatosOptimizar;
	private JLabel lblMaxDepth;
	private JLabel lblNaddrInputs;
	private JLabel lblcbGramaticaInit;
	private DefaultComboBoxModel<Object> modelCromosomas;
	private DefaultComboBoxModel<Object> modelSelecciones;
	private DefaultComboBoxModel<Object> modelCruces;
	private DefaultComboBoxModel<Object> modelMutaciones;
	private DefaultComboBoxModel<Object> modelFunciones;
	private JTabbedPane tabbedPane;
	public Plot2DPanel p2d;
	private JEditorPane jEditorPane;
	private DefaultComboBoxModel<Object> modelGramaticaInit;
	private JLabel lblcbBloating;
	private JComboBox<Object> cbBloating;
	private DefaultComboBoxModel<Object> modelBloating;
	private JLabel lblTarpeianN;
	private JSlider jslidderTarpeianN;

	public MainView(MainModel model) {
		this.model = model;
		window = new JFrame(model.getTitle());
		window.setVisible(true);
		window.setTitle("Programación evolutiva");
		window.setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/org/math/plot/icons/position.png")));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(100, 100, 703, 526);
		setDoubleBuffered(true);
		setLayout(new BorderLayout(0, 0));
		window.setContentPane(this);
		addAllComponents();
		registerEvents();
		updateCromosomaProps();
	}

	public void updateView() {
		updateGeneralPanel();
		updateCromosomaPanel();
		updateSeleccionPanel();
		updateCrucePanel();
		updateMutacionPanel();
	}
	
	public void updateGeneralPanel() {
		Integer tamP			= (Integer) model.getPropValue("tamPoblacion");
		spinnerTamPoblacion.setValue(tamP);
		
		Integer niters		  	= (Integer) model.getPropValue("maxIteraciones");
		SpinnerNumberModel sngm = (SpinnerNumberModel) spinnerNGeneraciones.getModel();
		spinnerNGeneraciones.setModel(
				new SpinnerNumberModel(niters, sngm.getMinimum(), sngm.getMaximum(), sngm.getStepSize()));
		
		Double tol				= (Double) model.getPropValue("tolerancia");
		tfTolerancia.setText(String.valueOf(tol));
		
		String self				= model.getPropValue("funcion").toString();
		List<Object> cblist		= model.getPropValues("funcion");
		modelFunciones = new DefaultComboBoxModel<>(cblist.toArray());
		modelFunciones.setSelectedItem(self);
		cbFuncionSeleccionada.setModel(modelFunciones );
		
		Boolean randEnabled 	= (Boolean)model.getPropValue("randomSeed");
		checkboxRandomSeed.setSelected(randEnabled);
		tfSeed.setEnabled(!randEnabled);
		Long seed 				= (Long)model.getPropValue("seed");
		tfSeed.setValue(seed);
		
		String selDatosFich 	= model.getPropValue("fichero").toString();
		cbDatosOptimizar.setSelectedItem(selDatosFich);
		
		refreshFuncionFields(self);
	}
	

	public void updateCromosomaPanel() {
		List<Object> cblist 	= model.getPropValues("tipoCromosoma");
		String selCrom 			= model.getPropValue("tipoCromosoma").toString();
		modelCromosomas = new DefaultComboBoxModel<Object>(cblist.toArray());
		if(modelCromosomas.getIndexOf(selCrom)>=0)
			modelCromosomas.setSelectedItem(selCrom);
		else
			selCrom = modelCromosomas.getSelectedItem().toString();
		cbTipoCromosoma.setModel(modelCromosomas);
		
		refreshCromosomaFields(selCrom);
	}
	
	public void updateSeleccionPanel() {
		List<Object> cblist 	= model.getPropValues("tipoSeleccion");
		String selSel 			= model.getPropValue("tipoSeleccion").toString();
		modelSelecciones = new DefaultComboBoxModel<>(cblist.toArray());
		if(modelSelecciones.getIndexOf(selSel)>=0)
			modelSelecciones.setSelectedItem(selSel);
		else
			selSel = modelSelecciones.getSelectedItem().toString();
		cbTipoSeleccion.setModel(modelSelecciones);
		refreshSelectionFields(selSel);
	}
	
	public void updateCrucePanel() {
		List<Object> cblist 	= model.getPropValues("tipoCruce");
		String selCruce 		= model.getPropValue("tipoCruce").toString();
		modelCruces = new DefaultComboBoxModel<>(cblist.toArray());
		if(modelCruces.getIndexOf(selCruce)>=0)
			modelCruces.setSelectedItem(selCruce);
		else 
			selCruce = modelCruces.getSelectedItem().toString();
		cbTipoCruce.setModel(modelCruces);
		refreshCruceFields(selCruce);
	}
	
	public void updateMutacionPanel() {
		List<Object> cblist 	= model.getPropValues("tipoMutacion");
		String selMut 			= model.getPropValue("tipoMutacion").toString();
		modelMutaciones = new DefaultComboBoxModel<>(cblist.toArray());
		if(modelMutaciones.getIndexOf(selMut)>=0)
			modelMutaciones.setSelectedItem(selMut);
		else
			selMut = modelMutaciones.getSelectedItem().toString();
		cbTipoMutacion.setModel(modelMutaciones);
		refreshMutacionFields(selMut);
	}
	
	public void updateFuncionUI(String c) {
		JComponent[] comps = new JComponent[] {
				lblDatosOptimizar, cbDatosOptimizar
		};
		Arrays.asList(comps).forEach(cmp->cmp.setVisible(false));
		if(c.indexOf("datosOptimizar")!=-1) {
			lblDatosOptimizar.setVisible(true);
			cbDatosOptimizar.setVisible(true);
		}
	}
	
	public void updateCromosomaUI(String c) {
		JComponent[] comps = new JComponent[] {
				lblNumVariables, spinnerNumVariables,
				lblMinX, spinnerMinX, lblMaxX, spinnerMaxX, 
				lblMinY, spinnerMinY, lblMaxY, spinnerMaxY,
				lblMaxDepth, spinnerMaxDepth, 
				lblNaddrInputs, spinnerNaddrInputs, 
				lblcbGramaticaInit, cbGramaticaInit,
				checkboxInstrIF,
				lblcbBloating, cbBloating
				
		};
		Arrays.asList(comps).forEach(cmp->cmp.setVisible(false));
		if(c.indexOf("numVariables")!=-1) {
			lblNumVariables.setVisible(true); spinnerNumVariables.setVisible(true);
		}
		if(c.indexOf("minX")!=-1) {
			lblMinX.setVisible(true); spinnerMinX.setVisible(true);
		}
		if(c.indexOf("maxX")!=-1) {
			lblMaxX.setVisible(true); spinnerMaxX.setVisible(true);
		}
		if(c.indexOf("minY")!=-1) {
			lblMinY.setVisible(true); spinnerMinY.setVisible(true);
		}
		if(c.indexOf("maxY")!=-1) {
			lblMaxY.setVisible(true); spinnerMaxY.setVisible(true);
		}
		if(c.indexOf("maxDepth")!=-1) {
			lblMaxDepth.setVisible(true); spinnerMaxDepth.setVisible(true);
		}
		if(c.indexOf("nAddrInputs")!=-1) {
			lblNaddrInputs.setVisible(true); spinnerNaddrInputs.setVisible(true);
		}
		if(c.indexOf("cbGramaticaInit")!=-1) {
			lblcbGramaticaInit.setVisible(true); cbGramaticaInit.setVisible(true);
		}
		if(c.indexOf("instrIF")!=-1){
			checkboxInstrIF.setVisible(true);
		}
		if(c.indexOf("bloating")!=-1) {
			lblcbBloating.setVisible(true); cbBloating.setVisible(true);
			
		}
	}

	public void updateMutacionUI(String c) {
		JComponent[] comps = new JComponent[] {lblProbFlipBit, spinnerProbFlipBit, 
				lblNumGens2Ins, lblNumGens2Ins, spinnerNumGens2Ins, 
				lblNumG2Inv, spinnerNumG2Inv, lblNumGen2Perm, spinnerNumGen2Perm};
		Arrays.asList(comps).forEach(cmp->cmp.setVisible(false));
		if(c.indexOf("probFlipBit")!=-1) {
			lblProbFlipBit.setVisible(true);
			spinnerProbFlipBit.setVisible(true);
		}
		if(c.indexOf("numGens2Ins")!=-1) {
			lblNumGens2Ins.setVisible(true);
			spinnerNumGens2Ins.setVisible(true);
		}
		if(c.indexOf("numG2Inv")!=-1) {
			lblNumG2Inv.setVisible(true);
			spinnerNumG2Inv.setVisible(true);
		}
		if(c.indexOf("numGen2Perm")!=-1) {
			lblNumGen2Perm.setVisible(true);
			spinnerNumGen2Perm.setVisible(true);
		}
	}

	public void updateCruceUI(String c) {
		JComponent[] comps = new JComponent[] {
				lblProbXchngGen, spinnerProbXchngGen, lblAlfa, spinnerAlfa,
				lblNumGens2Xchng, spinnerNumGens2Xchng
		};
		Arrays.asList(comps).forEach(cmp->cmp.setVisible(false));
		if(c.indexOf("probXchngGen")!=-1) {
			lblProbXchngGen.setVisible(true);
			spinnerProbXchngGen.setVisible(true);
		}
		if(c.indexOf("alfa")!=-1) {
			lblAlfa.setVisible(true);
			spinnerAlfa.setVisible(true);
		}
		if(c.indexOf("numGens2Xchng")!=-1) {
			lblNumGens2Xchng.setVisible(true);
			spinnerNumGens2Xchng.setVisible(true);
		}
	}

	public void updateSeleccionUI(String c) {
		JComponent[] comps = new JComponent[] {
				lblKindividuos, spinnerKindividuos, lblTamMuestra, spinnerTamMuestra,
				lblUmbral, spinnerUmbral, lblBeta, spinnerBeta, lblTrunc, spinnerTrunc
		};
		Arrays.asList(comps).forEach(cmp->cmp.setVisible(false));
		if(c.indexOf("kindividuos")!=-1) {
			lblKindividuos.setVisible(true);
			spinnerKindividuos.setVisible(true);
		}
		if(c.indexOf("tamMuestra")!=-1) {
			lblTamMuestra.setVisible(true);
			spinnerTamMuestra.setVisible(true);
		}
		if(c.indexOf("umbral")!=-1) {
			lblUmbral.setVisible(true);
			spinnerUmbral.setVisible(true);
		}
		if(c.indexOf("beta")!=-1) {
			lblBeta.setVisible(true);
			spinnerBeta.setVisible(true);
		}
		if(c.indexOf("trunc")!=-1) {
			lblTrunc.setVisible(true);
			spinnerTrunc.setVisible(true);
		}
	}
	
	private void registerEvents() {
		checkboxRandomSeed.addActionListener(e-> 
		model.setPropValue( "randomSeed", Boolean.valueOf(checkboxRandomSeed.isSelected())));
		tfSeed.addActionListener(e->
		model.setPropValue("seed", Long.valueOf(tfSeed.getValue().toString())));
		btnEjecutar.addActionListener(e->
		model.setPropValue("randomSeed", Boolean.valueOf(checkboxRandomSeed.isSelected())));
		cbFuncionSeleccionada.addActionListener(e->
		model.setPropValue("funcion", cbFuncionSeleccionada.getSelectedItem()));
		cbTipoCromosoma.addActionListener(e->
		model.setPropValue("tipoCromosoma", cbTipoCromosoma.getSelectedItem()));
		cbTipoSeleccion.addActionListener(e->
		model.setPropValue("tipoSeleccion", cbTipoSeleccion.getSelectedItem()));
		cbTipoCruce.addActionListener(e->
		model.setPropValue("tipoCruce", cbTipoCruce.getSelectedItem()));
		cbTipoMutacion.addActionListener(e->
		model.setPropValue("tipoMutacion", cbTipoMutacion.getSelectedItem()));
		cbDatosOptimizar.addActionListener(e->
		model.setPropValue("fichero", cbDatosOptimizar.getSelectedItem()));
		spinnerNumVariables.addChangeListener(e->
		model.setPropValue("numVariables", (Integer) spinnerNumVariables.getValue()));
		tfTolerancia.addActionListener(e->
		model.setPropValue("tolerancia", Double.parseDouble(tfTolerancia.getText())));
		spinnerTamPoblacion.addChangeListener(e->
		model.setPropValue("tamPoblacion", (Integer) spinnerTamPoblacion.getValue()));
		spinnerNGeneraciones.addChangeListener(e->
		model.setPropValue("maxIteraciones", (Integer) spinnerNGeneraciones.getValue()));
		spinnerPorcntjElitismo.addChangeListener(e->
		model.setPropValue("%elitismo", (Double) spinnerPorcntjElitismo.getValue()));
		spinnerProbCruce.addChangeListener(e->
		model.setPropValue("probCruce", (Double) spinnerProbCruce.getValue()));
		spinnerProbMutacion.addChangeListener(e->
		model.setPropValue("probMutacion", (Double) spinnerProbMutacion.getValue()));
		spinnerMinX.addChangeListener(e->
		model.setPropValue("minX", (Double) spinnerMinX.getValue()));
		spinnerMaxX.addChangeListener(e->
		model.setPropValue("maxX", (Double) spinnerMaxX.getValue()));
		spinnerMinY.addChangeListener(e->
		model.setPropValue("minY", (Double) spinnerMinY.getValue()));
		spinnerMaxY.addChangeListener(e->
		model.setPropValue("maxY", (Double) spinnerMaxY.getValue()));
		spinnerKindividuos.addChangeListener(e->
		model.setPropValue("kindividuos", (Integer) spinnerKindividuos.getValue()));
		spinnerBeta.addChangeListener(e->
		model.setPropValue("beta", (Double) spinnerBeta.getValue()));
		spinnerUmbral.addChangeListener(e->
		model.setPropValue("umbral", (Double) spinnerBeta.getValue()));
		spinnerTamMuestra.addChangeListener(e->
		model.setPropValue("tamMuestra", (Integer) spinnerTamMuestra.getValue()));
		spinnerTrunc.addChangeListener(e->
		model.setPropValue("trunc", (Integer) spinnerTrunc.getValue()));
		spinnerAlfa.addChangeListener(e->
		model.setPropValue("alfa", (Double) spinnerAlfa.getValue()));
		spinnerNumGens2Xchng.addChangeListener(e->
		model.setPropValue("numGens2Xchng", (Integer) spinnerNumGens2Xchng.getValue()));
		spinnerProbXchngGen.addChangeListener(e->
		model.setPropValue("probXchngGen", (Double) spinnerProbXchngGen.getValue()));
		spinnerProbFlipBit.addChangeListener(e->
		model.setPropValue("probFlipBit", (Double) spinnerProbFlipBit.getValue()));
		spinnerNumGen2Perm.addChangeListener(e->
		model.setPropValue("numGen2Perm", (Integer) spinnerNumGen2Perm.getValue()));
		spinnerNumGens2Ins.addChangeListener(e->
		model.setPropValue("numGens2Ins", (Integer) spinnerNumGens2Ins.getValue()));
		spinnerNumG2Inv.addChangeListener(e->
		model.setPropValue("numG2Inv", (Integer) spinnerNumG2Inv.getValue()));
		spinnerMaxDepth.addChangeListener(e->
		model.setPropValue("profundidad", spinnerMaxDepth.getValue()));
		spinnerNaddrInputs.addChangeListener(e->
		model.setPropValue("nAddrInputs", spinnerNaddrInputs.getValue()));
		cbGramaticaInit.addActionListener(e->
		model.setPropValue("tipoCreacion", cbGramaticaInit.getSelectedIndex()));
		checkboxInstrIF.addActionListener(e->
		model.setPropValue("useIF", checkboxInstrIF.isSelected()));
		cbBloating.addActionListener(e->
		model.setPropValue("bloating", cbBloating.getSelectedItem()));
		tabbedPane.addChangeListener(e->updateProblemView());
		btnPaso.addActionListener(e->
		p2d.removeAllPlots());
		btnPaso.addActionListener(e->
		jtaLog.setText(""));
		jslidderTarpeianN.addChangeListener(e->
		model.setPropValue("tarpeianDeathProportion", jslidderTarpeianN.getValue()));
	}
	
	public void updateProgressBar() {
		int searchProgress 		= model.getSearchProgress();
		Integer maxIteraciones 	= (Integer) model.getPropValue("maxIteraciones");
		progressBar.setMaximum(maxIteraciones);
		progressBar.setValue(searchProgress++);
		model.setSearchProgress(searchProgress);
	}
	
	public void updateProblemView() {
		String funcion          = model.getPropValue("funcion").toString();
		ConcreteFactory factory = new ConcreteFactory(funcion);
        Component pv = factory.createView(model.getPropsMap()).getComponent();
        panel_7.removeAll();
        panel_7.add(pv);
        panel_7.revalidate();
	}

	@Override
	public JComponent getComponent() {
		return this;
	}

	private void refreshFuncionFields(String fun) {
		if(fun.equalsIgnoreCase("funcion 1")) updateFuncionUI("");
		else if(fun.equalsIgnoreCase("Holder table")) updateFuncionUI("");
		else if(fun.equalsIgnoreCase("Schubert")) updateFuncionUI("");
		else if(fun.equalsIgnoreCase("Michalewicz")) updateFuncionUI("");
		else if(fun.equalsIgnoreCase("Problema5")) updateFuncionUI("datosOptimizar"); 
		else if(fun.equalsIgnoreCase("Multiplexor")) updateFuncionUI(""); 
	}
	
	public void refreshMutacionFields(String mut) {
		if(mut.equalsIgnoreCase("Basica")) updateMutacionUI("probFlipBit");
		else if(mut.equalsIgnoreCase("Uniforme")) updateMutacionUI("");
		else if(mut.equalsIgnoreCase("Insercion")) updateMutacionUI("numGens2Ins");
		else if(mut.equalsIgnoreCase("Intercambio")) updateMutacionUI("numG2Inv");
		else if(mut.equalsIgnoreCase("Inversion")) updateMutacionUI("");
		else if(mut.equalsIgnoreCase("Heuristica")) updateMutacionUI("numGen2Perm");
		else if(mut.equalsIgnoreCase("TerminalSimple")) updateMutacionUI("");
		else if(mut.equalsIgnoreCase("FuncionalSimple")) updateMutacionUI("");
		else if(mut.equalsIgnoreCase("NodeRestart")) updateMutacionUI("");
		else if(mut.equalsIgnoreCase("PermutarArgs")) updateMutacionUI("");
	}

	public void refreshCruceFields(String cruc) {
		if(cruc.equalsIgnoreCase("Monopunto")) updateCruceUI("");
		else if(cruc.equalsIgnoreCase("Monopunto")) updateCruceUI("");
		else if(cruc.equalsIgnoreCase("Uniforme")) updateCruceUI("probXchngGen");
		else if(cruc.equalsIgnoreCase("Aritmetico")) updateCruceUI("alfa");
		else if(cruc.equalsIgnoreCase("CrucePMX")) updateCruceUI("");
		else if(cruc.equalsIgnoreCase("CruceERX")) updateCruceUI("");
		else if(cruc.equalsIgnoreCase("CruceCO")) updateCruceUI("");
		else if(cruc.equalsIgnoreCase("CruceCX")) updateCruceUI("");
		else if(cruc.equalsIgnoreCase("CruceOX")) updateCruceUI("");
		else if(cruc.equalsIgnoreCase("CruceOXPP")) updateCruceUI("numGens2Xchng");
	}

	public void refreshCromosomaFields(String crom) {
		if(crom.equalsIgnoreCase("Binario")) updateCromosomaUI("minX, maxX, minY, maxY");
		else if(crom.equalsIgnoreCase("Real")) updateCromosomaUI("numVariables, minX, maxX");
		else if(crom.equalsIgnoreCase("Permutacion")) updateCromosomaUI("");
		else if(crom.equalsIgnoreCase("Gramatica")) updateCromosomaUI("maxDepth, nAddrInputs, cbGramaticaInit, instrIF, bloating");
	}

	public void refreshSelectionFields(String sel) {
		if(sel.equalsIgnoreCase("Ranking")) updateSeleccionUI("beta");
		else if(sel.equalsIgnoreCase("Truncamiento")) updateSeleccionUI("trunc");
		else if(sel.equalsIgnoreCase("Ruleta")) updateSeleccionUI("");
		else if(sel.equalsIgnoreCase("Estocastico")) updateSeleccionUI("kindividuos");
		else if(sel.equalsIgnoreCase("Torneo")) updateSeleccionUI("tamMuestra, umbral");
	}

	public void updateCromosomaProps() {
		String selF = model.getPropValue("funcion").toString();
		if(selF.equalsIgnoreCase("funcion 1")) {
			spinnerMinX.setValue(model.getPropValue("minX"));
			spinnerMaxX.setValue(model.getPropValue("maxX")); 
			spinnerMinY.setValue(model.getPropValue("minY")); 
			spinnerMaxY.setValue(model.getPropValue("maxY"));
		} else if(selF.equalsIgnoreCase("Holder table")) {
			spinnerMinX.setValue(model.getPropValue("minX"));
			spinnerMaxX.setValue(model.getPropValue("maxX")); 
			spinnerMinY.setValue(model.getPropValue("minY")); 
			spinnerMaxY.setValue(model.getPropValue("maxY"));
		} else if(selF.equalsIgnoreCase("Schubert")) {
			spinnerMinX.setValue(model.getPropValue("minX"));
			spinnerMaxX.setValue(model.getPropValue("maxX")); 
			spinnerMinY.setValue(model.getPropValue("minY")); 
			spinnerMaxY.setValue(model.getPropValue("maxY"));
		} else if(selF.equalsIgnoreCase("Michalewicz")) {
			spinnerMinX.setValue(model.getPropValue("minX"));
			spinnerMaxX.setValue(model.getPropValue("maxX"));
			spinnerMinY.setValue(model.getPropValue("minY")); 
			spinnerMaxY.setValue(model.getPropValue("maxY"));
		} else if(selF.equalsIgnoreCase("Multiplexor")) {
			//TODO: updateCromosomaProps
		}
	}
	
	private void addAllComponents() {
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		JPanel propsPanel = new JPanel();
		propsPanel.setBorder(null);
		propsPanel.add(panel_1);
		JPanel mspPanel = new JPanel();
		FlowLayout fl_propsPanel = (FlowLayout) propsPanel.getLayout();
		fl_propsPanel.setVgap(0);
		fl_propsPanel.setHgap(0);
		
		ModernScrollPane msp = new ModernScrollPane(mspPanel);
		msp.getVerticalScrollBar().setUnitIncrement(16);
		mspPanel.setLayout(new BoxLayout(mspPanel, BoxLayout.Y_AXIS));
		mspPanel.add(propsPanel);
		panel_1.setBorder(null);
		panel.add(msp, BorderLayout.WEST);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(null);
		panel_11.setLayout(new BoxLayout(panel_11, BoxLayout.X_AXIS));
		
		progressBar = new JProgressBar();
		progressBar.setBorder(null);
		panel_11.add(progressBar);
		panel_1.add(panel_11);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(null);
		panel_9.setLayout(new GridLayout(0, 2, 0, 0));
		
		btnEjecutar = new JButton("Ejecutar");
		btnEjecutar.setMnemonic('j');
		btnEjecutar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnEjecutar.setHorizontalAlignment(SwingConstants.LEFT);
		panel_9.add(btnEjecutar);
		
		btnPaso = new JButton("Reset");
		btnPaso.setMnemonic('R');
		btnPaso.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnPaso.setHorizontalAlignment(SwingConstants.LEFT);
		panel_9.add(btnPaso);
		panel_1.add(panel_9);
		
		JPanel panelGeneral = new JPanel();
		TitledBorder border = new TitledBorder(null, "General", TitledBorder.LEADING, TitledBorder.TOP, null, null);
		panelGeneral.setBorder(border);
		panel_1.add(panelGeneral);
		panelGeneral.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EmptyBorder(0, 6, 6, 6));
		panelGeneral.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel("tama\u00F1o poblacion");
		lblNewLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setDisplayedMnemonic('t');
		panel_4.add(lblNewLabel);
		
		spinnerTamPoblacion = new JSpinner(new SpinnerNumberModel(100,10,10000000,2)); 
		lblNewLabel.setLabelFor(spinnerTamPoblacion);
		spinnerTamPoblacion.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_4.add(spinnerTamPoblacion);
		
		JLabel lblNewLabel_1 = new JLabel("num generaciones");
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_1.setDisplayedMnemonic('n');
		panel_4.add(lblNewLabel_1);
		
		spinnerNGeneraciones = new JSpinner(new SpinnerNumberModel(100,20,10000000,1)); 
		lblNewLabel_1.setLabelFor(spinnerNGeneraciones);
		spinnerNGeneraciones.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_4.add(spinnerNGeneraciones);
		
		JLabel lblNewLabel_2 = new JLabel("tolerancia"); 
		lblNewLabel_2.setAlignmentY(Component.TOP_ALIGNMENT);
		lblNewLabel_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_2.setDisplayedMnemonic('o');
		panel_4.add(lblNewLabel_2);
		
		tfTolerancia = new JTextField(); lblNewLabel_2.setLabelFor(tfTolerancia);
		tfTolerancia.setAlignmentY(Component.TOP_ALIGNMENT);
		tfTolerancia.setText("0.001");
		panel_4.add(tfTolerancia);
		tfTolerancia.setColumns(10);
		
		checkboxRandomSeed = new JCheckBox("random seed");
		checkboxRandomSeed.setMnemonic('d');
		checkboxRandomSeed.setAlignmentX(Component.CENTER_ALIGNMENT);
		checkboxRandomSeed.setToolTipText("Desactivar para obtener la misma secuencia de numeros aleatorios la siguiente ejecucion");
		panel_4.add(checkboxRandomSeed);
		
		NumberFormat longFormat = NumberFormat.getIntegerInstance();
		NumberFormatter numberFormatter = new NumberFormatter(longFormat);
		numberFormatter.setValueClass(Long.class);
		numberFormatter.setAllowsInvalid(false);
		
		tfSeed = new JFormattedTextField(numberFormatter);
		tfSeed.setColumns(10);
		panel_4.add(tfSeed);

		JLabel lblNewLabel_3 = new JLabel("funcion");
		lblNewLabel_3.setAlignmentY(Component.TOP_ALIGNMENT);
		lblNewLabel_3.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_3.setDisplayedMnemonic('f');
		panel_4.add(lblNewLabel_3);
		
		cbFuncionSeleccionada = new JComboBox<>(); lblNewLabel_3.setLabelFor(cbFuncionSeleccionada);
		cbFuncionSeleccionada.setAlignmentY(Component.TOP_ALIGNMENT);
		modelFunciones = new DefaultComboBoxModel<>(new Object[] {
				"funcion 1", 
				"Holder table", 
				"Schubert", 
				"Michalewicz", 
				"Problema5",
				"Multiplexor"});
		cbFuncionSeleccionada.setModel(modelFunciones);
		panel_4.add(cbFuncionSeleccionada);
		
		lblDatosOptimizar = new JLabel("datos a optimizar");
		lblDatosOptimizar.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblDatosOptimizar.setDisplayedMnemonic('t');
		panel_4.add(lblDatosOptimizar);
		
		cbDatosOptimizar = new JComboBox<>(); lblDatosOptimizar.setLabelFor(cbDatosOptimizar);
		cbDatosOptimizar.setModel(new DefaultComboBoxModel<Object>(new Object[] {"ajuste.dat", "datos12.dat", "datos15.dat", "datos30.dat"}));
		panel_4.add(cbDatosOptimizar);
		
		JPanel panelCromosoma = new JPanel();
		panelCromosoma.setBorder(new TitledBorder(null, "Cromosoma", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panelCromosoma);
		panelCromosoma.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_17 = new JPanel();
		panel_17.setBorder(new EmptyBorder(0, 6, 6, 6));
		panelCromosoma.add(panel_17);
		panel_17.setLayout(new BoxLayout(panel_17, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_10 = new JLabel("tipo cromosoma");
		lblNewLabel_10.setAlignmentY(Component.TOP_ALIGNMENT);
		lblNewLabel_10.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_10.setDisplayedMnemonic('a');
		panel_17.add(lblNewLabel_10);
		
		cbTipoCromosoma = new JComboBox<>(); lblNewLabel_10.setLabelFor(cbTipoCromosoma);
		cbTipoCromosoma.setAlignmentY(Component.TOP_ALIGNMENT);
		modelCromosomas = new DefaultComboBoxModel<Object>(new Object[] {"Binario", "Real"});
		cbTipoCromosoma.setModel(modelCromosomas);
		panel_17.add(cbTipoCromosoma);
		
		lblNumVariables = new JLabel("num. variables");
		lblNumVariables.setAlignmentY(Component.TOP_ALIGNMENT);
		lblNumVariables.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNumVariables.setDisplayedMnemonic('v');
		panel_17.add(lblNumVariables);
		
		spinnerNumVariables = new JSpinner(new SpinnerNumberModel(2, 2, 10, 1)); lblNumVariables.setLabelFor(spinnerNumVariables);
		spinnerNumVariables.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_17.add(spinnerNumVariables);
		
		lblMinX = new JLabel("min X");
		lblMinX.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblMinX.setDisplayedMnemonic('i');
		panel_17.add(lblMinX);
		
		spinnerMinX = new JSpinner(new SpinnerNumberModel(0.0,-100000000.0 ,100000000.0,0.01)); 
		lblMinX.setLabelFor(spinnerMinX);
		panel_17.add(spinnerMinX);
		
		lblMaxX = new JLabel("max X");
		lblMaxX.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblMaxX.setDisplayedMnemonic('X');
		panel_17.add(lblMaxX);
		
		spinnerMaxX = new JSpinner(new SpinnerNumberModel(0.0,-100000000.0 ,100000000.0,0.01)); 
		lblMaxX.setLabelFor(spinnerMaxX);
		panel_17.add(spinnerMaxX);
		
		lblMinY = new JLabel("min Y"); 
		lblMinY.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblMinY.setDisplayedMnemonic('Y');
		panel_17.add(lblMinY);
		
		spinnerMinY = new JSpinner(new SpinnerNumberModel(0.0,-100000000.0 ,100000000.0,0.01)); 
		lblMinY.setLabelFor(spinnerMinY);
		panel_17.add(spinnerMinY);
		
		lblMaxY = new JLabel("max Y");
		lblMaxY.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_17.add(lblMaxY);
		
		spinnerMaxY = new JSpinner(new SpinnerNumberModel(0.0,-100000000.0 ,100000000.0,0.01)); 
		lblMaxY.setLabelFor(spinnerMaxY);
		panel_17.add(spinnerMaxY);
		
		checkboxInstrIF = new JCheckBox("instruccion IF");
		checkboxInstrIF.setSelected(true);
		checkboxInstrIF.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_17.add(checkboxInstrIF);
		
		lblMaxDepth = new JLabel("max start depth");
		lblMaxDepth.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_17.add(lblMaxDepth);
		
		spinnerMaxDepth = new JSpinner(new SpinnerNumberModel(4, 4, 100000, 1)); 
		lblMaxDepth.setLabelFor(spinnerMaxDepth);
		panel_17.add(spinnerMaxDepth);
		
		lblNaddrInputs = new JLabel("<html>"
				+ "    (<b>1</b>+1&lt;&lt;<b>1</b>= 3) inputs"
				+ "<br>(<b>2</b>+1&lt;&lt;<b>2</b>= 6) inputs"
				+ "<br>(<b>3</b>+1&lt;&lt;<b>3</b>=11) inputs"
				+ "<br>(<b>4</b>+1&lt;&lt;<b>4</b>=20) inputs"
				+ "<br><b>+5</b> no permitido"
				+ "<br><b>nAddrInputs</b> Mux"
				+ "<br>Multiplexor"
				+ "</html>");
		lblNaddrInputs.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_17.add(lblNaddrInputs);
		
		spinnerNaddrInputs = new JSpinner(new SpinnerNumberModel(2, 1, 4, 1)); 
		lblNaddrInputs.setLabelFor(spinnerNaddrInputs);
		panel_17.add(spinnerNaddrInputs);
		
		lblcbGramaticaInit = new JLabel("tipo inicializacion");
		lblcbGramaticaInit.setAlignmentY(Component.TOP_ALIGNMENT);
		lblcbGramaticaInit.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_17.add(lblcbGramaticaInit);
		
		cbGramaticaInit = new JComboBox<>(); lblcbGramaticaInit.setLabelFor(cbGramaticaInit);
		cbGramaticaInit.setAlignmentY(Component.TOP_ALIGNMENT);
		modelGramaticaInit = new DefaultComboBoxModel<Object>(new Object[] {"Creciente", "Completa", "Ramped"});
		cbGramaticaInit.setModel(modelGramaticaInit);
		panel_17.add(cbGramaticaInit);
		
		lblcbBloating = new JLabel("bloating");//
		lblcbBloating.setDisplayedMnemonic('g');
		lblcbBloating.setAlignmentY(Component.TOP_ALIGNMENT);
		lblcbBloating.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_17.add(lblcbBloating);
		
		cbBloating = new JComboBox<>(); lblcbBloating.setLabelFor(cbBloating);
		cbBloating.setAlignmentY(Component.TOP_ALIGNMENT);
		modelBloating = new DefaultComboBoxModel<Object>(new Object[] {"ninguno", "Tarpeian", "PoliMcPhee"});
		cbBloating.setModel(modelBloating);
		panel_17.add(cbBloating);
		
		lblTarpeianN = new JLabel("Death Proportion");
		lblTarpeianN.setAlignmentY(Component.TOP_ALIGNMENT);
		lblTarpeianN.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTarpeianN.setVisible(false);
		jslidderTarpeianN = new JSlider(); lblTarpeianN.setLabelFor(jslidderTarpeianN);
		jslidderTarpeianN.setValue(7);
		jslidderTarpeianN.setVisible(false);
		jslidderTarpeianN.setMinimum(2);
		jslidderTarpeianN.setMaximum(20);
		jslidderTarpeianN.setMinorTickSpacing(1);
		panel_17.add(lblTarpeianN);
		Dimension size = new Dimension(lblTarpeianN.getSize()); size.height=60;
		jslidderTarpeianN.setPreferredSize(size);
		jslidderTarpeianN.setSnapToTicks(true);
		jslidderTarpeianN.setPaintTicks(true);
		jslidderTarpeianN.setPaintLabels(true);
		jslidderTarpeianN.setMajorTickSpacing(1);
		panel_17.add(jslidderTarpeianN);

		cbBloating.addActionListener(e->{
			if(cbBloating.getSelectedItem().equals("Tarpeian")) {
				lblTarpeianN.setVisible(true); jslidderTarpeianN.setVisible(true);
			} else { lblTarpeianN.setVisible(false); jslidderTarpeianN.setVisible(false); }
		});
		
		JPanel panelSeleccion = new JPanel();
		panelSeleccion.setBorder(new TitledBorder(null, "Seleccion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panelSeleccion);
		panelSeleccion.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new EmptyBorder(0, 6, 6, 6));
		panelSeleccion.add(panel_10, BorderLayout.NORTH);
		panel_10.setLayout(new BoxLayout(panel_10, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_4 = new JLabel("tipo Seleccion");
		lblNewLabel_4.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_4.setDisplayedMnemonic('S');
		panel_10.add(lblNewLabel_4);
		
		cbTipoSeleccion = new JComboBox<>(); lblNewLabel_4.setLabelFor(cbTipoSeleccion);
		modelSelecciones = new DefaultComboBoxModel<Object>(new Object[] {"Ruleta", "Estocastico", "Torneo"});
		cbTipoSeleccion.setModel(modelSelecciones);
		panel_10.add(cbTipoSeleccion);
		
		JLabel lblNewLabel_5 = new JLabel("% elitismo");
		lblNewLabel_5.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_5.setDisplayedMnemonic('%');
		panel_10.add(lblNewLabel_5);
		
		spinnerPorcntjElitismo = new JSpinner(new SpinnerNumberModel(0.0,0.0,0.1,0.001)); 
		lblNewLabel_5.setLabelFor(spinnerPorcntjElitismo);
		panel_10.add(spinnerPorcntjElitismo);
		
		lblKindividuos = new JLabel("kindividuos");
		lblKindividuos.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblKindividuos.setDisplayedMnemonic('k');
		panel_10.add(lblKindividuos);
		
		spinnerKindividuos = new JSpinner(); 
		lblKindividuos.setLabelFor(spinnerKindividuos);
		spinnerKindividuos.setModel(new SpinnerNumberModel(3,2,6,1));
		panel_10.add(spinnerKindividuos);
		
		lblBeta = new JLabel("beta");
		lblBeta.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblBeta.setDisplayedMnemonic('e');
		panel_10.add(lblBeta);
		
		spinnerBeta = new JSpinner(); lblBeta.setLabelFor(spinnerBeta);//SeleccionRanking
		spinnerBeta.setModel(new SpinnerNumberModel(1.0,1.0,2.0,0.05));
		panel_10.add(spinnerBeta);
		
		lblUmbral = new JLabel("umbral");
		lblUmbral.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblUmbral.setDisplayedMnemonic('l');
		panel_10.add(lblUmbral);
		
		spinnerUmbral = new JSpinner(); lblUmbral.setLabelFor(spinnerUmbral);
		spinnerUmbral.setModel(new SpinnerNumberModel(1.0,0.0,1.0,0.05));
		panel_10.add(spinnerUmbral);
		
		lblTamMuestra = new JLabel("tamMuestra");
		lblTamMuestra.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_10.add(lblTamMuestra);
		
		spinnerTamMuestra = new JSpinner();
		spinnerTamMuestra.setModel(new SpinnerNumberModel(3,2,6,1));
		panel_10.add(spinnerTamMuestra);
		
		lblTrunc = new JLabel("trunc");
		lblTrunc.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_10.add(lblTrunc);
		
		spinnerTrunc = new JSpinner(new SpinnerNumberModel(0.5d,0.1d,0.5d,0.1d));//SeleccionTruncamiento
		panel_10.add(spinnerTrunc);
		
		JPanel panelCruce = new JPanel();
		panelCruce.setBorder(new TitledBorder(null, "Cruce", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panelCruce);
		panelCruce.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new EmptyBorder(0, 6, 6, 6));
		panelCruce.add(panel_13, BorderLayout.NORTH);
		panel_13.setLayout(new BoxLayout(panel_13, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_6 = new JLabel("tipo Cruce");
		lblNewLabel_6.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_6.setDisplayedMnemonic('C');
		panel_13.add(lblNewLabel_6);
		
		cbTipoCruce = new JComboBox<>(); lblNewLabel_6.setLabelFor(cbTipoCruce);
		modelCruces = new DefaultComboBoxModel<>(new String[] {"Monopunto", "Uniforme", "Aritmetico"});
		cbTipoCruce.setModel(modelCruces);
		panel_13.add(cbTipoCruce);
		
		JLabel lblNewLabel_7 = new JLabel("probabilidad");
		lblNewLabel_7.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_13.add(lblNewLabel_7);
		
		spinnerProbCruce = new JSpinner(new SpinnerNumberModel(0.6,0.6,1.0,0.1));
		panel_13.add(spinnerProbCruce);
		
		lblAlfa = new JLabel("alfa");
		lblAlfa.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_13.add(lblAlfa);
		
		spinnerAlfa = new JSpinner();//CruceAritmetico
		spinnerAlfa.setModel(new SpinnerNumberModel(0.2, 0.2, 0.6, 0.05));
		panel_13.add(spinnerAlfa);
		
		lblNumGens2Xchng = new JLabel("numGens2Xchng");
		lblNumGens2Xchng.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_13.add(lblNumGens2Xchng);
		
		spinnerNumGens2Xchng = new JSpinner();//CruceOXPP
		spinnerNumGens2Xchng.setModel(new SpinnerNumberModel(2, 2, 6, 1));
		panel_13.add(spinnerNumGens2Xchng);
		
		lblProbXchngGen = new JLabel("probXchngGen");
		lblProbXchngGen.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_13.add(lblProbXchngGen);
		
		spinnerProbXchngGen = new JSpinner();//CruceUniforme
		spinnerProbXchngGen.setModel(new SpinnerNumberModel(0.4, 0.2, 0.5, 0.05));
		panel_13.add(spinnerProbXchngGen);
		
		JPanel panelMutacion = new JPanel();
		panelMutacion.setBorder(new TitledBorder(null, "Mutacion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panelMutacion);
		panelMutacion.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new EmptyBorder(0, 6, 6, 6));
		panelMutacion.add(panel_15, BorderLayout.NORTH);
		panel_15.setLayout(new BoxLayout(panel_15, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_8 = new JLabel("tipo Mutacion");
		lblNewLabel_8.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_8.setDisplayedMnemonic('m');
		panel_15.add(lblNewLabel_8);
		
		cbTipoMutacion = new JComboBox<>(); lblNewLabel_8.setLabelFor(cbTipoMutacion);
		modelMutaciones = new DefaultComboBoxModel<>(new Object[] {"Basica", "Uniforme"});
		cbTipoMutacion.setModel(modelMutaciones);
		panel_15.add(cbTipoMutacion);
		
		JLabel lblNewLabel_9 = new JLabel("probabilidad");
		lblNewLabel_9.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_15.add(lblNewLabel_9);
		
		spinnerProbMutacion = new JSpinner(new SpinnerNumberModel(0.1,0.0,1.0,0.1));
		panel_15.add(spinnerProbMutacion);
		
		lblProbFlipBit = new JLabel("probFlipBit");
		lblProbFlipBit.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_15.add(lblProbFlipBit);
		
		spinnerProbFlipBit = new JSpinner();//MutacionBasica
		spinnerProbFlipBit.setModel(new SpinnerNumberModel(0.15,0,1,0.05));
		panel_15.add(spinnerProbFlipBit);
		
		lblNumGen2Perm = new JLabel("numGen2Perm");
		lblNumGen2Perm.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_15.add(lblNumGen2Perm);
		
		spinnerNumGen2Perm = new JSpinner();//MutacionHeuristica
		spinnerNumGen2Perm.setModel(new SpinnerNumberModel(3, 0,6,1));
		panel_15.add(spinnerNumGen2Perm);
		
		lblNumGens2Ins = new JLabel("numGens2Ins");
		lblNumGens2Ins.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_15.add(lblNumGens2Ins);
		
		spinnerNumGens2Ins = new JSpinner(new SpinnerNumberModel(1, 1,6,1));//MutacionInsercion
		panel_15.add(spinnerNumGens2Ins);
		
		lblNumG2Inv = new JLabel("numG2Inv"); 
		lblNumG2Inv.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_15.add(lblNumG2Inv);
		
		spinnerNumG2Inv = new JSpinner();//MutacionIntercambio
		spinnerNumG2Inv.setModel(new SpinnerNumberModel(2,1,6,1));
		panel_15.add(spinnerNumG2Inv);
		
		//-----------------
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane();
		panel_2.add(tabbedPane, BorderLayout.CENTER);
		
		panel_6 = new JPanel();
		tabbedPane.addTab("Aprendizaje", null, panel_6, null);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_Z);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		p2d = new Plot2DPanel();
		p2d.addLinePlot("Globales", new double[] {0});
		p2d.addLinePlot("Locales", new double[] {0});
		p2d.addLinePlot("media", new double[] {0});
		panel_6.add(p2d);
		
		panel_7 = new JPanel(true);
		tabbedPane.addTab("Problem View", null, panel_7, null);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_3);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		tabbedPane.addTab("Log", null, panel_8, null);
//		tabbedPane.setMnemonicAt(2, KeyEvent.VK_G);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_8.add(scrollPane, BorderLayout.CENTER);
		
		jtaLog = new JTextArea();
		scrollPane.setViewportView(jtaLog);
		tabbedPane.setSelectedIndex(0);
		
		jEditorPane = new JEditorPane();
		jEditorPane.setEditable(false);
		jEditorPane.setContentType("text/html");
		HTMLEditorKit hek = new HTMLEditorKit();
		StyleSheet csshek = hek.getStyleSheet();
		csshek.addRule("body{margin:0px 20px;font-family: \"Segoe UI\", Arial, Helvetica, sans-serif;}");
		jEditorPane.setCursor(hek.getDefaultCursor());
		jEditorPane.setEditorKit(hek);
		try {
			jEditorPane.read(getClass().getModule().getResourceAsStream("res/help.html"), hek);
		} catch (IOException e) { }
		JScrollPane spHtml = new JScrollPane(jEditorPane);
		tabbedPane.addTab("Ayuda", spHtml);
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_U);
		jEditorPane.addHyperlinkListener(new CustomHyperLinkListener());
		
		panelGeneral  .addMouseListener(new TitleClickAdapter(panel_4));
		panelCromosoma.addMouseListener(new TitleClickAdapter(panel_17));
		panelSeleccion.addMouseListener(new TitleClickAdapter(panel_10));
		panelCruce    .addMouseListener(new TitleClickAdapter(panel_13));
		panelMutacion .addMouseListener(new TitleClickAdapter(panel_15));		
	}
	
}
