package cadastro;

public class Pessoa {

	private String nome;
	private int idade;
	private String cpf;
	private String logradouro;
	private String bairro;
	private String localidade;
	private String uf;

	public Pessoa(String nome, int idade, String cpf, String logradouro, String bairro, String localidade, String uf) {
		this.nome = nome;
		this.idade = idade;
		this.cpf = cpf;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
	}

	// Getters e Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Override
	public String toString() {
		return "Pessoa [Nome: " + nome + ", Idade: " + idade + ", CPF: " + cpf + ", Endereço: " + logradouro + ", "
				+ bairro + " - " + localidade + "/" + uf + "]";
	}
}
