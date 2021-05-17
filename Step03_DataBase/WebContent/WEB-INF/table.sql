-- �׽�Ʈ ȸ�� ����� ������ member ���̺� 
CREATE TABLE member(
num NUMBER PRIMARY KEY,
name VARCHAR2(30),
addr VARCHAR2(30)
);
-- member ���̺� ��ȣ�� ������ ������
CREATE SEQUENCE member_seq;

-- ���� ����� ������ todo ���̺� 
CREATE TABLE todo(
num NUMBER PRIMARY KEY,
content VARCHAR2(50) NOT NULL,
regdate DATE);

-- todo ���̺� num �� ������� ���ڸ� �ֱ� ���� ������
CREATE SEQUENCE todo_seq;

-- ���� �Խ��� ���̺� (�α��� ���� �ʾƵ� �����Ӱ� �ۼ��Ҽ� �ִ� �Խ���)
CREATE TABLE board_free(
	num NUMBER PRIMARY KEY,
	writer VARCHAR2(50) NOT NULL,
	title VARCHAR2(100),
	content CLOB,
	pwd VARCHAR2(50) NOT NULL,
	regdate DATE
);

-- ���� �Խ��� ���̺��� �� ��ȣ�� ������ ������
CREATE SEQUENCE board_free_seq;