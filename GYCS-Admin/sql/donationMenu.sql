-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('捐款信息记录', '1', '1', 'donation', 'charity/donation/index', 1, 0, 'C', '0', '0', 'charity:donation:list', '#', 'admin', sysdate(), '', null, '捐款信息记录菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('捐款信息记录查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'charity:donation:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('捐款信息记录新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'charity:donation:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('捐款信息记录修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'charity:donation:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('捐款信息记录删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'charity:donation:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('捐款信息记录导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'charity:donation:export',       '#', 'admin', sysdate(), '', null, '');