package com.mirzaakhena.belajarcrud.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mirzaakhena.belajarcrud.model.Pegawai;

/**
 * 
 * @author mirzaakhena
 *
 */
public class PegawaiDao {

	private static final String CREATE = //
	"INSERT INTO Pegawai(nip, nama, gaji, tanggal) VALUES(?, ?, ?, ?)";

	private static final String UPDATE = //
	"UPDATE Pegawai SET nama=?, gaji=?, tanggal=? WHERE nip = ?";

	private static final String DELETE = //
	"DELETE FROM Pegawai WHERE nip = ?";

	private static final String GET_ONE = //
	"SELECT * FROM Pegawai WHERE nip = ?";

	private static final String GET_ALL = //
	"SELECT * FROM Pegawai";

	private static final String url = "jdbc:mysql://localhost:3306/pegawaidb2";
	private static final String username = "root";
	private static final String password = "";

	private PreparedStatement createPegawai;
	private PreparedStatement updatePegawai;
	private PreparedStatement deletePegawai;
	private PreparedStatement getOnePegawai;
	private PreparedStatement getAllPegawai;

	private Connection connection;

	private PegawaiDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
			createPegawai = connection.prepareStatement(CREATE);
			updatePegawai = connection.prepareStatement(UPDATE);
			deletePegawai = connection.prepareStatement(DELETE);
			getOnePegawai = connection.prepareStatement(GET_ONE);
			getAllPegawai = connection.prepareStatement(GET_ALL);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static PegawaiDao pegawaiDao = new PegawaiDao();

	public static PegawaiDao getPegawaiDao() {
		return pegawaiDao;
	}

	public void create(Pegawai pegawai) {

		try {
			createPegawai.setInt(1, pegawai.getNip());
			createPegawai.setString(2, pegawai.getNama());
			createPegawai.setDouble(3, pegawai.getGaji());
			createPegawai.setTimestamp(4, new Timestamp(pegawai.getTanggal().getTime()));
			createPegawai.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void update(Pegawai pegawai) {

		try {

			updatePegawai.setString(1, pegawai.getNama());
			updatePegawai.setDouble(2, pegawai.getGaji());
			updatePegawai.setTimestamp(3, new Timestamp(pegawai.getTanggal().getTime()));
			updatePegawai.setInt(4, pegawai.getNip());
			updatePegawai.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void delete(Pegawai pegawai) {
		try {
			deletePegawai.setInt(1, pegawai.getNip());
			deletePegawai.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Pegawai getOne(int nip) {
		try {
			getOnePegawai.setInt(1, nip);
			ResultSet rs = getOnePegawai.executeQuery();
			if (rs.next()) {
				Pegawai pegawai = new Pegawai();
				pegawai.setNip(rs.getInt(1));
				pegawai.setNama(rs.getString(2));
				pegawai.setGaji(rs.getFloat(3));
				pegawai.setTanggal(new Date(rs.getTimestamp(4).getTime()));
				return pegawai;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Pegawai> getAll() {

		try {
			ResultSet rs = getAllPegawai.executeQuery();
			List<Pegawai> listPegawai = new ArrayList<>();
			while (rs.next()) {
				Pegawai pegawai = new Pegawai();
				pegawai.setNip(rs.getInt(1));
				pegawai.setNama(rs.getString(2));
				pegawai.setGaji(rs.getFloat(3));
				pegawai.setTanggal(new Date(rs.getTimestamp(4).getTime()));
				listPegawai.add(pegawai);
			}
			return listPegawai;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
