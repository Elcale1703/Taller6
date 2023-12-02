package uniandes.dpoo.taller1.modelo;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProductoAjustadoTest {

    @Test
    public void testGetNombre() {
        // Escenario de prueba
        ProductoMenu productoBase = new ProductoMenu("Hamburguesa", 10000);
        ProductoAjustado productoAjustado = new ProductoAjustado(productoBase);

        // Verificación
        assertEquals("Hamburguesa", productoAjustado.getNombre());
    }

    @Test
    public void testGetPrecioSinIngredientes() {
        // Escenario de prueba
        ProductoMenu productoBase = new ProductoMenu("Hamburguesa", 10000);
        ProductoAjustado productoAjustado = new ProductoAjustado(productoBase);

        // Verificación
        assertEquals(10000, productoAjustado.getPrecio());
    }

    @Test
    public void testGetPrecioConIngredientes() {
        // Escenario de prueba
        ProductoMenu productoBase = new ProductoMenu("Hamburguesa", 10000);
        ProductoAjustado productoAjustado = new ProductoAjustado(productoBase);
        Ingrediente ingrediente = new Ingrediente("Queso", 5000);
        productoAjustado.agregarIngrediente(ingrediente);

        // Verificación
        assertEquals(15000, productoAjustado.getPrecio());
    }

    @Test
    public void testGenerarTextoFacturaSinIngredientes() {
        // Escenario de prueba
        ProductoMenu productoBase = new ProductoMenu("Hamburguesa", 10000);
        ProductoAjustado productoAjustado = new ProductoAjustado(productoBase);

        // Verificación
        assertEquals("\n*Hamburguesa\t$10000\n", productoAjustado.generarTextoFactura());
    }

    @Test
    public void testGenerarTextoFacturaConIngredientes() {
        // Escenario de prueba
        ProductoMenu productoBase = new ProductoMenu("Hamburguesa", 10000);
        ProductoAjustado productoAjustado = new ProductoAjustado(productoBase);
        Ingrediente ingrediente = new Ingrediente("Queso", 5000);
        productoAjustado.agregarIngrediente(ingrediente);

        // Verificación
        assertEquals("\n*Hamburguesa\t$10000\n+Queso\t$5000\n", productoAjustado.generarTextoFactura());
    }

}
