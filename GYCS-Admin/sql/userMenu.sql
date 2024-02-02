-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户信息表', '1', '1', 'user', 'charity/user/index', 1, 0, 'C', '0', '0', 'charity:user:list', '#', 'admin', sysdate(), '', null, '用户信息表菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户信息表查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'charity:user:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户信息表新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'charity:user:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户信息表修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'charity:user:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户信息表删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'charity:user:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户信息表导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'charity:user:export',       '#', 'admin', sysdate(), '', null, '');