package com.mirzaakhena.belajarcrud.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.mirzaakhena.belajarcrud.dao.PegawaiDao;
import com.mirzaakhena.belajarcrud.model.Pegawai;

import net.miginfocom.swing.MigLayout;

/**
 * 
 * @author mirzaakhena
 *
 */
public class ScreenData extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4467459457988748121L;

	private JPanel contentPane;

	private JTable table;

	private PegawaiDao pegawaiDao;

	private PegawaiTableModel pegawaiTableModel;

	public ScreenData() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][grow]", "[][grow]"));

		JButton btnTambah = new JButton("Tambah");
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenInput dialog = new ScreenInput();
				dialog.setIsUpdate(false);
				dialog.setVisible(true);
				pegawaiTableModel.setItem(pegawaiDao.getAll());
			}
		});
		contentPane.add(btnTambah, "cell 0 0");

		JButton btnUbah = new JButton("Ubah");
		btnUbah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenInput dialog = new ScreenInput();
				dialog.setIsUpdate(true);

				int row = table.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Silahkan pilih dulu");

				} else {
					Pegawai pegawai = pegawaiTableModel.getItem(row);
					dialog.setData(pegawai);
					dialog.setVisible(true);
					pegawaiTableModel.setItem(pegawaiDao.getAll());
				}

			}
		});
		contentPane.add(btnUbah, "cell 1 0");

		JButton btnHapus = new JButton("Hapus");
		btnHapus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int row = table.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Silahkan pilih dulu");

				} else {

					int result = JOptionPane.showConfirmDialog(null, "Yakin hapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (result == JOptionPane.YES_OPTION) {
						Pegawai pegawai = pegawaiTableModel.getItem(row);
						pegawaiDao.delete(pegawai);
						pegawaiTableModel.setItem(pegawaiDao.getAll());
					}

				}

			}
		});
		contentPane.add(btnHapus, "cell 2 0");

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 1 4 1,grow");

		table = new JTable();
		scrollPane.setViewportView(table);

		pegawaiDao = PegawaiDao.getPegawaiDao();
		pegawaiTableModel = new PegawaiTableModel();

		table.setModel(pegawaiTableModel);
		pegawaiTableModel.setItem(pegawaiDao.getAll());

	}
}
