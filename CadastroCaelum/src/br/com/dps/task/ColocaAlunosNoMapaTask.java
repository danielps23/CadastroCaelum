package br.com.dps.task;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import br.com.dps.dao.AlunoDAO;
import br.com.dps.fragment.MapaFragment;
import br.com.dps.map.Localizador;
import br.com.dps.modelo.Aluno;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ColocaAlunosNoMapaTask extends
		AsyncTask<Object, Object, List<MarkerOptions>> {

	private ProgressDialog progress;
	private MapaFragment mapaFragment;

	public ColocaAlunosNoMapaTask(MapaFragment mapaFragment) {
		this.mapaFragment = mapaFragment;
	}

	@Override
	protected void onPreExecute() {
		progress = ProgressDialog.show(mapaFragment.getActivity(),
				"Aguarde...", "Marcando alunos no mapa...", true, true);
	}

	@Override
	protected List<MarkerOptions> doInBackground(Object... params) {
		Localizador localizador = new Localizador(mapaFragment.getActivity());

		AlunoDAO dao = new AlunoDAO();
		List<Aluno> alunos = dao.getLista();

		List<MarkerOptions> markers = new ArrayList<MarkerOptions>();

		for (Aluno aluno : alunos) {
			LatLng local = localizador.getCoordenada(aluno.getEndereco());

			if (local != null) {
				MarkerOptions marcador = new MarkerOptions().position(local)
						.title(aluno.getNome()).snippet(aluno.getEndereco());
				if (aluno.getFoto() != null) {
					Bitmap imagemFoto = BitmapFactory
							.decodeFile(aluno.getFoto());
					Bitmap imagemFotoReduzida = Bitmap.createScaledBitmap(
							imagemFoto, 60, 80, true);
					
					BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(imagemFotoReduzida);
					marcador.icon(icon);
				}
				markers.add(marcador);
			}
		}

		return markers;
	}

	@Override
	protected void onPostExecute(List<MarkerOptions> markers) {
		progress.dismiss();
		for (MarkerOptions marcador : markers) {
			mapaFragment.getMap().addMarker(marcador);
		}

	}

}
