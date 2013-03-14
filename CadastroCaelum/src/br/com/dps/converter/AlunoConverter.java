package br.com.dps.converter;

import java.util.List;

import org.json.JSONStringer;

import br.com.dps.modelo.Aluno;

public class AlunoConverter {
	
	public String toJSON( List<Aluno> alunos ) {
		String json = "";
		try {
			JSONStringer jsonStringer = new JSONStringer();
			jsonStringer.object().key("list").array().object().key("aluno").array();
			
			for (Aluno aluno : alunos) {
				jsonStringer.object()
					.key("id").value(aluno.getId())
					.key("nome").value(aluno.getNome())
					.key("telefone").value(aluno.getTelefone())
					.key("endereco").value(aluno.getEndereco())
					.key("site").value(aluno.getSite())
					.key("nota").value(aluno.getNota())
				.endObject();
			}
			
			json = jsonStringer.endArray().endObject().endArray().endObject().toString();
		} catch (Exception e) {
		}
		
		return json;
	}

}
