/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Pelanggan;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author ayu
 */
    public interface PelangganDao extends Remote{
        boolean insert (Pelanggan pelanggan)throws RemoteException;
        boolean update (Pelanggan pelanggan)throws RemoteException;
        boolean delete (Pelanggan pelanggan)throws RemoteException;
        Pelanggan getPelanggan (String id)throws RemoteException;
        List<Pelanggan> getPelanggan()throws RemoteException;    
}
