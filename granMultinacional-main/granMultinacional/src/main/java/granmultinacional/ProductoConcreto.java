package granmultinacional;



public class ProductoConcreto extends Producto {

    // Constructor
    public ProductoConcreto(String codigo, int precioBase, int stock) {
        super(codigo, precioBase, stock);
    }

    // Implementación del método abstracto
    @Override
    public int calcularTotal(int cantidad, String horario) {
        // Implementación básica para calcular el total
        return cantidad * this.precioBase;
    }
}