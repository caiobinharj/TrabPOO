import java.util.*;
import java.io.*;
import javax.swing.*;
public class Moderador extends Usuario {
    private Sistema sistema;

    public Moderador() {
        super();
        this.setModerador(true);
        this.sistema = new Sistema();
    }

    public ArrayList<String[]> consultarDenuncias() {
        ArrayList<String[]> denuncias = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("denuncias.txt"));
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                String[] denuncia = {dados[1], dados[dados.length-1]};
                denuncias.add(denuncia);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return denuncias;
    }

    public void removerUsuario(String nome) {
        try {
            // Encontra o login do usuário pelo nome
            final String loginParaRemover = sistema.listarUsuarios().stream()
                    .filter(u -> u.getNome().equals(nome))
                    .map(Usuario::getLogin)
                    .findFirst()
                    .orElse(null);

            if (loginParaRemover != null) {
                List<Usuario> usuarios = sistema.listarUsuarios();
                usuarios.removeIf(u -> u.getLogin().equals(loginParaRemover));

                // Atualiza o arquivo mantendo o formato completo
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.txt"))) {
                    for (Usuario u : usuarios) {
                        writer.write(String.format("%s;%s;%d;%c;%s;%s;%b;%b;%s;%s;%s;%b;%b;%c;%b;%s;%d;%b",
                                u.getLogin(),
                                u.getNome(),
                                u.getIdade(),
                                u.getSexo(),
                                u.getCidade(),
                                u.getPrefMusical(),
                                u.getBebe(),
                                u.getFuma(),
                                u.getOrientacaoSexual(),
                                u.getFoto(),
                                u.getHobbies(),
                                u.getTrabalha(),
                                u.getFaculdade(),
                                u.getPeriodo(),
                                u.getExercita(),
                                u.getDescricao(),
                                u.getDenuncias(),
                                u.isModerador()
                        ));
                        writer.newLine();
                    }
                }

                // Remove denúncias relacionadas a esse usuário
                List<Denuncia> denuncias = sistema.carregarDenuncias();
                denuncias.removeIf(d -> d.getUsuarioDenunciado().getLogin().equals(loginParaRemover));

                try (BufferedWriter writer = new BufferedWriter(new FileWriter("denuncias.txt"))) {
                    for (Denuncia d : denuncias) {
                        writer.write(d.getDenunciante().getLogin() + ";" +
                                d.getUsuarioDenunciado().getLogin() + ";" +
                                d.getMotivo());
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Erro ao remover usuário",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}