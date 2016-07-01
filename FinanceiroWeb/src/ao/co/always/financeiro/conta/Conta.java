package ao.co.always.financeiro.conta;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import ao.co.always.financeiro.usuario.Usuario;

@Entity
@Table(name="conta_usuario")
public class Conta implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name="id_conta")
	private Integer Idconta;
	
	@ManyToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name="id_conta", nullable = false)
	private Usuario usuario;
	
	@Column(name="desc_conta")
	private String descricao;
	
	@Column(name="data_cadastro", nullable=false, updatable=false)
	private Date dataCadastro;
	
	@Column(name="saldo_inicial")
	private float saldoInicila;
	
	@Column(name="favorita")
	private boolean favorita;

	public Integer getIdconta() {
		return Idconta;
	}
	public void setIdconta(Integer idconta) {
		Idconta = idconta;
	}
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public float getSaldoInicila() {
		return saldoInicila;
	}
	public void setSaldoInicila(float saldoInicila) {
		this.saldoInicila = saldoInicila;
	}
	public boolean isFavorita() {
		return favorita;
	}
	public void setFavorita(boolean favorita) {
		this.favorita = favorita;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Idconta == null) ? 0 : Idconta.hashCode());
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + (favorita ? 1231 : 1237);
		result = prime * result + Float.floatToIntBits(saldoInicila);
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (Idconta == null) {
			if (other.Idconta != null)
				return false;
		} else if (!Idconta.equals(other.Idconta))
			return false;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (favorita != other.favorita)
			return false;
		if (Float.floatToIntBits(saldoInicila) != Float.floatToIntBits(other.saldoInicila))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

}
