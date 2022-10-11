/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Compiler;

import com.sun.awt.AWTUtilities;
import java.awt.Color;
import java.awt.Font;
import static java.awt.Frame.ICONIFIED;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Julio Ponce
 */
public class Interfaz extends JFrame implements ActionListener/*, ItemListener*/ {
    static GraphicsDevice device = GraphicsEnvironment
        .getLocalGraphicsEnvironment().getScreenDevices()[0];
    int xx, xy;
    JLabel Cabecera;
    JButton BotonCerrar, Min;
    Color Fondo = new Color(51, 51, 51);
    JTextArea Codigo, Intermedio, Ensamblador, Consola;
    JScrollPane ScrollC, ScrollCo;
    JButton convertir, limpiar;
    JPanel Pestanas, Botones, Base;
    JMenu Archivo, Ejecutar, Vista, Ayuda;
    JMenuBar mb;
    ButtonGroup Grupo1, Grupo2;
    JMenuItem Nuevo, Abrir, Guardar, GuardarComo, Ajustes, Salir;
    JMenuItem Compilar, Optimizar, Analisis;
    JMenuItem Completa, Chica, Tema2,Tema1;
    JMenuItem Manual, Acerca;
    JButton ejec, deb, fil, fold, opt, ajus;
    NumeroLinea ln;
    JLabel Estado, Lineas,Com;
    String archivoAct="SN";
    String archivR="SN";
    JLabel tituloc;
    Color CabeceraC;
    Color titulosCol;
    Color FondoAr;
    Color txt;
    Color txtW,content;
    JPanel cabG = new JPanel();
     JLabel tit;
     JDesktopPane P;
     String LetraG;
 
    public Interfaz(Color CabeceraT, Color titulosT,Color titulW, Color textoT, Color FondoT,Color Fondo2T,Color con,String Letras) throws IOException {
        super("");
        CabeceraC=CabeceraT;
        titulosCol=titulosT;
        txtW=titulW;
        txt=textoT;
        LetraG=Letras;
        this.Fondo = FondoT;
        FondoAr=Fondo2T;
        content=con;
        setLayout(null);
        getContentPane().setBackground(content);
        BufferedImage bufferedImage0 = ImageIO.read(new File("Media/LOGO.png"));
        Image image0 = bufferedImage0.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        try {
            setIconImage(image0);
        } catch (Exception e) {
        }
        
        BotonCerrar = new JButton();
        BotonCerrar.setFocusable(false);
        BotonCerrar.setOpaque(true);
        BotonCerrar.setBorderPainted(false);
        BotonCerrar.setBackground(Fondo);
        BufferedImage bufferedImage2 = ImageIO.read(new File("Media/CLOS.png"));
        Image image2 = bufferedImage2.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon icon2 = new ImageIcon(image2);
        BotonCerrar.setIcon(icon2);
        BotonCerrar.setPressedIcon(icon2);
        BotonCerrar.setBounds(5, 5, 30, 30);
        BotonCerrar.addActionListener(this);

        add(BotonCerrar);
        Min = new JButton();
        Min.setFocusable(false);
        Min.setOpaque(true);
        Min.setBorderPainted(false);
        Min.setBackground(Fondo);
        BufferedImage bufferedImage = ImageIO.read(new File("Media/MIN.png"));
        Image image = bufferedImage.getScaledInstance(22, 22, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(image);
        Min.setIcon(icon);
        Min.setPressedIcon(icon);
        Min.setBounds(40, 5, 30, 30);
        Min.addActionListener(this);
        add(Min);

        Estado = new JLabel("<html>Bien</html>", SwingConstants.CENTER);
        Estado.setOpaque(true);
        Estado.setBackground(new Color(37, 136, 95, 255));
        Estado.setForeground(Color.WHITE);
        Estado.setBounds(0, 690, 70, 20);
        add(Estado);

        Com = new JLabel("<html></html>", SwingConstants.CENTER);
        Com.setForeground(Color.WHITE);
        Com.setBounds(70, 690, 400, 20);
        add(Com);
            
        Lineas = new JLabel("<html> </html>", SwingConstants.RIGHT);
        Lineas.setText("Linea: 0      Columna: 0     ");
        Lineas.setOpaque(true);
        Lineas.setBackground(new Color(64, 111, 207, 255));
        Lineas.setForeground(Color.WHITE);
        Lineas.setBounds(70, 690, 1025, 20);
        add(Lineas);

        Botones = new JPanel();
        Botones.setBounds(0, 0, 70, 690);
        Botones.setLayout(new GridLayout(9, 1));
        Botones.setBackground(Fondo);
        Botones.add(new JLabel(""));
        fil = new JButton();
        fil.setFocusable(false);
        fil.setOpaque(true);
        fil.setBorderPainted(false);
        fil.setBackground(Fondo);
        BufferedImage bufferedImage3 = ImageIO.read(new File("Media/FIL.png"));
        Image image3 = bufferedImage3.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon icon3 = new ImageIcon(image3);
        fil.setIcon(icon3);
        fil.setPressedIcon(icon3);
        fil.setBounds(40, 5, 30, 30);
        fil.addActionListener(this);
        fil.setToolTipText("Nuevo");
        Botones.add(fil);
        fold = new JButton();
        fold.setFocusable(false);
        fold.setOpaque(true);
        fold.setBorderPainted(false);
        fold.setBackground(Fondo);
        BufferedImage bufferedImage4 = ImageIO.read(new File("Media/FOLD.png"));
        Image image4 = bufferedImage4.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon icon4 = new ImageIcon(image4);
        fold.setIcon(icon4);
        fold.setPressedIcon(icon4);
        fold.setToolTipText("Abrir");
        fold.setBounds(40, 5, 30, 30);
        fold.addActionListener(this);
        Botones.add(fold);
        deb = new JButton();
        deb.setFocusable(false);
        deb.setOpaque(true);
        deb.setBorderPainted(false);
        deb.setBackground(Fondo);
        BufferedImage bufferedImage5 = ImageIO.read(new File("Media/DEB.png"));
        Image image5 = bufferedImage5.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon icon5 = new ImageIcon(image5);
        deb.setIcon(icon5);
        deb.setPressedIcon(icon5);
        deb.setToolTipText("Depurador");
        deb.setBounds(40, 5, 30, 30);
        deb.addActionListener(this);
        Botones.add(deb);
        ejec = new JButton();
        ejec.setFocusable(false);
        ejec.setOpaque(true);
        ejec.setBorderPainted(false);
        ejec.setBackground(Fondo);
        BufferedImage bufferedImage6 = ImageIO.read(new File("Media/RUN.png"));
        Image image6 = bufferedImage6.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon icon6 = new ImageIcon(image6);
        ejec.setIcon(icon6);
        ejec.setPressedIcon(icon6);
        ejec.setBounds(40, 5, 30, 30);
        ejec.setToolTipText("Compilar");
        ejec.addActionListener(this);
        Botones.add(ejec);
        opt = new JButton();
        opt.setFocusable(false);
        opt.setOpaque(true);
        opt.setBorderPainted(false);
        opt.setBackground(Fondo);
        BufferedImage bufferedImage7 = ImageIO.read(new File("Media/OPT.png"));
        Image image7 = bufferedImage7.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon icon7 = new ImageIcon(image7);
        opt.setIcon(icon7);
        opt.setPressedIcon(icon7);
        opt.setBounds(40, 5, 30, 30);
        opt.addActionListener(this);
        opt.setToolTipText("Optimizar Codigo");
        Botones.add(opt);
        JLabel vaci2 = new JLabel("");
        vaci2.setBounds(40, 5, 30, 30);
        Botones.add(vaci2);
        JLabel vaci = new JLabel("");
        vaci.setBounds(40, 5, 30, 30);
        Botones.add(vaci);
        ajus = new JButton();
        ajus.setFocusable(false);
        ajus.setOpaque(true);
        ajus.setBorderPainted(false);
        ajus.setBackground(Fondo);
        ajus.setToolTipText("Ajustes");
        BufferedImage bufferedImage8 = ImageIO.read(new File("Media/AJUS.png"));
        Image image8 = bufferedImage8.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon icon8 = new ImageIcon(image8);
        ajus.setIcon(icon8);
        ajus.setPressedIcon(icon8);
        ajus.setBounds(40, 5, 30, 30);
        ajus.addActionListener(this);
        Botones.add(ajus);
        add(Botones);

        Cabecera = new JLabel("<HTML>Compilador JPAS</HTML>", SwingConstants.CENTER); //Titulo del programa
        Cabecera.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                CabeceraMouseDragged(evt);
            }
        });
        Cabecera.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                CabeceraMousePressed(evt);
            }
        });
        cabG.setLayout(new GridLayout(1, 1));
        cabG.setBounds(0, 0, 1095, 40); //Cambia el tamaño y posicion del titulo
        cabG.setBackground(CabeceraC);
        Cabecera.setBackground(CabeceraC);
        Cabecera.setForeground(Color.WHITE);
        Cabecera.setFont(new Font(LetraG, Font.BOLD, 15));
        cabG.add(Cabecera); //Se agrega el titulo
        add(cabG);

        P = new JDesktopPane();
        P.setBorder(BorderFactory.createEmptyBorder());
        P.setBackground(CabeceraC);
        mb = new JMenuBar();
        mb.setLayout(new GridLayout(1, 4));
        mb.setBounds(0, 0, 700, 30);
        mb.setForeground(txtW);
        mb.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        mb.setBackground(content);
        Border OUTER = new MatteBorder(0, 0, 1, 0, new Color(78, 147, 117));
        Border OUTER2 = new MatteBorder(0, 0, 0, 1, new Color(78, 147, 117));
        mb.setBorder(OUTER);
        P.add(mb);
      Archivo = new JMenu("Archivo");
      Archivo.getPopupMenu().setBorder(new MatteBorder(1, 1,1, 1, CabeceraC));
      Archivo.setForeground(txtW);
      Archivo.setBounds(20, 10, 120, 30);
      Archivo.setBorder(OUTER2);
      
      mb.add(Archivo);
      Nuevo = new JMenuItem("Nuevo archivo");
      Nuevo.setBackground(content);
      Nuevo.setForeground(txtW);
      Nuevo.setBorder(BorderFactory.createEmptyBorder());
      Nuevo.addActionListener(this);
      Archivo.add(Nuevo);
      Abrir = new JMenuItem("Abrir archivo");
      Abrir.setBackground(content);
      Abrir.setForeground(txtW);
      Abrir.setBorder(BorderFactory.createEmptyBorder());
      Abrir.addActionListener(this);
      Archivo.add(Abrir);
      Guardar = new JMenuItem("Guardar archivo");
      Guardar.setBackground(content);
      Guardar.setForeground(txtW);
      Guardar.setBorder(BorderFactory.createEmptyBorder());
      Guardar.addActionListener(this);
      Archivo.add(Guardar);
      GuardarComo = new JMenuItem("Guardar Como");
      GuardarComo.setBackground(content);
      GuardarComo.setForeground(txtW);
      GuardarComo.setBorder(BorderFactory.createEmptyBorder());
      GuardarComo.addActionListener(this);
      Archivo.add(GuardarComo);
      Ajustes = new JMenuItem("Ajustes");
      Ajustes.setBackground(content);
      Ajustes.setForeground(txtW);
      Ajustes.setBorder(BorderFactory.createEmptyBorder());
      Ajustes.addActionListener(this);
      Archivo.add(Ajustes);
      Salir = new JMenuItem("Salir");
      Salir.setBackground(content);
      Salir.setForeground(txtW);
      Salir.setBorder(BorderFactory.createEmptyBorder());
      Salir.addActionListener(this);
      Archivo.add(Salir);
      Ejecutar = new JMenu("Ejecutar");
      Ejecutar.getPopupMenu().setBorder(new MatteBorder(1, 1,1, 1, CabeceraC));
      Ejecutar.setBounds(20, 10, 120, 30);
      Ejecutar.setForeground(txtW);
      Ejecutar.setBorder(OUTER2);
      mb.add(Ejecutar);
      Compilar = new JMenuItem("Compilar Proyecto");
      Compilar.setBackground(content);
      Compilar.setForeground(txtW);
      Compilar.setBorder(BorderFactory.createEmptyBorder());
      Compilar.addActionListener(this);
      Ejecutar.add(Compilar);
      Optimizar = new JMenuItem("Optimizar Codigo");
      Optimizar.setBackground(content);
      Optimizar.setForeground(txtW);
      Optimizar.setBorder(BorderFactory.createEmptyBorder());
      Optimizar.addActionListener(this);
      Ejecutar.add(Optimizar);
      Analisis = new JMenuItem("Analizar Codigo");
      Analisis.setBackground(content);
      Analisis.setForeground(txtW);
      Analisis.setBorder(BorderFactory.createEmptyBorder());
      Analisis.addActionListener(this);
      Ejecutar.add(Analisis);
      Vista = new JMenu("Vista");
      Vista.getPopupMenu().setBorder(new MatteBorder(1, 1,1, 1, CabeceraC));
      Vista.setBounds(20, 10, 120, 30);
      Vista.setForeground(txtW);
      Vista.setBorder(OUTER2);
      mb.add(Vista);
      Completa = new JMenuItem("Pantalla Completa");
      Completa.setBackground(content);
      Completa.setForeground(txtW);
      Completa.setBorder(BorderFactory.createEmptyBorder());
      Completa.addActionListener(this);
      Vista.add(Completa);
      Chica = new JMenuItem("Pantalla chica");
      Chica.setBackground(content);
      Chica.setForeground(txtW);
      Chica.setBorder(BorderFactory.createEmptyBorder());
      Chica.addActionListener(this);
      Vista.add(Chica);
      Tema2 = new JMenuItem("Tema Claro");
      Tema2.setBackground(content);
      Tema2.setForeground(txtW);
      Tema2.setBorder(BorderFactory.createEmptyBorder());
      Tema2.addActionListener(this);
      Vista.add(Tema2);
      Tema1 = new JMenuItem("Tema Obscuro");
      Tema1.setBackground(content);
      Tema1.setForeground(txtW);
      Tema1.setBorder(BorderFactory.createEmptyBorder());
      Tema1.addActionListener(this);
      Vista.add(Tema1);
      Ayuda = new JMenu("Ayuda");
      Ayuda.getPopupMenu().setBorder(new MatteBorder(1, 1,1, 1, CabeceraC));
      Ayuda.setBounds(20, 10, 120, 30);
      Ayuda.setForeground(txtW);
      Ayuda.setBorder(OUTER2);
      mb.add(Ayuda);
      Manual = new JMenuItem("Manual de usuario");
      Manual.setBackground(content);
      Manual.setForeground(txtW);
      Manual.setBorder(BorderFactory.createEmptyBorder());
      Manual.addActionListener(this);
      Ayuda.add(Manual);
      Acerca = new JMenuItem("Acerca de");
      Acerca.setBackground(content);
      Acerca.setForeground(txtW);
      Acerca.setBorder(BorderFactory.createEmptyBorder());
      Acerca.addActionListener(this);
      Ayuda.add(Acerca);
      P.setBounds(70, 40, 1025, 30);
      P.setBorder(OUTER);
      add(P);

        tituloc = new JLabel("Area de Codigo");
        tituloc.setBounds(120, 80, 500, 70);
        tituloc.setFont(new Font(LetraG, Font.BOLD, 18));
        tituloc.setForeground(titulosCol);
        add(tituloc);

        Base = new JPanel();
        Base.setLayout(null);
        Base.setBounds(80, 130, 1000, 555);

        Codigo = new JTextArea();
        Codigo.setFont(new Font(LetraG, Font.PLAIN, 15));
        Codigo.setBackground(FondoAr);
        Codigo.setCaretColor(txt);
        Codigo.setSelectedTextColor(new Color(30, 30, 30, 255));
        Codigo.setSelectionColor(txt);
        Codigo.setForeground(txt);
        ScrollC = new JScrollPane(Codigo, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        ScrollC.setBounds(0, 0, 1000, 400);
        ScrollC.setBackground(new Color(5, 12, 31));
        ScrollC.setForeground(txtW);

        Codigo.addCaretListener(new CaretListener() { //Permite actualizar la posicion del cursor en le Area de texto
            public void caretUpdate(CaretEvent e) {
                JTextArea editArea = (JTextArea) e.getSource();  //Obtiene cual es el Area de texto don de trabajara
                int linea = 1;
                int columna = 1;
                try {
                    int caretpos = editArea.getCaretPosition(); //Optine la longitud de caracteres de la linea actual
                    linea = editArea.getLineOfOffset(caretpos); //Obtienen cual es la linea actual
                    columna = caretpos - editArea.getLineStartOffset(linea); //Determina las columnas 

                    // Ya que las líneas las cuenta desde la 0
                    linea += 1;
                } catch (Exception ex) {
                }
                Lineas.setText("Linea: " + linea + "      Columna: " + columna + "     "); //Actualiza el estado actual de la posicion del cursor 
            }
        });
        Base.add(ScrollC);

        ln = new NumeroLinea(Codigo,Fondo,txt);
        ScrollC.setRowHeaderView(ln);
        ScrollC.setBorder(BorderFactory.createEmptyBorder());

        tit = new JLabel("<html>Salida de la Ejecucion</html>", SwingConstants.CENTER);
        tit.setBackground(new Color(27, 27, 27));
        tit.setForeground(txtW);
        tit.setFont(new Font(LetraG, Font.BOLD, 14));
        tit.setBounds(0, 400, 1070, 30);
        Base.add(tit);
        Consola = new JTextArea();
        Consola.setFont(new Font(LetraG, Font.ITALIC, 15));
        Consola.setBackground(FondoAr);
        Consola.setCaretColor(txt);
        Consola.setSelectedTextColor(new Color(30, 30, 30, 255));
        Consola.setSelectionColor(txt);
        Consola.setForeground(txt);
        ScrollCo = new JScrollPane(Consola, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        ScrollCo.setBounds(0, 430, 1070, 125);
        ScrollCo.setBorder(BorderFactory.createEmptyBorder());
        Base.add(ScrollCo);
        Base.setBackground(content);
        add(Base);
        Base.setBorder(BorderFactory.createEmptyBorder());

    }

////////////////////////////////////////////
    Runtime runtime = Runtime.getRuntime();
    int i = 0;
    File ArchiIn;
    File ArchEN;

    @Override

    public void actionPerformed(ActionEvent e) {
        long inicio = System.currentTimeMillis();
        Traduccion tr = new Traduccion();
        if (e.getSource() == ejec || e.getSource()==Compilar) {
            
            String nom= archivoAct.replaceAll(".JPAS|.jpas","");
            ArchiIn = new File(archivR.replaceAll(archivoAct,"")+nom+"_inter.jpin");
            // Si el archivo no existe es creado
            if (!ArchiIn.exists()) {
                try {
                    ArchiIn.createNewFile();
                } catch (IOException ex) {
                    System.out.println("Error: "+ex);
                        }
            }
             System.out.println(archivR+"\\"+nom+"_inter.jpin");
            ArchEN = new File(archivR.replaceAll(archivoAct,"")+nom+"_ensam.jpasm");
            // Si el archivo no existe es creado
            if (!ArchEN.exists()) {
                try {
                    ArchEN.createNewFile();
                } catch (IOException ex) {
                    System.out.println("Error: "+ex);
                }
            }
            System.out.println(archivR+"\\"+nom+"_ensam.jpasm");
          
            //Optimizar
            String auxC=Codigo.getText();
            int aux=auxC.split("\n").length;
            if(!auxC.equals("") && !auxC.matches(" *") && aux>3){
            if (tr.ComprobadorLexico(auxC) == true && tr.ComprobarSintaxis(auxC) == true && tr.AnalizadorSemantico(auxC.split("\n")) == true) {
                String optimizado = tr.optimizar(Codigo.getText().split("\n"));
                Consola.setText("\tConversion completada\n Todo correcto.\n");
                Consola.append(tr.General);
                tr = new Traduccion();
                if (tr.ComprobadorLexico(optimizado) == true && tr.ComprobarSintaxis(optimizado) == true && tr.AnalizadorSemantico(optimizado.split("\n")) == true) {
                    String inter= tr.CodigoIntermedio(optimizado);
                      BufferedWriter BWIN = null;
                            try {
                                BWIN = new BufferedWriter(new FileWriter(ArchiIn));
                            } catch (IOException ex) {
                                System.out.println("Error IN");
                            }

                    try {
                            BWIN.write(inter);
                            System.out.println("Escrito INTER");
                    } catch (IOException ex) {
                        System.out.println("NO Escrito INTER");
                        Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                    }
                                        try {
                        BWIN.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                    }
                      BufferedWriter BWEN=null;
                            try {
                                BWEN = new BufferedWriter(new FileWriter(ArchEN));
                            } catch (IOException ex) {
                                System.out.println("Error EN");
                                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                            }
                      String ensam=tr.CodigoEnsamblador(inter);
                    try {
                        BWEN.write(ensam);
                        System.out.println("Escrito ENSAM");
                    } catch (IOException ex) {
                        System.out.println("NO Escrito ENSAM");
                        Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        BWEN.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                Ensamblador en=new Ensamblador();
                try {
                    en.principal(archivR.replaceAll(archivoAct,""),nom+"_ensam.jpasm");
                } catch (IOException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                }
                Estado.setText("Bien");
                Estado.setBackground(new Color(37, 136, 95, 255));
            } else {
                Estado.setBackground(new Color(192,57,43));
                Estado.setText("Error");
                Consola.setText("\tErrores encontrados [" + tr.error + "]\n");
                Consola.append(tr.General);
            }
            System.gc();
            }
            else Consola.setText("\tCodigo Vacio. No hay nada que hacer.\n");
            

        } 
        else if (e.getSource() == limpiar) {
            Codigo.setText("");
            Ensamblador.setText("");
            Intermedio.setText("");
            Consola.setText("");
        } else if (e.getSource() == BotonCerrar) {
            System.exit(0);
        } else if (e.getSource() == Min) {
            this.setState(ICONIFIED);
        }
        else if (e.getSource() == Salir) {
            System.exit(0);
        }
        else if (e.getSource() == Abrir || e.getSource() == fold) {
            JFileChooser Chooser = new JFileChooser();
            
            Chooser.showOpenDialog(this);//Abre el seleccionador de archivos
            File archivo = Chooser.getSelectedFile(); //El archivo elegido se establece
            if (archivo != null) {//Si el archivo existe
                if (!archivo.getPath().toLowerCase().endsWith(".jpas")) {
                    JOptionPane.showMessageDialog(null, "El archivo debe de ser de extension .jpas", "Error de archivo", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        Scanner input = new Scanner(archivo);
                        archivR = archivo.getPath();
                        archivoAct = archivo.getName();
                        tituloc.setText("Area de Codigo [" + archivoAct + "]");
                        Codigo.setText("");//Limpia el area de texto
                        while (input.hasNextLine()) {//Analiza linea por linea el contenido del archivo
                            String line = input.nextLine(); //Obtiene la linea actual
                            Codigo.append(line);//Imprime la linea en el area de texto
                            Codigo.append("\n"); //Da un salto de linea en la area de texto
                        }
                        input.close();//Se cierra el analisis del archivo
                        //Se establecen los valores por defecto de las etiquetas.
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

        }
        else if(e.getSource()==Guardar ){
             File archivo;
            if(archivoAct.equals("SN") && archivR.equals("SN")){
                    JFileChooser guardar = new JFileChooser();
                    guardar.showSaveDialog(null);
                    guardar.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                     archivo = guardar.getSelectedFile();
                    File ruta = guardar.getCurrentDirectory();
                    String nombre = guardar.getSelectedFile().getName();
                    archivoAct=nombre;
                    archivR=ruta.getPath()+"\\"+archivoAct;
             
                    try {
                        archivo.createNewFile();
                    } catch (IOException ex) {
                        Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            
                    else{
                         archivo = new File(archivR);
                    }
                    BufferedWriter B=null;
                            try {
                                B = new BufferedWriter(new FileWriter(archivo));
                            } catch (IOException ex) {
                                System.out.println("Error EN");
                                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                            }
                    try {
                        String valor=Codigo.getText();
                        B.write(valor);
                        System.out.println("Escrito ENSAM");
                    } catch (IOException ex) {
                        System.out.println("NO Escrito ENSAM");
                        Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        B.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        else if(e.getSource()==GuardarComo){
                File archivo;
                JFileChooser guardar = new JFileChooser();
                    guardar.showSaveDialog(null);
                    guardar.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                     archivo = guardar.getSelectedFile();
                    File ruta = guardar.getCurrentDirectory();
                    String nombre = guardar.getSelectedFile().getName();
                    archivoAct=nombre;
                    archivR=ruta.getPath()+"\\"+archivoAct;
                    System.out.println(archivR);
                    try {
                        archivo.createNewFile();
                    } catch (IOException ex) {
                        Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    BufferedWriter B=null;
                            try {
                                B = new BufferedWriter(new FileWriter(archivo));
                            } catch (IOException ex) {
                                System.out.println("Error EN");
                                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                            }
                    try {
                        String valor=Codigo.getText();
                        B.write(valor);
                        System.out.println("Escrito ENSAM");
                    } catch (IOException ex) {
                        System.out.println("NO Escrito ENSAM");
                        Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        B.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        else if(e.getSource()==fil || e.getSource()==Nuevo){
               Interfaz in=null;
                try {
                    in = new Interfaz(CabeceraC,titulosCol,txtW,txt,Fondo,FondoAr,content,LetraG);
                } catch (IOException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                }
                in.setBackground(Color.BLACK);
                in.setSize(1095, 710);
                in.setUndecorated(true);
                Shape forma = new RoundRectangle2D.Double(0, 0, in.getBounds().width, in.getBounds().height, 20, 20);
                AWTUtilities.setWindowShape(in, forma);
                in.setResizable(false);
                in.setLocationRelativeTo(null);
                in.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                in.setVisible(true);
           }
        else if(e.getSource()==Optimizar || e.getSource()==opt){
               String auxC=Codigo.getText();
                int aux=auxC.split("\n").length;
                if(!auxC.equals("") && !auxC.matches(" *") && aux>3){
                if (tr.ComprobadorLexico(auxC) == true && tr.ComprobarSintaxis(auxC) == true && tr.AnalizadorSemantico(auxC.split("\n")) == true) {
                tr.optimizar(Codigo.getText().split("\n"));
                Consola.setText("\tOptimización completada\n Todo correcto.\n");
                Consola.append(tr.General.replace(", se ha optimizado","."));
                Estado.setText("Bien");
                Estado.setBackground(new Color(37, 136, 95, 255));
                }
                else{
                    Consola.setText("\tErrores encontrados\nNo se pudo optimizar, ya que existen errores.\n");
                    Consola.append(tr.General);
                    Estado.setBackground(new Color(192,57,43));
                    Estado.setText("Error");
                }
           }else{
                    Consola.setText("\tCodigo vacio\n");
                }
            
        }
           else if(e.getSource()==Analisis){
               String auxC=Codigo.getText();
                int aux=auxC.split("\n").length;
                if(!auxC.equals("") && !auxC.matches(" *") && aux>3){
                if (tr.ComprobadorLexico(auxC) == true && tr.ComprobarSintaxis(auxC) == true && tr.AnalizadorSemantico(auxC.split("\n")) == true) {
                Consola.setText("\tAnalisis completado\n Todo correcto.\n");
                Consola.append(tr.General);
                Estado.setText("Bien");
                Estado.setBackground(new Color(37, 136, 95, 255));
                }
                else{
                    Consola.setText("\tErrores encontrados\n");
                    Consola.append(tr.General);
                    Estado.setBackground(new Color(192,57,43));
                    Estado.setText("Error");
                }
           }else{
                    Consola.setText("\tCodigo vacio\n");
                }
           }else if(e.getSource()==Acerca){
               JFrame A = null;
            try {
                A = AcercaDe();
                A.toFront();
                A.requestFocus();
                A.setAlwaysOnTop(true);
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
               A.setBackground(Color.BLACK);
               A.setSize(400, 400);
               A.setUndecorated(true);
               Shape forma = new RoundRectangle2D.Double(0, 0, A.getBounds().width, A.getBounds().height, 20, 20);
               AWTUtilities.setWindowShape(A, forma);
               A.setResizable(false);
               A.setLocationRelativeTo(null);
               A.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                A.setVisible(true);
           }
           else if(e.getSource()==Tema2 || e.getSource()==Tema1){
              File config = new File(".config.txt");
                // Si el archivo no existe es creado
                if (!config.exists()) {
                    try {
                        config.createNewFile();
                        String valor;
                        if(e.getSource()==Tema2)valor= "BLANCO";
                        else valor= "OBSCURO";
                        System.out.println(valor);
                          BufferedWriter BWIN = null;
                                try {
                                    BWIN = new BufferedWriter(new FileWriter(config));
                                } catch (IOException ex) {
                                    System.out.println("Error IN");
                                }
                                try {
                                    BWIN.write(valor+"\n");
                                    BWIN.write(LetraG);

                        } catch (IOException ex) {
                            System.out.println("NO Escrito INTER");
                            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                        }
                                BWIN.close();
                    } catch (IOException ex) {
                        System.out.println("Error: "+ex);
                            }
                }
                        String valor;
                        if(e.getSource()==Tema2)valor= "BLANCO";
                        else valor= "OBSCURO";
                        System.out.println(valor);
                          BufferedWriter BWIN = null;
                                try {
                                    BWIN = new BufferedWriter(new FileWriter(config));
                                } catch (IOException ex) {
                                    System.out.println("Error IN");
                                }
                                try {
                                    BWIN.write(valor+"\n");
                                    BWIN.write(LetraG);

                        } catch (IOException ex) {
                            System.out.println("NO Escrito INTER");
                            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                        }
            try {
                BWIN.close();
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }

              JFrame ventana=new JFrame("Atención");
        ventana.setLayout(null);
        ventana.getContentPane().setBackground(content);
        BufferedImage bufferedImage0 = null;
            try {
                bufferedImage0 = ImageIO.read(new File("Media/LOGO.png"));
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        Image image0 = bufferedImage0.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        try {
            ventana.setIconImage(image0);
        } catch (Exception ex) {
        }
        JButton BC = new JButton();
        BC.setFocusable(false);
        BC.setOpaque(true);
        BC.setBorderPainted(false);
        BC.setBackground(CabeceraC);
        BufferedImage bufferedImage2 = null;
            try {
                bufferedImage2 = ImageIO.read(new File("Media/CLOS.png"));
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        Image image2 = bufferedImage2.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon icon2 = new ImageIcon(image2);
        BC.setIcon(icon2);
        BC.setPressedIcon(icon2);
        BC.setBounds(5, 5, 30, 30);
        BC.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
              ventana.dispose();   
            }
        });
        ventana.add(BC);
        JButton MV = new JButton();
        MV.setFocusable(false);
        MV.setOpaque(true);
        MV.setBorderPainted(false);
        MV.setBackground(CabeceraC);
        BufferedImage bufferedImage = null;
            try {
                bufferedImage = ImageIO.read(new File("Media/MIN.png"));
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        Image image = bufferedImage.getScaledInstance(22, 22, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(image);
        MV.setIcon(icon);
        MV.setPressedIcon(icon);
        MV.setBounds(40, 5, 30, 30);
        MV.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
              ventana.setState(ICONIFIED);  
            }
        });
        ventana.add(MV);
        JLabel CB = new JLabel("<HTML>Reinicio Requerido</HTML>", SwingConstants.CENTER); //Titulo del programa
        CB.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                int x = evt.getXOnScreen();
                int y = evt.getYOnScreen();

                ventana.setLocation(x - xx, y - xy);
            }
        });
        CB.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                xx = evt.getX();
                xy = evt.getY();
            }
        });
        
        JPanel cab = new JPanel();
        cab.setLayout(new GridLayout(1, 1));
        cab.setBounds(0, 0, 400, 40); //Cambia el tamaño y posicion del titulo
        cab.setBackground(CabeceraC);
        CB.setBackground(CabeceraC);
        CB.setForeground(Color.WHITE);
        CB.setFont(new Font(LetraG, Font.BOLD, 15));
        cab.add(CB); //Se agrega el titulo
        ventana.add(cab);
        JLabel Datos= new JLabel("<html>Para notar el cambio necesita reiniciar, si va a reiniciar procure guardar su trabajo previamente.</html>", SwingConstants.CENTER);
        Datos.setForeground(txtW);
        Datos.setBounds(10,50,390,100);
        
        Datos.setFont(new Font(LetraG, Font.BOLD, 12));
        ventana.add(Datos);
        JButton Rein = new JButton("Reiniciar");
        Rein.setFocusable(false);
        Rein.setOpaque(true);
        Rein.setBorderPainted(false);
        Rein.setBackground(FondoAr);
        Rein.setForeground(txt); //Establece el color del texto
        Rein.setFont(new Font(LetraG, Font.BOLD, 12)); //Establece el tipo de fuente
        Rein.setBounds(100, 150, 100, 40);
        Rein.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
              ventana.dispose();
              dispose();
              Principal p= new Principal();
                try {
                    p.main(null);
                } catch (IOException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.gc();
            }
        });
        ventana.add(Rein);
        JButton man = new JButton("Mas tarde");
        man.setFocusable(false);
        man.setOpaque(true);
        man.setBorderPainted(false);
        man.setBackground(FondoAr);
        man.setForeground(txt); //Establece el color del texto
        man.setFont(new Font(LetraG, Font.BOLD, 12)); //Establece el tipo de fuente
        man.setBounds(210, 150, 100, 40);
        man.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
              ventana.dispose();   
            }
        });
        ventana.add(man);
        ventana.setSize(400, 200);
        ventana.setUndecorated(true);
        Shape forma = new RoundRectangle2D.Double(0, 0, ventana.getBounds().width, ventana.getBounds().height, 20, 20);
        AWTUtilities.setWindowShape(ventana, forma);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.toFront();
        ventana.requestFocus();
        ventana.setAlwaysOnTop(true);
        ventana.setVisible(true);
           }
           else if(e.getSource()==deb){
                JFrame D = null;
            try {
                D = Debug();
                D.toFront();
                D.requestFocus();
                D.setAlwaysOnTop(true);
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
               D.setBackground(Color.BLACK);
               D.setSize(800, 600);
               D.setUndecorated(true);
               Shape forma = new RoundRectangle2D.Double(0, 0, D.getBounds().width, D.getBounds().height, 20, 20);
               AWTUtilities.setWindowShape(D, forma);
               D.setResizable(false);
               D.setLocationRelativeTo(null);
               D.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               D.setVisible(true);
           }
           else if(e.getSource()==Manual){
                JFrame D = null;
            try {
                D = manualV();
                D.toFront();
                D.requestFocus();
                D.setAlwaysOnTop(true);
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
               D.setBackground(Color.BLACK);
               D.setSize(800, 600);
               D.setUndecorated(true);
               Shape forma = new RoundRectangle2D.Double(0, 0, D.getBounds().width, D.getBounds().height, 20, 20);
               AWTUtilities.setWindowShape(D, forma);
               D.setResizable(false);
               D.setLocationRelativeTo(null);
               D.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               D.setVisible(true);
           }
           else if(e.getSource()==Completa){
               device.setFullScreenWindow(this);
               this.setAlwaysOnTop(false);
               int h = getContentPane().getHeight();
               int w = getContentPane().getWidth();
                System.out.println("-> "+w+"x"+h);
                maximizar(w,h);
               
           }
            else if(e.getSource()==Chica){
               device.setFullScreenWindow(null);
               minimizar();
           }
            else if(e.getSource()==Ajustes||e.getSource()==ajus){
                JFrame D = null;
            try {
                D = Ajustes();
                D.toFront();
                D.requestFocus();
                D.setAlwaysOnTop(true);
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
               D.setBackground(Color.BLACK);
               D.setSize(800, 600);
               D.setUndecorated(true);
               Shape forma = new RoundRectangle2D.Double(0, 0, D.getBounds().width, D.getBounds().height, 20, 20);
               AWTUtilities.setWindowShape(D, forma);
               D.setResizable(false);
               D.setLocationRelativeTo(null);
               D.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               D.setVisible(true);
           }
           else if(e.getSource()==Completa){
               device.setFullScreenWindow(this);
               this.setAlwaysOnTop(false);
               int h = getContentPane().getHeight();
               int w = getContentPane().getWidth();
                System.out.println("-> "+w+"x"+h);
                maximizar(w,h);
               
            }
           System.gc();
           long fin = System.currentTimeMillis();

            double tiempo = (double) ((fin - inicio));
            int dataSize = 1024 * 1024;
            Com.setText("  Tiempo de Compilacion:"+tiempo+"ms    Memoria usada: "+(runtime.totalMemory() - runtime.freeMemory()) / dataSize+"MB");
            System.out.println(i + "-------------------------------------");
            i++;
            System.out.println("Consumo despues de traducir");
            System.out.println("Memoria máxima: " + runtime.maxMemory() / dataSize + "MB");
            System.out.println("Memoria total: " + runtime.totalMemory() / dataSize + "MB");
            System.out.println("Memoria libre: " + runtime.freeMemory() / dataSize + "MB");
            System.out.println("Memoria usada: " + (runtime.totalMemory() - runtime.freeMemory()) / dataSize + "MB");
            System.out.println("--Solicitud procesada en " + tiempo + " milisegundos");
    }
    public void maximizar(int X,int Y){
        Botones.setBounds(0, 0, 70, Y-20);
        Base.setBounds(80, 130, X-95, Y-155);
        ScrollC.setBounds(0, 0, X-95, Y-310);
        cabG.setBounds(0, 0, X, 40);
        Estado.setBounds(0, Y-20, 70, 20);
        Lineas.setBounds(70, Y-20, X-70, 20);
        ScrollCo.setBounds(0, Y-280, X-95, 125);
        P.setBounds(70, 40, X, 30);
        Com.setBounds(70, Y-20, 400, 20);
        tit.setBounds(0, Y-310, 1070, 30);
        

    }
    public void minimizar(){
        Botones.setBounds(0, 0, 70, 690);
        ScrollC.setBounds(0, 0, 1000, 400);
        cabG.setBounds(0, 0, 1095, 40);
        Estado.setBounds(0, 690, 70, 20);
        Lineas.setBounds(70, 690, 1025, 20);
        tit.setBounds(0, 400, 1070, 30);
        ScrollCo.setBounds(0, 430, 1070, 125);
        P.setBounds(70, 40, 1025, 30);
        Com.setBounds(70, 690, 400, 20);
        Base.setBounds(80, 130, 1000, 555);
        tituloc.setBounds(120, 80, 500, 70);
        Shape forma = new RoundRectangle2D.Double(0, 0, this.getBounds().width, this.getBounds().height, 20, 20);
                AWTUtilities.setWindowShape(this, forma);

    }
/////////////////////////////////////////
///////////////////////////////////////

    public void CabeceraMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - xx, y - xy);
    }

    public void CabeceraMousePressed(java.awt.event.MouseEvent evt) {
        xx = evt.getX();
        xy = evt.getY();
    }
    /////////////////////////////////////////////7

    
    /*///////////////////////////////////////////////
    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */////////////////////////////////////////////
    public JFrame AcercaDe() throws IOException{
        JFrame ventana=new JFrame("Acerca de");
        ventana.setLayout(null);
        ventana.getContentPane().setBackground(content);
        BufferedImage bufferedImage0 = ImageIO.read(new File("Media/LOGO.png"));
        Image image0 = bufferedImage0.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        try {
            ventana.setIconImage(image0);
        } catch (Exception e) {
        }
        JButton BC = new JButton();
        BC.setFocusable(false);
        BC.setOpaque(true);
        BC.setBorderPainted(false);
        BC.setBackground(CabeceraC);
        BufferedImage bufferedImage2 = ImageIO.read(new File("Media/CLOS.png"));
        Image image2 = bufferedImage2.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon icon2 = new ImageIcon(image2);
        BC.setIcon(icon2);
        BC.setPressedIcon(icon2);
        BC.setBounds(5, 5, 30, 30);
        BC.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
              ventana.dispose();   
            }
        });
        ventana.add(BC);
        JButton MV = new JButton();
        MV.setFocusable(false);
        MV.setOpaque(true);
        MV.setBorderPainted(false);
        MV.setBackground(CabeceraC);
        BufferedImage bufferedImage = ImageIO.read(new File("Media/MIN.png"));
        Image image = bufferedImage.getScaledInstance(22, 22, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(image);
        MV.setIcon(icon);
        MV.setPressedIcon(icon);
        MV.setBounds(40, 5, 30, 30);
        MV.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
              ventana.setState(ICONIFIED);  
            }
        });
        ventana.add(MV);
        JLabel CB = new JLabel("<HTML>Acerca De</HTML>", SwingConstants.CENTER); //Titulo del programa
        CB.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                int x = evt.getXOnScreen();
                int y = evt.getYOnScreen();

                ventana.setLocation(x - xx, y - xy);
            }
        });
        CB.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                xx = evt.getX();
                xy = evt.getY();
            }
        });
        
        JPanel cab = new JPanel();
        cab.setLayout(new GridLayout(1, 1));
        cab.setBounds(0, 0, 400, 40); //Cambia el tamaño y posicion del titulo
        cab.setBackground(CabeceraC);
        CB.setBackground(CabeceraC);
        CB.setForeground(Color.WHITE);
        CB.setFont(new Font(LetraG, Font.BOLD, 15));
        cab.add(CB); //Se agrega el titulo
        ventana.add(cab);
        JLabel Datos= new JLabel("<html><p align='center'>Tecnologico de Estudios superiores de Jocotitlan<br><br>"+
                "Carrera:<br>"+
                "Ingenieria en Sistemas Computacionales<br><br>"+
                "Asignatura:<br>"+
                "Lenguajes y Automatas II<br><br>"+
                "Alumnos:<br>"+
                "Ponce Camacho Julio<br>"+
                "Sanchez Villegas Adrian<br><br>"+
                "Grupo IC-0701</p></html>", SwingConstants.CENTER);
        Datos.setForeground(txtW);
        Datos.setBounds(0,10,400,300);
        
        Datos.setFont(new Font(LetraG, Font.BOLD, 15));
        ventana.add(Datos);
        JLabel log=new JLabel();
        BufferedImage bufferedImage22 = ImageIO.read(new File("Media/LOGO.png"));
        Image image22 = bufferedImage22.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        log.setIcon(new ImageIcon(image22));
        log.setBounds(150,260,150,150);
        ventana.add(log);
        return ventana;
    }
     public JFrame Debug() throws IOException{
        JFrame ventana=new JFrame("Depurador");
        ventana.setLayout(null);
        ventana.getContentPane().setBackground(content);
        BufferedImage bufferedImage0 = ImageIO.read(new File("Media/LOGO.png"));
        Image image0 = bufferedImage0.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        try {
            ventana.setIconImage(image0);
        } catch (Exception e) {
        }
        JButton BC = new JButton();
        BC.setFocusable(false);
        BC.setOpaque(true);
        BC.setBorderPainted(false);
        BC.setBackground(CabeceraC);
        BufferedImage bufferedImage2 = ImageIO.read(new File("Media/CLOS.png"));
        Image image2 = bufferedImage2.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon icon2 = new ImageIcon(image2);
        BC.setIcon(icon2);
        BC.setPressedIcon(icon2);
        BC.setBounds(5, 5, 30, 30);
        BC.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
              ventana.dispose();   
            }
        });
        ventana.add(BC);
        JButton MV = new JButton();
        MV.setFocusable(false);
        MV.setOpaque(true);
        MV.setBorderPainted(false);
        MV.setBackground(CabeceraC);
        BufferedImage bufferedImage = ImageIO.read(new File("Media/MIN.png"));
        Image image = bufferedImage.getScaledInstance(22, 22, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(image);
        MV.setIcon(icon);
        MV.setPressedIcon(icon);
        MV.setBounds(40, 5, 30, 30);
        MV.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
              ventana.setState(ICONIFIED);  
            }
        });
        ventana.add(MV);
        JLabel CB = new JLabel("<HTML>Dupurar programa</HTML>", SwingConstants.CENTER); //Titulo del programa
        CB.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                int x = evt.getXOnScreen();
                int y = evt.getYOnScreen();

                ventana.setLocation(x - xx, y - xy);
            }
        });
        CB.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                xx = evt.getX();
                xy = evt.getY();
            }
        });
        
        JPanel cab = new JPanel();
        cab.setLayout(new GridLayout(1, 1));
        cab.setBounds(0, 0, 800, 40); //Cambia el tamaño y posicion del titulo
        cab.setBackground(CabeceraC);
        CB.setBackground(CabeceraC);
        CB.setForeground(Color.WHITE);
        CB.setFont(new Font(LetraG, Font.BOLD, 15));
        cab.add(CB); //Se agrega el titulo
        ventana.add(cab);
        
        String [] cabezara={"Componente Lexico","Descripcion"}; //Arreglo para la cabezera de la tabla
        String [][] datos={}; //Establece un arreglo vacio para los datos
        DefaultTableModel modelo = new DefaultTableModel(datos,cabezara); //Se crea el modelo de  la tabla con la cabezera y datos previos
        JLabel titTok= new JLabel("Tabla de Tokens");
        titTok.setForeground(titulosCol);
        titTok.setFont(new Font(LetraG, Font.BOLD, 20));
        titTok.setBounds(10,60,250,30);
        ventana.add(titTok);
        JTable tok = new JTable(modelo);
        tok.setBackground(FondoAr);
        tok.setForeground(txtW);
        tok.getTableHeader().setBackground(FondoAr);
        tok.getTableHeader().setForeground(txt);
        tok.setBorder(BorderFactory.createEmptyBorder());
        JScrollPane scrollt = new JScrollPane(tok, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollt.setBackground(FondoAr);
        scrollt.setBorder(BorderFactory.createEmptyBorder());
        scrollt.setBounds(10,100,250,300);
        scrollt.getViewport().setBackground(FondoAr);
        ventana.add(scrollt);
        
        String [] cabezara2={"Tipo","Variable","Estado"}; //Arreglo para la cabezera de la tabla
        String [][] datos2={}; //Establece un arreglo vacio para los datos2
        DefaultTableModel modeloV = new DefaultTableModel(datos2,cabezara2); //Se crea el modeloV de  la tabla con la cabezera y datos2 previos
        JLabel tittV= new JLabel("Variables");
        tittV.setForeground(titulosCol);
        tittV.setFont(new Font(LetraG, Font.BOLD, 20));
        tittV.setBounds(10,400,200,30);
        ventana.add(tittV);
        JTable tV = new JTable(modeloV);
        tV.setBackground(FondoAr);
        tV.setForeground(txtW);
        tV.getTableHeader().setBackground(FondoAr);
        tV.getTableHeader().setForeground(txt);
        tV.setBorder(BorderFactory.createEmptyBorder());
        JScrollPane scrolltv = new JScrollPane(tV, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrolltv.setBackground(FondoAr);
        scrolltv.setBorder(BorderFactory.createEmptyBorder());
        scrolltv.setBounds(10,435,380,160);
        scrolltv.getViewport().setBackground(FondoAr);
        ventana.add(scrolltv);
        
        
        JLabel titInte= new JLabel("Intermedio");
        titInte.setForeground(titulosCol);
        titInte.setFont(new Font(LetraG, Font.BOLD, 20));
        titInte.setBounds(285,60,250,30);
        ventana.add(titInte);
       
        JTextArea inte = new JTextArea();
        inte.setBackground(FondoAr);
        inte.setForeground(txtW);
        inte.setCaretColor(txt);
        inte.setSelectedTextColor(new Color(30, 30, 30, 255));
        inte.setSelectionColor(txt);
        inte.setEditable(false);
        JScrollPane scrollIN = new JScrollPane(inte, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollIN.setBounds(275,100,250,300);
        scrollIN.setBorder(BorderFactory.createEmptyBorder());
        ventana.add(scrollIN);
        
        JLabel titens= new JLabel("Ensamblador");
        titens.setForeground(titulosCol);
        titens.setFont(new Font(LetraG, Font.BOLD, 20));
        titens.setBounds(550,60,250,30);
        ventana.add(titens);
        
        JTextArea ens = new JTextArea();
        ens.setBackground(FondoAr);
        ens.setForeground(txtW);
        ens.setCaretColor(txt);
        ens.setSelectedTextColor(new Color(30, 30, 30, 255));
        ens.setSelectionColor(txt);
        ens.setEditable(false);
        JScrollPane scrollEN = new JScrollPane(ens, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollEN.setBorder(BorderFactory.createEmptyBorder());
        scrollEN.setBounds(540,100,250,300);
        ventana.add( scrollEN);
        
        JLabel titES= new JLabel("Estado General");
        titES.setForeground(titulosCol);
        titES.setFont(new Font(LetraG, Font.BOLD, 20));
        titES.setBounds(420,400,250,30);
        ventana.add(titES);
        
        JTextArea ES = new JTextArea();
        ES.setBackground(FondoAr);
        ES.setForeground(txtW);
        ES.setCaretColor(txt);
        ES.setSelectedTextColor(new Color(30, 30, 30, 255));
        ES.setSelectionColor(txt);
        ES.setEditable(false);
        JScrollPane scrollES = new JScrollPane(ES, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollES.setBorder(BorderFactory.createEmptyBorder());
        scrollES.setBounds(410,435,380,160);
        ventana.add(scrollES);
        
        String auxC=Codigo.getText();
        int aux=auxC.split("\n").length;
        if(!auxC.equals("") && !auxC.matches(" *") && aux>3){
              Traduccion tr=new Traduccion();
            if(tr.ComprobadorLexico(auxC) == true && tr.ComprobarSintaxis(auxC) == true && tr.AnalizadorSemantico(auxC.split("\n")) == true) {
             for(int i=0;i<tr.cont2;i++){
                modelo.addRow(new Object[]{tr.TOKENS[i][1],tr.TOKENS[i][0]});
             }
             String optimizado = tr.optimizar(Codigo.getText().split("\n"));
              String inter= tr.CodigoIntermedio(optimizado);
              String ensam=tr.CodigoEnsamblador(inter);
              
              inte.setText(inter);
              ens.setText(ensam);
              String var[]=tr.lis.listar().split(";");
              boolean enc=false;
              for(int i=0;i<var.length;i++){
                  String vas[]=var[i].split(" ");
                  for(int j=0;j<tr.vs;j++){
                    if(vas[1].equals(tr.NOUV[j])){
                        enc=true;
                    }
                  }
                  if(enc==false)
                  modeloV.addRow(new Object[]{vas[0],vas[1],"UTILIZADA"});
                  else modeloV.addRow(new Object[]{vas[0],vas[1],"NO UTILIZADA"});
                  
              }
                 ES.setText("\t Analisis Completado\n Todo Correcto");
          }
            else{
                tr.AnalizadorSemantico(auxC.split("\n"));
                String optimizado = tr.optimizar(Codigo.getText().split("\n"));
            for(int i=0;i<tr.cont2;i++){
                modelo.addRow(new Object[]{tr.TOKENS[i][0],tr.TOKENS[i][1]});
              }
            String var[]=tr.lis.listar().split(";");
              boolean enc=false;
              for(int i=0;i<var.length;i++){
                  String vas[]=var[i].split(" ");
                  for(int j=0;j<tr.vs;j++){
                    if(vas[1].equals(tr.NOUV[j])){
                        enc=true;
                    }
                  }
                  if(enc==false)
                  modeloV.addRow(new Object[]{vas[0],vas[1],"UTILIZADA"});
                  else modeloV.addRow(new Object[]{vas[0],vas[1],"NO UTILIZADA"});
              }
              inte.setText("\n\n\tExisten errores\nNo se puede crear el codigo Intermedio");
             ens.setText("\n\n\tExisten errores\nNo se puede crear el codigo ensamblador");
              ES.setText(tr.General);
                ES.setText("\tErrores encontrados\nNo se pudo optimizar, ya que existen errores.\n");
                ES.append(tr.General);
            }
        }
        else {
            inte.setText("\n\n\tNada que hacer");
            ens.setText("\n\n\tNada que hacer");
        }
               
        return ventana;
    }
     
     public JFrame manualV() throws IOException{
         JFrame ventana=new JFrame();
        ventana.setLayout(null);
        ventana.getContentPane().setBackground(content);

        JButton BC = new JButton();
        BC.setFocusable(false);
        BC.setOpaque(true);
        BC.setBorderPainted(false);
        BC.setBackground(CabeceraC);
        BufferedImage bufferedImage2 = ImageIO.read(new File("Media/CLOS.png"));
        Image image2 = bufferedImage2.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon icon2 = new ImageIcon(image2);
        BC.setIcon(icon2);
        BC.setPressedIcon(icon2);
        BC.setBounds(5, 5, 30, 30);
        BC.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
              ventana.dispose();   
            }
        });
        ventana.add(BC);
        JButton MV = new JButton();
        MV.setFocusable(false);
        MV.setOpaque(true);
        MV.setBorderPainted(false);
        MV.setBackground(CabeceraC);
        BufferedImage bufferedImage = ImageIO.read(new File("Media/MIN.png"));
        Image image = bufferedImage.getScaledInstance(22, 22, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(image);
        MV.setIcon(icon);
        MV.setPressedIcon(icon);
        MV.setBounds(40, 5, 30, 30);
        MV.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
              ventana.setState(ICONIFIED);  
            }
        });
        ventana.add(MV);
        JLabel CB = new JLabel("<HTML>Acerca De</HTML>", SwingConstants.CENTER); //Titulo del programa
        CB.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                int x = evt.getXOnScreen();
                int y = evt.getYOnScreen();

                ventana.setLocation(x - xx, y - xy);
            }
        });
        CB.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                xx = evt.getX();
                xy = evt.getY();
            }
        });
        
        JPanel cab = new JPanel();
        cab.setLayout(new GridLayout(1, 1));
        cab.setBounds(0, 0, 800, 40); //Cambia el tamaño y posicion del titulo
        cab.setBackground(CabeceraC);
        CB.setBackground(CabeceraC);
        CB.setForeground(Color.WHITE);
        CB.setFont(new Font(LetraG, Font.BOLD, 15));
        cab.add(CB); //Se agrega el titulo
        ventana.add(cab);
                
        JLabel titens= new JLabel("Manual de Usuario");
        titens.setForeground(titulosCol);
        titens.setFont(new Font(LetraG, Font.BOLD, 20));
        titens.setBounds(250,60,250,30);
        ventana.add(titens);
        
        JTextArea ens = new JTextArea();
        ens.setBackground(FondoAr);
        ens.setForeground(txtW);
        ens.setCaretColor(txt);
        ens.setSelectedTextColor(new Color(30, 30, 30, 255));
        ens.setSelectionColor(txt);
        ens.setEditable(false);
        JScrollPane scrollEN = new JScrollPane(ens, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollEN.setBorder(BorderFactory.createEmptyBorder());
        scrollEN.setBounds(15,100,770,470);
        ens.setText("============= Sintaxis del Lenguaje ==============="
                + "\nAsignacion Inicial\n"
                + "-->bodyNumero"
                + "\t->body ->Palabra Reservada para definir inicio.\n"
                + "\t->Numero -> Tamaño del programa.\n"
                + "Metodo Principal\n"
                + "--->Principal {Procedimientos}\n"
                + "\t->Principal-> Palabra reservada para metodo principal\n"
                + "\t->Procedimiento-> Instrucciones Internas\n"
                + "Definicion de variables\n"
                + "---> (Int|float|char) nombre;\n"
                + "---> (Int|float|char) nombre1,nombre2,nombre3;\n"
                + "\t->(Int|float|char)-> Tipo de dato\n"
                + "\t->nombren-> Identificador de la variable\n"
                + "Asignacion\n"
                + "--->var:=(var|numero);\n"
                + "\t->var->nombre de la variable \n"
                + "\t->:=-> Signo de asignacion \n"
                + "\t->numero->valor numerico \n"
                + "Salida\n"
                + "--->WRITE(TEXTO);\n"
                + "--->WRITE(var);\n"
                + "\t->TEXTO->Sentencia entre comillas \n"
                + "\t->var->nombre de variable\n"
                + "Entrada\n"
                + "--->READ(var);"
                + "\t->var->nombre de variable\n"
                + "Condicion SI\n"
                + "---> SI var CMP (var|num) entonces \n--->OPERACION\n--->De lo contrario\nOPERACION\n-->FIN SI;\n "
                + "\t->var->nombre de variable\n"
                + "\t->CMP->operador de comparacion (>|<|==)\n"
                + "\t->OPERACION-> Asigancion de dato\n"
                + "Ciclo\n"
                + "--->FOR(ASIGNACION; VAR CMP VAR ; OPERACION ARITMETICA)\n---> { SUBPROC }\n"
                + "\t->var->nombre de variable\n"
                + "\t->CMP->operador de comparacion (>|<|==)\n"
                + "\t->SUBPROC -> Instrucciones interiores (No soporta anidaciones) \n"
                + "Operacion Aritmetica\n"
                + "--> var:=(var|num) OPERADOR (var|num)\n"
                + "\t->var->Nombre de la variable\n"
                + "\t->num-> valor numerico\n"
                + "\t->OPERADOR-> operador aritmetico (+|-|*|/)\n"
               
        );
        
        ventana.add( scrollEN);
        
        
         return ventana;
     }
     String Letra="Arial", ColorF="OBSCURO";
     public JFrame Ajustes() throws IOException{
        JFrame ventana=new JFrame("Ajustes");
        ventana.setLayout(null);
        ventana.getContentPane().setBackground(content);
        BufferedImage bufferedImage0 = ImageIO.read(new File("Media/LOGO.png"));
        Image image0 = bufferedImage0.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        try {
            ventana.setIconImage(image0);
        } catch (Exception e) {
        }
        JButton BC = new JButton();
        BC.setFocusable(false);
        BC.setOpaque(true);
        BC.setBorderPainted(false);
        BC.setBackground(CabeceraC);
        BufferedImage bufferedImage2 = ImageIO.read(new File("Media/CLOS.png"));
        Image image2 = bufferedImage2.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon icon2 = new ImageIcon(image2);
        BC.setIcon(icon2);
        BC.setPressedIcon(icon2);
        BC.setBounds(5, 5, 30, 30);
        BC.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
              ventana.dispose();   
            }
        });
        ventana.add(BC);
        JButton MV = new JButton();
        MV.setFocusable(false);
        MV.setOpaque(true);
        MV.setBorderPainted(false);
        MV.setBackground(CabeceraC);
        BufferedImage bufferedImage = ImageIO.read(new File("Media/MIN.png"));
        Image image = bufferedImage.getScaledInstance(22, 22, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(image);
        MV.setIcon(icon);
        MV.setPressedIcon(icon);
        MV.setBounds(40, 5, 30, 30);
        MV.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
              ventana.setState(ICONIFIED);  
            }
        });
        ventana.add(MV);
        JLabel CB = new JLabel("<HTML>Ajustes</HTML>", SwingConstants.CENTER); //Titulo del programa
        CB.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                int x = evt.getXOnScreen();
                int y = evt.getYOnScreen();

                ventana.setLocation(x - xx, y - xy);
            }
        });
        CB.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                xx = evt.getX();
                xy = evt.getY();
            }
        });
        
        JPanel cab = new JPanel();
        cab.setLayout(new GridLayout(1, 1));
        cab.setBounds(0, 0, 800, 40); //Cambia el tamaño y posicion del titulo
        cab.setBackground(CabeceraC);
        CB.setBackground(CabeceraC);
        CB.setForeground(Color.WHITE);
        CB.setFont(new Font(LetraG, Font.BOLD, 15));
        cab.add(CB); //Se agrega el titulo
        ventana.add(cab);
        JLabel Datos= new JLabel("<html>Configuracion general</html>", SwingConstants.CENTER);
        Datos.setFont(new Font(LetraG, Font.BOLD, 20));
        Datos.setForeground(titulosCol);
        Datos.setBounds(0,10,800,100);
        Datos.setFont(new Font(LetraG, Font.BOLD, 15));
        ventana.add(Datos);
        JRadioButton Letra1 = new JRadioButton("Arial",true);
        Letra1.setBackground(new Color(205, 207, 228));
        Letra1.addActionListener((ActionEvent e) -> {
            Letra="Arial";
        });
        Letra1.setForeground(Color.BLACK);
        JRadioButton Letra21 = new JRadioButton("Century Gothic");
        Letra21.addActionListener((ActionEvent e) -> {
            Letra="Century Gothic";
        });
        Letra21.setBackground(new Color(205, 207, 228));
        Letra21.setForeground(Color.BLACK);
        JRadioButton Letra2 = new JRadioButton("Serif");
        Letra2.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Letra="Serif";
            }
            });
        Letra2.setBackground(new Color(205, 207, 228));
        Letra2.setForeground(Color.BLACK);
        JRadioButton Letra3= new JRadioButton("Calibri");
        Letra3.setBackground(new Color(205, 207, 228));
        Letra3.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Letra="Calibri";
            }
            });
        Letra3.setForeground(Color.BLACK);
        ButtonGroup grupo2= new ButtonGroup();
        
        grupo2.add(Letra3);	grupo2.add(Letra2);
        grupo2.add(Letra1); 	grupo2.add(Letra21);
    
        JLabel Datos2 = new JLabel("<html>Tipo de Letra</html>", SwingConstants.LEFT);
        Datos2.setFont(new Font(LetraG, Font.BOLD, 20));
        Datos2.setForeground(titulosCol);
        Datos2.setBounds(50,50,800,100);
        Datos2.setFont(new Font(LetraG, Font.BOLD, 15));
        ventana.add(Datos2);
        
        JPanel Bases = new JPanel();
        Bases.setLayout(new GridLayout(2,2));
        Bases.setBackground(FondoAr);
        Letra1.setBackground(FondoAr);
        Letra1.setForeground(txtW);
        Letra1.setFont(new Font(LetraG, Font.BOLD, 15));
        Letra1.setFocusable(false);
        Letra1.setOpaque(true);
        Letra1.setBorderPainted(false);
        
         Letra2.setBackground(FondoAr);
        Letra2.setForeground(txtW);
        Letra2.setFont(new Font(LetraG, Font.BOLD, 15));
        Letra2.setFocusable(false);
        Letra2.setOpaque(true);
        Letra2.setBorderPainted(false);
        
        Letra21.setBackground(FondoAr);
        Letra21.setForeground(txtW);
        Letra21.setFocusable(false);
        Letra21.setOpaque(true);
        Letra21.setBorderPainted(false);
        Letra21.setFont(new Font(LetraG, Font.BOLD, 15));
        
        Letra3.setBackground(FondoAr);
        Letra3.setForeground(txtW);
        Letra3.setFocusable(false);
        Letra3.setOpaque(true);
        Letra3.setBorderPainted(false);
        Letra3.setFont(new Font(LetraG, Font.BOLD, 15));
        
        Bases.add(Letra1);
        Bases.add(Letra2);
        Bases.add(Letra21);
        Bases.add(Letra3);
        Bases.setBounds(20,130,770,100);
        ventana.add(Bases);    
    
         JRadioButton Temas1 = new JRadioButton("Tema Obscuro",true);
        Temas1.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                Letra="OBSCURO";
            }
            });
        Temas1.setBackground(new Color(205, 207, 228));
        Temas1.setForeground(Color.BLACK);
        JRadioButton Temas2= new JRadioButton("Tema Claro");
        Temas2.setBackground(new Color(205, 207, 228));
        Temas2.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                ColorF="BLANCO";
            }
            });
        Temas2.setForeground(Color.BLACK);
        ButtonGroup grupo= new ButtonGroup();
        
        Temas1.setBackground(FondoAr);
        Temas1.setForeground(txtW);
        Temas1.setFocusable(false);
        Temas1.setOpaque(true);
        Temas1.setBorderPainted(false);
        Temas1.setFont(new Font(LetraG, Font.BOLD, 15));
        
        Temas2.setBackground(FondoAr);
        Temas2.setForeground(txtW);
        Temas2.setFocusable(false);
        Temas2.setOpaque(true);
        Temas2.setBorderPainted(false);
        Temas2.setFont(new Font(LetraG, Font.BOLD, 15));
        
        grupo.add(Temas1);
        grupo.add(Temas2); 	
    
        JLabel Datos3 = new JLabel("<html>Colores del sistema</html>", SwingConstants.LEFT);
        Datos3.setFont(new Font(LetraG, Font.BOLD, 20));
        Datos3.setForeground(titulosCol);
        Datos3.setBounds(50,250,800,100);
        Datos3.setFont(new Font(LetraG, Font.BOLD, 15));
        ventana.add(Datos3);
        
        JPanel Bases2 = new JPanel();
        Bases2.setLayout(new GridLayout(1,2));
        Bases2.setBackground(Fondo);
        Bases2.add(Temas1);
        Bases2.add(Temas2);
        Bases2.setBounds(20,350,770,100);
        ventana.add(Bases2);    
        
        JButton aplic = new JButton("Aplicar cambios");
        aplic.setFocusable(false);
        aplic.setOpaque(true);
        aplic.setBorderPainted(false);
        aplic.setBackground(FondoAr);
        aplic.setForeground(txt); //Establece el color del texto
        aplic.setFont(new Font(LetraG, Font.BOLD, 12)); //Establece el tipo de fuente
        aplic.setBounds(300, 500, 200, 40);
        aplic.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                     JFrame ventana=new JFrame("Atención");
        ventana.setLayout(null);
        ventana.getContentPane().setBackground(content);
        BufferedImage bufferedImage0 = null;
            try {
                bufferedImage0 = ImageIO.read(new File("Media/LOGO.png"));
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        Image image0 = bufferedImage0.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        try {
            ventana.setIconImage(image0);
        } catch (Exception ex) {
        }
        JButton BC = new JButton();
        BC.setFocusable(false);
        BC.setOpaque(true);
        BC.setBorderPainted(false);
        BC.setBackground(CabeceraC);
        BufferedImage bufferedImage2 = null;
            try {
                bufferedImage2 = ImageIO.read(new File("Media/CLOS.png"));
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        Image image2 = bufferedImage2.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon icon2 = new ImageIcon(image2);
        BC.setIcon(icon2);
        BC.setPressedIcon(icon2);
        BC.setBounds(5, 5, 30, 30);
        BC.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
              ventana.dispose();   
            }
        });
        ventana.add(BC);
        JButton MV = new JButton();
        MV.setFocusable(false);
        MV.setOpaque(true);
        MV.setBorderPainted(false);
        MV.setBackground(CabeceraC);
        BufferedImage bufferedImage = null;
            try {
                bufferedImage = ImageIO.read(new File("Media/MIN.png"));
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        Image image = bufferedImage.getScaledInstance(22, 22, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(image);
        MV.setIcon(icon);
        MV.setPressedIcon(icon);
        MV.setBounds(40, 5, 30, 30);
        MV.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
              ventana.setState(ICONIFIED);  
            }
        });
        ventana.add(MV);
        JLabel CB = new JLabel("<HTML>Reinicio Requerido</HTML>", SwingConstants.CENTER); //Titulo del programa
        CB.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                int x = evt.getXOnScreen();
                int y = evt.getYOnScreen();

                ventana.setLocation(x - xx, y - xy);
            }
        });
        CB.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                xx = evt.getX();
                xy = evt.getY();
            }
        });
        
        JPanel cab = new JPanel();
        cab.setLayout(new GridLayout(1, 1));
        cab.setBounds(0, 0, 400, 40); //Cambia el tamaño y posicion del titulo
        cab.setBackground(CabeceraC);
        CB.setBackground(CabeceraC);
        CB.setForeground(Color.WHITE);
        CB.setFont(new Font(LetraG, Font.BOLD, 15));
        cab.add(CB); //Se agrega el titulo
        ventana.add(cab);
        JLabel Datos= new JLabel("<html>Para notar el cambio necesita reiniciar, si va a reiniciar procure guardar su trabajo previamente.</html>", SwingConstants.CENTER);
        Datos.setForeground(txtW);
        Datos.setBounds(10,50,390,100);
        
        Datos.setFont(new Font(LetraG, Font.BOLD, 12));
        ventana.add(Datos);
        JButton Rein = new JButton("Reiniciar");
        Rein.setFocusable(false);
        Rein.setOpaque(true);
        Rein.setBorderPainted(false);
        Rein.setBackground(FondoAr);
        Rein.setForeground(txt); //Establece el color del texto
        Rein.setFont(new Font(LetraG, Font.BOLD, 12)); //Establece el tipo de fuente
        Rein.setBounds(100, 150, 100, 40);
        Rein.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
               File config = new File(".config.txt");
                // Si el archivo no existe es creado
                if (!config.exists()) {
                    try {
                        config.createNewFile();
                          BufferedWriter BWIN = null;
                                try {
                                    BWIN = new BufferedWriter(new FileWriter(config));
                                } catch (IOException ex) {
                                    System.out.println("Error IN");
                                }
                                try {
                                   BWIN.append(ColorF+"\n");
                                   BWIN.write(Letra);

                        } catch (IOException ex) {
                            System.out.println("NO Escrito INTER");
                            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                        }
                                BWIN.close();
                    } catch (IOException ex) {
                        System.out.println("Error: "+ex);
                            }
                }
                        String valor;
                        if(e.getSource()==Tema2)valor= "BLANCO";
                        else valor= "OBSCURO";
                        System.out.println(valor);
                          BufferedWriter BWIN = null;
                                try {
                                    BWIN = new BufferedWriter(new FileWriter(config));
                                } catch (IOException ex) {
                                    System.out.println("Error IN");
                                }
                                try {
                                    BWIN.append(ColorF+"\n");
                                    BWIN.write(Letra);
                                   

                        } catch (IOException ex) {
                            System.out.println("NO Escrito INTER");
                            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                        }
            try {
                BWIN.close();
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            } 
                
              ventana.dispose();
              dispose();
              Principal p= new Principal();
                try {
                    p.main(null);
                } catch (IOException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.gc();
            }
        });
        ventana.add(Rein);
        JButton man = new JButton("Mas tarde");
        man.setFocusable(false);
        man.setOpaque(true);
        man.setBorderPainted(false);
        man.setBackground(FondoAr);
        man.setForeground(txt); //Establece el color del texto
        man.setFont(new Font(LetraG, Font.BOLD, 12)); //Establece el tipo de fuente
        man.setBounds(210, 150, 100, 40);
        man.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
              ventana.dispose();   
            }
        });
        ventana.add(man);
        ventana.setSize(400, 200);
        ventana.setUndecorated(true);
        Shape forma = new RoundRectangle2D.Double(0, 0, ventana.getBounds().width, ventana.getBounds().height, 20, 20);
        AWTUtilities.setWindowShape(ventana, forma);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.toFront();
        ventana.requestFocus();
        ventana.setAlwaysOnTop(true);
        ventana.setVisible(true);
            }});
        ventana.add(aplic);
            
        return ventana;
    }

}
