package Model.VO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OcorrenciaVO {
	 final DateTimeFormatter formaterDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	private int id;
	private int idCidadao;
	private LocalDate data;
	private String descricao;
	private String local;
	
	public OcorrenciaVO(int id, int idCidadao, LocalDate data, String descricao, String local) {
		super();
		this.id = id;
		this.idCidadao = idCidadao;
		this.data = data;
		this.descricao = descricao;
		this.local = local;
	}

	public OcorrenciaVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCidadao() {
		return idCidadao;
	}

	public void setIdCidadao(int idCidadao) {
		this.idCidadao = idCidadao;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
	
	
	

}
