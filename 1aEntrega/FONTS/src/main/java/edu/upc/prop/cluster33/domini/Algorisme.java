package edu.upc.prop.cluster33.domini;

public abstract class Algorisme {

    public abstract char[][] generarLayout(Frequencies frequenciesObject, int columnes, int files);
    public abstract String getNom();
    // fica tots els valors de l'algorisme a 0 per a rebre noves dades
    public abstract void init();

}
