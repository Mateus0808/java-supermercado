
package supermercado;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Cliente {
    private /*@ spec_public @*/ CarrinhoDeCompras carrinho;
    
    //@ ensures carrinho != null;
    public Cliente(){
        this.carrinho = new CarrinhoDeCompras();
    }

    //@ ensures \result != null && \result instanceof CarrinhoDeCompras;
    //@ pure
    public CarrinhoDeCompras getCarrinho() {
        return carrinho;
    }

    //Para realizar a compra, o cliente escolhe um caixa disponível
    //@ requires caixa != null;
    //@ ensures true;
    public void realizarCompra(Caixa caixa){
        caixa.iniciarVenda(this);
    }
    
    //@ requires codigo != null;
    //@ ensures true;
    public void consultarValor(String codigo){
        Leitor.mostrarValorProduto(codigo);
    }
}
