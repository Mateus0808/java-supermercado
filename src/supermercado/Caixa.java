
package supermercado;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Caixa {
    //@ public invariant vendas != null;
    //@ public invariant numeroDoCaixa > 0;
    private /*@ spec_public @*/ ArrayList<Venda> vendas;
    private /*@ spec_public nullable @*/ Funcionario operadorCaixa;
    private /*@ spec_public @*/ int numeroDoCaixa;
    
    //@ requires numero > 0 && operador != null;
    //@ ensures vendas.isEmpty();
    //@ ensures operadorCaixa != null;
    //@ ensures numeroDoCaixa == numero;
    //@ pure
    public Caixa(int numero, OperadorDeCaixa operador){
        this.numeroDoCaixa = numero;
        this.operadorCaixa = operador;
        vendas = new ArrayList<Venda>();
    }    

    //@ requires numero > 0;
    //@ ensures vendas.isEmpty() && this.numeroDoCaixa == numero && operadorCaixa == null;
    //@ pure
    public Caixa(int numero){
        this.numeroDoCaixa = numero;
        this.operadorCaixa = null;
        vendas = new ArrayList<Venda>();
    } 
    
    public void relatorioCaixa(){
        if (vendas.size() > 0) {
            Iterator it = vendas.iterator();
            while(it.hasNext()){
                Venda venda = (Venda) it.next();
                Utilitario.ImprimaMensagem(venda.dadosVenda());
            }
        }else{
            Utilitario.ImprimaMensagem("*           Não há vendas registradas nesse caixa              *");
        }
    }
    
    public ArrayList<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(ArrayList<Venda> vendas) {
        this.vendas = vendas;
    }
    
    public int getNumeroDoCaixa() {
        return numeroDoCaixa;
    }

    public void setNumeroDoCaixa(int numeroDoCaixa) {
        this.numeroDoCaixa = numeroDoCaixa;
    }

    //@ public normal_behavior
    //@   ensures (\result == null || \result instanceof Funcionario);
    //@ pure helper
    public Funcionario getOperadorCaixa() {
        return operadorCaixa;
    }

    //@ assignable this.operadorCaixa;
    //@ requires operadorCaixa != null && operadorCaixa instanceof Funcionario;
    //@ ensures this.operadorCaixa == operadorCaixa;
    public void setOperadorCaixa(Funcionario  operadorCaixa) {
        this.operadorCaixa = operadorCaixa;
    }
    
    public void cancelarVenda(){
        
    }

    //@ requires valorDaUnidadeProduto >= 0 && quantidade >= 0;
    //@ ensures \result == valorDaUnidadeProduto * quantidade;
    private double calcularValorPorItem(double valorDaUnidadeProduto, int quantidade){
        return Balanca.calcularValorPorItem(valorDaUnidadeProduto, quantidade);
    }
    
    //@ requires valorDoPeso >= 0 && quantidade >= 0;
    //@ ensures \result == valorDoPeso * quantidade;
    private double calcularValorPorPeso(double valorDoPeso, double quantidade){
        return Balanca.calcularValorPorPeso(valorDoPeso, quantidade);
    }
    
    @Override
    public String toString() {
         return "Caixa " + numeroDoCaixa;        
    }
    
    //@ requires cliente != null;
    //@ ensures \result != null ==> vendas.contains(\result);
    //@ ensures (\result != null && \result instanceof Venda) || \result == null;
    public Venda iniciarVenda(Cliente cliente){
        Venda venda = new Venda(this, cliente);
        venda.vender();
        boolean pagamento = venda.formaDePagamento();
        if(pagamento){
            vendas.add(venda);
        }else{
            Utilitario.ImprimaMensagem("*  !!!! FALHA AO COMPRAR !!!!  *");
            return null;
        }
       
        return venda;
    }
}
