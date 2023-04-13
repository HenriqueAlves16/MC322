package projeto.lab03;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;


public class ClientePJ extends Cliente {
	final String CNPJ;
	private LocalDate dataFundacao;
	
	//Metodo construtor:
	public ClientePJ(String nome, String endereco, String dataLicenca, String tipo, String cnpj, String dataFundacao) {
		// chama o construtor da superclasse
		super(nome, endereco, dataLicenca, tipo);
		
		DateTimeFormatter formatterSTR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.CNPJ = cnpj;
		this.dataFundacao = LocalDate.parse(dataFundacao, formatterSTR);
	}

	//Getters e setters:
	public String getcnpj() {
		return CNPJ;
	}

	public LocalDate getDataNascimento() {
		return dataFundacao;
	}


	public void setDataNascimento(String dataFundacao) {
		this.dataFundacao = LocalDate.parse(dataFundacao);
	}	
	
	//toString:
	@Override
	public String toString() {
		StringJoiner str = new StringJoiner("\n");
		DateTimeFormatter formatterSTR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataLicencaFormatada = (getDataLicenca()).format(formatterSTR);
		String dataFundacaoFormatada = (dataFundacao).format(formatterSTR);
		str.add("Classe = ClientePJ");
		str.add("nome = " + getNome());
		str.add("endereco = " + getEndereco());
		str.add("dataLicenca = " + dataLicencaFormatada);
		str.add("tipo = " + getTipo());
		str.add("CNPJ = " + CNPJ);
		str.add("dataFundacao = " + dataFundacaoFormatada);
		str.add("listaVeiculos :");
		for(Veiculo veiculo : getListaVeiculos()) {
			str.add(" ".repeat(4) + veiculo.toString());
		}
		return str.toString();

	}	
	
	//Validando o cnpj
	public boolean validarCNPJ() {
		int j = 1, num, peso = 2, sm = 0, r;
		char dig13, dig14;
		String cnpj = CNPJ;
		//Remove os caracteres nao numericos
		cnpj = cnpj.replaceAll("[^0-9]","");
					
		//Verifica se o cpf tem 11 caracteres
		if(cnpj.length() != 14)	return false;
			
		// Verifica se todos os digitos sao iguais
		while(true) {
			if(cnpj.charAt(j) != cnpj.charAt(j - 1)) {
				break;
			}
			if(j == 13)	return false; 
			j++;
		}
		
		// Calculo do 1o. Digito Verificador
		
	    // converte o i-esimo caractere do CPF em um numero:
	    // por exemplo, transforma o caractere '0' no inteiro 0
	    // (48 eh a posicao de '0' na tabela ASCII)
		for (int i = 11; i >= 0; i--) {
			num = (int)(cnpj.charAt(i) - 48);
			sm += (num * peso);
			peso += 1;
			
			if(peso == 10)
				peso = 2;
	    }

	    r = (sm % 11);
	    if ((r == 0) || (r == 1))
	       dig13 = '0';
	    else dig13 = (char)((11 - r) + 48); // converte no respectivo caractere numerico
		
	    
	 // Calculo do 2o. Digito Verificador
	 	sm = 0;
	 	peso = 2;
			
 		for(int i = 12; i >= 0; i--) {
 			num = (int)(cnpj.charAt(i) - 48);
 			sm += num * peso;
 			peso += 1;
 			if(peso == 10)
 				peso = 2;
 		}
	 		
 		r = (sm % 11);
 		
 		if(r == 0 || r == 1) {
 			dig14 = '0';
 		}	else	{
 			dig14 = (char)((11 - r) + 48);
 		}
	 		
 		// Verifica se os digitos calculados conferem com os digitos informados.		
 		if(dig13 == cnpj.charAt(12) && dig14 == cnpj.charAt(13))
 			return true;
 		else return false;
 	}
}


		