import java.util.*;
import java.io.*;
import javax.swing.*;

public class Sistema {
    private ArrayList<Usuario> usuarios;
    private Usuario usuarioLogado;
    private int indiceAtual = 0;
    private static final String ARQUIVO_LIKES = "likes.txt";
    private static final String ARQUIVO_DISLIKES = "dislikes.txt";
    private List<Denuncia> denuncias;
    private List<Usuario> usuariosBloqueados;
    private static final String ARQUIVO_DENUNCIAS = "denuncias.txt";

    public Sistema() {
        this.usuarios = new ArrayList<>();
        this.denuncias = new ArrayList<>();
        this.usuariosBloqueados = new ArrayList<>();
        carregarUsuarios();
    }

    public Usuario buscarUsuarioPorLogin(String login) {
        List<Usuario> usuarios = listarUsuarios();

        for (Usuario u : usuarios) {
            if (u.getLogin().equals(login)) {
                return u;
            }
        }
        return null;
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String arquivo = "usuarios.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                String[] dados = linha.split(";");

                String login = dados[0];
                String nome = dados[1];
                int idade = Integer.parseInt(dados[2]);
                char sexo = dados[3].isEmpty() ? ' ' : dados[3].charAt(0);
                String cidade = dados[4];
                String prefMusical = dados[5];
                boolean bebe = Boolean.parseBoolean(dados[6]);
                boolean fuma = Boolean.parseBoolean(dados[7]);
                String orientacaoSexual = dados[8].equals("null") ? null : dados[8];
                String foto = dados[9].equals("null") ? null : dados[9];
                String hobbies = dados[10];
                boolean trabalha = Boolean.parseBoolean(dados[11]);
                boolean faculdade = Boolean.parseBoolean(dados[12]);
                char periodo = dados[13].isEmpty() ? ' ' : dados[13].charAt(0);
                boolean exercita = Boolean.parseBoolean(dados[14]);
                String descricao = dados[15];
                int denuncias = Integer.parseInt(dados[16]);
                boolean moderador = dados[17].trim().equalsIgnoreCase("true");

                Usuario usuario = new Usuario();
                usuario.setLogin(login);
                usuario.setNome(nome);
                usuario.setIdade(idade);
                usuario.setSexo(sexo);
                usuario.setCidade(cidade);
                usuario.setPrefMusical(prefMusical);
                usuario.setBebe(bebe);
                usuario.setFuma(fuma);
                usuario.setOrientacaoSexual(orientacaoSexual);
                usuario.setFoto(foto);
                usuario.setHobbies(hobbies);
                usuario.setTrabalha(trabalha);
                usuario.setFaculdade(faculdade);
                usuario.setPeriodo(periodo);
                usuario.setExercita(exercita);
                usuario.setDescricao(descricao);
                usuario.setDenuncias(denuncias);
                usuario.setModerador(moderador);

                usuarios.add(usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    private void carregarUsuarios() {
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] dados = linha.split(";");
                if (dados.length >= 18) {
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
                    u.setModerador(dados[17].trim().equalsIgnoreCase("true"));

                    usuarios.add(u);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Usuario login(String login, String senha) {
        try (BufferedReader br = new BufferedReader(new FileReader("login.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] dados = linha.split(";");
                if (dados.length >= 2 && dados[0].equals(login) && dados[1].equals(senha)) {
                    for (Usuario u : usuarios) {
                        if (u != null && u.getLogin() != null && u.getLogin().equals(login)) {
                            usuarioLogado = u;
                            return u; // Retorna o objeto Usuario correspondente
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void logout() {
        usuarioLogado = null;
    }

    public Usuario getRandomUser() {
        if (usuarios.isEmpty() || usuarios.size() == 1 && usuarios.contains(usuarioLogado)) {
            return null;
        }
        while (indiceAtual < usuarios.size()) {
            Usuario proximoUsuario = usuarios.get(indiceAtual);
            indiceAtual++;
            if (!proximoUsuario.equals(usuarioLogado) && !jaInteragiu(usuarioLogado, proximoUsuario)) {
                return proximoUsuario;
            }
        }
        indiceAtual = 0;
        return null;
    }

    private boolean jaInteragiu(Usuario usuario1, Usuario usuario2) {
        Set<String> interacoes = carregarInteracoes();

        String interacao = usuario1.getNome() + ";" + usuario2.getNome();

        return interacoes.contains(interacao);
    }

    private Set<String> carregarInteracoes() {
        Set<String> interacoes = new HashSet<>();
        String[] arquivos = {ARQUIVO_LIKES, ARQUIVO_DISLIKES, ARQUIVO_DENUNCIAS};

        for (String arquivo : arquivos) {
            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    linha = linha.trim();

                    if (arquivo.equals(ARQUIVO_DENUNCIAS)) {
                        String[] partes = linha.split(";", 3);
                        if (partes.length >= 2) {
                            interacoes.add(partes[0] + ";" + partes[1]);
                        }
                    } else {
                        interacoes.add(linha);
                    }
                }
            } catch (IOException e) {
                System.err.println("Erro ao ler o arquivo: " + arquivo + " - " + e.getMessage());
            }
        }

        return interacoes;
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

            if (verificarMatch(usuario)) {
                JOptionPane.showMessageDialog(null, "Você tem um novo match!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void darDislike(Usuario usuario) {
        try {
            FileWriter fw = new FileWriter("dislikes.txt", true);
            fw.write(usuarioLogado.getLogin() + ";" + usuario.getLogin() + "\n");
            fw.close();

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

    public List<Denuncia> getDenuncias() {
        return denuncias;
    }

    public void registrarDenuncia(String denunciante, String denunciado, String motivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_DENUNCIAS, true))) {
            writer.write(denunciante + ";" + denunciado + ";" + motivo);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Denuncia> carregarDenuncias() {
        List<Denuncia> listaDenuncias = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_DENUNCIAS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 3) {
                    Usuario denunciante = buscarUsuarioPorLogin(dados[0]);
                    Usuario denunciado = buscarUsuarioPorLogin(dados[1]);
                    if (denunciante != null && denunciado != null) {
                        listaDenuncias.add(new Denuncia(denunciante, denunciado, dados[2]));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaDenuncias;
    }

    public void removerDenuncia(Denuncia denunciaRemover) {
        List<Denuncia> denuncias = carregarDenuncias();
        denuncias.removeIf(d -> d.getDenunciante().equals(denunciaRemover.getDenunciante()) &&
                d.getUsuarioDenunciado().equals(denunciaRemover.getUsuarioDenunciado()) &&
                d.getMotivo().equals(denunciaRemover.getMotivo()));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_DENUNCIAS))) {
            for (Denuncia d : denuncias) {
                writer.write(d.getDenunciante() + ";" + d.getUsuarioDenunciado() + ";" + d.getMotivo());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void atualizarUsuario(Usuario usuario) {
        ArrayList<String> linhas = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"));
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados[0].equals(usuario.getLogin())) {
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

