package application;

/**
 * Berechnet das Formelrad
 * 
 * @author Tim Dubath und Yannick Ruck
 * @version 13.11.2019
 */
public class Calculator {
	private double leistung;
	private double spannung;
	private double strom;
	private double widerstand;

	public Calculator(double leistung, double spannung, double strom, double widerstand) {
		super();
		this.leistung = leistung;
		this.spannung = spannung;
		this.strom = strom;
		this.widerstand = widerstand;
	}

	public double getLeistung() {
		return leistung;
	}

	public double getSpannung() {
		return spannung;
	}

	public double getStrom() {
		return strom;
	}

	public double getWiderstand() {
		return widerstand;
	}

	@Override
	public String toString() {
		return "Calculator [leistung=" + leistung + ", spannung=" + spannung + ", strom=" + strom + ", widerstand="
				+ widerstand + "]";
	}

	public void calculate() {
		/*
		 * Hier auf Grund der vorhanden Werte entscheiden welche Methode unten
		 * aufgerufen werden muss.
		 */
		if (spannung != 0 && strom != 0) {

			leistung = pAusUundI(spannung, strom);
			widerstand = rAusUundI(spannung, strom);
		}
		
		else if(spannung != 0 && widerstand != 0) {

			leistung = pAusUx2durchR(spannung, widerstand);
			

		}
		
		else if(widerstand != 0 && strom != 0) {

			leistung = pAusRundIx2(widerstand, strom);
			spannung = uAusRundI(widerstand, strom);
		}

		else if(leistung != 0 && strom != 0) {

			spannung = uAusPundI(leistung, strom);
		}

		else if(leistung != 0 && widerstand != 0) {

			spannung = uAusPundRwurzel(leistung, widerstand);
		}
	}

	/*
	 * Hier die Methoden mit den Formlen hinzuf�gen
	 */
	public double pAusUundI(double spannung, double strom) {
		System.out.println("Berechnung von der Leistung aus der Spannung und der Stromstärke.");
		return spannung * strom;
	}
	
	public double pAusUx2durchR(double spannung, double widerstand) {
		System.out.println("Berechnung von der Leistung aus der Spannung und dem Widerstand.");
		return (spannung * spannung) / widerstand;
	}
	
	public double pAusRundIx2(double widerstand, double strom) {
		System.out.println("Berechnung von der Leistung aus dem Widerstand und dem Strom.");
		return widerstand * (strom * strom);
	}

	public double uAusRundI(double widerstand, double strom) {
		System.out.println("Berechnung von der Spannung aus dem Widerstand und der Stromstärke.");
		return widerstand * strom;
	}

	public double uAusPundI(double leistung, double strom) {
		System.out.println("Berechnung von der Spannung aus der Leistung und der Stromstärke.");
		return leistung / strom;
	}

	public double uAusPundRwurzel(double leistung, double widerstand) {
		System.out.println("Berechnung von der Spannung aus der Leistung und dem Widerstand.");
		return Math.sqrt(leistung * widerstand);
	}
	
	public double rAusUundI(double spannung, double widerstand) {
		System.out.println("Berechnung von dem Widerstand aus der Spannung und der Stromstärke.");
		return spannung / widerstand;
	}
}
