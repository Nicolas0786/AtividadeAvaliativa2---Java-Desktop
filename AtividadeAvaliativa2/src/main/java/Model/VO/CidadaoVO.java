package Model.VO;

public class CidadaoVO {
	
	private int idCidadao;
	private String nome;
	private long numeroDocumento;
	private String endereco;
	private String telefone;
	private String email;
	
	public CidadaoVO(int idCidadao, String nome, long numeroDocumento, String endereco, String telefone, String email) {
		super();
		this.idCidadao = idCidadao;
		this.nome = nome;
		this.numeroDocumento = numeroDocumento;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
	}

	public CidadaoVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdCidadao() {
		return idCidadao;
	}

	public void setIdCidadao(int idCidadao) {
		this.idCidadao = idCidadao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	

}
