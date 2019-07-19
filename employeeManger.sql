/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/7/3 9:37:48                             */
/*==============================================================*/


drop table if exists t_apply;

drop table if exists t_attendance;

drop table if exists t_dep;

drop table if exists t_document;

drop table if exists t_employee;

drop table if exists t_job;

drop table if exists t_notice;

drop table if exists t_report;

/*==============================================================*/
/* Table: t_apply                                               */
/*==============================================================*/
create table t_apply
(
   empid                int,
   applyid              int not null auto_increment,
   applyreason          varchar(255),
   applytype            char(1),
   applystatus          char(1),
   applytime            timestamp,
   remark               varchar(255),
   approverid           int,
   approvertime         timestamp,
   refusereason         varchar(255),
   starttime            timestamp,
   endtime              timestamp,
   primary key (applyid)
);

/*==============================================================*/
/* Table: t_attendance                                          */
/*==============================================================*/
create table t_attendance
(
   empid                int,
   empname              varchar(20),
   attendancetime       date,
   openstatus           char(1),
   opentime             timestamp,
   closestatus          char(1),
   closetime            timestamp,
   remark               varchar(255)
);

/*==============================================================*/
/* Table: t_dep                                                 */
/*==============================================================*/
create table t_dep
(
   depid                int not null auto_increment,
   depname              varchar(20),
   depdetail            varchar(255),
   primary key (depid)
);

/*==============================================================*/
/* Table: t_document                                            */
/*==============================================================*/
create table t_document
(
   documentid           int not null auto_increment,
   empid                int,
   title                varchar(255),
   filepath             varchar(50),
   remark               varchar(255),
   createtime           timestamp,
   primary key (documentid)
);

/*==============================================================*/
/* Table: t_employee                                            */
/*==============================================================*/
create table t_employee
(
   empid                int not null auto_increment,
   depid                int,
   jobid                int,
   empname              varchar(20),
   cardnumber           varchar(20),
   address              varchar(50),
   postcode             varchar(20),
   tel                  varchar(20),
   phone                char(11),
   qq                   varchar(20),
   email                varchar(20),
   sex                  char(1),
   party                varchar(20),
   birthday             datetime,
   race                 varchar(50),
   education            varchar(20),
   speciality           varchar(20),
   hobby                varchar(255),
   remark               varchar(255),
   createtime           timestamp,
   password             varchar(20),
   faceurl              varchar(20),
   facepath             varchar(20),
   role                 char(1),
   status               char(1),
   primary key (empid)
);

/*==============================================================*/
/* Table: t_job                                                 */
/*==============================================================*/
create table t_job
(
   jobid                int not null auto_increment,
   jobname              varchar(20),
   jobdetail            varchar(255),
   primary key (jobid)
);

/*==============================================================*/
/* Table: t_notice                                              */
/*==============================================================*/
create table t_notice
(
   noticeid             int not null auto_increment,
   empid                int,
   noticename           varchar(255),
   noticecontent        varchar(255),
   createtime           timestamp,
   primary key (noticeid)
);

/*==============================================================*/
/* Table: t_report                                              */
/*==============================================================*/
create table t_report
(
   empid                int,
   reportid             int not null auto_increment,
   reporttype           char(1),
   reporttitle          varchar(50),
   reportcontent        varchar(255),
   reporttime           timestamp,
   primary key (reportid)
);

alter table t_apply add constraint FK_Reference_7 foreign key (empid)
      references t_employee (empid) on delete restrict on update restrict;

alter table t_attendance add constraint FK_Reference_6 foreign key (empid)
      references t_employee (empid) on delete restrict on update restrict;

alter table t_document add constraint FK_Reference_5 foreign key (empid)
      references t_employee (empid) on delete restrict on update restrict;

alter table t_employee add constraint FK_Reference_2 foreign key (depid)
      references t_dep (depid) on delete restrict on update restrict;

alter table t_employee add constraint FK_Reference_3 foreign key (jobid)
      references t_job (jobid) on delete restrict on update restrict;

alter table t_notice add constraint FK_Reference_4 foreign key (empid)
      references t_employee (empid) on delete restrict on update restrict;

alter table t_report add constraint FK_Reference_8 foreign key (empid)
      references t_employee (empid) on delete restrict on update restrict;

