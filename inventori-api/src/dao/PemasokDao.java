/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Pemasok;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author ayu
 */
public interface PemasokDao extends Remote{
 boolean insert (Pemasok pemasok)throws RemoteException;
    boolean update (Pemasok pemasok)throws RemoteException;
    boolean delete (Pemasok pemasok)throws RemoteException;
    Pemasok getPemasok (String id)throws RemoteException;
    List<Pemasok> getPemasok()throws RemoteException;   
}
