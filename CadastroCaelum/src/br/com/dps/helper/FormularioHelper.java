package br.com.dps.helper;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import br.com.dps.FormularioActivity;
import br.com.dps.R;
import br.com.dps.modelo.Aluno;

public class FormularioHelper {
	
	private EditText nome;
	private EditText telefone;
	private EditText endereco;
	private EditText site;
	private SeekBar nota;
	private ImageView botaoImagem;
	private Aluno aluno;
	
	
	public FormularioHelper( FormularioActivity activity ) {
		nome = (EditText) activity.findViewById(R.id.nome);
		telefone = (EditText) activity.findViewById(R.id.telefone);
		endereco = (EditText) activity.findViewById(R.id.endereco);
		site = (EditText) activity.findViewById(R.id.site);
		nota = (SeekBar) activity.findViewById(R.id.nota);
		botaoImagem = (ImageView) activity.findViewById(R.id.imagem);
		aluno = new Aluno();
	}
	
	public Aluno pegaAluno() {
		aluno.setNome( nome.getEditableText().toString() );
		aluno.setTelefone( telefone.getEditableText().toString() );
		aluno.setEndereco( endereco.getEditableText().toString() );
		aluno.setSite( site.getEditableText().toString() );
		aluno.setNota(Double.valueOf(nota.getProgress()));
		
		return aluno;
	}

}
