/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.ReturPembelian;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author ayu
 */
public interface ReturPembelianDao extends Remote{
    boolean insertReturn(ReturPembelian kembali) throws RemoteException; 
    List<ReturPembelian>getReturPembelian() throws RemoteException;
}
