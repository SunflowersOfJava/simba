/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/3/1 17:41:45                            */
/*==============================================================*/


drop table if exists buss;

drop table if exists chatRecord;

drop table if exists installPackage;

drop table if exists menu;

drop table if exists permission;

drop table if exists role;

drop table if exists rolePermission;

drop table if exists systemUser;

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

alter table menu comment '²Ëµ¥';

/*==============================================================*/
/* Table: permission                                            */
/*==============================================================*/
create table permission
(
   name                 varchar(64) not null,
   description          varchar(64),
   url                  varchar(512) not null,
   primary key (name)
);

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
   roleName             varchar(64) not null,
   permissionName       varchar(64) not null,
   primary key (roleName, permissionName)
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
/* Table: userRole                                              */
/*==============================================================*/
create table userRole
(
   userAccount          varchar(64) not null,
   roleName             varchar(64) not null,
   primary key (userAccount, roleName)
);

