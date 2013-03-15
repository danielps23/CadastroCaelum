package br.com.dps;

import br.com.dps.fragment.DetalhesProvaFragment;
import br.com.dps.fragment.ListaProvasFragment;
import br.com.dps.modelo.Prova;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class ProvasActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.provas);

		if (bundle == null) {
			FragmentTransaction transaction = getSupportFragmentManager()
					.beginTransaction();
			if (isTablet()) {
				transaction.replace(R.id.provas_lista,
						new ListaProvasFragment(),
						ListaProvasFragment.class.getCanonicalName()).replace(
						R.id.provas_detalhe, new DetalhesProvaFragment(),
						DetalhesProvaFragment.class.getCanonicalName());
			} else {
				transaction.replace(R.id.provas_lista,
						new ListaProvasFragment(),
						ListaProvasFragment.class.getCanonicalName());
			}
			transaction.commit();
		}

	}

	public boolean isTablet() {
		return getResources().getBoolean(R.bool.isTablet);
	}

	public void selecionaProva(Prova prova) {
		Bundle argumentos = new Bundle();
		argumentos.putSerializable("prova", prova);

		DetalhesProvaFragment detalhesProva = new DetalhesProvaFragment();
		detalhesProva.setArguments(argumentos);

		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();

		if (!isTablet()) {
			transaction.replace(R.id.provas_lista, detalhesProva,
					DetalhesProvaFragment.class.getCanonicalName());
			transaction.addToBackStack(null);
		} else {
			transaction.replace(R.id.provas_detalhe, detalhesProva,
					DetalhesProvaFragment.class.getCanonicalName());
		}
		transaction.commit();

	}

}
