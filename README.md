# Repozytorium do zajęć z Hibernate w SDA
Poniższe zadania zakładają, że mamy zaimportowaną bazę world_x.sql

Zadania:
1. Utworzyć następujące modele (na razie bez uwzględniania relacji):
   - Model dla encji City
   - Model dla encji Country
2. Dodać do powyższych informacje o relacjach.
3. Wyświetlić wszystkie państwa wraz z ich kodami
4. Znaleźć wszystkie państwa wraz z ich dwuliterowymi kodami bez pobierania innych danych
5. Znaleźć nazwy wszystkich miast w zadanym regionie
6. Napisać i przetestować zapytania w HQL realizujące następujące zadania:
   - znalezienie wszystkich miast z zadanego kraju wraz z ich regionami
   - znalezienie państwa o najmniejszej liczbie powiązanych miast
7. Dodać miasto do dowolnego państwa
8. Zmienić stolicę dowolnego państwa
9. Usunąć dowolne miasto
10. Usunąć dowolne państwo
11. Utworzyć nowe państwo i dodać do niego jedno miasto - w charakterze stolicy
12. Dodać do utworzonego państwa dwa nowe miasta.
13. Utworzyć model dla CountryLanguage
14. Poowiązać CountryLanguage z encjami City i Country
15. Napisać i przetestować zapytania w HQL realizujące następujące zadania:
   - znalezienie państw posiadających jeden używany język
   - znalezienie wszystkich państw, w których mówi się w zadanym języku
   - znalezienie wszystkich państw, w których zadany język jest urzędowy
   - znalezienie państwa o największej liczbie języków
