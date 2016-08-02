package cp.proyects.album;


import java.util.ArrayList;

import cp.proyects.conexion.ConexionAlbum;
import Interface.DatosInicializacion;
import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


public class AlbumMainActivity extends Activity {

	TableLayout table;
	private static final int TABLE_ROW = 4;
	private static final int TABLE_COLUMN= 3;
	FotoAlbum ficha;
	SQLiteDatabase database;
	ConexionAlbum conexion;
	
	//variables para manejar las páginas
	private int paginaActual = 1;
	private int paginafinal = 0;
	Button btiz;
	Button btdr;
	Button btgetpackage, favoritos;
	TextView txtpag;
    String nombreDB = "DBALbum";
    ArrayList<FotoAlbum> fotospagina, fotos;
    ArrayList<Ficha> fichasobtendidas; //por ahor son las fichas de la base de datos
    int actual = 12;
    private int FICHASPAGINA = 12;
    private int fichasporpaquete = 4;
    private int totalfichas = 0;
    private int contfichasactuales = 0;
    //private 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_album_main);		
		
		
		fichasobtendidas = new ArrayList<Ficha>(); //arreglo de fichas obtenidas
		
		//creamos la conexion a la base de datos y la probamos
		conexion = new ConexionAlbum(this, nombreDB, null, DatosInicializacion.VersionDB); //se crea la conexion si no existe
		database = conexion.getWritableDatabase();//configuramos para que se crea la base de datos la primera vez
		database.close();//cerramos la base de datos
			
		
		//llenamos la tabla ficha pero primero la borramos
		if(!conexion.determinarTablaFicha())
			conexion.llenarDatosFicha(); //actuliando los datos

		//abrimos la database
		totalfichas = conexion.getTotalFichas(); //verificamos el total de fichas que hay en el sistema
		Log.d("total imagenes","el total de fichas es:"+totalfichas);
		paginafinal = getPaginafinal();		
		Log.d("total imagenes","pagina final:"+paginafinal);
		
		//mostrar datos de la ficha
		fichasobtendidas = conexion.mostrarDatostablaFicha(); //se traen las fichas obtenidas del usuairo
				
		//instancio las filas iniciales
		btiz = (Button)findViewById(R.id.button1);
		btdr = (Button)findViewById(R.id.button2);
		btgetpackage = (Button)findViewById(R.id.getpack);
		txtpag = (TextView)findViewById(R.id.textView1);
		favoritos = (Button)findViewById(R.id.favoritos);
		
		//arreglos para el manego de las fotos		
		fotospagina = new ArrayList<FotoAlbum>();
		fotos = new ArrayList<FotoAlbum>();
		
		//manejo de enventos de los botones
		btiz.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				paginaActual--;				
				//limpiarTabla();
				//poblarFilas(determinarNumFichas());
				updateUI();
			}
		});
		
		btdr.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				paginaActual++;				
				//limpiarTabla();
				//poblarFilas(determinarNumFichas());
				updateUI();
			}
		});
			
		//evento que genera las 4 fichas
		btgetpackage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				getFichas();
				updateUI();
			}
		});
		
		//botón favoritos
		favoritos.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(AlbumMainActivity.this, "estos son los favoritos", Toast.LENGTH_LONG).show();
			}
		});
		
		//cargo la pagina del album(fichas)
		table = (TableLayout)findViewById(R.id.tablaimagenes);
		//se llena la tabla
		poblarFilas(0);	
		//actulizo la página
		updateUI();
	}//fin oncreate

	public void poblarFilas(int filasinicial){
		
		int id=0;		
		//creo las filas
		for(int i = 0; i<TABLE_ROW; i++){
			
			TableRow fila = new TableRow(this);
			//ahora creamos los elemntos
			id = i*TABLE_COLUMN + filasinicial;
			for(int j=1; j<=TABLE_COLUMN;j++){
				
				//genero el id que tendrá cada ficha
				int temp = id;
				temp = id + j;
				
				//string que tendra el id en formato 0+numero
				String idfoto;
				if(temp <= 9){
					idfoto = "0"+temp;
				}else{
					idfoto = ""+temp;
				}
						
				//se crea una ficha de tipo fotoalbum
				ficha = new FotoAlbum(this);
				
				//determino si ya se consiguio la foto
			//	if(temp == 5 || temp == 20)
			//		ficha.setConseguida(true);
				
				//busco si el id de la ficha ya fue registrado
			/*	if(determinarFichaobtenida(temp+1)){
					Ficha ft = getFichaobtenida(temp);
					
					ficha.setConseguida(true);
					ficha.setIdfoto(ft.getIdficha()+" ");
					ficha.setTituloFicha(ft.getNombrepersonaje());
					ficha.setId(temp);					
					ficha.setImagenFicha(setImage(temp));						
				}else{
					ficha.setIdfoto(idfoto);
					ficha.setTituloFicha("ficha-"+idfoto);
					ficha.setId(temp);
					ficha.setImagenFicha(0);
				}*/				
				
				Ficha fichatemp = determinarFichaobtenida(temp+1);
			//	Log.e("ficha actual", "la ficha dio: "+fichatemp);
				if(fichatemp != null){
				//	Ficha ft = getFichaobtenida(temp);
					ficha.setConseguida(true);
					//ficha.setIdfoto(ft.getIdficha()+" ");
					//ficha.setTituloFicha(ft.getNombrepersonaje());
					Log.e("ficha actual", "Entro aqui???: "+fichatemp.getIdficha());
					ficha.setIdfoto(fichatemp.getIdficha()+" ");
					ficha.setTituloFicha(fichatemp.getNombrepersonaje());
					ficha.setId(fichatemp.getIdficha());
					ficha.setImagenFicha(setImage(fichatemp.getFoto()));
					//ficha.setId(temp);					
					//ficha.setImagenFicha(setImage(temp));
				}else{
					ficha.setIdfoto(idfoto);
					ficha.setTituloFicha("ficha-"+idfoto);
					ficha.setId(temp);
					ficha.setImagenFicha(0);
				}				
				
				//limite de fichas indicada
				//fotospagina.add(ficha);
		
				if(ficha.getId() <= totalfichas){
					fila.addView(ficha);					
					//break;
				}
				
				//contfichasactuales++;
			}//fin for columnas	
			
			table.addView(fila);
			//if(contfichasactuales >= totalfichas)
			//	break;
					
		}//fin for filas
		
	}//fin poblar filas
		
	//me´tod par actulizar la interfaz
	public void updateUI(){
		
		//lleno nuevamente la lista de fichas obtenidas (seria mas optimo actualizar pero solo rellenare el arreglo)
		fichasobtendidas = conexion.mostrarDatostablaFicha();
		
		
		//compruebo si es la página 1
		if(paginaActual == 1){
			btiz.setVisibility(Button.INVISIBLE);
		}else{
			btiz.setVisibility(Button.VISIBLE);
		}
		
		//compruebo el botón final
		if(paginaActual == paginafinal){
			btdr.setVisibility(Button.INVISIBLE);
		}else{
			btdr.setVisibility(Button.VISIBLE);
		}
		
		//actulizo el titulo de la pagina
		txtpag.setText("PAGINA "+paginaActual);
		limpiarTabla();
		poblarFilas(determinarNumFichas());
	}//fin 
	
	//método para determinar el numero de las fichas
	public int determinarNumFichas(){
		
		int multiplicador = paginaActual - 1;
		this.actual = multiplicador * FICHASPAGINA;		
		return multiplicador * FICHASPAGINA;
	}
	
	//determinar ficha obtenida
/*	public boolean determinarFichaobtenida(int idficha){
		for (Ficha f:  fichasobtendidas){
			if(f.getIdficha() == idficha){
		  		//Log.e("la ficha","la ficha tal esta funcionando "+idficha+" - "+f.getIdficha());		  		
				return true;				
			}
		}		
		return false;
	}*/

	public Ficha determinarFichaobtenida(int idficha){
		
		//Ficha ftobtenida = null; 
		for (Ficha f:  fichasobtendidas){
			if(f.getIdficha() == idficha){
		  		//Log.e("la ficha","la ficha tal esta funcionando "+idficha+" - "+f.getIdficha());		  		
				return f;				
			}
		}		
		return null;
	}
	
	//obtener la ficha ficha obtenida
	public Ficha getFichaobtenida(int idficha){
		
		Ficha fipr = new Ficha();
		for (Ficha f:  fichasobtendidas){
			if(f.getIdficha() == idficha)
				fipr = f;
		}
		
		return fipr;
	}
	
	//funcion para limpiar las filas
	public void limpiarTabla(){
		table.removeAllViews();
		
	}
	
	//funcion para devolver el id de la ficha
	public int getidficha(int idficha){
		
		int ficha = 0;
		for(Ficha f: fichasobtendidas){			
			if(f.getIdficha() == idficha){
				ficha = f.getIdficha();	
				Log.d("imagen desde set image"," "+ficha);
			}			 
		}
		Log.d("imagen desde set image- final"," "+ficha);
		return ficha;		
	}///fin 	
	
	//establecer imagen de la ficha
/*	public int setImage(int idficha){		
		int resID;
		resID = 0;	
		//String nombrefoto = "lucyheartfiliaft";
		int idfinal = idficha;
		
		/*if(getidficha(idficha) != 0){
			
			//int pt = getidficha(idfinal); 
			int pt = getidficha(idfinal);			
			String fichaimage = fichasobtendidas.get(pt).getFoto();			
			resID = getResources().getIdentifier(fichaimage , "drawable", getPackageName());
			//String fichaimage = "R.drawable."+fichasobtendidas.get(idficha).getFoto();			
		}	*/		
	/*	resID = getResources().getIdentifier(fichaimage , "drawable", getPackageName());
	    return resID;	
	}*/
	
	public int setImage(String idficha){		
		int resID;
		resID = 0;	
		resID = getResources().getIdentifier(idficha, "drawable", getPackageName());
	    return resID;	
	}
	
	//método para calcular los figuras nuevas
	public void getFichas(){
		int fichaescogida = 0;
		for(int i=0; i< fichasporpaquete; i++){			
			fichaescogida = (int)(1+Math.random()*totalfichas);
			//Toast.makeText(this, "la figura selecionada es: "+fichaescogida, Toast.LENGTH_LONG).show();
			//se registran en la base de datos
			conexion.insetarFichasObtenidas(fichaescogida);
		}
	}
	
	//determinar la pagina final
	public int getPaginafinal(){
		
		double ft = (float)totalfichas/12.0;
		Log.d("pagina final internto ","Math.floor(totalfichas/12)"+ Math.ceil(ft));
		return (int) Math.ceil(ft); 
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.album_main, menu);
		return true;
	}
	
	
	
}
