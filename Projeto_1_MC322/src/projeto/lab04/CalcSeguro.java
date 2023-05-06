package projeto.lab04;

public enum CalcSeguro {
	VALOR_BASE (100.0),
	FATOR_18_30(1.2),
	FATOR_30_60(1.0),
	FATOR_60_90(1.5);
	
	private double f;
	
	private CalcSeguro(double f) {
		this.f = f;
	}
	
	double getF() {
		return this.f;
	}
}
