package br.com.dps;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import br.com.dps.dao.AlunoDAO;
import br.com.dps.helper.FormularioHelper;
import br.com.dps.modelo.Aluno;
import br.com.dps.util.Extras;

public class FormularioActivity extends Activity {

	private static final int TIRA_FOTO = 123;
	private FormularioHelper helper;
	private AlunoDAO dao;
	private String localArquivoFoto;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);

		helper = new FormularioHelper(this);

		dao = new AlunoDAO();

		Button botao = (Button) findViewById(R.id.botao);
		botao.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Aluno aluno = helper.pegaAluno();
				dao.insereAltera(aluno);
				finish();
			}

		});

		Aluno alunoParaSerAlterado = (Aluno) getIntent().getSerializableExtra(
				Extras.ALUNO_SELECIONADO);
		if (alunoParaSerAlterado != null) {
			botao.setText("Alterar");
			this.helper.colocaNoFormulario(alunoParaSerAlterado);
		}

		ImageView foto = helper.getFoto();
		foto.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent irParaCamera = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);
				localArquivoFoto = Environment.getExternalStorageDirectory()
						+ "/" + System.currentTimeMillis()
						+ ".jpg";
				File arquivo = new File(localArquivoFoto);
				getIntent().putExtra("localFoto", localArquivoFoto);
				Uri localFoto = Uri.fromFile(arquivo);
				
				irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT, localFoto);
				startActivityForResult(irParaCamera, TIRA_FOTO);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if ( requestCode == TIRA_FOTO ) {
			if ( resultCode == Activity.RESULT_OK ) {
				String localFoto = getIntent().getStringExtra("localFoto");
				helper.carregaImagem(localFoto);
			} else {
				this.localArquivoFoto = null;
			}
		}
	}
}
