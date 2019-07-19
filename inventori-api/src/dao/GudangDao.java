/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Gudang;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author ayu
 */
public interface GudangDao extends Remote{
    boolean insert(Gudang gudang)throws RemoteException;
    boolean update(Gudang gudang)throws RemoteException;
    boolean delete(Gudang gudang)throws RemoteException;
    Gudang getGudang(String id)throws RemoteException;
    List<Gudang>getGudang()throws RemoteException;
}
