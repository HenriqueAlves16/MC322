package projeto.lab05;

public class SeguroPJ extends Seguro {
	private Frota frota;
	private ClientePJ cliente;
	
	//Construtor:
	SeguroPJ(int ID, String dataInicio, String dataFim, Seguradora seguradora, double valorMensal, Frota frota, ClientePJ cliente){
		super(ID, dataInicio, dataFim, seguradora, valorMensal);
		this.frota = frota;
		this.cliente = cliente;
	}

	//gets e sets:
	public Frota getFrota() {
		return frota;
	}

	public void setFrota(Frota frota) {
		this.frota = frota;
	}

	public ClientePJ getCliente() {
		return cliente;
	}

	public void setCliente(ClientePJ cliente) {
		this.cliente = cliente;
	}

	//toString():
	@Override
	public String toString() {
		return "SeguroPJ [frota=" + frota + ", cliente=" + cliente + "]";
	}
	
	//Método que calcula o valor do seguroPJ:
	public double calcularValor() {
		return 0;
	}
		
	//Método que gera sinistros:
	public boolean gerarSinistro() {
		return true;
	}
	
}
