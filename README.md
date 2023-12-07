# dogins_api 🐶💙
Dogin's é um petshop online com um design AUUdacioso 🐶💙 Nele, você pode visualizar produtos em destaque na Home, ver mais detalhes do seu produto favorito e adicionar ao carrinho! É claro, você também pode comprar os produtos mais AUUdacioso para seu pet 💙💙💙

### Bora conhecer?
Acesse o link do repositório GitHub do [Dogins Mobile](https://github.com/isefshondo/PetshopDoginsMobile).
<br> Opa, o Dogin's tem a <strong> 💙 versão Web 💙 </strong> mais AUUdaciosa que já se viu, [venha conhecer](https://github.com/isefshondo/petshopDogins) 🐶💙

###### O que essa API faz?
<p> A atual API desenvolvida em Spring Boot Java fornece os principais endpoints de comunicação do back end do aplicativo mobile kotlin do Dogins. A API faz comunicação direta com o banco de dados em MongoDb, compartilhado pelo ecommerce Web e pela aplicação Mobile. Além disso, o database, coleções e documentos são carregados automaticamente através do MongoRepository e do DatabasePopulator. Para o consumo da API na aplicação Kotlin foi utilizado o client do Retrofit. </p><br>

## Arquivos Importantes 🐶💙
- Tenha certeza que o documento JSON `products.json` foi clonado corretamente na pasta `dogins/src/main/resources`
- Verifique igualmente se a classe `DatabasePopulator` está na pasta `dogins/src/main/java/br/com/dogins/data/DatabasePopulator.java`
- Para encontrar a classe Main acesse `dogins/src/main/java/br/com/dogins/DoginsApplication.java`
- Se necessário houver problemas com a conexão com o MongoDB, certifique-se de olhar se a classe `dogins/src/main/resources/application.properties` está configurada

## Como rodar este projeto? 🐶💙

### Requisitos de Software 🐶💙

<li>MongoDB</li>
<li>Java SDK 17</li>
<li>IntelliJ, VS Code ou outra IDE compatível</li>

### Configurando o MongoDB 🐶💙
É recomendado que a máquina tenha o MongoDB Compass instalado. Após fazer a conexão com localhost, uma nova coleção deverá ser criada `automaticamente` pela API com o nome `dogin` e uma coleção chamada `products`. A coleção `shopping-cart` só é criada quando solicitado pelo aplicativo mobile.

### Play na API 🐶💙
1. <p>Clone o atual repositório em seu computador utilizando o comando:</p>
```
git clone https://github.com/mandis-ncs/dogins_api.git
```

2. Abra a API Dogins pelo VS Code ou IntelliJ

3. Certifique-se de que o JDK está instalado e configurado corretamente

4. Certifique-se de ter realizado a configuração correta do MongoDB

5. Certifique-se de iniciar a API pressionando o botão de Run na classe Main. Caso não encontre esse botão, clique com o botão direito do mouse na classe Main no diretório e selecione Run.

6. A API será iniciada na porta 8080 `http://localhost:8080`, certifique-se que esta porta não está em uso.

7. Execute o Emulador do Android Studio


## Problemas Conhecidos 🐶💙

### Erro ao Configurar o banco de dados com o Database Populator.

Se você encontrar um erro relacionado a Configurar o banco de dados com o Database Populator, você terá que fazer a configuração manual:

1. Apagar a classe DatabasePopulator;

2. Acessar o MongoDB Compass;

3. Criar um database com nome `dogin` e a coleção `products`;

4. Acessar a coleção criada e escolher `Add Data - Import JSON file`.
<br>

### Erro na porta 8080.

Se você encontrar um erro relacionado a ter outra aplicação rodando na porta 8080, use o comando `server.port = {nova porta}` no `application.properties` para configurar outra porta. Por exemplo:
```
server.port = 8081
```

<br>

## EndPoints 🐶💙
Se tiver o interesse de testar somente a API com auxílio do Postman ou Isomnia, use os comandos comentados acima de cada endpoint declarado no controller `ProductController` na pasta `dogins/src/main/java/br/com/dogins/controllers`.

Para o endpoint POST do shopping Cart, use o seguinte corpo:
```
[
    {
        "id": "656d4191ab73ee5765012ead",
        "image": ["iVBORw0"],
        "title": "Brinquedo de Pelúcia Chalesco Tatu",
        "discount": 0.0,
        "inStock": 15,
        "quantity": 1,
        "price": 55.0,
        "total": 55.0
    },
    {
        "id": "643734c97f7b43ab29138190",
        "image": ["iVBORw"],
        "title": "Hemolitan Pet",
        "discount": 0.0,
        "inStock": 20,
        "quantity": 1,
        "price": 54.9,
        "total": 54.9
    }
]
```
Obs: Os produtos acima são exemplos de produtos cadastrados automaticamente no database. Se outros ids forem adicionados no lugar do exemplo acima, os outros métodos serão afetados e irão cair em exceção. Sinta-se livre para testar.

## Nosso Team AUUdacioso 🐶💙
You can see more about us in our profile:
* [Isabelle](https://github.com/isefshondo)
* [Amanda](https://github.com/mandis-ncs)
* [Junior](https://github.com/aasjunior)
* [Elizama](https://github.com/Eliz-ama)
* [Nayara](https://github.com/NahAzevedo)
* André
* Eliana

###### Aviso
Esta é uma iniciativa acadêmica, sendo assim, não possui todas as funcionalidades e características de uma aplicação real.
