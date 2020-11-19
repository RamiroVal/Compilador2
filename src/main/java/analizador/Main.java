package main.java.analizador;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Main extends JFrame implements ActionListener {

	public static JTextArea area, consola;
	private JButton btnCompilar, btnAbrir, btnCuadruplos;
	private ArrayList<TablaSimbolos> tablaSimbolos;
	private ArrayList<Cuadruplo> cuadruplos;
	
	public Main() {
		super("Compilador");
		setLayout(null);
		setSize(800, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		hazInterfaz();
	}
	
	
	public static void main(String[] args) {
		new Main();
	}

	private void hazInterfaz() {
		area = new JTextArea();
		consola = new JTextArea();
		consola.setEnabled(false);
		consola.setDisabledTextColor(Color.BLACK);
		btnCompilar= new JButton("Compilar");
		btnCompilar.setBounds(480, 50, 100, 40);
		btnCompilar.addActionListener(this);
		btnAbrir= new JButton("Buscar");
		btnAbrir.setBounds(480, 100, 100, 40);
		btnAbrir.addActionListener(this);
		
		btnCuadruplos = new JButton("Cuadruplos");
		btnCuadruplos.setBounds(480, 150, 100, 40);
		btnCuadruplos.addActionListener(this);
		
		JLabel lblArea = new JLabel("Area de Trabajo");
		lblArea.setBounds(148, 15, 174, 30);
		lblArea.setFont(new Font("Calibri", Font.BOLD, 26));
		
		JLabel lblConsola = new JLabel("Consola");
		lblConsola.setBounds(337, 370, 86, 30);
		lblConsola.setFont(new Font("Calibri", Font.BOLD, 26));

		JScrollPane scrollPaneArea = new JScrollPane(area);
		scrollPaneArea.setBounds(30, 50, 440, 300);
		JScrollPane scrollPaneConsola = new JScrollPane(consola);
		scrollPaneConsola.setBounds(30, 410, 740, 330);
		
		add(scrollPaneArea);
		add(scrollPaneConsola);
		add(btnAbrir);
		add(btnCompilar);
		add(btnCuadruplos);
		add(lblConsola);
		add(lblArea);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnCompilar) {
			generarArchivo();
			compilar();
			return;
		}
		
		if(e.getSource() == btnCuadruplos) {
			if (cuadruplos.isEmpty() || area.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "No hay operaciones", "Vacío", JOptionPane.ERROR_MESSAGE);
			}else {
				String[][] lista = new String[cuadruplos.size()][5];
				for (int i = 0; i < cuadruplos.size(); i++) {
						lista[i][0] = cuadruplos.get(i).getExpresion();
						lista[i][1] = cuadruplos.get(i).getOperador();
						lista[i][2] = cuadruplos.get(i).getOperando1();
						lista[i][3] = cuadruplos.get(i).getOperando2();
						lista[i][4] = cuadruplos.get(i).getResultado();
				}
				
				String[] titulos = {"Expresión", "Operador", "Operando1", "Operando2", "Resultado"};
				
				VistaCuadruplos cuadruplos = new VistaCuadruplos(this, "Cuadruplos", titulos, lista);
				cuadruplos.setVisible(true);
			}
		}
		
		if (e.getSource() == btnAbrir) {
			JFileChooser chooser = new JFileChooser();
			int opcion = chooser.showSaveDialog(this);
			if (opcion == JFileChooser.APPROVE_OPTION) {
				File f = chooser.getSelectedFile();

				try {
					BufferedReader br= new BufferedReader(new FileReader(f));
					String lineaActual;
					while ((lineaActual = br.readLine()) != null) {
						area.append(lineaActual+"\n");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			
			}
		}
		
	}
	
	private void generarArchivo() {
		String ruta = "codigo.txt";
		File archivo = new File(ruta);
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(archivo));
			bw.write(area.getText());

			bw.close();
		} catch (Exception ex) {

		}
	}
	
	private void compilar() {		
		if(area.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Primero escribe código...");
			area.requestFocus();
			return;
		}
		
		Analiza analizador = new Analiza("codigo.txt");
		ArrayList<String> a1 = analizador.resultado;
		ArrayList<Token> tk = analizador.tokenRC;
		Sintactico s;
		LlenaTabla t;
		

		consola.setText("");
		
		for (int i = 0; i < a1.size(); i++) {
			consola.append(a1.get(i)+ "\n");
		}

		//if (a1.get(0).equals("No hay errores lexicos")) {
			s = new Sintactico(analizador.tokenRC);
			t = new LlenaTabla(analizador.tokenRC);
			tablaSimbolos = t.tablaSimbolos;
			ArrayList<String> errores = t.errores;
			
			for (int i = 0; i < errores.size(); i++) {
				consola.append(errores.get(i) + "\n");
			}
			CodigoIntermedio codigoIntermedio = new CodigoIntermedio(this.area.getText().trim(), tablaSimbolos);
			cuadruplos = codigoIntermedio.getCuadruplos();
		//}
	}
	

}