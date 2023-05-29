package projeto.lab05;

import java.util.Scanner;

public class SeguroPJ extends Seguro {
	private Frota frota;
	private ClientePJ cliente;
	private double valorMensal;
	
	//Construtor:
	public SeguroPJ(String dataInicio, String dataFim, Seguradora seguradora, Frota frota, ClientePJ cliente){
		super(dataInicio, dataFim, seguradora);
		this.frota = frota;
		this.cliente = cliente;
		this.valorMensal = calcularValor();
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
		String info = "";
		info = info + "Seguradora: " + getSeguradora().getNome();
		info = info + ", Cliente: " + getCliente().getNome();
		info = info + ", Código da frota: " + getFrota().getCode();
		return info;
	}

	//Métodos:
	
	//Método que lê informações para criar um sinistro e o cadastra na lista de sinistros:
	public boolean gerarSinistro() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Digite, respectivamente, a data (dd/mm/aaaa), endereço, número do documento do condutor relativo ao sinistro:");
		String data = scanner.nextLine();
		String endereco = scanner.nextLine();
		String documento = Validacao.recebeDocumentoValido();
		Condutor condutor = encontraCondutor(documento);
		
		Sinistro sinistro = new Sinistro(data, endereco, condutor, this);
		boolean sucesso = cadastrarSinistro(sinistro);
		atualizaValorMensal();
		return sucesso;
	}

	//Método que calcula o valor do seguroPJ:
	public double calcularValor() {
		double valor;
		int quantidadeFunc = this.getCliente().getQtdeFuncionarios();
		int quantidadeVeiculos = getCliente().totalVeiculos();
		int anosPosFundacao = getCliente().getIdade();
		int quantidadeSinistrosCliente = quantidadeSinistrosCliente(this.getCliente());
		int quantidadeSinistrosCondutor = quantidadeSinistrosCondutores(this.getListaCondutores());
		
		valor = CalcSeguro.VALOR_BASE.getNum() * (10 + (quantidadeFunc) / 10) * (1 + 1/(quantidadeVeiculos + 2)) * 
				(1 + 1/(anosPosFundacao + 2)) * (2 + quantidadeSinistrosCliente / 10) * (5 + quantidadeSinistrosCondutor / 10);
		
		return valor;	
	}
	
	public void atualizaValorMensal() {
		this.valorMensal = calcularValor();
	}
		

}
