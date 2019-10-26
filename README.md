# Sokoban

## Wstęp
Niniejsza instrukcja dotyczy gry Sokoban przygotowanej przez studentów czwartego semestru kierunku Elektronika na Wydziale Elektroniki i Technik Informacyjnych Politechniki Warszawskiej w ramach projektu z przedmiotu Programowanie Zdarzeniowe (PROZE). Autorami projektu są: Jakub Wrzosek oraz Hubert Jaworski.
W kolejnych punktach zostaną przedstawione najważniejsze informacje na temat użytkowania aplikacji.

## Informacje wstępne
Aplikacja została napisana w języku Java w wersji 8. Do jej uruchomienia niezbędne jest posiadanie na swoim komputerze zainstalowanego środowiska uruchomieniowego Javy (JRE) służącego do uruchomienia aplikacji .jar. W wypadku naszej aplikacji niezbędne jest posiadanie Java SE Runtime Enviroment 8.
Jeśli nie posiadamy niezbędnego środowiska uruchomieniowego, możemy pobrać je ze strony internetowej Oracle: http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html

## Elementy aplikacji
Aplikacja składa się z plików z kodem źródłowym programu, plików konfiguracyjnych zawierających elementy niezbędne do prawidłowego funkcjonowania programu (definicję poziomów) oraz z pliku uruchomieniowego z rozszerzeniem .jar.

## Cel gry
Na rozgrywkę składają się następujące elementu: gracz, skrzynie, ściany oraz miejsca docelowe dla skrzyń.
Celem gracza jest ustawienie skrzyń na miejscach docelowych, z tym faktym, iż gracz może poruszać paczki tylko w kierunkach poruszania się tj. w górę, w dół, w lewo i w prawo po terenie ograniczonym przez ściany.
W momencie ustawienia skrzyń na miejscach docelowych gracz przechodzi do kolejnego poziomu. Po przejściu wszystkich poziomów gracz zwycięża oraz zostaje sklasyfikowany z możliwością znalezienia się w oknie z trzema najlepszymi wynikami.

## Elementy wizualne aplikacji
### Okno główne aplikacji
![](images/OknoGlowne.jpg)
Powyższa grafika przedstawia okno główne aplikacji. Możemy w nim wyodrębnić cztery elementu głowne. Jednym z nich jest napis informujący, iż znajdujemy się w menu gry Sokoban. Pozostałymi trzema elementami są przyciski, za pomocą których możemy wybrać interesującą nas w danej chwili akcję.
Pierwszym przyciskiem od dołu jest „Wyjdź z gry”. Wciśniecie tego przycisku powoduje zamknięcie aplikacji. Warto zaznaczyć, iż ten sam skutek możemy osiągnąć klikając czerwoną ikonkę z „iksem” w prawym górnym rogu okna.
Środkowy przycisk „Najlepsze wyniki” pozwala nam przejść do okna zawierającego trzy najlepsze wyniki.
Przycisk na samej górze wywołuję akcję prowadzącą do rozpoczęcia rozgrywki. Po jego wciśnięciu aplikacja rozpoczyna dialog z użytkownikiem, mający na celu pobranie od niego nicku oraz wybór przez użytkownika poziomu trudności.
Po wciśnięciu przycisku „Nowa gra” naszym oczom ukaże się okno następujące okno dialogowe:
![](images/NickInput.jpg)
![](images/DefaultNickInput.jpg)
W tym momencie powinniśmy podać nick i kliknąć przycisk „OK” aby przejść dalej. W tym miejscu warto zaznaczyć, że kliknięcie „Cancel” lub czerwonej ikonki z „iksem” spowoduje powrót do głównego menu. Podanie nieprawidlowego nicku (tzn. chęć zatwierdzenia bez wprowadzenia żadnego znaku) spowoduje wyświetlenie następującego okna:
![](images/ZlyNick.jpg)
Po wciśnięciu „OK” nastąpi powrót do menu głównego.
Załóżmy, że podanie nicku zakończyło się sukcesem. Kolejnym krokiem jest wybór poziomu trudności. Po zatwierdzeniu podanego nicku, naszym oczom ukaże się okno umożliwające wybór jednego z trzech przewidzianych poziomów trudności: Easy, Medium oraz Hard.
![](images/PoziomTrudnosci.jpg)
Po wciśnięciu strzałki po prawiej stronie od podświetlonego napisu „Medium” rozwinie się lista ze wszystkimi dostępnymi poziomami trudności. W przypadku kliknęcia opcji „Cancel” lub czerwonej ikonki z „iksem” nastąpi powrót do menu głównego.
![](images/ListraPoziomowTrudnosci.jpg)
Po wybraniu poziomu trudności oraz kliknięciu „OK” przechodzimy do okna rozgrywki.

















