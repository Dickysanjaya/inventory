/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Kategori;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author ayu
 */
public interface KategoriDao extends Remote{
    boolean insert(Kategori kategori)throws RemoteException;
    boolean update(Kategori kategori)throws RemoteException;
    boolean delete(Kategori kategori)throws RemoteException;
    Kategori getKategori(String id)throws RemoteException;
    List<Kategori>getKategori() throws RemoteException;
    
}
