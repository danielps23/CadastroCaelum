package br.com.dps;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.dps.dao.AlunoDAO;
import br.com.dps.helper.ConexaoHelper;
import br.com.dps.modelo.Aluno;
import br.com.dps.util.Extras;

public class ListaAlunosActivity extends Activity {

	private List<Aluno> alunos;

	private ListView listaAlunos;

	private Aluno alunoSelecionado;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ConexaoHelper.createInstance(this);

		listaAlunos = (ListView) findViewById(R.id.lista_alunos);

		listaAlunos.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> adapter, View view,
					int posicao, long id) {
				Intent edicao = new Intent(ListaAlunosActivity.this,
						FormularioActivity.class);
				Aluno alunoSelecionado = (Aluno) listaAlunos
						.getItemAtPosition(posicao);
				edicao.putExtra(Extras.ALUNO_SELECIONADO, alunoSelecionado);
				startActivity(edicao);
			}
		});

		listaAlunos.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int posicao, long id) {

				alunoSelecionado = (Aluno) adapter.getItemAtPosition(posicao);
				return false;
			}

		});

		registerForContextMenu(listaAlunos);

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);

		MenuItem ligar = menu.add("Ligar");
		Intent intentLigar = new Intent(Intent.ACTION_CALL);
		intentLigar.setData(Uri.parse("tel:" + alunoSelecionado.getTelefone()));
		ligar.setIntent(intentLigar);

		MenuItem sms = menu.add("Enviar SMS");
		Intent intentSms = new Intent(Intent.ACTION_VIEW);
		intentSms.setData(Uri.parse("sms:" + alunoSelecionado.getTelefone()));
		intentSms.putExtra("sms_body", "sent from my Android");
		sms.setIntent(intentSms);

		MenuItem mapa = menu.add("Achar no Mapa");
		Intent intentMapa = new Intent(Intent.ACTION_VIEW);
		intentMapa.setData(Uri.parse("geo:0,0?z=14&q="
				+ alunoSelecionado.getEndereco()));
		mapa.setIntent(intentMapa);

		MenuItem site = menu.add("Navegar no site");
		Intent intentSite = new Intent(Intent.ACTION_VIEW);
		intentSite.setData(Uri.parse(alunoSelecionado.getSite()));
		site.setIntent(intentSite);

		MenuItem deletar = menu.add("Deletar");
		menu.add("Enviar por E-mail");

		deletar.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			public boolean onMenuItemClick(MenuItem item) {
				new AlertDialog.Builder(ListaAlunosActivity.this)
						.setIcon(android.R.drawable.ic_dialog_alert)
						.setTitle("Deletar")
						.setMessage("Deseja mesmo deletar?")
						.setPositiveButton("Sim",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										AlunoDAO dao = new AlunoDAO();
										dao.deletar(alunoSelecionado);
										carregaLista();
									}
								}).setNegativeButton("Não", null).show();

				return false;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_principal, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.novo:
			Intent intent = new Intent(ListaAlunosActivity.this,
					FormularioActivity.class);
			startActivity(intent);
			return false;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		carregaLista();
	}

	private void carregaLista() {
		AlunoDAO dao = new AlunoDAO();
		alunos = dao.getLista();
		Collections.sort(alunos, new Comparator<Aluno>() {

			public int compare(Aluno a1, Aluno a2) {
				return a1.getNome().compareToIgnoreCase(a2.getNome());
			}
		});

		ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this,
				android.R.layout.simple_list_item_1, alunos);
		listaAlunos.setAdapter(adapter);
	}

	@Override
	protected void onDestroy() {
		ConexaoHelper.getInstance().close();
		super.onDestroy();
	}
}