package br.com.dps.map;

import java.util.List;
import java.util.Locale;

import com.google.android.gms.maps.model.LatLng;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

public class Localizador {
	
	private Geocoder geo;
	
	public Localizador(Context context ) {
		geo = new Geocoder(context, Locale.getDefault());
	}
	
	public LatLng getCoordenada(String endereco) {
		LatLng latLng = null;
		try {
			List<Address> listaEnderecos = geo.getFromLocationName(endereco, 1);
			if ( !listaEnderecos.isEmpty() ) {
				Address address = listaEnderecos.get(0);
				latLng = new LatLng(address.getLatitude(), address.getLongitude());
			} 
			
		} catch (Exception e) {
		}
		
		return latLng;
	}
}
