package projeto.lab05;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Seguro {
	private final int ID;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private Seguradora seguradora;
	private double valorMensal;
	private ArrayList<Sinistro> listaSinistros;
	private ArrayList<Condutor> listaCondutores;
	
	//Construtor:
	Seguro(int ID, String dataInicio, String dataFim, Seguradora seguradora, double valorMensal){
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		this.ID = ID;
		this.dataInicio = LocalDate.parse(dataFim, formatador);
		this.dataFim = LocalDate.parse(dataFim, formatador);
		this.seguradora = seguradora;
		this.valorMensal = valorMensal;
		this.listaSinistros = new ArrayList<Sinistro>();
		this.listaCondutores = new ArrayList<Condutor>();		
	}

	//gets e sets:
	
	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public Seguradora getSeguradora() {
		return seguradora;
	}

	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}

	public double getValorMensal() {
		return valorMensal;
	}

	public void setValorMensal(double valorMensal) {
		this.valorMensal = valorMensal;
	}

	public ArrayList<Sinistro> getListaSinistros() {
		return listaSinistros;
	}

	public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
		this.listaSinistros = listaSinistros;
	}

	public ArrayList<Condutor> getListaCondutores() {
		return listaCondutores;
	}

	public void setListaCondutores(ArrayList<Condutor> listaCondutores) {
		this.listaCondutores = listaCondutores;
	}

	public int getID() {
		return ID;
	}

	//toString():
	@Override
	public String toString() {
		return "Seguro [ID=" + ID + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", seguradora=" + seguradora
				+ ", valorMensal=" + valorMensal + ", listaSinistros=" + listaSinistros + ", listaCondutores="
				+ listaCondutores + "]";
	}
	
	//Métodos:
	
	//Método que retira um condutor da lista de condutores (desautoriza sua condução):
	public boolean desautorizarCondutor() {
		return true;
	}
	//Método que adiciona um condutor da lista de condutores (autoriza sua condução):
	public boolean autorizarCondutor() {
		return true;
	}
	//Método que calcula o valor do seguro:
	public abstract double calcularValor();
	
	//Método que gera sinistros:
	public abstract boolean gerarSinistro();
	
}
