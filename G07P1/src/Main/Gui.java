package Main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class Gui extends JFrame {

	public JPanel contentPane;
	public JPanel panel_6;
	public JProgressBar progressBar;

	public JButton btnPaso;
	public JButton btnEjecutar;
	public JTextField tfTamPoblacion;
	public JTextField tfNGeneraciones;
	public JTextField tfTolerancia;
	public JTextField tfPorcntjElitismo;
	public JTextField tfProbCruce;
	public JTextField tfProbMutacion;
	public JComboBox<String> cbFuncionSeleccionada;
	public JComboBox<String> cbTipoSeleccion;
	public JComboBox<String> cbTipoCruce;
	public JComboBox<String> cbTipoMutacion;
	public JComboBox<String> cbTipoCromosoma;
	public JTextField tfNumVariables;
	public JPanel panel_7;
	public JTextArea jtaLog;

	/**
	 * Launch the application. adsdww 
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { System.exit(-1);}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Gui() {
		setTitle("Practica 1 Optimizacion funciones");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Gui.class.getResource("/org/math/plot/icons/position.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 526);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel.add(panel_1, BorderLayout.WEST);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{213, 0};
		gbl_panel_1.rowHeights = new int[]{1, 63, 65, 57, 161, 24, 23, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "General", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.anchor = GridBagConstraints.NORTH;
		gbc_panel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		panel_1.add(panel_3, gbc_panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EmptyBorder(0, 6, 6, 6));
		panel_3.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(6, 2, 2, 2));
		
		JLabel lblNewLabel = new JLabel("tama\u00F1o poblacion");
		panel_4.add(lblNewLabel);
		
		tfTamPoblacion = new JTextField();
		tfTamPoblacion.setText("100");
		panel_4.add(tfTamPoblacion);
		tfTamPoblacion.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("numero generac...");
		panel_4.add(lblNewLabel_1);
		
		tfNGeneraciones = new JTextField();
		tfNGeneraciones.setText("100");
		panel_4.add(tfNGeneraciones);
		tfNGeneraciones.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("tolerancia");
		panel_4.add(lblNewLabel_2);
		
		tfTolerancia = new JTextField();
		tfTolerancia.setText("0.001");
		panel_4.add(tfTolerancia);
		tfTolerancia.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("funcion");
		panel_4.add(lblNewLabel_3);
		
		cbFuncionSeleccionada = new JComboBox<>();
		cbFuncionSeleccionada.setModel(new DefaultComboBoxModel<String>(new String[] {"funcion 1", "Holder table", "Schubert", "Michalewicz"}));
		panel_4.add(cbFuncionSeleccionada);
		
		JLabel tfNvariables = new JLabel("num. variables");
		panel_4.add(tfNvariables);
		
		tfNumVariables = new JTextField();
		tfNumVariables.setEnabled(false);
		tfNumVariables.setText("4");
		panel_4.add(tfNumVariables);
		tfNumVariables.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("tipo cromosoma");
		panel_4.add(lblNewLabel_10);
		
		cbTipoCromosoma = new JComboBox<>();
		cbTipoCromosoma.setEnabled(false);
		cbTipoCromosoma.setModel(new DefaultComboBoxModel<String>(new String[] {"Binario", "Real"}));
		panel_4.add(cbTipoCromosoma);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Seleccion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.anchor = GridBagConstraints.NORTH;
		gbc_panel_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 1;
		panel_1.add(panel_5, gbc_panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new EmptyBorder(0, 6, 6, 6));
		panel_5.add(panel_10, BorderLayout.CENTER);
		panel_10.setLayout(new GridLayout(2, 2, 2, 2));
		
		JLabel lblNewLabel_4 = new JLabel("tipo seleccion");
		panel_10.add(lblNewLabel_4);
		
		cbTipoSeleccion = new JComboBox<>();
		cbTipoSeleccion.setModel(new DefaultComboBoxModel<String>(new String[] {"Ruleta", "Estocastico", "Torneo"}));
		panel_10.add(cbTipoSeleccion);
		
		JLabel lblNewLabel_5 = new JLabel("% elitismo");
		panel_10.add(lblNewLabel_5);
		
		tfPorcntjElitismo = new JTextField();
		tfPorcntjElitismo.setText("0.05");
		panel_10.add(tfPorcntjElitismo);
		tfPorcntjElitismo.setColumns(10);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new TitledBorder(null, "Cruce", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_12 = new GridBagConstraints();
		gbc_panel_12.anchor = GridBagConstraints.NORTH;
		gbc_panel_12.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_12.gridx = 0;
		gbc_panel_12.gridy = 2;
		panel_1.add(panel_12, gbc_panel_12);
		panel_12.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new EmptyBorder(0, 6, 6, 6));
		panel_12.add(panel_13, BorderLayout.CENTER);
		panel_13.setLayout(new GridLayout(0, 2, 2, 2));
		
		JLabel lblNewLabel_6 = new JLabel("tipo cruce");
		panel_13.add(lblNewLabel_6);
		
		cbTipoCruce = new JComboBox<>();
		cbTipoCruce.setModel(new DefaultComboBoxModel<>(new String[] {"Monopunto", "Uniforme", "Aritmetico"}));
		panel_13.add(cbTipoCruce);
		
		JLabel lblNewLabel_7 = new JLabel("probabilidad");
		panel_13.add(lblNewLabel_7);
		
		tfProbCruce = new JTextField();
		tfProbCruce.setText("0.6");
		panel_13.add(tfProbCruce);
		tfProbCruce.setColumns(10);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBorder(new TitledBorder(null, "Mutacion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_14 = new GridBagConstraints();
		gbc_panel_14.anchor = GridBagConstraints.NORTH;
		gbc_panel_14.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_14.gridx = 0;
		gbc_panel_14.gridy = 3;
		panel_1.add(panel_14, gbc_panel_14);
		panel_14.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new EmptyBorder(0, 6, 6, 6));
		panel_14.add(panel_15, BorderLayout.CENTER);
		panel_15.setLayout(new GridLayout(0, 2, 2, 2));
		
		JLabel lblNewLabel_8 = new JLabel("tipo mutacion");
		panel_15.add(lblNewLabel_8);
		
		cbTipoMutacion = new JComboBox<>();
		cbTipoMutacion.setModel(new DefaultComboBoxModel<String>(new String[] {"B\u00E1sica", "Uniforme"}));
		panel_15.add(cbTipoMutacion);
		
		JLabel lblNewLabel_9 = new JLabel("probabilidad");
		panel_15.add(lblNewLabel_9);
		
		tfProbMutacion = new JTextField();
		tfProbMutacion.setText("0.05");
		panel_15.add(tfProbMutacion);
		tfProbMutacion.setColumns(10);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(null);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		progressBar = new JProgressBar();
		progressBar.setBorder(null);
		panel_11.add(progressBar, BorderLayout.CENTER);
		GridBagConstraints gbc_panel_11 = new GridBagConstraints();
		gbc_panel_11.fill = GridBagConstraints.BOTH;
		gbc_panel_11.insets = new Insets(0, 0, 5, 0);
		gbc_panel_11.gridx = 0;
		gbc_panel_11.gridy = 5;
		panel_1.add(panel_11, gbc_panel_11);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(null);
		panel_9.setLayout(new GridLayout(0, 2, 0, 0));
		
		btnEjecutar = new JButton("Ejecutar");
		btnEjecutar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnEjecutar.setHorizontalAlignment(SwingConstants.LEFT);
		panel_9.add(btnEjecutar);
		
		btnPaso = new JButton("Paso");
		btnPaso.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnPaso.setHorizontalAlignment(SwingConstants.LEFT);
		panel_9.add(btnPaso);
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.anchor = GridBagConstraints.NORTH;
		gbc_panel_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_9.gridx = 0;
		gbc_panel_9.gridy = 6;
		panel_1.add(panel_9, gbc_panel_9);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel_2.add(tabbedPane, BorderLayout.CENTER);
		
		panel_6 = new JPanel();
		tabbedPane.addTab("Aprendizaje", null, panel_6, null);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		
		panel_7 = new JPanel();
		tabbedPane.addTab("Grafico 3D", null, panel_7, null);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		tabbedPane.addTab("Log", null, panel_8, null);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_8.add(scrollPane, BorderLayout.CENTER);
		
		jtaLog = new JTextArea();
		scrollPane.setViewportView(jtaLog);
		tabbedPane.setSelectedIndex(0);
	}
}
