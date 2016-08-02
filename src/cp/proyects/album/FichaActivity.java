package cp.proyects.album;

import cp.proyects.conexion.ConexionAlbum;
import Interface.DatosInicializacion;
import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class FichaActivity extends Activity {

	TextView descficha, tit1, tit2, tit3, titp;
	ImageView imagen;
	String datopasado;
	SQLiteDatabase database;
	ConexionAlbum conexion;
	String nombreDB = "DBALbum";
	Ficha fichactual;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ficha);
		
		//aqui recibo el intent enviado
		tit1 = (TextView)findViewById(R.id.tituloficha);
		tit2 = (TextView)findViewById(R.id.tituloficha2);
		tit3 = (TextView)findViewById(R.id.tituloficha3);
		titp = (TextView)findViewById(R.id.tituloprincipalficha);
		descficha = (TextView)findViewById(R.id.descficha);
		imagen = (ImageView)findViewById(R.id.fichapersonalizda);
		fichactual = new Ficha();
		
		//Recuperamos la información pasada en el intent
        Bundle bundle = this.getIntent().getExtras();
        datopasado = bundle.getString("id");
		
		
		//se obtiene la ficha enviada
		//creamos la conexion a la base de datos y la probamos
//		conexion = new ConexionAlbum(this, nombreDB, null, 5);
		conexion = new ConexionAlbum(this, nombreDB, null, DatosInicializacion.VersionDB);
		//abrimos la database
	//	Log.e("dato pasado","este es el dato pasado "+datopasado);
		fichactual = conexion.getFicha(Integer.parseInt(datopasado));
		
		///se ponen la información del personaje        
		titp.setText(fichactual.getNombrepersonaje());		
		tit1.setText(fichactual.getNombrepersonaje());
        tit2.setText("ficha: "+fichactual.getIdficha());
        tit3.setText("Anime: "+fichactual.getAnime());        
		descficha.setText(fichactual.getDescripcionpersonaje());
		
		//se establece la imagen
		int imagenid = getResources().getIdentifier(fichactual.getFoto(), "drawable", getPackageName());
		imagen.setImageResource(imagenid);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ficha, menu);
		return true;
	}

	//cerrar activity al presionar el boton atras
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
	    if ((keyCode == KeyEvent.KEYCODE_BACK))
	    {
	        finish();
	    }
	    
	   // Log.e("cerrar", "se presiono la tecla back del android");
	    return super.onKeyDown(keyCode, event);
	}
	
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
		//Log.e("cerrar", "se ejecuto onStop");
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
		//Log.e("cerrar", "se ejecuto onpause");
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
		//Log.e("cerrar", "se ejecuto on backpress");
	}
	
}

