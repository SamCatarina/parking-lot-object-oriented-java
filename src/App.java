import estacionamento.Estacionamento;
import tela.TelaPrincipalUtil;

public class App {

  public static void main(String[] args) throws Exception {

    Estacionamento estacionamento = Estacionamento.getInstance();
    estacionamento.boasVindas();
    System.out.println("\nCrie uma conta de Administrador para ter acesso a todo o sistema: \n");
    estacionamento.criarContaDeAdministrador();


    TelaPrincipalUtil.telaPrincipal();

  }

}
