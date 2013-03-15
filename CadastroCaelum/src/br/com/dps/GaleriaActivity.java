package br.com.dps;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import br.com.dps.adapter.GaleriaAlunosAdapter;
import br.com.dps.dao.AlunoDAO;
import br.com.dps.modelo.Aluno;

public class GaleriaActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.galeria);
		
		AlunoDAO dao = new AlunoDAO();
		List<Aluno> alunos = dao.getLista();
		Collections.sort(alunos, new Comparator<Aluno>() {

			public int compare(Aluno a1, Aluno a2) {
				return a1.getNome().compareToIgnoreCase(a2.getNome());
			}
		});

		GaleriaAlunosAdapter galeriaAdapter = new GaleriaAlunosAdapter(this, alunos);
		ViewPager pager = (ViewPager) findViewById(R.id.gallery);
		pager.setAdapter(galeriaAdapter);
	}

}
