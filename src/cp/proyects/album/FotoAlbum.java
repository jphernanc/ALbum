package cp.proyects.album;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FotoAlbum  extends LinearLayout{
	Context context;
	
	TextView textoprincipal;
	TextView numeroficha;
	ImageView imagendeficha;
	private boolean conseguida;
	private onViewFichaListener listener;
	boolean presionado = false;
	
	//constructor 1
	 public FotoAlbum(Context context) {
	        super(context);
	        this.context = context;
	        inicializar();
	 }
	 
	 //constructor 2
	 public FotoAlbum(Context context, AttributeSet attrs) {
		 	super(context, attrs);
		 	this.context = context;
		 	inicializar();
	}
	 
	 //método para inicializar todos los componentes
	 public void inicializar(){
		 
		 conseguida = false;
		 //tomamos el valor del servicio de inflater 
		 String servicio = Context.LAYOUT_INFLATER_SERVICE;
		 LayoutInflater inf = (LayoutInflater)getContext().getSystemService(servicio);
		 inf.inflate(R.layout.foto_album, this, true);
		 
		 ///se llenan los campos del layout
		 textoprincipal = (TextView)findViewById(R.id.textoprincipal);
		 numeroficha = (TextView)findViewById(R.id.numeroficha);
		 imagendeficha = (ImageView)findViewById(R.id.fotopersonaje);
		 
		asignarEventos();
	 }//fin método inicializar
	 

	 //eventos del coso
	 public void asignarEventos(){
			 
		 imagendeficha.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View arg0, MotionEvent arg1) {
					// TODO Auto-generated method stub
					//Toast.makeText(context, "me tocaron "+getIdfoto(), Toast.LENGTH_SHORT ).show();
					if(conseguida)
						showInfoFicha();
					  // Log.e("tocando.... ", "se presino la ficha: "+getIdfoto());
					return false; //evita que se propague el evento a otros eventos
				}
			});		 
	 }
	 
	 //establecer el id de la foto
	 public void setIdfoto(String idfoto){
		 numeroficha.setText(idfoto);
	 }
	 
	 
	 //establecer el texto descriptivo de la imagen
	 public void setTituloFicha(String textoficha){
		 textoprincipal.setText(textoficha);
	 }
	 
	 //establecer la imagen de la ficha y que permita editarla
	 public void setImagenFicha(int identificadorficha){
		 
		 if(conseguida){
			imagendeficha.setImageResource(identificadorficha);
		 }else{
			 imagendeficha.setImageResource(R.drawable.perfil2); 
		 }
	 }//fin set image
	 
	 //se establece el metodo de escucha del listener
	 public void setOnViewFichaListener(onViewFichaListener li){
		 listener = li;
	 }
	 
	 //devolver el ide de la ficha
	 public String getIdfoto(){
		 return  numeroficha.getText().toString().trim();
	 }
	
	 //mostra inforamción completa de la ficha
	 public void showInfoFicha(){
		 
		 Intent showinfo = new Intent(context, FichaActivity.class);
		 Bundle id = new Bundle();
		 id.putString("id", getIdfoto());
		 showinfo.putExtras(id);
		 context.startActivity(showinfo);				 
	 }//se envie al otro
	 
	 
	 public void setConseguida(boolean cong){
		 conseguida = cong;
	 }
}
