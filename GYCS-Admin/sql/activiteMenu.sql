-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('公益活动溯源记录', '1', '1', 'activite', 'charity/activite/index', 1, 0, 'C', '0', '0', 'charity:activite:list', '#', 'admin', sysdate(), '', null, '公益活动溯源记录菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('公益活动溯源记录查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'charity:activite:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('公益活动溯源记录新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'charity:activite:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('公益活动溯源记录修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'charity:activite:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('公益活动溯源记录删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'charity:activite:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('公益活动溯源记录导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'charity:activite:export',       '#', 'admin', sysdate(), '', null, '');