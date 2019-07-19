/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Gudang;
import entity.Manajemen;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author ayu
 */
public interface ManajementDao extends Remote{
  boolean insert (Manajemen manajemen) throws RemoteException;
  boolean update (Manajemen manajemen) throws RemoteException;
  boolean delete (Manajemen manajemen) throws RemoteException;
  Manajemen getById(String Id)throws RemoteException;
  List<Manajemen> getManajemen()throws RemoteException;
  Manajemen getRofSafety(String barangId)throws RemoteException;
}
