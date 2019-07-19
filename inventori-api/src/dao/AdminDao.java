/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Admin;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author ayu
 */
public interface AdminDao extends Remote{
    boolean insert (Admin admin)throws RemoteException;
    boolean update (Admin admin)throws RemoteException;
    boolean delete (Admin admin)throws RemoteException;
    Admin getAdmin (String id)throws RemoteException;
    Admin getAdminLogin(Admin admin) throws RemoteException;
    List<Admin> getAdmin()throws RemoteException;
    
}
