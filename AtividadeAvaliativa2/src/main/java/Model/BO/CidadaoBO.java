package Model.BO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import Model.DAO.CidadaoDAO;
import Model.VO.CidadaoVO;
import Model.VO.OcorrenciaVO;

public class CidadaoBO {

	public List<CidadaoVO> buscarDadosCidadaoBO() throws ClassNotFoundException, SQLException, IOException {
		CidadaoDAO cidadaoDAO = new CidadaoDAO();
		return cidadaoDAO.buscarDadosCidadaoDAO();
	}



	public boolean deletarCidadaoBO(String id) {
		CidadaoDAO cidadaoDAO = new CidadaoDAO();
		return cidadaoDAO.deletarCidadaoDAO(id);
	}



	/*
	 * public Object[][] buscarOcorrenciaCidadaoBO(String idCliente) throws
	 * ClassNotFoundException, SQLException, IOException { CidadaoDAO cidadaoDAO =
	 * new CidadaoDAO(); return cidadaoDAO.buscarOcorrenciaCidadaoDAO(idCliente); }
	 */



	public int insereRegistroBO(String nome, String numero_doc, String endereço, String telefone, String email) {
		CidadaoDAO cidadaoDAO = new CidadaoDAO();
		return cidadaoDAO.insereRegistroDAO(nome, numero_doc, endereço, telefone, email);
	}



	public void atualizaRegistroBO(String idCidadao, String nome, String numero_doc, String endereço, String telefone,
			String email) {
		CidadaoDAO cidadaoDAO = new CidadaoDAO();
		cidadaoDAO.atualizaRegistroDAO(idCidadao ,nome, numero_doc, endereço, telefone, email);
		
	}



	public boolean deletarOcorrenciaCidadaoBO(String idOcoren) {
		CidadaoDAO cidadaoDAO = new CidadaoDAO();
		return cidadaoDAO.deletarOcorrenciaCidadaoDAO(idOcoren);
	}



	public void atualizaRegistroOcorrenciaBO(String idOcorrencia, String idCidadao, String data, String descricao, String local) {
		CidadaoDAO cidadaoDAO = new CidadaoDAO();
		cidadaoDAO.atualizaRegistroOcorrenciaDAO(idOcorrencia ,idCidadao ,data, descricao, local);
	}



	public int insereRegistroOcorrenciaBO(String idCidadao, String data, String descricao, String local) {
		CidadaoDAO cidadaoDAO = new CidadaoDAO();
		return cidadaoDAO.insereRegistroOcorrenciaDAO(idCidadao, data, descricao, local);
	}



	public List<OcorrenciaVO> buscarDadosOcorrenciaBO(String idCliente) throws ClassNotFoundException, SQLException, IOException {
		CidadaoDAO cidadaoDAO = new CidadaoDAO();
		return cidadaoDAO.buscarDadosOcorrenciaDAO(idCliente);
	}



	public CidadaoVO inserirCidadaoBO(CidadaoVO cidadao) throws ClassNotFoundException, SQLException, IOException {
		CidadaoDAO cidadaoDAO = new CidadaoDAO();
		return cidadaoDAO.inserirCidadaoDAO(cidadao);
	}

}
