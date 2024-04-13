package edu.upc.prop.cluster33.domini;

import java.util.*;


public class BranchAndBoundEager extends Algorisme {

    private int[][] matriuDistancies;
    private int[][] matriuProximitat;

    //retorna el nom de l'algorisme
    public String getNom(){
        return "Branch and Bound Eager";
    }
    // inicialitza les matrius a 0
    public  void init(){
        matriuDistancies = new int[0][0];
        matriuProximitat = new int[0][0];
    }

    @Override
    //retorna el layout optimitzat
    public char[][] generarLayout(Frequencies frequencies, int columnes, int files) {
        final int midaMatriuCritica = 21;

        //inicialitzem
        Integer[] caractersPocFrequents = inicialitzarMatriuProximitat(frequencies);
        Integer[] teclesLlunyanes = inicialitzarMatriuDistancies(columnes,files);
        PriorityQueue<SolucioParcial> priorityQueue = new PriorityQueue<>();

        //solucio inicial
        ArrayList<Integer> solucioInicial = new ArrayList<>(matriuProximitat.length);
        for (int i = 0; i < matriuProximitat.length; i++) solucioInicial.add(-1); // solucio Buida ha d'estar inicialitzada a -1, no tenim cap caracter assignat
        final int nCaractersAfegir = matriuProximitat.length - midaMatriuCritica;
        if(nCaractersAfegir > 0) {
            for (int i = 0; i < nCaractersAfegir; i++)
                solucioInicial.set(teclesLlunyanes[matriuProximitat.length - i - 1], caractersPocFrequents[i]);
        }

        SolucioParcial sp = new SolucioParcial(solucioInicial); // creem la solucio inicial amb 0 elements, o els elements necessaris per a puguer compllir amb l'eficiencia i ficats a les posicions mes llunyanes
        priorityQueue.add(sp);

        boolean solucioTrobada = false;
        while(!solucioTrobada){
            //agafem el primer element i eliminem la resta
            sp = priorityQueue.poll();
            priorityQueue.clear();

            if(sp == null) break;
            ArrayList<Integer> charsDisponibles = sp.getCharsDisponibles();

            for(Integer character : charsDisponibles){
                SolucioParcial sp2 = sp.creaSolucioParcialAmb(character);
                if (sp2.solucionat()){
                    solucioTrobada = true;
                    sp = sp2;
                    break;
                }
                sp2.calcularCotaGilmoreLawler();
                priorityQueue.add(sp2);
            }

        }

        if (sp == null) return new char[0][0]; // aixo no hauria de passar mai, pero retorna un layout buit
        SolucioParcial solucioFinal = sp;
        return creaLayoutAmbSolucio(solucioFinal, columnes, files, frequencies);
    }

    //omple la matriu de distancies, agafa el nombre de files i el de columnes, i fa la distancia euclidiana entre cada posicio, retorna aun array amb els indexs de les tecles ordenades
    private Integer[] inicialitzarMatriuDistancies(int columnes,int files){
        int nElements = columnes * files;
        int[][] matriuDist = new int[nElements][nElements];
        int[] teclesLlunyanes = new int[matriuProximitat.length];

        for (int i = 0; i < nElements; i++) {
            int xi = i / columnes;
            int yi = i % columnes;

            for (int j = 0; j < nElements; j++) {
                int xj = j / columnes;
                int yj = j % columnes;

                int dx = xj - xi;
                int dy = yj - yi;
                int dist = (int) Math.sqrt(dx * dx + dy * dy)*100;
                matriuDist[i][j] = dist;
                if(i < matriuProximitat.length && j < matriuProximitat.length) {
                    teclesLlunyanes[i] += dist;
                    teclesLlunyanes[j] += dist;
                }

            }
        }

        matriuDistancies = matriuDist;
        return indexsArrayOrdenat(teclesLlunyanes);
    }
    //omple la matriu proximiat amb les frequencies de les paraules, retorna aun array amb els indexs de les tecles paraules
    private Integer[] inicialitzarMatriuProximitat(Frequencies frequencies){
        TreeMap<String,Integer> freq = frequencies.getLlistaFrequencies();
        Alfabet alfabet = frequencies.getAlfabet();
        int alfabetSize = alfabet.getMida();
        int[] caractersPocFrequents = new int[alfabetSize];
        int[][] matriuProx = new int[alfabetSize][alfabetSize];

        Random random = new Random(4);
        // Ho fiquem tot a un valor random entre [0,1,2,3,4], aquest valor es insignificant, pero l'eficiencia es dispara
        for (int i = 0; i < alfabetSize; i++) {
            for (int j = 0; j < alfabetSize; j++) {
                matriuProx[i][j] = random.nextInt(15);
            }
        }


        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            String paraula = entry.getKey();
            int paraulaFreq = entry.getValue();

            // per cada parell de caracters en la paraula sumem la frequencia
            for (int i = 0; i < paraula.length() - 1; i++) {
                char caracterEsquerra = paraula.charAt(i);
                char caracterDreta = paraula.charAt(i + 1);

                int indexEsquerra = alfabet.getIndex(caracterEsquerra);
                int indexDreta = alfabet.getIndex(caracterDreta);


                if (indexEsquerra >= 0 && indexDreta >= 0) {
                    matriuProx[indexEsquerra][indexDreta] += paraulaFreq*10;
                    matriuProx[indexDreta][indexEsquerra] += paraulaFreq*10;
                    caractersPocFrequents[indexEsquerra] += paraulaFreq;
                    caractersPocFrequents[indexDreta] += paraulaFreq;
                }
            }
        }
        matriuProximitat = matriuProx;
        return indexsArrayOrdenat(caractersPocFrequents);
    }
    //retorna un nou array amb indexos als elements originals ordenats com a elements,
    public Integer[] indexsArrayOrdenat(int[] array) {
        Integer[] indexes = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            indexes[i] = i;
        }

        Arrays.sort(indexes, new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return Integer.compare(array[i1], array[i2]);
            }
        });

        return indexes;
    }
    // omple el layout amb la solucio trobada
    private char[][] creaLayoutAmbSolucio(SolucioParcial solucioFinal, int columnes, int files, Frequencies frequencies) {
        Alfabet alfabet = frequencies.getAlfabet();
        ArrayList<Integer> solucio = solucioFinal.getSolucio();

        char[][] layout = new char[files][columnes];
        for (int tecla = 0; tecla < solucio.size(); tecla++){
            int caracterIndex = solucio.get(tecla);
            layout[tecla/columnes][tecla%columnes] = alfabet.getCharAtIndex(caracterIndex);
        }
        return layout;
    }
    //retorna la suma de costs de les arestes entre A i la resta d'aresets del arraylist, no suma el cost de l'element caracter si es el mateix
    private int costArestes(int caracter1, int tecla1, ArrayList<Integer> sol ){
        int cost = 0;
        for (int i = 0; i < sol.size(); ++i){
            int caracter2 = sol.get(i);
            if (caracter2 != -1 && caracter1 != caracter2)
                cost += matriuProximitat[caracter1][caracter2]  * matriuDistancies[tecla1][i];
        }
        return cost;
    }

    //classe que representa una solucio parcial (un layout mig ple)
    private class SolucioParcial implements Comparable<SolucioParcial> {
        final private ArrayList<Integer> solucio; // la solucio parcial, es un arraylist on els elements son els indexs dels caracters i si la posicio esta buida un -1
        private int cotaGilmoreLawler;  // el resultat despres de calcular la cota
        private int f1; //el factor f1 de la cota de gilmore lawler, aixi no l'hem de recalcular cada vegada i el poodem mantenir amb un simple calcul

        final private ArrayList<Integer> charsPerAssignar;  //arraylist on els elements son els caracters que queden per assignar
        final private ArrayList<Integer> teclesPerAssignar; // arraylist on els elements son les tecles  buides

        // creadora amb una solucio donada
        public SolucioParcial(ArrayList<Integer> solucio) {

            this.solucio = new ArrayList<>(solucio);
            // calculem f1
            this.f1 = calculaF1();
            this.cotaGilmoreLawler = -1; //encara no la calculem

            charsPerAssignar = new ArrayList<Integer>();
            teclesPerAssignar = new ArrayList<Integer>();
            //omplim charsPerAssignar
            for (int character = 0; character < solucio.size(); ++character) {
                if (!solucio.contains(character)) {
                    charsPerAssignar.add(character);
                }
            }

            //omplim teclesPerAssignar
            for (int j = 0; j < solucio.size(); j++) {
                if (solucio.get(j) == -1) {
                    teclesPerAssignar.add(j);
                }
            }
        }

        //creadora amb tots els parametres determinats
        private SolucioParcial(ArrayList<Integer> solucio, int f1, ArrayList<Integer> charsPerAssignar, ArrayList<Integer> teclesPerAssignar) {
            this.solucio = solucio;
            this.f1 = f1;
            this.cotaGilmoreLawler = -1; //encara no la calculem
            this.charsPerAssignar = charsPerAssignar;
            this.teclesPerAssignar = teclesPerAssignar;

        }

        // retorna una copia de solucio parcial amb caracter afegit a tecla a la solucio i tots els valors de solucioParcial actualitzats
        public SolucioParcial creaSolucioParcialAmb(int character) {

            ArrayList<Integer> novaSolucio = new ArrayList<>(this.solucio);
            int tecla = -1;
            for (int i = 0; i < solucio.size(); i++) {
                if (solucio.get(i) == -1) {
                    tecla = i;
                    break;
                }
            }

            novaSolucio.set(tecla, character);

            ArrayList<Integer> nousCharsPerAssignar = new ArrayList<>(this.charsPerAssignar);
            ArrayList<Integer> novesTeclesPerAssignar = new ArrayList<>(this.teclesPerAssignar);

            nousCharsPerAssignar.remove(Integer.valueOf(character));
            novesTeclesPerAssignar.remove(Integer.valueOf(tecla));

            int nouF1 = f1 + costArestes(character, tecla, novaSolucio);

            return new SolucioParcial(novaSolucio, nouF1, nousCharsPerAssignar, novesTeclesPerAssignar);


        }

        // retorna els caracters pendents d'assignar
        public ArrayList<Integer> getCharsDisponibles() {
            return this.charsPerAssignar;
        }

        //retorna true si es una solucio que nomes li falta una assignacio, a mes fa aquesta assignacio i retorna true, sino retorna false
        public boolean solucionat() {
            if (charsPerAssignar.size() <= 1 ) {
                int tecla = -1;
                for (int i = 0; i < solucio.size(); i++) {
                    if (solucio.get(i) == -1) {
                        tecla = i;
                        break;
                    }
                }
                solucio.set(tecla, charsPerAssignar.get(0));
                return true;
            }
            return false;
        }

        //getter de la solucio
        public ArrayList<Integer> getSolucio(){
            return solucio;
        }

        // funcio que indica l'ordre en la priority queue
        @Override
        public int compareTo(SolucioParcial other) {
            return Integer.compare(this.cotaGilmoreLawler, other.cotaGilmoreLawler);
        }

        //calcula el factor F1
        private int calculaF1() {
            ArrayList<Integer> list = new ArrayList<>(solucio);
            int cost = 0;
            for (int i = 0; i < solucio.size(); ++i) {
                int caracter = solucio.get(i);
                if (caracter != -1) {
                    list.set(i, -1);
                    cost += costArestes(caracter, i, list);
                }
            }
            return cost;
        }

        // calcula la cota GL i la guarda a l'atribut cotaGilmoreLawler
        public void calcularCotaGilmoreLawler() {
            int[][] c1 = computarC1();
            int[][] c2 = computarC2();
            int[][] matriuC1C2 = suma(c1, c2);

            HungarianAlgorithm hungarianAlgorithm = new HungarianAlgorithm();
            boolean[][] matSolucioOptima = hungarianAlgorithm.resoldre(matriuC1C2);
            int f2f3 = calculaF2F3(matriuC1C2, matSolucioOptima);

            cotaGilmoreLawler = f1 + f2f3;
        }

        // retorna la matriu C1 utilitzada per l'algorisme que troba la cota GilmoreLawler
        private int[][] computarC1() {
            int m = charsPerAssignar.size();
            int[][] matriuC1 = new int[m][m];
            for (int indexChar = 0; indexChar < m; indexChar++) {
                for (int indexTecla = 0; indexTecla < m; indexTecla++) {
                    int caracter = charsPerAssignar.get(indexChar);
                    int tecla = teclesPerAssignar.get(indexTecla);
                    matriuC1[indexChar][indexTecla] = costArestes(caracter, tecla, solucio);
                }
            }
            return matriuC1;
        }

        // retorna la matriu C2 utilitzada per l'algorisme que troba la cota GilmoreLawler
        private int[][] computarC2() {
            int m = charsPerAssignar.size();
            int[][] matriuC2 = new int[m][m];
            for (int indexChar = 0; indexChar < m; ++indexChar) {
                for (int indexTecla = 0; indexTecla < m; ++indexTecla) {
                    int caracter = charsPerAssignar.get(indexChar);
                    int tecla = teclesPerAssignar.get(indexTecla);
                    int[] vectorT = vectorT(caracter);
                    int[] vectorD = vectorD(tecla);
                    Arrays.sort(vectorT);
                    Arrays.sort(vectorD);
                    matriuC2[indexChar][indexTecla] = dotDReversed(vectorT, vectorD);
                }
            }
            return matriuC2;
        }

        // retorna el vector T donat un caracter, i ho compara amb els caracters encara no assignats, s'utilitza per a calcular C2
        private int[] vectorT(int caracter) {
            int[] t = new int[charsPerAssignar.size() - 1];
            int i = 0;
            for (int charPerAssignar : charsPerAssignar) {
                if (charPerAssignar != caracter) {
                    t[i] = matriuProximitat[charPerAssignar][caracter];
                    ++i;
                }
            }
            return t;
        }

        // retorna el vector D donat una tecla, i ho compara amb les tecles encara no assignades, s'utilitza per a calcular C2
        private int[] vectorD(int tecla) {
            int[] d = new int[teclesPerAssignar.size() - 1];
            int i = 0;
            for (int teclaPerAssignar : teclesPerAssignar) {
                if (teclaPerAssignar != tecla) {
                    d[i] = matriuDistancies[teclaPerAssignar][tecla];
                    ++i;
                }
            }
            return d;
        }

        // retorna el producte escalar de dos vectors, pero tenint en compte que el segon vector esta girat, es a dir, retorna dot(v1, reversed(v2))
        private int dotDReversed(int[] vectorT, int[] vectorD) {
            int m = vectorT.length;
            int resultat = 0;
            for (int i = 0; i < m; ++i) {
                resultat += vectorT[i] * vectorD[m - i - 1];
            }
            return resultat;
        }

        // reotrna la suma de dues matrius quadrades amb la mateixa mida
        private int[][] suma(int[][] c1, int[][] c2) {
            int m = c1.length;
            int[][] sum = new int[m][m];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    sum[i][j] = c1[i][j] + c2[i][j];
                }
            }

            return sum;
        }

        public int calculaF2F3(int[][] mat, boolean[][] solucioOptima) {
            int f2f3 = 0;
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[i].length; j++) {
                    if (solucioOptima[i][j]) {
                        f2f3 += mat[i][j];
                    }
                }
            }
            return f2f3;
        }

        private class HungarianAlgorithm {
            //resol el problema LAP, retorna l'assignacio optima com una matriu de boleans amb true a les posicions optimes
            public boolean[][] resoldre(int[][] mat) {
                int[][] matriu = deepCopyMatriu(mat);
                int mida = matriu.length;
                restaMinimPerFila(matriu);
                restaMinimPerColumna(matriu);
                int[] linies = minLiniesCobrirZeros(matriu);
                while (linies.length < mida) {
                    int minNoCobert = minNoCobert(matriu, linies);
                    sumaCoberts(matriu, linies, minNoCobert);
                    int minim = minimMat(matriu);
                    restaMatriu(matriu, minim);
                    linies = minLiniesCobrirZeros(matriu);
                }

                return trobaAssignacioZeroFilaColumna(matriu); // nomes te 0 a les posicions que volem
            }

            //Retorna una copia de la matriu
            private int[][] deepCopyMatriu(int[][] original) {
                int[][] copy = new int[original.length][];
                for (int i = 0; i < original.length; i++) {
                    copy[i] = Arrays.copyOf(original[i], original[i].length); // Copy each row
                }
                return copy;
            }

            // per cada fila resta el minim de la fila a la resta de la fila
            private void restaMinimPerFila(int[][] matriu) {

                for (int i = 0; i < matriu.length; i++) {
                    int minim = matriu[i][0]; // trobem el minim
                    for (int j = 0; j < matriu[i].length; j++) {
                        if (matriu[i][j] < minim) {
                            minim = matriu[i][j];
                        }
                    }
                    for (int j = 0; j < matriu[i].length; j++)  // restem el minim a cada element de la fila
                        matriu[i][j] = matriu[i][j] - minim;

                }
            }

            // per cada columna resta el minim de cada columna a la resta de la columna
            private void restaMinimPerColumna(int[][] matriu) {
                for (int j = 0; j < matriu.length; j++) {
                    int minim = matriu[0][j];
                    for (int[] ints : matriu) { // trobem el minim
                        if (ints[j] < minim)
                            minim = ints[j];
                    }
                    for (int i = 0; i < matriu.length; i++)  //restem el minim a cada element de la columna
                        matriu[i][j] -= minim;

                }
            }

            // retorna el nombre minim no covert per les linies
            private int minNoCobert(int[][] mat, int[] linies) {
                boolean[] isFilaCoberta = new boolean[mat.length];
                boolean[] isColumnaCoberta = new boolean[mat[0].length];

                for (int linia : linies) { //marquem les files i columnes
                    if (linia < mat.length) {
                        isFilaCoberta[linia] = true; //es una fila
                    } else if (linia < 2 * mat.length) {
                        isColumnaCoberta[linia - mat.length] = true; // es una columna
                    }
                }

                int min = Integer.MAX_VALUE;
                for (int i = 0; i < mat.length; i++) { //mirem nomes els que no estan coberts
                    for (int j = 0; j < mat[i].length; j++) {
                        if (!isFilaCoberta[i] && !isColumnaCoberta[j]) {
                            if (mat[i][j] < min) min = mat[i][j];
                        }
                    }
                }
                return min;
            }

            // suma el nombre a cada nombre covert
            private void sumaCoberts(int[][] mat, int[] linies, int minNoCobert) {
                boolean[] isFilaCoberta = new boolean[mat.length];
                boolean[] isColumnaCoberta = new boolean[mat[0].length];

                for (int linia : linies) { //marquem les files i columnes
                    if (linia < mat.length) {
                        isFilaCoberta[linia] = true; //es una fila
                    } else if (linia < 2 * mat.length) {
                        isColumnaCoberta[linia - mat.length] = true; // es una columna
                    }
                }


                for (int i = 0; i < mat.length; i++) { //sumem nomes els elements que estan coberts
                    for (int j = 0; j < mat[i].length; j++) {
                        if (isFilaCoberta[i] || isColumnaCoberta[j]) {
                            mat[i][j] += minNoCobert;
                            if (isFilaCoberta[i] && isColumnaCoberta[j]) { //pot ser que estigui cobert deus vegades, per tant hem de sumar el valor dues vegades
                                mat[i][j] += minNoCobert;
                            }
                        }
                    }
                }
            }

            //retorna l'element minim de la matriu
            private int minimMat(int[][] mat) {

                int min = Integer.MAX_VALUE;
                for (int i = 0; i < mat.length; i++)
                    for (int j = 0; j < mat[i].length; j++)
                        if (mat[i][j] < min) min = mat[i][j];

                return min;
            }

            //li resta val a cada element de la matriu
            private void restaMatriu(int[][] mat, int val) {
                for (int i = 0; i < mat.length; i++) {
                    for (int j = 0; j < mat[i].length; j++)
                        mat[i][j] -= val;
                }
            }

            // Retorna el minim nombre de linies necessaries per a cobrir tots els zeros
            private int[] minLiniesCobrirZeros(int[][] mat) {
                boolean[][] assignacioFiles = new boolean[mat.length][mat[0].length];
                boolean[] filesSeleccionades = assignacioCompletaDeFiles(mat, assignacioFiles);
                boolean[] filesMarcades = filesMarcadesDeSeleccionades(filesSeleccionades);
                boolean[] columnesMarcades = new boolean[mat[0].length];

                while (!totsZerosCoberts(mat, filesMarcades, columnesMarcades)) {
                    marcarColumnesAmb0AFilaMarcada(mat, filesMarcades, columnesMarcades);
                    marcarFilesAmbAssignacioAColumnaMarcada(mat, filesMarcades, columnesMarcades, assignacioFiles);
                }
                return liniesSolucio(filesMarcades, columnesMarcades);
            }

            // retorna true si les files no marcades i les columnes marcades cobreixen tots els 0s
            public boolean totsZerosCoberts(int[][] mat, boolean[] filesMarcades, boolean[] columnesMarcades) {
                for (int i = 0; i < mat.length; i++) {
                    for (int j = 0; j < mat[i].length; j++) {
                        if (mat[i][j] == 0 && filesMarcades[i] && !columnesMarcades[j]) {
                            return false;
                        }
                    }
                }
                return true;
            }

            // retorna una matriu boleana on pren per valor true els elements d'una solucio
            private boolean[][] trobaAssignacioZeroFilaColumna(int[][] mat) {
                boolean[][] selectedMat = new boolean[mat.length][mat[0].length];
                return selectedMat;
            }
            /*
            // funcio auxilar de trobaAssignacioZeroFilaColumna
            private boolean trobarAssignacioZeros(int[][] mat, boolean[][] slectedMat, int currentRow) {
                if (currentRow == mat.length) {
                    return true;
                }
                for (int col = 0; col < mat[currentRow].length; col++) {
                    if (mat[currentRow][col] == 0 && !isColumnSelected(slectedMat, mat, col)) {
                        slectedMat[currentRow][col] = true;
                        if (trobarAssignacioZeros(mat, slectedMat, currentRow + 1)) {
                            return true;
                        }
                        slectedMat[currentRow][col] = false;
                    }
                }
                return false;
            }
            */
            //retorna true si hi ha un 0 marcat en aquella columna, funcio auxiliar de trobarAssignacioZeros
            private boolean isColumnSelected(boolean[][] selectedMat, int[][] mat, int col) {
                for (int i = 0; i < mat.length; i++) {
                    if (selectedMat[i][col] && mat[i][col] == 0) {
                        return true;
                    }
                }
                return false;
            }

            // retorna les files no marcades, s'utilitza per a invertir les files seleccionades obtenint les marcades, funcio auxiliar de minLiniesCobrirZeros
            private boolean[] filesMarcadesDeSeleccionades(boolean[] filesSeleccionades) {
                boolean[] filesMarcades = new boolean[filesSeleccionades.length];
                for (int i = 0; i < filesSeleccionades.length; ++i)
                    filesMarcades[i] = !filesSeleccionades[i];
                return filesMarcades;
            }

            // marca les columnes no marcades que tenen un 0 a les files marcades, funcio auxiliar de minLiniesCobrirZeros
            private boolean marcarColumnesAmb0AFilaMarcada(int[][] mat, boolean[] filesMarcades, boolean[] columnesMarcades) {
                boolean haCanviat = false;
                for (int i = 0; i < mat.length; i++) {
                    if (filesMarcades[i]) {
                        for (int j = 0; j < mat[0].length; j++) {
                            if (mat[i][j] == 0 && !columnesMarcades[j]) {
                                columnesMarcades[j] = true;
                                haCanviat = true;
                            }
                        }
                    }
                }
                return haCanviat;
            }

            // marca les files no marcades que tenen un 0 a les columnes marcades
            private boolean marcarFilesAmbAssignacioAColumnaMarcada(int[][] mat, boolean[] filesMarcades, boolean[] columnesMarcades, boolean[][] assignacioFiles) {
                boolean haCanviat = false;
                for (int j = 0; j < mat[0].length; j++) {
                    if (columnesMarcades[j]) {
                        for (int i = 0; i < mat.length; i++) {
                            if (assignacioFiles[i][j] && !filesMarcades[i]) {
                                filesMarcades[i] = true;
                                haCanviat = true;
                            }
                        }
                    }
                }

                return haCanviat;
            }

            //transforma de files a columnes a linies tenint que n < solucio.length llavors es una fila, si n > solucio.length llavors n - solucio.length es una columna
            private int[] liniesSolucio(boolean[] filesMarcades, boolean[] columnesMarcades) {
                int mida = filesMarcades.length;
                int[] linies = new int[mida * 2];

                int indexL = 0;
                for (int i = 0; i < mida; i++) {
                    if (!filesMarcades[i]) {
                        linies[indexL] = i;
                        indexL++;
                    }
                }

                for (int j = 0; j < mida; j++) {
                    if (columnesMarcades[j]) {
                        linies[indexL] = j + mida;
                        indexL++;
                    }
                }

                return Arrays.copyOf(linies, indexL);

            }

            // retorna una assignacio completa de files
            private boolean[] assignacioCompletaDeFiles(int[][] mat, boolean[][] assignacioFiles) {
                boolean[] filesSeleccionades = new boolean[mat.length];
                boolean[][] assignacioFiles0 = new boolean[mat.length][mat[0].length];
                boolean[] maxSolucio = new boolean[mat.length];
                boolean[] colSeleccionades = new boolean[mat[0].length];
                int[] maxTrobat = new int[1]; // maxTrobat[0] = 0;
                trobarMaxFilesZero(mat, colSeleccionades, filesSeleccionades, maxSolucio, 0, 0, maxTrobat, assignacioFiles0, assignacioFiles);

                return maxSolucio;
            }

            // troba la maxima assignacio de files tal que es el maxim nombre d'elles que cobreixen tots els 0
            private void trobarMaxFilesZero(int[][] mat, boolean[] colSeleccionades, boolean[] filesSeleccionades, boolean[] maxSolucio, int fila, int nFilesSeleccionades, int[] maxTrobat, boolean[][] assignacioFiles, boolean[][] assignacioFilesFinal) {
                int n = mat.length;
                if (fila == n) {
                    if (maxTrobat[0] < nFilesSeleccionades) {
                        maxTrobat[0] = nFilesSeleccionades;
                        for (int i = 0; i < assignacioFiles.length; i++) {
                            assignacioFilesFinal[i] = Arrays.copyOf(assignacioFiles[i], assignacioFiles[i].length);
                        }
                        System.arraycopy(filesSeleccionades, 0, maxSolucio, 0, filesSeleccionades.length);
                    }
                    return;
                }
                if (n - fila + nFilesSeleccionades < maxTrobat[0] || maxTrobat[0] == n) {
                    return;
                }

                for (int col = 0; col < mat[fila].length; col++) {
                    if (mat[fila][col] == 0 && !colSeleccionades[col]) {
                        colSeleccionades[col] = true;
                        filesSeleccionades[fila] = true;
                        assignacioFiles[fila][col] = true;
                        trobarMaxFilesZero(mat, colSeleccionades, filesSeleccionades, maxSolucio, fila + 1, nFilesSeleccionades + 1, maxTrobat, assignacioFiles, assignacioFilesFinal);
                        colSeleccionades[col] = false;
                        filesSeleccionades[fila] = false;
                        assignacioFiles[fila][col] = false;

                        if (maxTrobat[0] == n) break;
                    }
                }
                trobarMaxFilesZero(mat, colSeleccionades, filesSeleccionades, maxSolucio, fila + 1, nFilesSeleccionades, maxTrobat, assignacioFiles, assignacioFilesFinal);
            }
        }
    }
}
