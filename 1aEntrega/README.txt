*******************************************************************
README PROJECTE PROP GRUP 33 2023T
*******************************************************************



---Com Compilar---

Dins de la carpeta FONTS trobem un make.sh i un make.bat.
Si s'executen amb bash ./make.sh o ./make.bat es generen els fitxers .class en la carpeta EXE, si no tens drets d'execucio executa chmod +x make.sh o chmod +x make.bat abans.
Sortiran un parell de linies de Warning, pero es pot ignorar.

--Com executar---

Els executables que executen els drivers Principals es troben dins de directoris de la carpeta EXE:

Primer de tot dona permis als executables amb:
    chmod +x */*.sh

I despres executa el driver que vulguis entrant al directori (cd) amb:
    DriverControladorCapaDomini: ./executarDriverControladorCapaDomini.sh
    DriverControladorCapaDominiOpcionsUsuari: ./executarDriverControladorCapaDominiOpcionsUsuari

    A mes dins de cada directori es troben els jocs de prova

--Com utilitzar DriverControladorCapaDomini---
 Un cop executat el driver, l’usuari pot triar d’una llista amb les diferents funcionalitats del driver. Les funcionalitats que treuen informacio del sistema, en aquesta primera entrega escriuen a la terminal. Funcions dins del driver:
1.Test Get Tots Els Teclats: El sistema imprimeix per terminal tots els teclats del usuari registrat

2.Test Get Teclat: El sistema mostra tots els teclats, l’usuari selecciona per id el que vol visualitzar, i el sistema presenta el nom, algorisme de creacio, la data de creacio, l’alfabet i el layout. 

3.Test Get Algorisme Teclat: El sistema mostra tots els teclats, l’usuari selecciona per id del que vol consultar l’algorisme

4.Test Get Nom Teclat: El sistema mostra tots els teclats, l’usuari selecciona per id del que vol consultar el nom

5.Test Esborrar Teclat: El sistema mostra tots els teclats, l’usuari selecciona per id el que vol eliminar

6.Test Get Algorismes: El sistema mostra tots els algorismes de creacio disponibles al sistema

7.Test Get Textos Publics: El sistema mostra tots els textos publics registrats al sistema

8.Test Get Textos Predefinits: El sistema mostra tots els textos predefinits registrats al sistema

9.Test Crear Teclat Input Manual: El sistema demana el id del algorisme de creacio que es vol usar, el nom del teclat i l’input, que s’introdueix en el format
paraula1-frequencia1
paraula2-frequencia2
paraula3-frequencia3;

10.Test Crear Teclat Fitxer Text Frequencies: El sistema demana el id del algorisme de creacio que es vol usar, el nom del teclat i l’input s’introdueix com un path. El path ha de ser relatiu desde el executable del driver (Si es desde IntelliJ, el path ha de començar desde src/… El fitxer del path ha de contenir les parelles de paraules amb frequencies en el mateix format que la funcionalitat anterior

11.Test Crear Teclat Fitxer Text Usuari: El sistema demana el id del algorisme de creacio que es vol usar, el nom del teclat i l’input s’introdueix com un path. El path ha de ser relatiu desde el executable del driver (Si es desde IntelliJ, el path ha de començar desde src/… 

12.Test Crear Teclat Fitxer Text Public: El sistema demana el id del algorisme de creacio que es vol usar, el nom del teclat. El sistema mostra tots els textos publics i l’usuari introdueix el id del text public que vol usar com a input

13.Test Crear Teclat Fitxer Text Predefinit: El sistema demana el id del algorisme de creacio que es vol usar, el nom del teclat. El sistema mostra tots els textos predefinits i l’usuari introdueix el id del text predefinit que vol usar com a input

14.Test Canviar Nom Teclat: El sistema ensenya tots els teclats de l’usuari. L’usuari introdueix el id del teclat al que vol canviar el nom. Despres introdueix el nou nom que li vol donar al teclat

15.Test Canviar Algorisme Teclat:  El sistema ensenya tots els teclats de l’usuari. L’usuari introdueix el id del teclat al que vol canviar l’algorisme

16.Test Canviar Input Manual: El sistema ensenya tots els teclats de l’usuari. L’usuari introdueix el id del teclat al que vol canviar l’input. Despres l’usuari introdueix el nou input seguint el format explicat a la creacio de teclat amb input manual

17.Test Canviar Input Fitxer Frequencies: El sistema ensenya tots els teclats de l’usuari. L’usuari introdueix el id del teclat al que vol canviar l’input. Despres l’usuari introdueix el nou input seguint el format explicat a la creacio de teclat amb input fitxer frequencies

18.Test Canviar Input Fitxer Text Usuari: El sistema ensenya tots els teclats de l’usuari L’usuari introdueix el id del teclat al que vol canviar l’input. Despres l’usuari introdueix el nou input seguint el format explicat a la creacio de teclat amb input fitxer text usuari

19.Test Canviar Input Fitxer Text Public: El sistema ensenya tots els teclats de l’usuari. L’usuari introdueix el id del teclat al que vol canviar l’input. Despres l’usuari introdueix el id del text public que vol usar com a nou input

20.Test Canviar Input Fitxer Text Predefinit: El sistema ensenya tots els teclats de l’usuari. L’usuari introdueix el id del teclat al que vol canviar l’input. Despres l’usuari introdueix el id del text predefinit que vol usar com a nou input

21.Test Get Textos Publics Usuari: El sistema mostra tots els textos publics publicats per l’usuari que ha fet login

22.Test Afegir Nou Text Public: L’usuari introdueix el nom que li vol donar al text public. Despres introdueix el path del text que vol fer public. El path ha de ser relatiu desde el executable del driver (Si es desde IntelliJ, el path ha de començar desde src/...)

23.Test Eliminar Text Public: L’usuari tria el id del text public que vol eliminar. S’elimina el text public identificat pel id

24.Test Canviar Nom Text Public: L’usuari tria el id del text public al que vol canviar el nom. Despres introdueix el nou nom que vol donarli al text public

25.Test Get Text Public: L’usuari tria el id del text public que vol visualitzar. El sistema mostra el contingut del text public

26.Test Afegir Nou Text Predefinit: L’usuari introdueix el nom que li vol donar al text predefinit. Despres introdueix el path del text que vol fer predefinit. El path ha de ser relatiu desde el executable del driver (Si es desde IntelliJ, el path ha de començar desde src/...)

27.Test Eliminar Text Predefinit: L’usuari tria el id del text predefinit que vol eliminar. S’elimina el text predefinitidentificat pel id

28.Test Canviar Nom Text Predefinit: L’usuari tria el id del text predefinit al que vol canviar el nom. Despres introdueix el nou nom que vol donar-li al text predefinit

29.Test Get Text Predefinit: L’usuari tria el id del text predefinit que vol visualitzar. El sistema mostra el contingut del text predefinit

30.Per ultim, es dona la opcio de sortir del Driver

 --Com utilitzar DriverControladorCapaDominiOpcionsUsuari—
Un cop executat el driver, l’usuari pot triar d’una llista amb les diferents funcionalitats del driver. Les funcionalitats que treuen informacio del sistema, en aquesta primera entrega escriuen a la terminal. Funcions dins del driver:

1.Test Login: L’usuari introdueix el username i el password que vol usar per fer el login. El sistema valida les credencials

2.Test Registre: L’usuari introdueix el username i el password amb el que es vol registrar en el sistema. Es crea l’usuari i es fa login

3.Test Canviar Password: L’usuari introdueix el seu password antic, i despres el nou password al que vol canviar

4.Test Canviar Ma Bona: L’usuari demana al sistema canviar la seva ma bona (al ser una opcio binaria, el canvi no requeriex de input)

5.Test Logout: L’usuari informa al sistema que vol fer logout, el sistema guarda la seva informacio i el deslogueja

6.Test Eliminar Propi Usuari: L’usuari informa que vol eliminar el seu perfil. El sistema demana el username i el password per confirmar l’esborrat

7.Test Get Ma Bona: El sistema informa de la ma bona de l’usuari que ha fet login

8.Test Is Admin: El sistema informa de si l’usuari que ha fet login es admin o no

//Funcions nomes per administradors

9.Test Get Usuaris: El sistema retorna tots els usuaris registrats al sistema.

10.Test Elimina Usuari: L’administrador introdueix el username de l’usuari al que vol eliminar del sistema. El sistema fa l’esborrat de l'usuari

11.Test Set Admin: L’administrador introdueix el username del usuari al que vol donar permisos d’administrador en el sistema

12.Per ultim, es dona la opcio de sortir del Driver
