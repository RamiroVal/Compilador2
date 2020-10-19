package main.java.analizador;

import java.util.ArrayList;
import java.util.Arrays;

public class LlenaTabla {
	final int clase = 0, publico = 1, privado = 2, whilex = 3, entero = 4, booleano = 5, llaveizq = 6, llaveder = 7,
			EQ = 8, semi = 9, menor = 10, mayor = 11, d2EQ = 12, menorEQ = 13, mayorEQ = 14, diferente = 15, difEQ = 16,
			truex = 17, falsex = 18, brackizq = 19, brackder = 20, div = 21, mas = 22, menos = 23, mult = 24, ifx = 25, doble = 26, charx = 27,
			num = 50, numd=51, ID = 52;

	ArrayList<Token> tokenRC;
	ArrayList<TablaSimbolos> tablaSimbolos = new ArrayList<TablaSimbolos>();
	ArrayList<String> errores = new ArrayList<String>();

	public LlenaTabla(ArrayList<Token> tokenrc) {

//		public String rango, tipo, nombre, valor, renglon, columna;

		tokenRC = tokenrc;
		String nombre[] = new String[tokenRC.size()];
		int tipo[] = new int[tokenRC.size()];
		String nombreTipo = "";
		String valorTipo = "";
		String mod = "";
		String renglon[] = new String[tokenRC.size()];
		String columna[] = new String[tokenRC.size()];

		// desmonto valores del arraylist en arreglos para su uso m�s f�cil

		for (int i = 0; i < tokenrc.size(); i++) {
			nombre[i] = tokenrc.get(i).getToken();
			tipo[i] = tokenrc.get(i).getTipo();
			renglon[i] = String.valueOf(tokenrc.get(i).getRenglon());
			columna[i] = String.valueOf(tokenrc.get(i).getColumna());

		}
		System.out.println("Imprime los tipos " + Arrays.toString(tipo));

		// Asigna valores al arraylist que desplegar� la tabla
		for (int i = 0; i < tokenrc.size(); i++) {
	/*		System.out.println("Imprime tipo dato [" + i + "]: " + Arrays.toString(tipo));
			System.out.println("Imprime nombre [" + i + "]: " + Arrays.toString(nombre));
			System.out.println("Imprime renglon [" + i + "]: " + Arrays.toString(renglon));
			System.out.println("Imprime columna [" + i + "]: " + Arrays.toString(columna));
			System.out.println("\n---------------------------------------------------------------------\n------------------------------------------------");
*/
			if (tipo[i] == entero || tipo[i] == booleano || tipo[i] == doble || tipo[i] == charx) {

				/*System.out.println("Imprime tipo dato DENTRO [" + i + "]: " + Arrays.toString(tipo));
				System.out.println("Imprime nombre DENTRO [" + i + "]: " + Arrays.toString(nombre));
				System.out.println("Imprime renglon DENTRO [" + i + "]: " + Arrays.toString(renglon));
				System.out.println("Imprime columna DENTRO [" + i + "]: " + Arrays.toString(columna));*/
				if (tipo[i] == entero) {System.out.println("Entra if entero");
					nombreTipo = "int";
					if (tipo[i - 1] == publico) {//System.out.println("Entra al if publico");
						mod = "public";
						if(tipo[i + 2 ] == EQ) {//System.out.println("Entra al if equals");
						valorTipo = Integer.toString(tipo[i + 3]);
						}else {System.out.println("No entro al equals");
							valorTipo = "N/I";
						}
					}else if (tipo[i - 1] == privado) {//System.out.println("No entro al publico");
						mod = "private";
						if(tipo[i + 2 ] == EQ) {//System.out.println("Entra al if equals del privado");
							valorTipo = Integer.toString(tipo[i + 3]);
							}else {System.out.println("No entro al eq del privado");
								valorTipo = "N/I";
							}
					}else {System.out.println("No entro al privado ni al publico");
						mod = "S/M";
						if(tipo[i + 3 ] == EQ) {//System.out.println("Entro al equals del nulo");
							valorTipo = Integer.toString(tipo[i + 3]);
							}else {//System.out.println("No entro al equals del nulo");
								valorTipo = "N/I";
							}
					}
					System.out.println("ESTO SE MANDA A GUARDAR: " + mod + " | " + nombreTipo + " | " + valorTipo + " | " + renglon[i + 1] + " | " + columna[i + 1]);
					agregaTabla(mod, nombreTipo, nombre[i + 1], valorTipo, renglon[i + 1], columna[i + 1]);
				}else
				if (tipo[i] == booleano) {
					nombreTipo = "boolean";
					if (tipo[i - 1] == publico) {
						mod = "public";
						if(tipo[i + 2 ] == EQ) {
						valorTipo = Integer.toString(tipo[i + 3]);
						}else {
							valorTipo = "N/I";
						}
					}else if (tipo[i - 1] == privado) {
						mod = "private";
						if(tipo[i + 2 ] == EQ) {
							valorTipo = Integer.toString(tipo[i + 3]);
							}else {
								valorTipo = "N/I";
							}
					}else {
						mod = "S/M";
						if(tipo[i + 2 ] == EQ) {
							valorTipo = Integer.toString(tipo[i + 3]);
							}else {
								valorTipo = "N/I";
							}
					}
					agregaTabla(mod, nombreTipo, nombre[i + 1], valorTipo, renglon[i + 1], columna[i + 1]);
					
				}else
				if (tipo[i] == doble) {
					nombreTipo = "double";
					if (tipo[i - 1] == publico) {
						mod = "public";
						if(tipo[i + 2 ] == EQ) {
						valorTipo = Integer.toString(tipo[i + 3]);
						}else {
							valorTipo = "N/I";
						}
					}else if (tipo[i - 1] == privado) {
						mod = "private";
						if(tipo[i + 2 ] == EQ) {
							valorTipo = Integer.toString(tipo[i + 3]);
							}else {
								valorTipo = "N/I";
							}
					}else {
						mod = "S/M";
						if(tipo[i + 2 ] == EQ) {
							valorTipo = Integer.toString(tipo[i + 3]);
							}else {
								valorTipo = "N/I";
							}
					}
					agregaTabla(mod, nombreTipo, nombre[i + 1], valorTipo, renglon[i + 1], columna[i + 1]);
				}else
				if (tipo[i] == charx) {
					nombreTipo = "char";
					if (tipo[i - 1] == publico) {
						mod = "public";
						if(tipo[i + 2 ] == EQ) {
						valorTipo = Integer.toString(tipo[i + 3]);
						}else {
							valorTipo = "N/I";
						}
					}else if (tipo[i - 1] == privado) {
						mod = "private";
						if(tipo[i + 2 ] == EQ) {
							valorTipo = Integer.toString(tipo[i + 3]);
							}else {
								valorTipo = "N/I";
							}
					}else {
						mod = "S/M";
						if(tipo[i + 2 ] == EQ) {
							valorTipo = Integer.toString(tipo[i + 3]);
							}else {
								valorTipo = "N/I";
							}
					}
					agregaTabla(mod, nombreTipo, nombre[i + 1], valorTipo, renglon[i + 1], columna[i + 1]);
				}

			}

		}
		// Aqu� a las variables declaradas se les asignan lo valores correspondientes al
		// c�digo en el .txt
		for (int i = 0; i < tokenRC.size(); i++) {

			for (int j = 0; j < tablaSimbolos.size(); j++) {
				if (tokenRC.get(i).getToken().equals(tablaSimbolos.get(j).nombre))
					if (tipo[i] == ID && tipo[i + 1] == EQ) {

						if (tipo[i + 3] == mas || tipo[i + 3] == menos || tipo[i + 3] == div || tipo[i + 3] == mult) {
							tablaSimbolos.set(j,
									new TablaSimbolos(tablaSimbolos.get(j).rango, tablaSimbolos.get(j).tipo,
											tablaSimbolos.get(j).nombre,
											tokenRC.get(i + 2).getToken() + " " + tokenRC.get(i + 3).getToken() + " "
													+ tokenRC.get(i + 4).getToken(),
											tablaSimbolos.get(j).renglon, tablaSimbolos.get(j).columna));
						} else {

							tablaSimbolos.set(j,
									new TablaSimbolos(tablaSimbolos.get(j).rango, tablaSimbolos.get(j).tipo,
											tablaSimbolos.get(j).nombre, tokenRC.get(i + 2).getToken(),
											tablaSimbolos.get(j).renglon, tablaSimbolos.get(j).columna));

						}
					}
			}

		}
		
		revisaDeclaraciones(tablaSimbolos);
		
		// Imprime la tabla de simbolos con sus datos siempre y cuando no haya errores
		if (errores.isEmpty()) {
			
		Main.consola.append("\n" + "No." + blancos("no.       ") + "Modificador" + blancos("modificador") + "Tipo"
				+ blancos("tipo") + "Nombre" + blancos("nombre") + "Valor" + blancos("valor") + "Renglon"
				+ blancos("renglon") + "Columna o No. de token" + blancos("columna o No. de token") + "\n");
		for (int i = 0; i < tablaSimbolos.size(); i++) {
			Main.consola.append((i + 1) +"    "+ blancos(String.valueOf((i + 1 + "    " ))) + tablaSimbolos.get(i).rango
					+ "   "+blancos(tablaSimbolos.get(i).rango) + tablaSimbolos.get(i).tipo +" "+ blancos(tablaSimbolos.get(i).tipo)
					+ "    "+tablaSimbolos.get(i).nombre + blancos(tablaSimbolos.get(i).nombre)+ "    " + tablaSimbolos.get(i).valor
					+ blancos(tablaSimbolos.get(i).valor)+ "    " + tablaSimbolos.get(i).renglon + blancos(tablaSimbolos.get(i).renglon)+ "        "
					+ tablaSimbolos.get(i).columna + blancos(tablaSimbolos.get(i).columna)+"\n");
		}
		}
		
	}
	
	//Valida si la asignacion es booleana
	public boolean esBooleano(String asignacion) {
		return asignacion.equals("false") || asignacion.equals("true");
	}
	
	//Valida si la asignacion es entera
	public boolean esIntOChar(String asignacion) {
		if (asignacion.matches("[0-9]+")) {
			return true;
		}else {
			return false;
		}
	}
	
	//Valida si la asignacion es double
	public boolean esDouble(String asignacion) {
		if (asignacion.matches("[0-9]+\\.?[0-9]*")) {
			return true;
		}else {
			return false;
		}
	}
	
	//Comprueba que las asignaciones sean correctas
	public void revisaDeclaraciones(ArrayList<TablaSimbolos> tablaSimbolos) {
		for (int i = 0; i < tablaSimbolos.size(); i++) {
			TablaSimbolos s = tablaSimbolos.get(i);
			if (s.tipo.equals("int") && !esIntOChar(s.valor)) {
				if (esDouble(s.valor)) {
					errores.add("La variable entera " + s.nombre + " del renglón" + s.renglon + " columna " + s.columna + " se le intenta asignar un valor de tipo double");
				}
				if (esBooleano(s.valor)) {
					errores.add("La variable entera " + s.nombre + " del renglon " + s.renglon + " columna " + s.columna + " se le intenta asginar un valor de tipo booleano");
				}
			}
			if (s.tipo.equals("double") && !esDouble(s.valor)) {
				if (esBooleano(s.valor)) {
					errores.add("La variable double " + s.nombre + " del renglón " + s.renglon + " columna " + s.columna + " se le intenta asignar un valor de tipo booleano");
				}	
			}
			if (s.tipo.equals("boolean") && !esBooleano(s.valor)) {
				if (esIntOChar(s.valor)) {
					errores.add("La variable booleana " + s.nombre + " del renglon " + s.renglon + " columna " + s.columna + " se le intenta asignar un valor de tipo entero");
				}
				if (esDouble(s.valor)) {
					errores.add("La variable booleana " + s.nombre + " del renglon " + s.renglon + " columna " + s.columna + " se le intenta asignar un valor de tipo double");
				}
			}
			if (s.tipo.equals("char") && !esIntOChar(s.valor)) {
				if (esDouble(s.valor)) {
					errores.add("La variable char " + s.nombre + " del renglon " + s.renglon + " columna " + s.columna + " se le intenta asignar un valor de tipo dobule");
				}
				if (esBooleano(s.valor)) {
					errores.add("La variable char " + s.nombre + " del renglon " + s.renglon + " columna " + s.columna + " se le intenta asignar un valor de tipo booleano");
				}
			}
		}
	}
	
	//Valida si una declaración ya fue agregada a la tabla
	public int estaRepedio(String nombre) {
		int esta = -1;
		for (int i = 0; i < tablaSimbolos.size(); i ++) {
			if (tablaSimbolos.get(i).nombre.equals(nombre)) {
				esta=i;
			}
		}
		return esta;
	}
        
	//Método que agrega las declaraciones a la tabla de signos
	public void agregaTabla(String ran, String tip, String nom, String val, String reng, String col) {
		int rep = estaRepedio(nom);
		System.out.println("Respuesta de rep antes de entrar al if " + rep);
		if (rep == -1) {System.out.println("Entro al if rep = " + rep);
			tablaSimbolos.add(new TablaSimbolos(ran, tip, nom, val, reng, col));
			System.out.println("Guardo los datos rep = " + rep);
		}else {System.out.println("No entro al if rep = " + rep);
			System.out.println("La variable " + nom + " ya ha sido decladara en el renglón " + tablaSimbolos.get(rep).renglon + " columna " + tablaSimbolos.get(rep).columna);
			errores.add("La variable " + nom + " ya ha sido decladara en el renglón " + tablaSimbolos.get(rep).renglon + " columna " + tablaSimbolos.get(rep).columna);
		}
		System.out.println("Valor de rep al finalizar el metodo = " + rep);
		
	}

	public String blancos(String cadena) {

		String blancos = "";

		for (int i = cadena.length(); i < 15; i++) {
			blancos += " ";
		}

		return blancos;
	}

}