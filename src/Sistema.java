import java.util.*;
import java.io.*;
import javax.swing.*;

public class Sistema {
    private ArrayList<Usuario> usuarios;
    private Usuario usuarioLogado;

    public Sistema() {
        usuarios = new ArrayList<>();
        carregarUsuarios();
    }

    private void carregarUsuarios() {
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] dados = linha.split(";");
                if (dados.length >= 17) {
                    Usuario u = new Usuario();
                    u.setLogin(dados[0]);
                    u.setNome(dados[1]);
                    u.setIdade(Integer.parseInt(dados[2]));
                    u.setSexo(dados[3].charAt(0));
                    u.setCidade(dados[4]);
                    u.setPrefMusical(dados[5]);
                    u.setBebe(Boolean.parseBoolean(dados[6]));
                    u.setFuma(Boolean.parseBoolean(dados[7]));
                    u.setOrientacaoSexual(dados[8]);
                    u.setFoto(dados[9]);
                    u.setHobbies(dados[10]);
                    u.setTrabalha(Boolean.parseBoolean(dados[11]));
                    u.setFaculdade(Boolean.parseBoolean(dados[12]));
                    u.setPeriodo(dados[13].charAt(0));
                    u.setExercita(Boolean.parseBoolean(dados[14]));
                    u.setDescricao(dados[15]);
                    u.setDenuncias(Integer.parseInt(dados[16]));

                    usuarios.add(u);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean login(String login, String senha) {
        try (BufferedReader br = new BufferedReader(new FileReader("login.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] dados = linha.split(";");
                if (dados.length >= 2 && dados[0].equals(login) && dados[1].equals(senha)) {
                    for (Usuario u : usuarios) {
                        if (u != null && u.getLogin() != null && u.getLogin().equals(login)) {
                            usuarioLogado = u;
                            return true;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void logout() {
        usuarioLogado = null;
    }

    public Usuario getRandomUser() {
        if (usuarios.size() > 1) {
            Usuario random;
            do {
                random = usuarios.get((int)(Math.random() * usuarios.size()));
            } while (random == usuarioLogado);
            return random;
        }
        return null;
    }

    public void salvarConversa(String remetente, String destinatario, String mensagem) {
        try {
            FileWriter fw = new FileWriter("conversas.txt", true);
            fw.write(String.format("%s;%s;%s\n", remetente, destinatario, mensagem));
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void darLike(Usuario usuario) {
        try {
            FileWriter fw = new FileWriter("likes.txt", true);
            fw.write(usuarioLogado.getLogin() + ";" + usuario.getLogin() + "\n");
            fw.close();

            // Verificar se há match
            if (verificarMatch(usuario)) {
                JOptionPane.showMessageDialog(null, "Você tem um novo match!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean verificarMatch(Usuario usuario) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("likes.txt"));
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados[0].equals(usuario.getLogin()) &&
                        dados[1].equals(usuarioLogado.getLogin())) {
                    br.close();
                    return true;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void denunciar(Usuario usuario) {
        usuario.incrementarDenuncias();
        atualizarUsuario(usuario);
    }

    public void atualizarUsuario(Usuario usuario) {
        // Implementar atualização no arquivo
        ArrayList<String> linhas = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"));
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados[0].equals(usuario.getLogin())) {
                    // Substituir linha com dados atualizados
                    linha = String.format("%s;%s;%d;%c;%s;%s;%b;%b;%s;%s;%s;%b;%b;%c;%b;%s;%d",
                            usuario.getLogin(), usuario.getNome(), usuario.getIdade(),
                            usuario.getSexo(), usuario.getCidade(), usuario.getPrefMusical(),
                            usuario.getBebe(), usuario.getFuma(), usuario.getOrientacaoSexual(),
                            usuario.getFoto(), usuario.getHobbies(), usuario.getTrabalha(),
                            usuario.getFaculdade(), usuario.getPeriodo(), usuario.getExercita(),
                            usuario.getDescricao(), usuario.getDenuncias());
                }
                linhas.add(linha);
            }
            br.close();

            // Reescrever arquivo
            FileWriter fw = new FileWriter("usuarios.txt");
            for (String l : linhas) {
                fw.write(l + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String[]> getConversas(Usuario outroUsuario) {
        ArrayList<String[]> mensagens = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("conversas.txt"));
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if ((dados[0].equals(usuarioLogado.getLogin()) &&
                        dados[1].equals(outroUsuario.getLogin())) ||
                        (dados[0].equals(outroUsuario.getLogin()) &&
                                dados[1].equals(usuarioLogado.getLogin()))) {
                    mensagens.add(new String[]{dados[0], dados[2]});
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensagens;
    }

    public ArrayList<Usuario> getMatches() {
        ArrayList<Usuario> matches = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("likes.txt"));
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados[0].equals(usuarioLogado.getLogin())) {
                    if (verificarMatch(getUsuarioPorLogin(dados[1]))) {
                        matches.add(getUsuarioPorLogin(dados[1]));
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
    }

    private Usuario getUsuarioPorLogin(String login) {
        for (Usuario u : usuarios) {
            if (u.getLogin().equals(login)) {
                return u;
            }
        }
        return null;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
}

