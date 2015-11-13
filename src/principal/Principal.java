package principal;

import Metodos.MetodosTPO;
import implementaciones.GrafoImpl;
import interfaces.GrafoTDA;

// AGREGAR LOS IMPORT TODAS LAS CLASES NECESARIAS

public class Principal {

	public static void main(String[] args) {
	
		GrafoTDA G = new GrafoImpl();
		G.InicializarGrafo();
		
		MetodosTPO.CargarGrafo(G);
		
		int vert_orig = 1;
		
		GrafoTDA GCM= MetodosTPO.CaminosMinimos(G, vert_orig);
		
		MetodosTPO.MostrarCaminos(GCM,vert_orig);
	}

}
