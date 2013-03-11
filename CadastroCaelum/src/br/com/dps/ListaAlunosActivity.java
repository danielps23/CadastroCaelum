package br.com.dps;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListaAlunosActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final String []alunos = {"Daniel", "Anderson", "Felipe", "Guilherme"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alunos );
        ListView listaAlunos = (ListView) findViewById(R.id.lista_alunos);
        listaAlunos.setAdapter(adapter);
        
        listaAlunos.setOnItemClickListener( new OnItemClickListener() {

			public void onItemClick(AdapterView<?> adapter, View view, int posicao,
					long id) {
				Toast.makeText(ListaAlunosActivity.this, "Posição selecionada: " + posicao + ", o aluno é " + alunos[posicao], Toast.LENGTH_SHORT).show();				
			}
		});
    }
}