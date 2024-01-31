
create table forma_pagamento (
id_pagamento bigint not null auto_increment,
data_atualizacao datetime,
descricao varchar(60) not null,
 primary key (id_pagamento)
 ) engine=InnoDB;

create table grupo (
id_grupo bigint not null auto_increment,
nome varchar(80) not null,
primary key (id_grupo)
) engine=InnoDB;


create table grupo_permissao (
grupo_id bigint not null,
permissao_id bigint not null,
 primary key (grupo_id, permissao_id)
 ) engine=InnoDB;


create table item_pedido (
id_item_pedido bigint not null auto_increment,
observacao varchar(60),
 preco_total decimal(10,2),
 preco_unitario decimal(10,2),
 quantidade integer,
 pedido_id bigint not null,
 produto_id bigint not null,
 primary key (id_item_pedido)
 ) engine=InnoDB;


create table pedido (
id_pedido bigint not null auto_increment,
codigo_pedido varchar(36),
 data_cancelamento datetime null,
 data_confirmacao datetime null,
 data_criacao datetime not null,
 data_entrega datetime null,
 endereco_bairro varchar(60),
 endereco_cep varchar(9),
 endereco_complemento varchar(60),
 endereco_logradouro varchar(100),
 endereco_numero varchar(20),
 status varchar(10) not null,
 subtotal decimal(10,2),
 taxa_frete decimal(10,2),
 valor_total decimal(10,2),
 usuario_cliente_id varchar (36) not null,
 endereco_cidade_id bigint not null,
 forma_pagamento_id bigint not null,
 restaurante_id bigint not null,
 primary key (id_pedido)
 ) engine=InnoDB;


create table permissao (
id_permissao bigint not null auto_increment,
 descricao varchar(100) not null,
 nome varchar(100) not null,
 primary key (id_permissao)
 ) engine=InnoDB;


create table produto (
id_produto bigint not null auto_increment,
ativo tinyint(1) not null,
 descricao varchar(255) not null,
 nome varchar(70) not null,
 preco decimal(10,2) not null,
 restaurante_id bigint not null,
 primary key (id_produto)
 ) engine=InnoDB;


create table restaurante (
id_restaurante bigint not null auto_increment,
data_atualizacao datetime not null,
data_cadastro datetime not null,
nome varchar(80) not null,
taxa_frete decimal(10,2) not null,
ativo tinyint(1) not null,
aberto tinyint(1) not null,
endereco_cidade_id bigint,
endereco_cep varchar(9),
endereco_logradouro varchar(100),
endereco_numero varchar(20),
endereco_complemento varchar(60),
endereco_bairro varchar(60),
cozinha_id bigint not null,
primary key (id_restaurante)
) engine=InnoDB;

create table restaurante_forma_pagamento (
	restaurante_id bigint not null,
	forma_pagamento_id bigint not null,

	primary key (restaurante_id, forma_pagamento_id)
) engine=InnoDB ;


create table restaurante_usuario_responsavel (
  restaurante_id bigint not null,
  usuario_id varchar(36) not null,

  primary key (restaurante_id, usuario_id)
) engine=InnoDB ;


create table usuario (
id_usuario varchar(36) not null,
data_cadastro datetime not null,
email varchar(150) not null,
nome varchar(80) not null,
senha varchar(100) not null,
primary key (id_usuario)
) engine=InnoDB;


create table usuario_grupo (
usuario_id varchar(36) not null,
grupo_id bigint not null,
 primary key (usuario_id, grupo_id)
 ) engine=InnoDB;





alter table grupo_permissao
add constraint fk_grupo_permissao
foreign key (permissao_id) references permissao (id_permissao);


alter table grupo_permissao
add constraint fk_permissao_grupo
foreign key (grupo_id) references grupo (id_grupo);


alter table item_pedido
add constraint fk_itempedido_pedido
foreign key (pedido_id) references pedido (id_pedido);


alter table item_pedido
 add constraint fk_itempedido_produto
 foreign key (produto_id) references produto (id_produto);


alter table pedido
add constraint fk_pedido_cliente
foreign key (usuario_cliente_id) references usuario (id_usuario);

alter table pedido
 add constraint fk_pedido_cidade
 foreign key (endereco_cidade_id) references cidade (id_cidade);

alter table pedido
 add constraint fk_pedido_pagamento
 foreign key (forma_pagamento_id) references forma_pagamento (id_pagamento);

alter table pedido
add constraint fk_pedido_restaurante
 foreign key (restaurante_id) references restaurante (id_restaurante);

alter table produto
add constraint fk_produto_restaurante
foreign key (restaurante_id) references restaurante (id_restaurante);

alter table restaurante
add constraint fk_restaurante_cozinha
foreign key (cozinha_id) references cozinha (id_cozinha);

alter table usuario_grupo
 add constraint fk_usuario_grupo_grupo
 foreign key (grupo_id) references grupo (id_grupo);

alter table usuario_grupo
add constraint fk_usuario_grupo_usuario
 foreign key (usuario_id) references usuario (id_usuario);

 alter table restaurante_usuario_responsavel
 add constraint fk_restaurante_usuario_restaurante
 foreign key (restaurante_id) references restaurante (id_restaurante);

 alter table restaurante_usuario_responsavel
 add constraint fk_restaurante_usuario_usuario
 foreign key (usuario_id) references usuario (id_usuario);

 alter table restaurante_forma_pagamento
 add constraint fk_rest_forma_pagto_forma_pagto
 foreign key (forma_pagamento_id) references forma_pagamento (id_pagamento);

 alter table restaurante_forma_pagamento
 add constraint fk_rest_forma_pagto_restaurante
 foreign key (restaurante_id) references restaurante (id_restaurante);

alter table restaurante add constraint fk_restaurante_cidade
foreign key (endereco_cidade_id) references cidade (id_cidade);