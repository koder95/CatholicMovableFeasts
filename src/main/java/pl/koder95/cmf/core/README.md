# Struktura

## Interfejsy
1.	pl.koder95.cmf.core.MovableFeastsRow
2.	pl.koder95.cmf.core.OrdinaryTimeRow

## Klasy
1.	pl.koder95.cmf.core.Cycle

## Typy wyliczeniowe
1.	pl.koder95.cmf.core.Cycle$Normal
2.	pl.koder95.cmf.core.Cycle$Solemnities

# Opis
![cmf-core](https://github.com/koder95/CatholicMoveableFeasts/assets/9617256/481ab786-5167-4f1d-9554-cc79635f935a)
Interfejsy **nr 1** (MovableFeastRow) i **2** (OrdinaryTimeRow) oraz **klasa nr 1** (Cycle) to interfejsy generujące daty świąt ruchomych.
**Interfejs nr 1** posiada domyślnie zaimplementowane metody (oprócz `getYear()`), które wyliczają daty i tygodnie dla świąt ruchomych w roku zwróconym przez metodę `getYear()`.
Takie rozwiązanie sprawia, że ten interfejs jest funkcjonalny i łatwy w użyciu.
**Klasa nr 1** jest singletonem, który uniemożliwia utworzenie obiektu, ale zawiera w sobie dwa typy wyliczeniowe: **nr 1** (Normal) i **2** (Solemnities).
