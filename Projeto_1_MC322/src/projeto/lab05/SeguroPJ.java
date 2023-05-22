package projeto.lab05;

public class SeguroPJ extends Seguro {
	private Frota frota;
	private ClientePJ cliente;
	
	//Construtor:
	SeguroPJ(String dataInicio, String dataFim, Seguradora seguradora, double valorMensal, Frota frota, ClientePJ cliente){
		super(dataInicio, dataFim, seguradora, valorMensal);
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

	//Métodos:
	
	//Método que calcula a quantidade de sinistros de um ClientePJ na seguradora:
	public int quantidadeSinistrosCliente(ClientePJ cliente) {
		int qtdSinistros = 0;
		for(Seguro seguro : getSeguradora().getListaSeguros()) {
			if(seguro instanceof SeguroPJ) {
				String cnpjClienteIteracao = ((SeguroPJ) seguro).getCliente().getCNPJ();
				if(cnpjClienteIteracao.equals(cliente.getCNPJ())){
					qtdSinistros++;
				}
			}
		}
		return qtdSinistros;
	}
	
	//Método que calcula o valor do seguroPJ:
	public double calcularValor() {
		double valor;
		int quantidadeFunc = getCliente().getQtdeFuncionarios();
		int quantidadeVeiculos = getCliente().totalVeiculos();
		int anosPosFundacao = getCliente().getIdade();
		int quantidadeSinistrosCliente = quantidadeSinistrosCliente(this.getCliente());
		int quantidadeSinistrosCondutor = quantidadeSinistrosCondutores(this.getListaCondutores());
		
		valor = CalcSeguro.VALOR_BASE.getNum() * (10 + (quantidadeFunc) / 10) * (1 + 1/(quantidadeVeiculos + 2)) * 
				(1 + 1/(anosPosFundacao + 2)) * (2 + quantidadeSinistrosCliente / 10) * (5 + quantidadeSinistrosCondutor / 10);
		
		return valor;	
	}
		
	//Método que gera sinistros:
	public boolean gerarSinistro() {
		return true;
	}
	
}
