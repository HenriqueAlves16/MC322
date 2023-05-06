package projeto.lab04;

import java.util.Scanner;

public class Validacao {
	//Método que valida o cpf:
	
	public static boolean validarCPF(String cpf) {
		int num, peso = 10, sm = 0, r, j = 1;
		char dig10, dig11;
			
		//Remove os caracteres nao numericos
		cpf = cpf.replaceAll("[^0-9]","");
					
		//Verifica se o cpf tem 11 caracteres
		if(cpf.length() != 11)	return false;
		
		// Verifica se todos os digitos sao iguais
		while(true) {
			if(cpf.charAt(j) != cpf.charAt(j - 1)) {
				break;
			}
			if(j == 10)	return false; 
			j++;
		}

		// Calculo do 1o. Digito Verificador
			
	    // converte o i-esimo caractere do cpf em um numero:
	    // por exemplo, transforma o caractere '0' no inteiro 0
	    // (48 eh a posicao de '0' na tabela ASCII)
		for (int i = 0; i < 9; i++) {
			num = (int)(cpf.charAt(i) - 48);
			sm += (num * peso);
			peso -= 1;
        }

        r = 11 - (sm % 11);
        if ((r == 10) || (r == 11))
           dig10 = '0';
        else dig10 = (char)(r + 48); // converte no respectivo caractere numerico
				
	    // Calculo do 2o. Digito Verificador
		sm = 0;
		peso = 11;
				
		for(int i = 0; i < 10; i++) {
			num = (int)cpf.charAt(i) - 48;
			sm += num * peso;
			peso -= 1;
		}
				
		r = 11 - (sm % 11);
			
		if(r == 10 || r == 11) {
			dig11 = '0';
		}	else	{
			dig11 = (char)(r + 48);
		}
				
		// Verifica se os digitos calculados conferem com os digitos informados.		
		if(dig10 == cpf.charAt(9) && dig11 == cpf.charAt(10))
			return true;
		else return false;
		}
			
	//Validando o cnpj
	public static boolean validarCNPJ(String cnpj) {
		int j = 1, num, peso = 2, sm = 0, r;
		char dig13, dig14;
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
	
	// Método que verifica se o nome contém apenas letras e espaços em branco:
	public static boolean validaNome(String nome) {
		for (int i = 0; i < nome.length(); i++) {
			if ((nome.charAt(i) == 32) || (nome.charAt(i) > 64 && nome.charAt(i) < 91)
					|| (nome.charAt(i) > 96 && nome.charAt(i) < 123))
				continue;
			else
				return false;
		}
		return true;
	}

	// Método que valida um documento genérico passado por input:
	public static String recebeDocumentoValido() {
		boolean docValido = false;
		String documento;
		do {
			Scanner scanner = new Scanner(System.in);
			documento = scanner.nextLine();
			documento = documento.replaceAll("[^0-9]","");
				
			if(documento.length() != 11 && documento.length() != 14) {
			}	else if(documento.length() == 11) {
				if(Validacao.validarCPF(documento)) {
					docValido = true;
					documento = documento.substring(0, 3) + "." + documento.substring(3, 6) + "." + documento.substring(6,9) + "-" + documento.substring(9);
				}

			}	else {
				if(Validacao.validarCNPJ(documento)) {
					docValido = true;
					documento = documento.substring(0, 2) + "." + documento.substring(2, 5) + "." + documento.substring(5,8) + "/" + documento.substring(8,12) + "-" + documento.substring(12);
				}
			}
			
			if(!docValido) {
				System.out.println("Documento inválido. Digite novamente.");
			}
		} while(!docValido);
		
		return documento;
	}
	
	
}
