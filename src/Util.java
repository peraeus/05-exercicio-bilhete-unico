import java.text.DecimalFormat;

import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.Double.parseDouble;

public class Util {
    private Bilhete[] bilhete = new Bilhete[3];
    private int index = 0;

    public void menuPrincipal() {
        int opcao;
        String menu = "1. Administrador\n2. Usuario\n3. Finalizar\n";
        do {
            opcao = parseInt(showInputDialog(menu));
            if (opcao < 1 || opcao > 3) {
                showMessageDialog(null, "Opção inválida");
            } else {
                switch (opcao) {
                    case 1:
                        menuAdministrador();
                        break;
                    case 2:
                        menuUsuario();
                        break;
                }
            }
        } while (opcao != 3);


    }

    // menu com as funcionalidades do admin
    private void menuAdministrador() {
        int opcao;
        String menu = "1. Emitir Bilhete\n 2. Listar os bilhetes\n" +
                " 3.Remover bilhete\n 4. Sair";
        do {
            opcao = parseInt(showInputDialog(menu));
            if (opcao < 1 || opcao > 4) {
                showMessageDialog(null, "Opção inválida");
            } else {
                switch (opcao) {
                    case 1:
                        emitirBilhete();
                        break;
                    case 2:
                        listarBilhetes();
                        break;
                    case 3:
                        removerBilhetes();
                }
            }
        } while (opcao != 4);
    }

    private void emitirBilhete() {
        String nome;
        String perfil;
        long cpf;
        if (index < bilhete.length) {
            nome = showInputDialog("Nome");
            cpf = parseLong(showInputDialog("CPF"));
            perfil = showInputDialog("Tipo de tarifa(perfil) --> Professor ou Estudante ou Comum");

            bilhete[index] = new Bilhete(new Usuario(nome, cpf, perfil));
            index++;
        } else {
            showMessageDialog(null, "Procure a administração");
        }

    }

    // metodo para listar os dados do bilhete --> nome, cpf, saldo e perfil
    private void listarBilhetes() {
        DecimalFormat df = new DecimalFormat("0.00");
        String aux = "";
        for (int i = 0; i < index; i++) {
            aux += "Nome do usuário: " + bilhete[i].usuario.nome + "\n";
            aux += "CPF do usuário: " + bilhete[i].usuario.cpf + "\n";
            aux += "Número do bilhete: " + bilhete[i].numero + "\n";
            aux += "Saldo do bilhete: " + df.format(bilhete[i].saldo) + "\n";
            aux += "Perfil do usuário: " + bilhete[i].usuario.perfil + "\n";
            aux += "------------------------------------------\n";

        }

        showMessageDialog(null, aux);

    }

    private void removerBilhetes() {

    }

    // menu com as funcionalidades do usuario do bilhete
    private void menuUsuario() {
        int opcao;
        String menu = "1. Consultar saldo\n 2. Carregar bilhete\n" +
                " 3. Passar na catraca\n 4. Sair";
        do {
            opcao = parseInt(showInputDialog(menu));
            if (opcao < 1 || opcao > 4) {
                showMessageDialog(null, "Opção inválida");
            } else {
                switch (opcao) {
                    case 1:
                        consultarSaldo();
                        break;
                    case 2:
                        carregarBilhete();
                        break;
                    case 3:
                        passarCatraca();
                }
            }
        } while (opcao != 4);
    }

    // metodo auxiliar para os outros metodos da aplicação
    private int pesquisarCPF() {
        long cpf = parseLong(showInputDialog("CPF"));
        for (int i = 0; i < index; i++) {
            if (bilhete[i].usuario.cpf == cpf) {
                return i;
            }
        }
        showMessageDialog(null, cpf + " não encontrado");
        return -1;
    }


    // metodo para consultar o saldo
    private void consultarSaldo() {
        int posicao = pesquisarCPF();
        if (posicao != -1) {
            showMessageDialog(null, "Saldo = R$ " + bilhete[posicao].consultarSaldo());
        }

    }

    // metodo para carregar bilhete --> com valor informado pelo usuario
    private void carregarBilhete() {
        double valor;
        int posicao = pesquisarCPF();
        if (posicao != -1) {
            valor = parseDouble(showInputDialog("Valor para carregar bilhete"));
            bilhete[posicao].carregar(valor);
        }

    }

    // metodo para passar na catraca --> simulação
    private void passarCatraca() {
        int posicao = pesquisarCPF();
        if (posicao != -1) {
            showMessageDialog(null, bilhete[posicao].passarCatraca());
        }

    }


}




