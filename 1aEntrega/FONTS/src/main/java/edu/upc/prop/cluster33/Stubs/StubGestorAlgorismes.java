package edu.upc.prop.cluster33.Stubs;

import edu.upc.prop.cluster33.domini.Algorisme;
import edu.upc.prop.cluster33.domini.BranchAndBoundEager;
import edu.upc.prop.cluster33.excepcions.ExcepcioIdNoValid;

import java.util.HashMap;

public class StubGestorAlgorismes {
    HashMap<Integer, Algorisme> algorismes;

    public StubGestorAlgorismes() {
        algorismes = new HashMap<>();
        BranchAndBoundEager alg1 = new BranchAndBoundEager();
        BranchAndBoundEager alg2 = new BranchAndBoundEager();
        algorismes.put(1, alg1);
        algorismes.put(2, alg2);
    }

    public HashMap<Integer, Algorisme> getAlgorismes() {
        return algorismes;
    }

    public Algorisme getAlgorisme(int idAlgoritme) throws ExcepcioIdNoValid {
        if (!algorismes.containsKey(idAlgoritme)) throw new ExcepcioIdNoValid();
        return algorismes.get(idAlgoritme);
    }
}
