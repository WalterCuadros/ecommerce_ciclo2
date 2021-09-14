/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controladores;

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
import com.mycompany.modelos.ProductoCarritoVista;
import com.mycompany.modelos.ProductoVista;
import com.mycompany.vistas.JFrameApp;
import com.mycompany.vistas.JFrameCompra;
import com.mycompany.vistas.JFrameInicio;
import com.mycompany.vistas.JFrameRegistrarCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class ControladorApp implements ActionListener {
    private Carrito carrito;
    private Cliente cliente;
    private Compra compra;
    private Producto producto;
    private ProductoCarrito productoCarrito;
    private IDaoCarrito IDaocarrito;
    private IDaoCliente IDaocliente;
    private IDaoCompra IDaocompra;
    private IDaoProductoCarrito IDaoproductoCarrito;
    private IDaoProducto IDaoproducto;
    private JFrameApp jFrame;
    private JFrameInicio jFrameInicio;
    private JFrameRegistrarCliente jFrameRegistrarCliente;
    private JFrameCompra jFrameCompra;
    private DefaultTableModel tableModelProductos;
    private DefaultTableModel tableModelCarrito;
    private DefaultTableModel tableModelCompra;
    private String cedula;
    public ControladorApp(Carrito carrito, Cliente cliente, Compra compra, Producto producto, 
            ProductoCarrito productoCarrito, IDaoCarrito IDaocarrito, IDaoCliente IDaocliente,
            IDaoCompra IDaocompra,IDaoProducto IDaoProducto ,IDaoProductoCarrito IDaoProductoCarrito, JFrameApp jFrame, JFrameInicio jFrameInicio, JFrameRegistrarCliente jFrameRegistrarCliente, JFrameCompra jFrameCompra) {
        this.carrito = carrito;
        this.cliente = cliente;
        this.compra = compra;
        this.producto = producto;
        this.productoCarrito = productoCarrito;
        this.IDaocarrito = IDaocarrito;
        this.IDaocliente = IDaocliente;
        this.IDaocompra = IDaocompra;
        this.IDaoproducto = IDaoProducto;
        this.IDaoproductoCarrito = IDaoProductoCarrito;
        this.jFrame = jFrame;
        this.jFrameInicio = jFrameInicio;
        this.jFrameRegistrarCliente = jFrameRegistrarCliente;
        this.jFrameCompra = jFrameCompra;
        this.jFrameInicio.bBuscarCliente.addActionListener(this);
        this.jFrameRegistrarCliente.bRegistrarCliente.addActionListener(this);
        this.jFrame.bTraerProdutosComestibles.addActionListener(this);
        this.jFrame.bTraerProdutosLiquidos.addActionListener(this);
        this.jFrame.bTraerProdutosAseo.addActionListener(this);
        this.jFrame.bTraerOfertasComestibles.addActionListener(this);
        this.jFrame.bTraerOfertasLiquidos.addActionListener(this);
        this.jFrame.bTraerOfertasAseo.addActionListener(this);
        this.jFrame.bAgregarACarrito.addActionListener(this);
        this.jFrame.bTraerCarritoComestibles.addActionListener(this);
        this.jFrame.bTraerCarritoLiquidos.addActionListener(this);
        this.jFrame.bTraerCarritoAseo.addActionListener(this);
        this.jFrame.bSacarProducto.addActionListener(this);
        this.jFrame.bPasarACompra.addActionListener(this);
        this.jFrameCompra.bComprar.addActionListener(this);
        this.jFrameCompra.bVolver.addActionListener(this);
        this.tableModelProductos = new DefaultTableModel();
        this.tableModelCarrito =  new DefaultTableModel();
        this.tableModelCompra =  new DefaultTableModel();
    }
    
    public void iniciar(){
        inicializarTablaProductos();
        inicializarTablaCarrito();
        inicializarTablaCompra();
    }
     
    
    public void inicializarTablaProductos(){
        tableModelProductos.addColumn("C Barras");
        tableModelProductos.addColumn("Producto");
        tableModelProductos.addColumn("Marca");
        tableModelProductos.addColumn("Tipo producto");
        tableModelProductos.addColumn("F. vencimiento");
        tableModelProductos.addColumn("Precio");
        tableModelProductos.addColumn("Descuento");
        jFrame.tProductos.setModel(tableModelProductos);
    }
    public void inicializarTablaCarrito(){
        tableModelCarrito.addColumn("Identificador");
        tableModelCarrito.addColumn("Producto");
        tableModelCarrito.addColumn("Precio");
        tableModelCarrito.addColumn("Descuento");
        jFrame.tCarrito.setModel(tableModelCarrito);
    }
    
    public void inicializarTablaCompra(){
        tableModelCompra.addColumn("Producto");
        tableModelCompra.addColumn("Precio Unitario");
        tableModelCompra.addColumn("Valor IVA");
        tableModelCompra.addColumn("Valor descuento");
        tableModelCompra.addColumn("Precio a pagar");
        tableModelCompra.addColumn("Valor Obsequio compra");
        jFrameCompra.tComprar.setModel(tableModelCompra);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jFrameInicio.bBuscarCliente) {
               bienvenidaCliente();
        }
        if (e.getSource() == this.jFrameRegistrarCliente.bRegistrarCliente) {
               crearCliente(this.cedula);
               
        }
        if (e.getSource() == this.jFrame.bTraerProdutosComestibles) {
            verProductos(IDaoproducto.traerProductos(1, false));
        }
        if (e.getSource() == this.jFrame.bTraerProdutosLiquidos) {
            verProductos(IDaoproducto.traerProductos(2, false));
        }
        if (e.getSource() == this.jFrame.bTraerProdutosAseo) {
            verProductos(IDaoproducto.traerProductos(3, false));
        }
        if (e.getSource() == this.jFrame.bTraerOfertasComestibles) {
            verProductos(IDaoproducto.traerProductos(1, true));
        }
        if (e.getSource() == this.jFrame.bTraerOfertasLiquidos) {
            verProductos(IDaoproducto.traerProductos(2, true));
        }
        if (e.getSource() == this.jFrame.bTraerOfertasAseo) {
            verProductos(IDaoproducto.traerProductos(3, true));
        }
        if (e.getSource() == this.jFrame.bAgregarACarrito) {
           agregarProductoCarrito();
        }
        if (e.getSource() == this.jFrame.bTraerCarritoComestibles) {
            limpiarTableCarrito();
            ArrayList<ProductoCarritoVista> listaProductoCarritoVistaComestibles = IDaoproductoCarrito.visualizarProductoCarrito(this.cliente.getCedula(), 1, true,true);
            int sizeComestibles = listaProductoCarritoVistaComestibles.size();
            if( sizeComestibles >0 ){
                verProductosCarrito(listaProductoCarritoVistaComestibles);
                this.jFrame.cantidadProductoLabel.setText(String.valueOf("Cantidad comestibles:"));
                this.jFrame.tCantidadCarrito.setText(String.valueOf(sizeComestibles));
                
            }else{
                this.jFrame.tCantidadCarrito.setText(null);
                JOptionPane.showMessageDialog(null,"No hay productos comestibles en el carrito");
            }
            
            verCantidadProductosCarrito();
        }
        if (e.getSource() == this.jFrame.bTraerCarritoLiquidos) {
            limpiarTableCarrito();
            ArrayList<ProductoCarritoVista> listaProductoCarritoVistaLiquidos = IDaoproductoCarrito.visualizarProductoCarrito(this.cliente.getCedula(), 2, true,true);
            int sizeLiquidos = listaProductoCarritoVistaLiquidos.size();
            if( sizeLiquidos >0 ){
           
                verProductosCarrito(listaProductoCarritoVistaLiquidos);
                this.jFrame.cantidadProductoLabel.setText(String.valueOf("Cantidad liquidos:"));
                this.jFrame.tCantidadCarrito.setText(String.valueOf(sizeLiquidos));
            }else{
                this.jFrame.tCantidadCarrito.setText(null);
                JOptionPane.showMessageDialog(null,"No hay productos liquidos en el carrito");
            }
            verCantidadProductosCarrito();
        }
        if (e.getSource() == this.jFrame.bTraerCarritoAseo) {
            
            limpiarTableCarrito();
            ArrayList<ProductoCarritoVista> listaProductoCarritoVistaAseo = IDaoproductoCarrito.visualizarProductoCarrito(this.cliente.getCedula(), 3, true,true);
            int sizeAseo = listaProductoCarritoVistaAseo.size();
            if( sizeAseo >0 ){
                verProductosCarrito(listaProductoCarritoVistaAseo);
                this.jFrame.cantidadProductoLabel.setText(String.valueOf("Cantidad aseo:"));        
                this.jFrame.tCantidadCarrito.setText(String.valueOf(sizeAseo));
            }else{
                this.jFrame.tCantidadCarrito.setText(null);
                JOptionPane.showMessageDialog(null,"No hay productos de aseo en el carrito");
            }
            verCantidadProductosCarrito();
        }
        if (e.getSource() == this.jFrame.bSacarProducto) {
           sacarProductoCarrito();
        }
        if (e.getSource() == this.jFrame.bPasarACompra) {
           
            limpiarTableCarrito();
            limpiarTableProductos();
            limpiarTextoJFrame();
            verDetalleCompra();
            this.jFrameCompra.setVisible(true);
            this.jFrame.setVisible(false);
        }
        if (e.getSource() == this.jFrameCompra.bComprar) {
           comprar();
        }
        
        if (e.getSource() == this.jFrameCompra.bVolver) {
           limpiarTableCompra();
           this.jFrameCompra.setVisible(false);
           this.jFrame.setVisible(true);
        }
       
    }
    
    private void bienvenidaCliente(){
        this.cedula=jFrameInicio.tCedula.getText();
 
        if(!cedula.equals("")){
            this.cliente = buscarCliente(this.cedula);
            if(this.cliente == null){
                System.out.println(this.cedula);
                this.jFrameInicio.setVisible(false);
                limpiarTextoFrameInicio();
                this.jFrameRegistrarCliente.setVisible(true);
                
            }else{
                this.jFrameInicio.setVisible(false);
                limpiarTextoFrameInicio();
                this.jFrameRegistrarCliente.setVisible(false);
                limpiarTextoFrameRegistroCliente();
                this.jFrame.setVisible(true);
            }
       
        }else{
        JOptionPane.showMessageDialog(null, "No se permiten campos vacios");
        }
    } 
     
    private Cliente buscarCliente(String cedula){
        
        Cliente clienteBuscado = IDaocliente.getCliente(cedula); 
        return (clienteBuscado.getId() == 0)? null: clienteBuscado;
     
    }
    
    private void crearCliente(String cedula){
        
        String nombre =   jFrameRegistrarCliente.tNombreCliente.getText();
        String tarjeta = jFrameRegistrarCliente.tTarjetaCliente.getText();
        String email = jFrameRegistrarCliente.tEmail.getText();
        
        if(!nombre.equals("") && !tarjeta.equals("") && !email.equals("")){
            IDaocliente.agregarCliente(new Cliente(
            jFrameRegistrarCliente.tNombreCliente.getText(),cedula,
            jFrameRegistrarCliente.tTarjetaCliente.getText(),
            jFrameRegistrarCliente.tEmail.getText()
            ));
            this.cliente = IDaocliente.getCliente(cedula); 
           
        }
        this.jFrameRegistrarCliente.setVisible(false);
        this.jFrame.setVisible(true);
       
    }
    
    private void verCantidadProductosCarrito(){
        
            int cantidadProductosSacadosCarrito = IDaoproductoCarrito.getCantidadProductos(this.cliente.getCedula(), false);
            int cantidadProductosCarrito = IDaoproductoCarrito.getCantidadProductos(this.cliente.getCedula(), true);
           
            this.jFrame.cantidadInCarritoLabel.setText("No. de Productos en carrito:");
            this.jFrame.cantidadInCarrito.setText(String.valueOf(cantidadProductosCarrito));
            
            this.jFrame.cantidadOutCarritoLabel.setText("No. de Productos fuera carrito:");
            this.jFrame.cantidadOutCarrito.setText(String.valueOf(cantidadProductosSacadosCarrito));
            
        } 
    
    private void verProductosCarrito(ArrayList<ProductoCarritoVista> listaProductoCarritoVista){
        limpiarTableCarrito();
        for (int i = 0; i < listaProductoCarritoVista.size(); i++) {

           String datos[]=new String[4];
           datos[0]=String.valueOf(listaProductoCarritoVista.get(i).getId());
           datos[1]=listaProductoCarritoVista.get(i).getNombre();
           datos[2]=String.valueOf(listaProductoCarritoVista.get(i).getPrecio());
           datos[3]=String.valueOf(listaProductoCarritoVista.get(i).getDescuento());
      

           
            this.tableModelCarrito.addRow(datos);

            this.jFrame.tCarrito.setModel(tableModelCarrito);
        
     
           
       }
        
        
        
    }
     
    private void verProductos(ArrayList<ProductoVista> listaProductoVista){
        
        limpiarTableProductos();
        for (int i = 0; i < listaProductoVista.size(); i++) {

            String datos[]=new String[7];
            datos[0]=listaProductoVista.get(i).getCodigoBarras();
            datos[1]=listaProductoVista.get(i).getNombre();
            datos[2]=listaProductoVista.get(i).getMarca();
            datos[3]=String.valueOf(listaProductoVista.get(i).getTipoProductoNombre());
            datos[4]=String.valueOf(listaProductoVista.get(i).getFechaVencimiento());
            datos[5]=String.valueOf(listaProductoVista.get(i).getPrecio());
            datos[6]=String.valueOf(listaProductoVista.get(i).getDescuento());

            this.tableModelProductos.addRow(datos);

            this.jFrame.tProductos.setModel(tableModelProductos);
        }
    }
    
    private void agregarProductoCarrito(){
        
        String codigoBarras = this.jFrame.tCodigoB.getText();
        if(!codigoBarras.equals("")){
            this.carrito = buscarCarrito();
            if(this.carrito == null){
                this.carrito = crearCarrito();
            }

            ProductoCarrito productoCarritoGuardar = new ProductoCarrito();
            productoCarritoGuardar.setCantidadProducto(1);
            productoCarritoGuardar.setProductoId(IDaoproducto.getProducto(codigoBarras).getId());
            productoCarritoGuardar.setCarritoId(this.carrito.getId());
            productoCarritoGuardar.setInCarrito(true);
            IDaoproductoCarrito.crearProductoCarrito(productoCarritoGuardar);
            limpiarAgregarCarrito();
        }else{
            JOptionPane.showMessageDialog(null, "No se permiten campos vacios");
        }
        
            
        
    }
   
    private Carrito crearCarrito(){
        
        Carrito carritoCrear = new Carrito();
        carritoCrear.setClienteId(this.cliente.getId());
        carritoCrear.setActive(true);
        IDaocarrito.crearCarrito(carritoCrear);
        carritoCrear = IDaocarrito.getCarrito(this.cliente.getCedula(), true);
        return carritoCrear;
    }
    
    private Carrito buscarCarrito(){
        
        Carrito carritoBuscado = IDaocarrito.getCarrito(this.cliente.getCedula(), true);
        return (carritoBuscado.getId() == 0)? null: carritoBuscado;
   
    }
    
    private void sacarProductoCarrito(){
        
        String identificador = this.jFrame.tSacarCarrito.getText();
        try{
            int opcion = Integer.valueOf(identificador);
            this.IDaoproductoCarrito.actualizarProductoCarrito(productoCarrito);
            this.productoCarrito = IDaoproductoCarrito.getProductoCarrito(opcion);
            this.productoCarrito.setInCarrito(false);

            if( IDaoproductoCarrito.actualizarProductoCarrito(productoCarrito) && this.productoCarrito.getId() != 0){
                 verCantidadProductosCarrito();
                 JOptionPane.showMessageDialog(null,"Producto sacado del carrito");
            }else{
                 JOptionPane.showMessageDialog(null,"No se pudo sacar el producto del carrito\n ó producto no existe en carrito");
            }
           this.jFrame.tSacarCarrito.setText(null); 
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Debe ingresar un número obligatoriamente");
        }
       
    }
  
    private void verDetalleCompra(){
        
        this.carrito = buscarCarrito();
        if(this.carrito != null){
            
            ArrayList<ProductoCarritoVista> listaProductoCarrito = IDaoproductoCarrito.visualizarProductoCarrito(this.cliente.getCedula(),0, true, false);
            verproductosDetalleCompra(listaProductoCarrito);
            this.jFrameCompra.tNombreCliente.setText(this.cliente.getNombre());
            this.jFrameCompra.tCedula.setText(this.cliente.getCedula());
            this.jFrameCompra.tTotalPagar.setText(String.valueOf(this.compra.getValorTotal()));
            this.jFrameCompra.tValorObsequioCompra.setText(String.valueOf(this.compra.getValorObsequio()));
            
        }else{
            JOptionPane.showMessageDialog(null,"No hay carrito relacionado a este cliente");
        }
        
         
    }
    
    private void verproductosDetalleCompra(ArrayList<ProductoCarritoVista> listaProductoCarritoVista){
        
        float valorObsequioTotal = 0;
        float valorTotal = 0;
        for (int i = 0; i < listaProductoCarritoVista.size(); i++) {
            float precioUnitario =listaProductoCarritoVista.get(i).getPrecio();
            float porcentajeIVA = (listaProductoCarritoVista.get(i).getPrecio()*(float)0.19);
            float valorDescuento = listaProductoCarritoVista.get(i).getDescuento();
            float precioTotal = (precioUnitario+porcentajeIVA)- valorDescuento ;
           
            int division = (int)((double)precioUnitario/10000.0);
            double  valorObsequio = (double)division*1000.0 ;
            valorObsequioTotal = valorObsequioTotal+(float)valorObsequio;
            valorTotal = valorTotal+(float)precioTotal;


            String datos[]=new String[6];
            datos[0]=listaProductoCarritoVista.get(i).getNombre();
            datos[1]=String.valueOf(precioUnitario);
            datos[2]=String.valueOf(porcentajeIVA);
            datos[3]=String.valueOf(valorDescuento);
            datos[4]=String.valueOf(precioTotal);
            datos[5] = String.valueOf(valorObsequio);

            
            this.tableModelCompra.addRow(datos);

            this.jFrameCompra.tComprar.setModel(tableModelCompra);
        }
            
            this.compra.setValorTotal(this.compra.getValorTotal()+valorTotal);
            this.compra.setValorObsequio(this.compra.getValorObsequio()+valorObsequioTotal);
            
       
        
    }
     
    private void comprar(){
        
        this.compra.setCarritoId(this.carrito.getId());
        if(IDaocompra.agregarCompra(this.compra)){
            this.carrito.setActive(false);
            IDaocarrito.actualizarCarrito(this.carrito);
        }
        verObsequioAcumulado( );
        this.jFrameCompra.bVolver.setEnabled(false);
        this.jFrameCompra.bComprar.setEnabled(false);
    }
    
    private void verObsequioAcumulado( ){
        
        ArrayList<Compra> arrayListCompras = IDaocompra.getCompras(this.cliente.getCedula());
        float valorObsequioAcumulado = 0; 
        for (int i = 0; i < arrayListCompras.size(); i++) {
            valorObsequioAcumulado = valorObsequioAcumulado + arrayListCompras.get(i).getValorObsequio();
        }
        jFrameCompra.tValorObsequioAcumulado.setText(String.valueOf(valorObsequioAcumulado));
        
    }
    
    private void limpiarTextoFrameInicio(){
        
        jFrameInicio.tCedula.setText(null);
    }
    
    private void limpiarTextoFrameRegistroCliente(){
        
        jFrameRegistrarCliente.tNombreCliente.setText(null);
        jFrameRegistrarCliente.tTarjetaCliente.setText(null);
        jFrameRegistrarCliente.tEmail.setText(null);
    }
    
    private void limpiarTableProductos(){
        
        if(tableModelProductos.getRowCount()>0){
            for (int i = tableModelProductos.getRowCount()-1; i > -1; i--) {
                tableModelProductos.removeRow(i);
            }
        }
    }
    
    private void limpiarTableCarrito(){
        
        if(tableModelCarrito.getRowCount()>0){
            for (int i = tableModelCarrito.getRowCount()-1; i > -1; i--) {
                tableModelCarrito.removeRow(i);
            }
        }
    }
    
    private void limpiarTableCompra(){
        
        if(tableModelCompra.getRowCount()>0){
            for (int i = tableModelCompra.getRowCount()-1; i > -1; i--) {
                tableModelCompra.removeRow(i);
            }
        }
    }
    private void limpiarAgregarCarrito(){
        
        jFrame.tCodigoB.setText(null);
    }
    private void limpiarTextoJFrame(){
        
        jFrame.tCodigoB.setText(null);
        jFrame.tSacarCarrito.setText(null);
        jFrame.cantidadInCarrito.setText(null);
        jFrame.cantidadOutCarrito.setText(null);
        jFrame.cantidadOutCarrito.setText(null);
        jFrame.tCantidadCarrito.setText(null);
    }
   
   
}

   