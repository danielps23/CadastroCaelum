package br.com.dps.adapter;

import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.dps.R;
import br.com.dps.modelo.Aluno;

public class GaleriaAlunosAdapter extends PagerAdapter {

	private final List<Aluno> alunos;
	private final Activity activity;

	public GaleriaAlunosAdapter(Activity activity, List<Aluno> alunos) {
		this.activity = activity;
		this.alunos = alunos;
	}

	public int getCount() {
		return alunos.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object obj) {
		return view.equals(obj);
	}

	@Override
	public void destroyItem(ViewGroup pager, int position, Object object) {
		((ViewPager) pager).removeView((View) object);
	}

	@Override
	public Object instantiateItem(ViewGroup pager, int posicao) {
		
		Aluno aluno = alunos.get(posicao);
		
		TextView alunoNome = new TextView(activity);
		LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		alunoNome.setLayoutParams(lp);
		alunoNome.setText(aluno.getNome());
		
		
		ImageView foto = new ImageView(activity);
		if (aluno.getFoto() != null) {
			Bitmap bm = BitmapFactory.decodeFile(aluno.getFoto());
			foto.setImageBitmap(Bitmap.createScaledBitmap(bm, 200, 200, true));
		} else {
			foto.setImageResource(R.drawable.ic_no_image);
		}
		
		
//		((ViewPager) pager).addView(alunoNome, 0);
		((ViewPager) pager).addView(foto, 0);
		return foto;
	}

}
