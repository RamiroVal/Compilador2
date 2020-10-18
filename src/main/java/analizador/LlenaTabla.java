package main.java.analizador;

import java.util.ArrayList;
import java.util.Arrays;

public class LlenaTabla {
	final int clase = 0, publico = 1, privado = 2, whilex = 3, entero = 4, booleano = 5, llaveizq = 6, llaveder = 7,
			EQ = 8, semi = 9, menor = 10, mayor = 11, d2EQ = 12, menorEQ = 13, mayorEQ = 14, diferente = 15, difEQ = 16,
			truex = 17, falsex = 18, brackizq = 19, brackder = 20, div = 21, mas = 22, menos = 23, mult = 24, ifx = 25, doble = 26, charx = 27,
			num = 50, numd=51, ID = 52;

	ArrayList<Token> tokenRC;
	ArrayList<TablaSimbolos> valoresTab = new ArrayList<TablaSimbolos>();
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

				/*if (tipo[i - 1] == publico) {
					if (nombreTipo.equals("int"))
						ValoresHaciaTabla("public", nombreTipo, nombre[i + 1], "null", renglon[i + 1], columna[i + 1]);
					if (nombreTipo.equals("boolean"))
						ValoresHaciaTabla("public", nombreTipo, nombre[i + 1], "null", renglon[i + 1], columna[i + 1]);
					if (nombreTipo.equals("double"))
						ValoresHaciaTabla("public", nombreTipo, nombre[i + 1], "null", renglon[i + 1], columna[i + 1]);

				} else if (tipo[i - 1] == privado) {
					if (nombreTipo.equals("int"))
						ValoresHaciaTabla("private", nombreTipo, nombre[i + 1], "0", renglon[i + 1], columna[i + 1]);
					else
						ValoresHaciaTabla("private", nombreTipo, nombre[i + 1], "false", renglon[i + 1],
								columna[i + 1]);
				} else {
					if (nombreTipo.equals("int"))
						ValoresHaciaTabla("S/M", nombreTipo, nombre[i + 1], "0", renglon[i + 1], columna[i + 1]);
					else
						ValoresHaciaTabla("S/M", nombreTipo, nombre[i + 1], "false", renglon[i + 1], columna[i + 1]);
				}*/

			}

		}
		// Aqu� a las variables declaradas se les asignan lo valores correspondientes al
		// c�digo en el .txt
		for (int i = 0; i < tokenRC.size(); i++) {

			for (int j = 0; j < valoresTab.size(); j++) {
				if (tokenRC.get(i).getToken().equals(valoresTab.get(j).nombre))
					if (tipo[i] == ID && tipo[i + 1] == EQ) {

						if (tipo[i + 3] == mas || tipo[i + 3] == menos || tipo[i + 3] == div || tipo[i + 3] == mult) {
							valoresTab.set(j,
									new TablaSimbolos(valoresTab.get(j).rango, valoresTab.get(j).tipo,
											valoresTab.get(j).nombre,
											tokenRC.get(i + 2).getToken() + " " + tokenRC.get(i + 3).getToken() + " "
													+ tokenRC.get(i + 4).getToken(),
											valoresTab.get(j).renglon, valoresTab.get(j).columna));
						} else {

							valoresTab.set(j,
									new TablaSimbolos(valoresTab.get(j).rango, valoresTab.get(j).tipo,
											valoresTab.get(j).nombre, tokenRC.get(i + 2).getToken(),
											valoresTab.get(j).renglon, valoresTab.get(j).columna));

						}
					}
			}

		}
		
		
		// Imprime la tabla de simbolos con sus datos siempre y cuando no haya errores
		if (errores.isEmpty()) {
			
		Main.consola.append("\n" + "No." + blancos("no.       ") + "Modificador" + blancos("modificador") + "Tipo"
				+ blancos("tipo") + "Nombre" + blancos("nombre") + "Valor" + blancos("valor") + "Renglon"
				+ blancos("renglon") + "Columna o No. de token" + blancos("columna o No. de token") + "\n");
		for (int i = 0; i < valoresTab.size(); i++) {
			Main.consola.append((i + 1) +"    "+ blancos(String.valueOf((i + 1 + "    " ))) + valoresTab.get(i).rango
					+ "   "+blancos(valoresTab.get(i).rango) +valoresTab.get(i).tipo +" "+ blancos(valoresTab.get(i).tipo)
					+ "    "+valoresTab.get(i).nombre + blancos(valoresTab.get(i).nombre)+ "    " + valoresTab.get(i).valor
					+ blancos(valoresTab.get(i).valor)+ "    " + valoresTab.get(i).renglon + blancos(valoresTab.get(i).renglon)+ "        "
					+ valoresTab.get(i).columna + blancos(valoresTab.get(i).columna)+"\n");
		}
		}
		
	}
	
	//Valida si una declaración ya fue agregada a la tabla
	public int estaRepedio(String nombre) {
		int esta = -1;
		for (int i = 0; i < valoresTab.size(); i ++) {
			if (valoresTab.get(i).nombre.equals(nombre)) {
				esta=i;
			}
		}
		return esta;
	}
        
	
	public void agregaTabla(String ran, String tip, String nom, String val, String reng, String col) {
		int rep = estaRepedio(nom);
		System.out.println("Respuesta de rep antes de entrar al if " + rep);
		if (rep == -1) {System.out.println("Entro al if rep = " + rep);
			valoresTab.add(new TablaSimbolos(ran, tip, nom, val, reng, col));
			System.out.println("Guardo los datos rep = " + rep);
		}else {System.out.println("No entro al if rep = " + rep);
			System.out.println("La variable " + nom + " ya ha sido decladara en el renglón " + valoresTab.get(rep).renglon + " columna " + valoresTab.get(rep).columna);
			errores.add("La variable " + nom + " ya ha sido decladara en el renglón " + valoresTab.get(rep).renglon + " columna " + valoresTab.get(rep).columna);
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
	
	public ArrayList<TablaSimbolos>  getValoresTabla(){
		return this.valoresTab;
	}
}