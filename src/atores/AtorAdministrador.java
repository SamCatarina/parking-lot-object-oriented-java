package atores;

import java.util.List;
import java.util.Scanner;

import classes.classesDePiso.PisoDeEstacionamento;
import classesAbstratas.Ator;
import classesAbstratas.Vaga;
import estacionamento.Estacionamento;
import tela.TelaPrincipalUtil;
import tipos.DadosDaConta;
import tipos.TipoDeVaga;

public class AtorAdministrador extends Ator {

    private Estacionamento estacionamento;
    private String login, senha;

    public AtorAdministrador(DadosDaConta dadosDaConta) {
        super();
        this.login = dadosDaConta.getLogin();
        this.senha = dadosDaConta.getSenha();

        estacionamento = Estacionamento.getInstance();
    }

    public void telaDeAdm() {

        System.out.println("Bem vindo a tela de Administrador! ");
        System.out.println("------------------------------");
        System.out.println("| [ 1 ] criar nova conta");
        System.out.println("| [ 2 ] excluir conta");
        System.out.println("| [ 3 ] criar novo piso");
        System.out.println("| [ 4 ] destruir piso");
        System.out.println("| [ 5 ] criar nova vaga");
        System.out.println("| [ 6 ] destruir vaga");
        System.out.println("| [ 7 ] sair da conta");
        System.out.println("-----------------------");
        System.out.printf("Digite o número: ");

        Scanner scanner = new Scanner(System.in);
        String opcaoAdm = scanner.next();

        switch (opcaoAdm) {
            case "1":
                criarConta();
                break;
            case "2":
                destruirConta();
                break;
            case "3":
                criarPiso();
                break;
            case "4":
                destruirPiso();
                break;
            case "5":
                criarVaga();
                break;
            case "6":
                destruirVaga();
                break;
            case "7":
                sairDaConta();
                break;
            default:

                System.out.println("[ERRO] Opção inválida...");
                telaDeAdm();
                break;
        }

    }

    public void criarConta() {

        estacionamento.criarContaDeAdministrador();

        telaDeAdm();
    }

    public void destruirConta(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.printf("Digite o login da conta que deseja excluir:");

        String loginDigitado = scanner.next();

        if(loginDigitado.equals(getLogin())){
            System.out.println("[ERRO] Você não pode excluir sua própria conta...");
            telaDeAdm();
        }else{

            for(AtorAdministrador admElem : estacionamento.getListaDeContasDeAdministrador()){
                if(admElem.getLogin().equals(loginDigitado)){
                    estacionamento.removerContaAdministrador(admElem);
                    System.out.println("Administrador removido! ");
                    telaDeAdm();
                }
            }

            System.out.println("[ERRO] Conta não encontrada");
            telaDeAdm();

            
        }

    }

    public void criarPiso() {

        System.out.printf("Digite o número do piso: ");
        Scanner scanner = new Scanner(System.in);

        String pisoId = scanner.next();

        boolean verificarPiso = estacionamento.adicionarPiso(pisoId);

        if (!verificarPiso) {
            System.out.println("[ERRO] O piso já existe!");
            telaDeAdm();
        } else {

            System.out.println("Piso criado! ");

            telaDeAdm();
        }

    }

    public void destruirPiso() {

        System.out.printf("Digite o número (id) do piso: ");
        Scanner scanner = new Scanner(System.in);

        String pisoId = scanner.next();

        boolean remocao = this.estacionamento.removerPiso(pisoId);
        if (remocao) {
            System.out.println("O piso foi removido!");
        } else {
            System.out.println("[ERRO] não foi possível remover o piso...");
        }

        telaDeAdm();
    }

    public void criarVaga() {

        estacionamento.imprimirPisos();

        PisoDeEstacionamento piso = null;

        Scanner scanner = new Scanner(System.in);

        System.out.printf("Digite o id do piso: ");
        String pisoId = scanner.next();

        List<PisoDeEstacionamento> listaDePisos = estacionamento.getListaDePisos();

        for (PisoDeEstacionamento pisoElem : listaDePisos) {
            if (pisoElem.getPisoId().equals(pisoId)) {
                piso = pisoElem;
                break;
            }
        }

        if (piso == null) {
            System.out.println("[ERRO] O piso não existe...");
            telaDeAdm();
        }

        boolean criacaoDaVaga = piso.guardarVaga();

        if (criacaoDaVaga) {

            System.out.println("Vaga criada! ");

            telaDeAdm();
        } else {
            System.out.println("[ERRO] Opção inválida");
            telaDeAdm();
        }

    }

    public void sairDaConta() {

        TelaPrincipalUtil.telaPrincipal();

    }

    public void destruirVaga(){

        System.out.printf("Digite o id do piso: ");
        Scanner scanner = new Scanner(System.in);

        String pisoId = scanner.next();

        PisoDeEstacionamento piso = null;

        for(PisoDeEstacionamento pisoElem : estacionamento.getListaDePisos()){
            if(pisoElem.getPisoId().equals(pisoId)){
                piso = pisoElem;
            }
        }

        if(piso != null){
            System.out.printf("Digite o tipo de vaga\n[1] pequena\n[2] grande\n[3] motocicleta \n[4] eletrico \nOpção: ");

            String tipoVagaInt = scanner.next();

            TipoDeVaga tipoVaga = null;

            switch (tipoVagaInt) {
                case "1":
                    tipoVaga = TipoDeVaga.PEQUENA;
                    break;
                case "2":
                    tipoVaga = TipoDeVaga.GRANDE;
                    break;
                case "3":
                    tipoVaga = TipoDeVaga.MOTOCICLETA;
                    break;
                case "4":
                    tipoVaga = TipoDeVaga.ELETRICO;
                    break;
                default:
                    System.out.println("[ERRO] Tipo de vaga não existe...");
                    telaDeAdm();
                    
            }


            for(Vaga vagaElem : piso.getListaDeVagas()){
                if(vagaElem.getTipoDaVaga() == tipoVaga && vagaElem.getOcupada() == false){
                        piso.removerVaga(vagaElem);
                        System.out.println("Vaga removida");
                        telaDeAdm();

                    
                }
            }
            System.out.println("[ERRO] A vaga não existe ou está ocupada...");
            telaDeAdm();

            
        }else{
            System.out.println("[ERRO] Piso não encontrado...");
            telaDeAdm();
        }

        
        


        telaDeAdm();


    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

}
