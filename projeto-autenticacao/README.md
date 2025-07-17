Recomendações de boas práticas:

1) Sempre use Log4J e faça logs claros do que está sendo processado;
2) use o .gitignore do git para evitar comitar arquivos que são da IDE (por exemplo diretorio .idea e arquivo .iml, ou arquivos de compilação (que ficam dentro do diretorio target)
3) Coloque o pom.xml e o diretorio src na raiz do projeto (é uma boa pratica também já que é apenas um projeto)

Evolução do projeto:

1) Habilite o spring actuator no projeto;
2) O path /health/ do spring actuator deve ficar sem autenticação;
3) Criar 3 paths novos:
   a) O primeiro não vai exigir permissão especifica, o usuário só precisa estar logado (é o que o atual faz);
   b) O segundo vai exigir uma permissão específica;
   c) O terceiro vai exigir uma permissão que o usuário não tem;