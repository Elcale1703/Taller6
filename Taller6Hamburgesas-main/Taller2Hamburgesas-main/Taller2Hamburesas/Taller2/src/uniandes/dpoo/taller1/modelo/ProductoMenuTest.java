package uniandes.dpoo.taller1.modelo;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProductoMenuTest {

    @Test
    public void testAgregarIngrediente() {
        // Escenario de prueba
        ProductoMenu producto = new ProductoMenu("Hamburguesa", 10000);
        Ingrediente ingrediente = new Ingrediente("Queso", 5000);

        // Acción
        producto.agregarIngrediente(ingrediente);

        // Verificación
        assertTrue(producto.getIngredientesAgregados().contains(ingrediente));
    }

    @Test
    public void testEliminarIngrediente() {
        // Escenario de prueba
        ProductoMenu producto = new ProductoMenu("Hamburguesa", 10000);
        Ingrediente ingrediente = new Ingrediente("Queso", 5000);

        // Acción
        producto.eliminarIngrediente(ingrediente);

        // Verificación
        assertTrue(producto.getIngredientesEliminados().contains(ingrediente));
    }

    @Test
    public void testGetPrecioSinIngredientes() {
        // Escenario de prueba
        ProductoMenu producto = new ProductoMenu("Hamburguesa", 10000);

        // Verificación
        assertEquals(10000, producto.getPrecio());
    }

    @Test
    public void testGetPrecioConIngredientes() {
        // Escenario de prueba
        ProductoMenu producto = new ProductoMenu("Hamburguesa", 10000);
        Ingrediente ingrediente = new Ingrediente("Queso", 5000);
        producto.agregarIngrediente(ingrediente);

        // Verificación
        assertEquals(15000, producto.getPrecio());
    }

    @Test
    public void testGenerarTextoFacturaSinIngredientes() {
        // Escenario de prueba
        ProductoMenu producto = new ProductoMenu("Hamburguesa", 10000);

        // Verificación
        assertEquals("\n*Hamburguesa \t$10000", producto.generarTextoFactura());
    }

    @Test
    public void testGenerarTextoFacturaConIngredientes() {
        // Escenario de prueba
        ProductoMenu producto = new ProductoMenu("Hamburguesa", 10000);
        Ingrediente ingrediente = new Ingrediente("Queso", 5000);
        producto.agregarIngrediente(ingrediente);

        // Verificación
        assertEquals("\n*Hamburguesa \t$10000", producto.generarTextoFactura());
    }

}
