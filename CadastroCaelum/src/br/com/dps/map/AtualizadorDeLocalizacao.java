package br.com.dps.map;

import br.com.dps.fragment.MapaFragment;

import com.google.android.gms.maps.model.LatLng;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class AtualizadorDeLocalizacao implements LocationListener {
	
	private MapaFragment mapa;
	private LocationManager manager;
	
	public AtualizadorDeLocalizacao(Context context, MapaFragment mapa) {
		this.mapa = mapa;
		manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		
		int distanciaMinima = 20;
		int tempoMinimo = 2000;
		
		manager.requestLocationUpdates(getMelhorProvider(), tempoMinimo, distanciaMinima, this);
	}
	
	public void cancela() {
		manager.removeUpdates(this);
	}
	
	private String getMelhorProvider() {
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_COARSE);
		criteria.setAltitudeRequired(false);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_HIGH);
		String provider = this.manager.getBestProvider(criteria, true);
		
		return provider;
	}

	public void onLocationChanged(Location location) {
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		
		LatLng local = new LatLng(latitude, longitude);
		
		mapa.centralizaNo(local);
	}

	public void onProviderDisabled(String arg0) {

	}

	public void onProviderEnabled(String arg0) {

	}

	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
	}

}
