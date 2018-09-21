package Utilities;

import Model.Aresta;
import Model.MalhaViaria;
import Model.Vertice;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Ivens
 */
public class ArchiveReader {

    public MalhaViaria readMalhaViaria(String archive) {
        MalhaViaria malha = new MalhaViaria();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(archive));
            int linhas = 0;
            int colunas = 0;
            String str = reader.readLine().trim();
            linhas = Integer.parseInt(str);
            str = reader.readLine().trim();
            colunas = Integer.parseInt(str);
            String[] arrayStr = null;

            while ((str = reader.readLine()) != null) {
                arrayStr = str.split("\t");

                if (arrayStr.length != 4) {
                    throw new IOException("Arquivo no formato incorreto!");
                }

                int x = Integer.parseInt(arrayStr[0]);
                int y = Integer.parseInt(arrayStr[1]);
                boolean isBorda = (x == 0) || (x == colunas - 1) || (y == 0) || (y == linhas - 1);
                Vertice origem = new Vertice(x, y, true, isBorda);

                x = Integer.parseInt(arrayStr[2]);
                y = Integer.parseInt(arrayStr[3]);
                isBorda = (x == 0) || (x == colunas - 1) || (y == 0) || (y == linhas - 1);
                Vertice destino = new Vertice(x, y, true, isBorda);

                List<Aresta> arestasSaidas = new ArrayList<>();
                if (!destino.isBorda()) {

                    for (Aresta a : malha.getArestas()) {
                        if (a.getInicio().getX() == destino.getX()
                                && a.getInicio().getY() == destino.getY()) {
                            arestasSaidas.add(a);
                        }
                    }
                }

                malha.addAresta(new Aresta(origem, destino, arestasSaidas));

            }

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        malha.defineBordasLivres();
        System.out.println(malha);
        return malha;
    }

}
