package projeto.lab05;
import java.util.ArrayList;

public class SeguroPF extends Seguro {
	private Veiculo veiculo;
	private ClientePF cliente;
	
	//Construtor:
	SeguroPF(String dataInicio, String dataFim, Seguradora seguradora, double valorMensal, Veiculo veiculo, ClientePF cliente){
		super(dataInicio, dataFim, seguradora, valorMensal);
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
	
	//Método que calcula a quantidade de sinistros do ClientePF na seguradora:
	public int quantidadeSinistrosCliente(ClientePF cliente) {
		int qtdSinistros = 0;
		for(Seguro seguro : getSeguradora().getListaSeguros()) {
			for(Sinistro sinistro : seguro.getListaSinistros()) {
				String cpfClienteIteracao = sinistro.getCondutor().getCPF();
				if(cpfClienteIteracao.equals(cliente.getCPF())) {
					qtdSinistros++;
				}
			}
		}
		return qtdSinistros;
	}
	
	//Método que calcula a quantidade de sinistros de todas as pessoas na lista de condutores:
	public int quantidadeSinistrosCondutores(ArrayList<Condutor> listaCondutores) {
		int qtdSinistros = 0;
		for(Condutor condutor : listaCondutores) {
			qtdSinistros += condutor.getListaSinistros().size();
		}
		return qtdSinistros;
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
		
		valor = CalcSeguro.VALOR_BASE.getNum() * fatorIdade * (1 + 1/(quantidadeVeiculos + 2)) * (2 + quantidadeSinistrosCliente/10) * (5 + quantidadeSinistrosCondutor/10);
		return valor;
	}
		
	//Método que gera sinistros:
	public boolean gerarSinistro() {
		return true;
	}
	
}
