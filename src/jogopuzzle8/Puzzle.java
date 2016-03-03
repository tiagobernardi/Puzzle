/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogopuzzle8;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Tiago
 */
public class Puzzle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[][] inicio = {{4, 1, 0}, {2, 6, 3}, {7, 5, 8}};
        int[][] solucao = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Elo inicial = new Elo(inicio);
        Elo sol = BuscarSolucao(inicial, solucao);

        while (sol.pai != null) {
            imprimirEstado(sol.getEstado());
            System.out.println("---------------------");
            sol = sol.pai;
        }

    }

    public static Elo BuscarSolucao(Elo inicio, int[][] solucao) {
        ArrayList<Elo> expandidos = new ArrayList<>();
        ArrayList<Elo> visitados = new ArrayList<Elo>();
        expandidos.add(inicio);
        int cont = 0;
        while (expandidos.size() != 0) {
            Elo revisar = expandidos.remove(0);
            imprimirEstado(revisar.getEstado());
            int[] pzero = localizarPosicaoZero(revisar.getEstado());
            System.out.println("Interação Número: " + (++cont));
            if (Arrays.deepEquals(revisar.getEstado(), solucao)) {
                System.out.println("**** Solução Encontrada");
                return revisar;
            }
            ArrayList<Elo> filhos = new ArrayList<Elo>();
            visitados.add(revisar);

            if (pzero[0] != 0) {
                Elo filho = new Elo(clonar(revisar.getEstado()));
                int acima = filho.getEstado()[pzero[0] - 1][pzero[1]];
                filho.getEstado()[pzero[0]][pzero[1]] = acima;
                filho.getEstado()[pzero[0] - 1][pzero[1]] = 0;
                if (!estaEmVisitados(visitados, filho)) {
                    expandidos.add(filho);
                    filhos.add(filho);
                }

            }
            if (pzero[0] != 2) {
                Elo filho = new Elo(clonar(revisar.getEstado()));
                int abaixo = filho.getEstado()[pzero[0] + 1][pzero[1]];
                filho.getEstado()[pzero[0]][pzero[1]] = abaixo;
                filho.getEstado()[pzero[0] + 1][pzero[1]] = 0;
                if (!estaEmVisitados(visitados, filho)) {
                    expandidos.add(filho);
                    filhos.add(filho);
                }

            }
            if (pzero[1] != 0) {
                Elo filho = new Elo(clonar(revisar.getEstado()));
                int esquerda = filho.getEstado()[pzero[0]][pzero[1] - 1];
                filho.getEstado()[pzero[0]][pzero[1]] = esquerda;
                filho.getEstado()[pzero[0]][pzero[1] - 1] = 0;
                if (!estaEmVisitados(visitados, filho)) {
                    expandidos.add(filho);
                    filhos.add(filho);
                }

            }
            if (pzero[1] != 2) {
                Elo filho = new Elo(clonar(revisar.getEstado()));
                int direita = filho.getEstado()[pzero[0]][pzero[1] + 1];
                filho.getEstado()[pzero[0]][pzero[1]] = direita;
                filho.getEstado()[pzero[0]][pzero[1] + 1] = 0;
                if (!estaEmVisitados(visitados, filho)) {
                    expandidos.add(filho);
                    filhos.add(filho);
                }
                revisar.setFilhos(filhos);
            }

        }
        return null;
    }

    public static void imprimirEstado(int[][] estado) {
        for (int i = 0; i < estado.length; i++) {
            for (int j = 0; j < estado.length; j++) {
                System.out.print("[" + estado[i][j] + "]");

            }
            System.out.println("");
        }

    }

    private static int[] localizarPosicaoZero(int[][] estado) {
        int[] posicao = new int[2]; //Guardar a posição de i e j
        for (int i = 0; i < estado.length; i++) {
            for (int j = 0; j < estado.length; j++) {
                if (estado[i][j] == 0) {
                    posicao[0] = i;
                    posicao[1] = j;
                }

            }

        }
        System.out.println("A posição do espaço é: " + posicao[0] + "," + posicao[1]);
        return posicao;
    }

    private static int[][] clonar(int[][] estado) {
        int[][] clon = new int[estado.length][estado.length];
        for (int i = 0; i < estado.length; i++) {
            for (int j = 0; j < estado.length; j++) {
                clon[i][j] = estado[i][j];

            }

        }
        return clon;
    }

    private static boolean estaEmVisitados(ArrayList<Elo> visitados, Elo filho) {
        for (Elo v : visitados) {
            if (Arrays.deepEquals(v.getEstado(), filho.getEstado())) {
                return true;

            }
        }
        return false;
    }
}
