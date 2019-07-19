/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori.client.TablelModel;

import com.stripbandunk.jwidget.model.DynamicTableModel;
import entity.Barang;
import entity.PembelianDetail;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asep
 */
public class TabelModelPembelian extends DynamicTableModel<PembelianDetail>{
    
    List< PembelianDetail> list=new ArrayList<>();

    public TabelModelPembelian(Class<PembelianDetail> clazz) {
        super(clazz);
    }

    public TabelModelPembelian(List<PembelianDetail> data, Class<PembelianDetail> clazz) {
        super(data, clazz);
        this.list=data;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Double jumlah=list.get(rowIndex).getJumlah();
        if(aValue!=null&&aValue instanceof Double&&columnIndex==2){
            jumlah=(Double) aValue;
            list.get(rowIndex).setJumlah(jumlah);
        }
        list.get(rowIndex).setHarga_beli_total(list.get(rowIndex).getHarga_beli()*jumlah);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 0: return String.class;
            case 1: return Barang.class;
            case 2: return Double.class;
                default:return Object.class;
        }
    }
    
}
