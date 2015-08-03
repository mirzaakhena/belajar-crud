package com.mirzaakhena.belajarcrud.ui;

import com.mirzaakhena.belajarcrud.model.Pegawai;
import com.mirzaakhena.ui.basetablemodel.BaseTableModel;

/**
 * 
 * @author mirzaakhena
 *
 */
public class PegawaiTableModel extends BaseTableModel<Pegawai> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8377399835597522967L;

	public PegawaiTableModel() {
		super("NIP","Nama","Gaji","Tanggal");
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Pegawai pegawai = getItem(rowIndex);
		switch (columnIndex) {
		case 0:
			return pegawai.getNip();
		case 1:
			return pegawai.getNama();
		case 2:
			return pegawai.getGaji();
		case 3:
			return pegawai.getTanggal();
		}
		
		return null;
	}

}
