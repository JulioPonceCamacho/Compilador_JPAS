/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Compiler;

/**
 *
 * @author Julio Ponce
 */
public class Traduccion {

    String General = "";
    int error = 0;
    String RES[][];
    String TOKENS[][];
    String REGLAS[];
    String Sintactico = "";

    public Traduccion() {
        TOKENS = new String[1000][2];
        RES = new String[10][];
        RES[0] = new String[]{"RESEVERDA", "body", "SI", "entonces", "De lo contrario", "FIN", "Principal", "Int", "Float", "Char","FOR","READ","WRITE"};
        RES[1] = new String[]{"BOOLEAN", ">", "<", "==", ">=", "<="};
        RES[2] = new String[]{"ASIGNACION", ":="};
        RES[3]=new String[]{"OPERADOR","+","*","-","/"};
        RES[4] = new String[]{"NUMERO", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        RES[5] = new String[]{"LETRA", "A", "B", "C", "D","E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z","\""};
        RES[6] = new String[]{"SIGNOS", "{", "}", " ", ",",")","("};
        RES[7] = new String[]{"CERRARLINEA", ";"};
        REGLAS = new String[]{"INICIO->body_NUMERO_Principal_{_PROCEDIMIENTO_}",
            "PROCEDIMIENTO->CONDICION|DECLARACION|OPERACION",
            "CONDICION->SI_VAR_BOOLEAN_VAR_entonces_OPERACION_De lo contrario_OPERACION_FIN_SI_;",
            "PARA->FOR_(_OPERACION_VAR_BOOLEAN_VAR_;_VAR_ASIGNACION_VAR_OPERADOR_VAR_)_{_SUBPROC_}",
            "SUBPROC->CONDICION|DECLARACION|OPERACION",
            "ARITMETICA->VAR_ASIGNACION_VAR_OPERADOR_VAR_;",
            "SALIDA->WRITE_(_TEXTO_)_;",
            "SALIDA->WRITE_(_TEXTO_,_VAR_)_;",
            "SALIDA->WRITE_(_VAR_)_;",
            "ENTRADA->READ_(_VARIABLE_)_;",
            "OPERACION->VAR_ASIGNACION_VAR_;",
            "DECLARACION->TIPO_VARIABLE_;",
            "DECLARACION->TIPO_MUL_;",
            "TIPO->Char|Int|Float",
            "VAR->VARIABLE|NUMERO"};
    }
    int h = 0, V = 0, cont = 0, cont2 = 0;

    public boolean ComprobadorLexico(String Codigo) {
        //Optimizado
        String val[][] = new String[1000][2];
        String val2[][] = new String[1000][2];
        Codigo=Codigo.replaceAll("\t"," ");
        Codigo=Codigo.replaceAll(" +"," ");
        String aux[] = Codigo.split("\n");
        String tmpR, tmpU; //Optimizado
        ///Comprobar que los caracteres existan
        for (int i = 0; i < aux.length; i++) {
            for (int j = 0; j < aux[i].length(); j++) {
                tmpU = "" + aux[i].charAt(j);
                val[cont][1] = tmpU;
                for (int t = 0; t < 8; t++) {
                    for (int tk = 1; tk < RES[t].length; tk++) {
                        if (tmpU.equals(RES[t][tk])) {
                            h++;
                            val[cont][0] = RES[t][0];
                        }
                    }
                }
                if (h == 0) {
                    for (int t = 0; t < 8; t++) {
                        for (int tk = 1; tk < RES[t].length; tk++) {
                            if (RES[t][tk].contains(tmpU)) {
                                h++;
                                val[cont][0] = RES[t][0];
                            }
                        }
                    }
                    if (h == 0) {
                        val[cont][0] = "DESCONOCIDO";
                        General = General + "\nError Lexico linea[" + i + "]-> No se reconoce el caracter [" + tmpU+"]";
                        error++;
                    }
                }
                h = 0;
                cont++;
            }
        }
        
       String tl="";
       
         for (int i = 0; i < cont; i++) {
             tl="";
           if(val[i][1].equals("\"")){
               tl+=val[i][1];
               i++;
               while(!val[i][1].equals("\"")){
                   tl+=val[i][1];
                   i++;
                   if(i==cont){
                       General+="Error, no se cerraron las comillas en "+tl.substring(0,tl.indexOf(");")+2);
                       error++;
                       break;
                   }
               }
               val2[cont2][0] = "TEXTO";
               val2[cont2][1] = tl+val[i][1];
               cont2++;
           }else{
               val2[cont2][0] = val[i][0];
               val2[cont2][1] = val[i][1];
               cont2++;
           }
        }
        val=val2;
        cont=cont2;
        cont2=0;
        int hj = 0;
        String v = val[0][1];
        for (int i = 0; i < cont; i++) {
            if (val[i][0].equals(val[i + 1][0]) && !val[i][1].equals("{")&& !val[i][1].equals("}")&& !val[i][1].equals("(")&& !val[i][1].equals(")")) {
                v = v + val[i + 1][1];
                hj++;
            } else {
                hj = 0;
                val2[cont2][0] = val[i][0];
                val2[cont2][1] = v;
                v = val[i + 1][1];
                cont2++;
            }
        }
        boolean en = false;
        V = 0;
        //Busqueda inicial Palabras RESEVERDA
        int contV = 0;
        cont = 0;
        for (int i = 0; i < cont2; i++) {
            tmpR = "";
            for (int t = 0; t < 3; t++) {
                for (int tk = 1; tk < RES[t].length; tk++) {
                    if (RES[t][tk].contains(val2[i][1])) {
                        tmpR = tmpR + val2[i][1];
                        for (int m = i + 1; m < cont2; m++) {
                            tmpR = tmpR + val2[m][1];
                            if (RES[t][tk].contains(tmpR)) {
                                contV++;
                            } else {
                                break;
                            }
                        }
                        if (contV > 0) {
                            t = 4;
                            tk = RES[t].length;
                        }
                    }
                }
            }
            if (contV > 0) {
                val[cont][0] = "LETRA";
                val[cont][1] = tmpR;
                cont++;
                i = i + contV + 1;
            } else {
                val[cont][0] = val2[i][0];
                val[cont][1] = val2[i][1];
                cont++;
            }
            contV = 0;
        }
        
        val2 = val;
        cont2 = cont;
        /////Determinar Palabras RESEVERDAS
        for (int i = 0; i < cont2; i++) {
            for (int t = 0; t < 3; t++) {
                for (int tk = 1; tk < RES[t].length; tk++) {
                    if (val2[i][1].equals(RES[t][tk])) {
                        TOKENS[V][0] = RES[t][0];
                        TOKENS[V][1] = RES[t][tk];
                        V++;
                        en = true;
                    } else if (val2[i][1].contains(RES[t][tk]) && !val2[i][0].equals("TEXTO")) {
                        int in = val2[i][1].indexOf(RES[t][tk]);
                        String tmp[] = val2[i][1].split(RES[t][tk]);
                        if (tmp.length == 1) {
                            if (in > 0) {
                                TOKENS[V][0] = val2[i][0];
                                TOKENS[V][1] = tmp[0];
                                V++;
                                TOKENS[V][0] = RES[t][0];
                                TOKENS[V][1] = RES[t][tk];
                                V++;
                                en = true;
                            } else {
                                TOKENS[V][0] = RES[t][0];
                                TOKENS[V][1] = RES[t][tk];
                                V++;
                                TOKENS[V][0] = val2[i][0];
                                TOKENS[V][1] = tmp[0];
                                V++;
                                en = true;
                            }
                        } else if (tmp.length == 2) {
                            TOKENS[V][0] = val2[i][0];
                            TOKENS[V][1] = tmp[0];
                            V++;
                            TOKENS[V][0] = RES[t][0];
                            TOKENS[V][1] = RES[t][tk];
                            V++;
                            TOKENS[V][0] = val2[i][0];
                            TOKENS[V][1] = tmp[1];
                            V++;
                            en = true;
                        }
                    }
                }
            }
            if (en == false) {
                TOKENS[V][0] = val2[i][0];
                TOKENS[V][1] = val2[i][1];
                V++;
            }
            en = false;
        }
        val2 = TOKENS;
        
        ////Determinar variables
        contV = 0;
        cont2 = 0;
        for (int i = 0; i < V; i++) {
            String AB; //Optimizado
            String CD; //Optimizado
            if (val2[i][0].equals("LETRA")) {
                for (int j = i + 1; j < V; j++) {
                    if (val2[j][0].equals("LETRA") || val2[j][0].equals("NUMERO")) {
                        contV++;
                    } else {
                        break;
                    }
                }
            }
            if (contV > 0) {
                AB = "VARIABLE";
                CD = val2[i][1];
                for (int j = i + 1; j < i + 1 + contV; j++) {
                    CD = CD + val2[j][1];
                }
                TOKENS[cont2][0] = AB;
                TOKENS[cont2][1] = CD;
                cont2++;
                i = i + contV;
            } else {
                if (val2[i][0].equals("LETRA")) {
                    val2[i][0] = "VARIABLE";
                }
                TOKENS[cont2][0] = val2[i][0];
                TOKENS[cont2][1] = val2[i][1];
                cont2++;
            }
            contV = 0;
        }
         
        val = TOKENS;
        cont = cont2;
        cont2 = 0;
        for (int i = 0; i < cont; i++) {
            val[i][1] = val[i][1].trim();
            if (!val[i][1].matches(" +") && !val[i][1].equals("")) {
                TOKENS[cont2][0] = val[i][0];
                TOKENS[cont2][1] = val[i][1];
                cont2++;

            }
        }
        
        //Optimizado
        val = TOKENS;
        cont = cont2;
        cont2 = 0;
        int auxCont = 0;
        for (int i = 0; i < cont; i++) {
            if (val[i][1].matches("Int|Float|Char") && val[i + 1][0].equals("VARIABLE")) {
                String tm = val[i + 1][1];
                auxCont = 0;
                for (int j = i + 2; j < cont; j = j + 2) {
                    if (val[j][1].equals(",") && val[j + 1][0].equals("VARIABLE")) {
                        tm += val[j][1] + val[j + 1][1];
                        auxCont++;
                    } else {
                        break;
                    }
                }
                if (auxCont == 0) {
                    TOKENS[cont2][0] = val[i][0];
                    TOKENS[cont2][1] = val[i][1];
                    cont2++;
                    i++;
                    TOKENS[cont2][0] = val[i][0];
                    TOKENS[cont2][1] = val[i][1];
                    cont2++;
                } else {
                    TOKENS[cont2][0] = val[i][0];
                    TOKENS[cont2][1] = val[i][1];
                    cont2++;
                    TOKENS[cont2][0] = "MUL";
                    TOKENS[cont2][1] = tm;
                    cont2++;
                    i = i + auxCont * 2 + 1;
                }
            } else {
                TOKENS[cont2][0] = val[i][0];
                TOKENS[cont2][1] = val[i][1];
                cont2++;
            }
        }
        /*for(int i=0;i<cont2;i++){
            System.out.println(TOKENS[i][0]+"----"+TOKENS[i][1]);
        }*/

        return error <= 0; //Optimizado
    }

    int ac = 0;

    public boolean ComprobarSintaxis(String Codigo) {
        String RG[][] = new String[REGLAS.length][2];
        for (int i = 0; i < REGLAS.length; i++) {
            String tmep[] = REGLAS[i].split("->");
            RG[i][0] = tmep[0];
            RG[i][1] = tmep[1];
        }
        /////PROCEDIMIENTO
        String res = "";
        while (ac < cont2 - 1) {
            boolean encontrado = false, enc;//Optimizado
            int val = 0;
            for (String[] RG1 : RG) { //Optimizado
                String[] gram2 = RG1[1].split("_");
                for (String gram21 : gram2) { //Optimizado
                    enc = false;
                    if (TOKENS[ac][0].equals(gram21)) {
                        res = res + gram21 + "_";
                        encontrado = true;
                        ac++;
                    } else if (TOKENS[ac][1].equals(gram21)) {
                        res = res + gram21 + "_";
                        encontrado = true;
                        ac++;
                    } else if (gram21.contains(TOKENS[ac][0]) && gram21.contains("|")) {
                        String[] partir = gram21.split("\\|");
                        for (String partir1 : partir) { //Optimizado
                            if (partir1.equals(TOKENS[ac][0])) {
                                res = res + RG1[0];
                                ac++;
                                encontrado = true;
                                break;
                            }
                        }
                    } else if (gram21.contains(TOKENS[ac][1]) && gram21.contains("|")) {
                        String[] partir = gram21.split("\\|");
                        for (String partir1 : partir) {
                            if (partir1.equals(TOKENS[ac][1])) {
                                res = res + RG1[0];
                                ac++;
                                encontrado = true;
                                break;
                            }
                        }
                    } else {
                        for (int m = 0; m < RG.length; m++) {
                            if (gram21.equals(RG[m][0])) {
                                enc = true;
                                val = m;
                                break;
                            }
                        }
                        if (enc == true) {
                            String ares = revisar(RG, TOKENS, val);
                            if (!ares.equals("")) {
                                res = res + ares + "_";
                                //Optimizado
                                encontrado = true;
                            }
                        }
                    }
                }
                if (encontrado == true) {
                    break;
                }
            }
            if(res.length()>0){
               try{
            res=eliminar_(res);
            for (String[] RG1 : RG) { //Optimizado
                if (res.contains(RG1[1])) {
                    res = res.replace(RG1[1], RG1[0]);
                }
            }
            for (String[] RG1 : RG) { //Optimizado
                if (res.contains(RG1[1]) && !res.equals(RG1[1])) {
                    res = res.replace(RG1[1], RG1[0]);
                }
            }
            res=eliminar_(res);
            String auxSa[] = res.split("_");
            int Gram[][] = new int[RG.length][1];
            int j = 0, maux = 0, indi = 0;

            for (String RG1[] : RG) {
                Gram[j][0] = 0;
                String az[] = RG1[1].split("_");
                for (String za : az) {
                    for (String auxSA : auxSa) {
                        if (za.equals(auxSA)) {
                            Gram[j][0]++;
                        }
                    }
                }
                if (Gram[j][0] > maux) {
                    maux = Gram[j][0];
                    indi = j;
                }
                j++;
            }
            String az[] = RG[indi][1].split("_");
            res = "";
            boolean na = false;
            for (int i = 0; i < auxSa.length; i++) {
                if (!az[i].equals(auxSa[i])) {
                    for (String[] RG1 : RG) {
                        if (az[i].equals(RG1[0]) && auxSa[i].equals(RG1[1])) {
                            res = res + RG1[0] + "_";
                            na = true;
                        }
                    }
                    if (na == false) {
                        res = res + auxSa[i] + "_";
                    }
                } else {
                    res = res + auxSa[i] + "_";
                }
            }
            if (res.charAt(res.length() - 1) == '_') {
                res = res.substring(0, res.length() - 1);
            }
            res=res.replace("_}_}","_}");
            //System.out.println("--->>"+res);
            
            Sintactico = Sintactico + res + "\n";
            //System.out.println(Sintactico);
            res = "";
            }catch(Exception exe){
                error++; 
                General+="Hay un problema con la Sintaxis.\n"+exe;
            }
        } else{
                error++;
                if(ac>1)
                General += "Error de sintaxis. No se entiende " + TOKENS[ac-1][1] + TOKENS[ac][1] + TOKENS[ac+1][1]+ "\n";
                else{
                     General += "Error de sintaxis. No se entiende " + TOKENS[ac][1] + TOKENS[ac+1][1]+ "\n";
                }
                break;
            }
        }
        try{
        String varS[] = Sintactico.split("\n");
        Sintactico = "";
        for (String sin : varS) {
            Sintactico += sin + "_";
        }
        //System.out.println("_2_>"+Sintactico);
        for (String[] RG1 : RG) { //Optimizado
            if (Sintactico.contains(RG1[1]) && !Sintactico.equals(RG1[1])) {
                Sintactico = Sintactico.replace(RG1[1], RG1[0]);
            }
        }
        Sintactico=eliminar_(Sintactico);
       //System.out.println("_3_"+Sintactico);
        String vaS[]=Sintactico.split("_");
        int pro=0;
        Sintactico="";
        String pr="";
        
        for(int i=0;i<vaS.length;i++){
            pro=0;
            if(vaS[i].equals("FOR")){
                Sintactico+=vaS[i]+"_";
                i++;
                String Tme=vaS[i]+"_"+vaS[i+1]+"_"+vaS[i+2]+"_"+vaS[i+3]+"_"+vaS[i+4]+"_"+vaS[i+5]+"_"+vaS[i+6]+"_"+vaS[i+7]+"_"
                        +vaS[i+8]+"_"+vaS[i+9]+"_"+vaS[i+10]+"_"+vaS[i+11]+"_"+vaS[i+12];
                i=i+12;
                if(Tme.equals("(_OPERACION_VAR_BOOLEAN_VAR_;_VAR_ASIGNACION_VAR_OPERADOR_VAR_)_{")){
                    Sintactico+=Tme+"_";
                    i++;
                    while(i<vaS.length){
                        if(!(vaS[i].equals("CONDICION") || vaS[i].equals("OPERACION") || vaS[i].equals("ENTRADA") || vaS[i].equals("SALIDA")  || vaS[i].equals("DECLARACION") || vaS[i].equals("ARITMETICA"))){
                            General += "Error en la sintaxis de FOR,No soporta ->["+vaS[i]+"]\n";
                            error++;
                            pro++;
                        }
                        i++;
                        if(vaS[i].equals("}")) break;
                    }
                    if(pro==0){
                        Sintactico+="SUBPROC_"+vaS[i]+"_";
                    }
            }
            else{
                General += "Error en la sintaxis de FOR()\n";
                error++;
                Sintactico+=Tme+"_";
                }
            }
            else{
                Sintactico+=vaS[i]+"_";
            }
        }
        Sintactico=eliminar_(Sintactico);
       //System.out.println("_4_"+Sintactico);
        
        
        for (String[] RG1 : RG) { //Optimizado
            if (Sintactico.contains(RG1[1]) && !Sintactico.equals(RG1[1])) {
                Sintactico = Sintactico.replace(RG1[1], RG1[0]);
            }
        }
        varS=Sintactico.split("_");
        if (!varS[varS.length-1].equals("}")) {
            Sintactico += "_}";
        }
       //System.out.println("_Fin_"+Sintactico);
        }
        catch(Exception e){
            General+="Sintaxis General Erronea \n"+e;
        }
        
        int zz = 0;
        String auxSa[] = Sintactico.split("_");
        String Grama[] = RG[0][1].split("_");
        Sintactico = "";
        try{
        for (int x = 0; x < Grama.length; x++) {
            if (Grama[x].equals("PROCEDIMIENTO")) {
                boolean temp = false;
                String temp2 = "";
                String err = "";
                for (int y = x; y < auxSa.length - 1; y++) {
                    if (auxSa[y].equals("CONDICION") || auxSa[y].equals("OPERACION") || auxSa[y].equals("ENTRADA") || auxSa[y].equals("SALIDA") || auxSa[y].equals("DECLARACION") || auxSa[y].equals("ARITMETICA") || auxSa[y].equals("PARA") ) {
                        if (!err.equals("")) {
                            General += "Error en la sintaxis " + err + "\n";
                            err = "";
                        }
                    } else {
                        error++;
                        temp = true;
                        err += auxSa[y] + " ";
                    }
                    temp2 += auxSa[y] + "_";
                }
                zz = auxSa.length - 2;
                if (temp = false) {
                    Sintactico += "PROCEDIMIENTO_";
                } else {
                    Sintactico += temp2;
                    if (!err.equals("")) {
                        General += "Error en la sintaxis " + err + "\n";
                    }
                }
            } else if (!auxSa[zz].equals(Grama[x]) && !Grama[x].equals("PROCEDIMIENTO")) {
                error++;
                if(x>0)
                General += "Error no se encuentra " + Grama[x] +" despues de "+Grama[x-1]+"\n";
                else 
                General += "Error no se encuentra " + Grama[x] + "\n";
            } else {
                if (x == Grama.length - 1) {
                    Sintactico += auxSa[zz];
                } else {
                    Sintactico += auxSa[zz] + "_";
                }
            }

            zz++;
        }
        }catch (Exception exe){
            error++; 
            General+="Hay un problema con la Sintaxis.\n";
        }
        return error <= 0; //Optimizado
    }
    public String eliminar_(String res){
        res = res.replaceAll("_+", "_");
            if ('_' == res.charAt(0)) {
                res = res.replaceFirst("_", "");
            }
            if (res.charAt(res.length() - 1) == '_') {
                res = res.substring(0, res.length() - 1);
            }
            return res;
    }
    public String revisar(String R[][], String T[][], int col) {
        String res = "";
        boolean enc;//Optimizado
        int val = 0;
        String gram2[] = R[col][1].split("_");
        for (String gram21 : gram2) {
            enc = false;
            if (T[ac][0].equals(gram21)) {
                res = res + gram21 + "_";
                ac++;
            } else if (T[ac][1].equals(gram21)) {
                res = res + gram21 + "_";
                ac++;
            } else if (gram21.contains(T[ac][0]) && gram21.contains("|")) {
                String[] partir = gram21.split("\\|");
                for (String partir1 : partir) {//Optimizado
                    if (partir1.equals(T[ac][0])) {
                        res = res + R[col][0] + "_";
                        ac++;
                        break;
                    }
                }
            } else if (gram21.contains(T[ac][1]) && gram21.contains("|")) {
                String[] partir = gram21.split("\\|");
                for (String partir1 : partir) {
                    if (partir1.equals(T[ac][1])) {
                        res = res + R[col][0] + "_";
                        ac++;
                        break;
                    }
                }
            } else {
                for (int m = 0; m < R.length; m++) {
                    if (gram21.equals(R[m][0])) {
                        enc = true;
                        val = m;
                        break;
                    }
                }
                if (enc == true) {

                    String ares = revisar(R, T, val);
                    if (!ares.equals("")) {
                        res = res + ares + "_";
                    }
                }
            }
        }
        return res;
    }
    Lista lis = new Lista();
    public boolean AnalizadorSemantico(String[] codigo) {    
        String aux[][] = new String[codigo.length][];
        for (int i = 0; i < codigo.length; i++) {
            codigo[i]=codigo[i].replaceAll("\t"," ");
            codigo[i]=codigo[i].replaceAll(" +"," ");
            aux[i] = codigo[i].split(" ");
            
        }
        for (int i = 0; i < codigo.length; i++) {
            if (codigo[i].matches("( *(Int|Float|Char)\\s+([a-zA-Z][a-zA-Z0-9]*) *;)+ *")) {
                codigo[i] = codigo[i].replaceAll(" +", " ");
                codigo[i] = codigo[i].trim();
                String auxV[] = codigo[i].split(";");
                for (String auxV1 : auxV) {
                    auxV1 = auxV1.trim();
                    String valV[] = auxV1.split(" ");
                    if (valV[0].matches("Int|Float|Char") && valV[1].matches("[a-zA-Z][a-zA-Z0-9]*")) {
                        if (lis.existe(valV[1]) == false) {
                            lis.AgregarElemento(valV[0], valV[1]);
                        } else {
                            General = General + "\nError sementico linea [" + i + "]. La variable [" + valV[1] + "] ya existe.\n";
                            error++;
                        }
                    }
                }
            } else if (codigo[i].matches("( *(Int|Float|Char)\\s+([a-zA-Z][a-zA-Z0-9]*( *, *[a-zA-Z][a-zA-Z0-9]*)+ *) *;)+ *")) {
                codigo[i] = codigo[i].replaceAll(" +", " ");
                codigo[i] = codigo[i].trim();
                codigo[i] = codigo[i].replace(";", "");
                String auxV[] = codigo[i].split(" *, *");
                String auxTip[] = auxV[0].split(" ");
                String Tipo = auxTip[0];
                if (auxTip[1].matches("[a-zA-Z][a-zA-Z0-9]*")) {
                    if (lis.existe(auxTip[1]) == false) {
                        lis.AgregarElemento(auxTip[0], auxTip[1]);
                    } else {
                        General = General + "\nError sementico linea [" + i + "]. La variable [" + auxTip[1] + "] ya existe.\n";
                        error++;
                    }
                }
                for (int m = 1; m < auxV.length; m++) {
                    auxV[m] = auxV[m].trim();
                    if (auxV[m].matches("[a-zA-Z][a-zA-Z0-9]*")) {
                        if (lis.existe(auxV[m]) == false) {
                            lis.AgregarElemento(auxTip[0], auxV[m]);
                        } else {
                            General = General + "\nError sementico linea [" + i + "]. La variable [" + auxV[m] + "] ya existe.\n";
                            error++;
                        }
                    }
                }
            }
        }
        boolean comp;
        for (int i = 0; i < cont2; i++) {
            if (TOKENS[i][0].equals("VARIABLE") && lis.existe(TOKENS[i][1]) == false) {
                General += "Error semantico. La variable [" + TOKENS[i][1] + "] no esta declarada.\n";
                error++;
            }
        }
        return error <= 0;
    }
    String NOUV[]=new String[1000];
    int vs=0;
    public String optimizar(String[] codigo) {
        String Optimizacion = "";
        String aux[][] = new String[codigo.length][];
        for (int i = 0; i < codigo.length; i++) {
            codigo[i] = codigo[i].trim();
            codigo[i]=codigo[i].replaceAll("\t"," ");
            codigo[i] = codigo[i].replaceAll(" +", " ");
            codigo[i] = codigo[i].replaceAll(" *; *", ";");
            codigo[i] = codigo[i].replaceAll(" *, *", ",");
            if (codigo[i].contains(",")) {
                aux[i] = codigo[i].split(",");
            } else {
                aux[i] = codigo[i].split(" ");
            }
        }
        for (int j = 0; j < aux.length; j++) {
            int veces = 0;
            if (aux[j].length == 2 && !aux[j][0].contains(" ") && !codigo[j].contains(":=") &&
                    (codigo[j].contains("Int") || codigo[j].contains("Float") || codigo[j].contains("Float"))) {
                aux[j][1] = aux[j][1].replaceAll(";", "");
                for (int i = 0; i < cont2; i++) {
                    if (aux[j][1].equals(TOKENS[i][1])) {
                        veces++;
                    }
                }
                if (veces > 1) {
                    Optimizacion += aux[j][0] + " " + aux[j][1] + ";\n";
                } else{
                    General = General + ("Atencion. La variable [ " + aux[j][1] + " ] esta declarada pero no es utilizada.") + "\n";
                }
            } else if (aux[j][0].contains(" ") && !codigo[j].contains(":=")) {
                String mm[]=aux[j][0].split(" ");
                String auxC = codigo[j];
                veces=1;
                for (int i = 0; i < cont2; i++) {
                    if (mm[1].equals(TOKENS[i][1])) {
                        veces++;
                    }
                }
                if (veces < 2) {
                    auxC = auxC.replaceAll(" *, *" + mm[1] + " *; *", ";");
                    auxC = auxC.replaceAll(" *, *" + mm[1] + " *, *", ",");
                    auxC = auxC.replaceAll(" *" + mm[1] + " *, *", " ");
                    auxC=  auxC.replaceAll("(Int|Float|Char) " + mm[1] + ";", "");
                    General = General + ("Atencion. La variable " + mm[1] + " no es utilizada, se ha optimizado") + "\n";
                    NOUV[vs]=mm[1];
                    vs++;
                }
                for (int m = 1; m < aux[j].length; m++) {
                  veces=1;
                  aux[j][m]=aux[j][m].replaceAll(";","");
                  aux[j][m]=aux[j][m].replaceAll(" ","");
                  for (int i = 0; i < cont2; i++) {
                        if (aux[j][m].equals(TOKENS[i][1])) {
                            veces++;
                        }
                    }
                 if (veces<2) {
                     String inicio=auxC;
                     if(inicio.equals(auxC))
                        auxC=  auxC.replaceAll(" *(Int|Float|Char) *" + aux[j][m] + " *; *", "");
                     if(inicio.equals(auxC))  
                        auxC = auxC.replaceAll(" *" + aux[j][m] + " *, *", " ");
                     if(inicio.equals(auxC))  
                        auxC = auxC.replaceAll(" *, *" + aux[j][m] + " *, *", ",");
                     if(inicio.equals(auxC))
                        auxC = auxC.replaceAll(" *, *" + aux[j][m] + " *; *", ";");
                        General = General + ("Atencion. La variable " + aux[j][m] + " no es utilizada, se ha optimizado") + "\n";
                        NOUV[vs]=aux[j][m];
                        vs++;
                    }
                }
                Optimizacion += auxC + "\n";
            } else {
                for (int i = 0; i < aux[j].length; i++) {
                    Optimizacion += aux[j][i] + " ";
                }
                Optimizacion += "\n";
            }
        }
        return Optimizacion;
    }

    String Etiquetas[];
    int et = 1;

    public String CodigoIntermedio(String Codigo) {
        Etiquetas = new String[1000];
        String Intermedio = "";
        //Optimizado
        int e = 1;
        Intermedio += TOKENS[0][1] + TOKENS[1][1] + "\n";
        Intermedio += TOKENS[2][1] + "\n";
        Intermedio += TOKENS[3][1] + "\n";
        int i = 4;
        while (i < cont2) {
            //System.out.println("-->"+TOKENS[i][1]);
            if (TOKENS[i][1].matches("Int|Float|Char")) {
                Intermedio += " " + TOKENS[i][1] + " " + TOKENS[i + 1][1] + "\n";
                i = i + 2;
            } else if (TOKENS[i][0].equals("VARIABLE") && TOKENS[i + 1][1].equals(":=")) {
                Intermedio += " " + TOKENS[i][1] + " " + TOKENS[i + 1][1] + TOKENS[i + 2][1] + "\n";
                i = i + 3;
            } else if (TOKENS[i][1].equals("SI")) {
                Intermedio += " " + TOKENS[i][1] + " " + TOKENS[i + 1][1] + TOKENS[i + 2][1] + TOKENS[i + 3][1] + " goto " + generarEtiqueta() + "\n  ";
                Intermedio += "goto " + generarEtiqueta() + "\n  ";
                Intermedio += "etiqueta " + Etiquetas[e] + "\n  ";
                e++;
                Intermedio += TOKENS[i + 5][1] + TOKENS[i + 6][1] + TOKENS[i + 7][1] + "\n  ";
                Intermedio += "goto " + generarEtiqueta() + "\n  ";
                Intermedio += "etiqueta " + Etiquetas[e] + "\n  ";
                e++;
                Intermedio += TOKENS[i + 10][1] + TOKENS[i + 11][1] + TOKENS[i + 12][1] + "\n  ";
                Intermedio += "etiqueta " + Etiquetas[e] + "\n\n  ";
                e++;
                i = i + 12 + 4;
            }
            else if(TOKENS[i][1].equals("WRITE") && TOKENS[i+2][0].equals("TEXTO")){
                if(TOKENS[i+3][1].equals(")")){
                    Intermedio+=" "+TOKENS[i][1]+TOKENS[i+1][1]+TOKENS[i+2][1]+TOKENS[i+3][1]+"\n";
                    i=i+4;
                }
                else if(TOKENS[i+3][1].equals(",")){
                    Intermedio+=" "+TOKENS[i][1]+TOKENS[i+1][1]+TOKENS[i+2][1]+TOKENS[i+3][1]+TOKENS[i+4][1]+TOKENS[i+5][1]+"\n";
                    i=i+6;
                }
            }
            else if(TOKENS[i][1].equals("WRITE") && TOKENS[i+2][0].equals("VARIABLE")){
                Intermedio+=" "+TOKENS[i][1]+TOKENS[i+1][1]+TOKENS[i+2][1]+TOKENS[i+3][1]+"\n";
                 i=i+4;
            }
            else if(TOKENS[i][1].equals("READ")){
                Intermedio+=" "+TOKENS[i][1]+TOKENS[i+1][1]+TOKENS[i+2][1]+TOKENS[i+3][1]+"\n";
                 i=i+4;
            }
            else if(TOKENS[i][1].equals("FOR")){
                //System.out.println("NI");
                Intermedio+=" "+TOKENS[i+2][1]+TOKENS[i+3][1]+TOKENS[i+4][1]+"\n";
                Intermedio+=" etiqueta "+generarEtiqueta()+"\n";
                
                String auxB=" "+TOKENS[i+10][1]+TOKENS[i+11][1]+TOKENS[i+12][1]+TOKENS[i+13][1]+TOKENS[i+14][1]+"\n"+
                        " SI "+TOKENS[i+6][1]+TOKENS[i+7][1]+TOKENS[i+8][1]+" ";
                auxB+=" goto "+Etiquetas[e];
                e++;
                i=i+14;
                 while (i < cont2) {
            if (TOKENS[i][1].matches("Int|Float|Char")) {
                Intermedio += " " + TOKENS[i][1] + " " + TOKENS[i + 1][1]+ "\n";
                i = i + 2;
            } else if (TOKENS[i][0].equals("VARIABLE") && TOKENS[i + 1][1].equals(":=")) {
                Intermedio += " " + TOKENS[i][1] + " " + TOKENS[i + 1][1] + TOKENS[i + 2][1] + "\n";
                i = i + 3;
            } else if (TOKENS[i][1].equals("SI")) {
                Intermedio += " " + TOKENS[i][1] + " " + TOKENS[i + 1][1] + TOKENS[i + 2][1] + TOKENS[i + 3][1] + " goto " + generarEtiqueta() + "\n  ";
                Intermedio += "goto " + generarEtiqueta() + "\n  ";
                Intermedio += "etiqueta " + Etiquetas[e] + "\n  ";
                e++;
                Intermedio += TOKENS[i + 5][1] + TOKENS[i + 6][1] + TOKENS[i + 7][1] + "\n  ";
                Intermedio += "goto " + generarEtiqueta() + "\n  ";
                Intermedio += "etiqueta " + Etiquetas[e] + "\n  ";
                e++;
                Intermedio += TOKENS[i + 10][1] + TOKENS[i + 11][1] + TOKENS[i + 12][1] + "\n  ";
                Intermedio += "etiqueta " + Etiquetas[e] + "\n\n  ";
                e++;
                i = i + 12 + 4;
            }
            else if(TOKENS[i][1].equals("WRITE") && TOKENS[i+2][0].equals("TEXTO")){
                if(TOKENS[i+3][1].equals(")")){
                    Intermedio+=" "+TOKENS[i][1]+TOKENS[i+1][1]+TOKENS[i+2][1]+TOKENS[i+3][1]+"\n";
                    i=i+4;
                }
                else if(TOKENS[i+3][1].equals(",")){
                    Intermedio+=" "+TOKENS[i][1]+TOKENS[i+1][1]+TOKENS[i+2][1]+TOKENS[i+3][1]+TOKENS[i+4][1]+TOKENS[i+5][1]+"\n";
                    i=i+6;
                }
            }
            else if(TOKENS[i][1].equals("WRITE") && TOKENS[i+2][0].equals("VARIABLE")){
                Intermedio+=" "+TOKENS[i][1]+TOKENS[i+1][1]+TOKENS[i+2][1]+TOKENS[i+3][1]+"\n";
                 i=i+4;
            }
            else if(TOKENS[i][1].equals("READ")){
                Intermedio+=" "+TOKENS[i][1]+TOKENS[i+1][1]+TOKENS[i+2][1]+TOKENS[i+3][1]+"\n";
                 i=i+4;
                }
            else if(TOKENS[i][1].equals("}")) {
                i++;
                break;}
            else{
                i++;
            }
              }
                Intermedio+=auxB+"\n";
            }
            else {
                i++;
            }
        }
        Intermedio += TOKENS[cont2 - 1][1];
        return Intermedio;
    }

    public String generarEtiqueta() {
        Etiquetas[et] = "ETQ" + String.valueOf(et);
        et++;
        return Etiquetas[et - 1];
    }

    public String CodigoEnsamblador(String Codigo) {
        String Ensamblador = "";
        String Line[] = Codigo.split("\n");
        int ens = 0;
        int e = 1;
        String lineas[] = new String[Line.length];
        for (String Line1 : Line) {//Optimizado
            if (!Line1.equals("")) {
                lineas[ens] = Line1;
                ens++;
            }
        }
        Ensamblador += TOKENS[2][1]+" START "+ TOKENS[1][1]+"\n";
        String var[] = new String[100];
        int v = 0;
        boolean comp;//Optimizado
        for (int i = 0; i < cont2; i++) {
            if (TOKENS[i][0].equals("VARIABLE")) {
                comp = false;
                for (int j = 0; j < v; j++) {
                    if (TOKENS[i][1].equals(var[j])) {
                        comp = true;
                        break;
                    }
                }
                if (comp == false) {
                    Ensamblador += TOKENS[i][1]+ " WORD " + "10\n";
                    var[v] = TOKENS[i][1];
                    v++;
                }
            }
        }
        int i = 0;
        while (i < cont2) {
            if(TOKENS[i][0].equals("VARIABLE") && TOKENS[i+3][0].equals("OPERADOR")){
                switch(TOKENS[i+3][1]){
                    case "*":
                        Ensamblador+="  MOV AL,"+TOKENS[i+2][1]+"\n";
                        Ensamblador+="  MUL AL,"+TOKENS[i+4][1]+"\n";
                        Ensamblador+="  MOV "+TOKENS[i][1]+",AL\n";
                    break;
                    case "+":
                        Ensamblador+="  MOV AL,"+TOKENS[i+2][1]+"\n";
                        Ensamblador+="  ADD AL,"+TOKENS[i+4][1]+"\n";
                        Ensamblador+="  MOV "+TOKENS[i][1]+",AL\n";
                     break;
                    case "-":
                        Ensamblador+="  MOV AL,"+TOKENS[i+2][1]+"\n";
                        Ensamblador+="  SUB AL,"+TOKENS[i+4][1]+"\n";
                        Ensamblador+="  MOV "+TOKENS[i][1]+",AL\n";
                     break;
                    case "/":
                        Ensamblador+="  MOV AL,"+TOKENS[i+2][1]+"\n";
                        Ensamblador+="  MUL AL,"+TOKENS[i+4][1]+"\n";
                        Ensamblador+="  DIV BL\n";
                        Ensamblador+="  MOV "+TOKENS[i][1]+",AL\n";
                     break;
                }
                i=i+5;
            }
            else if (TOKENS[i][0].equals("VARIABLE") && TOKENS[i + 1][1].equals(":=")) {
                Ensamblador += "  MOV " + TOKENS[i][1] + "," + TOKENS[i + 2][1] + "\n";
                i = i + 3;
            } else if (TOKENS[i][1].equals("SI")) {
                switch (TOKENS[i + 2][1]) { //Optimizado
                    case ">":
                        Ensamblador += "  MOV AL," + TOKENS[i + 1][1] + "\n";
                        Ensamblador += "  CMP AL," + TOKENS[i + 3][1] + "\n";
                        Ensamblador += "  JGT " + Etiquetas[e] + "\n";
                        e++;
                        Ensamblador += "  JMP " + Etiquetas[e] + "\n";
                        break;
                    case "<":
                        Ensamblador += "  MOV AL," + TOKENS[i + 1][1] + "\n";
                        Ensamblador += "  CMP AL," + TOKENS[i + 3][1] + "\n";
                        Ensamblador += "  JLT " + Etiquetas[e] + "\n";
                        e++;
                        Ensamblador += "  JMP " + Etiquetas[e] + "\n";
                        break;
                    case "==":
                        Ensamblador += "  MOV AL," + TOKENS[i + 1][1] + "\n";
                        Ensamblador += "  CMP AL," + TOKENS[i + 3][1] + "\n";
                        Ensamblador += "  JEQ " + Etiquetas[e] + "\n";
                        e++;
                        Ensamblador += "  JMP " + Etiquetas[e] + "\n";
                        e++;
                        break;
                    default:
                        break;
                }
                Ensamblador += Etiquetas[e - 1];
                Ensamblador += "  MOV " + TOKENS[i + 5][1] + "," + TOKENS[i + 7][1] + "\n";
                Ensamblador += "  JMP " + Etiquetas[e + 1] + "\n";
                Ensamblador += Etiquetas[e];
                Ensamblador += "  MOV " + TOKENS[i + 10][1] + "," + TOKENS[i + 12][1] + "\n";
                Ensamblador += Etiquetas[e + 1];
                i = i + 12 + 4;
                e = e + 2;
            }
            else if(TOKENS[i][1].equals("READ")){
                Ensamblador+="  MOV AH,01H\n";
                Ensamblador+="  MOV "+TOKENS[i+2][1]+",AH\n";
                i=i+4;
            }
            else if(TOKENS[i][1].equals("WRITE")){
                Ensamblador+="  MOV AH,09H\n";
                Ensamblador+="  LEA DX,"+TOKENS[i+2][1]+"\n";
                i=i+4;
            }
            else if(TOKENS[i][1].equals("FOR")){
                Ensamblador+=" MOV "+TOKENS[i+2][1]+","+TOKENS[i+4][1]+"\n";
                Ensamblador+= Etiquetas[e];
                String et=Etiquetas[e];
                e++;
                String auxB=""; 
               switch(TOKENS[i+13][1]){
                    case "*":
                        auxB+="  MOV AL,"+TOKENS[i+12][1]+"\n";
                        auxB+="  MUL AL,"+TOKENS[i+14][1]+"\n";
                        auxB+="  MOV "+TOKENS[i+10][1]+",AL\n";
                    break;
                    case "+":
                        auxB+="  MOV AL,"+TOKENS[i+12][1]+"\n";
                        auxB+="  ADD AL,"+TOKENS[i+14][1]+"\n";
                        auxB+="  MOV "+TOKENS[i+10][1]+",AL\n";
                     break;
                    case "-":
                        auxB+="  MOV AL,"+TOKENS[i+12][1]+"\n";
                        auxB+="  SUB AL,"+TOKENS[i+14][1]+"\n";
                        auxB+="  MOV "+TOKENS[i+10][1]+",AL\n";
                     break;
                    case "/":
                        auxB+="  MOV AL,"+TOKENS[i+12][1]+"\n";
                        auxB+="  MUL AL,"+TOKENS[i+14][1]+"\n";
                        auxB+="  DIV BL\n";
                        auxB+="  MOV "+TOKENS[i+10][1]+",AL\n";
                     break;
                     default:
                        break;
                     
                }
                switch (TOKENS[i + 7][1]) { //Optimizado
                    case ">":
                        auxB += "  MOV AL," + TOKENS[i + 6][1] + "\n";
                        auxB += "  CMP AL," + TOKENS[i + 8][1] + "\n";
                        auxB += "  JGT " + et+"\n";
                        break;
                    case "<":
                        auxB += "  MOV AL," + TOKENS[i + 6][1] + "\n";
                        auxB += "  CMP AL," + TOKENS[i + 8][1] + "\n";
                        auxB += "  JLT " + et+"\n";
                        break;
                    case "==":
                        auxB += "  MOV AL," + TOKENS[i + 6][1] + "\n";
                        auxB += "  CMP AL," + TOKENS[i + 8][1] + "\n";
                        auxB += "  JEQ " + et+"\n";
                        break;
                    default:
                        break;
                }     
                             
                i=i+14;
                while (i < cont2) {
            if(TOKENS[i][0].equals("VARIABLE") && TOKENS[i+3][0].equals("OPERADOR")){
                switch(TOKENS[i+3][1]){
                    case "*":
                        Ensamblador+="  MOV AL,"+TOKENS[i+2][1]+"\n";
                        Ensamblador+="  MUL AL,"+TOKENS[i+4][1]+"\n";
                        Ensamblador+="  MOV "+TOKENS[i][1]+",AL\n";
                    break;
                    case "+":
                        Ensamblador+="  MOV AL,"+TOKENS[i+2][1]+"\n";
                        Ensamblador+="  ADD AL,"+TOKENS[i+4][1]+"\n";
                        Ensamblador+="  MOV "+TOKENS[i][1]+",AL\n";
                     break;
                    case "-":
                        Ensamblador+="  MOV AL,"+TOKENS[i+2][1]+"\n";
                        Ensamblador+="  SUB AL,"+TOKENS[i+4][1]+"\n";
                        Ensamblador+="  MOV "+TOKENS[i][1]+",AL\n";
                     break;
                    case "/":
                        Ensamblador+="  MOV AL,"+TOKENS[i+2][1]+"\n";
                        Ensamblador+="  MUL AL,"+TOKENS[i+4][1]+"\n";
                        Ensamblador+="  DIV BL\n";
                        Ensamblador+="  MOV "+TOKENS[i][1]+",AL\n";
                     break;
                    default:
                        break;
                }
                i=i+5;
            }
            else if (TOKENS[i][0].equals("VARIABLE") && TOKENS[i + 1][1].equals(":=")) {
                Ensamblador += "  MOV " + TOKENS[i][1] + "," + TOKENS[i + 2][1] + "\n";
                i = i + 3;
            } else if (TOKENS[i][1].equals("SI")) {
                switch (TOKENS[i + 2][1]) { //Optimizado
                    case ">":
                        Ensamblador += "  MOV AL," + TOKENS[i + 1][1] + "\n";
                        Ensamblador += "  CMP AL," + TOKENS[i + 3][1] + "\n";
                        Ensamblador += "  JGT " + Etiquetas[e] + "\n";
                        e++;
                        Ensamblador += "  JMP " + Etiquetas[e] + "\n";
                        break;
                    case "<":
                        Ensamblador += "  MOV AL," + TOKENS[i + 1][1] + "\n";
                        Ensamblador += "  CMP AL," + TOKENS[i + 3][1] + "\n";
                        Ensamblador += "  JLT " + Etiquetas[e] + "\n";
                        e++;
                        Ensamblador += "  JMP " + Etiquetas[e] + "\n";
                        break;
                    case "==":
                        Ensamblador += "  MOV AL," + TOKENS[i + 1][1] + "\n";
                        Ensamblador += "  CMP AL," + TOKENS[i + 3][1] + "\n";
                        Ensamblador += "  JEQ " + Etiquetas[e] + "\n";
                        e++;
                        Ensamblador += "  JMP " + Etiquetas[e] + "\n";
                        e++;
                        break;
                    default:
                        break;
                }
                Ensamblador += Etiquetas[e - 1];
                Ensamblador += "  MOV " + TOKENS[i + 5][1] + "," + TOKENS[i + 7][1] + "\n";
                Ensamblador += "  JMP " + Etiquetas[e + 1] + "\n";
                Ensamblador += Etiquetas[e];
                Ensamblador += "  MOV " + TOKENS[i + 10][1] + "," + TOKENS[i + 12][1] + "\n";
                Ensamblador += Etiquetas[e + 1];
                i = i + 12 + 4;
                e = e + 2;
            }
            else if(TOKENS[i][1].equals("READ")){
                Ensamblador+="  MOV AH,01H\n";
                Ensamblador+="  MOV "+TOKENS[i+2][1]+",AH\n";
                i=i+4;
            }
            else if(TOKENS[i][1].equals("WRITE")){
                Ensamblador+="  MOV AH,09H\n";
                Ensamblador+="  LEA DX,"+TOKENS[i+2][1]+"\n";
                i=i+4;
            }
            else if(TOKENS[i][1].equals("}")) {
                i++;
            break;}
                else i++;
            }
                Ensamblador+=auxB;
            }
            else {
                i++;
            }
        }
       
        Ensamblador += " END "+TOKENS[2][1]+"\n";
        return Ensamblador;
    }
    /*
body1000
Principal
{
SI A > 0 entonces
S1 := 1;
De lo contrario
S2 := 2;
FIN SI;
}
/////Nivel intermedio
body1000
Principal
{
Si A > 0 goto etiq1
goto etiq2
etiqueta etiq1
S1 = 1
goto etiq3
etiqueta etiq2
S2 = 2
etiqueta etiq3
}
////Nivel ensamblador
Etiqueta (body1000) START X (localidad de memoria aleatoria)
% declarar variables
Principal JEQ EXIT
% Utilizar saltos condicionales JEQ, JGT, JLT
% Marcar salidas
            JLT EXIT
            END PRINCIPAL
END Etiqueta
     */
}
