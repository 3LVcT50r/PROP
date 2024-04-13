package edu.upc.prop.cluster33.domini;

public class TextPredefinit extends Text{
    public TextPredefinit(String titol) {
        super(titol);
    }

    public TextPredefinit(String titol, String contingut) {
        super(titol, contingut);
    }

    public TextPredefinit(Text tx) {
        super(tx);
    }
}
