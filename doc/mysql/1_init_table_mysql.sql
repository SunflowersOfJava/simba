/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/6/9 ������ 18:27:04                        */
/*==============================================================*/


drop table if exists buss;

drop table if exists chatRecord;

drop table if exists installPackage;

drop table if exists menu;

drop table if exists org;

drop table if exists orgExt;

drop table if exists permission;

drop table if exists registryTable;

drop table if exists registryType;

drop table if exists role;

drop table if exists rolePermission;

drop table if exists systemUser;

drop table if exists userExt;

drop table if exists userOrg;

drop table if exists userRole;

/*==============================================================*/
/* Table: buss                                                  */
/*==============================================================*/
create table buss
(
   name                 varchar(64) not null,
   description          varchar(128),
   script               text not null,
   primary key (name)
);

/*==============================================================*/
/* Table: chatRecord                                            */
/*==============================================================*/
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

/*==============================================================*/
/* Table: installPackage                                        */
/*==============================================================*/
create table installPackage
(
   installVersion       varchar(32) not null,
   detail               varchar(512) not null,
   fileName             varchar(64) not null,
   newest               tinyint not null,
   publishDate          varchar(32) not null,
   primary key (installVersion)
);

/*==============================================================*/
/* Table: menu                                                  */
/*==============================================================*/
create table menu
(
   id                   int not null auto_increment,
   text                 varchar(64) not null,
   parentID             int not null,
   url                  varchar(256),
   orderNo              int,
   primary key (id)
);

alter table menu comment '�˵�';

/*==============================================================*/
/* Table: org                                                   */
/*==============================================================*/
create table org
(
   id                   int not null auto_increment comment '����ID',
   text                 varchar(128) not null comment '����',
   parentID             int not null comment '������ID',
   orderNo              int comment '����',
   primary key (id)
);

alter table org comment '����';

/*==============================================================*/
/* Table: orgExt                                                */
/*==============================================================*/
create table orgExt
(
   id                   int not null comment '����ID',
   primary key (id)
);

alter table orgExt comment '������չ';

/*==============================================================*/
/* Table: permission                                            */
/*==============================================================*/
create table permission
(
   id                   int not null auto_increment,
   text                 varchar(64) not null,
   url                  varchar(512),
   parentID             int not null,
   primary key (id)
);

/*==============================================================*/
/* Table: registryTable                                         */
/*==============================================================*/
create table registryTable
(
   id                   int not null auto_increment,
   code                 varchar(64) not null comment '����',
   value                varchar(128) not null comment 'ֵ',
   typeID               int not null comment '����ID',
   description          varchar(128) comment '����',
   primary key (id)
);

alter table registryTable comment 'ע���';

/*==============================================================*/
/* Table: registryType                                          */
/*==============================================================*/
create table registryType
(
   id                   int not null auto_increment,
   name                 varchar(128) not null comment '����',
   parentID             int not null comment '��ID',
   primary key (id)
);

alter table registryType comment 'ע������';

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role
(
   name                 varchar(64) not null,
   description          varchar(64),
   primary key (name)
);

/*==============================================================*/
/* Table: rolePermission                                        */
/*==============================================================*/
create table rolePermission
(
   id                   int not null auto_increment,
   roleName             varchar(64) not null,
   permissionID         int not null,
   primary key (id)
);

/*==============================================================*/
/* Table: systemUser                                            */
/*==============================================================*/
create table systemUser
(
   account              varchar(64) not null,
   name                 varchar(64) not null,
   pwd                  varchar(256) not null,
   primary key (account)
);

/*==============================================================*/
/* Table: userExt                                               */
/*==============================================================*/
create table userExt
(
   userAccount          varchar(64) not null comment '�û��˺�',
   primary key (userAccount)
);

alter table userExt comment '�û���չ';

/*==============================================================*/
/* Table: userOrg                                               */
/*==============================================================*/
create table userOrg
(
   id                   int not null auto_increment,
   userAccount          varchar(64) not null comment '�û��˺�',
   orgID                int not null comment '����ID',
   primary key (id)
);

alter table userOrg comment '�û�����';

/*==============================================================*/
/* Table: userRole                                              */
/*==============================================================*/
create table userRole
(
   userAccount          varchar(64) not null,
   roleName             varchar(64) not null,
   primary key (userAccount, roleName)
);

