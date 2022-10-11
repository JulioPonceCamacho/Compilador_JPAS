/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Compiler;
public class Nodo_V {
    private String Tipo;
    private String Nombre;
    // Variable para enlazar los nodos.
    private Nodo_V siguiente;

    public void Nodo_V(){
        this.Tipo = "";
        this.Nombre="";
        this.siguiente = null;
    }

    // Métodos get y set para los atributos.

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    public Nodo_V getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo_V siguiente) {
        this.siguiente = siguiente;
    }
}
