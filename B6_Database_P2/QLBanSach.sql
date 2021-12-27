create database if not exists QLBanSach;

use QLBanSach;

create table if not exists khachhang (
	ma_kh int auto_increment ,
    tai_khoan varchar(255) not null ,
    mat_khau varchar(255) not null,
    email varchar(255) not null,
    dia_chi varchar(255) ,
    dien_thoai varchar(100) not null , 
    gioi_tinh boolean not null,
    ngay_sinh date ,
    ho_ten varchar(255), 
    primary key (ma_kh)
);

create table if not exists don_hang(
	ma_don_hang int auto_increment,
    da_thanh_toan boolean default false,
    ngay_giao date not null,
    ngay_dat  date not null,
    tinh_trang varchar(255) ,
    primary key (ma_don_hang)
);

create table if not exists chu_de(
	ma_chu_de int auto_increment,
	ten_chu_de varchar(255),
    primary key (ma_chu_de)
);
create table if not exists nhaxuatban(
	ma_nsx int not null auto_increment,
    ten_nsx varchar(255) not null,
    diachi varchar(255) not null,
    dienthoai varchar(100) not null,
    primary key(ma_nsx)
);
create table if not exists tacgia(
	ma_tacgia int auto_increment,
    ten_tacgia varchar(255) not null,
    diachi varchar(255),
    tieusu varchar(255),
    dienthoai varchar(100),
    primary key(ma_tacgia)
);
create table if not exists sach(
	ma_sach int auto_increment,
    ten_sach varchar(255) not null,
    giaban float not null,
    mota varchar(255),
    anhbia_url varchar(255),
    ngaycapnhat datetime not null default CURRENT_TIMESTAMP,
    soluongton int,
    primary key(ma_sach)
);

create table if not exists don_hang_sach(
	ma_don_hang int not null,
    ma_sach int ,
    soluong int not null,
    dongia float not null,
    primary key(ma_don_hang,ma_sach)
);

alter table don_hang_sach  add constraint fk_ma_don_hang foreign key (ma_don_hang) references don_hang(ma_don_hang);
alter table don_hang_sach  add constraint fk_ma_sach_dh foreign key (ma_sach) references sach(ma_sach);

create table if not exists sach_tac_gia(
	ma_sach int not null,
    ma_tacgia int not null,
    vai_tro varchar(50),
    vi_tri varchar(50),
    primary key(ma_sach,ma_tacgia)
);
alter table sach_tac_gia add constraint fk_ma_sach_tg foreign key (ma_sach) references sach(ma_sach);
alter table sach_tac_gia add constraint fk_ma_tacgia foreign key(ma_tacgia) references tacgia(ma_tacgia);

alter table sach add column ma_chude int not null ;
alter table sach add constraint fk_ma_chude foreign key(ma_chude) references chu_de(ma_chu_de);

alter table sach add column ma_nsx int not null;
alter table sach add constraint fk_ma_nsx foreign key(ma_nsx) references nhaxuatban(ma_nsx);

alter table don_hang add column ma_kh int not null;
alter table  don_hang  add CONSTRAINT fk_ma_kh FOREIGN KEY(ma_kh) references khachhang(ma_kh);





