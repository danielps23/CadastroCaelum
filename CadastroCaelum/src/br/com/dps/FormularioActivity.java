package br.com.dps;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import br.com.dps.dao.AlunoDAO;
import br.com.dps.helper.FormularioHelper;
import br.com.dps.modelo.Aluno;

public class FormularioActivity extends Activity {
	
	private FormularioHelper helper;
	private AlunoDAO dao;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);
        
        helper = new FormularioHelper( this );
        
        dao = new AlunoDAO();
        
        Button botao = (Button) findViewById(R.id.botao);
        botao.setOnClickListener( new View.OnClickListener() {

			public void onClick(View view) {
				Aluno aluno = helper.pegaAluno();
				dao.insere(aluno);
				finish();				
			}
        	
        });
    }
	
}
