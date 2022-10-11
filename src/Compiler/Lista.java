/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Compiler;

public class Lista {
    private Nodo_V inicio; // Puntero que indica el inicio de la lista o conocida tambien
    private int Longitud; // Variable para registrar el tamaño de la lista.

    public void Lista(){
        inicio = null;
        Longitud = 0;
    }

    public boolean esVacia(){
        return inicio == null;
    }
    public int getLongitud(){
        return Longitud;
    }
    public void AgregarElemento(String Tipo, String Nombre){
        Nodo_V nuevo = new Nodo_V();// Define un nuevo nodo.
        nuevo.setTipo(Tipo); // Agrega al Tipo al nodo.
        nuevo.setNombre(Nombre);// Agrega el Nombre al nodo.
        if (esVacia()) {// Consulta si la lista esta vacia.
            inicio = nuevo;// Si esta vacia, inicia la lista
        } else{
            Nodo_V aux = inicio; // Crea ua copia de la lista.
            // Recorre la lista hasta llegar al ultimo nodo.
            while(aux.getSiguiente() != null){
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo); // Agrega el nuevo nodo al final de la lista.
        }
        Longitud++; // Incrementa el contador de tamaño de la lista
    }

    public void eliminar(){
        inicio = null;// Elimina el Tipo y la ID a los demas nodos.
        Longitud = 0;// Reinicia el contador de tamaño de la lista a 0.
    }
    public String listar(){
      String l="";
        if (!esVacia()) { // Verifica si la lista contiene elementos
            Nodo_V aux = inicio;  // Crea una copia de la lista.
            int i = 0;// Posicion de los elementos de la lista.
            while(aux != null){// Recorre la lista hasta el final.
                l=l+aux.getTipo()+" "+aux.getNombre()+";";
                aux = aux.getSiguiente();
                i++;// Avanza al siguiente nodo e Incrementa el contador de la posión.
            }
        }
        return l;
    }
    public int ObtenerID(String Nombre){
      int ID=1;
        for(int i=0; i<Nombre.length();i++){
          if(i==(Nombre.length())-1){ ID=ID*(int)Nombre.charAt(i);
          }
          else ID=ID+(int)Nombre.charAt(i);
        }
        return ID;
    }
    public boolean existe(String n){
      String auxS[]=listar().split(";");
      String Hash[][]= new String [auxS.length][];
      if(!esVacia()){
      for(int i=0;i<Hash.length;i++){
        Hash[i]=auxS[i].split("\\s+");
        if(ObtenerID(Hash[i][1])==ObtenerID(n)) return true;
      }}
      return false;
    }


}
