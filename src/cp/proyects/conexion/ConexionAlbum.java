package cp.proyects.conexion;

import java.util.ArrayList;
import cp.proyects.album.Ficha;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ConexionAlbum extends SQLiteOpenHelper {

	SQLiteDatabase database;
	
	//tabla usuario
	String createUsuarioTable = "create table usuarioalbum ("+
								"id_usuario integer primary key autoincrement,"+
								"nombre text,"+
								"nick text,"+
								"password text,"+
								"facebookid text"+
								")";
	
	//tabla ficha
	String createFichasTable = "create table Ficha(id_ficha integer primary key autoincrement,"+
							   "nombre text,"+
							   "descripcion text,"+
							   "genero text,"+
							   "anime text,"+
							   "foto text"+
							   ")";
	
	//ficha usuario
	String createUsuariofichatable ="create table FichaUsuario ("+
									"id_fichausuario integer primary key autoincrement,"+
									"id_usuario integer,"+
									"id_ficha integer,"+
									"cantidad integer,"+
									"favoritos boolean,"+
                                    "totalcambiar integer,"+
									"FOREIGN KEY(id_usuario) REFERENCES usuarioalbum(id_usuario),"+
									"FOREIGN KEY (id_ficha) REFERENCES FichaUsuario (id_ficha)"+
									")";
		
	String borrarusuario = "drop table if exists usuarioalbum";
	String borrarficha = "drop table if exists Ficha";
	String borrarfichausuario = "drop table if exists FichaUsuario";
	int IDusuario = 1;
	
	//constructor
	public ConexionAlbum(Context context, String nombre, CursorFactory factory, int version){
				super(context, nombre, factory, version);
	}
	
	//el oncreate se ejecuta la primera vez y crea la base de datos
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(createUsuarioTable);
		db.execSQL(createFichasTable);
		db.execSQL(createUsuariofichatable);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		//borramos todo de arriba para abajo
		db.execSQL(borrarfichausuario);
		db.execSQL(borrarusuario);
		db.execSQL(borrarficha);
		
		//se vuelve a crear todo
		db.execSQL(createUsuarioTable);
		db.execSQL(createFichasTable);
		db.execSQL(createUsuariofichatable);
	}//fin datos
	
	///llenar datos
	public boolean llenarDatosFicha(){
		
		database = this.getWritableDatabase();
		
		database.execSQL("insert into Ficha values(null ,"+
						"'Natsu Dragneel','Es miembro de uno de los gremios más poderosos"+
				        " de todo el Reino de Fiore , teniendo un potencial incluso mayor a"+
						" un mago clase S . Es comúnmente llamado Salamander al igual que Igneel,"+
				        " debido al tipo de magia que utiliza y a su gran resistencia. Junto a Lucy"+
						" Heartfilia, Gray Fullbuster, Erza Scarlet, Happy forman el Equipo de Natsu.',"+
                        "'masculino' , 'Fairy Tail',"+
                        "'natsudragneelft')");
		
		database.execSQL("insert into Ficha values(null ,"+
				"'Erza Scarlet','Es una Maga Clase S y denominada"+
				" como la mujer más fuerte de Fairy Tail y del Equipo de Natsu,"+
				" también es una de las principales protagonistas femeninas.',"+
		        "'femenino' , 'Fairy Tail',"+
                "'erzascarletft')");
		
		database.execSQL("insert into Ficha values(null ,"+
				"'Lucy Heartfilia ','Es una maga de Espíritus Celestiales y la protagonista femenina de la serie. Es una nueva miembro del Gremio Fairy Tail, uniéndose al principio de la historia. Su sueño era unirse a Fairy Tail, y después de conocer a Natsu Dragneel y Happy lo consiguió. Forma parte del equipo más fuerte del gremio en el cual esta con Natsu, Erza, Gray, Happy, Wendy y Charle.',"+
                "'femenino' , 'Fairy Tail',"+
				"'lucyheartfiliaft')");
		
		
		database.execSQL("insert into Ficha values(null ,"+
				"'Jellal Fernandes','Es un amigo de la infancia de Erza Scarlet donde la conoció en la (Torre del Cielo) o (R System). Sin embargo durante su estancia ahí, Jellal fue presuntamente poseído por el mago Oscuro Zeref y se volvió al mal en consecuencia de ello. Es el antagonista principal en la Saga de la Torre del Cielo. Aparece de nuevo en la Saga de Oración Seis para encontrar el Nirvana, aunque al parecer Jellal ya no es malvado. Es arrestado por el ejército del Nuevo Consejo Mágico. Actualmente forma parte del gremio independiente Crime Sorciere',"+
                "'masculino', 'Fairy Tail',"+
				"'jellalfernandesft')");
		
		
		database.execSQL("insert into Ficha values(null ,"+
				"'Gray Fullbuster','Es uno de los protagonistas de la serie, que hace equipo con Natsu Dragneel, Lucy Heartfilia, Erza Scarlet y Happy y miembro del gremio Fairy Tail simbolo Fairy Tail.',"+
                "'masculino', 'Fairy Tail',"+
				"'grayfullbusterft')");
		
		database.execSQL("insert into Ficha values(null ,"+
				"'Zeref','en algún momento de su vida, fue reconocido como el mago oscuro más poderoso de todos los tiempos, ya que poseía una magia extremadamente fuerte y terrorífica. Actualmente es un joven solitario, que no logra controlar todo su poder mágico.',"+
				"'masculino', 'Fairy Tail',"+
				"'zerefft')");
		
		database.execSQL("insert into Ficha values(null ,"+
				"'Mirajane Strauss','Es la chica portada de la revista Hechicero Semanal Sorcerers Weekly y la principal camarera del gremio de Fairy Tail. Es una de las magas más antigua de Fairy Tail y también es una maga clase-S,que dejó de luchar debido a la supuesta muerte de su hermana pequeña Lisanna, pero luego vuelve a luchar como una de los magos de clase-S del Gremio durante la batalla del festival. Lucy Heartfilia la admira mucho y Mirajane fue la primera en darle la bienvenida al Gremio.',"+
                "'femenino', 'Fairy Tail',"+
				"'mirajanestraussft')");
		
		database.execSQL("insert into Ficha values(null ,"+
				"'Gajeel Redfox','Es uno de los protagonistas de la serie, que hace equipo con Natsu Dragneel, Lucy Heartfilia, Erza Scarlet y Happy y miembro del gremio Fairy Tail simbolo Fairy Tail.',"+
                "'masculino', 'Fairy Tail',"+
				"'gajeelredfoxft')");
		
		
		database.execSQL("insert into Ficha values(null ,"+
				"'Laxus Dreyar','es uno de los miembros de Fairy Tail simbolo Fairy Tail, siendo este uno de los 4 Magos Clase-S del Gremio. Al igual que el miembro de Oración Seis Simbolo Oración Seis, Cobra, Laxus es un Dragón Slayer de Segunda Generación. Su abuelo es el actual maestro del Gremio, Makarov Dreyar, así como su padre es el maestro del Gremio Raven Tail symbol Raven Tail, Ivan Dreyar.',"+
                "'masculino', 'Fairy Tail',"+
				"'laxusdreyarft')");
		
		database.execSQL("insert into Ficha values(null ,"+
				"'Wendy Marvell','es una Dragon Slayer (Cazadragones) muy joven. Anteriormente miembro del gremio Cait Shelter Cait Shelter. Pero ahora es parte de Fairy Tail simbolo Fairy Tail. Aprendió sus habilidades gracias a Grandine, La Dragón del Cielo.',"+
                "'femenino', 'Fairy Tail',"+
				"'wendymarvellft')");
		
		database.execSQL("insert into Ficha values(null ,"+
				"'Gildarts Clive',' Es uno de los magos de Clase-S del gremio Fairy Tail (En el manga dicen que es la única persona capaz de aceptar los trabajos de rango SS). Es reconocido por todos los miembros como el más fuerte en el gremio aparte de Makarov y se ha debatido ampliamente sobre si es o no tan poderoso como dicho maestro. Tiene escasas apariciones en el gremio debido al gran numero de trabajos que tiene. Su fama como un mago también fue comentado por el Maestro de Phantom Lord, José Porla:',"+
                "'masculino', 'Fairy Tail',"+
				"'gildartscliveft')");
		
		database.execSQL("insert into Ficha values(null ,"+
				"'Acnologia','También conocido como El Dragón Negro del Apocalipsis y como el Rey de los Dragones, es un poderoso dragón que parece estar volando en todo el continente o el mundo, aunque no se sabe su verdadera ubicación. A diferencia de los otros dragones que se han retirado de relaciones con humanos, Acnología no lo ha hecho.',"+
                "'masculino', 'Fairy Tail',"+
				"'acnologiaft')");
		
		database.execSQL("insert into Ficha values(null ,"+
				"'Mystogan','es un ex-miembro del gremio Fairy Tail y era uno de los magos más fuertes del mismo siendo clasificado como uno de los magos de Clase-S. Actualmente es el gobernante del reino de Edolas. Era un absoluto desconocido en el gremio, ya que en su afán de que no le viesen dormía a la gente del gremio cuando aceptaba misiones. Es la contraparte de Jellal en Edolas.',"+
                "'masculino', 'Fairy Tail',"+
				"'mystoganft')");
		
		database.execSQL("insert into Ficha values(null ,"+
				"'Igneel','Es el rey dragón de fuego y es el padre adoptivo de Natsu Dragneel. Enseñó a Natsu a escribir, hablar, e incluso cómo usar la magia conocida como (Dragon Slayer). Por alguna razón desconocida, Igneel desapareció un día sin decir nada a Natsu el 07 de julio 777 (junto con los otros dragones como Grandine y Metalicana), dejando Natsu valerse por sí mismo. Natsu desea reunirse con Igneel de nuevo.',"+
                "'masculino', 'Fairy Tail',"+
				"'igneelft')");
		
		database.execSQL("insert into Ficha values(null ,"+
				"'Juvia Loxar','es una ex-miembro del Gremio de Phantom Lord Symbol Phantom Lord, donde fue una maga Clase-S perteneciente al equipo Element 4 . Ahora ella es miembro de Fairy Tail simbolo Fairy Tail.',"+
                "'masculino', 'Fairy Tail',"+
				"'juvialoxarft')");
		
		database.execSQL("insert into Ficha values(null ,"+
				"'Lisanna Strauss','es la hermana menor de Elfman y Mirajane, y una amiga de la infancia de Natsu Dragneel.',"+
                "'femenino', 'Fairy Tail',"+
				"'lisannastraussft')");
		
		Log.e("insertando datos", "datos no han acabado de insertar");
		
		database.close();
		return true;
	}//fin llenar datos
	
	//buscar si la tabla ya esta poblada
	public boolean determinarTablaFicha(){
		
		int total;
		database = this.getWritableDatabase();
		
		String consulta ="select count(*) from Ficha";
		Cursor c = database.rawQuery(consulta, null); 
		
		c.moveToFirst();
		total = c.getInt(0);
		
		c.close();
		database.close();
		
		if(total <= 0){
			return false;
		}else{
			return true;
		}
	}//fin determinar tabla ficha
	
	public ArrayList<Ficha> mostrarDatostablaFicha(){
		
		ArrayList<Ficha> fichatemp = new ArrayList<Ficha>();
		
		database = this.getWritableDatabase();
		
		//String consulta = "select * from Ficha";
		String consulta = "select Ficha.id_ficha,"+
						  " ficha.nombre,"+
						  " ficha.descripcion,"+
						  " ficha.genero,"+
						  " ficha.anime,"+
						  " ficha.foto"+
						  " from FichaUsuario,Ficha where FichaUsuario.id_usuario = "+IDusuario+
						  " and ficha.id_ficha = fichausuario.id_ficha";///uno por defecto pero toca cambiar eso
		Cursor c = database.rawQuery(consulta, null);
		
		if(c.moveToFirst()){
			
			do {
		       //  Log.e("miembros fairy tail", " "+c.getString(0)+ " "+c.getString(1));
		         Ficha fich = new Ficha();
		         fich.setIdficha( Integer.parseInt(c.getString(0)));
		         fich.setNombrepersonaje(c.getString(1));
		         fich.setDescripcionpersonaje(c.getString(2));
		         fich.setGeneropersonaje(c.getString(3));
		         fich.setAnime(c.getString(4));
		         fich.setFoto(c.getString(5));
		         
		         fichatemp.add(fich);
		     } while(c.moveToNext());
		}
		c.close();
		database.close();
		
		return fichatemp;
	}//fin mostrar datos tabla ficha

	//método para traer el total de la base de datos
	public int getTotalFichas(){
		int total = 0;
		database = this.getWritableDatabase(); ///se abre la conexión a la base de datos
		String consulta = "select count(*) as total from Ficha";
		
		Cursor c = database.rawQuery(consulta, null);
		
		c.moveToFirst();
		total = c.getInt(0);		
		c.close();
		database.close();
		return total;
	}	
	
	///metotod par atraer la ficha indicada
	public Ficha getFicha(int idficha){
		
		Ficha ft = new Ficha();
		database = this.getWritableDatabase();
				
		String consulta = "select * from Ficha where id_ficha = "+idficha;
		
		Cursor c = database.rawQuery(consulta, null);
		
		if(c.moveToFirst()){
			
			ft.setIdficha(c.getInt(0));
			ft.setNombrepersonaje(c.getString(1));
			ft.setDescripcionpersonaje(c.getString(2));
			ft.setGeneropersonaje(c.getString(3));
			ft.setAnime(c.getString(4));
			ft.setFoto(c.getString(5));
		}
		
		c.close();
		database.close();			
		return ft;
	}//fin getficha
	
	//método para registrar las fichas obtenidas del usuario
	public void insetarFichasObtenidas(int idficha){
		int total;
		
		//verifico si ya esta la fichar registrada
		database = this.getWritableDatabase();
		String query = "select count(*) as total from FichaUsuario where id_ficha = "+idficha+" and id_usuario = "+IDusuario;
		Cursor c = database.rawQuery(query, null);
		c.moveToFirst();
		total = c.getInt(0);
		if(total == 0){
			//registro una nueva entrada
			String insertquery = "insert into FichaUsuario values(null,1,"+idficha+","+IDusuario+",0,0)";
			database.execSQL(insertquery);
		}else{
			//actualizo la ficha
			String updatequery = "update FichaUsuario set cantidad = cantidad+1"+
								 " where id_ficha = "+idficha+
								 " and id_usuario = "+IDusuario;
			database.execSQL(updatequery);
		}
		
		c.close();
		database.close();
	}//fin registrar fichas
	

}//fin clase

/*
 * insert into ficha values(null ,"ficha01","esta es la fecha 01 desde la base de datos ",
                          "nombrefotoficha014")
 * 
 * 
 * 
 * 
 * insert into usuarioalbum values(null, "pepito perez","pepito","123456", "123456")
 * 
 * 
 * 
 * insert into FichaUsuario values(null,1,2,3)
 * */
