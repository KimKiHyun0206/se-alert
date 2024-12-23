insert into permission (permission_name, notice, enroll)
values ('STUDENT', 0, 0);

insert into permission (permission_name, notice, enroll)
values ('STUDENT_LEAVE', 0, 0);

insert into permission (permission_name, notice, enroll)
values ('GRADUATE', 0, 0);

insert into permission (permission_name, notice, enroll)
values ('LAB_LEADER', 1, 1);

insert into permission (permission_name, notice, enroll)
values ('ADMIN', 1, 1);


insert into student (id, password, name, phone_number, about_me, permission)
values (202158037, 123456789, 'kim', '010-8380-8775', 'backend',5);
