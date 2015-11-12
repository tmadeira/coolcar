package coolcar.managers;

import java.util.ArrayList;

import coolcar.modelos.Locacao;

public class LocacoesManager {
  public ArrayList<Locacao> buscaLocacoes() {
    ArrayList<Locacao> locacoes = new ArrayList<Locacao>();
    locacoes.add(new Locacao());
    locacoes.add(new Locacao());
    return locacoes;
  }
}
