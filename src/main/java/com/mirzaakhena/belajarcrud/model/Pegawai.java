package com.mirzaakhena.belajarcrud.model;

import java.util.Date;

/**
 * 
 * @author mirzaakhena
 *
 */
public class Pegawai {

	private int nip;
	private String nama;
	private double gaji;
	private Date tanggal;

	public int getNip() {
		return nip;
	}

	public void setNip(int nip) {
		this.nip = nip;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public double getGaji() {
		return gaji;
	}

	public void setGaji(double gaji) {
		this.gaji = gaji;
	}

	public Date getTanggal() {
		return tanggal;
	}

	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}

}
