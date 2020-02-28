package Main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class Gui extends JFrame {

	public JPanel contentPane;
	public JPanel panel_6;
	public JProgressBar progressBar;
	public JTextField tfPorcntjElitismo;
	public JComboBox<String> cbFuncionSeleccionada;
	public JTextField tfNGeneraciones;
	public JTextField tfTamPoblacion;
	public JComboBox<String> cbTipoSeleccion;
	public JComboBox<String> cbTipoCruce;
	public JTextField tfProbCruce;
	public JComboBox<String> cbTipoMutacion;
	public JTextField tfProbMutacion;

	public JButton btnPaso;
	public JButton btnEjecutar;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel.add(panel_1, BorderLayout.WEST);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Cruce", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Mutaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "General", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(null);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new EmptyBorder(0, 2, 0, 2));
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new TitledBorder(null, "Seleccion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
					.addGap(2)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_11, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(1)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
										.addComponent(panel_9, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
										.addComponent(panel_5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)))
								.addComponent(panel_12, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(1))
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
						.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(1)
					.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(panel_12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
					.addComponent(panel_11, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_1.linkSize(SwingConstants.HORIZONTAL, new Component[] {panel_3, panel_4, panel_5});
		
		JLabel lblNewLabel_2 = new JLabel("tipo seleccion");
		
		cbTipoSeleccion = new JComboBox<>();
		cbTipoSeleccion.addItem("Ruleta");
		cbTipoSeleccion.addItem("Torneo");
		cbTipoSeleccion.addItem("Estocastico");
		
		JLabel lblNewLabel_9 = new JLabel("% elitismo");
		
		tfPorcntjElitismo = new JTextField();
		tfPorcntjElitismo.setText("0.05");
		tfPorcntjElitismo.setColumns(10);
		GroupLayout gl_panel_12 = new GroupLayout(panel_12);
		gl_panel_12.setHorizontalGroup(
			gl_panel_12.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_12.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_12.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_9))
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addGroup(gl_panel_12.createParallelGroup(Alignment.LEADING, false)
						.addComponent(tfPorcntjElitismo)
						.addComponent(cbTipoSeleccion, 0, 88, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_12.setVerticalGroup(
			gl_panel_12.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_12.createSequentialGroup()
					.addGroup(gl_panel_12.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(cbTipoSeleccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_12.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_9)
						.addComponent(tfPorcntjElitismo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_12.setLayout(gl_panel_12);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		progressBar = new JProgressBar();
		panel_11.add(progressBar, BorderLayout.CENTER);
		panel_9.setLayout(new GridLayout(0, 2, 0, 0));
		
		btnEjecutar = new JButton("Ejecutar");
		btnEjecutar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnEjecutar.setHorizontalAlignment(SwingConstants.LEFT);
		panel_9.add(btnEjecutar);
		
		btnPaso = new JButton("Paso");
		btnPaso.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnPaso.setHorizontalAlignment(SwingConstants.LEFT);
		panel_9.add(btnPaso);
		
		JLabel lblNewLabel = new JLabel("tam poblacion");
		
		JLabel lblNewLabel_1 = new JLabel("n generaciones");
		
		tfNGeneraciones = new JTextField();
		tfNGeneraciones.setText("100");
		tfNGeneraciones.setColumns(10);
		
		tfTamPoblacion = new JTextField();
		tfTamPoblacion.setText("100");
		tfTamPoblacion.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("funcion");
		
		cbFuncionSeleccionada = new JComboBox<String>();
		cbFuncionSeleccionada.addItem("no name 1");
		cbFuncionSeleccionada.addItem("Holder table");
		cbFuncionSeleccionada.addItem("Schubert");
		cbFuncionSeleccionada.addItem("Michalewicz");
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_1))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_8)))
					.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING, false)
						.addComponent(cbFuncionSeleccionada, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(tfNGeneraciones, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
						.addComponent(tfTamPoblacion, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfTamPoblacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(6)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfNGeneraciones, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_8)
						.addComponent(cbFuncionSeleccionada, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(8))
		);
		gl_panel_5.linkSize(SwingConstants.HORIZONTAL, new Component[] {tfNGeneraciones, tfTamPoblacion});
		panel_5.setLayout(gl_panel_5);
		
		JLabel lblNewLabel_5 = new JLabel("tipo");
		
		JLabel lblNewLabel_6 = new JLabel("probabilidad");
		
		cbTipoMutacion = new JComboBox<>();
		cbTipoMutacion.addItem("basica");
		
		tfProbMutacion = new JTextField();
		tfProbMutacion.setText("0.05");
		tfProbMutacion.setColumns(10);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_6))
					.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING, false)
						.addComponent(cbTipoMutacion, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfProbMutacion, Alignment.TRAILING, 88, 88, 88))
					.addContainerGap())
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(cbTipoMutacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_6)
						.addComponent(tfProbMutacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(23))
		);
		gl_panel_4.linkSize(SwingConstants.HORIZONTAL, new Component[] {lblNewLabel_5, lblNewLabel_6});
		gl_panel_4.linkSize(SwingConstants.HORIZONTAL, new Component[] {cbTipoMutacion, tfProbMutacion});
		panel_4.setLayout(gl_panel_4);
		
		JLabel lblNewLabel_3 = new JLabel("tipo");
		
		cbTipoCruce = new JComboBox<>();
		cbTipoCruce.addItem("Monopunto");
		cbTipoCruce.addItem("Uniforme");
		
		JLabel lblNewLabel_4 = new JLabel("probabilidad");
		
		tfProbCruce = new JTextField();
		tfProbCruce.setText("0.6");
		tfProbCruce.setColumns(10);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
							.addComponent(cbTipoCruce, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblNewLabel_4)
							.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
							.addComponent(tfProbCruce, 88, 88, 88)))
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(cbTipoCruce, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addGap(4)))
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel_4))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(7)
							.addComponent(tfProbCruce, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel_3.linkSize(SwingConstants.HORIZONTAL, new Component[] {cbTipoCruce, tfProbCruce});
		gl_panel_3.linkSize(SwingConstants.HORIZONTAL, new Component[] {lblNewLabel_3, lblNewLabel_4});
		panel_3.setLayout(gl_panel_3);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel_2.add(tabbedPane, BorderLayout.CENTER);
		
		panel_6 = new JPanel();
		tabbedPane.addTab("Aprendizaje", null, panel_6, null);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		
		JPanel panel_7 = new JPanel();
		tabbedPane.addTab("Grafico 3D", null, panel_7, null);
		
		JPanel panel_8 = new JPanel();
		tabbedPane.addTab("Log", null, panel_8, null);
		tabbedPane.setSelectedIndex(0);
	}
}
