/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercado;

/**
 *
 * @author Richiely Batista, Filipe Maciel
 */
public class Balanca{
    
    //@ requires pesoDoProduto >= 0 && quantidade >= 0;
    //@ ensures \result >= 0;
    //@ ensures \result == pesoDoProduto * quantidade;
    //@ pure
    public static double calcularValorPorPeso(double pesoDoProduto, double quantidade){
        return pesoDoProduto*quantidade;
    }
    
    //@ requires valorDoProduto >= 0 && quantidade >= 0;
    //@ ensures \result >= 0;
    //@ ensures \result == valorDoProduto * quantidade;
    //@ pure
    public static double calcularValorPorItem(double valorDoProduto, int quantidade){
        return valorDoProduto*quantidade;
    }
}
