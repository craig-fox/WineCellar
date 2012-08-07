package winecellar

class Winery {

    static constraints = {
    }
	
	static hasMany = [varietals: Varietal]
	
	String name
	String location
	
}
