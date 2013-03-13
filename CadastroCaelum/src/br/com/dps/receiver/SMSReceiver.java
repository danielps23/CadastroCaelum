package br.com.dps.receiver;

import br.com.dps.R;
import br.com.dps.dao.AlunoDAO;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();

		Object messages[] = (Object[]) bundle.get("pdus");
		SmsMessage smsMessage[] = new SmsMessage[messages.length];

		for (int i = 0; i < messages.length; i++) {
			smsMessage[i] = SmsMessage.createFromPdu((byte[]) messages[i]);
		}
		
		AlunoDAO dao = new AlunoDAO();
		if ( dao.isAluno( smsMessage[0].getDisplayOriginatingAddress())) {
			Toast.makeText(context, "SMS de Aluno: " + smsMessage[0].getMessageBody(), Toast.LENGTH_LONG).show();
			MediaPlayer mp = MediaPlayer.create(context, R.raw.msg);
			mp.start();
		}
	}

}
