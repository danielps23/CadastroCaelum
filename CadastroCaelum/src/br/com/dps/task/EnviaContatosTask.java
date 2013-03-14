package br.com.dps.task;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import br.com.dps.converter.AlunoConverter;
import br.com.dps.dao.AlunoDAO;
import br.com.dps.modelo.Aluno;
import br.com.dps.support.WebClient;

public class EnviaContatosTask extends AsyncTask<Object, Object, String> {
	
	private final Context context;
	private final String endereco = "http://www.caelum.com.br/mobile";
	
	private ProgressDialog progress;

	public EnviaContatosTask(Context context) {
		this.context = context;
	}
	
	@Override
	protected void onPreExecute() {
		progress = ProgressDialog.show(context, "Aguarde...", "Envio de dados para a web", true, true);
	}

	@Override
	protected String doInBackground(Object... params) {
		AlunoDAO dao = new AlunoDAO();
		List<Aluno> alunos = dao.getLista();
		
		AlunoConverter alunoConverter = new AlunoConverter();
		String json = alunoConverter.toJSON(alunos);
		
		WebClient client = new WebClient(endereco);
		String resposta = client.post(json);
		
		return resposta;
	}
	
	@Override
	protected void onPostExecute(String result) {
		progress.dismiss();
		Toast.makeText(context, result, Toast.LENGTH_LONG).show();
	}

}
