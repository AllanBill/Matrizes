# Multiplicador de Matrizes - Java

Este programa Java lê duas matrizes quadradas (M1 e V1) de um arquivo `.txt` ou `.xlsx`, realiza a multiplicação entre elas (M1 × V1), e imprime a matriz resultante (V2).

---

## 📦 Requisitos

- **Java JDK 8+**
- Para leitura de arquivos `.xlsx`, é necessário adicionar as bibliotecas Apache POI:

### Bibliotecas necessárias (para `.xlsx`)
Se estiver usando `.xlsx`, você deve adicionar os arquivos `.jar` ao classpath:
- `poi-*.jar`
- `poi-ooxml-*.jar`
- `commons-*.jar`
- `xmlbeans-*.jar`
- Outros `.jar` da pasta `lib/`

---

## 📁 Estrutura esperada dos arquivos

### ✅ Arquivo `.txt` (`matrizes.txt`)
Formato esperado:

3
1 2 3
4 5 6
7 8 9
1 0 0
0 1 0
0 0 1


- Linha 1: valor `n` (tamanho da matriz `n x n`)
- Linhas 2 a `n+1`: matriz M1
- Linhas `n+2` a `2n+1`: matriz V1

### ✅ Arquivo `.xlsx` (`matrizes.xlsx`)
Formato esperado:

|     | A | B | C |
|-----|---|---|---|
| 1   | 3 |   |   | ← Valor de `n` (A1)  
| 2   | 1 | 2 | 3 | ← M1 começa aqui  
| 3   | 4 | 5 | 6 |
| 4   | 7 | 8 | 9 |
| 5   | 1 | 0 | 0 | ← V1 começa aqui  
| 6   | 0 | 1 | 0 |
| 7   | 0 | 0 | 1 |

---

## 🚀 Como compilar e executar

### 📌 Usando `.txt`

```bash
javac matrizes.java
java matrizes
