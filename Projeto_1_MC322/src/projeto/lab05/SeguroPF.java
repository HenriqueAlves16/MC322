package projeto.lab05;

public class SeguroPF extends Seguro {
	private Veiculo veiculo;
	private ClientePF cliente;
	
	//Construtor:
	SeguroPF(int ID, String dataInicio, String dataFim, Seguradora seguradora, double valorMensal, Veiculo veiculo, ClientePF cliente){
		super(ID, dataInicio, dataFim, seguradora, valorMensal);
		this.veiculo = veiculo;
		this.cliente = cliente;
	}

	//gets e sets:
	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public ClientePF getCliente() {
		return cliente;
	}

	public void setCliente(ClientePF cliente) {
		this.cliente = cliente;
	}

	//toString():
	@Override
	public String toString() {
		return "SeguroPF [veiculo=" + veiculo + ", cliente=" + cliente + "]";
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
