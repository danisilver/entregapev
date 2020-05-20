package utils;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.Observer;
import view.View;

public class NAddrInputsObserver extends SwingWorker<Integer,String[]> implements Observer {
	private final HashMap<String, Object> props;
	int nInputs, nOutputs/*, nDataInputs*/;
	Integer nAddrInputs = -1;
	NAddrInputsObserver worker;
	private String[] columns;
	private DefaultTableModel dataModel;
	private View view;
	
	public NAddrInputsObserver(HashMap<String, Object> props, View view) {
		this.props = props;
		this.view = view;
	}

	@Override public void update() {
		Integer nAddrInputs = (Integer) props.get("nAddrInputs");
		if(this.nAddrInputs==nAddrInputs) return;
		if(this.worker==null)
			this.worker = new NAddrInputsObserver(props, view);
		else if(this.worker.isDone())
			this.worker = new NAddrInputsObserver(props, view);
		if(this.worker.getProgress()>0 && this.worker.getProgress()<100) {
			this.worker.cancel(true);
			this.worker = new NAddrInputsObserver(props, view);
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
		JComponent panelView = (JComponent) view.getComponent();
		panelView.removeAll();
		panelView.add(scrollPane,BorderLayout.CENTER);
		panelView.revalidate();
		panelView.repaint();
	}
}
