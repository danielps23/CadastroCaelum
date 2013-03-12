package br.com.dps.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import br.com.dps.helper.ConexaoHelper;
import br.com.dps.modelo.Aluno;

public class AlunoDAO {

	private static final String TABELA = "Aluno";

	private static final String[] COLUNAS = { "id", "nome", "telefone",
			"endereco", "site", "nota", "foto" };

	public void insere(Aluno aluno) {
		ContentValues values = toValues(aluno);
		ConexaoHelper.getInstance().getWritableDatabase()
				.insert(TABELA, null, values);
	}

	private ContentValues toValues(Aluno aluno) {
		ContentValues values = new ContentValues();
		values.put("nome", aluno.getNome());
		values.put("telefone", aluno.getTelefone());
		values.put("endereco", aluno.getEndereco());
		values.put("site", aluno.getSite());
		values.put("nota", aluno.getNota());
		values.put("foto", aluno.getFoto());

		return values;
	}

	public List<Aluno> getLista() {
		List<Aluno> alunos = new ArrayList<Aluno>();

		Cursor c = null;
		
		try {
			c = ConexaoHelper.getInstance().getReadableDatabase()
					.query(TABELA, COLUNAS, null, null, null, null, null);
			
			while (c.moveToNext()) {
				Aluno aluno = new Aluno();

				aluno.setId(c.getLong(0));
				aluno.setNome(c.getString(1));
				aluno.setTelefone(c.getString(2));
				aluno.setEndereco(c.getString(3));
				aluno.setSite(c.getString(4));
				aluno.setNota(c.getDouble(5));
				aluno.setFoto(c.getString(6));

				alunos.add(aluno);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( c != null ) {
				c.close();
			}
		}

		

		

		return alunos;
	}

	public void deletar(Aluno aluno) {
		String[] args = { aluno.getId().toString() };
		ConexaoHelper.getInstance().getWritableDatabase()
				.delete(TABELA, "id=?", args);
	}

}
