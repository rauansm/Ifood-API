create table cozinha (
  id_cozinha bigint not null auto_increment,
  nome varchar(60) not null,

  primary key (id_cozinha)
) engine=InnoDB default charset=utf8;

create table cidade (
	id_cidade bigint not null auto_increment,
	nome varchar(80) not null,
	estado_id bigint not null,

	primary key (id_cidade)
) engine=InnoDB default charset=utf8;

create table estado (
	id_estado bigint not null auto_increment,
	nome varchar(80) not null,

	primary key (id_estado)
) engine=InnoDB default charset=utf8;


alter table cidade add constraint fk_cidade_estado
foreign key (estado_id) references estado (id_estado);

