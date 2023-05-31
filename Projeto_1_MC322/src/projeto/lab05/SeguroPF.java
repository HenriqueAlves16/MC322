package projeto.lab05;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class SeguroPF extends Seguro {
	private Veiculo veiculo;
	private ClientePF cliente;
	private double valorMensal;
	
	//Construtor:
	public SeguroPF(String dataInicio, String dataFim, Seguradora seguradora, Veiculo veiculo, ClientePF cliente){
		super(dataInicio, dataFim, seguradora);
		this.veiculo = veiculo;
		this.cliente = cliente;
		this.valorMensal = calcularValor();
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

	public double getValorMensal() {
		return valorMensal;
	}

	public void setValorMensal(double valorMensal) {
		this.valorMensal = valorMensal;
	}
	//toString():
	@Override
	public String toString() {
		String info = "ID: " + getID();
		info = info + ", Seguradora: " + getSeguradora().getNome();
		info = info + ", Cliente: " + getCliente().getNome();
		info = info + ", Placa do veículo: " + getVeiculo().getPlaca();
		return info;
	}
	
	//Métodos:	
	//Método que lê informações para criar um sinistro e o cadastra na lista de sinistros:
	public boolean gerarSinistro() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Digite, respectivamente, a data (dd/mm/aaaa), endereço e número do documento do condutor relativos ao sinistro:");
		String data = scanner.nextLine();
		String endereco = scanner.nextLine();
		String documento = Validacao.recebeDocumentoValido();
		Condutor condutor = encontraCondutor(documento);
		
		Sinistro sinistro = new Sinistro(data, endereco, condutor, this);
		boolean sucesso = cadastrarSinistro(sinistro);
		if(sucesso) {
			System.out.println("Sinistro cadastrado com sucesso para o condutor " + condutor + "!");
		}	else	{
			System.out.println("Sinistro já cadastrado.");
		}
		return sucesso;
	}
	
	//Método que retorna a quantidade de veículos segurados que o cliente tem cadastrados na seguradora:
	public int qtdVeiculosSegurados() {
		int qtd = 0;
		for(Seguro seguro : getSeguradora().getListaSeguros()) {
			if(seguro.getCliente().getDocumento().equals(this.getCliente().getDocumento())) {
				qtd++;
			}
		}
		return qtd;
	}
	
	//Método que calcula o valor do seguroPJ:
	public double calcularValor() {
		double valor;
		int idade = getCliente().getIdade();
		double fatorIdade;
		int quantidadeVeiculos = qtdVeiculosSegurados();
		int quantidadeSinistrosCliente = quantidadeSinistrosCliente(this.getCliente());
		int quantidadeSinistrosCondutor = quantidadeSinistrosCondutores(this.getListaCondutores());
		
		if(getCliente().getIdade() < 30) {
			fatorIdade = CalcSeguro.FATOR_18_30.getNum();
		}	else if(getCliente().getIdade() < 60) {
			fatorIdade = CalcSeguro.FATOR_30_60.getNum();
		}	else	{
			fatorIdade = CalcSeguro.FATOR_60_90.getNum();
		}
		
		valor = CalcSeguro.VALOR_BASE.getNum() * fatorIdade * (1 + 1/(quantidadeVeiculos + 2)) * 
				(2 + quantidadeSinistrosCliente/10) * (5 + quantidadeSinistrosCondutor/10);
		
		System.out.println("VALORBASE:" + CalcSeguro.VALOR_BASE.getNum());
		System.out.println("FATOR IDADE:" + fatorIdade);
		System.out.println("VEICULOS:" + (1 + 1/(quantidadeVeiculos + 2)));
		System.out.println("SIN CLIENTE:" + (2 + quantidadeSinistrosCliente/10));
		System.out.println("SIN CDT:" + (5 + quantidadeSinistrosCondutor/10) + "\n");
		
		

		return valor;
	}
	
	public void atualizaValorMensal() {
		this.valorMensal = calcularValor();
	}
}
