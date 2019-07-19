/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori.client.TablelModel;

import entity.Admin;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ayu
 */
public class TabelModelAdmin extends AbstractTableModel{
    
 List<Admin>list = new ArrayList<>();
 
    public TabelModelAdmin(List list1) {
        this.list = list;
    }

    public TabelModelAdmin() {
    }
    
    public void insert(Admin admin){
        list.add(admin);
        fireTableDataChanged();
    }
    
    public void update (Admin admin){
        list.add(admin);
        fireTableDataChanged();
    }
    
    public void delete (Admin admin){
        list.add(admin);
        fireTableDataChanged();
    }
    
    
    public void setList(List<Admin> list) {
        this.list = list;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
       return  list.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }
    
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0: return list.get(rowIndex).getKd_user();
            case 1: return list.get(rowIndex).getNm_user();
            case 2: return list.get(rowIndex).getUser_name();
            case 3: return list.get(rowIndex).getUser_pass();
            case 4: return list.get(rowIndex).getBagian();
            case 5: return list.get(rowIndex).getTelepon();
            case 6: return list.get(rowIndex).getAlamat();
                default: return null;
        }
    }
    
     public Admin getAdmin(int index){
        return list.get(index);
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
           case 0: return "Kode User";
           case 1: return "Nama User";
           case 2: return "User Name";
           case 3: return "Password User";
           case 4: return "Bagian";
           case 5: return "Telepon";
           case 6: return "Alamat";
               default: return  null;
       }
    }
     public void insertAdmin(Admin admin){
        this.list.add(admin);
        fireTableDataChanged();
    }
          
    public void updateAdmin(int index, Admin admin){
        this.list.set(index, admin);
                fireTableDataChanged();
    }
    public void deleteAdmin(int index){
        this.list.remove(index);
        fireTableDataChanged();
    }
    public void setData(List<Admin> list){
        this.list=list;
        fireTableDataChanged();
    }
    public Admin getKaryawan(int index){
        return list.get(index);
    }
    public void clear(){
        list.clear();
    }
     
     
}
