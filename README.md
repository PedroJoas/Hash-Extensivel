# Hash-Extensivel
Repositório direcionado para o Trabalho I da SGBD 

# Explicação do trabalho 

Consiste em implementar um hash extensível utilizando Java em cima do arquivo compras.csv.

## Exemplo Arquivo compras.csv
| Pedido | Valor|  Ano |
| ------------- | ------------- | ------------- |
| 1  | 5000 | 2018  | 
| 2  | 3589  |  1998  | 
| 3  | 12.56 | 2010  | 

O programa vai receber um arquivo in.txt com uma série de comandos para serem feitos e gerar um arquivo out.txt

## Exemplo in.txt:
```python
PG/2      # Configura a profundidade global inicial do índice hash como 3. Isso significa que o diretório começará com 2^3 (ou seja, 8) buckets.
INC:1998  # Insere um registro com chave 1998.
INC:2024
INC:1995
BUS:1998  # Busca pelo registro com chave 1998.
REM:1995  # Remove o registro com chave 1995.
INC:2013
INC:2005
REM:2013
```
## Exemplo out.txt esperado
```bash
PG/2   # Configura a profundidade global inicial do índice hash como 3. Isso significa que o diretório começará com 2^3 (ou seja, 8) buckets.
INC:1998/2,2   # Operação: Chave/PG, PA 
INC:2024/2,2
INC:1995/2,2
BUS:1998/1    # Operação: Chave/tuplas encontradas
REM:1995/1,2,2  # Operação: chave/tuplas removidas, PG, PA
INC:2013/2,2
INC:2005/4,4
REM:2013/3,2,2
```
