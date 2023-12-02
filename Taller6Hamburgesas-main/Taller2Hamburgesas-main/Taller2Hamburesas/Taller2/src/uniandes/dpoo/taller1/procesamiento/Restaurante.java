package uniandes.dpoo.taller1.procesamiento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import uniandes.dpoo.taller1.consola.IngredienteRepetidoException;
import uniandes.dpoo.taller1.consola.ProductoRepetidoException;
import uniandes.dpoo.taller1.modelo.Combo;
import uniandes.dpoo.taller1.modelo.Ingrediente;
import uniandes.dpoo.taller1.modelo.Pedido;
import uniandes.dpoo.taller1.modelo.Producto;
import uniandes.dpoo.taller1.modelo.ProductoMenu;

public class Restaurante 
{
	private ArrayList<Producto> MenuBase;
	private ArrayList<Ingrediente> Ingredientes;
	private HashMap<Integer, Pedido> Pedidos;
	private ArrayList<Combo> Combos;
	private Pedido PedidoEnCurso;
	
	public Restaurante()
	{
		MenuBase = new ArrayList<Producto>();
		Ingredientes = new ArrayList<Ingrediente>();
		Pedidos = new HashMap<Integer, Pedido>();
		Combos = new ArrayList<Combo>();
	}
	
	public int iniciarPedido(String nombreCliente, String direccionCliente)
	{
		Pedido PedidoActual = new Pedido(nombreCliente, direccionCliente);
		
		PedidoEnCurso = PedidoActual;
		
		PedidoEnCurso.setNumeroPedidos();
		
		Pedidos.put(PedidoEnCurso.getIdPedido(), PedidoEnCurso);
		
		return PedidoEnCurso.getIdPedido();
	}
	
	public void cerrarYGuardarPedido()
	{
		String Path="./Taller2Hamburesas/Taller2/facturas/Pedido-" + PedidoEnCurso.getIdPedido() + ".txt";
		File Factura =new File(Path);
		PedidoEnCurso.guardarFactura(Factura);
		System.out.println("FACTURA GENERADA - REVISE EL ARCHIVO GENERADO EN LA CARPETA FACTURAS");
		for(int i = 0; i<PedidoEnCurso.getItemsPedido().size(); i++)
		{
			System.out.println("Productos: ");
			System.out.println("*"+ PedidoEnCurso.getItemsPedido().get(i).getNombre()+ '\n');
		}
		Pedido.setNumeroPedidos();
		System.out.println("PEDIDO GUARDADO");
	}
	
	public Pedido getPedidoEnCurso()
	{
		return PedidoEnCurso;
	}
	
	public ArrayList<Producto> getMenuBase()
	{
		return MenuBase;
	}
	
	public ArrayList<Ingrediente> getIngredientes()
	{
		return Ingredientes;
		
	}
	
	public ArrayList<Combo> getCombos()
	{
		return Combos;
	}
	
	public HashMap<Integer, Pedido> getPedidos() 
	{
		return Pedidos;
	}
	
	public void cargarInformacionRestaurante(File archivoMenu, File archivoIngredientes, File archivoCombos) throws ProductoRepetidoException, IngredienteRepetidoException
	{
		cargarMenu(archivoMenu);
		
		cargarIngredientes(archivoIngredientes);
		
		cargarCombos(archivoCombos);
	}
	
	private void cargarMenu(File archivoMenu) throws ProductoRepetidoException {
	    FileReader fr = null;
	    BufferedReader br = null;
	    try {
	        fr = new FileReader(archivoMenu);
	        br = new BufferedReader(fr);

	        String linea;
	        linea = br.readLine();
	        while (linea != null) {
	            String[] partes = linea.split(";");
	            String nombreProducto = partes[0];
	            int costo = Integer.parseInt(partes[1]);
	            Producto producto = new ProductoMenu(nombreProducto, costo);

	            // Verificar si el producto ya existe en el menú
	            if (MenuBase.contains(producto)) {
	                throw new ProductoRepetidoException("Producto repetido: " + nombreProducto);
	            }

	            MenuBase.add(producto);
	            linea = br.readLine();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (fr != null) {
	                fr.close();
	            }
	        } catch (Exception e2) {
	            e2.printStackTrace();
	        }
	    }
	}
	
	private void cargarIngredientes(File archivoIngredientes) throws IngredienteRepetidoException {
	    FileReader fr = null;
	    BufferedReader br = null;
	    try {
	        fr = new FileReader(archivoIngredientes);
	        br = new BufferedReader(fr);

	        String linea;
	        linea = br.readLine();
	        while (linea != null) {
	            String[] partes = linea.split(";");
	            String nombreIngrediente = partes[0];
	            int costo = Integer.parseInt(partes[1]);
	            Ingrediente ingrediente = new Ingrediente(nombreIngrediente, costo);

	            // Verificar si el ingrediente ya existe en la lista de ingredientes
	            if (Ingredientes.contains(ingrediente)) {
	                throw new IngredienteRepetidoException("Ingrediente repetido: " + nombreIngrediente);
	            }

	            Ingredientes.add(ingrediente);
	            linea = br.readLine();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (fr != null) {
	                fr.close();
	            }
	        } catch (Exception e2) {
	            e2.printStackTrace();
	        }
	    }
	}
	
	public Producto buscarProducto(String nombre)
	{	
		boolean Encontrado = false;
		int i=0;
		Producto productoBuscado = null;
		while (!Encontrado && i < MenuBase.size() )
		{	
			productoBuscado = MenuBase.get(i);
			if (productoBuscado.getNombre().equals(nombre))
			{
				Encontrado = true;
				
			}
			i ++;
		}
		return productoBuscado;
	}
	
	private void cargarCombos(File archivoCombos)
	{

	    FileReader fr = null;
	    BufferedReader br = null;
	    try 
	    {
	    	//Se abre el fichero y se crea el BufferReader para leer el archivo utilizando el m�todo readline()
        fr = new FileReader (archivoCombos);
        br = new BufferedReader(fr);

        // Se lee el fichero:
        String linea;
        linea = br.readLine();
        while(linea!=null)
        {
            String[] partes = linea.split(";");
            String nombreCombo = partes[0];
            double descuento = Integer.parseInt(partes[1].substring(0, partes[1].length()-1));
            Combo Combo = new Combo(nombreCombo, descuento/100);
            for(int i=2;i<partes.length;i++)
			{	
				String nombre=partes[i];
				Combo.agregarProductoACombo(buscarProducto(nombre));
			}
            Combos.add(Combo);
            linea = br.readLine();
        }
	    }
	    catch(Exception e)
	    {
         e.printStackTrace();
	    }
	    finally
	    {
	    	// Para asegurarnos que se cierra el fichero, en el finally lo cerramos por si funciona bien o salta una excepcion..
         try
         {                    
            if( null != fr )
            {   
               fr.close();     
            }                  
         }
         catch (Exception e2)
         { 
            e2.printStackTrace();
         }
	     }
	}
}
