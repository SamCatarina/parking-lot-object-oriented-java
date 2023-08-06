package tipos;

public class DadosDaConta {
    private String login;
    private String senha;

    public DadosDaConta(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

}
