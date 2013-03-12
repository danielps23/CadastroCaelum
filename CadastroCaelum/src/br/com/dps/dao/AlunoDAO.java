package br.com.dps.dao;

import br.com.dps.modelo.Aluno;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AlunoDAO {

	private static final int VERSAO = 1;
	private static final String TABELA = "Aluno";
	private static final String DATABASE = "FJ57";
	
	private SQLiteOpenHelper sqliteHelper;

	public AlunoDAO(Context context) {
		sqliteHelper = new SQLiteOpenHelper(context, DATABASE, null, VERSAO) {
			
			@Override
			public void onCreate(SQLiteDatabase database) {
				String ddl = "CREATE TABLE " + TABELA + " (id INTEGER PRIMARY KEY, "
						+ " nome TEXT UNIQUE NOT NULL, telefone TEXT, endereco TEXT, "
						+ "site TEXT, nota REAL, foto TEXT);";
				database.execSQL(ddl);

			}

			@Override
			public void onUpgrade(SQLiteDatabase database, int versaoAntiga,
					int versaoNova) {
				String sql = "DROP TABLE IF EXISTS " + TABELA;
				database.execSQL(sql);
				onCreate(database);
			}
		};
	}

	public void insere(Aluno aluno) {
		ContentValues values = toValues(aluno);
		sqliteHelper.getWritableDatabase().insert(TABELA, null, values);
	}

	private ContentValues toValues(Aluno aluno) {
		ContentValues values = new ContentValues();
		values.put("nome", aluno.getNome());
		values.put("telefone", aluno.getTelefone());
		values.put("endereco", aluno.getEndereco());
		values.put("site", aluno.getSite());
		values.put("nota", aluno.getNota());
		values.put("foto", aluno.getFoto());

		return values;
	}
	
	public void close() {
		sqliteHelper.close();
	}

}
