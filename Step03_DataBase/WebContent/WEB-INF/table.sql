-- ���� ����� ������ todo ���̺� 
CREATE TABLE todo(
num NUMBER PRIMARY KEY,
content VARCHAR2(50) NOT NULL,
regdate DATE);

-- todo ���̺� num �� ������� ���ڸ� �ֱ� ���� ������
CREATE SEQUENCE todo_seq;