/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.modelos.Producto;
import com.mycompany.modelos.ProductoVista;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author cuadr
 */
public interface IDaoProducto {
    
    public ArrayList<ProductoVista>  traerProductos(int tipoProducto, boolean productosDescuento);
    public Producto getProducto(String codigoBarras);
    
}
