package estacionamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Objects;

import atores.AtorAdministrador;
import atores.AtorAtendente;
import atores.AtorCliente;
import classes.classesDePiso.PisoDeEstacionamento;
import classes.classesDeVagas.VagaEletrico;
import classes.classesDeVagas.VagaGrande;
import classes.classesDeVagas.VagaMotocicleta;
import classes.classesDeVagas.VagaPequena;
import classesAbstratas.Vaga;
import tela.TelaPrincipalUtil;
import tipos.DadosDaConta;
import tipos.Ticket;
import tipos.TipoDeVaga;

public class Estacionamento {

    private static Estacionamento instancia;

    private String nome;
    private String endereço;
    private int qntPisos = 0;

    private List<PisoDeEstacionamento> listaDePisos;

    private List<Ticket> listaDeTickets;

    private List<AtorAdministrador> listaDeContasDeAdministrador;
    private List<AtorAtendente> listaDeContasDeAtendente;
    private List<AtorCliente> listaDeClientes;

    public Estacionamento() {

        listaDePisos = new ArrayList<>();
        listaDeTickets = new ArrayList<>();
        listaDeContasDeAdministrador = new ArrayList<>();
        listaDeContasDeAtendente = new ArrayList<>();
        listaDeClientes = new ArrayList<>();

        this.nome = "EstacionamentoNome";
        this.endereço = "Endereço";

    }

    public static Estacionamento getInstance() {
        if (instancia == null) {
            instancia = new Estacionamento();
        }
        return instancia;
    }

    public List<AtorAdministrador> getListaDeContasDeAdministrador() {
        return listaDeContasDeAdministrador;
    }

    public boolean adicionarPiso(String pisoId) {

        for (PisoDeEstacionamento pisoElem : listaDePisos) {
            if (pisoElem.getPisoId().equals(pisoId)) {
                return false;
            }
        }

        PisoDeEstacionamento piso = new PisoDeEstacionamento(pisoId);

        AtorAtendente atendenteCriado = criarContaDeAtendente(piso);

        piso.criarPortal(atendenteCriado);

        listaDePisos.add(piso);
        setQntPisos(getQntPisos() + 1);

        return true;

    }

    public boolean removerPiso(String pisoId) {

        for (PisoDeEstacionamento piso : listaDePisos) {
            if (piso.getPisoId().equals(pisoId)) {
                listaDePisos.remove(piso);
                return true;
            }
        }

        return false;
    }

    public void adicionarTicket(Ticket ticket) {

        listaDeTickets.add(ticket);

    }

    public void removerTicket(Ticket ticket) {

        listaDeTickets.remove(ticket);

    }

    public void adicionarContaAdministrador(AtorAdministrador administrador) {

        listaDeContasDeAdministrador.add(administrador);

    }

    public void removerContaAdministrador(AtorAdministrador administrador) {
        listaDeContasDeAdministrador.remove(administrador);
    }

    public void adicionarContaAtendente(AtorAtendente atendente) {

        listaDeContasDeAtendente.add(atendente);
    }

    public void removerContaAtendente(AtorAtendente atendente) {

        listaDeContasDeAtendente.remove(atendente);
    }

    public void criarContaDeAdministrador() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("---- CRIAR NOVA CONTA DE ADM ----");
        System.out.printf("Login: ");
        String login = scanner.next();
        boolean verificarAdm = true;

        for (AtorAdministrador admElem : listaDeContasDeAdministrador) {
            if (admElem.getLogin().equals(login))
                verificarAdm = false;
        }

        if (verificarAdm) {
            System.out.printf("Senha: ");
            String senha = scanner.next();
            AtorAdministrador administrador = new AtorAdministrador(new DadosDaConta(login, senha));
            adicionarContaAdministrador(administrador);
            System.out.println("Conta de administrador criada com sucesso!");
        } else {
            System.out.println("Administrador já existente");
        }
    }

    public AtorAtendente criarContaDeAtendente(PisoDeEstacionamento piso) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("---- CRIAR NOVA CONTA DE ATENDENTE ----");
        System.out.printf("Novo login: ");
        String loginAtd = scanner.next();
        System.out.printf("Nova senha: ");
        String senhaAtd = scanner.next();
        DadosDaConta dadosAtd = new DadosDaConta(loginAtd, senhaAtd);
        AtorAtendente atendente = new AtorAtendente(dadosAtd, piso);
        adicionarContaAtendente(atendente);
        System.out.println("Conta de Atendente criada! ");
        return atendente;

    }

    public List<Ticket> getListaDeTickets() {
        return listaDeTickets;
    }

    public void adicionarCliente(AtorCliente cliente) {
        listaDeClientes.add(cliente);
    }

    public void removerCliente(AtorCliente cliente) {
        listaDeClientes.remove(cliente);
    }

    public void fazerLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\tFAZER LOGIN  ");
        System.out.printf("Login: ");
        String login = scanner.nextLine();
        System.out.printf("Senha: ");
        String senha = scanner.nextLine();

        boolean loginSucesso = false;
        for (AtorAdministrador adm : this.listaDeContasDeAdministrador) {
            String admLogin = adm.getLogin();
            if (admLogin != null && Objects.equals(admLogin, login) && Objects.equals(adm.getSenha(), senha)) {
                loginSucesso = true;
                break;
            }
        }

        if (loginSucesso) {
            System.out.println("Login bem-sucedido!");
            AtorAdministrador adm = new AtorAdministrador(new DadosDaConta(login, senha));
            adicionarContaAdministrador(adm);
            adm.telaDeAdm();
        } else {
            System.out.println("[ERRO] Login ou senha incorretos");
            TelaPrincipalUtil.telaPrincipal();
        }
    }

    public void setQntPisos(int qnt) {
        this.qntPisos = qnt;
    }

    public int getQntPisos() {
        return this.qntPisos;
    }

    public List<PisoDeEstacionamento> getListaDePisos() {
        return listaDePisos;
    }

    public List<AtorCliente> getListaDeClientes() {
        return listaDeClientes;
    }

    public Ticket escanearTicket(String ticketDigitado) {

        for (Ticket ticketElem : listaDeTickets) {
            if (ticketElem.getTicketId().equals(ticketDigitado)) {
                return ticketElem;
            }
        }
        return null;
    }

    public Vaga verificarDisponibilidadeDaVaga(PisoDeEstacionamento piso, TipoDeVaga tipoVaga) {

        for (Vaga vagaElem : piso.getListaDeVagas()) {
            if (vagaElem.getTipoDaVaga() == tipoVaga) {
                if (!vagaElem.getOcupada()) {
                    return vagaElem;
                }

            }
        }
        return null;

    }

    public void imprimirPisos() {
        System.out.println("----- PISOS DO ESTACIONAMENTO -----");

        for (PisoDeEstacionamento piso : listaDePisos) {
            System.out.println("\tPiso: " + piso.getPisoId());
        }
        System.out.println("-----------------------------------");

    }

    public void imprimirEstacionamento() {
        System.out.println("----------PAINEL DO ESTACIONAMENTO-----------");
        for (PisoDeEstacionamento piso : listaDePisos) {
            System.out.println("------------------------------------");
            System.out.println("\tPiso: \t" + piso.getPisoId());
            for (Vaga vaga : piso.getListaDeVagas()) {
                String tipoVagaString = "";
                if (vaga instanceof VagaPequena) {
                    tipoVagaString = "Pequena";
                } else if (vaga instanceof VagaGrande) {
                    tipoVagaString = "Grande";
                } else if (vaga instanceof VagaEletrico) {
                    tipoVagaString = "Elétrico";
                } else if (vaga instanceof VagaMotocicleta) {
                    tipoVagaString = "Motocicleta";
                }
                System.out.println("- " + tipoVagaString + " -> " + vaga.getOcupadaString());
            }
        }
        System.out.println("--------------------------------------------");

    }

    public void boasVindas() {
        System.out.printf("Bem-vindo ao estacionamento " + this.nome);
        System.out.printf("\nQue fica localizado em  " + this.endereço);

    }

}
