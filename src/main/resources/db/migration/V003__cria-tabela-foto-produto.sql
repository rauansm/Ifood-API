create table foto_produto (
  id_foto_produto bigint not null,
  nome_arquivo varchar(150) not null,
  descricao varchar(150),
  content_type varchar(80) not null,
  tamanho int not null,
  produto_id bigint not null,

  primary key (id_foto_produto),
  constraint fk_foto_produto_produto foreign key (produto_id) references produto (id_produto)
) engine=InnoDB default charset=utf8;