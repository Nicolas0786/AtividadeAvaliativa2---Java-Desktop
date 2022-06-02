package Model.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Model.VO.CidadaoVO;
import Model.VO.OcorrenciaVO;

public class CidadaoDAO {

	DateTimeFormatter formaterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	SimpleDateFormat formate = new SimpleDateFormat("dd-MM-yyyy");

	private String getConfigValueByKey(String key) throws IOException {
		File f = new File("admin/config.ini");
		FileInputStream fis = new FileInputStream(f);
		byte[] conteudo = fis.readAllBytes();
		String fileContent = new String(conteudo);
		String[] variaveis = fileContent.split("\r\n");
		for (String variavel : variaveis) {
			int indexSeparator = variavel.indexOf("=");
			String keyTemp = variavel.substring(0, indexSeparator);
			if (keyTemp.equals(key)) {
				return variavel.substring(indexSeparator + 1, variavel.length());
			}
		}
		return null;
	}

	public java.sql.Connection conectaBD() throws SQLException, ClassNotFoundException, IOException {

		String url = this.getConfigValueByKey("url");
		String user = this.getConfigValueByKey("user");
		String passwd = this.getConfigValueByKey("password");
		java.sql.Connection con = DriverManager.getConnection(url, user, passwd);
		return con;

	}

	public List<CidadaoVO> buscarDadosCidadaoDAO() throws ClassNotFoundException, SQLException, IOException {
		List<CidadaoVO> cida = new ArrayList<CidadaoVO>();
		String query = "SELECT id_cidadao, nome, numero_documento, endereco, telefone, email FROM cidadao ORDER BY nome";
		Connection con = this.conectaBD();
		PreparedStatement pstm = con.prepareStatement(query);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			CidadaoVO cidadaoVO = new CidadaoVO();
			cidadaoVO.setIdCidadao(Integer.parseInt(rs.getString(1)));
			cidadaoVO.setNome(rs.getString(2));
			cidadaoVO.setNumeroDocumento(Long.parseLong(rs.getString(3)));
			cidadaoVO.setEndereco(rs.getString(4));
			cidadaoVO.setTelefone(rs.getString(5));
			cidadaoVO.setEmail(rs.getString(6));
			cida.add(cidadaoVO);
		}
		return cida;
	}

	public boolean deletarCidadaoDAO(String id) {
		try {
			String query = "DELETE FROM cidadao WHERE id_cidadao = ?";
			Connection c = this.conectaBD();
			PreparedStatement pstm = c.prepareStatement(query);
			pstm.setString(1, id);
			int resultado = pstm.executeUpdate();
			return resultado > 0 ? true : false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	/*
	 * public Object[][] buscarOcorrenciaCidadaoDAO(String idCliente) throws
	 * ClassNotFoundException, SQLException, IOException { String query =
	 * "SELECT id_ocorrencia, id_cidadao, data_ocorrencia, descricao, local_ocorrencia FROM ocorrencia WHERE id_cidadao =?"
	 * ; Connection con = this.conectaBD(); PreparedStatement pstm =
	 * con.prepareStatement(query); pstm.setString(1, idCliente); ResultSet rs =
	 * pstm.executeQuery(); ArrayList<String[]> list = new ArrayList<String[]>();
	 * while (rs.next()) { String[] linha = { rs.getString(1), rs.getString(2),
	 * rs.getString(3), rs.getString(4), rs.getString(5) }; list.add(linha); }
	 * Object[][] array = new Object[list.size()][5]; int i = 0; for (String[] tupla
	 * : list) { array[i] = list.get(i); i++; } // Object[][] array =
	 * (Object[][])list.toArray(); return array;
	 * 
	 * }
	 */

	public int insereRegistroDAO(String nome, String numero_doc, String endereço, String telefone, String email) {
		try {
			String query = "INSERT INTO cidadao (nome, numero_documento, endereco, telefone, email) VALUES (?, ?, ?, ?, ?)";
			Connection con = this.conectaBD();
			PreparedStatement pstm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, nome);
			pstm.setString(2, numero_doc);
			pstm.setString(3, endereço);
			pstm.setString(4, telefone);
			pstm.setString(5, email);
			pstm.executeUpdate();
			ResultSet rs = pstm.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			return id;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return -1;
	}

	public boolean atualizaRegistroDAO(String idCidadao, String nome, String numero_doc, String endereço, String telefone,
			String email) {
		try {
			String sql = "UPDATE cidadao SET nome=?, numero_documento=?, endereco=?, telefone=?, email=? WHERE id_cidadao=?";
			Connection c = this.conectaBD();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, nome);
			ps.setString(2, numero_doc);
			ps.setString(3, endereço);
			ps.setString(4, telefone);
			ps.setString(5, email);
			ps.setString(6, idCidadao);
			int linhasAfetadas = ps.executeUpdate();
			return linhasAfetadas > 0 ? true : false;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;

	}

	public boolean deletarOcorrenciaCidadaoDAO(String idOcoren) {
		try {
			String query = "DELETE FROM ocorrencia WHERE id_ocorrencia = ?";
			Connection c = this.conectaBD();
			PreparedStatement pstm = c.prepareStatement(query);
			pstm.setString(1, idOcoren);
			int resultado = pstm.executeUpdate();
			return resultado > 0 ? true : false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public boolean atualizaRegistroOcorrenciaDAO(String idOcorrencia, String idCidadao, String data, String descricao,
			String local) {
		try {
			String sql = "UPDATE ocorrencia SET id_cidadao=?, data_ocorrencia=?, descricao=?, local_ocorrencia=? WHERE id_ocorrencia=?";
			Connection c = this.conectaBD();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, idCidadao);
			ps.setDate(2, new java.sql.Date(formate.parse(data).getTime()));
			ps.setString(3, descricao);
			ps.setString(4, local);
			ps.setString(5, idOcorrencia);
			int linhasAfetadas = ps.executeUpdate();
			return linhasAfetadas > 0 ? true : false;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;

	}

	public int insereRegistroOcorrenciaDAO(String idCidadao, String data, String descricao, String local) {
		try {
			String query = "INSERT INTO ocorrencia (id_cidadao, data_ocorrencia, descricao, local_ocorrencia) VALUES (?, ?, ?, ?)";
			Connection con = this.conectaBD();
			PreparedStatement pstm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, idCidadao);
			pstm.setDate(2, new java.sql.Date(formate.parse(data).getTime()));
			pstm.setString(3, descricao);
			pstm.setString(4, local);
			pstm.executeUpdate();
			ResultSet rs = pstm.getGeneratedKeys();
			rs.next();
			int idOco = rs.getInt(1);
			return idOco;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return -1;
	}

	public List<OcorrenciaVO> buscarDadosOcorrenciaDAO(String idCliente)
			throws ClassNotFoundException, SQLException, IOException {
		List<OcorrenciaVO> oco = new ArrayList<OcorrenciaVO>();
		String query = "SELECT * FROM ocorrencia WHERE id_cidadao =?";
		Connection con = this.conectaBD();
		PreparedStatement pstm = con.prepareStatement(query);
		ResultSet rs = null;
		pstm.setString(1, idCliente);
		rs = pstm.executeQuery();
		try {

			while (rs.next()) {
				OcorrenciaVO ocorrenciaVO = new OcorrenciaVO();
				ocorrenciaVO.setId(Integer.parseInt(rs.getString(1)));
				ocorrenciaVO.setIdCidadao(Integer.parseInt(rs.getString(2)));
				ocorrenciaVO.setData(LocalDate.parse(rs.getString(3), formaterDate));
				ocorrenciaVO.setDescricao(rs.getString(4));
				ocorrenciaVO.setLocal(rs.getString(5));
				oco.add(ocorrenciaVO);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return oco;
	}

	public CidadaoVO inserirCidadaoDAO(CidadaoVO cidadao) throws SQLException, ClassNotFoundException, IOException {
		String query = "INSERT INTO cidadao (nome, numero_documento, endereco, telefone, email) VALUES (?, ?, ?, ?, ?)";
		Connection con = this.conectaBD();
		// PreparedStatement pstm = con.prepareStatement(query);
		PreparedStatement pstm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = null;

		try {
			pstm.setString(1, cidadao.getNome());
			pstm.setLong(2, cidadao.getNumeroDocumento());
			pstm.setString(3, cidadao.getEndereco());
			pstm.setString(4, cidadao.getTelefone());
			pstm.setString(5, cidadao.getEmail());
			rs = pstm.executeQuery();

			if (rs.next()) {
				cidadao.setIdCidadao(rs.getInt(1));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar a query de cadastro do usuario.");
			System.out.println("Erro " + e.getMessage());

		}

		return cidadao;
	}

}
