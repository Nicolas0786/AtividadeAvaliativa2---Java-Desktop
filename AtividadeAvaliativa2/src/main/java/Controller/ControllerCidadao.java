package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import Model.BO.CidadaoBO;
import Model.VO.CidadaoVO;
import Model.VO.OcorrenciaVO;

public class ControllerCidadao {

	public List<CidadaoVO> buscarDadosCidadao() throws ClassNotFoundException, SQLException, IOException {
		CidadaoBO cidadaoBO = new CidadaoBO();
		return cidadaoBO.buscarDadosCidadaoBO();
	}



	public boolean deleteRegistro(String id) {
		CidadaoBO cidadaoBO = new CidadaoBO();
		return cidadaoBO.deletarCidadaoBO(id);
	}



	/*
	 * public Object[][] buscarOcorrenciaCidadao(String idCliente) throws
	 * ClassNotFoundException, SQLException, IOException { CidadaoBO cidadaoBO = new
	 * CidadaoBO(); return cidadaoBO.buscarOcorrenciaCidadaoBO(idCliente); }
	 */

	public static int insereRegistro(String nome, String numero_doc, String endereço, String telefone, String email) {
		CidadaoBO cidadaoBO = new CidadaoBO();
		return cidadaoBO.insereRegistroBO(nome, numero_doc, endereço, telefone, email);
		
	}



	public void atualizaRegistro(String idCidadao, String nome, String numero_doc, String endereço, String telefone,
			String email) {
		CidadaoBO cidadaoBO = new CidadaoBO();
		cidadaoBO.atualizaRegistroBO(idCidadao ,nome, numero_doc, endereço, telefone, email);
		
	}



	public boolean deleteRegistroOcorrencia(String idOcoren) {
		CidadaoBO cidadaoBO = new CidadaoBO();
		return cidadaoBO.deletarOcorrenciaCidadaoBO(idOcoren);
	}



	public void atualizaRegistroOcorrencia(String idOcorrencia, String idCidadao, String data, String descricao,
			String local) {
		CidadaoBO cidadaoBO = new CidadaoBO();
		cidadaoBO.atualizaRegistroOcorrenciaBO(idOcorrencia ,idCidadao ,data, descricao, local);
	}



	public static int insereRegistroOcorrencia(String idCidadao, String data, String descricao, String local) {
		CidadaoBO cidadaoBO = new CidadaoBO();
		return cidadaoBO.insereRegistroOcorrenciaBO(idCidadao, data, descricao, local);
		
		
	}



	public List<OcorrenciaVO> buscarDadosOcorrencia(String idCliente) throws ClassNotFoundException, SQLException, IOException {
		CidadaoBO cidadaoBO = new CidadaoBO();
		return cidadaoBO.buscarDadosOcorrenciaBO(idCliente);
	}



	public static CidadaoVO inserirCidadao(CidadaoVO cidadao) throws ClassNotFoundException, SQLException, IOException {
		CidadaoBO cidadaoBO = new CidadaoBO();
		return cidadaoBO.inserirCidadaoBO(cidadao);
	}



	
}
