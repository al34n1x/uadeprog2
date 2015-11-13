package Metodos;

import interfaces.GrafoTDA;

public class MetodosTPO {


	public static void CargarGrafo(GrafoTDA G) {
	// Ejemplo del Apunte
	G.AgregarVertice(1);
	G.AgregarVertice(2);
	G.AgregarVertice(3);
	G.AgregarVertice(4);
	G.AgregarVertice(5);
	G.AgregarVertice(6);
	G.AgregarVertice(7);
	G.AgregarVertice(8);
	G.AgregarVertice(10);
	G.AgregarArista(1, 2, 12);
	G.AgregarArista(1, 3, 21);
	G.AgregarArista(2, 1, 10);
	G.AgregarArista(3, 4, 32);
	G.AgregarArista(4, 6, 10);
	G.AgregarArista(3, 5, 9);
	G.AgregarArista(5, 6, 12);
	G.AgregarArista(6, 5, 87);
	G.AgregarArista(8, 10, 10);
}

	public static GrafoTDA CaminosMinimos(GrafoTDA G, int vert_orig) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void MostrarCaminos(GrafoTDA GCM, int vert_orig) {
	// TODO Auto-generated method stub
    }
}