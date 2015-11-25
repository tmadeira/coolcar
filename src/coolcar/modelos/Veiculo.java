package coolcar.modelos;

public class Veiculo {
  private String tipoDoVeiculo;
  private String chassi, placa, status;
  private int km, ano, id_func, id_modelo, filial_alojada;
  private Carro carro;
  private Moto moto;

  public Veiculo() {
    km = -1;
    ano = -1;
    id_func = -1;
    id_modelo = -1;
    filial_alojada = -1;

    carro = new Carro();
    moto = new Moto();
  }

  public Veiculo(String tipoDoVeiculo, String chassi, String placa, int km, int ano, String status, int id_func,
      int id_modelo, int filial_alojada, Carro carro, Moto moto) {
    this.tipoDoVeiculo = tipoDoVeiculo;
    this.chassi = chassi;
    this.placa = placa;
    this.km = km;
    this.ano = ano;
    this.status = status;
    this.id_func = id_func;
    this.id_modelo = id_modelo;
    this.filial_alojada = filial_alojada;
    this.carro = carro;
    this.moto = moto;
  }

  public void setTipoDoVeiculo(String tipoDoVeiculo) {
    this.tipoDoVeiculo = tipoDoVeiculo;
  }

  public void setChassi(String chassi) {
    this.chassi = chassi;
  }

  public void setPlaca(String placa) {
    this.placa = placa;
  }

  public void setKm(int km) {
    this.km = km;
  }

  public void setAno(int ano) {
    this.ano = ano;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setIdFunc(int id_func) {
    this.id_func = id_func;
  }

  public void setIdModelo(int id_modelo) {
    this.id_modelo = id_modelo;
  }

  public void setFilialAlojada(int filial_alojada) {
    this.filial_alojada = filial_alojada;
  }

  public void setCarro(Carro carro) {
    this.carro = carro;
  }

  public void setMoto(Moto moto) {
    this.moto = moto;
  }

  public String getTipoDoVeiculo() {
    return this.tipoDoVeiculo;
  }

  public String getChassi() {
    return this.chassi;
  }

  public String getPlaca() {
    return this.placa;
  }

  public int getKm() {
    return this.km;
  }

  public int getAno() {
    return this.ano;
  }

  public String getStatus() {
    return this.status;
  }

  public int getIdFunc() {
    return this.id_func;
  }

  public int getIdModelo() {
    return this.id_modelo;
  }

  public int getFilialAlojada() {
    return this.filial_alojada;
  }

  public Carro getCarro() {
    return this.carro;
  }

  public Moto getMoto() {
    return this.moto;
  }
}