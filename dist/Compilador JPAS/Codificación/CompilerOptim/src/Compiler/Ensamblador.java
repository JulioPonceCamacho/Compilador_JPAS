package Compiler;

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class Ensamblador {

    String nombreEn,rutaEn;
    int valor = 0, bander;
    int s = 0, cont = 0, ini = 0;
    nodo1 tabla[] = new nodo1[61];
    int loc = 0;
    int iii = 0;
    nodo Tabla[] = new nodo[1000];
    tabop2 A[] = new tabop2[1000];
    tabsim1 B[] = new tabsim1[1000];
    data der[] = new data[1000];
    tabop Table[] = new tabop[61];

    void inicializa1() {
        for (int i = 0; i < 61; i++) {
            tabla[i] = new nodo1();
            tabla[i].operador1 = " ";
            Table[i] = new tabop();
            Table[i].opc = " ";
            tabla[i].codop1 = 0;
            tabla[i].ptrsig = null;
            Table[i].cod = " ";
        }
    }

    void inicializa() {
        for (int i = 0; i < 1000; i++) {
            Tabla[i] = new nodo();
            Tabla[i].operador = " ";
            Tabla[i].etiqueta = " ";
            Tabla[i].codop = " ";
            A[i] = new tabop2();
            A[i].cod = " ";
            A[i].opc = " ";
            B[i] = new tabsim1();
            B[i].etiq = " ";
            B[i].dir = 0;
            der[i]=new data();
            der[i].opc="";
            der[i].cod="";
            der[i].etiq="";
            der[i].dir=0;
        }
    }

    int principal(String ruta, String nombre) throws IOException {
        nombreEn=nombre;
        rutaEn=ruta;
        File arch1 = new File(rutaEn+"tabop.txt");
        // Si el archivo no existe es creado
        if (arch1.exists()) {
            arch1.delete();
        }
        arch1.createNewFile();

        File arch4 = new File(rutaEn+"tabsim.txt");
        // Si el archivo no existe es creado
        if (arch4.exists()) {
            arch4.delete();
        }
        arch4.createNewFile();
        //arch4.close();

        paso1();
        paso2();

        return 0;
    }

    int paso1() throws FileNotFoundException, IOException/*Funcion del paso 1*/ {
        int dir_ini = 0;
        bander = 1;
        inicializa();
        inicializa1();
        Abrir("instrucciones.txt");
        cargar();
        int v_codop;
        int contloc = 0;
        loc = 0;
        if (Tabla[loc].codop.equals("START")) {
            v_codop = Integer.parseInt(Tabla[loc].operador);
            dir_ini = Integer.parseInt(Tabla[loc].operador);
            contloc += v_codop;
        } else {
            contloc = 0;
        }
        while (!Tabla[loc].codop.equals("END")) {

            if (Consulta(Tabla[loc].codop) == 1) //-->REVISAR
            {
                contloc = contloc + 3;
            }
            if (Tabla[loc].codop.equals("WORD") || Tabla[loc].codop.equals("RESW") || Tabla[loc].codop.equals("RESB")
                    || Tabla[loc].codop.equals("END") || Tabla[loc].codop.equals("BYTE") || Tabla[loc].codop.equals("START") ) {
                contloc = contloc - 3;
            }
            if (Consulta(Tabla[loc].codop) == 0 ) {
                
                System.out.println("Operador erroneo: "+Tabla[loc].codop);
                bander = 0;
                return 0;
            }
            if (Tabla[loc].codop.equals("WORD")) {
                contloc = contloc + 3;
            }
            if (Tabla[loc].codop.equals("RESW")) {
                contloc = contloc + (3 * Integer.parseInt(Tabla[loc].operador));
            }
            if (Tabla[loc].codop.equals("RESB")) {
                contloc = contloc + Integer.parseInt(Tabla[loc].operador);
            }
            if (Tabla[loc].codop.equals("BYTE")) {
                contloc = contloc + Integer.parseInt(Tabla[loc].operador);
            }
            if (!Tabla[loc + 1].etiqueta.equals(" ")) {
                Altatabsim(Tabla[loc + 1].etiqueta, contloc);
            }
            loc++;
        }
        Consulta_todo();
        ini = dir_ini;
        cont = contloc;
        System.out.println("RESULTADOS");
        System.out.println("Direccion inicial : " + dir_ini);
        System.out.println("Contador local : " + contloc);
        System.out.println("Longitud del programa : " + (contloc-dir_ini));

//arch.close(); //-->
        return 0;
    }

    int paso2() throws FileNotFoundException, IOException/*implementacion del paso 2*/ {
        InputStream input2 = new FileInputStream(rutaEn+"tabop.txt");
        Scanner arch2 = new Scanner(input2);
        InputStream input3 = new FileInputStream(rutaEn+"tabsim.txt");
        Scanner arch3 = new Scanner(input3);

        String longitud;
        String longit;
        String l, call, l1;
        l = " ";
        call = "0";
        l1 = " ";
        int call1 = 0;
        int inicial = 0;
        int bytes = 0;

        File arch = new File(rutaEn+"objeto.txt");
// Si el archivo no existe es creado
        if (!arch.exists()) {
            arch.createNewFile();
        }
        FileWriter fw = new FileWriter(arch);
        BufferedWriter bw = new BufferedWriter(fw);
        File exe = new File(rutaEn+"BIN.bin");
// Si el archivo no existe es creado
        if (!arch.exists()) {
            arch.createNewFile();
        }
        FileWriter fwx = new FileWriter(exe);
        BufferedWriter bwx = new BufferedWriter(fwx);
        if (Tabla[0].codop.equals("START")) {
            bw.write("H" + "^" + Tabla[0].etiqueta + "^" + ini + "^" + (cont - ini) + "\n");
            bwx.write("H" + "^" + Tabla[0].etiqueta + "^" + ini + "^" + Integer.toBinaryString(cont - ini) + "\n");
            bytes += ini;
        }
        if (bander == 0) {
            return 0;
        }
        loc--;

        while (!Tabla[inicial].codop.equals("END") && inicial != loc && inicial < 1000) {
//Se crea para concatenar en caso de que sea WORD o BYTE
            if (Tabla[inicial].codop.equals("WORD") || Tabla[inicial].codop.equals("BYTE")) {
                longit = "^";
                longitud=Tabla[inicial].operador;
                bw.write("T" + "^" + bytes + "^" + "1" + "^" + longitud + "\n");
                bwx.write("T" + "^" + Integer.toBinaryString(bytes) + "^" + "1" + "^" + Integer.toBinaryString(Integer.parseInt(longitud)) + "\n");
                bytes += 8;
            } else {
                longitud = " ";
                longit = " ";
            }
            int ex = 0;
            //Verificacion de los operadores
            for (int i = 0; i < 1000; i++) {
                if (validatabop(Tabla[inicial + 1].codop) == 1 && Tabla[inicial + 1].codop.equals(der[i].opc)) {
                    l = der[i].cod;
                    call = der[i].opc;
                }
                if(Tabla[inicial + 1].codop.equals("MOV") && Tabla[inicial+1].codop.equals(der[i].opc)){
                    l=der[i].cod;
                    call=der[i].opc;
                }
                if(Tabla[inicial + 1].codop.equals("CMP") && Tabla[inicial+1].codop.equals(der[i].opc)){
                    l=der[i].cod;
                    call=der[i].opc;
                }
                if(Tabla[inicial + 1].codop.equals("LEA") && Tabla[inicial+1].codop.equals(der[i].opc)){
                    l=der[i].cod;
                    call=der[i].opc;
                }
                if(Tabla[inicial + 1].codop.equals("ADD") && Tabla[inicial+1].codop.equals(der[i].opc)){
                    l=der[i].cod;
                    call=der[i].opc;
                }
                if(Tabla[inicial + 1].codop.equals("MUL") && Tabla[inicial+1].codop.equals(der[i].opc)){
                    l=der[i].cod;
                    call=der[i].opc;
                }
                if(Tabla[inicial + 1].codop.equals("DIV") && Tabla[inicial+1].codop.equals(der[i].opc)){
                    l=der[i].cod;
                    call=der[i].opc;
                }
                if(Tabla[inicial + 1].codop.equals("SUB") && Tabla[inicial+1].codop.equals(der[i].opc)){
                    l=der[i].cod;
                    call=der[i].opc;
                }
                
                if (Tabla[inicial + 1].codop.equals("WORD") || Tabla[inicial + 1].codop.equals("BYTE") || Tabla[inicial + 1].codop.equals("END")) {
                    ex = 1;
                    break;
                }
            }
            //Verificacion de los simbolos, para proceder a ensamblarlos
            if (ex == 0) {
                for (int i = 0; i < 1000; i++) {
                    if (validatabsim(Tabla[inicial + 1].operador) == 1 && Tabla[inicial + 1].operador.equals(der[i].etiq)) {
                        l1 = der[i].etiq;
                        call1 = der[i].dir;
                    }
                    if (Tabla[inicial + 1].codop.equals("WORD") || Tabla[inicial + 1].codop.equals("BYTE") || Tabla[inicial + 1].codop.equals("END")) {
                        break;
                    }
                }
                bw.write("T" + "^" + bytes + "^" + "1" + "^" + l + call1 + "\n");
                if(l.equals(" "))
                bwx.write("T" + "^" + Integer.toBinaryString(bytes) + "^" + "1" + "^" + l+ Integer.toBinaryString(call1) + "\n");
                else  bwx.write("T" + "^" + Integer.toBinaryString(bytes) + "^" + "1" + "^" + Integer.toBinaryString(Integer.parseInt(l.replace(" ",""))) + Integer.toBinaryString(call1) + "\n");
                bytes += 8;
            }
            longitud = " ";
            l = " ";
            call = "0";
            l1 = " ";
            call1 = 0;
            inicial++;
        }
        if (Tabla[loc + 1].codop.equals("END")) {
            bw.write("E" + "^" + ini);
            bwx.write("E" + "^" + Integer.toBinaryString(ini));
        }
        bwx.close();
        bw.close();
        return 0;
    }

    void Abrir(String prog) throws FileNotFoundException/*Carga de todo el archivo de instrucciones de operadores*/ {
        String v_operador;
        int v_codop;
        int bandera = 0;
        nodo1 aux;
        nodo1 p;
        InputStream input = new FileInputStream(prog);
        Scanner arch = new Scanner(input);
        while (arch.hasNext()) {
          String auxS=arch.nextLine();
          String a[]=auxS.split(" ");
          v_operador = a[0];
          v_codop = Integer.parseInt(a[1]);

            valor = hash(v_operador);
            bandera = valida(v_operador);
            if (bandera == 0) {

                if (tabla[valor].operador1.equals(" ") /*&& tabla[valor].operador1 != 0*/) {
                    tabla[valor].operador1 = v_operador;
                    tabla[valor].codop1 = v_codop;
                } else {
                    if (tabla[valor].ptrsig == null) {
                        aux = new nodo1();
                        aux.operador1 = v_operador;
                        aux.codop1 = v_codop;
                        aux.ptrsig = null;
                        tabla[valor].ptrsig = aux;
                    } else {
                        p = tabla[valor].ptrsig;
                        while (p.ptrsig != null) {
                            p = p.ptrsig;
                        }
                        aux = new nodo1();
                        aux.operador1 = v_operador;
                        aux.codop1 = v_codop;
                        aux.ptrsig = null;
                        p.ptrsig = aux;
                    }
                }
            }

        }

    }

    int valida(String cad)/*Se verifica si el elemento ya existe*/ {
        int num = 0;
        nodo1 p;
        num = hash(cad);
        if (tabla[num].operador1.equals(cad)) {
            return 1;
        } else {
            p = tabla[num].ptrsig;
            while (p != null) {
                if (p.operador1.equals(cad)) {
                    return 1;
                }
                p = p.ptrsig;
            }
        }
        num = 0;
        return 0;
    }

    int hash(String cad)
{
    int cont = 0;
        for (int i = 0; i < cad.length(); i++) {
            if (cad.charAt(i) != 'a') {
                if (cad.charAt(i)!= 'e') {
                    if (cad.charAt(i) != 'i') {
                        if (cad.charAt(i) != 'o') {
                            if (cad.charAt(i) != 'u') {
                                cont++;
                            }
                        }
                    }
                }
            }
        }

        return cont;
    }

    int Consulta(String z)/*Se verifica si el operador existe y se hacen las excepciones correspondientes*/ {
        String v_operador;
        int valor;
        v_operador = z;
        valor = valida(v_operador);
        if (valor == 1 || z.equals("WORD") || z.equals("RESW") || z.equals("RESB")
                || z.equals("END") || z.equals("BYTE") || z.equals("START")  || z.equals("LOB")) {
            return 1;
        } else {
            return 0;
        }
    }

    void Consulta_todo() throws FileNotFoundException, IOException {
        String cad2;
        String cad3;

        InputStream input = new FileInputStream("instrucciones.txt");
        Scanner ins = new Scanner(input);

        InputStream input2 = new FileInputStream(rutaEn+"tabop.txt");
        Scanner in = new Scanner(input2);

        int h = 0;
        while (ins.hasNext()) {
            String aux = ins.nextLine(); //--> REVISAR ins>>cad2>>cad3;
            aux=aux.replaceAll("  +","");
            String a[]=aux.split(" ");
            cad2 = a[0];
            cad3 = a[1];

            Table[h].opc = cad2;
            Table[h].cod = cad3;
            h++;
        }
        for (int i = 0; i <= loc; i++) {
            for (s = 0; s < 59; s++) {
                if (Table[s].opc.equals(Tabla[i].codop)) {
                    Altatabop(Table[s].opc, Table[s].cod);
                }

            }
        }
    }

    void valit() throws FileNotFoundException/*Funcion que permite verificar si existe una direccion inicial valida*/ {
        InputStream input = new FileInputStream(rutaEn+nombreEn);
        Scanner arch = new Scanner(input);
        int i = 0, o = 0;
        String v_operador="";
        String ptrtoken[];
        for (int k = 0; k < 30; k++) {
            v_operador += " ";
        }
        v_operador = arch.nextLine();

        int ex = 0;
        for (int k = 0; k < v_operador.length(); k++) {
            if (v_operador.charAt(k) == '.') {
                ex = 1;
                break;
            }
            if (v_operador.charAt(k) == ' ') {
                i++;
            }
            if (v_operador.equals("")) {
                ex = 1;
                break;
            }
        }
        if (ex == 0) {
            ptrtoken = v_operador.split(" ");

            while (o < ptrtoken.length) {
                if (o == 0) {
                    Tabla[0].etiqueta = ptrtoken[o];
                }
                if (o == 1) {
                    Tabla[0].codop = ptrtoken[o];
                }
                if (o == 2) {
                    Tabla[0].operador = ptrtoken[o];
                }
                if (o + 1 >= ptrtoken.length && o < 2) {
                    Tabla[0].operador = "0";
                }
      		o++;
            }
            o = 0;
        }
//1//;
    }

    void cargar() throws FileNotFoundException, IOException {/*Se crea este metodo para que se carguen a la estructura de comparacion que se usara en el paso1*/
        valit();
        InputStream input = new FileInputStream(rutaEn+nombreEn);
        Scanner arch = new Scanner(input);

        File ar = new File(rutaEn+"ensam1.txt");
        // Si el archivo no existe es creado
        if (!ar.exists()) {
            ar.createNewFile();
        }
        FileWriter fw = new FileWriter(ar);
        BufferedWriter arch1 = new BufferedWriter(fw);

    int i = 0, o = 0, dir = 0;
        String v_operador="";
        String ptrtoken[];
        for (int k = 0; k < 30; k++) {
            v_operador += " ";
        }

        while (arch.hasNext()) {
            int ex = 0;
            v_operador = arch.nextLine();

            if (Tabla[dir].operador.equals("0")) {
                ex = 1;
            }
            if(ex==0){
            for (int k = 0; k < v_operador.length(); k++) {
                if (v_operador.charAt(k) == '.') {
                    ex = 1;
                    break;
                }
                if (v_operador.charAt(k) == ' ') {
                    i++;
                }
                if (v_operador.equals("")) {
                    ex = 1;
                    break;
                }
            }
           }
            if (ex == 0) {
                v_operador=v_operador.replaceAll(" +"," ");
                ptrtoken = v_operador.split(" ");
                while (o < ptrtoken.length) {
                    if (o == 0) {
                        Tabla[dir].etiqueta = ptrtoken[o];
                    }
                    if (o == 1) {
                        Tabla[dir].codop = ptrtoken[o];
                    }
                    if (o == 2) {
                        Tabla[dir].operador = ptrtoken[o];
                      }

    	                 if (o + 1 >= ptrtoken.length && o < 2) {
                        Tabla[dir].operador = Tabla[dir].codop;
                        Tabla[dir].codop = Tabla[dir].etiqueta;
                        Tabla[dir].etiqueta = " ";
                    }
                    o++;
                }
                o = 0;
                dir++;
            }
        }
        System.out.println("\tCODIGO FUENTE A TRATAR POR MEDIO DEL ENSAMBLADOR HIPOTETICO SIC/XE");
        System.out.println();
        for (int k = 0; k <= dir; k++) {
            arch1.write(Tabla[k].etiqueta + "        " + Tabla[k].codop + "         " + Tabla[k].operador + "\n");
            System.out.println(Tabla[k].etiqueta + "  " + Tabla[k].codop + "  " + Tabla[k].operador);
        }
        arch1.close();
    }

    void cargar2() throws FileNotFoundException {
        InputStream input = new FileInputStream(rutaEn+"ensam1.txt");
    Scanner arch = new Scanner(input);
        int i = 0, o = 0, dir = 0;
        String v_operador="";
        String ptrtoken[];
        for (int k = 0; k < 30; k++) {
            v_operador += " ";
        }

        while (arch.hasNext()) {
            int ex = 0;
            v_operador = arch.nextLine();
            for (int k = 0; k < v_operador.length(); k++) {
                if (v_operador.charAt(k) == '.') {
                    ex = 1;
                    break;
                }
                if (v_operador.charAt(k) == ' ') {
                    i++;
                }
                if (v_operador.equals("")) {
                    ex = 1;
                    break;
                }
            }

            if (ex == 0) {
                ptrtoken = v_operador.split(" ");
                while (o < ptrtoken.length) {
                    if (o == 0) {
                        Tabla[dir].etiqueta = ptrtoken[o];
                    }
                    if (o == 1) {
                        Tabla[dir].codop = ptrtoken[o];
                    }
                    if (o == 2) {
                        Tabla[dir].operador = ptrtoken[o];
                    }
                    if (o + 1 == ptrtoken.length && o<2 ) {
                        Tabla[dir].operador = Tabla[dir].codop;
                        Tabla[dir].codop = Tabla[dir].etiqueta;
                        Tabla[dir].etiqueta = " ";
                    }
                    o++;
                }
                o = 0;
                dir++;
            }
        }
    }

    int hashtabsim(String cad)/*Asignacion de la posicion de un elemento*/ {
        int cont = 0;
        for (int i = 0; i < cad.length(); i++) {
            if (cad.charAt(i) != 'a') {
                if (cad.charAt(i)!= 'e') {
                    if (cad.charAt(i) != 'i') {
                        if (cad.charAt(i) != 'o') {
                            if (cad.charAt(i) != 'u') {
                                cont++;
                            }
                        }
                    }
                }
            }
        }
        return cont;
    }
    int acv = 1;

    void Altatabsim(String aa, int bb) throws IOException {
      if(!aa.equals("")){
        int bandera = 0;
        tabsim1 aux;
        int var;
        tabsim1 p;
        File a = new File(rutaEn+"tabsim.txt");
        FileWriter fw = new FileWriter(a,true);
        BufferedWriter arch2 = new BufferedWriter(fw);
        valor = hashtabsim(aa);
        bandera = validatabsim(aa);
        if (bandera == 0) {
            arch2.append(aa + "         " + bb + "\n");
            der[acv].etiq = aa;
            der[acv].dir = bb;
            acv++;
            if (B[valor].etiq.equals(" ")) {
                B[valor].etiq = aa;
                B[valor].dir = bb;
            } else {
                if (B[valor].ptrsig == null)/*Valores agreagdos a lista en caso de colisiones*/ {
                    aux = new tabsim1();
                    aux.etiq = aa;
                    aux.dir = bb;
                    aux.ptrsig = null;
                    B[valor].ptrsig = aux;
                } else {
                    p = B[valor].ptrsig;
                    while (p.ptrsig != null) {
                        p = p.ptrsig;
                    }
                    aux = new tabsim1();
                    aux.etiq = aa;
                    aux.dir = bb;
                    aux.ptrsig = null;/*Se deja apuntando nulo una vez que ha terminado de agragar un dato*/
                    p.ptrsig = aux;
                }
            }
        }
        arch2.close();
    }}

    int validatabsim(String cad)/*Se verifica si el elemento ya existe*/ {
        int num = 0;
        tabsim1 p;
        num = hashtabsim(cad);
        if (B[num].etiq.equals(cad)) {
            return 1;
        } else {
            p = B[num].ptrsig;
            while (p != null) {
                if (p.etiq.equals(cad)) {
                    return 1;
                }
                p = p.ptrsig;
            }
        }
        num = 0;
        return 0;
    }

    int hashtabop(String cad)/*Asignacion de la posicion de un elemento*/ {
        int cont = 0;
        for (int i = 0; i < cad.length(); i++) {
            if (cad.charAt(i) != 'a') {
                if (cad.charAt(i)!= 'e') {
                    if (cad.charAt(i) != 'i') {
                        if (cad.charAt(i) != 'o') {
                            if (cad.charAt(i) != 'u') {
                                cont++;
                            }
                        }
                    }
                }
            }
        }
        return cont;
    }

    void Altatabop(String cc, String dd) throws IOException/*Se da de alta los simbolos de la tabla de operadores*/ {
        int bandera = 0;
        tabop2 aux;
        int var;
        tabop2 p;

        File a = new File(rutaEn+"tabop.txt");
        FileWriter fw = new FileWriter(a,true);
        BufferedWriter arch3 = new BufferedWriter(fw);

        bandera = validatabop(cc);
        if (bandera == 0) {
            arch3.append(cc + "         " + dd + "\n");
            der[iii].opc = cc;
            der[iii].cod = dd;
            iii++;
            if (A[valor].opc.equals(" ")) {
                A[valor].opc = cc;
                A[valor].cod = dd;
            } else {
                if (A[valor].ptrsig == null)/*Valores agreagdos a lista en caso de colisiones*/ {
                    aux = new tabop2();
                    aux.opc = cc;
                    aux.cod = dd;
                    aux.ptrsig = null;
                    A[valor].ptrsig = aux;
                } else {
                    p = A[valor].ptrsig;
                    while (p.ptrsig != null) {
                        p = p.ptrsig;
                    }
                    aux = new tabop2();
                    aux.opc = cc;
                    aux.cod = dd;
                    aux.ptrsig = null;/*Se deja apuntando nulo una vez que ha terminado de agragar un dato*/
                    p.ptrsig = aux;
                }

            }
        }
        arch3.close();
    }

    int validatabop(String cad)/*Se verifica si el elemento ya existe*/ {
        int num = 0;
        tabop2 p;
        num = hashtabop(cad);
        if (A[num].opc.equals(cad)) {
            System.out.println("Paso "+cad);
            return 1;
        } else {
            p = A[num].ptrsig;
            while (p != null) {
                if (p.opc.equals(cad)) {
                    System.out.println("Paso "+cad);
                    return 1;
                }

                p = p.ptrsig;
            }
        }
        num = 0;
        return 0;
    }
//////////////////////////////////////
}
//Se guardan los registros del codigo fuente

class nodo {

    String etiqueta;
    String operador;
    String codop;
}

//Aqui se cargan todos los datos de los aperadores
class tabop {
    String opc;
    String cod;
}
//Se crea para uso exclusivo del paso 2 con tabsim y tabop

class data {

    String opc;
    String cod;
    String etiq;
    int dir;
}

class tabop2 {

    String opc;
    String cod;
    tabop2 ptrsig;
}

class tabsim1 {

    String etiq;
    int dir;
    tabsim1 ptrsig;
}

//Epublic classura donde se cargan
class nodo1 {

    String operador1;
    int codop1;
    nodo1 ptrsig;
}

