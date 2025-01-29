import java.util.*;
import java.io.*;

public class Moderador extends Usuario {
    public Moderador() {
        super();
        this.setModerador(true);
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

    public void removerUsuario(String login) {
    }
}
