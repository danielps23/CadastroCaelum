package br.com.dps.fragment;

import br.com.dps.map.Localizador;
import br.com.dps.task.ColocaAlunosNoMapaTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapaFragment extends SupportMapFragment {
	
	@Override
	public void onResume() {
		super.onResume();
		
		Localizador localizador = new Localizador(getActivity());
		LatLng local = localizador.getCoordenada("Rua Vergueiro 3185 Vila Mariana");
		centralizaNo(local);
		
		new ColocaAlunosNoMapaTask(this).execute(this);
	}

	public void centralizaNo(LatLng local) {
		GoogleMap map = this.getMap();
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(local, 17));
		
	}

}
