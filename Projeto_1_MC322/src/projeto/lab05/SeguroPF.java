package projeto.lab05;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class SeguroPF extends Seguro {
	private Veiculo veiculo;
	private ClientePF cliente;
	
	//Construtor:
	public SeguroPF(String dataInicio, String dataFim, Seguradora seguradora, Veiculo veiculo, ClientePF cliente){
		super(dataInicio, dataFim, seguradora);
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
		String info = "";
		info = info + "Seguradora: " + getSeguradora();
		info = info + ", Cliente: " + getCliente().getNome();
		info = info + ", Placa do veículo: " + getVeiculo().getPlaca();
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
		int idade = getCliente().getIdade();
		double fatorIdade;
		int quantidadeVeiculos = getCliente().getListaVeiculos().size();
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
		
		return valor;
	}
	
}
