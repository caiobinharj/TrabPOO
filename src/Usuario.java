import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Usuario {
    private String login;
    private String senha;
    private String nome;
    private int idade;
    private char sexo;
    private String cidade;
    private String prefMusical;
    private boolean bebe;
    private boolean fuma;
    private String orientacaoSexual;
    private String foto;
    private String hobbies;
    private boolean trabalha;
    private boolean faculdade;
    private char periodo;
    private boolean exercita;
    private String descricao;
    private int denuncias;
    private boolean moderador;

    public Usuario() {
        this.denuncias = 0;
    }

    public void setModerador(boolean moderador) {
        this.moderador = moderador;
    }

    public boolean isModerador() {
        return this.moderador;
    }

    public void setBebe(boolean bebe) {
        this.bebe = bebe;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPrefMusical() {
        return prefMusical;
    }

    public void setPrefMusical(String prefMusical) {
        this.prefMusical = prefMusical;
    }

    public void setFuma(boolean fuma) {
        this.fuma = fuma;
    }

    public String getOrientacaoSexual() {
        return orientacaoSexual;
    }

    public void setOrientacaoSexual(String orientacaoSexual) {
        this.orientacaoSexual = orientacaoSexual;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public void setTrabalha(boolean trabalha) {
        this.trabalha = trabalha;
    }

    public void setFaculdade(boolean faculdade) {
        this.faculdade = faculdade;
    }

    public char getPeriodo() {
        return periodo;
    }

    public void setPeriodo(char periodo) {
        this.periodo = periodo;
    }

    public void setExercita(boolean exercita) {
        this.exercita = exercita;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDenuncias() {
        return denuncias;
    }

    public void setDenuncias(int denuncias) {
        this.denuncias = denuncias;
    }

    public void incrementarDenuncias() {
        this.denuncias++;
    }

    public boolean getBebe() {
        return this.bebe;
    }

    public boolean getFuma() {
        return this.fuma;
    }

    public boolean getTrabalha() {
        return this.trabalha;
    }

    public boolean getFaculdade() {
        return this.faculdade;
    }

    public boolean getExercita() {
        return this.exercita;
    }

    public void salvarUsuario() {
        try {
            FileWriter fw = new FileWriter("login.txt", true);
            fw.write(login + ";" + senha + "\n");
            fw.close();

            fw = new FileWriter("usuarios.txt", true);
            fw.write(String.format("%s;%s;%d;%c;%s;%s;%b;%b;%s;%s;%s;%b;%b;%c;%b;%s;%d\n",
                    login, nome, idade, sexo, cidade, prefMusical, bebe, fuma,
                    orientacaoSexual, foto, hobbies, trabalha, faculdade,
                    periodo, exercita, descricao, denuncias));
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
