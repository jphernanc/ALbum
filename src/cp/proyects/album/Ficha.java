package cp.proyects.album;

public class Ficha {

	int idficha;
	String nombrepersonaje;
	String descripcionpersonaje;
	String generopersonaje;
	String anime;
	String foto;
	boolean favoritos = false;
	int totalfichascambiar = 0;
	
	public int getTotalfichascambiar() {
		return totalfichascambiar;
	}
	public void setTotalfichascambiar(int totalfichascambiar) {
		this.totalfichascambiar = totalfichascambiar;
	}
	
	
	public boolean isFavoritos() {
		return favoritos;
	}
	public void setFavoritos(boolean favoritos) {
		this.favoritos = favoritos;
	}
	public int getIdficha() {
		return idficha;
	}
	public void setIdficha(int idficha) {
		this.idficha = idficha;
	}
	public String getNombrepersonaje() {
		return nombrepersonaje;
	}
	public void setNombrepersonaje(String nombrepersonaje) {
		this.nombrepersonaje = nombrepersonaje;
	}
	public String getDescripcionpersonaje() {
		return descripcionpersonaje;
	}
	public void setDescripcionpersonaje(String descripcionpersonaje) {
		this.descripcionpersonaje = descripcionpersonaje;
	}
	public String getGeneropersonaje() {
		return generopersonaje;
	}
	public void setGeneropersonaje(String generopersonaje) {
		this.generopersonaje = generopersonaje;
	}
	public String getAnime() {
		return anime;
	}
	public void setAnime(String anime) {
		this.anime = anime;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
}
