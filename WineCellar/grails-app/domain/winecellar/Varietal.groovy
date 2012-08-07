package winecellar

class Varietal {

    static constraints = {
		name maxSize: 50
		color inList:["White", "Red", "Pink"]
		maturity: range: 0..6
		lifespan range: 1..10
    }
	
	String name
	String color
	int maturity
	int lifespan
	
	String toString(){
		name
	}
}
