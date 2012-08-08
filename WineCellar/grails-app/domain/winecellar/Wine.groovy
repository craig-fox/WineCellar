package winecellar

class Wine {

    static constraints = {
		style inList:["Still", "Sparkling"]
    }
	
	String name
	Varietal varietal
	String style
	Winery winery
	
	String toString(){
		name
	}
}
