package br.com.dps;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
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
        
        listaAlunos.setOnItemLongClickListener( new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int posicao, long id) {
				Toast.makeText(ListaAlunosActivity.this, "Posição selecionada click longo: " + posicao + ", o aluno é " + alunos[posicao], Toast.LENGTH_LONG).show();
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
			Toast.makeText(ListaAlunosActivity.this, "Voce clicou no novoAluno", Toast.LENGTH_LONG).show();
			return false;
		default:
			return super.onOptionsItemSelected(item);
		}
    }
}