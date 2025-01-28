import java.util.*;
import java.io.*;

public class Moderador {
    public ArrayList<String[]> consultarDenuncias() {
        ArrayList<String[]> denuncias = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"));
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                String[] denuncia = {dados[1], dados[dados.length-1]}; // nome e número de denúncias
                denuncias.add(denuncia);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return denuncias;
    }

    public void removerUsuario(String login) {
        // Implementar remoção do usuário dos arquivos
    }
}
