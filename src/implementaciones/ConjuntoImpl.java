package implementaciones;

import tiposDatosAbs.ConjuntoTDA;

public class ConjuntoImpl implements ConjuntoTDA{

	class Nodo {
		int info ;
		Nodo sig ;
	}

	Nodo c;

	public void inicializarConjunto() {
		c = null;
	}

	public boolean conjuntoVacio() {
		return (c == null);
	}

	public void agregar(int x) {
		/* Verifica que x no este en el conjunto */
		if (!this . pertenece(x)){
			Nodo aux = new Nodo ();
			aux. info = x;
			aux.sig = c;
			c = aux ;
		}
	}

	public int elegir() {
		return c.info;
	}

	public void sacar(int x) {
		if (c!= null ){
			// si es el primer elemento de la lista
			if (c. info == x) {
				c = c.sig ;
			} else {
				Nodo aux = c;
				while (aux .sig != null && aux. sig.info !=x)
					aux = aux. sig;
				if (aux. sig != null )
					aux. sig = aux.sig. sig;
			}
		}
	}

	public boolean pertenece(int x) {
		Nodo aux = c;
		while ((aux != null ) && (aux. info != x)){
			aux = aux.sig ;
		}
		return (aux != null );
	}
	
	public ConjuntoImpl devolverCopia(){
		ConjuntoImpl conjuntoImpl = new ConjuntoImpl();
		conjuntoImpl.setNodo(this.c);
		return conjuntoImpl;
	}

	public void setNodo(Nodo c) {
		this.c = c;
	}

}
