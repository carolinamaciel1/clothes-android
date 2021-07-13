# clothes-Android

O app contém duas telas:

1. Listagem das roupas exibindo nome e foto;
2. Tela com as informações detalhadas da roupa selecionada na tela 1. 

Arquitetura utilizada: MVVM

Testes de snapshots: 
1. Checa se a recycler está sendo apresentada na tela
2. Checa o click em um produto dada uma posição
3. Faz o scroll pra uma posição de um produto e realiza o click 

Informações na tela 1: 
- Imagem do produto;
- Nome do produto;
- Tag de oferta (se houver);

Informações na tela 2:
- Imagem;
- Nome;
- Preço;
- Status de promoção;
- Preço promocional (se houver);
- Parcelamentos disponíveis;
- Tamanhos disponíveis.
- Pontos importantes:
- Qualquer outro atributo que vier na api, deve ser ignorado.
- Caso exista um preço promocional, exibir uma tag de promoção e "cortar" o preço regular.

Imagens das telas 

<img src="https://github.com/carolinamaciel1/carolinamaciel1.github.io/blob/master/imgs/tela1.png" alt="SpashScreen"/>
<img src="https://github.com/carolinamaciel1/carolinamaciel1.github.io/blob/master/imgs/tela2.png" alt="ProductsListActivity"/>
<img src="https://github.com/carolinamaciel1/carolinamaciel1.github.io/blob/master/imgs/tela3.png" alt="ProductsDetailActivity"/>

