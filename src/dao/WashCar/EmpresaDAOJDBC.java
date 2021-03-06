package dao.WashCar;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.ConexaoUtil;
import exception.WashCar.RegistroExistente;
import exception.WashCar.RegistroNotExistente;
import model.WashCar.Cidade;
import model.WashCar.Empresa;
import model.WashCar.Pais;
import model.WashCar.UnidadeFederativa;

public class EmpresaDAOJDBC implements EmpresaDAO{

	private Connection connection;
	private String sql;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public EmpresaDAOJDBC() {
		connection = ConexaoUtil.openConnection();
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void inserir(Empresa empresa) throws RegistroExistente {
		sql = "insert into tb_empresa(razaoSocialEmpresa, nomeFantasiaEmpresa, cnpjEmpresa, inscricaoEstadualEmpresa, inscricaoMunicipalEmpresa, enderecoEmpresa, bairroEmpresa, numeroEmpresa, "
				+ "telefoneComercialEmpresa, telefoneCelularEmpresa, faxEmpresa, emailEmpresa, regimeTributario, empresaForaUso, dataAlteracaoEmpresa, idCidade)"
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?, ?)";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, empresa.getRazaoSocial());
			pstmt.setString(2, empresa.getNomeFantasia());
			pstmt.setString(3, empresa.getCnpj());
			pstmt.setString(4, empresa.getInscricaoEstadual());
			pstmt.setString(5, empresa.getInscricaoMunicipal());
			pstmt.setString(6, empresa.getEndereco());
			pstmt.setString(7, empresa.getBairro());
			pstmt.setInt(8, empresa.getNumero());
			pstmt.setString(9, empresa.getTelefoneComercial());
			pstmt.setString(10, empresa.getTelefoneCelular());
			pstmt.setString(11, empresa.getFax());
			pstmt.setString(12, empresa.getEmail());
			pstmt.setInt(13, empresa.getRegimeTributario());
			pstmt.setBoolean(14, empresa.isForaUso());
			pstmt.setDate(15, Date.valueOf(empresa.getDataAltercacao().now()));
			pstmt.setInt(16, empresa.getCidade().getIdCidade());
			pstmt.executeUpdate();
			empresa.setIdEmpresa(obterUltimoID(pstmt, rs));
		} catch (SQLException e) {
			throw new RegistroExistente(e.getMessage());
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public void alterar(Empresa empresa) throws RegistroExistente {
		sql = "update tb_empresa "
				+ "set tb_empresa.razaoSocialEmpresa = ?, tb_empresa.nomeFantasiaEmpresa = ?, tb_empresa.cnpjEmpresa = ?, "
				+ "tb_empresa.inscricaoEstadualEmpresa = ?, tb_empresa.inscricaoMunicipalEmpresa = ?, tb_empresa.enderecoEmpresa = ?, "
				+ "tb_empresa.bairroEmpresa = ?, tb_empresa.numeroEmpresa = ?, tb_empresa.telefoneComercialEmpresa = ?, "
				+ "tb_empresa.telefoneCelularEmpresa = ?, tb_empresa.faxEmpresa = ?, tb_empresa.emailEmpresa = ?, tb_empresa.regimeTributario = ?, "
				+ "tb_empresa.empresaForaUso = ?, tb_empresa.dataAlteracaoEmpresa = ?, tb_empresa.idCidade = ? "
				+ "where tb_empresa.idEmpresa = ?";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, empresa.getRazaoSocial());
			pstmt.setString(2, empresa.getNomeFantasia());
			pstmt.setString(3, empresa.getCnpj());
			pstmt.setString(4, empresa.getInscricaoEstadual());
			pstmt.setString(5, empresa.getInscricaoMunicipal());
			pstmt.setString(6, empresa.getEndereco());
			pstmt.setString(7, empresa.getBairro());
			pstmt.setInt(8, empresa.getNumero());
			pstmt.setString(9, empresa.getTelefoneComercial());
			pstmt.setString(10, empresa.getTelefoneCelular());
			pstmt.setString(11, empresa.getFax());
			pstmt.setString(12, empresa.getEmail());
			pstmt.setInt(13, empresa.getRegimeTributario());
			pstmt.setBoolean(14, empresa.isForaUso());
			pstmt.setDate(15, Date.valueOf(empresa.getDataAltercacao().now()));
			pstmt.setInt(16, empresa.getCidade().getIdCidade());
			pstmt.setInt(17, empresa.getIdEmpresa());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RegistroExistente(e.getMessage());
		}
	}

	@Override
	public void excluir(Empresa emprea) throws RegistroExistente {
		// TODO Auto-generated method stub
	}

	@Override
	public Empresa buscarId(Integer id) {
		Empresa empresa = null;
		sql = "select * from tb_empresa "
				+ "inner join tb_cidade "
				+ "on tb_empresa.idCidade = tb_cidade.idCidade "
				+ "inner join tb_UF "
				+ "on tb_cidade.idUF = tb_UF.idUF "
				+ "inner join tb_pais "
				+ "on tb_UF.idPais = tb_pais.idPais "
				+ "where tb_empresa.idEmpresa = ?";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				empresa = new Empresa();
				empresa.setIdEmpresa(rs.getInt("idEmpresa"));
				empresa.setRazaoSocial(rs.getString("razaoSocialEmpresa"));
				empresa.setNomeFantasia(rs.getString("nomeFantasiaEmpresa"));
				empresa.setCnpj(rs.getString("cnpjEmpresa"));
				empresa.setInscricaoEstadual(rs.getString("inscricaoEstadualEmpresa"));
				empresa.setInscricaoMunicipal(rs.getString("inscricaoMunicipalEmpresa"));
				empresa.setEndereco(rs.getString("enderecoEmpresa"));
				empresa.setBairro(rs.getString("bairroEmpresa"));
				empresa.setNumero(rs.getInt("numeroEmpresa"));
				empresa.setTelefoneComercial(rs.getString("telefoneComercialEmpresa"));
				empresa.setTelefoneCelular(rs.getString("telefoneCelularEmpresa"));
				empresa.setFax(rs.getString("faxEmpresa"));
				empresa.setEmail(rs.getString("emailEmpresa"));
				empresa.setRegimeTributario(rs.getInt("regimeTributario"));
				empresa.setForaUso(rs.getBoolean("empresaForaUso"));
				empresa.setDataAltercacao(rs.getDate("dataAlteracaoEmpresa").toLocalDate());
				Cidade cidade = new Cidade(rs.getInt("idCidade"), rs.getString("nomeCidade"));
				cidade.setUnidadeFederativa(new UnidadeFederativa(rs.getInt("idUF"), rs.getString("nomeUF"),
				new Pais(rs.getInt("idPais"), rs.getString("nomePais"))));
				empresa.setCidade(cidade);
			}
			if(empresa == null) {
				throw new RegistroNotExistente("N�o foi poss�vel encontrar nenhum registro "
						+ "para o conte�do de busca informado.\n"
						+ "Por gentileza, efetue uma nova pesquisa.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empresa;
	}

	@Override
	public List<Empresa> buscarDescricao(String descricao) {
		List<Empresa> empresas = new ArrayList<>();
		Empresa empresa = null;
		sql = "select * from tb_empresa "
				+ "inner join tb_cidade "
				+ "on tb_empresa.idCidade = tb_cidade.idCidade "
				+ "inner join tb_UF "
				+ "on tb_cidade.idUF = tb_UF.idUF "
				+ "inner join tb_pais "
				+ "on tb_UF.idPais = tb_pais.idPais "
				+ "where tb_empresa.razaoSocialEmpresa like ? "
				+ "or tb_empresa.nomeFantasiaEmpresa like ?";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, "%" + descricao +"%");
			pstmt.setString(2, "%" + descricao + "%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				empresa = new Empresa();
				empresa.setIdEmpresa(rs.getInt("idEmpresa"));
				empresa.setRazaoSocial(rs.getString("razaoSocialEmpresa"));
				empresa.setNomeFantasia(rs.getString("nomeFantasiaEmpresa"));
				empresa.setCnpj(rs.getString("cnpjEmpresa"));
				empresa.setInscricaoEstadual(rs.getString("inscricaoEstadualEmpresa"));
				empresa.setInscricaoMunicipal(rs.getString("inscricaoMunicipalEmpresa"));
				empresa.setEndereco(rs.getString("enderecoEmpresa"));
				empresa.setBairro(rs.getString("bairroEmpresa"));
				empresa.setNumero(rs.getInt("numeroEmpresa"));
				empresa.setTelefoneComercial(rs.getString("telefoneComercialEmpresa"));
				empresa.setTelefoneCelular(rs.getString("telefoneCelularEmpresa"));
				empresa.setFax(rs.getString("faxEmpresa"));
				empresa.setEmail(rs.getString("emailEmpresa"));
				empresa.setRegimeTributario(rs.getInt("regimeTributario"));
				empresa.setForaUso(rs.getBoolean("empresaForaUso"));
				empresa.setDataAltercacao(rs.getDate("dataAlteracaoEmpresa").toLocalDate());
				Cidade cidade = new Cidade(rs.getInt("idCidade"), rs.getString("nomeCidade"));
				cidade.setUnidadeFederativa(new UnidadeFederativa(rs.getInt("idUF"), rs.getString("nomeUF"),
				new Pais(rs.getInt("idPais"), rs.getString("nomePais"))));
				empresa.setCidade(cidade);
				empresas.add(empresa);
			}
			if(empresa == null) {
				throw new RegistroNotExistente("N�o foi poss�vel encontrar nenhum registro "
						+ "para o conte�do de busca informado.\n"
						+ "Por gentileza, efetue uma nova pesquisa.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empresas;
	}

	@Override
	public Empresa buscarDocumento(String codDocumento) {
		Empresa empresa = null;
		sql = "select * from tb_empresa "
				+ "inner join tb_cidade "
				+ "on tb_empresa.idCidade = tb_cidade.idCidade "
				+ "inner join tb_UF "
				+ "on tb_cidade.idUF = tb_UF.idUF "
				+ "inner join tb_pais "
				+ "on tb_UF.idPais = tb_pais.idPais "
				+ "where tb_empresa.cnpjEmpresa = ?";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, codDocumento);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				empresa = new Empresa();
				empresa.setIdEmpresa(rs.getInt("idEmpresa"));
				empresa.setRazaoSocial(rs.getString("razaoSocialEmpresa"));
				empresa.setNomeFantasia(rs.getString("nomeFantasiaEmpresa"));
				empresa.setCnpj(rs.getString("cnpjEmpresa"));
				empresa.setInscricaoEstadual(rs.getString("inscricaoEstadualEmpresa"));
				empresa.setInscricaoMunicipal(rs.getString("inscricaoMunicipalEmpresa"));
				empresa.setEndereco(rs.getString("enderecoEmpresa"));
				empresa.setBairro(rs.getString("bairroEmpresa"));
				empresa.setNumero(rs.getInt("numeroEmpresa"));
				empresa.setTelefoneComercial(rs.getString("telefoneComercialEmpresa"));
				empresa.setTelefoneCelular(rs.getString("telefoneCelularEmpresa"));
				empresa.setFax(rs.getString("faxEmpresa"));
				empresa.setEmail(rs.getString("emailEmpresa"));
				empresa.setRegimeTributario(rs.getInt("regimeTributario"));
				empresa.setForaUso(rs.getBoolean("empresaForaUso"));
				empresa.setDataAltercacao(rs.getDate("dataAlteracaoEmpresa").toLocalDate());
				Cidade cidade = new Cidade(rs.getInt("idCidade"), rs.getString("nomeCidade"));
				cidade.setUnidadeFederativa(new UnidadeFederativa(rs.getInt("idUF"), rs.getString("nomeUF"),
				new Pais(rs.getInt("idPais"), rs.getString("nomePais"))));
				empresa.setCidade(cidade);
			}
			if(empresa == null) {
				throw new RegistroNotExistente("N�o foi poss�vel encontrar nenhum registro "
						+ "para o conte�do de busca informado.\n"
						+ "Por gentileza, efetue uma nova pesquisa.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empresa;
	}

	@Override
	public List<Empresa> todos() {
		List<Empresa> empresas = new ArrayList<>();
		Empresa empresa = null;
		sql = "select * from tb_empresa "
				+ "inner join tb_cidade "
				+ "on tb_empresa.idCidade = tb_cidade.idCidade "
				+ "inner join tb_UF "
				+ "on tb_cidade.idUF = tb_UF.idUF "
				+ "inner join tb_pais "
				+ "on tb_UF.idPais = tb_pais.idPais";
		try {
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				empresa = new Empresa();
				empresa.setIdEmpresa(rs.getInt("idEmpresa"));
				empresa.setRazaoSocial(rs.getString("razaoSocialEmpresa"));
				empresa.setNomeFantasia(rs.getString("nomeFantasiaEmpresa"));
				empresa.setCnpj(rs.getString("cnpjEmpresa"));
				empresa.setInscricaoEstadual(rs.getString("inscricaoEstadualEmpresa"));
				empresa.setInscricaoMunicipal(rs.getString("inscricaoMunicipalEmpresa"));
				empresa.setEndereco(rs.getString("enderecoEmpresa"));
				empresa.setBairro(rs.getString("bairroEmpresa"));
				empresa.setNumero(rs.getInt("numeroEmpresa"));
				empresa.setTelefoneComercial(rs.getString("telefoneComercialEmpresa"));
				empresa.setTelefoneCelular(rs.getString("telefoneCelularEmpresa"));
				empresa.setFax(rs.getString("faxEmpresa"));
				empresa.setEmail(rs.getString("emailEmpresa"));
				empresa.setRegimeTributario(rs.getInt("regimeTributario"));
				empresa.setForaUso(rs.getBoolean("empresaForaUso"));
				empresa.setDataAltercacao(rs.getDate("dataAlteracaoEmpresa").toLocalDate());
				Cidade cidade = new Cidade(rs.getInt("idCidade"), rs.getString("nomeCidade"));
				cidade.setUnidadeFederativa(new UnidadeFederativa(rs.getInt("idUF"), rs.getString("nomeUF"),
				new Pais(rs.getInt("idPais"), rs.getString("nomePais"))));
				empresa.setCidade(cidade);
				empresas.add(empresa);
			}
			if(empresa == null) {
				throw new RegistroNotExistente("N�o foi poss�vel encontrar nenhum registro "
						+ "para o conte�do de busca informado.\n"
						+ "Por gentileza, efetue uma nova pesquisa.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empresas;
	}

	@Override
	public Integer obterUltimoID(PreparedStatement pstmt, ResultSet rs) throws RegistroExistente {
		try {
			rs = pstmt.executeQuery("select last_insert_id()");
			while(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}	
}