package projeto.lab04;

public enum MenuOperacoes {
	SAIR (0),
	CADASTRAR(1),
	LISTAR (2),
	EXCLUIR (3),
	GERAR_SINISTRO (4),
	TRANSFERIR_SEGURO (5),
	CALCULAR_RECEITA_SEGURADORA (6),
	ALTERAR_SEGURADORA_ATIVA (7),
	CADASTRAR_CLIENTE (11),
	CADASTRAR_VEICULO (12),
	CADASTRAR_SEGURADORA (13),
	VOLTAR_CADASTRO (14),
	LISTAR_CLIENTE_POR_SEG (21),
	LISTAR_SINISTROS_POR_SEG (22),
	LISTAR_SINISTROS_POR_CLIENTE (23),
	LISTAR_VEICULOS_POR_CLIENTE (24),
	LISTAR_VEICULOS_POR_SEG (25),
	VOLTAR_LISTAR (26),
	EXCLUIR_CLIENTE (31),
	EXCLUIR_VEICULO (32),
	EXCLUIR_SINISTRO (33),
	VOLTAR_EXCLUIR (34);
	
	public final int operacao;	
	
	MenuOperacoes(int operacao){
		this.operacao = operacao;
	}
	
	//Método que retorna uma constante do enum a partir de um inteiro target
	public static MenuOperacoes busca(int target) {
		for(MenuOperacoes i : MenuOperacoes.values()) {
			if(i.operacao == target)	return i;
		}
		return null;
	}
	
	public int getOperacao() {
		return this.operacao;
	}	
}
