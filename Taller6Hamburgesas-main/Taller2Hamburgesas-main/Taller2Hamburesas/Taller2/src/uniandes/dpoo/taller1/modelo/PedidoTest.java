package uniandes.dpoo.taller1.modelo;

import org.junit.Test;

import uniandes.dpoo.taller1.consola.ExcesoPedioException;

import static org.junit.Assert.*;

public class PedidoTest {

    @Test
    public void testAgregarProducto() throws ExcesoPedioException {
        // Escenario de prueba
        Pedido pedido = new Pedido("Cliente1", "Dirección1");
        ProductoMenu producto1 = new ProductoMenu("Hamburguesa", 10000);
        ProductoMenu producto2 = new ProductoMenu("Papas", 5000);

        // Acción
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);

        // Verificación
        assertEquals(2, pedido.getItemsPedido().size());
    }

    @Test(expected = ExcesoPedioException.class)
    public void testAgregarProductoConExceso() throws ExcesoPedioException {
        // Escenario de prueba
        Pedido pedido = new Pedido("Cliente2", "Dirección2");
        ProductoMenu producto = new ProductoMenu("Hamburguesa Deluxe", 120000);

        // Acción
        pedido.agregarProducto(producto);

        // Verificación (se espera que lance ExcesoPedioException)
    }

    @Test
    public void testGenerarTextoFactura() throws ExcesoPedioException {
        // Escenario de prueba
        Pedido pedido = new Pedido("Cliente3", "Dirección3");
        ProductoMenu producto1 = new ProductoMenu("Hamburguesa", 10000);
        ProductoMenu producto2 = new ProductoMenu("Papas", 5000);
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);

        // Acción
        String textoFactura = pedido.generarTextoFactura();

        // Verificación
        assertTrue(textoFactura.contains("Cliente: Cliente3"));
        assertTrue(textoFactura.contains("Direccion Cliente: Dirección3"));
        assertTrue(textoFactura.contains("Hamburguesa"));
        assertTrue(textoFactura.contains("Papas"));
    }

}