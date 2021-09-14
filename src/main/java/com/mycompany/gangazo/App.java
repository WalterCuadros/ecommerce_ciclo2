/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gangazo;

import com.mycompany.controladores.ControladorApp;
import com.mycompany.dao.DaoCarrito;
import com.mycompany.dao.DaoProducto;
import com.mycompany.dao.DaoCliente;
import com.mycompany.dao.DaoCompra;
import com.mycompany.dao.DaoProductoCarrito;
import com.mycompany.dao.IDaoCarrito;
import com.mycompany.dao.IDaoCliente;
import com.mycompany.dao.IDaoCompra;
import com.mycompany.dao.IDaoProducto;
import com.mycompany.dao.IDaoProductoCarrito;
import com.mycompany.modelos.Carrito;
import com.mycompany.modelos.Cliente;
import com.mycompany.modelos.Compra;
import com.mycompany.modelos.Producto;
import com.mycompany.modelos.ProductoCarrito;
import com.mycompany.vistas.JFrameApp;
import com.mycompany.vistas.JFrameCompra;
import com.mycompany.vistas.JFrameInicio;
import com.mycompany.vistas.JFrameRegistrarCliente;



public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Carrito carrito = new Carrito();
        Cliente cliente = new Cliente();
        Compra compra = new Compra();
        Producto producto = new Producto();
        ProductoCarrito productoCarrito = new ProductoCarrito();
        IDaoCarrito IDaocarrito = new DaoCarrito();
        IDaoCliente IDaocliente = new DaoCliente();
        IDaoCompra IDaocompra = new DaoCompra();
        IDaoProducto IDaoProducto = new DaoProducto();
        IDaoProductoCarrito IDaoProductoCarrito = new DaoProductoCarrito();
 
        JFrameApp jFrame = new JFrameApp();
        JFrameInicio jFrameInicio = new JFrameInicio();
        JFrameRegistrarCliente  jFrameRegistrarCliente = new JFrameRegistrarCliente(); 
        JFrameCompra jFrameCompra = new JFrameCompra();

        ControladorApp controladorApp = new ControladorApp(carrito, cliente,compra, producto, 
            productoCarrito, IDaocarrito, IDaocliente,
            IDaocompra, IDaoProducto , IDaoProductoCarrito, 
           jFrame, jFrameInicio, jFrameRegistrarCliente, jFrameCompra);
        controladorApp.iniciar();
        jFrameInicio.setVisible(true);
    }
    
}
