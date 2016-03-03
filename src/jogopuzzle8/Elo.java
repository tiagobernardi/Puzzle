/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogopuzzle8;

import java.util.ArrayList;

/**
 *
 * @author Tiago
 */
public class Elo {

    int[][] estado;
    ArrayList<Elo> filhos = new ArrayList<Elo>();
    Elo pai;

    public Elo(int[][] estado) {
        this.estado = estado;
        filhos = null;
        pai = null;

    }

    public int[][] getEstado() {
        return estado;
    }

    public void setEstado(int[][] estado) {
        this.estado = estado;
    }

    public ArrayList<Elo> getFilhos() {
        return filhos;
    }

    public void setFilhos(ArrayList<Elo> filhos) {
        this.filhos = filhos;
        if(filhos!=null){
            for(Elo f:filhos){
                f.pai=this;
            }
        }
    }

    public Elo getPai() {
        return pai;
    }

    public void setPai(Elo pai) {
        this.pai = pai;
    }

}
