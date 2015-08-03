package com.mirzaakhena.belajarcrud;

import java.awt.EventQueue;

import com.mirzaakhena.belajarcrud.ui.ScreenData;

/**
 * 
 * @author mirzaakhena
 *
 */
public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScreenData frame = new ScreenData();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
