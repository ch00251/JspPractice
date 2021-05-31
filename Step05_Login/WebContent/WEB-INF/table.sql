CREATE TABLE users
(id VARCHAR2(30) PRIMARY KEY,
pwd VARCHAR2(30) NOT NULL,
email VARCHAR2(30),
regdate DATE);

create table board_cafe(
	num number primary key,
	writer varchar2(100) not null,
	title varchar2(100)not null,
	content clob,
	viewCount number,
	regdate date
);

create sequence board_cafe_seq;

create table board_file(
num number primary key,
writer varchar2(100),
title varchar2(100) not null,
orgFileName varchar2(100) not null, --���� ���ϸ�
saveFileName varchar2(100) not null, --���� �ý��ۿ� ���� ����� ���ϸ�
fileSize number, --������ ũ��(byte)
downCount number default 0, -- �ٿ�ε� Ƚ��
regdate date );

create sequence board_file_seq;

alter table users add(profile varchar2(50));

