# Dziennik zmian

## Wersje poprzedzające v1.0 (prototypowe)

### v0.1.3
- Dodano możliwość modyfikowania `MovableFeastsRow` za pomocą metody statycznej singletonu `Modifier`.

### v0.1.2
- Dodano możliwość zapisywania i wczytywania `MovableFeastsRow` do formatu JSON.
- Dodano klasy implementujące interfejsy jądra.
- Dodano obsługę wiersza poleceń.

### v0.1.1
- Zmieniono datę Uroczystości Wniebowstąpienia Pańskiego: od teraz interfejs domyślnie zwraca 40 dzień po Wielkanocy. 
  Wcześniej zwracana była 7. niedziela wielkanocna, która jest wyjątkiem dla Polski niż normą w Kościele powszechnym.
- Dodano możliwość strumieniowego odczytywania danych zwracanych przez interfejs `MovableFeastsRow`.
- Usunięto niepotrzebną zależność.

### v0.1.0
- Utworzenie plików *README.md* i *CHANGELOG.md*.
- Dodano podstawowe interfejsy dla programu.
