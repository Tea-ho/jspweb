# DB설계
# 1. 회원 기능: 회원번호, 아이디, 비밀번호, 이메일
# 2. 친구 기능: No, 신청받는 회원번호, 신청하는 회원번호
# 3. 포인트 기능: 포인트 번호, 포인트 내역, 포인트 증감

use jspweb;

drop table if exists member;
create table member(
	mno int auto_increment primary key,
    mid varchar(30) not null unique,
    mpw varchar(30) not null,
    mimg longtext,
    memail varchar(100) not null unique
);

drop table if exists friend;
create table friend(
	fno int auto_increment primary key,
    ffrom int,
    fto int,
    foreign key( ffrom ) references member ( mno ) on delete set null,
    foreign key( fto ) references member( mno ) on delete set null
);

drop table if exists mpoint;
create table mpoint(
	mpno int auto_increment primary key,
    mpcomment varchar(1000) not null,
    mpamount int default 0,
    mno int,
    foreign key( mno ) references member (mno) on delete set null
);