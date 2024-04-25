public class Registro {
    private String pedido;
    private String valor;
    private String ano;

    public Registro(String pedido, String valor, String ano) {
        this.pedido = pedido;
        this.valor = valor;
        this.ano = ano;
    }

    
    public String getPedido() {
        return pedido;
    }


    public void setPedido(String pedido) {
        this.pedido = pedido;
    }


    public String getValor() {
        return valor;
    }


    public void setValor(String valor) {
        this.valor = valor;
    }


    public String getAno() {
        return ano;
    }


    public void setAno(String ano) {
        this.ano = ano;
    }


    
    
   
}
