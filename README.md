coolCar
=======

Protótipo de sistema web de locações de veículo realizado para a disciplina de Engenharia de Software (MAC0332) no IME-USP no segundo semestre de 2015.

**Autores**

- Caio Lopes Demario (7991187)
- Felipe Túlio Pereira da Cruz (7557709)
- Marcos Kazuya Yamazaki (7577622)
- Rodrigo Zerbini (4324346)
- Tiago Madeira (6920244)

Professora: Ana Cristina Vieira de Melo

**Requisitos para desenvolvimento**

- JDK 1.7
- Eclipse 4.5.1 (Mars)

**Como desenvolver**

1. Importe o projeto no Eclipse (*File -> Import*)

2. Clique com a tecla direita no projeto (à esquerda) e *Properties -> Targeted Runtimes*.

  1. New... -> Apache -> Apache TomCat v8.0 (e marcar **Create a new local server**) -> Next
  2. Use *Download and Install* para preencher *Tomcat Installation Directory* (a não ser que você já tenha instalado o Tomcat 8 de outra forma; aí basta selecionar a pasta dele... no Arch é /usr/share/tomcat8) -> Finish.

4. Na barra inferior, em *Servers*, clicar com a tecla direita no *Tomcat v8.0*, *Add and Remove* e adicionar coolcar.

5. Em *Run -> Run Configurations... -> Apache Tomcat -> Classpath -> Add JARs*, adicione *coolcar/vendor/postgresql-9.4-1205.jdbc41.jar*.

6. Caso você não queira usar a configuração padrão para o banco de dados (Postgres rodando em *localhost* com nome de usuário do usuário que está rodando o servidor e sem senha), crie um arquivo *coolcar.properties* na sua home com um conteúdo como:

  ```
  db_host=jdbc:postgresql://postgresql.linux.ime.usp.br:5432/usuario
  db_user=usuario
  ```

  (esse exemplo foi feito para a Rede Linux)

7. *Run -> Run*

Para testar se o banco de dados está configurado corretamente, acesse [http://localhost:8080/coolcar/teste_bd.jsp](http://localhost:8080/coolcar/teste_bd.jsp).
