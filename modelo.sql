 load data infile '/home/goao/random2.csv' into table random_a fields terminated by ',';
 load data infile '/home/goao/devel/LOTO/random2.csv' into table random2_a fields terminated by ',';
 insert into random2_b (bol1,bol2,bol3,bol4,bol5,bol6) select * from random2_a;

 insert into random2_c  select codigo, bol1 from random2_b;
 insert into random2_c  select codigo, bol2 from random2_b;
 insert into random2_c  select codigo, bol3 from random2_b;
 insert into random2_c  select codigo, bol4 from random2_b;
 insert into random2_c  select codigo, bol5 from random2_b;
 insert into random2_c  select codigo, bol6 from random2_b;


select count(1), numero from random2_c group by numero order by 1;
select count(1), numero,quadrante from random2_d group by numero,quadrante order by 1;

 insert into random2_e select count(1), numero,quadrante from random2_d group by numero,quadrante order by 1;
select sum(qtd),quadrante from random2_e group by quadrante

 select dezena, count(1),numero from sorteios_grouped where dezena = 3 group by numero  order by 2

create table random_org_monitor(
    round int not null,
    bola int not null,
    numero int not null,
    position int not null,
    data datetime not null,

    PRIMARY KEY(round, bola, position)
);


create table random2_a(
    bol1 int not null,
    bol2 int not null,
    bol3 int not null,
    bol4 int not null,
    bol5 int not null,
    bol6 int not null

);

create table random2_b(
    codigo int not null auto_increment,
    bol1 int not null,
    bol2 int not null,
    bol3 int not null,
    bol4 int not null,
    bol5 int not null,
    bol6 int not null,
PRIMARY KEY  (`codigo`)
);


create table random2_bb(
    codigo int not null ,
    numero int not null,
    dezena int not null
);

 insert into random2_bb  select codigo, bol1,1 from random2_b;
 insert into random2_bb  select codigo, bol2,2 from random2_b;
 insert into random2_bb  select codigo, bol3,3 from random2_b;
 insert into random2_bb  select codigo, bol4,4 from random2_b;
 insert into random2_bb  select codigo, bol5,5 from random2_b;
 insert into random2_bb  select codigo, bol6,6 from random2_b;


create table random2_c (

    codigo int not null,
    numero int not null
);



create table random2_d (

    codigo int not null,
    numero int not null,
    quadrante int not null
);


create table random2_e(
    qtd int not null,
    numero int not null,
    quadrante int not null

)

create table sorteios_grouped_quadrante(
    concurso int not null,
    numero int not null,
    quadrante int not null
)


create table sorteios_grouped_quadrante_qtd(
    qtd int not null,
    numero int not null,
    quadrante int not null
)


create table sorteios_grouped_seq(
    codigo int not null auto_increment,
    numero int not null,
    primary key(codigo)
)
