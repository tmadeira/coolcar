package coolcar.modelos;

import java.util.Date;

public class Reserva {
	private int id_cliente, id_reserva, id_modelo, id_filial_retirada, id_acessorios, 
	id_filial_devolucao, cadeiras_de_bebe;
	private float valor;
	private Date dt_fim_reserva, dt_inicio_reserva;
	private boolean gps, seguro;
	
  public Reserva() {
  }

public int getId_filial_retirada() {
	return id_filial_retirada;
}

public void setId_filial_retirada(int id_filial_retirada) {
	this.id_filial_retirada = id_filial_retirada;
}

public int getId_cliente() {
	return id_cliente;
}

public void setId_cliente(int id_cliente) {
	this.id_cliente = id_cliente;
}

public int getId_reserva() {
	return id_reserva;
}

public void setId_reserva(int id_reserva) {
	this.id_reserva = id_reserva;
}

public int getId_modelo() {
	return id_modelo;
}

public void setId_modelo(int id_modelo) {
	this.id_modelo = id_modelo;
}

public int getId_filial_devolucao() {
	return id_filial_devolucao;
}

public void setId_filial_devolucao(int id_filial_devolucao) {
	this.id_filial_devolucao = id_filial_devolucao;
}

public int getCadeiras_de_bebe() {
	return cadeiras_de_bebe;
}

public void setCadeiras_de_bebe(int cadeiras_de_bebe) {
	this.cadeiras_de_bebe = cadeiras_de_bebe;
}

public float getValor() {
	return valor;
}

public void setValor(float valor) {
	this.valor = valor;
}

public boolean temGps() {
	return gps;
}

public void setGps(boolean gps) {
	this.gps = gps;
}

public boolean temSeguro() {
	return seguro;
}

public void setSeguro(boolean seguro) {
	this.seguro = seguro;
}

public Date getDt_fim_reserva() {
	return dt_fim_reserva;
}

public void setDt_fim_reserva(Date dt_fim_reserva) {
	this.dt_fim_reserva = dt_fim_reserva;
}

public Date getDt_inicio_reserva() {
	return dt_inicio_reserva;
}

public void setDt_inicio_reserva(Date dt_inicio_reserva) {
	this.dt_inicio_reserva = dt_inicio_reserva;
}

public void setId_acessorios(int id_acessorios) {
	this.id_acessorios = id_acessorios;
}

public int getId_acessorios() {
	return id_acessorios;
}
}
