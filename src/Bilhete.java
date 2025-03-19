import java.util.Random;

public class Bilhete {


    double saldo;
    static final double tarifaBase = 5.20;
    long numero;
    Usuario usuario;

    public Bilhete(Usuario usuario) {
        this.numero = gerarNumero(); // this opcional
        this.usuario = usuario; // this obrigatorio, pois o parametro tem o mesmo nome da var global
    }

    private long gerarNumero() {
        Random rd = new Random();
        return rd.nextLong(1000, 10000);
    }

    // metodo para carregar o bilhete
    public void carregar(double valor) {
        System.out.println("Valor de recarga: ");
        saldo += valor;
    }

    // metodo para consultar o saldo
    public double consultarSaldo() {
        return saldo;
    }

    // metodo para passar na catraca
    public String passarCatraca() {
        double debito = tarifaBase / 2;
        if (usuario.perfil.equalsIgnoreCase("comum")) {
            debito = tarifaBase;


            if (saldo >= debito) {
                saldo -= debito;
            }
            return "Catraca liberada!";
        }
        return "Saldo insulficiente";
    }
}