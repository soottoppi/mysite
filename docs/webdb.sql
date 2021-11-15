-- scheme user
desc user;

-- insert
insert into user values(null, '관리자', 'admin@mysite.com', '123', 'male', now(), 'ADMIN');
insert into user values(null, '관리자', 'duwjs@gmail.com', '123', 'male', now(), 'ADMIN');

select no, name, date_format(reg_date, '%Y/%m/%d %H:%i:%s') as regDate, message 
		   			from guestbook 
                    
	   			order by reg_date desc
                limit ,5;
                
select * from emaillist;
select * from site;

-- select01
select no, name from user where email='dooly@mgail.com' and password='1234';
select no, name from user where no = 10;
-- select02
select * from user;
select * from site;

select ifnull(max(group_no), 0) from board;

delete from user where no = 1;

			select count(*) from site;
select * from site;


select no, name, email, password, gender from user where no = 10;

update user
set name = '홍길동', password = '1111', gender='female'
where no = 10;
delete from user;

select no, name, password
		  from user
		 where email='duwjs1103@gmail.com'
		   and password='123';

select no, url, comments from gallery order by no desc;

			select b.no, title, contents, hit, reg_date, group_no, order_no, depth, user_no, name 
				from board b, user u 
			where b.user_no = u.no
		    order by group_no DESC, order_no ASC limit 0, 10;
select no, name, email, gender 
				from user ;

select b.no, title, contents, hit, reg_date as regDate, group_no, order_no, depth, user_no as userNo, name userName 
				 from board b, user u 
			where b.user_no = u.no and b.no = 154;

update board 
			   set hit = hit + 1
			 where no=154;

select max(group_no) from board;
alter table user add column role enum('USER', 'ADMIN') not null default 'USER';
select * from user;
insert into user values(null, '관리자', 'admin@mysite.com', '123', 'male', now(), 'ADMIN');

desc site;


select * from guestbook;

select no, name, date_format(reg_date, '%Y/%m/%d %H:%i:%s') as regDate, message 
		   			from guestbook 
	   			order by no desc;
delete from guestbook;

select * from site;

insert into site values(null, 'MySite', '안녕하세요. 김수형의 홈페이지에 오신 것을 환영합니다.', '/admin/images/cat.png', '이 사이트는 웹 프로그램밍 실습과제 예제 사이트입니다.
\n메뉴는 사이트 소개, 방명록, 게시판이 있구요. \nJava수업 + 데이터베이스 수업 + 웹프로그래밍 수업 배운 거 있는거 없는 거 다 합쳐서 만들어 놓은 사이트 입니다.');