package tiposDatosAbs;

public interface ABBTDA {
	
	int raiz();
	ABBTDA hijoIzq();
	ABBTDA hijoDer();
	boolean arbolVacio();
	void inicializarArbol();
	void agregarElem(int x);
	void eliminarElem(int x);
	
}