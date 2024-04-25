public class Registro {
    private int pedido;
    private double valor;
    private int ano;

    
    public Registro(int pedido, double valor, int ano) {
        this.pedido = pedido;
        this.valor = valor;
        this.ano = ano;
    }
    
    public int getPedido() {
        return pedido;
    }
    public void setPedido(int pedido) {
        this.pedido = pedido;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    
}
