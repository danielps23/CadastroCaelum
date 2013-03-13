package br.com.dps.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
	private ImageView foto;
	private Aluno aluno;

	public FormularioHelper(FormularioActivity activity) {
		nome = (EditText) activity.findViewById(R.id.nome);
		telefone = (EditText) activity.findViewById(R.id.telefone);
		endereco = (EditText) activity.findViewById(R.id.endereco);
		site = (EditText) activity.findViewById(R.id.site);
		nota = (SeekBar) activity.findViewById(R.id.nota);
		foto = (ImageView) activity.findViewById(R.id.imagem);
		aluno = new Aluno();
	}

	public Aluno pegaAluno() {
		aluno.setNome(nome.getEditableText().toString());
		aluno.setTelefone(telefone.getEditableText().toString());
		aluno.setEndereco(endereco.getEditableText().toString());
		aluno.setSite(site.getEditableText().toString());
		aluno.setNota(Double.valueOf(nota.getProgress()));

		return aluno;
	}

	public void colocaNoFormulario(Aluno aluno) {
		nome.setText(aluno.getNome());
		telefone.setText(aluno.getTelefone());
		site.setText(aluno.getSite());
		endereco.setText(aluno.getEndereco());
		nota.setProgress(aluno.getNota().intValue());

		if (aluno.getFoto() != null) {
			this.carregaImagem(aluno.getFoto());
		}

		this.aluno = aluno;
	}

	public ImageView getFoto() {
		return foto;
	}

	public void carregaImagem(String localArquivoFoto) {
		Bitmap imagemFoto = BitmapFactory.decodeFile(localArquivoFoto);
		Bitmap imagemFotoReduzida = Bitmap.createScaledBitmap(imagemFoto, 100,
				100, true);

		aluno.setFoto(localArquivoFoto);
		foto.setImageBitmap(imagemFotoReduzida);
	}

}
