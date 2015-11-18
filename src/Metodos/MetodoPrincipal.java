// GRUPO #8


package Metodos;



/* El método CaminosMinimos que utiliza el algoritmo de Dijkstra modificado para la
obtención de los caminos mínimos entre un vértice de un grafo y los restantes.*/

// El metodo Mostrar Caminos para visualizar resultados.


//Mediante Import incorporamos todas las clases necesarias para proceder con el trabajo.

import implementaciones.ConjuntoImpl;
import implementaciones.GrafoImpl;
import tiposDatosAbs.ConjuntoTDA;
import tiposDatosAbs.GrafoTDA;


public class MetodoPrincipal {

	public static void main(String[] args) 
	
	{

		GrafoTDA Grafo = new GrafoImpl();
		Grafo.inicializarGrafo();

		CargarGrafo(Grafo);

		int vert_orig = 1;

		GrafoTDA GrafoCM;
		GrafoCM = CaminosMinimos(Grafo, vert_orig);

		MostrarCaminos(GrafoCM,vert_orig);

	}


	private static void CargarGrafo(GrafoTDA Grafo) 
	
	{
		// Agregamos vertices basados en el ejemplo de TPO
		
		Grafo.agregarVertice(1);
		Grafo.agregarVertice(2);
		Grafo.agregarVertice(3);
		Grafo.agregarVertice(4);
		Grafo.agregarVertice(5);
		Grafo.agregarVertice(6);
		Grafo.agregarVertice(7);
		Grafo.agregarVertice(8);
		Grafo.agregarVertice(10);
		
		//Agregamos aristas basados en el ejemplo de TPO
		
		Grafo.agregarArista(1, 3, 21); 
		Grafo.agregarArista(1, 2, 12); 
		Grafo.agregarArista(2, 1, 10);  
		Grafo.agregarArista(3, 4, 32); 
		Grafo.agregarArista(4, 6, 10);
		Grafo.agregarArista(3, 5, 9);
		Grafo.agregarArista(5, 6, 12);
		Grafo.agregarArista(6, 5, 87);
		Grafo.agregarArista(8, 10, 10);
	}

	private static GrafoTDA CaminosMinimos(GrafoTDA Grafo, int v) 
	
	{
		
		// Paso 1
		ConjuntoImpl visitados = new ConjuntoImpl();
		visitados.inicializarConjunto();
		visitados.agregar(v);

		// Paso 2
		GrafoTDA A = new GrafoImpl();
		A.inicializarGrafo();
		GrafoTDA R = new GrafoImpl();
		R.inicializarGrafo();

		ConjuntoTDA conjuntoAuxVerticesG2 = Grafo.vertices();
		
		while(!conjuntoAuxVerticesG2.conjuntoVacio())
		{
			
			// Copia conjuntoAuxVerticesG2 a Auxiliar (A) y a Resultado (R)
			
			int w = conjuntoAuxVerticesG2.elegir();
			A.agregarVertice(w);
			R.agregarVertice(w);
			conjuntoAuxVerticesG2.sacar(w);
		}
		
		//Se carga nuevamente conjuntoAuxVerticesG2 
		
		conjuntoAuxVerticesG2 = Grafo.vertices();
		
		while(!conjuntoAuxVerticesG2.conjuntoVacio())
		{
			int v1 = conjuntoAuxVerticesG2.elegir();
			if(Grafo.existeArista(v, v1))
			{
				A.agregarArista(v, v1, Grafo.pesoArista(v, v1));
				R.agregarArista(v, v1, Grafo.pesoArista(v, v1));
			}
			
			conjuntoAuxVerticesG2.sacar(v1);
		}

		ConjuntoTDA pendientes = Grafo.vertices();
		ConjuntoTDA visitadosAux = visitados.devolverCopia();
		while(!visitadosAux.conjuntoVacio())
		{
			int i = visitadosAux.elegir();
			if(pendientes.pertenece(i))
			{
				pendientes.sacar(i);
			}
			
			visitadosAux.sacar(i);
		}

		ConjuntoImpl auxPendientes = pendientes.devolverCopia();
		
		// Paso 3
		while(!pendientes.conjuntoVacio())
		{
			int w = elegirVerticeDeMinimaArista(v,pendientes.devolverCopia(),A);
			if(w != -1)
			{ 
				visitados.agregar(w);
				pendientes.sacar(w);
				auxPendientes = pendientes.devolverCopia();
				while(!auxPendientes.conjuntoVacio())
				{
					int p = auxPendientes.elegir();
					if(A.existeArista(v, w) && Grafo.existeArista(w, p))
					{
						if(A.existeArista(v, p))
						{
							int pesoAristaEnA_vw = A.pesoArista(v, w);
							int pesoAristaEnG_wp = Grafo.pesoArista(w, p);
							int pesoAristaEnA_vp = Grafo.pesoArista(w, p);

							if(pesoAristaEnA_vw + pesoAristaEnG_wp < pesoAristaEnA_vp )
							{
								A.agregarArista(v, p, pesoAristaEnA_vw + pesoAristaEnG_wp);
								ConjuntoTDA aux = auxPendientes.devolverCopia();
								aux.sacar(p);
								while(!aux.conjuntoVacio())
								{
									int a = aux.elegir();
									if(R.existeArista(a, p))
										R.eliminarArista(a, p);
									aux.sacar(a);
								}
								R.agregarArista(w, p, Grafo.pesoArista(w, p));
							}
						} 
						
						else 
						
						{
							A.agregarArista(v, p, A.pesoArista(v, w) + Grafo.pesoArista(w, p));
							R.agregarArista(w, p, Grafo.pesoArista(w, p));
						}
					}
					auxPendientes.sacar(p);
				}
			} 
			
			else
			
			{
				
				break;
				
				// Llegado este punto no se han encontrado mas vertices adyacentes a v
			}
		}
		return R;

	}

	private static int elegirVerticeDeMinimaArista(int v, ConjuntoTDA pendientes, GrafoTDA Grafo) 
	{
		int menorArista = -1;
		int ultimoVertice = -1;
		while(!pendientes.conjuntoVacio())
		{
			int i = pendientes.elegir();
			pendientes.sacar(i);
			if(Grafo.existeArista(v, i))
			{
				int pesoArista = Grafo.pesoArista(v, i);
				if(menorArista == -1)
				{
					menorArista = pesoArista;
					ultimoVertice = i;
				}
				else
				{
					if(pesoArista < menorArista )
					{
						menorArista = pesoArista;
						ultimoVertice = i;
					}
				}
			}
		}
		
		return ultimoVertice;
	}

	private static void MostrarCaminos(GrafoTDA gCM, int vert_orig) 
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("Vertice origen: " + vert_orig + "\n");
		buffer.append("Camino detallado + (Costo) hasta\n");
		
		ConjuntoTDA verticesGrafo = gCM.vertices();
		verticesGrafo.sacar(vert_orig);
		while(!verticesGrafo.conjuntoVacio())
		{
			int elegir = verticesGrafo.elegir();
			buffer.append(elegir + ": ");
			int peso = devolverCaminoInverso(buffer, gCM, elegir, 0);
			if(peso == 0)
				buffer.append("-\n");
			else
				buffer.append( elegir + " (" + peso + ")\n");
			verticesGrafo.sacar(elegir);
		}
		
        System.out.println(buffer.toString());
	}
	
	private static int devolverCaminoInverso(StringBuffer buffer, GrafoTDA gCM, int vertice, int peso)
	{
		ConjuntoTDA verticesGrafo = gCM.vertices();
		while(!verticesGrafo.conjuntoVacio())
		{
			int i = verticesGrafo.elegir();
			if(gCM.existeArista(i, vertice))
			{
				peso += gCM.pesoArista(i, vertice);
				peso = devolverCaminoInverso(buffer, gCM, i, peso);
				buffer.append(i + " - ");
			}
			verticesGrafo.sacar(i);
		}
		return peso;
	}
}
