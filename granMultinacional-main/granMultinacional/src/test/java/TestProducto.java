
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import granmultinacional.*;


//vamos a probar el gestor y las validaciones de producto, primero instanciamos el registro de producto
public class TestProducto {

    private RegistroProducto registroProducto;

    @BeforeEach
    public void setUp() {
        registroProducto = new RegistroProducto();

    }

    //prueba unitaria #1 para validar que el stock sea mayor a cero
    @Test
    public void testStockMayorACeroCP2() {
        // primero, creamos el pantalón a probar con stock en 0
        Pantalon pantalon = new Pantalon ("jeans", 'M', "PA001", 1000, 0);

        // llamamos al métdo agregarProducto y agregarmos el pantalón
        registroProducto.agregarProducto(pantalon);

        // creamos una flag que se inicialice en caso de que el producto fuese registrado
        boolean productoRegistrado = registroProducto.buscarProducto("PA001");

        // Si el producto fue registrado, debería fallar
        assertFalse(productoRegistrado, "El producto no debería registrarse con stock mayor a 0");

    }

    // prueba unitaria #2 para validar que el precio sea mayor a cero
    @Test
    public void testPrecioMayorACeroCP1() {
        // creamos pantalón con precio = 0
        Pantalon pantalon = new Pantalon ("jeans", 'M', "PA002", 0, 10);

        //agregamos pantalón
        registroProducto.agregarProducto(pantalon);

        // verificar si el producto se agregó (si es false el producto no se agregó)
        boolean productoRegistrado = registroProducto.buscarProducto("PA002");

        // si el producto fue registrado, la prueba fallará
        assertFalse(productoRegistrado, "El producto no debería registrarse con precio igual a 0");
    }

    // prueba unitaria #3 para verificar que la marca deba tener mínimo dos caracteres
    @Test
    public void testCaracteresMarcaCP3() {
        // creamos refrigerador con marca "A"
        Refrigerador refrigerador = new Refrigerador (5, "A", "RA001", 1000, 10 );

        // llamamos a metodo para agregar producto
        registroProducto.agregarProducto(refrigerador);

        // verificar si el producto se agregó
        boolean productoRegistrado = registroProducto.buscarProducto("RA001");

        // si el producto fue registrado la prueba fallará
        assertFalse(productoRegistrado, "El refrigerador fue registrado con una marca de menos de dos caracteres");
    }

    // prueba unitaria #4 para verificar que solo se puedan agregar pantalones con las telas predefinidas
    @Test
    public void testTipoTelaCP4() {
        // creamos un nuevo pantalón con tela 'Spandex'
        Pantalon pantalon = new Pantalon ("Spandex", 'M', "PA003", 1000, 10);

        // Registramos pantalon
        registroProducto.agregarProducto(pantalon);

        // flag registro
        boolean productoRegistrado = registroProducto.buscarProducto("PA003");

        // testeo, si el pantalón se registró la prueba fallará
        assertFalse(productoRegistrado, "Pantalón registrado con tela no válida");

    }

    // Prueba unitaria #5 para verificar si solo se admiten los sexos permitidos
    @Test
    public void testSexoPantalonCP5() {
        // crear pantalón con sexo 'O' (campo tipo char)
        Pantalon pantalon = new Pantalon ("jeans", 'O', "PA004", 1000, 10);

        // crear pantalón con sexo vacío
        Pantalon pantalonVacio = new Pantalon ("jeans", ' ', "PA005", 1000, 10);

        // registramos ambos pantalones
        registroProducto.agregarProducto(pantalon);
        registroProducto.agregarProducto(pantalonVacio);

        // flag para ver si se registraron ambos pantalones
        boolean productoRegistrado1 = registroProducto.buscarProducto("PA004");
        boolean productoRegistrado2 = registroProducto.buscarProducto("PA005");

        //pantalon 1
        assertFalse(productoRegistrado1, "PANTALON REGISTRADO CON SEXO NO VALIDO");
        assertFalse(productoRegistrado2, "PANTALON REGISTRADO CON SEXO VACÍO");
    }

    // prueba unitaria #6 para verificar agregar producto nuevo a la colección
    @Test
    public void testAgregarProductoCP8() {
        // creamos pantalon de cotele
        Pantalon jonson = new Pantalon ("cotele", 'M', "PA006", 1000, 10);

        //registramos pantalon
        registroProducto.agregarProducto(jonson);

        // verificamos con la flag
        boolean productoRegistrado = registroProducto.buscarProducto("PA006");

        // si el pantalón se agregó (True), la prueba será aprobada
        assertTrue(productoRegistrado, "Pantalon Jonson registrado correctamente.");
    }

    // prueba unitaria #7 para verificar el buscador de productos
    @Test
    public void testBuscarProductoCP9() {
        // creamos un pantalón
        Pantalon pantalon = new Pantalon ("jeans", 'M', "P001", 1000, 10);

        // agregamos
        registroProducto.agregarProducto(pantalon);

        // flag buscar producto, si lo encuentra será true
        boolean buscarProducto = registroProducto.buscarProducto("P001");

        // Si el pantalón fue encontrado (True) la prueba será aprobada
        assertTrue(buscarProducto, "Pantalón encontrado correctamente");
    }

    // prueba unitaria #8 para verificar si se eliminan los productos correctamente
    @Test
    public void testEliminarProductoCP11() {
        // Creamos un producto, esta vez una lavadora
        Lavadora lavadora = new Lavadora ('A', "SAMSUNG", "LA001", 1000, 10);

        // agregamos producto a la colección
        registroProducto.agregarProducto(lavadora);

        // eliminamos producto
        registroProducto.eliminarProducto("LA001");

        // buscamos producto para ver si fue eliminado
        boolean productoEliminado = registroProducto.buscarProducto("LA001");

        // si el producto es encontrado, no se eliminó, por lo que la prueba fallaría
        assertFalse(productoEliminado, "El producto no fue eliminado, prueba fallida");
    }

    // prueba unitaria #9 para verificar caracteres no validos en el campo "precio"
    @Test
    public void testPrecioInvalidoCP26() {
        String precioEntrada = "abc"; // Simula un dato ingresado por un usuario
        boolean excepcionLanzada = false;

        try {
            // Intentar convertir la entrada a un número
            int precioConvertido = Integer.parseInt(precioEntrada);

            // Intentar crear un producto con el precio inválido
            Pantalon pantalon = new Pantalon("jeans", 'M', "PA007", precioConvertido, 10);

            // Agregar el producto (este paso no debería alcanzarse si hay un error en el precio)
            RegistroProducto.agregarProducto(pantalon);
        } catch (NumberFormatException e) {
            excepcionLanzada = true; // Marcar que se lanzó la excepción esperada
            assertEquals("For input string: \"abc\"", e.getMessage(), "El mensaje de la excepción no coincide");
        }

        // Verificar que se lanzó la excepción
        assertTrue(excepcionLanzada, "Se esperaba que se lanzara una excepción debido a un precio no numérico.");
    }

    // prueba unitaria #10 para verificar si se pueden agregar marcas con más de 255 caracteres
    @Test
    public void testMaximoCaracteresCP30() {
        //creamos lavadora con marca de más de 255 caracteres
        Lavadora lavadora = new Lavadora ('A', "Este es un ejemplo de un texto largo que contiene más de 255 caracteres. El propósito de este texto es cumplir con el requerimiento de superar los 255 caracteres y demostrar que el sistema es capaz de manejar cadenas largas sin problemas. Además, este texto tiene el objetivo de ilustrar cómo se puede generar un contenido extenso para pruebas o para situaciones en las que sea necesario superar la longitud estándar de caracteres.", "LA002", 1000000000, 10);

        // registramos lavadora
        RegistroProducto.agregarProducto(lavadora);

        // boolean para verificar si fue agregado
        boolean lavadoraAgregada = RegistroProducto.buscarProducto("LA002");

        assertFalse(lavadoraAgregada, "No se debería agregar un producto de más de 255 caracteres");
    }


}
