/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Compiler;

/**
 *
 * @author Julio Ponce
 */
import com.sun.awt.AWTUtilities;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Principal {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        long inicio = System.currentTimeMillis();
            File config = new File(".config.txt");
            // Si el archivo no existe es creado
            if (!config.exists()) {
                try {
                    config.createNewFile();
                     String valor= "OBSCURO";
                      BufferedWriter BWIN = null;
                            try {
                                BWIN = new BufferedWriter(new FileWriter(config));
                            } catch (IOException ex) {
                                System.out.println("Error IN");
                            }
                            try {
                                BWIN.write(valor+"\n");
                                BWIN.append("Arial");
                                
                    } catch (IOException ex) {
                        System.out.println("NO Escrito INTER");
                        Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                    }
                            BWIN.close();
                } catch (IOException ex) {
                    System.out.println("Error: "+ex);
                        }
            }
            InputStream input = new FileInputStream(".config.txt");
            Scanner arch = new Scanner(input);
            String tema=arch.nextLine();
            String fuente=arch.nextLine();
       
            System.out.println(fuente);
            Interfaz in =null;
            Color Fondo = new Color(51, 51, 51);
            Color CabeceraC=new Color(27, 27, 27);
            Color titulosCol=new Color(137, 209, 239);
            Color FondoAr=new Color(20, 20, 20, 255);
            Color txt=new Color(137, 209, 239);
            Color txtW=Color.WHITE;
            Color content=new Color(37, 37, 39);
                if(tema.equals("BLANCO")){
                    Fondo = new Color(85,96, 106, 255);
                    CabeceraC=new Color(58, 75, 92);
                    titulosCol=new Color(39, 186, 144);
                    FondoAr=new Color(204, 205, 203);
                    txt=new Color(37,43,59);
                    txtW=Color.BLACK;
                    content=new Color(234, 235, 233);
                }
                 in = new Interfaz(CabeceraC,titulosCol,txtW,txt,Fondo,FondoAr,content,fuente);
                in.setBackground(Color.BLACK);
                in.setSize(1095, 710);
                in.setUndecorated(true);
                Shape forma = new RoundRectangle2D.Double(0, 0, in.getBounds().width, in.getBounds().height, 20, 20);
                AWTUtilities.setWindowShape(in, forma);
                in.setResizable(false);
                in.setLocationRelativeTo(null);
                in.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                in.setVisible(true);
        long fin = System.currentTimeMillis();
        double tiempo = (double) ((fin - inicio));
       
        System.out.println("Tiempo de inicio "+tiempo +" milisegundos");
        
         Runtime runtime = Runtime.getRuntime();
         int dataSize = 1024 * 1024;
         System.out.println("-------------------------------------");
        System.out.println ("Consumo al iniciar");
        System.out.println ("Memoria máxima: " + runtime.maxMemory() / dataSize + "MB");
        System.out.println ("Memoria total: " + runtime.totalMemory() / dataSize + "MB");
        System.out.println ("Memoria libre: " + runtime.freeMemory() / dataSize + "MB");
        System.out.println ("Memoria usada: " + (runtime.totalMemory() - runtime.freeMemory()) / dataSize + "MB");
        int h = in.getContentPane().getHeight();
        int w = in.getContentPane().getWidth();
        System.out.println("-> "+w+"x"+h);
    }   
}
