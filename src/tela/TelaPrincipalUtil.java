package tela;

import java.util.Scanner;

import atores.AtorCliente;
import atores.AtorSistema;
import classes.classesDePiso.PisoDeEstacionamento;
import classes.classesDeVeiculo.VeiculoCaminhao;
import classes.classesDeVeiculo.VeiculoCarro;
import classes.classesDeVeiculo.VeiculoEletrico;
import classes.classesDeVeiculo.VeiculoMotocicleta;
import classes.classesDeVeiculo.VeiculoVan;
import classesAbstratas.Vaga;
import classesAbstratas.Veiculo;
import estacionamento.Estacionamento;

import tipos.TipoDeVaga;

public class TelaPrincipalUtil {

    public static void telaPrincipal() {

        Estacionamento estacionamento = Estacionamento.getInstance();

        AtorSistema sistema = AtorSistema.getInstance();

        System.out.println("\n\tTELA PRINCIPAL");
        System.out.println("------------------------------");
        System.out.println("| [ 1 ] Fazer login como adm");
        System.out.println("| [ 2 ] Novo cliente");
        System.out.println("| [ 3 ] Retirar carro");
        System.out.println("| [ 4 ] Exibir painel do estacionamento");
        System.out.println("------------------------------");
        System.out.printf("Digite o número: ");

        Scanner scanner = new Scanner(System.in);
        String opcao = scanner.next();

        if (opcao.equals("1")) {
            estacionamento.fazerLogin();
        } else if (opcao.equals("2")) {
            System.out.println("Bem vindo! Esses são os pisos disponíveis: ");
            estacionamento.imprimirEstacionamento();
            System.out.println("Informe seu nome, o piso e o tipo de veículo");
            System.out.printf("Nome: ");
            String nome = scanner.next();
            for (AtorCliente clienteElem : estacionamento.getListaDeClientes()) {
                if (clienteElem.getNome().equals(nome)) {
                    System.out.println("[ERRO] Cliente já cadastrado...");
                    telaPrincipal();
                }
            }
            System.out.printf("Piso: ");
            String pisoId = scanner.next();
            PisoDeEstacionamento piso = null;

            for (PisoDeEstacionamento pisoElem : estacionamento.getListaDePisos()) {
                if (pisoElem.getPisoId().equals(pisoId)) {
                    piso = pisoElem;

                    break;
                }
            }
            if (piso == null) {
                System.out.println("[ERRO] O piso não existe...");
                telaPrincipal();
            } else {
                sistema.exibirPainel(piso);
            }

            System.out.printf("Digite o tipo de veículo \n[1] Carro\n[2] Caminhão\n[3] Van\n[4] Elétrico\n[5] Motocicleta: \nOpção: ");
            String tipoDeVeiculoInt = scanner.next();
            Veiculo tipoDeVeiculo = null;
            TipoDeVaga tipoDeVaga = null;

            switch (tipoDeVeiculoInt) {
                case "1":
                    tipoDeVeiculo = new VeiculoCarro();
                    tipoDeVaga = TipoDeVaga.PEQUENA;
                    break;
                case "2":
                    tipoDeVeiculo = new VeiculoCaminhao();
                    tipoDeVaga = TipoDeVaga.GRANDE;
                    break;
                case "3":
                    tipoDeVeiculo = new VeiculoVan();
                    tipoDeVaga = TipoDeVaga.GRANDE;
                    break;
                case "4":
                    tipoDeVeiculo = new VeiculoEletrico();
                    tipoDeVaga = TipoDeVaga.ELETRICO;
                    break;
                case "5":
                    tipoDeVeiculo = new VeiculoMotocicleta();
                    tipoDeVaga = TipoDeVaga.MOTOCICLETA;
                    break;
                default:
                    System.out.println("[ERRO] Não existe vaga para esse veículo...");
                    telaPrincipal();
                    break;
            }

            Vaga vaga = estacionamento.verificarDisponibilidadeDaVaga(piso, tipoDeVaga);

            if (vaga != null) {
                AtorCliente cliente = new AtorCliente(nome, piso, tipoDeVeiculo, vaga);
                estacionamento.adicionarCliente(cliente);
                cliente.gerarTicket();
            } else {
                System.out.println("[ERRO] Não há vaga disponivel para esse veiculo...");
                telaPrincipal();
            }

        } else if (opcao.equals("3")) {

            System.out.printf("Por favor, digite seu nome: ");
            String nomeDigitado = scanner.next();
            AtorCliente cliente = null;

            for (AtorCliente clienteElem : estacionamento.getListaDeClientes()) {
                if (clienteElem.getNome().equals(nomeDigitado)) {
                    cliente = clienteElem;
                    break;
                }
            }

            if (cliente == null) {
                System.out.println("[ERRO] Cliente não cadastrado...");
                telaPrincipal();
            } else {
                cliente.pagarTicket();
            }

        }

        else if (opcao.equals("4")) {
            estacionamento.imprimirEstacionamento();
            TelaPrincipalUtil.telaPrincipal();
        } else {
            System.out.println("\n[ERRO] Opção inválida.\n");
            telaPrincipal();
        }

    }
}
