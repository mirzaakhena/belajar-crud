package com.mirzaakhena.belajarcrud.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import com.mirzaakhena.belajarcrud.dao.PegawaiDao;
import com.mirzaakhena.belajarcrud.model.Pegawai;

/**
 * 
 * @author mirzaakhena
 *
 */
public class ScreenInput extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4050214842299115703L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNip;
	private JTextField textFieldNama;
	private JTextField textFieldGaji;
	private JTextField textFieldTanggal;

	private boolean isUpdate;

	private PegawaiDao pegawaiDao;

	public void setIsUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
		if (isUpdate) {
			setTitle("Update Data Lama");
			textFieldNip.setEnabled(false);
		} else {
			setTitle("Buat Data Baru");
		}
	}

	/**
	 * Create the dialog.
	 */
	public ScreenInput() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(300, 250);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][][]"));
		{
			JLabel lblNip = new JLabel("NIP");
			contentPanel.add(lblNip, "cell 0 0,alignx trailing");
		}
		{
			textFieldNip = new JTextField();
			contentPanel.add(textFieldNip, "cell 1 0");
			textFieldNip.setColumns(10);
		}
		{
			JLabel lblNama = new JLabel("Nama");
			contentPanel.add(lblNama, "cell 0 1,alignx trailing");
		}
		{
			textFieldNama = new JTextField();
			contentPanel.add(textFieldNama, "cell 1 1,growx");
			textFieldNama.setColumns(10);
		}
		{
			JLabel lblGaji = new JLabel("Gaji");
			contentPanel.add(lblGaji, "cell 0 2,alignx trailing");
		}
		{
			textFieldGaji = new JTextField();
			contentPanel.add(textFieldGaji, "cell 1 2,growx");
			textFieldGaji.setColumns(10);
		}
		{
			JLabel lblTanggal = new JLabel("Tanggal");
			contentPanel.add(lblTanggal, "cell 0 3,alignx trailing");
		}
		{
			textFieldTanggal = new JTextField();
			contentPanel.add(textFieldTanggal, "cell 1 3,growx");
			textFieldTanggal.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						saveData();
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		pegawaiDao = PegawaiDao.getPegawaiDao();
	}

	private void saveData() {

		Pegawai pegawai = new Pegawai();
		pegawai.setNama(textFieldNama.getText());
		pegawai.setGaji(Double.parseDouble(textFieldGaji.getText()));		
		pegawai.setTanggal(new Date());	
		pegawai.setNip(Integer.parseInt(textFieldNip.getText()));
		
		if (isUpdate) {		
			pegawaiDao.update(pegawai);			
		} else {			
			pegawaiDao.create(pegawai);
		}

	}
	
	public void setData(Pegawai pegawai) {
		textFieldGaji.setText(String.valueOf(pegawai.getGaji()));
		textFieldNama.setText(String.valueOf(pegawai.getNama()));
		textFieldNip.setText(String.valueOf(pegawai.getNip()));
		textFieldTanggal.setText(String.valueOf(pegawai.getTanggal()));		
	}

}
