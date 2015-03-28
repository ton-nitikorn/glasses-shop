package com.glassshop.ui;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel   
{   
    private boolean editable;   
  
    public MyTableModel(String[] columnNames, int i) {
    	
		super(columnNames,i);
	}

	public void setEditable(boolean editable) { this.editable = editable; }   
  
    @Override  
    public boolean isCellEditable(int row, int col) { return editable; }   

    /*List<Color> rowColours = Arrays.asList(
            Color.RED,
            Color.GREEN,
            Color.CYAN
        );

        public void setRowColour(int row, Color c) {
            rowColours.set(row, c);
            fireTableRowsUpdated(row, row);
        }

        public Color getRowColour(int row) {
            return rowColours.get(row);
        }

        @Override
        public int getRowCount() {
            return 3;
        }

        @Override
        public int getColumnCount() {
            return 3;
        }

        @Override
        public Object getValueAt(int row, int column) {
            return String.format("%d %d", row, column);
        }*/
}
