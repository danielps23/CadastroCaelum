package br.com.dps.fragment;

import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.dps.R;
import br.com.dps.modelo.Prova;

public class ListaProvasFragment extends Fragment {

	private ListView listViewProvas;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View layoutProvas = inflater.inflate(R.layout.provas_lista, container,
				false);

		this.listViewProvas = (ListView) layoutProvas
				.findViewById(R.id.lista_provas);

		Prova prova1 = new Prova("20/03/2012", "Matematica");
		prova1.setTopicos(Arrays.asList("Algebra Linear", "Integral",
				"Diferencial"));

		Prova prova2 = new Prova("25/03/2012", "Portugues");
		prova2.setTopicos(Arrays.asList("Complemento nominal",
				"Oracoes subordinadas"));

		List<Prova> provas = Arrays.asList(prova1, prova2);

		this.listViewProvas.setAdapter(new ArrayAdapter<Prova>(getActivity(),
				android.R.layout.simple_list_item_1, provas));

		this.listViewProvas
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					public void onItemClick(AdapterView<?> adapter, View view,
							int posicao, long id) {
						Prova provaSelecionada = (Prova) adapter
								.getItemAtPosition(posicao);

						// Toast.makeText(getActivity(), "Prova selecionada: " +
						// provaSelecionada, Toast.LENGTH_LONG).show();

						LayoutInflater inflater = ListaProvasFragment.this
								.getActivity().getLayoutInflater();
						View toastView = inflater
								.inflate(
										R.layout.toast,
										(ViewGroup) ListaProvasFragment.this
												.getActivity().findViewById(
														R.id.toast));

						TextView message = (TextView) toastView
								.findViewById(R.id.message);
						message.setText("Prova selecionada: "
								+ provaSelecionada);

						Toast toast = new Toast(getActivity());
						toast.setView(toastView);
						toast.show();
					}

				});

		return layoutProvas;
	}

}
