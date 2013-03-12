package br.com.dps.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexaoHelper extends SQLiteOpenHelper {

	private static final int VERSAO = 1;
	private static final String DATABASE = "FJ57";

	private static ConexaoHelper instance = null;

	private ConexaoHelper(Context context) {
		super(context, DATABASE, null, VERSAO);
	}

	public static ConexaoHelper createInstance( Context context ) {
		if (instance == null) {
			synchronized (ConexaoHelper.class) {
				if (instance == null) {
					instance = new ConexaoHelper( context);
				}
			}
		}
		return instance;
	}
	
	public static ConexaoHelper getInstance() {
		return instance;
	}
	
	public static void destroyInstance() {
		if ( instance != null ) {
			instance.close();
		}
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createTableAluno(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		upgradeTableAluno( db, oldVersion, newVersion);
	}

	private void createTableAluno(SQLiteDatabase db) {
		String ddl = "CREATE TABLE Aluno (id INTEGER PRIMARY KEY, "
				+ " nome TEXT UNIQUE NOT NULL, telefone TEXT, endereco TEXT, "
				+ "site TEXT, nota REAL, foto TEXT);";
		db.execSQL(ddl);
	}
	
	private void upgradeTableAluno(SQLiteDatabase db, int oldVersion,
			int newVersion) {
		String sql = "DROP TABLE IF EXISTS Aluno";
		db.execSQL(sql);
		createTableAluno(db);
	}

}
