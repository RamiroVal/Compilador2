package main.java.analizador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VistaCuadruplos extends JDialog{

	public VistaCuadruplos(JFrame frame, String titulo,  String[] titulos, String[][] datos) {
		super(frame, titulo, true);
        setSize(700, 600);
        setLocationRelativeTo(null);
		
	JTable tblTabla = new JTable(datos, titulos);
	tblTabla.setPreferredScrollableViewportSize(new Dimension(500, 800));

	JScrollPane scpScroll = new JScrollPane(tblTabla);
	
	getContentPane().add(scpScroll, BorderLayout.CENTER);
		
    }

}