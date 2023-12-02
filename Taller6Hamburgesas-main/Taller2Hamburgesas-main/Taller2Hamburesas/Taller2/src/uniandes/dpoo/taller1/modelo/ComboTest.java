package uniandes.dpoo.taller1.modelo;

import org.junit.Test;
import static org.junit.Assert.*;

public class ComboTest {

    @Test
    public void testAgregarProductoACombo() {
        // Escenario de prueba
        Combo combo = new Combo("Combo1", 0.1);
        ProductoMenu producto = new ProductoMenu("Hamburguesa", 10000);

        // Acción
        combo.agregarProductoACombo(producto);

        // Verificación
        assertTrue(combo.getItemsCombo().contains(producto));
    }

    @Test
    public void testGetPrecioSinDescuento() {
        // Escenario de prueba
        Combo combo = new Combo("Combo2", 0.0);
        ProductoMenu producto1 = new ProductoMenu("Hamburguesa", 10000);
        ProductoMenu producto2 = new ProductoMenu("Papas", 5000);
        combo.agregarProductoACombo(producto1);
        combo.agregarProductoACombo(producto2);

        // Verificación
        assertEquals(15000, combo.getPrecio());
    }

    @Test
    public void testGetPrecioConDescuento() {
        // Escenario de prueba
        Combo combo = new Combo("Combo3", 0.2);
        ProductoMenu producto1 = new ProductoMenu("Hamburguesa", 10000);
        ProductoMenu producto2 = new ProductoMenu("Papas", 5000);
        combo.agregarProductoACombo(producto1);
        combo.agregarProductoACombo(producto2);

        // Verificación
        assertEquals(12000, combo.getPrecio());
    }

    @Test
    public void testGenerarTextoFactura() {
        // Escenario de prueba
        Combo combo = new Combo("Combo4", 0.1);
        ProductoMenu producto1 = new ProductoMenu("Hamburguesa", 10000);
        ProductoMenu producto2 = new ProductoMenu("Papas", 5000);
        combo.agregarProductoACombo(producto1);
        combo.agregarProductoACombo(producto2);

        // Verificación
        assertEquals("\n*Combo4: \t$13500\nHamburguesa\n+Queso\t$5000\n-Pepinillos\nPapas\n", combo.generarTextoFactura());
    }

}
