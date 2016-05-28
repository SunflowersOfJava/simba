create table chatRecord
(
   id                   int not null auto_increment,
   fromAccount          varchar(128) not null,
   fromName             varchar(128) not null,
   toAccount            varchar(128) not null,
   toName               varchar(128) not null,
   content              varchar(512) not null,
   sendTime             datetime not null,
   primary key (id)
);
