/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.modelos.Cliente;

/**
 *
 * @author cuadr
 */
public interface IDaoCliente {
    public boolean agregarCliente(Cliente cliente);
    public Cliente getCliente(String cedula);
}
