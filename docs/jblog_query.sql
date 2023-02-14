-- --------------------------
show tables;

desc user;
desc blog;
desc category;
desc post;
-- --------------------------

select * from user;
select * from blog;
select * from category;
select * from post;

-- isUser (유저 존재 여부  검색)
select ifnull(count(*), 0)
from user
where id = 'hd';

select ifnull(count(*), 0)
from category
where id = 'hd' and no = '2';

select  ifnull(count(*), 0)
from post a join category b
	on a.category_no = b.no
where 
		b.id = 'hd' 
	and b.no = 2 
    and a.no = 1;


-- user insert (회원가입)
insert into user
values ("admin", "관리자", password(123123), "ADMIN", now());

-- login (로그인)
select id, name, password, join_date
from user
where id='hdd' and password = password(123123);

-- create (블로그 생성)
insert into blog
values ("id", "id님의 블로그 입니다.", "");

-- create (카테고리 생성)
insert into category
values(null, '미분류', '카테고리 설명 란', 0, hd);

-- getCategory (카테소리 선택)
select no, name, description, id
from category
where no = 9;

-- getCategoryPostCount (카테고리에 포스트 몇개 있는지)
select count(b.no) as countPost
from category a join post b
	on a.no = b.category_no
where a.no = 2;

-- getCategoryCount (카테고리 몇개 있는지)
select count(*)
from category
where id = 'hd';



-- getCategoryList (카테고리 리스트)
select a.no, a.name, a.id, a.description, count(b.no) as countPost
from category a left join post b
	on a.no = b.category_no
where id = 'hd'
group by a.no;

-- deleteCategory (카테고리 삭제)
delete
from category
where no = 1;

-- getPostList (게시글 리스트)
-- -- categoryNo 가 있을때
select no, title, contents, reg_date, category_no
from post
where category_no = 5;
-- -- categoryNo 가 없을때
select no, title, contents, reg_date, category_no
from post
where category_no = (
						select no
                        from category
                        where id = 'hd'
						order by no
                        limit 1
					);
                    
-- getPost (게시글)
-- -- postNo 가 있을때
select no, title, contents, reg_date, category_no
from post
where no = 3;
-- -- postNo 가 없을때
select no, title, contents, reg_date, category_no
from post
where category_no = (
						select no
                        from category
                        where id = 'hd'
						order by no
                        limit 1
					)
order by no
limit 1;

-- writePost (게시글 작성)
insert into post
values (null, 'title', 'contents', now(), (
											select no
                                            from category
                                            where name = 'name'
												and id = 'id'
											));